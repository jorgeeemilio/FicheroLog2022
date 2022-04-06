package es.studium.FicheroLog;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;

public class Login
{
	Frame ventana = new Frame("Login");
	Label lblUsuario = new Label("Usuario");
	Label lblClave = new Label("Clave");
	TextField txtUsuario = new TextField(10);
	TextField txtClave = new TextField(10);
	Button btnAceptar = new Button("Aceptar");
	Button btnCancelar = new Button("Cancelar");
	
	Dialog dlgMensaje = new Dialog(ventana, "Mensaje", true);
	Label lblMensaje = new Label("XXXXXXXXXXXXXXXXXXXX");

	public Login()
	{
		ventana.setSize(220, 150);
		ventana.setResizable(false);

		ventana.setLayout(new FlowLayout());
		ventana.add(lblUsuario);
		ventana.add(txtUsuario);
		ventana.add(lblClave);
		txtClave.setEchoChar('*');
		ventana.add(txtClave);
		ventana.add(btnAceptar);
		ventana.add(btnCancelar);

		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
	}
}
