package Old;

import javax.swing.*;

public class LegendaGUI extends JFrame{
    private JPanel legendaPanel;
    private JLabel infoField;

    public LegendaGUI(String info){
        setContentPane(legendaPanel);
        setTitle("Legenda");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

        if (!info.equals(""))
            infoField.setText(info);
    }
}
