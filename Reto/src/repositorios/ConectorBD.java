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
        }finally {
        	
        }
    }
    
    public static void cerrarConexion() {
        try {
                conexion.close();
           
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }

    
    public static void getConexion() {
    	
    }

}
