package clases;

public class Usuario {
	
	//Atributos
	private String dni;
	private String nombre;
	private String apellido;
	private String nomUs;
	private String email;
	private String contraseña;
	private String rol;
	
	//Constructor vacío
	public Usuario() {
		super();
	}

	//Constructor con atributos
	public Usuario(String dni, String nombre, String apellido, String nomUs, String email, String contraseña, String rol) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.nomUs = nomUs;
		this.email = email;
		this.contraseña = contraseña;
		this.rol= rol;
	}

	//Getters y Setters
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNomUs() {
		return nomUs;
	}

	public void setNomUs(String nomUs) {
		this.nomUs = nomUs;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	
}
