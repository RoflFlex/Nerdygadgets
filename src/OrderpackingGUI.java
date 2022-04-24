import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderpackingGUI extends JFrame{
    private JButton infoBut;
    private JButton terugBut;
    private JButton a2Button;
    private JButton a1Button;
    private JButton a3Button;
    private JButton a4Button;
    private JButton doos3Button;
    private JButton doos2Button;
    private JButton doos1Button;
    private JLabel rijText;
    private JPanel OrderPackingPanel;
    private JPanel draaiPanel;
    private JPanel rijPanel;

    public OrderpackingGUI(){
        setContentPane(OrderPackingPanel);
        setTitle("Order inpakken");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }

    public static void main(String[] args) {
        JFrame fr = new OrderpackingGUI();
    }
}
