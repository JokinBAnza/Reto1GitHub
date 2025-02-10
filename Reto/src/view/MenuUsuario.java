package view;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import clases.Usuario;

import repositorios.GestionUsuario;

public class MenuUsuario{
	public static Scanner sc=new Scanner(System.in);
	
	public static void mostrarMenuUsuario(Scanner sc) {//Primer menu que veremos.
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

        try {
            opcion = sc.nextInt();
            sc.nextLine(); 
        } catch (InputMismatchException e) {
            System.out.println("Error. Debes ingresar un número válido.");
            sc.nextLine();
            continue;
        }

        switch (opcion) {
            case 1:
            	Usuario us=crearUsuario();
            	GestionUsuario.insertarUsuario(us);
            	System.out.println("¡Usuario registrado con exito!");
                break;  
            case 2: 
            	System.out.println("Ingresa tu email:");
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
                System.exit(0);
                
            default:
                System.out.println("Opción no válida. Intentalo de nuevo.");
                break;
        }
		}
    }
	public static Usuario crearUsuario() {//Para insertar un usuario en la BBDD.
			Usuario usuar=new Usuario();
			String dni;
			boolean dniValido=false;
			boolean emailValido=false;
			do {
			System.out.println("Ingresa tu DNI:");
			do {
			dni=sc.nextLine();
			if(dni.length()!=9) {
				System.out.println("Error. Introduce un DNI válido:");
			}else {
			usuar.setDni(dni);
			}
			}while(dni.length()!=9);
			if(GestionUsuario.comprobarDNI(usuar)) {
				System.out.println("Error. El DNI ya existe en la base de datos.");
				System.out.println("Intentalo de nuevo.");
			}else {
				dniValido=true;
			}
			
			}while(!dniValido);
			
			
			System.out.println("Ingresa tu nombre:");
			String nombre=sc.nextLine();
			System.out.println("Ingresa tu apellido:");
			String apellido=sc.nextLine();
			System.out.println("Ingresa tu nombre de usuario:");
			String nomUs=sc.nextLine();
			Matcher matcher;
			String email;
			do {
				 do {
			System.out.println("Ingresa tu email:");
			email=sc.nextLine();
			String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
	        Pattern pattern = Pattern.compile(regex);
	        matcher = pattern.matcher(email);
	        usuar.setEmail(email);
	       
	        if (matcher.matches()) {
	     
	        } else {
	            System.out.println("Error. Ingresa un email válido.");  
	        }
	        }while(!matcher.matches());
	        if(GestionUsuario.comprobarCorreo(usuar)) {
	        	System.out.println("ERROR. El email ya existe en la base de datos.");
	        }else {
	        	emailValido=true;
	        }
	        
	        }while(!emailValido);
			
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
