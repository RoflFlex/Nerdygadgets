package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

///<summary>
/// the window script handles all the base requirements for what needs to be in on screen.
/// basic buttons will be handled by this script, more in depth items will be handled by other scripts.
///
/// in case an optimisation is found for splitting the current four panels into their own scripts please do so.
///</summary>

public class Window extends JFrame{
    private JPanel window;

    // all elements from the information window
    private JPanel informationWindow;
    private JTextPane information;

    // all elements from the content window
    private JPanel contentWindow;
    private JTable table1;

    // all elements from the order window (ophaal-robot)
    private JPanel orderWindow;
    private JButton informationButton;
    private JProgressBar progressBar1;
    private JButton ophalenButton;
    private JButton cancelButton;

    private JPanel grid;
    private JButton itemButton;
    private JButton itemButton1;
    private JButton itemButton2;
    private JButton itemButton3;
    private JButton itemButton4;
    private JButton itemButton5;
    private JButton itemButton6;
    private JButton itemButton7;
    private JButton itemButton8;
    private JButton itemButton9;
    private JButton itemButton10;
    private JButton itemButton11;
    private JButton itemButton12;
    private JButton itemButton13;
    private JButton itemButton14;
    private JButton itemButton15;
    private JButton itemButton16;
    private JButton itemButton17;
    private JButton itemButton18;
    private JButton itemButton19;
    private JButton itemButton20;
    private JButton itemButton21;
    private JButton itemButton22;
    private JButton itemButton23;
    private JButton itemButton24;

    private JButton[] itemButtons;
    private String[] buttonText;

    private JComboBox comportOrder;
    private JComboBox algoritmOrder;

    // all elements from the packing robot (inpak-robot)
    private JPanel packingWindow;
    private JButton informationButton2;

    private JPanel waitingLine;
    private JButton itemButton25;
    private JButton itemButton26;
    private JButton itemButton29;
    private JButton itemButton28;

    private JButton boxButton;
    private JButton boxButton1;
    private JButton boxButton2;
    private JButton boxButton3;

    private JComboBox comportPacking;
    private JComboBox algoritmPacking;
    private JComboBox comboBox1;

    public Window(String title){
        // setting all necessary window information
        setContentPane(window);
        setTitle(title);
        setSize(1500, 844);

        // calling set functions for the four panels
        SetInformationPanel("Test build");
        SetContentPanel(null);
        SetOrderPanel();
        SetPackingPanel();

        // setting button behaviours
        SetOrderItemBehaviour();

        // setting dropdown behaviour
        SetItemListeners();

        // setting the visibility and close operation
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    // setup for all that is inside the information panel
    private void SetInformationPanel(String content){
        information.setText(content);
    }

    // setup for all that is side the content panel
    private void SetContentPanel(String content){
        // do something...
    }

    // setup for all that is inside the order panel
    private void SetOrderPanel(){
        // setup for information button
        informationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SetInformationPanel("information about order panel");
            }
        });
    }

    // setup for all that is inside the packing panel
    private void SetPackingPanel(){
        // setup for information button
        informationButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SetInformationPanel("information about packing panel");
            }
        });
    }

    // set button text based on the number you give it
    public void SetOrderItemText(int number, String text){
        itemButtons[number].setText(text);
        buttonText[number] = text;
    }

    // get button text bases on the number you give it
    public String GetOrderItemText(int number){
        return itemButtons[number].getText();
    }

    // set progress bar percentage from 0-100
    public void SetProgressBar(int percentage){
        progressBar1.setValue(percentage);
    }

    // set table content
    // currently doesn't work
    public void SetTabelContent(String[] names, String[][] data){
        table1 = new JTable(data, names);
    }

    // get dropdown values
    public String GetComportOrder(){
        return comportOrder.getSelectedItem().toString();
    }
    public String GetComportPacking(){
        return comportPacking.getSelectedItem().toString();
    }
    public String GetAlgoritmOrder(){
        return algoritmOrder.getSelectedItem().toString();
    }
    public String GetAlgoritmPacking(){
        return algoritmPacking.getSelectedItem().toString();
    }

    // set behaviour for other functions and what a button does when you press it
    private void SetOrderItemBehaviour() {
        itemButtons = new JButton[25];
        itemButtons[0] = itemButton;
        itemButtons[1] = itemButton1;
        itemButtons[2] = itemButton2;
        itemButtons[3] = itemButton3;
        itemButtons[4] = itemButton4;
        itemButtons[5] = itemButton5;
        itemButtons[6] = itemButton6;
        itemButtons[7] = itemButton7;
        itemButtons[8] = itemButton8;
        itemButtons[9] = itemButton9;
        itemButtons[10] = itemButton10;
        itemButtons[11] = itemButton11;
        itemButtons[12] = itemButton12;
        itemButtons[13] = itemButton13;
        itemButtons[14] = itemButton14;
        itemButtons[15] = itemButton15;
        itemButtons[16] = itemButton16;
        itemButtons[17] = itemButton17;
        itemButtons[18] = itemButton18;
        itemButtons[19] = itemButton19;
        itemButtons[20] = itemButton20;
        itemButtons[21] = itemButton21;
        itemButtons[22] = itemButton22;
        itemButtons[23] = itemButton23;
        itemButtons[24] = itemButton24;

        buttonText = new String[25];
        for (JButton button : itemButtons) {
            button.addActionListener(new ActionListener() {
                private int index = 0;

                @Override
                public void actionPerformed(ActionEvent e) {
                    buttonText[index] = button.getText();
                    System.out.println(buttonText[index]);
                    index++;
                }
            });
        }
    }

    // setting up listeners for dropdown menus
    private void SetItemListeners(){
        algoritmOrder.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e){
                if(e.getStateChange()==ItemEvent.SELECTED){
                    System.out.println(e.getItem());
                }
            }
        });
        comportOrder.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e){
                if(e.getStateChange()==ItemEvent.SELECTED){
                    System.out.println(e.getItem());
                }
            }
        });
        algoritmPacking.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e){
                if(e.getStateChange()==ItemEvent.SELECTED){
                    System.out.println(e.getItem());
                }
            }
        });
        comportPacking.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e){
                if(e.getStateChange()==ItemEvent.SELECTED){
                    System.out.println(e.getItem());
                }
            }
        });
    }
}
