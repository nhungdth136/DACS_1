package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {

    public static Connection con = null;
    private static final ConnectDB instance = new ConnectDB();

    public static ConnectDB getInstance() {
        return instance;
    }

    public void connect() throws SQLException {

String url = "jdbc:mysql://localhost:3306/quanlykhachsan";
String user = "root"; 
String password = ""; 
con = DriverManager.getConnection(url, user, password);


//        System.out.println("connect database!!");
        System.out.println("connect database!!");
    }

    public static void disconnect() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static Connection getConnection() {
        return con;
    }
}
