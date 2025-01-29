package repositorios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import clases.Reserva;
import clases.Vivienda;

public class GestionReserva {
	
	public static void insertarReserva(Reserva reserva) {
        
        String insert = "INSERT INTO reserva (FechaEntrada, FechaSalida, NumHuespedes, TotalPagado) VALUES (?, ?, ?, ?)";
        
        try ( 
             PreparedStatement statement = ConectorBD.conexion.prepareStatement(insert)) {
           
        	 
            statement.setString(1, reserva.getFechaEntrada());
            statement.setString(2, reserva.getFechaSalida());
            statement.setInt(3, reserva.getNumHuespedes());
            statement.setDouble(4, reserva.getTotalPagado());
            
            int rowsInserted = statement.executeUpdate();
        
            if (rowsInserted > 0) {
                System.out.println("¡Vivienda añadida con éxito!");
            }
        	}
            catch (SQLException e) {
    			e.printStackTrace();
    			System.out.println("Error al hacer la consulta: "+insert);
            }
    }
	public static void mostrarReservas() {
    	System.out.println("Lista de viviendas");
           String Select = "SELECT * FROM mr_robot.reserva WHERE";
        	try {
				PreparedStatement statement=ConectorBD.conexion.prepareStatement(Select);
				ResultSet rs=statement.executeQuery(Select);
				
				while(rs.next()) {
					System.out.println("Codigo Vivienda: "+rs.getInt("CodVivienda")+", IdOficina: "+rs.getInt("IdOficina")+
							", Ciudad: "+rs.getString("Ciudad")+", Direccion: "+rs.getString("Direccion")
							+", Numero Habitantes: "+rs.getInt("NumHab")+", Descripción: "+rs.getString("descripcion")
							+", Precio/dia: "+rs.getDouble("Precio_Dia")+", Tipo Vivienda: "+rs.getString("Tipo_Vivienda")
							+", Dias: "+rs.getInt("Dias")+", Semanas: "+rs.getInt("Semanas"));
				}			
			} catch (SQLException e) {
				
				e.printStackTrace();
				System.out.println("Error al hacer la consulta: "+Select);
			}
    }

}
