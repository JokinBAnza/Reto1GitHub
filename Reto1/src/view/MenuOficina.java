package view;

import java.util.Scanner;



public class MenuOficina {
	 public static void mostrarMenuOficina(Scanner sc) {
	        boolean salir = false;

	        while (!salir) {
	            System.out.println("\n--- Menú Oficina ---");
	            System.out.println("1.- Oficina Asia");
	            System.out.println("2.- Oficina America");
	            System.out.println("3.- Oficina Europa");
	            System.out.println("4.- Salir");
	            System.out.print("Selecciona una opción: ");

	            int opcion = sc.nextInt();
	            sc.nextLine(); // Limpiar el buffer

	            switch (opcion) {
	                case 1:
	                
	                        break;
	    
	                case 2:
	                	
	                    break;
	                case 3:
	                   //modificarVivienda()
	                 
	              
	                case 4:
	                    salir = true;
	                    System.out.println("Saliendo del menú. ¡Hasta luego!");
	                    break;
	                default:
	                    System.out.println("Opción no válida. Intenta de nuevo.");
	            }
	        }
	 }

}
