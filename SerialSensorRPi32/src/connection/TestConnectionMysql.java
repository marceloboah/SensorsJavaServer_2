package connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 * 
 * @author Marcelo Boá
 * @since jan, 2012.
 * @version 1.3
 * @email marcelorosa1978@gmail.com
 * @doc TCC - Sistema de Automação Remota via WEB.
 * 
 */
public class TestConnectionMysql
{
    public static void main(String[] args) 
    {
      String url = "jdbc:mysql://localhost:3306/db_monitoring";
      String login = "root";
      String senha = "";

        try 
        {
         Class.forName("com.mysql.jdbc.Driver").newInstance();
            System.out.println("\nDriver carregado com sucesso!\n");
         try
         {
                 Connection conn = DriverManager.getConnection(url, login, senha);
                 try
                 {
                	 //String sql = "SELECT codigo,nome FROM teste";
                	 String sql = "SELECT chavea,chaves FROM tb_status_now WHERE id=1";
                	 Statement stm = conn.createStatement();
                    try
                    {
                     ResultSet rs = stm.executeQuery(sql);
                  while (rs.next()) 
                  {
                     int nome = rs.getInt("chavea");
                     int codigo = rs.getInt("chaves");
                     System.out.println("chavea: " + codigo + "\nchaves: " +nome);
                     System.out.println("---------------------------------------");
                  }
                  System.out.println("\nConsulta realizada com sucesso!!!\n");                     
                    }
               catch (Exception ex) 
               {
                  System.out.println("\nErro no resultset!");
               }
                 }
                  catch (Exception ex) 
            {
               System.out.println("\nErro no statement!");
            }
         }
         catch (Exception ex) 
         {
            System.out.println("\nErro no connection!");
         }   
        }
        catch (Exception ex) 
        {
            System.out.println("\nDriver nao pode ser carregado!");
        }
    }
}
