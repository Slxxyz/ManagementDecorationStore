
package dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnection {
    private static Connection connection;
    public static Connection getInstance() throws SQLException {
        try {
            if (connection == null) {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/storemanager_db", "root", "1Geniheure03");
            }
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
        return connection;
    }
}