package clases;

public class Piso extends Vivienda {
	
	//Atributos
	private String planta;

	//Constructor vacío
	public Piso() {
		super();
	}
	
	//Constructor con atributos
	public Piso(int idOficina, String ciudad, String direccion, int numHab, String descripcion, double precioDia, String tipo_Vivienda, String planta) {
		super();
		this.planta = planta;
	}
	
	//Getters y Setters
	public String getPlanta() {
		return planta;
	}

	public void setPlanta(String planta) {
		this.planta = planta;
	}
		
}
