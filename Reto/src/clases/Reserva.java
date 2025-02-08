package clases;

import java.sql.Date;

public class Reserva {
	
	//Atributos
	private String dniUsuario;
	private int codVivienda;
	private Date fechaEntrada;
	private Date fechaSalida;
	private int numHuespedes;
	private double totalPagado;
	
	//Constructor vacío
	public Reserva() {
		super();
	}

	//Constructor con atributos
	public Reserva(String dniUsuario, int codVivienda, Date fechaEntrada, Date fechaSalida, int numHuespedes, double totalPagado) {
		super();
		this.dniUsuario= dniUsuario;
		this.codVivienda= codVivienda;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.numHuespedes = numHuespedes;
		this.totalPagado = totalPagado;
	}
	
	//Getters y Setters
	
	public String getDniUsuario() {
		return dniUsuario;
	}

	public void setDniUsuario(String dniUsuario) {
		this.dniUsuario = dniUsuario;
	}
	
	public int getCodVivienda() {
		return codVivienda;
	}

	public void setCodVivienda(int codVivienda) {
		this.codVivienda = codVivienda;
	}

	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public int getNumHuespedes() {
		return numHuespedes;
	}

	public void setNumHuespedes(int numHuespedes) {
		this.numHuespedes = numHuespedes;
	}

	public double getTotalPagado() {
		return totalPagado;
	}

	public void setTotalPagado(double totalPagado) {
		this.totalPagado = totalPagado;
	}
	
}
