
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Database {
    private static String DB_URL = "jdbc:mysql://localhost:3306/nerdygadgets";
    private static String DB_USERNAME = "root" ;
    private static String DB_PASSWORD = "" ;
    private static Connection connection ;

    static ArrayList<ArrayList<String>> executeQuery(String query) throws SQLException{
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        if (connection.isClosed()){
            openConnection();
        }
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
        return  result;
    }

    private static void openConnection() throws SQLException{
        connection = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
    }

    private static Connection getConnection() throws SQLException {
        if(connection.isClosed()){
            openConnection();
        }
        return connection;
    }
    private static void closeConnection() throws SQLException{
        connection.close();
    }
}
//C:\xampp\mysql\data\nerdygadgets