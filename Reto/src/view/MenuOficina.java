package view;

import java.util.InputMismatchException;
import java.util.Scanner;

import clases.Oficina;
import repositorios.GestionOficina;
import repositorios.GestionReserva;
import repositorios.GestionUsuario;

public class MenuOficina {
	 
	public static void menuOficina(Scanner sc) {
	        
		boolean salir = false;

	        while (!salir) {
	            System.out.println("\n--- Menú Oficina ---");
	            System.out.println("1.- Buscar vivienda por Oficina");
	            System.out.println("2.- Mostrar oficinas disponibles");
	            System.out.println("3.- Mostrar reservas de "+GestionUsuario.nombre);
	            System.out.println("4.- Salir");
	            System.out.println();
	            System.out.print("Selecciona una opción: ");
	            int opcion = 0;  

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
	                	mostrarViviendasOficina(sc);
	                    break;
	                case 2:
	                	GestionOficina.DatosOficina();
	                    break;
	                case 3:
	                	GestionReserva.mostrarReservas();
	                	break;
	                case 4: 
	                	
	                    System.out.println("Finalizando programa ¡Hasta la próxima!");
	                    System.exit(0);
	                    break;
	                    
	                default:
	                    System.out.println("Opción no válida. Intenta de nuevo.");
	                    break;
	            }
	        }
	 }
	
	 public static void mostrarViviendasOficina(Scanner sc) {
   
	        boolean salir = false;

	        while (!salir) {
	            System.out.println("\n--- Menú Oficinas ---");
	            System.out.println("1.- Oficina Asia");
	            System.out.println("2.- Oficina America");
	            System.out.println("3.- Oficina Europa");
	            System.out.println("4.- Volver atras");
	            System.out.println("5.- Salir");
	            System.out.print("Selecciona una opción: ");

	            int opcion = sc.nextInt();
	            Oficina ofi= new Oficina();//--------------------------------------> Creo que no hace nada.
	            ofi.setId(opcion);
	            sc.nextLine();
	            
	            switch (opcion) {
	                case 1:
	                	GestionOficina.mostrarOficinaViviendasBD(opcion);
	                	MenuReservas.mostrarMenuReservas(sc, opcion);
	                        break;
	    
	                case 2:
	                	GestionOficina.mostrarOficinaViviendasBD(opcion);
	                	MenuReservas.mostrarMenuReservas(sc, opcion);
	                    break;
	                case 3:
	                	GestionOficina.mostrarOficinaViviendasBD(opcion);
	                	MenuReservas.mostrarMenuReservas(sc, opcion);
	                 break;
	                case 4:
	                	return;
	                case 5:
	                	System.exit(0);
	                    System.out.println("Finalizando programa ¡Hasta la próxima!");
	                    break;
	                default:
	                    System.out.println("Opción no válida. Intenta de nuevo.");
	                    break;
	            }
	           
	        }
	 }

}
