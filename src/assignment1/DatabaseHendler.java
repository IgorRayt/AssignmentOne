package assignment1;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DatabaseHendler {
	
	static final int MISSING_ARGUMENT_ERROR_CODE = 1;
    static final int UNHANDLED_EXCEPTION_ERROR_CODE = 2;

    static final String CONNECTION_STRING = "jdbc:derby://localhost:1527/CanadaCensusDB;create=true";
    
    Connection connection;
    
    Boolean connectionFailed = false;
	
	public   DatabaseHendler(String username, String password){
		
		 try
	        {
	            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance(); 
	        }
	     catch (Exception except)
	        {
	            except.printStackTrace();
	        }
		
		try
		{
			Connection tempConnection = OpenConnection(username, password);
			
			tempConnection.setAutoCommit(false);
	        tempConnection.createStatement().executeUpdate("SET SCHEMA APP");
			
			connection = tempConnection;

		}
		catch (SQLException e)
        {
            e.printStackTrace();

            System.exit(e.getErrorCode());
            
            connectionFailed = true;
        }
		
	}
	
	public boolean getConnectionStatus() {
		return connectionFailed;
	}
	
	public Connection getConnection() {
		return connection;
	}
	
	private static Connection OpenConnection(String username, String password) throws SQLException
    {

        Properties tempConnectionProperties = new Properties();
        tempConnectionProperties.put("user", username);
        tempConnectionProperties.put("password", password);

        Connection tempConnection = DriverManager.getConnection(CONNECTION_STRING, tempConnectionProperties);

        tempConnection.setAutoCommit(false);
        tempConnection.createStatement().executeUpdate("SET SCHEMA APP");

        return tempConnection;

    } 

}
