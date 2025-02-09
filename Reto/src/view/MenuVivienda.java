package view;
import java.util.InputMismatchException;
import java.util.Scanner;
import clases.Vivienda;
import repositorios.GestionUsuario;
import repositorios.GestionVivienda;

public class MenuVivienda {
	
	 public static void mostrarMenuVivienda(Scanner sc) {
	        
		 boolean salir = false;
	        
	        while (!salir) {
	            System.out.println("\n--- Menú Administrador ---");
	            System.out.println("1.- Agregar Vivienda");
	            System.out.println("2.- Mostrar Viviendas");
	            System.out.println("3.- Modificar Vivienda");
	            System.out.println("4.- Eliminar Vivienda");
	            System.out.println("5.- Mostrar Usuarios");
	            System.out.println("6.- Volver atras");
	            System.out.println("7.- Salir");
	            System.out.println();
	            System.out.print("Selecciona una opción: ");

	            int opcion = sc.nextInt();
	            sc.nextLine();

	            switch (opcion) {
	                case 1:

	                  Vivienda viv=agregarVivienda(sc);
	                  GestionVivienda.insertarVivienda(viv);
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
	                	return;
	                case 7:
	                    salir = true;
	                    System.out.println("Finalizando programa. ¡Nos vemos Administrador!");
	                    break;
	                default:
	                    System.out.println("Opción no válida. Intenta de nuevo.");
	            }

	            }
	        }
	        public static Vivienda agregarVivienda(Scanner sc) {
	    		System.out.println("\n--- Añadir Vivienda ---");
	    		System.out.println("Id Oficina:");
	    		int idOficina=sc.nextInt();
	    		sc.nextLine();
	    	    String disponible = "Si";
	            System.out.print("Ciudad: ");
	            String ciudad = sc.nextLine();
	            System.out.print("Dirección: ");
	            String direccion = sc.nextLine();
	            System.out.print("Número de habitaciones: ");
	            int numHab = sc.nextInt();
	            System.out.print("Descripción: ");
	            String descripcion = sc.nextLine(); 
	            sc.nextLine();
	            System.out.print("Precio por día: ");
	            double precioDia = sc.nextDouble();
	            sc.nextLine();
	            String tipo_Vivienda;
	            String planta = null;
	            String piscina = "No";
	            do {
	            System.out.println("TipoVivienda (Villa/Piso):");
	            tipo_Vivienda= sc.nextLine();
	           
		            if(tipo_Vivienda.equalsIgnoreCase("Villa")) {
		            	do {
		            	System.out.println("¿Tiene piscina? (Si/No):");
		            	piscina=sc.nextLine();
		            	}while(!piscina.equalsIgnoreCase("Si")||!piscina.equalsIgnoreCase("No"));
		            		  
		            }else if (tipo_Vivienda.equalsIgnoreCase("Piso")){
		            	System.out.println("¿Que planta es?(Numero y letra) :");
		            	 planta =sc.nextLine();
		            
		            }else {
		            	System.out.println("Error. Introduce Villa o Piso:");
		            }
	            }while(!tipo_Vivienda.equalsIgnoreCase("Villa") && !tipo_Vivienda.equalsIgnoreCase("Piso"));
	            Vivienda vivienda=new Vivienda(idOficina, disponible, ciudad, direccion, numHab, descripcion, precioDia, tipo_Vivienda, planta, piscina);
	            return vivienda;
	        }
	        private static Vivienda modificarVivienda(Scanner sc) {
	           
	            System.out.println("Introduce el Codigo de la vivienda a actualizar:");
	            int codV = sc.nextInt();
	            sc.nextLine();

	            Vivienda vivi = new Vivienda();
	            vivi.setCodViv(codV);

	            String disponible;
	            do {
	                System.out.println("¿Disponible para alquilar? (Si/No):");
	                disponible = sc.nextLine();
	            } while (!disponible.equalsIgnoreCase("Si") && !disponible.equalsIgnoreCase("No"));
	            vivi.setDisponible(disponible);

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

