package repositorios;

public interface InterfazRepositorio<T> {
	public void insertar(T t);
	public void modificar(T t);
	public void eliminar();
	public void mostrar();

}
