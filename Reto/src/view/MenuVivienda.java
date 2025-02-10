package view;
import java.util.InputMismatchException;
import java.util.Scanner;

import clases.Piso;
import clases.Villa;
import clases.Vivienda;
import repositorios.GestionReserva;
import repositorios.GestionUsuario;
import repositorios.GestionVivienda;

public class MenuVivienda {
	
	 public static void mostrarMenuVivienda(Scanner sc) {//Menu Admin.
	        
		 boolean salir = false;
	        
	        while (!salir) {
	            System.out.println("\n--- Menú Administrador ---");
	            System.out.println("1.- Agregar Vivienda");
	            System.out.println("2.- Mostrar Viviendas");
	            System.out.println("3.- Modificar Vivienda");
	            System.out.println("4.- Eliminar Vivienda");
	            System.out.println("5.- Mostrar Usuarios");
	            System.out.println("6.- Eliminar Usuario");
	            System.out.println("7.- Mostrar Reservas");
	            System.out.println("8.- Eliminar Reserva");
	            System.out.println("9.- Volver atras");
	            System.out.println("10.- Salir");
	            System.out.println();
	            System.out.print("Selecciona una opción: ");
	            
	            int opcion = -1;  

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
	                	Piso vivi=new Piso();
	                	Villa viviend=new Villa();
		                Vivienda viv=agregarVivienda(sc);
		                GestionVivienda.insertarVivienda(viv,vivi,viviend);
	                     break;
	                case 2:
	                	GestionVivienda.mostrarViviendasBD();
	                    break;
	                case 3:
	                	 Vivienda viviendaModificada = modificarVivienda(sc);
	                	 GestionVivienda.modificarViviendaBD(viviendaModificada);
	                   break;
	                case 4:
	                  	GestionVivienda.mostrarViviendasBD();
	                  	System.out.println();
	                	System.out.println("¿Que vivienda quieres borrar? Introduce su CodVivienda:");
	                	int CodV=sc.nextInt();
	                	GestionVivienda.eliminarVivienda(CodV);
	                	break;
	                case 5:
	                	GestionUsuario.mostrarUsuarios();
	                	break;
	                case 6:
	                	GestionUsuario.mostrarUsuarios();
	                	System.out.println("Introduce el email del usuario que quieres eliminar:");
	                	String email=sc.nextLine();
	                	GestionVivienda.eliminarUsuario(email);
	                	break;
	                case 7:
	                	GestionReserva.mostrarTodasReservas();
	                	break;
	                case 8:
	                	GestionReserva.mostrarTodasReservas();
	                	System.out.println("Introduce el Codigo de Reserva de la reserva a eliminar:");
	                	int codR=sc.nextInt();
	                	GestionVivienda.eliminarReserva(codR);
	                	break;
	                case 9:
	                	return;
	                case 10:
	                    System.out.println("Finalizando programa. ¡Nos vemos Administrador!");
	                    System.exit(0);
	                default:
	                    System.out.println("Opción no válida. Intenta de nuevo.");
	            }

	            }
	        }
	 public static Vivienda agregarVivienda(Scanner sc) {
		    System.out.println("\n--- Añadir Vivienda ---");


		    int idOficina = 0;
		    boolean valido = false;
		    while (!valido) {
		        try {
		        	do {
		            System.out.println("Id Oficina:");
		            idOficina = sc.nextInt();
		            if(idOficina>3||idOficina<1) {
		            	System.out.println("Error. No existe la oficina introducida.");
		            }
		        	}while(idOficina>3||idOficina<1);
		           
		            valido = true;
		        } catch (Exception e) {
		            System.out.println("Error. Debes ingresar un número entero para el Id de la oficina.");
		            sc.nextLine();
		        }
		    }
		    sc.nextLine(); 

		    System.out.print("Ciudad: ");
		    String ciudad = sc.nextLine();
		    System.out.print("Dirección: ");
		    String direccion = sc.nextLine();

		  
		    int numHab = 0;
		    valido = false;
		    while (!valido) {
		        try {
		            System.out.print("Número de habitaciones: ");
		            numHab = sc.nextInt();
		            valido = true; 
		        } catch (Exception e) {
		            System.out.println("Error. Debes ingresar un número entero para el número de habitaciones.");
		            sc.nextLine(); 
		        }
		    }

		    System.out.print("Descripción: ");
		    String descripcion = sc.nextLine();
		    sc.nextLine(); 

		    double precioDia = 0.0;
		    valido = false;
		    while (!valido) {
		        try {
		            System.out.print("Precio por día: ");
		            precioDia = sc.nextDouble();
		            valido = true; 
		        } catch (Exception e) {
		            System.out.println("Error. Debes ingresar un número decimal para el precio por día.");
		            sc.nextLine(); 
		        }
		    }
		    sc.nextLine(); 

		    String tipo_Vivienda;
		    String planta = null;
		    String piscina = "No";
		    do {
		        System.out.println("TipoVivienda (Villa/Piso):");
		        tipo_Vivienda = sc.nextLine();

		        if (tipo_Vivienda.equalsIgnoreCase("Villa")) {
		            do {
		                System.out.println("¿Tiene piscina? (Si/No):");
		                piscina = sc.nextLine();
		            } while (!piscina.equalsIgnoreCase("Si") && !piscina.equalsIgnoreCase("No"));
		        } else if (tipo_Vivienda.equalsIgnoreCase("Piso")) {
		        	do {
		            System.out.println("¿Qué planta es? (Número y letra):");
		            planta = sc.nextLine();
		            if(planta.length()>3) {
		            	System.out.println("Error. Inserta máximo 2 letras y un número.");
		            }
		        	}while(planta.length()>3);
		        } else {
		            System.out.println("Error. Introduce Villa o Piso:");
		        }
		    } while (!tipo_Vivienda.equalsIgnoreCase("Villa") && !tipo_Vivienda.equalsIgnoreCase("Piso"));

		    Vivienda vivienda = new Vivienda(idOficina, ciudad, direccion, numHab, descripcion, precioDia, tipo_Vivienda);
		    Piso vivi=new Piso(idOficina, ciudad, direccion, numHab, descripcion, precioDia, tipo_Vivienda, planta);
		    Villa viviend=new Villa(idOficina, ciudad, direccion, numHab, descripcion, precioDia, tipo_Vivienda, piscina);
		    if(tipo_Vivienda.equals("Piso")) {
		    	return vivi;
		    	
		    }else if(tipo_Vivienda.equals("Villa")) {
		    	return viviend;
		    }else {
		       	return vivienda;
		    }
		 
		}

	        private static Vivienda modificarVivienda(Scanner sc) {
	           
	            System.out.println("Introduce el Codigo de la vivienda a actualizar:");
	            int codV = sc.nextInt();
	            sc.nextLine();

	            Vivienda vivi = new Vivienda();
	            vivi.setCodViv(codV);

	            System.out.println("Cambia la descripción de la vivienda:");
	            String desc = sc.nextLine();
	            vivi.setDescripcion(desc);
	            
	            while(true) {
	            	try {
	            		System.out.println("Introduce el nuevo precio por dia:");
	    	            double precioD = sc.nextDouble();
	    	            sc.nextLine();
	    	            vivi.setPrecioDia(precioD);
	    	            break;
	            	}catch(InputMismatchException e) {
	            		System.out.println("Error. Introduce un número válido.");
	            		sc.nextLine();
	            	}
	            	
	            }  
				return vivi;
	        }
	       
	 }

