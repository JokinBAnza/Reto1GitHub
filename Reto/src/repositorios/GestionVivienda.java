package repositorios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import clases.Piso;
import clases.Villa;
import clases.Vivienda;


public class GestionVivienda {
	
	
    public static void insertarVivienda(Vivienda vivienda, Piso vivi, Villa viviend) {//Inserta una vivienda en la BBDD.
        
            String insert = "INSERT INTO vivienda (idOficina, ciudad, direccion, descripcion, numhab, precio_dia, Tipo_Vivienda, planta, piscina) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            try ( 
                 PreparedStatement statement = ConectorBD.conexion.prepareStatement(insert)) {
               
            	statement.setInt(1, vivienda.getIdOficina());
                statement.setString(2, vivienda.getCiudad());
                statement.setString(3, vivienda.getDireccion());
                statement.setString(4, vivienda.getDescripcion());
                statement.setInt(5,vivienda.getNumHab() );
                statement.setDouble(6,vivienda.getPrecioDia() );
                statement.setString(7,vivienda.getTipo_Vivienda());
                statement.setString(8,vivi.getPlanta());
                statement.setString(9,viviend.getPiscina());
                
                int rowsInserted = statement.executeUpdate();
            
                if (rowsInserted > 0) {
                    System.out.println("¡Vivienda añadida con éxito!");
                }
            }
                catch (SQLException e) {
        			e.printStackTrace();
        			System.out.println("Error al intentar insertar la vivienda: "+insert);
                }
        		}
    public static void mostrarViviendasBD() {//Muestra todas las viviendas de la BBDD (Menu Admin).
            	System.out.println("Lista de viviendas:");
            	System.out.println();
                   String Select = "SELECT * FROM mr_robot.vivienda";
                	try {
        				PreparedStatement statement=ConectorBD.conexion.prepareStatement(Select);
        				ResultSet rs=statement.executeQuery(Select);
        				
        				while(rs.next()) {
        					System.out.println("Codigo Vivienda: "+rs.getInt("CodVivienda")+", IdOficina: "+rs.getInt("IdOficina")
        					+", Ciudad: "+rs.getString("Ciudad")+", Direccion: "+rs.getString("Direccion")
        							+", Numero Habitaciones: "+rs.getInt("NumHab")+", Descripción: "+rs.getString("descripcion")
        							+", Precio/dia: "+rs.getDouble("Precio_Dia")+", Tipo Vivienda: "+rs.getString("Tipo_Vivienda")
        							+", Planta: "+rs.getString("Planta")+", Piscina: "+rs.getString("Piscina"));
        				}			
        			} catch (SQLException e) {
        				
        				e.printStackTrace();
        				System.out.println("Error al mostrar las viviendas: "+Select);
        			}
            }
    public static void modificarViviendaBD(Vivienda vivi) {//Modifica una vivienda de la BBDD (Menu Admin).
        String updateQuery = "UPDATE Vivienda SET descripcion=?, Precio_Dia=? WHERE CodVivienda=?";

        try {
            PreparedStatement statement = ConectorBD.conexion.prepareStatement(updateQuery);

            statement.setString(1, vivi.getDescripcion()); 
            statement.setDouble(2, vivi.getPrecioDia()); 
            statement.setInt(3, vivi.getCodViv());  

            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Vivienda actualizada exitosamente.");
            } else {
                System.out.println("Error. No se encontró ninguna vivienda con el código proporcionado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al hacer la consulta: " + updateQuery);
        }
    }
    public static void eliminarVivienda(int CodV) {//Elimina una vivienda de la BBDD (Menu Admin).
    	String deleteQuery= "DELETE FROM vivienda WHERE CodVivienda = ?";
    	
    	try {
    		PreparedStatement statement = ConectorBD.conexion.prepareStatement(deleteQuery);
    		
    		statement.setInt(1, CodV );
    		 int rowsAffected = statement.executeUpdate(); 

    	        if (rowsAffected > 0) {
    	            System.out.println("¡Vivienda eliminada con éxito!");
    	        } else {
    	            System.out.println("No se encontró la vivienda para eliminar.");
    	        }
    	}catch(SQLException e) {
    		e.printStackTrace();
            System.out.println("Error al hacer la consulta: " + deleteQuery);
    	}
    			
    }
    public static void eliminarUsuario(String email) {//Elimina un usuario de la BBDD (Menu Admin).
    	String deleteQuery= "DELETE FROM usuario WHERE email = ?";
    	
    	try {
    		PreparedStatement statement = ConectorBD.conexion.prepareStatement(deleteQuery);
    		
    		statement.setString(1, email );
    		 int rowsAffected = statement.executeUpdate(); 

    	        if (rowsAffected > 0) {
    	            System.out.println("¡Usuario eliminado con éxito!");
    	        } else {
    	            System.out.println("No se encontró el usuario para eliminar.");
    	        }
    	}catch(SQLException e) {
    		e.printStackTrace();
            System.out.println("Error al hacer la consulta: " + deleteQuery);
    	}
    			
    }
                    
}

