package repositorios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import clases.Usuario;
import view.MenuOficina;
import view.MenuVivienda;

public class GestionUsuario implements InterfazRepositorio<Usuario> {
	
	private final static Scanner sc= new Scanner (System.in);
	
	private static String dniUsuario;

    public static String getDniUsuario() {
        return dniUsuario;
    }
    public static String nombre;
    public static String getNombre() {
    	return nombre;
    }
	
    public static boolean comprobarCorreo(Usuario us){

	    String checkEmail = "SELECT COUNT(*) FROM Usuario WHERE Email = ?";
	    try {
	    	 PreparedStatement checkEmailStatement = ConectorBD.conexion.prepareStatement(checkEmail);
		        checkEmailStatement.setString(1, us.getEmail());
		        ResultSet resultEmail = checkEmailStatement.executeQuery();
		        if (resultEmail.next() && resultEmail.getInt(1) > 0) {;
		            return true;
		        }
	    }catch(SQLException e) {
	    	System.out.println("ERROR. No se pudo verificar el email.");
	    }
	    return false;
    }
    public static boolean comprobarDNI(Usuario u) {
    
	    String checkDni = "SELECT COUNT(*) FROM Usuario WHERE DNI = ?";
	    
	    	 try {
	 	     
	 	        PreparedStatement checkDniStatement = ConectorBD.conexion.prepareStatement(checkDni);
	 	        checkDniStatement.setString(1, u.getDni());
	 	        ResultSet resultDni = checkDniStatement.executeQuery();
	 	        
	 	        if (resultDni.next() && resultDni.getInt(1) > 0) {
	 	            return true;
	 	        }
	    }catch(SQLException e) {
	    	System.out.println("ERROR. No se pudo verificar el DNI.");
	    
	    }
	    	 return false;
    }
    
    
	public static void insertarUsuario(Usuario usuario) {
		
	   
	}
	public static void loginUsuario(String email, String Contraseña) {
	    
		   String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		    Pattern pattern = Pattern.compile(regex);
		    Matcher matcher = pattern.matcher(email);

		    if (!matcher.matches()) {
		        System.out.println("El correo electrónico no es válido.");
		        return;
		    }
		
		String consulta = "SELECT * FROM usuario WHERE email=? AND Contraseña=?";
	    try {
	        PreparedStatement statement = ConectorBD.conexion.prepareStatement(consulta);
	        statement.setString(1, email);
	        statement.setString(2, Contraseña);
	        ResultSet rs = statement.executeQuery();

	        if (!rs.next()) {
	            System.out.println("Usuario o contraseña incorrectos.");
	        } else {
	            String rol = rs.getString("Rol");
	            dniUsuario = rs.getString("DNI");
	            nombre= rs.getString("Nombre");
	            if ("Administrador".equalsIgnoreCase(rol)) {
	                System.out.println("Bienvenido Administrador.");
	                MenuVivienda.mostrarMenuVivienda(sc);
	            } else if ("Cliente".equalsIgnoreCase(rol)) {
	                System.out.println("Bienvenido "+nombre+".");
	                MenuOficina.menuOficina(sc);
	            } else {
	                System.out.println("Usuario o contraseña incorrectos.");
	                System.out.println();
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Error al realizar el login.");
	    }
	}

	@Override
	public void insertar(Usuario usuario) {
		 try {  
		       
				String insert="INSERT INTO Usuario (DNI, Nombre, Apellido, NomUs, Email, Contraseña, Rol) VALUES (?, ?, ?, ?, ?, ?, ?)";
					PreparedStatement statement=ConectorBD.conexion.prepareStatement(insert);
					statement.setString(1, usuario.getDni());
					statement.setString(2, usuario.getNombre());
					statement.setString(3, usuario.getApellido());
					statement.setString(4, usuario.getNomUs());
					statement.setString(5, usuario.getEmail());
					statement.setString(6, usuario.getContraseña());
					statement.setString(7, usuario.getRol());
						
					statement.executeUpdate();
						
				} catch (SQLException e) {
						
					e.printStackTrace();
					System.out.println("Error al hacer la consulta.");	
					
				}
		
	}

	@Override
	public void modificar(Usuario t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrar() {
		String Select= "SELECT * FROM usuario";
		try {
			PreparedStatement statement=ConectorBD.conexion.prepareStatement(Select);
			ResultSet rs=statement.executeQuery(Select);
			
			while(rs.next()) {
				System.out.println("DNI: "+rs.getString("DNI")+", Nombre: "+rs.getString("Nombre")
				+", Nombre Usuario: "+rs.getString("NomUs")+", Email: "+rs.getString("Email")+", Contraseña: "+rs.getString("Contraseña")
						+", Rol: "+rs.getString("Rol"));
			}			
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println("Error al mostrar los usuarios: "+Select);
		}
		
	}

}
