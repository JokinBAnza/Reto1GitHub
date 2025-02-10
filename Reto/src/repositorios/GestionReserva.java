package repositorios;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import clases.Reserva;

public class GestionReserva {
	
	public static boolean consultarFechaBD(int opcion,Date fechaEntrada, Date fechaSalida) {
	    String Select = "SELECT v.CodVivienda, v.IdOficina, v.Ciudad, v.Direccion, v.Descripcion, v.NumHab, v.Precio_Dia, v.Tipo_Vivienda, v.Planta, v.Piscina "
	            + "FROM vivienda v "
	            + "LEFT JOIN reserva r ON v.CodVivienda = r.CodVivienda AND "
	            + "( (r.FechaEntrada BETWEEN ? AND ?) OR "
	            + "  (r.FechaSalida BETWEEN ? AND ?) OR "
	            + "  (r.FechaEntrada < ? AND r.FechaSalida > ?) ) "
	            + "WHERE r.CodVivienda IS NULL AND v.idOficina=?";  // Sólo selecciona viviendas que no tienen reservas que se superpongan
	   
	    boolean hayViviendasDisponibles = false;
	    try {
	        PreparedStatement statement = ConectorBD.conexion.prepareStatement(Select);
	        statement.setDate(1, fechaEntrada);
	        statement.setDate(2, fechaSalida);
	        statement.setDate(3, fechaEntrada);
	        statement.setDate(4, fechaSalida);
	        statement.setDate(5, fechaEntrada);
	        statement.setDate(6, fechaSalida);
	        statement.setInt(7, opcion);

	        ResultSet rs = statement.executeQuery();


	        while (rs.next()) {
	            hayViviendasDisponibles = true;
	            System.out.println("Codigo Vivienda: " + rs.getInt("CodVivienda") + ", IdOficina: " + rs.getInt("IdOficina")
	                    + ", Ciudad: " + rs.getString("Ciudad") + ", Direccion: " + rs.getString("Direccion")
	                    + ", Numero Habitantes: " + rs.getInt("NumHab") + ", Descripción: " + rs.getString("Descripcion")
	                    + ", Precio/dia: " + rs.getDouble("Precio_Dia") + ", Tipo Vivienda: " + rs.getString("Tipo_Vivienda")
	                    + ", Planta: " + rs.getString("Planta") + ", Piscina: " + rs.getString("Piscina"));
	        }

	        if (!hayViviendasDisponibles) {
	            System.out.println("No hay viviendas disponibles en este rango de fechas.");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Error al hacer la consulta: " + Select);
	    }
	    return hayViviendasDisponibles;
	}
		
	public static void insertarReserva(Reserva reserva) {//Inserta la reserva en la BBDD.
        
        String insert = "INSERT INTO reserva (DniUsuario, CodVivienda, FechaEntrada, FechaSalida, NumHuespedes, TotalPagado) VALUES (?, ?, ?, ?, ?, ?)";
        
        try ( 
             PreparedStatement statement = ConectorBD.conexion.prepareStatement(insert)) {
           
        	statement.setString(1, reserva.getDniUsuario());
        	statement.setInt(2, reserva.getCodVivienda());
            statement.setDate(3, reserva.getFechaEntrada());
            statement.setDate(4, reserva.getFechaSalida());
            statement.setInt(5, reserva.getNumHuespedes());
            statement.setDouble(6, reserva.getTotalPagado());
            
            int rowsInserted = statement.executeUpdate();
            
            if (rowsInserted > 0) {
                System.out.println("¡Reserva realizada con éxito!");
            }
        	}
            catch (SQLException e) {
    			e.printStackTrace();
    			System.out.println("Error al hacer la reserva "+insert);
            }
    }
	public static void mostrarReservas() {//Muestra las reservas del usuario con el log in.
	    System.out.println("Lista de reservas");
	    String Select = "SELECT * FROM mr_robot.reserva WHERE dniUsuario= ?";

	    try {
	        PreparedStatement statement = ConectorBD.conexion.prepareStatement(Select);
	        statement.setString(1, GestionUsuario.getDniUsuario());
	        ResultSet rs = statement.executeQuery();

	        if (!rs.next()) {
	            System.out.println("¡No existe ninguna reserva!");
	        } else {

	            do {
	                System.out.println("Codigo reserva: " + rs.getInt("CodReserva") + ", DNI: " + rs.getString("DniUsuario") +
	                        ", CodVivienda: " + rs.getInt("CodVivienda") + ", Fecha Entrada: " + rs.getString("FechaEntrada") +
	                        ", Fecha Salida: " + rs.getString("FechaSalida") + ", Numero de Huespedes: " + rs.getInt("NumHuespedes") +
	                        ", Total a pagar: " + rs.getDouble("TotalPagado"));
	            } while (rs.next());
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Error al hacer la consulta: " + Select);
	    }
	}
	public static void mostrarTodasReservas() {//Muestra todas las reservas(Menu Admin).
	    System.out.println("Lista de reservas");
	    String Select = "SELECT * FROM mr_robot.reserva";

	    try {
	        PreparedStatement statement = ConectorBD.conexion.prepareStatement(Select);
	        
	        ResultSet rs = statement.executeQuery();

	        if (!rs.next()) {
	            System.out.println("¡No existe ninguna reserva!");
	        } else {

	            do {
	                System.out.println("Codigo reserva: " + rs.getInt("CodReserva") + ", DNI: " + rs.getString("DniUsuario") +
	                        ", CodVivienda: " + rs.getInt("CodVivienda") + ", Fecha Entrada: " + rs.getString("FechaEntrada") +
	                        ", Fecha Salida: " + rs.getString("FechaSalida") + ", Numero de Huespedes: " + rs.getInt("NumHuespedes") +
	                        ", Total a pagar: " + rs.getDouble("TotalPagado"));
	            } while (rs.next());
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Error al hacer la consulta: " + Select);
	    }
	}

	public static double obtenerPrecioDiaVivienda(int codVivienda) {
	    String query = "SELECT precio_Dia FROM vivienda WHERE CodVivienda = ?";
	    double precioDia = 0.0;
	    
	    try {
	        PreparedStatement statement = ConectorBD.conexion.prepareStatement(query);
	        statement.setInt(1, codVivienda);
	        ResultSet rs = statement.executeQuery();
	        
	        if (rs.next()) {
	            precioDia = rs.getDouble("precio_Dia");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Error al obtener el precio de la vivienda.");
	    }

	    return precioDia;
	}
	public static void finalizarReserva(Reserva rese) {
		String updateQuery=  "UPDATE Vivienda v " +
			                "JOIN Reserva r ON v.CodVivienda = r.CodVivienda " +
			                "SET v.disponible=? " +
			                "WHERE r.dniUsuario=?";
				
				try {
					PreparedStatement statement= ConectorBD.conexion.prepareStatement(updateQuery);
					statement.setString(1, "Si");
					statement.setString(2, GestionUsuario.getDniUsuario());
					
					int rowsAffected=statement.executeUpdate();
					if (rowsAffected > 0) {
			            System.out.println("Reserva finalizada correctamente.");
			        } else {
			            System.out.println("No se encontró ninguna reserva para ese usuario.");
			        }
					
				}catch(SQLException e) {
					e.printStackTrace();
		            System.out.println("Error al hacer la consulta: " + updateQuery);
	
				}
	}
	public static boolean esViviendaDeOficina(int codVivienda, int idOficina) {
	    String query = "SELECT COUNT(*) FROM mr_robot.vivienda WHERE CodVivienda = ? AND IdOficina = ?";
	    
	    try {
	        PreparedStatement statement = ConectorBD.conexion.prepareStatement(query);
	        statement.setInt(1, codVivienda);
	        statement.setInt(2, idOficina);
	        
	        ResultSet rs = statement.executeQuery();
	        
	        if (rs.next() && rs.getInt(1) > 0) {
	            return true;
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Error al verificar la vivienda.");
	    }
	    
	    return false;
	}


}
