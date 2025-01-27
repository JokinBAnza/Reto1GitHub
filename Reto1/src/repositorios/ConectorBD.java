package repositorios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectorBD {
	
   public static Connection conexion;
   
    //Metodos
    public static void conectar(){

        try{
            //Cargamos el driver, el driver es la libreria que nos permite conectarnos a la BD
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver cargado");        
            try{
      
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/MR_ROBOT","root","1DAW3_BBDD");
          
            System.out.println("Conexion establecida");
          
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
