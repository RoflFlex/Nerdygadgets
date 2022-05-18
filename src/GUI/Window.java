package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JButton cancelButton;
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
        SetInformationPanel(null);
        SetContentPanel(null);
        SetOrderPanel();
        SetPackingPanel();

        // setting the visibility and close operation
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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
}
