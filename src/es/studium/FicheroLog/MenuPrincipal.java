package es.studium.FicheroLog;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;

public class MenuPrincipal
{
	Frame ventana = new Frame("Pantalla Principal");
	Label lblMensaje = new Label("Principal");
	TextField txtTexto = new TextField(10);
	Button btnAceptar = new Button("Aceptar");
	Button btnCancelar = new Button("Cancelar");
	
	public MenuPrincipal(int tipoUsuario)
	{
		ventana.setSize(220, 150);
		ventana.setResizable(false);

		ventana.setLayout(new FlowLayout());
		ventana.add(lblMensaje);
		ventana.add(txtTexto);
		ventana.add(btnAceptar);
		ventana.add(btnCancelar);

		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
	}
}
