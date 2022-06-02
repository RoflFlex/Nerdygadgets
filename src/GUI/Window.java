package GUI;

import Algoritmes.TSP.*;
import Klasses.Product;
import Panels.ItemRack;
import Panels.SortingLinePanel;
import Panels.TurningPanel;
import Robots.Robot;
import com.fazecast.jSerialComm.SerialPort;


import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

///<summary>
/// the window script handles all the base requirements for what needs to be in on screen.
/// basic buttons will be handled by this script, more in depth items will be handled by other scripts.
///
/// in case an optimisation is found for splitting the current four panels into their own scripts please do so.
///</summary>

public class Window extends JFrame implements ActionListener, PopupMenuListener, MouseListener {
    private JPanel window;
    public TSPAlgorithm tspAlgorithm = new OwnChoice(null);

    public Robot orderRobot = new Robot();
    private Robot packingRobot = new Robot();

    // all elements from the information window
    private JPanel informationWindow;
    private JTextPane information;

    // all elements from the content window
    private JPanel contentWindow;
    private JLabel contentLabel;
    private JComboBox comboBox1;

    // all elements from the order window (ophaal-robot)
    private JPanel orderWindow;
    private JButton informationButton;
    private JProgressBar progressBar1;
    private JButton ophalenButton;
    private JButton cancelButton;

    private JPanel grid;
    public ItemRack currentGrid;
//    private JButton itemButton;
//    private JButton itemButton1;
//    private JButton itemButton2;
//    private JButton itemButton3;
//    private JButton itemButton4;
//    private JButton itemButton5;
//    private JButton itemButton6;
//    private JButton itemButton7;
//    private JButton itemButton8;
//    private JButton itemButton9;
//    private JButton itemButton10;
//    private JButton itemButton11;
//    private JButton itemButton12;
//    private JButton itemButton13;
//    private JButton itemButton14;
//    private JButton itemButton15;
//    private JButton itemButton16;
//    private JButton itemButton17;
//    private JButton itemButton18;
//    private JButton itemButton19;
//    private JButton itemButton20;
//    private JButton itemButton21;
//    private JButton itemButton22;
//    private JButton itemButton23;
//    private JButton itemButton24;

//    private JButton[] itemButtons;

    private JComboBox comportOrder;
    private JComboBox tspAlgorithmComboBox;

    // all elements from the packing robot (inpak-robot)
    private JPanel packingWindow;
    private JButton informationButton2;

    private JPanel waitingLine;
    public SortingLinePanel sortingLinePanel;
//    private JButton itemButton25;
//    private JButton itemButton26;
//    private JButton itemButton29;
//    private JButton itemButton28;

    private JComboBox comportPacking;
    private JComboBox bppAlgorithmComboBox;
    private JPanel turning;
    private TurningPanel turningPanel;

    private boolean[] buttons;
    private Order order;
    private ArrayList<Order> orders = new ArrayList<>();

    private int orderIndex;

    public Window(String title){
        // setting all necessary window information
        setContentPane(window);
        setTitle(title);
        setSize(1500, 950);

        sortingLinePanel = new SortingLinePanel();
        waitingLine.setLayout(new GridLayout());

        setTurningPanel();

        waitingLine.add(sortingLinePanel);

//        currentGrid = new ItemRack();
        currentGrid = new ItemRack();
        grid.setLayout(new GridLayout());
        grid.add(currentGrid);
        // calling set functions for the four panels
        setInformationPanel("Select an order under \"Bestelling\"\nthis will automatically start that order");
        setOrderPanel();
        setPackingPanel();

        // setting button behaviours
//        SetOrderItemBehaviour();

        // setting dropdown behaviour
        setItemListeners();


        setComportOrder();
        setBppAlgorithmComboBox();
        setComportPacking();
        setTspAlgorithmComboBox();
        // setting the visibility and close operation
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        // setting some variables
        buttons = new boolean[]{false, false, false, false};
        newOrder(1);

    }

    private void setTurningPanel(){
        turningPanel = new TurningPanel();
        turning.setLayout(new FlowLayout());
        turning.add(turningPanel);
        turning.addMouseListener(this);
        turningPanel.addMouseListener(this);
        for(int i = 0; i < turningPanel.getBoxPanels().length; i++ ){
            turningPanel.getBoxPanels()[i].addMouseListener(this);
        }
    }

    private void setComportOrder(){
        comportOrder.addPopupMenuListener(this);
    }

    private void setTspAlgorithmComboBox(){
        tspAlgorithmComboBox.addPopupMenuListener(this);
    }

    private void setBppAlgorithmComboBox(){
        bppAlgorithmComboBox.addPopupMenuListener(this);
    }

    private void setComportPacking(){
        comportPacking.addPopupMenuListener(this);
    }

    // setup for all that is inside the information panel
    private void setInformationPanel(String content){
        information.setText(content);
    }

    // setup for all that is inside the order panel
    private void setOrderPanel(){
        // setup for information button
        informationButton.addActionListener(this);
    }

    // setup for all that is inside the packing panel
    private void setPackingPanel(){
        // setup for information button
        informationButton2.addActionListener(this);
    }

    // set behaviour for other functions and what a button does when you press it
//    private void SetOrderItemBehaviour() {
//        itemButtons = new JButton[25];
//        itemButtons[0] = itemButton;
//        itemButtons[1] = itemButton1;
//        itemButtons[2] = itemButton2;
//        itemButtons[3] = itemButton3;
//        itemButtons[4] = itemButton4;
//        itemButtons[5] = itemButton5;
//        itemButtons[6] = itemButton6;
//        itemButtons[7] = itemButton7;
//        itemButtons[8] = itemButton8;
//        itemButtons[9] = itemButton9;
//        itemButtons[10] = itemButton10;
//        itemButtons[11] = itemButton11;
//        itemButtons[12] = itemButton12;
//        itemButtons[13] = itemButton13;
//        itemButtons[14] = itemButton14;
//        itemButtons[15] = itemButton15;
//        itemButtons[16] = itemButton16;
//        itemButtons[17] = itemButton17;
//        itemButtons[18] = itemButton18;
//        itemButtons[19] = itemButton19;
//        itemButtons[20] = itemButton20;
//        itemButtons[21] = itemButton21;
//        itemButtons[22] = itemButton22;
//        itemButtons[23] = itemButton23;
//        itemButtons[24] = itemButton24;
//
//        for (int i = 0; i < 25; i++) {
////            int finalI = i;
//            itemButtons[i].addActionListener(this);
//        }
//
//        ophalenButton.addActionListener(this);
//        cancelButton.addActionListener(this);
//    }

    private void setContentText(int id){
        int index = 0;
        for(Order order : orders){
            int temp = order.getOrderID();
            if (temp == id){
                id = orders.indexOf(order);
                break;
            }
        }
        StringBuilder temp = new StringBuilder("<html>\n");
        for(Object[] objects : orders.get(id).getItems()){
            temp.append("Name: ").append(objects[1]).append(" | Location: ").append(objects[3]).append(" | weight: ").append(objects[4]).append(" <br/>\n");
        }
        temp.append("</html>");
        contentLabel.setText(temp.toString());

        System.out.println(temp);
    }
    private void setContentText(ArrayList<Product> products, int boxId){
        StringBuilder temp = new StringBuilder("<html>\nBox ID: " + boxId);
        for(Product product : products){
            temp.append("Name: ").append(product.getName()).append(" | Weight: ").append(product.getWeight()).append(" <br/>\n");
        }
        temp.append("</html>");
        contentLabel.setText(String.valueOf(temp));

        System.out.println(temp);
    }

    // setting up listeners for dropdown menus
    private void setItemListeners(){
        tspAlgorithmComboBox.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e){
                if(e.getStateChange()==ItemEvent.SELECTED){
                    //System.out.println(e.getItem());
                    if (e.getItem().equals("do it yourself")) {
                        new EasterEgg();
                        System.exit(0);
                    }
                }
            }
        });
        comportOrder.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e){
                if(e.getStateChange()==ItemEvent.SELECTED){
                    //System.out.println(e.getItem());
                }
            }
        });
        bppAlgorithmComboBox.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e){
                if(e.getStateChange()==ItemEvent.SELECTED){
                    //System.out.println(e.getItem());
                    if (e.getItem().equals("do it yourself")) {
                        new EasterEgg();
                        System.exit(0);
                    }
                }
            }
        });
        comportPacking.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e){
                if(e.getStateChange()==ItemEvent.SELECTED){
                    //System.out.println(e.getItem());
                }
            }
        });
        comboBox1.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e){
                if(e.getStateChange()==ItemEvent.SELECTED){
                    //System.out.println(e.getItem());
                    try {
                        int id = Integer.parseInt(e.getItem().toString());
                        setContentText(id);
                    } catch (NumberFormatException ex) {
                        System.out.println(e.getItem() + " does not contain an integer");
                    }
                }
            }
        });
    }

    public void setOrderIndex() {
        if (comboBox1.getSelectedItem().equals("OrderID")){
            return;
        }
        orderIndex = comboBox1.getSelectedIndex() - 1;
    }
    public void updateOrder(){
        comboBox1.removeItemAt(orderIndex + 1);
        orders.remove(orderIndex);
    }


    // get the selected order for processing
    public Order getSelectedOrder(){
        if (comboBox1.getSelectedItem().equals("OrderID")){
            return null;
        }
        Integer id = (Integer)comboBox1.getSelectedItem();
        System.out.println(id);
        for (Order temp : orders){
            if (temp.getOrderID() == id){
                return temp;
            }
        }
        System.out.println("no order found that matched that ID");
        return null;
    }

    // set color of button by index
//    public void SetColor(int index, Color color){
//        itemButtons[index].setForeground(color);
//    }

    // three functions to make new orders, use for database connection
    public void newOrder(int id){
        order = new Order(id);
    }
    public void addOrder(String itemName, String itemID, int rackPlacement, int weight){
        if (itemName.length() > 15) {
            itemName = itemName.substring(0, 15);
        }
        order.addItem(itemName, itemID, rackPlacement, weight);
    }
    public void finishOrder(){
        if (checkOrders()){
            comboBox1.addItem(order.getOrderID());
            orders.add(order);
        }
        order = new Order(orders.size() + 1);
    }
    private boolean checkOrders(){
        int id = order.getOrderID();
        for (Order order : orders){
            if (order.getOrderID() == id)
                return false;
        }
        return true;
    }

    // call ones the top order is finished
    public void updateOrders(){
        if (orders.size() < 1){
            System.out.println("can't remove NULL item, add one first");
            return;
        }
        comboBox1.removeItemAt(1);
        orders.remove(0);
    }

    // set button text based on the number you give it
//    public void setOrderItemText(int number, String text){
//        itemButtons[number].setText(text);
//    }

    // get button text bases on the number you give it
//    public String getOrderItemText(int number){
//        return itemButtons[number].getText();
//    }

    // set progress bar percentage from 0-100
    public void setProgressBar(int percentage){
        progressBar1.setValue(percentage);
    }

    // get dropdown values
    public String getComportOrder(){
        return comportOrder.getSelectedItem().toString();
    }
    public String getComportPacking(){
        return comportPacking.getSelectedItem().toString();
    }
    public String getAlgoritmOrder(){
        return tspAlgorithmComboBox.getSelectedItem().toString();
    }
    public String getAlgoritmPacking(){
        return bppAlgorithmComboBox.getSelectedItem().toString();
    }

    public SortingLinePanel getSortingLinePanel(){
        return sortingLinePanel;
    }
    public TurningPanel getTurningPanel(){
        return turningPanel;
    }

    // call to add a packing item to the line
//    public void addPackingItem(String text){
//        if (!buttons[0]){
//            itemButton28.setEnabled(true);
//            itemButton28.setText(text);
//            buttons[0] = true;
//        }
//        else if (!buttons[1]){
//            itemButton29.setEnabled(true);
//            itemButton29.setText(text);
//            buttons[1] = true;
//        }
//        else if (!buttons[2]){
//            itemButton26.setEnabled(true);
//            itemButton26.setText(text);
//            buttons[2] = true;
//        }
//        else if (!buttons[3]){
//            itemButton25.setEnabled(true);
//            itemButton25.setText(text);
//            buttons[3] = true;
//        }
//        else{
//            System.out.println("Line is currently full");
//        }
//    }

    // call ones an item get removed from the line (if it gets placed in a box)
//    public void MovePackingItems(){
//        itemButton28.setText(itemButton29.getText());
//        itemButton29.setText(itemButton26.getText());
//        itemButton26.setText(itemButton25.getText());
//        itemButton25.setText("_______");
//
//        for (int i = 0; i < 3; i++){
//            buttons[i] = buttons[i + 1];
//        }
//        buttons[3] = false;
//
//        itemButton28.setEnabled(buttons[0]);
//        itemButton29.setEnabled(buttons[1]);
//        itemButton26.setEnabled(buttons[2]);
//        itemButton25.setEnabled(buttons[3]);
//    }
//    public void enableButtons(int buttonID, String name){
//        itemButtons[buttonID].setEnabled(true);
//        if (name.length() > 15) {
//            name = name.substring(0, 15);
//        }
//        SetOrderItemText(buttonID, name);
//    }
//    public void disabledButtons(int buttonID) {
//        itemButtons[buttonID].setEnabled(false);
//        SetOrderItemText(buttonID, "_______________");
//    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
//        itemButtons = new JButton[25];
//        itemButtons[0] = itemButton;
//        itemButtons[1] = itemButton1;
//        itemButtons[2] = itemButton2;
//        itemButtons[3] = itemButton3;
//        itemButtons[4] = itemButton4;
//        itemButtons[5] = itemButton5;
//        itemButtons[6] = itemButton6;
//        itemButtons[7] = itemButton7;
//        itemButtons[8] = itemButton8;
//        itemButtons[9] = itemButton9;
//        itemButtons[10] = itemButton10;
//        itemButtons[11] = itemButton11;
//        itemButtons[12] = itemButton12;
//        itemButtons[13] = itemButton13;
//        itemButtons[14] = itemButton14;
//        itemButtons[15] = itemButton15;
//        itemButtons[16] = itemButton16;
//        itemButtons[17] = itemButton17;
//        itemButtons[18] = itemButton18;
//        itemButtons[19] = itemButton19;
//        itemButtons[20] = itemButton20;
//        itemButtons[21] = itemButton21;
//        itemButtons[22] = itemButton22;
//        itemButtons[23] = itemButton23;
//        itemButtons[24] = itemButton24;
        if(actionEvent.getSource() == informationButton){
            setInformationPanel("information about order panel\n\n" +
                                        "First step is to select the correct USB connection using the COMPORT dropdown.\n" +
                                        "Ones you have selected one you can pick an algorithm or leave it as is.\n" +
                                        "Then you can select an order using the \"Bestelling\" dropdown,\n" +
                                        "this wil start the order automatically.");
        }
        if(actionEvent.getSource() == informationButton2){
            setInformationPanel("information about packing panel\n\n" +
                                        "First step is to select the correct USB connection using the COMPORT dropdown.\n" +
                                        "Ones you have selected one you can pick an algorithm or leave it as is.\n" +
                                        "When you selected an order it will be shown in the order line and inside the boxes.\n" +
                                        "To see what is in a box just press it and its content will be shown.\n" +
                                        "This whole process is automatic and requires no further input.");
        }
        if(actionEvent.getSource() == ophalenButton){
            finishOrder();
        }
        if(actionEvent.getSource() == cancelButton){
            updateOrders();
        }

//        for (int i = 0; i < 25; i++) {
//            if(actionEvent.getSource() == itemButtons[i]){
//                order.AddItem(itemButtons[i].getText(), "id", i);
//            }
//        }
    }

    @Override
    public void popupMenuWillBecomeVisible(PopupMenuEvent popupMenuEvent) {
        if(comportOrder == popupMenuEvent.getSource()){
            comportOrder.removeAllItems();
            comportOrder.addItem("COMPORT");
            SerialPort portList[] =  SerialPort.getCommPorts();
            for(SerialPort port : portList){
                comportOrder.addItem(port.getSystemPortName());
            }
            if(packingRobot.getSerialPort() != null){
                comportOrder.remove(packingRobot.getSerialPortInt()+1);
            }
        }
        if(comportPacking == popupMenuEvent.getSource()){
            comportPacking.removeAllItems();
            comportPacking.addItem("COMPORT");
            SerialPort portList[] =  SerialPort.getCommPorts();

            for(SerialPort port : portList){
                comportPacking.addItem(port.getSystemPortName());
            }

            if(orderRobot.getSerialPort() != null){
                comportPacking.removeItemAt(orderRobot.getSerialPortInt()+1);
            }
        }
    }

    @Override
    public void popupMenuWillBecomeInvisible(PopupMenuEvent popupMenuEvent) {
        if(popupMenuEvent.getSource() == comportOrder ){
            int comport = comportOrder.getSelectedIndex();
            if(comportOrder.getSelectedIndex() != 0){
                orderRobot.setComport(comport-1);
                if(orderRobot.getTextToPrint().equals("Succes")){
                    comportOrder.setEnabled(false);
                }
            }

        }
        if(popupMenuEvent.getSource() == comportPacking){
            int comport = comportPacking.getSelectedIndex();
            if(comportOrder.getSelectedIndex() != 0){
                packingRobot.setComport(comport-1);
                if(packingRobot.getTextToPrint().equals("Succes")){
                    comportPacking.setEnabled(false);
                }
            }
        }
        if(popupMenuEvent.getSource() == tspAlgorithmComboBox){
            int tsp = tspAlgorithmComboBox.getSelectedIndex();
            if(tsp == 0){
                tspAlgorithm = new NearestInsertion(null);
            }
            if(tsp == 1){
                tspAlgorithm = new TwoOpt();
            }
            if (tsp == 2){
                tspAlgorithm = new NearestNeighbour(null);
            }
            if(tsp == 3){
                tspAlgorithm = new OwnChoice(null);
            }
            System.out.println(tspAlgorithm.getClass());
        }
    }

    @Override
    public void popupMenuCanceled(PopupMenuEvent popupMenuEvent) {
//        System.out.println("ss");
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
//        for (int i = 0; i < 25; i ++){
//            if(mouseEvent.getSource() == currentGrid.getProductPanels()[i]){
//                currentGrid.getProductPanels()[i].setChosen();
////                System.out.println("Clicked" + i);
//                currentGrid.getProductPanels()[i].repaint();
//            }
//        }

//        currentGrid.repaint();
        for(int i = 0; i < turningPanel.getBoxPanels().length; i++){
            if(mouseEvent.getSource() == turningPanel.getBoxPanels()[i]){
                System.out.println(turningPanel.getBoxes().get(i));
                setContentText(turningPanel.getBoxes().get(i).getProducts(), i);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
