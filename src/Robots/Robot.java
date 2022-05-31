package Robots;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

import java.io.IOException;
import java.io.OutputStream;

public class Robot {
    private final String baudRate = "9600";
    private final String dataBits = "8";
    private final String stopBits = "1";
    private final String parityBits = "NO_PARITY";
    private int serialPortInt;
    private String dataBuffer="";
    private SerialPort serialPort;
    private OutputStream outputStream;
    private String textToPrint;

    public Robot(){
//        this.serialPort.setBaudRate(Integer.parseInt(baudRate));
//        this.serialPort.setNumDataBits((Integer.parseInt(dataBits)));
//        this.serialPort.setNumStopBits(Integer.parseInt(stopBits));
//        this.serialPort.setParity(0);
    }
    public void setComport(int serialPortInt){
        try{
            this.serialPortInt = serialPortInt;
            SerialPort portLists[] = SerialPort.getCommPorts();
            this.serialPort = portLists[serialPortInt];
            //System.out.println(comboBoxComport.getSelectedIndex());
            this.serialPort.setBaudRate(Integer.parseInt(baudRate));
            this.serialPort.setNumDataBits((Integer.parseInt(dataBits)));
            this.serialPort.setNumStopBits(Integer.parseInt(stopBits));
            this.serialPort.setParity(0);
            this.serialPort.openPort();
            Serial_EventBasedReading(serialPort);
//            System.out.println(serialPort);
            textToPrint = "Succes";
        }catch (ArrayIndexOutOfBoundsException e){
            textToPrint = "Canceled";
        }
    }

    public int getSerialPortInt() {
        return serialPortInt;
    }

    public SerialPort getSerialPort() {
        return serialPort;
    }

    public boolean sendInformation(String dataToSend){
        outputStream = serialPort.getOutputStream();
        try{
            outputStream.write(dataToSend.getBytes());
            return true;
        }catch (IOException e){
            return false;
        }
    }

    public String getText(int amountCharacters){
        String textToReturn = "";
        if(dataBuffer.length() > amountCharacters){
            textToReturn = dataBuffer.substring(dataBuffer.length()-amountCharacters);
            dataBuffer = "";
        }
        return textToReturn;
    }

    public String getText(){
        return getText(5);
    }

    public String getTextToPrint(){
        return textToPrint;
    }

    private void Serial_EventBasedReading(SerialPort activePort){
        activePort.addDataListener(new SerialPortDataListener() {
            @Override
            public int getListeningEvents() {
                return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
            }

            @Override
            public void serialEvent(SerialPortEvent serialPortEvent) {
                System.out.println(serialPortEvent.getEventType());
                byte []newData = serialPortEvent.getReceivedData();
                for (int i = 0; i < newData.length; i++){
                    dataBuffer += (char)newData[i];
                }
//                dataBuffer += "\n";
            }
        });
    }



}
