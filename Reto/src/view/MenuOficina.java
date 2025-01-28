package view;

import java.util.Scanner;


import repositorios.GestionOficina;
import repositorios.GestionVivienda;



public class MenuOficina {
	 public static void menuOficina(Scanner sc) {
	        boolean salir = false;

	        while (!salir) {
	            System.out.println("\n--- Menú oficina ---");
	            System.out.println("1.- Buscar por Oficina");
	            System.out.println("2.- Datos de Oficina");
	            System.out.println("3.- Salir");
	            System.out.print("Selecciona una opción: ");
	            int opcion;
	        do {
	        	opcion = sc.nextInt();
	            sc.nextLine(); // Limpiar el buffer

	            switch (opcion) {
	                case 1:
	                	MostrarViviendasOficina(sc);
	                        break;
	    
	                case 2:
	                	GestionOficina.DatosOficina();
	                    break;
	                case 3: 
	                    System.out.println("Saliendo del menú. ¡Hasta luego!");
	                    break;
	                    
	                default:
	                    System.out.println("Opción no válida. Intenta de nuevo.");
	                    break;
	            }
	        }while(opcion<1||opcion>3);
	        }
	 }
	
	 public static void MostrarViviendasOficina(Scanner sc) {
     	System.out.println("Inserta el numero de la oficina :");
	        boolean salir = false;

	        while (!salir) {
	            System.out.println("\n--- Menú Oficinas ---");
	            System.out.println("1.- Oficina Asia");
	            System.out.println("2.- Oficina America");
	            System.out.println("3.- Oficina Europa");
	            System.out.println("4.- Salir");
	            System.out.print("Selecciona una opción: ");

	            int opcion = sc.nextInt();
	            sc.nextLine(); // Limpiar el buffer
	            
	            switch (opcion) {
	                case 1:
	                	GestionOficina.mostrarOficinaViviendasBD(opcion);
	                        break;
	    
	                case 2:
	                	GestionOficina.mostrarOficinaViviendasBD(opcion);
	                    break;
	                case 3:
	                	GestionOficina.mostrarOficinaViviendasBD(opcion);
	                 break;
	              
	                case 4:
	                    salir = true;
	                    System.out.println("Saliendo del menú. ¡Hasta luego!");
	                    break;
	                default:
	                    System.out.println("Opción no válida. Intenta de nuevo.");
	                    break;
	            }
	           
	        }
	 }

}
