package view;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

	        int opcion2 = sc.nextInt();
	        sc.nextLine(); // Limpiar el buffer

	        switch (opcion2) {
	            case 1:   
	            	Reserva reserva=consultarFechas(sc);
	            	if (reserva == null) {
	            	    System.out.println("La fecha de entrada no puede ser posterior a la fecha de salida.");
	            	    return; // O manejar el error de otra forma
	            	}
	            	GestionReserva.consultarFechaBD(idOficina,reserva.getFechaEntrada(), reserva.getFechaSalida());
	            	System.out.println("Viviendas disponibles en estas fechas:");
	            	Reserva res=agregarReserva(sc, idOficina, reserva.getFechaEntrada(), reserva.getFechaSalida());
	            	if(res==null) {
	            		System.out.println("Cambia de oficina o selecciona una vivienda asociada a esta oficina.");
	            		return;
	            	}
	            	GestionReserva.insertarReserva(res);
	            	GestionReserva.mostrarReservas();
	            	return;
			case 2:
	          
	             return;

	            case 3:
	                salir = true;
	                System.out.println("Finalizando programa ¡Hasta la próxima!");
	                break;
	            default:
	                System.out.println("Opción no válida. Intenta de nuevo.");
	        }

	        }
	    }
	private static Date convertirFecha(String fechaString) {
	       
        SimpleDateFormat formatoEntrada = new SimpleDateFormat("yyyy/MM/dd");
        java.sql.Date fechaSql = null;
        try {
            // Convertir el String a java.util.Date
            java.util.Date fechaUtil = formatoEntrada.parse(fechaString);
            
            // Convertir el java.util.Date a java.sql.Date
            fechaSql = new java.sql.Date(fechaUtil.getTime());
            
            
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return fechaSql;
    }
	public static Reserva consultarFechas(Scanner sc) {
		System.out.println("Fecha de Entrada(yyyy/mm/dd):");
	    String fechaE = sc.nextLine();
	    Date fechaEd = MenuReservas.convertirFecha(fechaE);

	    System.out.print("Fecha de Salida(yyyy/mm/dd): ");
	    String fechaS = sc.nextLine();
	    
	   
	   
	    Date fechaSd = MenuReservas.convertirFecha(fechaS);
	    if(fechaEd.after(fechaSd)) {
	    	System.out.println("Error.");
	    	return null;
	    }
	    Reserva reserva=new Reserva();
	    reserva.setFechaEntrada(fechaEd);
	    reserva.setFechaSalida(fechaSd);
		return reserva;
	}
	public static Reserva agregarReserva(Scanner sc, int idOficina, Date fechaEd, Date fechaSd) {
	    System.out.println("\n--- Añadir Reserva ---");
	    System.out.println("Código de la vivienda:");
	    int codVivienda = sc.nextInt();
	    sc.nextLine();
	    if (!GestionReserva.esViviendaDeOficina(codVivienda, idOficina)) {
	        System.out.println("Error: La vivienda seleccionada no pertenece a la oficina indicada.");
	        return null; // Cancelar la reserva si la vivienda no es válida
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


