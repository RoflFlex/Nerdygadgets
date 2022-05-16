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

    // all elements from the order window (ophaal-robot)
    private JPanel orderWindow;
    private JButton informationButton;

    // all elements from the packing robot (inpak-robot)
    private JPanel packingWindow;
    private JButton informationButton2;

    // ongeordered:
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    private JButton button10;
    private JButton button11;
    private JButton button12;
    private JButton button13;
    private JButton button14;
    private JButton button15;
    private JButton button16;
    private JButton button17;
    private JButton button18;
    private JButton button19;
    private JButton button20;
    private JButton button21;
    private JButton button22;
    private JButton button23;
    private JButton button24;
    private JButton button25;
    private JTable table1;
    private JProgressBar progressBar1;
    private JButton ophalenButton;
    private JButton cancelButton;
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
