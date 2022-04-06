package es.studium.FicheroLog;

public class Principal
{
	public static void main(String[] args)
	{
		Login login = new Login(); // Ventana de Login
		Modelo modelo = new Modelo(); // Funcionalidad BD
		new Controlador(login, modelo);
	}
}