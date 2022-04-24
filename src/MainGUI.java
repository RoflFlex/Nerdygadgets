import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame{
    private JButton orderPickerButton;
    private JButton orderPackingButton;
    private JButton iButton;
    private JPanel mainGUI;

    public MainGUI(){
        setContentPane(mainGUI);
        setTitle("ASRS");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        orderPickerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // open de orderpicking window
            }
        });
        orderPackingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // open de orderpacking window
            }
        });
        iButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // open de legenda
                new LegendaGUI("<html>" +
                                        "Druk op de \"OrderPicker\" knop om het order picker window te openen" +
                                            "<br/>" +
                                        "Druk op de \"OrderPacking\" knop om het order packing window te openen" +
                                            "<br/>" +
                                            "<br/>" +
                                        "Voor informatie in elke window kun je weer op de \"i\" knop drukken" +
                                            "<br/>" +
                                        "Om de aplicatie te sluiten kun je op de \"X\" knop drukken" +
                                    "</html>");
            }
        });
    }
}
