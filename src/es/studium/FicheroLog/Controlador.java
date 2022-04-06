package es.studium.FicheroLog;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Controlador implements WindowListener, ActionListener
{
	Login login;
	Modelo modelo;
	MenuPrincipal menuPrincipal;
	String usuario;

	public Controlador(Login l, Modelo m)
	{
		login = l;
		modelo = m;
		login.ventana.addWindowListener(this);
		login.btnCancelar.addActionListener(this);
		login.btnAceptar.addActionListener(this);
		login.dlgMensaje.addWindowListener(this);
	}

	public void windowActivated(WindowEvent we) {}
	public void windowClosed(WindowEvent we) {}
	public void windowClosing(WindowEvent we)
	{
		if(login.dlgMensaje.isActive())
		{
			login.dlgMensaje.setVisible(false);
		}
		else if(menuPrincipal.ventana.isActive())
		{
			System.exit(0);
		}
		else
		{
			System.exit(0);
		}
	}
	public void windowDeactivated(WindowEvent we) {}
	public void windowDeiconified(WindowEvent we) {}
	public void windowIconified(WindowEvent we) {}
	public void windowOpened(WindowEvent we) {}

	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource().equals(login.btnCancelar))
		{
			login.txtUsuario.setText("");
			login.txtClave.setText("");
			login.txtUsuario.requestFocus();
		}
		else if(ae.getSource().equals(login.btnAceptar))
		{
			modelo.conectar();
			usuario = login.txtUsuario.getText();
			String clave = login.txtClave.getText();
			int resultado = modelo.consultar("SELECT * FROM usuarios WHERE nombreUsuario = '"+usuario+
					"' AND claveUsuario = SHA2('"+clave+"', 256);");
			if(resultado==-1)
			{
				login.dlgMensaje.setSize(180,75);
				login.dlgMensaje.setLayout(new FlowLayout());
				login.dlgMensaje.setResizable(false);
				login.lblMensaje.setText("Credenciales incorrectas");
				login.dlgMensaje.add(login.lblMensaje);
				login.dlgMensaje.setLocationRelativeTo(null);
				login.dlgMensaje.setVisible(true);
				modelo.guardarLog(usuario,"Login incorrecto");
			}
			else
			{
				modelo.guardarLog(usuario,"Login");
				menuPrincipal = new MenuPrincipal(resultado);
				menuPrincipal.ventana.addWindowListener(this);
				menuPrincipal.btnAceptar.addActionListener(this);
				menuPrincipal.btnCancelar.addActionListener(this);
				login.ventana.setVisible(false);
			}
			modelo.desconectar();
		}
		else if(ae.getSource().equals(menuPrincipal.btnAceptar))
		{
			modelo.guardarLog(usuario, menuPrincipal.txtTexto.getText().toLowerCase());
		}
		else if(ae.getSource().equals(menuPrincipal.btnCancelar))
		{
			modelo.guardarLog(usuario, menuPrincipal.txtTexto.getText().toUpperCase());
		}
	}
}
