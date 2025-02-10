package view;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

import clases.Reserva;
import repositorios.GestionReserva;
import repositorios.GestionUsuario;

public class MenuReservas {
	
	public static void mostrarMenuReservas(Scanner sc, int idOficina) {
	    boolean salir = false;

	    while (!salir) {
	        System.out.println("\n--- Menú Reservas ---");
	        System.out.println("1.- Disponibilidad fechas");
	        System.out.println("2.- Volver atras");
	        System.out.println("3.- Salir");
	        System.out.println();
	        System.out.print("Selecciona una opción: ");
	        
	        int opcion2 = -1;  

            try {
                opcion2 = sc.nextInt();
                sc.nextLine(); 
            } catch (InputMismatchException e) {
                System.out.println("Error. Debes ingresar un número válido.");
                sc.nextLine();
                continue;
            }

	        switch (opcion2) {
	            case 1:   
	            	Reserva reserva=consultarFechas(sc);
	            	if (reserva == null) {
	            	    break;
	            	}
	            	boolean viviendaDisponible=GestionReserva.consultarFechaBD(idOficina,reserva.getFechaEntrada(), reserva.getFechaSalida());
	            	if(!viviendaDisponible) {
	            		break;
	            	}
	            	Reserva res=agregarReserva(sc, idOficina, reserva.getFechaEntrada(), reserva.getFechaSalida());
	            	if(res==null) {
	            		System.out.println("Cambia de oficina o selecciona una vivienda asociada a esta oficina.");
	            		break;
	            	}
	            	GestionReserva.insertarReserva(res);
	            	GestionReserva.mostrarReservas();
	            	return;
			case 2:
	          
	             return;

	            case 3:
	                System.out.println("Finalizando programa ¡Hasta la próxima!");
	                System.exit(0);
	            default:
	                System.out.println("Opción no válida. Intenta de nuevo.");
	        }

	        }
	    }
	private static Date convertirFecha(String fechaString) throws ParseException {
	    SimpleDateFormat formatoEntrada = new SimpleDateFormat("yyyy/MM/dd");//Descubrimos que si cambias el año a mayusculas o el mes a minusculas da errores.
	    formatoEntrada.setLenient(false);

	    java.util.Date fechaUtil = formatoEntrada.parse(fechaString);
	    return new java.sql.Date(fechaUtil.getTime());
	}

	public static Reserva consultarFechas(Scanner sc) {//Comprobamos que las fechas sean viables y factibles.
	    Date fechaEd = null;
	    Date fechaSd = null;
	    LocalDate fechaActual = LocalDate.now();//Para saber la fecha actual.

	    while (fechaEd == null) {
	        try {
	            System.out.print("Fecha de Entrada (YYYY/MM/DD): ");
	            String fechaE = sc.nextLine();
	            fechaEd = convertirFecha(fechaE);
	            LocalDate fechaEntradaLocal = fechaEd.toLocalDate();
	            if (fechaEntradaLocal.isBefore(fechaActual)) {
	    	        System.out.println("Error: La fecha de entrada no puede ser anterior a la fecha actual (" + fechaActual + ").");
	    	        return null;
	    	    }
	        } catch (ParseException e) {
	            System.out.println("Error: Formato de fecha incorrecto. Usa YYYY/MM/DD.");
	        }
	    }

	    while (fechaSd == null) {
	        try {
	            System.out.print("Fecha de Salida (YYYY/MM/DD): ");
	            String fechaS = sc.nextLine();
	            fechaSd = convertirFecha(fechaS);
	    	    LocalDate fechaSalidaLocal = fechaSd.toLocalDate();
	    	    if (fechaSalidaLocal.isBefore(fechaActual)) {
	    	        System.out.println("Error: La fecha de salida no puede ser anterior a la fecha actual (" + fechaActual + ").");
	    	        return null;
	    	    }
	    	    
	        } catch (ParseException e) {
	            System.out.println("Error: Formato de fecha incorrecto. Usa YYYY/MM/DD.");
	        }
	    }

	 
	    if(fechaEd.after(fechaSd)) {
	    	System.out.println("Error. La fecha de entrada no puede ser posterior a la fecha de salida.");
	    	return null;
	    }
	  
	    Reserva reserva = new Reserva();
	    reserva.setFechaEntrada(fechaEd);
	    reserva.setFechaSalida(fechaSd);

	    return reserva;
	}

	public static Reserva agregarReserva(Scanner sc, int idOficina, Date fechaEd, Date fechaSd) {//Creamos la reserva y verificamos antes de insertarla en la BBDD.
	    System.out.println("\n--- Añadir Reserva ---");
	    System.out.println("Código de la vivienda:");
	    int codVivienda = sc.nextInt();
	    sc.nextLine();
	    if (!GestionReserva.esViviendaDeOficina(codVivienda, idOficina)) {
	        System.out.println("Error: La vivienda seleccionada no pertenece a la oficina indicada.");
	        return null;
	    }

	    long ms = fechaSd.getTime() - fechaEd.getTime();
	    long dias = ms / (1000 * 60 * 60 * 24);

	    System.out.print("Número de huéspedes: ");
	    int huespedes = sc.nextInt();

	    double precioDia = GestionReserva.obtenerPrecioDiaVivienda(codVivienda);

	    double totalPagado = totalPagar(dias, precioDia);
	    System.out.println("El total a pagar por la reserva: "+totalPagado+" euros.");

	    String dniUsuario = GestionUsuario.getDniUsuario();
	    
	    Reserva reserva = new Reserva(dniUsuario, codVivienda, fechaEd, fechaSd, huespedes, totalPagado);
	    
	    return reserva;
	}
	public static Double totalPagar(long dias, double precioDia) {
		return dias*precioDia;
	}
	
	
	 }


