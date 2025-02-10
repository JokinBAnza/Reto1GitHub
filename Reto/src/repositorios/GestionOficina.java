package repositorios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GestionOficina {

    public static void mostrarOficinaViviendasBD(int id) {//Muestra las viviendas con la idOficina seleccionada.
    	System.out.println("Viviendas totales:");

        String Select = "SELECT * FROM mr_robot.vivienda WHERE IdOficina = ?";
        try {
            PreparedStatement statement = ConectorBD.conexion.prepareStatement(Select);
            statement.setInt(1, id);
            
            ResultSet rs = statement.executeQuery();
            

            if (!rs.next()) {
            	System.out.println();
                System.out.println("No hay viviendas disponibles.");
                return;
            } else { 
                do {
                    System.out.println("Codigo Vivienda: " + rs.getInt("CodVivienda") + 
                            ", IdOficina: " + rs.getInt("IdOficina") +
                            ", Ciudad: " + rs.getString("Ciudad") + 
                            ", Direccion: " + rs.getString("Direccion") +
                            ", Numero Habitantes: " + rs.getInt("NumHab") + 
                            ", Descripcion: " + rs.getString("descripcion") +
                            ", Precio/dia: " + rs.getDouble("precio_Dia") + 
                            ", Tipo Vivienda: " + rs.getString("tipo_Vivienda") +
                            ", Planta: " + rs.getString("Planta") + 
                            ", Piscina: " + rs.getString("Piscina"));
                } while (rs.next());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al hacer la consulta: " + Select);
        }
    }

    public static void DatosOficina() {//Muestra la lista de oficinas disponibles.
        System.out.println("Lista de viviendas");
        System.out.println();

        String Select = "SELECT * FROM mr_robot.oficina";
        try {
            PreparedStatement statement = ConectorBD.conexion.prepareStatement(Select);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("Id") +
                        ", telefono: " + rs.getInt("telefono") +
                        ", Ubicacion: " + rs.getString("ubicacion"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al hacer la consulta: " + Select);
        }
    }
}

