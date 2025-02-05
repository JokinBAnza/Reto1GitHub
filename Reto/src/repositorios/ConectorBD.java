package repositorios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectorBD {
	
   public static Connection conexion;
   
    public static void conectar(){

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver cargado");        
            try{
      
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/MR_ROBOT","root","1DAW3_BBDD");
          
            System.out.println("Conexion establecida");
            System.out.println();
          
        }catch(Exception e){
            System.out.println("Error en la conexion");
        }
        }catch(Exception e){
            System.out.println("Error en el driver");
        }
    }
    
    public static void cerrarConexion() throws SQLException {
    	conexion.close();
    }
    
    public static void getConexion() {
    	
    }

}
