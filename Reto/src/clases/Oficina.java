package clases;

public class Oficina {
	
	//Atributos
	private int id;
	private int telefono;
	private String ubicacion;
	
	//Constructor vacío
	public Oficina() {
		super();
	}
	
//Constructor con atributos
	public Oficina(int id, int telefono, String ubicacion) {
		super();
		this.id = id;
		this.telefono = telefono;
		this.ubicacion = ubicacion;
	}
	
//Getters y Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	
}
