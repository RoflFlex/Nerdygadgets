
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.text.DefaultCaret;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;

public class MainFrame extends JFrame implements ActionListener, PopupMenuListener {
    private JComboBox comboBoxComport;
    private JProgressBar progressBarComStatus;
    private JTextArea textAreaIncomingData;
    private JButton butOpen;
    private JButton butClose;
    private JButton butSend;
    private JPanel Cum;
    private JTextField textFieldDataToSend;
    private JButton butLicht;
    private JScrollPane scrolPane;
    private SerialPort serialPort1;
    private OutputStream outputStream1;
    private String baudRate = "9600", dataBits = "8", stopBits = "1", parityBits = "NO_PARITY", endLine = "None";
    private String dataBuffer;
    private boolean removeNull = false;

    public MainFrame(){
        setContentPane(Cum);
        setSize(500,250);
        comboBoxComport.addPopupMenuListener(this);
        comboBoxComport.setEnabled(true);
        progressBarComStatus.setValue(0);
        butLicht.setVisible(false);
        //butLicht.setEnabled(false);
        butOpen.setEnabled(true);
        butClose.setEnabled(false);
        butSend.setEnabled(false);
        butLicht.addActionListener(this);
        butSend.addActionListener(this);
        butClose.addActionListener(this);
        butOpen.addActionListener(this);
        JTextArea textArea= new JTextArea();

        DefaultCaret caret = (DefaultCaret)textAreaIncomingData.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        scrolPane= new JScrollPane(textArea);
        scrolPane.setAutoscrolls(true);
        scrolPane. setVerticalScrollBarPolicy( JScrollPane. VERTICAL_SCROLLBAR_ALWAYS );
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        comboBoxComport.removeAllItems();

    }
    public static void main(String[] args) {
        new MainFrame();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == butOpen){
            try{
                SerialPort portLists[] = SerialPort.getCommPorts();
                serialPort1 = portLists[comboBoxComport.getSelectedIndex()];
                serialPort1.setBaudRate(Integer.parseInt(baudRate));
                serialPort1.setNumDataBits((Integer.parseInt(dataBits)));
                serialPort1.setNumStopBits(Integer.parseInt(stopBits));
                serialPort1.setParity(0);
                serialPort1.openPort();
                if(serialPort1.isOpen()){
                    JOptionPane.showMessageDialog(this,serialPort1.getDescriptivePortName() + " -- Success to OPEN!" );
                    progressBarComStatus.setValue(100);
                    butOpen.setEnabled(false);
                    butClose.setEnabled(true);
                    butSend.setEnabled(true);
                    butLicht.setEnabled(true);
                    Serial_EventBasedReading(serialPort1);
                }else{
                    JOptionPane.showMessageDialog(this,serialPort1.getDescriptivePortName() + " -- Failed to OPEN!" );
                }
            }catch (ArrayIndexOutOfBoundsException e){
                JOptionPane.showMessageDialog(this, " Please choose COMPOR!","ERROR", JOptionPane.ERROR_MESSAGE);
            }
            catch (Exception b){
                JOptionPane.showMessageDialog(this, b, "ERROR", JOptionPane.ERROR_MESSAGE);

            }
        }
        if(actionEvent.getSource() == butClose){
            if(serialPort1.isOpen()){
                serialPort1.closePort();
                progressBarComStatus.setValue(0);
                butOpen.setEnabled(true);
                butClose.setEnabled(false);
                butSend.setEnabled(false);
                butLicht.setEnabled(false);
            }
        }
        if(actionEvent.getSource() == butSend){
            outputStream1 = serialPort1.getOutputStream();
            String dataToSend = "";
            dataToSend = textFieldDataToSend.getText();
            textFieldDataToSend.setText("");
            try{
                outputStream1.write(dataToSend.getBytes());

            }catch (IOException e){
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
        if(actionEvent.getSource() == butLicht){
            outputStream1 = serialPort1.getOutputStream();
            String dataToSend = "0";
            if(butLicht.getText().equals("Aan")){
                dataToSend = "1";
                butLicht.setText("Uit");
            }else{
                butLicht.setText("Aan");
            }
            try{
                outputStream1.write(dataToSend.getBytes());

            }catch (IOException e){
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
    }

    private void Serial_EventBasedReading(SerialPort activePort){
        activePort.addDataListener(new SerialPortDataListener() {
            @Override
            public int getListeningEvents() {
                return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
            }

            @Override
            public void serialEvent(SerialPortEvent serialPortEvent) {
                //System.out.println(serialPortEvent.getEventType());
                byte []newData = serialPortEvent.getReceivedData();
                for (int i = 0; i < newData.length; i++){
                        dataBuffer += (char)newData[i];
                        if (!removeNull){
                            dataBuffer = "";
                            removeNull = true;
                        }
                        textAreaIncomingData.setText(dataBuffer);
                        if (dataBuffer.length() >= 200) {
                            dataBuffer = dataBuffer.substring(100);
                        }

                }
//                dataBuffer += "\n";
            }
        });

    }

    @Override
    public void popupMenuWillBecomeVisible(PopupMenuEvent popupMenuEvent) {
        if(comboBoxComport == popupMenuEvent.getSource()){
            comboBoxComport.removeAllItems();
            SerialPort portList[] =  SerialPort.getCommPorts();
            for(SerialPort port : portList){
                comboBoxComport.addItem(port.getSystemPortName());

            }
        }
    }

    @Override
    public void popupMenuWillBecomeInvisible(PopupMenuEvent popupMenuEvent) {

    }

    @Override
    public void popupMenuCanceled(PopupMenuEvent popupMenuEvent) {

    }
}

