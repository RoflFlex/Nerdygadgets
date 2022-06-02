package Database;

import GUI.Window;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class Database {
    private static String DB_URL = "jdbc:mysql://localhost:3306/nerdygadgets";
    private static String DB_USERNAME = "root" ;
    private static String DB_PASSWORD = "" ;
    private static Connection connection ;

    public static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";

    public static ArrayList<ArrayList<String>> executeQuery(String query) throws SQLException{
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        openConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet=statement.executeQuery(query);
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnCount = rsmd.getColumnCount();

        while (resultSet.next()){
            ArrayList<String > row = new ArrayList<>();
            for(int j = 1; j <= columnCount; j++){
                row.add(resultSet.getString(j));
            }
            result.add(row);
        }
        closeConnection();
        return result;
    }

    public static int executeUpdate(String query) throws SQLException{
        openConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        closeConnection();
        return statement.executeUpdate();
    }

    private static void openConnection() throws SQLException{
        if(connection == null || connection.isClosed()) {

            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        }
    }

    private static Connection getConnection() throws SQLException {
        openConnection();
        return connection;
    }
    private static void closeConnection() throws SQLException{
        if(!connection.isClosed()){
            connection.close();
        }
    }
    public static void getOrders(Window window){
        ArrayList<ArrayList<String>> Order = new ArrayList<>();
        try {
            Order = Database.executeQuery("SELECT OrderID FROM nerdygadgets.orders\n" +
                    "where PickingCompletedWhen IS NULL\n" +
                    "order by OrderID desc;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (ArrayList<String> Order1: Order) {
            for (String OrderID:Order1) {
                if (!(OrderID == null)) {
                    window.newOrder(Integer.parseInt(OrderID));
                    ArrayList<ArrayList<String>> OrderLine = new ArrayList<>();
                    try {
                        OrderLine = Database.executeQuery("SELECT OrderID, StockItemID, Description FROM nerdygadgets.orderlines\n" +
                                "where OrderId = " + OrderID + "\n" +
                                "order by orderlineID desc;");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    for (ArrayList<String> OrderLine1: OrderLine) {
                        window.addOrder(OrderLine1.get(2), OrderLine1.get(1), 5, 10); // do add rackplacement and weight to it
                        window.finishOrder();
                    }
                }
            }
        }
    }

    public static void updateOrder(String orderID){
        try {
            Database.executeQuery("UPDATE nerdygadgets.orderlines\n" +
                    "SET PickingCompletedWhen='" + now() + "'\n" +
                    "WHERE OrderId='" + orderID + "';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String now() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return sdf.format(cal.getTime());
    }
}
//C:\xampp\mysql\data\nerdygadgets