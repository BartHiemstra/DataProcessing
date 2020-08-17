package persistency;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OracleBaseDAO {

    //Connection info: driver, url, user and password to database.
    private final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private final String DB_URL = "jdbc:oracle:thin:@//localhost:1521/XEPDB1";
    private final String DB_USER = "OV";
    private final String DB_PASS = "hoidoei";

    protected Connection connection;
    protected PreparedStatement statement;

    //Register the Driver (Oracle in this case) and try to open a connection with the specified database.
    protected void openConnection() throws SQLException {
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        }
        catch (SQLException e) {
            throw e;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Close the connection with the database.
    public void closeConnection() throws SQLException {
        try {
            connection.close();
        }
        catch(SQLException e) {
            throw e;
        }
    }
}
