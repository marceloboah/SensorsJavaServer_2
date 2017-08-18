package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import connection.ConnectionUtil;
/**
 * 
 * @author Marcelo Boá
 * @since jan, 2012.
 * @version 1.3
 * @email marcelorosa1978@gmail.com
 * @doc TCC - Sistema de Automação Remota via WEB.
 * 
 */
public class Persist {
	public ResultSet Consult (String sql){
		
	Connection con = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
		try {
			con = ConnectionUtil.getConnection();
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			/*while (rs.next()) {
				System.out.println(rs.getInt("chavea"));
			}*/
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.freeConnection(con);
		}
		return rs;
	}
	
}