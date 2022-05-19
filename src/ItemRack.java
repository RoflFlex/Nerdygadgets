import GUI.Window;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemRack {
    public ItemRack(Window window){
     //   Database waardes ophalen
        // Kleuren
        // Disabelen
        ArrayList<ArrayList<String>> Stellingen = new ArrayList<>();
        try {
            Stellingen = Database.executeQuery("SELECT StellingID,StockItemName FROM nerdygadgets.stockitems\n" +
                    "WHERE StellingID IS NOT NULL;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(Stellingen);
        for (ArrayList<String> stelling: Stellingen) {
           // for (String stellingID:stelling) {
                window.enableButtons(Integer.parseInt(stelling.get(0)),stelling.get(1));
            //}
        }
    }
}
