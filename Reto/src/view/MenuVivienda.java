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
	
	 public static void mostrarMenuVivienda(Scanner sc) {
	     GestionVivienda viva = new GestionVivienda ();
	     GestionReserva res=new GestionReserva();
	     
		 boolean salir = false;
	        
	        while (!salir) {
	            System.out.println("\n--- Menú Administrador ---");
	            System.out.println("1.- Agregar Vivienda");
	            System.out.println("2.- Mostrar Viviendas");
	            System.out.println("3.- Modificar Vivienda");
	            System.out.println("4.- Eliminar Vivienda");
	            System.out.println("5.- Mostrar Usuarios");
	            System.out.println("6.- Eliminar Usuarios");
	            System.out.println("7.- Mostrar Reservas");
	            System.out.println("8.- Volver atras");
	            System.out.println("9.- Salir");
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
		                 Vivienda viv=agregarVivienda(sc);
		                 Piso piso=new Piso();
		                 Villa villa=new Villa();
		                 GestionVivienda.insertarVivienda(viv, piso, villa);
	                     break;
	                case 2:
	                	
	                	viva.mostrar();
	                    break;
	                case 3:
	                	System.out.println("Introduce el codigo de la vivienda a modificar:");
	                	int codigo=sc.nextInt();
	                	Vivienda vivienda=GestionVivienda.obtenerViviendaPorCodigo(codigo);
	                	modificarVivienda(codigo, sc);
	                	viva.modificar(vivienda);
	                   break;
	                case 4:
	                  	viva.mostrar();
	                  	System.out.println();
	                	System.out.println("¿Que vivienda quieres borrar? Introduce su CodVivienda:");
	                	int CodV=sc.nextInt();
	                	GestionVivienda.eliminarVivienda(CodV);
	                	break;
	                case 5:
	                	viva.mostrar();
	                	break;
	                case 6:
	                	viva.mostrar();
	                	System.out.println("Introduce el email del usuario que quieres eliminar:");
	                	String email=sc.nextLine();
	                	GestionVivienda.eliminarUsuario(email);
	                	break;
	                case 7:
	                	res.mostrar();
	                	break;
	                case 8:
	                	return;
	                case 9:
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
		                if (idOficina > 3 || idOficina < 1) {
		                    System.out.println("Error. No existe la oficina introducida.");
		                }
		            } while (idOficina > 3 || idOficina < 1);
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

		    sc.nextLine(); 

		    System.out.print("Descripción: ");
		    String descripcion = sc.nextLine();

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

		    while (true) {
		        System.out.println("Tipo de Vivienda (Villa/Piso):");
		        String tipoVivienda = sc.nextLine();

		        if (tipoVivienda.equalsIgnoreCase("Villa")) {
		            while (true) {
		                System.out.println("¿Tiene piscina? (Si/No):");
		                String piscina = sc.nextLine();
		                if (piscina.equalsIgnoreCase("Si") || piscina.equalsIgnoreCase("No")) {
		                    return new Villa(idOficina, ciudad, direccion, numHab, descripcion, precioDia, tipoVivienda, piscina);
		                }
		                System.out.println("Error. Introduce 'Si' o 'No'.");
		            }
		        } else if (tipoVivienda.equalsIgnoreCase("Piso")) {
		            while (true) {
		                System.out.println("¿Qué planta es? (Número y letra, máx. 3 caracteres):");
		                String planta = sc.nextLine();
		                if (planta.length() <= 3) {
		                    return new Piso(idOficina, ciudad, direccion, numHab, descripcion, precioDia, tipoVivienda, planta);
		                }
		                System.out.println("Error. Inserta máximo 2 letras y un número.");
		            }
		        } else {
		            System.out.println("Error. Introduce 'Villa' o 'Piso'.");
		        }
		    }
		}


	 private static void modificarVivienda(int codigo, Scanner sc) {
		 GestionVivienda v=new GestionVivienda();
		    // Buscar la vivienda en la BD según el código
		    Vivienda vivi = GestionVivienda.obtenerViviendaPorCodigo(codigo);

		    if (vivi == null) {
		        System.out.println("No se encontró ninguna vivienda con el código " + codigo);
		        return;
		    }

		    System.out.println("Modificando vivienda con código: " + codigo);

		    System.out.println("Cambia la descripción de la vivienda:");
		    String desc = sc.nextLine();
		    vivi.setDescripcion(desc);

		    while (true) {
		        try {
		            System.out.println("Introduce el nuevo precio por día:");
		            double precioD = sc.nextDouble();
		            sc.nextLine();
		            vivi.setPrecioDia(precioD);
		            break;
		        } catch (InputMismatchException e) {
		            System.out.println("Error. Introduce un número válido.");
		            sc.nextLine();
		        }
		    }

		    // Guardar los cambios en la BD
		    v.modificar(vivi);
		}

	       
	 }

