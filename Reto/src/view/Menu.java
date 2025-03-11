package view;

import java.util.Scanner;

import repositorios.ConectorBD;

public class Menu {
	public static Scanner sc=new Scanner(System.in);
	

		public static void MenuCompleto() {
			
			ConectorBD.conectar();
			MenuUsuario.mostrarMenuUsuario(sc);
			ConectorBD.cerrarConexion();
		}

}
