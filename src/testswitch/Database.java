package testswitch;
 
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
 
/**
 *
 * @author Ron 
 */
public class Database {
 
    private final static String DB_DRIVER_URL = "com.mysql.jdbc.Driver";
    private final static String DB_DRIVER_PREFIX = "jdbc:mysql://";
 
    private Connection connection = null;
 
    public Database(String dataBaseName, String serverURL, String userName, String passWord) {
        try {
            // verify that a proper JDBC driver has been installed and linked
            if (!selectDriver(DB_DRIVER_URL)) {
                return;
            }
 
            if (serverURL == null || serverURL.isEmpty()) {
                serverURL = "ronpelt.synology.me:3306";
            }
 
            // establish a connection to a named Database on a specified server
            connection = DriverManager.getConnection(DB_DRIVER_PREFIX + serverURL + "/" + dataBaseName, userName, passWord);
        } catch (SQLException eSQL) {
            logException(eSQL);
        }
    }
 
    private static boolean selectDriver(String driverName) {
        // Selects proper loading of the named driver for Database connections.
        // This is relevant if there are multiple drivers installed that match the JDBC type.
        try {
            Class.forName(driverName);
            // Put all non-prefered drivers to the end, such that driver selection hits the first
            Enumeration<Driver> drivers = DriverManager.getDrivers();
            while (drivers.hasMoreElements()) {
                Driver d = drivers.nextElement();
                if (!d.getClass().getName().equals(driverName)) {
                    // move the driver to the end of the list
                    DriverManager.deregisterDriver(d);
                    DriverManager.registerDriver(d);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            logException(ex);
            return false;
        }
        return true;
    }
 
    public void executeNonQuery(String query) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException eSQL) {
            logException(eSQL);
        }
    }
    
    public PreparedStatement prepareStatement(String query) throws SQLException {
         return connection.prepareStatement(query);
    }

 
    public ResultSet executeQuery(String query) {
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
           
            return result;
        } catch (SQLException eSQL) {
            logException(eSQL);
        }
 
        return null;
    }
 
    private static void logException(Exception e) {
        System.out.println(e.getClass().getName() + ": " + e.getMessage());
 
        e.printStackTrace();
    }
}