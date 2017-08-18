package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



/**
 * 
 * @author Marcelo Bo�
 * @since jan, 2012.
 * @version 1.3
 * @email marcelorosa1978@gmail.com
 * @doc TCC - Sistema de Automa��o Remota via WEB.
 * 
 */
public class ConnectionUtil {
	private static ConnectionUtil connectionUtil;
	String url;
	String user;
	String password;
	

	
	private ConnectionUtil() {
		super();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//StrackTraceAppender.appendToFile(e);
		}
		
		url = "jdbc:mysql://localhost:3306/db_sctdht";
		//url = "jdbc:mysql://beaglebone:3306/db_monitoring";
	    user = "root";
		//user = "root";
	    password = "root";
		//password = "";
	}
	
	public static Connection getConnection() throws SQLException {
		if (connectionUtil == null) {
			connectionUtil = new ConnectionUtil();
		}
		Connection con = DriverManager.getConnection(connectionUtil.url, connectionUtil.user, connectionUtil.password);
		return con;
	}
	
	public static void freeConnection(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
