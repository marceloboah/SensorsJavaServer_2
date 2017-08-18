package controler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import connection.ConnectionUtil;
import logger.StrackTraceAppender;
import model.Config;
import persistence.Persist;

public class ConfigDao {

	public ConfigDao() {
		super();
		
	}
	Persist persist = new Persist();
	Connection con = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	Config config;
	
	public void update(Config config) {
		try {
			con = ConnectionUtil.getConnection();
			stmt = con.prepareStatement("UPDATE tb_config  SET con_par='"+config.getCon_par()+
																		"' WHERE con_id="+config.getCon_id());
				
			stmt.executeUpdate("UPDATE tb_config  SET con_par='"+config.getCon_par()+
					"' WHERE con_id="+config.getCon_id());
				
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			StrackTraceAppender.appendToFile(e);
		} finally {
			ConnectionUtil.freeConnection(con);
		}
		
	}
	
	public void updateTable(String parametro) {
		try {
			con = ConnectionUtil.getConnection();
			String frase = parametro;
			String array[] = new String[10];
			array = frase.split("=");
			/*System.out.println(array[0]);
			System.out.println(array[1]);
			System.out.println(array[2]);
			System.out.println(array[3]);
			System.out.println(array[4]);
			System.out.println(array[5]);
			System.out.println(array[6]);
			System.out.println(array[7]);*/
			double dht_temperature=Double.parseDouble(array[1]);
			double dht_humidity=Double.parseDouble(array[3]);
			double sct_current=Double.parseDouble(array[5]);
			double sct_powerrate=Double.parseDouble(array[7]);
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 
			Date date=new Date();
			//Timestamp dt_created= new Timestamp(date.getTime());
			
			String dt_created=dateFormat.format(date.getTime());
			//Timestamp dthora= new Timestamp(data);
			
			stmt = con.prepareStatement("INSERT INTO tb_sensors (`dht_humidity`, `dht_temperature`, `sct_current`, `sct_powerrate`, `dt_created`) "
					+ "VALUES ("+dht_humidity+","+dht_temperature+","+sct_current+","+sct_powerrate+",'"+dt_created+"')  ");
			
				
			stmt.executeUpdate("INSERT INTO tb_sensors (`dht_humidity`, `dht_temperature`, `sct_current`, `sct_powerrate`, `dt_created`) "
					+ "VALUES ("+dht_humidity+","+dht_temperature+","+sct_current+","+sct_powerrate+",'"+dt_created+"')  ");
				
			
			
			
			System.out.println("parametro em ConfigDao="+parametro);
		} catch (Exception e) {
			e.printStackTrace();
			StrackTraceAppender.appendToFile(e);
			System.out.println(e.getMessage());
		} finally {
			ConnectionUtil.freeConnection(con);
			System.out.println("OK");
		}
		
	}
	
	public void esvaziaBalanca() {
		try {
			con = ConnectionUtil.getConnection();
			stmt = con.prepareStatement("UPDATE tb_config  SET con_par='' WHERE con_id=2");
				
			stmt.executeUpdate("UPDATE tb_config  SET con_par='' WHERE con_id=2");
				
			
		} catch (Exception e) {
			e.printStackTrace();
			StrackTraceAppender.appendToFile(e);
			System.out.println(e.getMessage());
		} finally {
			ConnectionUtil.freeConnection(con);
			System.out.println("OK");
		}
		
	}
	
	public Config retornaPesoBalanca() {
		try {
			con = ConnectionUtil.getConnection();
			stmt = con.prepareStatement("SELECT * FROM tb_config  WHERE con_id=2");
			rs = stmt.executeQuery();
			
			
			while (rs.next()) {
				config = new Config();
				
				String con_par=rs.getString("con_par");
				config.setCon_par(con_par);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			StrackTraceAppender.appendToFile(e);
			System.out.println(e.getMessage());
		} finally {
			ConnectionUtil.freeConnection(con);
			System.out.println("OK");
		}
		return config;
		
	}
	
}
