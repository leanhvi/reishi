package reishi.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import reishi.config.ReishiConfig;

/**
 *
 * @author manhc
 */
public class MySqlConnectionSingleton {
    private static MySqlConnectionSingleton instance = null;
    private static Connection connect = null;
    
    public static MySqlConnectionSingleton getInstance() {
        if(instance == null) {
            instance = new MySqlConnectionSingleton();
        }
        return instance;
    }
    
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        if(connect == null) {
            ReishiConfig config = new ReishiConfig();
            Class.forName("com.mysql.jdbc.Driver");      
            String connectionString = config.mySqlConnection;
            connect = DriverManager.getConnection(connectionString);
            return connect;
        }
        return connect;        
    }
}
