package view;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import clases.Usuario;

import repositorios.GestionUsuario;

public class MenuUsuario {
	public static Scanner sc=new Scanner(System.in);

	public static void mostrarMenuUsuario(Scanner sc) {
		int opcion = 0;
		while (opcion!=3) {
			System.out.println();
		System.out.println("--¡BIENVENIDO A MR. ROBOT!--"
				+ "\n--Tu App de alquiler de viviendas vacacionales--");
		System.out.println("\n--- Menú Usuario ---");
        System.out.println("1.- Crear Usuario");
        System.out.println("2.- Login Usuario");
        System.out.println("3.- Salir");
        System.out.println();
        System.out.print("Selecciona una opción: ");
        opcion = sc.nextInt();
        sc.nextLine(); // Limpia el buffer

        switch (opcion) {
            case 1:
            	Usuario us=crearUsuario();
            	GestionUsuario.insertarUsuario(us);
            	System.out.println("¡Usuario registrado con exito!");
                break;  
            case 2: 
            	System.out.println("Ingresa tu mail:");
            	String email=sc.nextLine();
            	String contraseña="0";
            	while(contraseña.length()<8) {
	            	System.out.println("Ingresa tu contraseña(mínimo 8 caracteres):");
	            	contraseña=sc.nextLine();
	            	if(contraseña.length()<8) {
	            		System.out.println("Error. Introduce una contraseña válida.");
	            	}
            	}
            	GestionUsuario.loginUsuario(email, contraseña);
                break;      
    
            case 3: 
                System.out.println("Finalizando programa ¡Hasta la próxima!");
                break;
                
            default:
                System.out.println("Opción no válida. Intentalo de nuevo.");
                break;
        }
		}
    }
	public static Usuario crearUsuario() {
			
			System.out.println("Ingresa tu DNI:");
			String dni;
			do {
			dni=sc.nextLine();
			if(dni.length()!=9) {
				System.out.println("Error. Introduce un DNI válido:");
			}
			}while(dni.length()!=9);
			System.out.println("Ingresa tu nombre:");
			String nombre=sc.nextLine();
			System.out.println("Ingresa tu apellido:");
			String apellido=sc.nextLine();
			System.out.println("Ingresa tu nombre de usuario:");
			String nomUs=sc.nextLine();
			System.out.println("Ingresa tu email:");
			String email=sc.nextLine();
			String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
	        Pattern pattern = Pattern.compile(regex);
	        Matcher matcher = pattern.matcher(email);

	        if (matcher.matches()) {
	            System.out.println("El correo es válido.");
	        } else {
	            System.out.println("El correo no es válido.");
	        }
			System.out.println("Ingresa tu contraseña(mínimo 8 caracteres):");
			String contraseña;
			System.out.println();
			do {
			contraseña=sc.nextLine();
			if(contraseña.length()<8) {
				System.out.println("Introduce una contraseña mínimo con 8 caracteres:");
			}
			}while(contraseña.length()<8);
			String rol;
			if(email.equals("ikdgg@plaiaundi.net") || email.equals("ikdgs@plaiaundi.net")) {
				rol="Administrador";
			}else {
				rol="Cliente";
			}
			
			Usuario usuario=new Usuario(dni, nombre, apellido, nomUs, email, contraseña, rol );
			return usuario;	

	}

}
