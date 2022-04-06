package es.studium.FicheroLog;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Modelo
{
	String driver = "com.mysql.cj.jdbc.Driver"; 
	String url = "jdbc:mysql://localhost:3306/empresatextil"; 
	String login = "usuario_textil"; // Usuario MySQL
	String password = "Studium2022;"; // Su clave correspondiente
	String sentencia = "";
	Connection connection = null; 
	Statement statement = null;

	public void conectar()
	{
		try
		{
			//Cargar los controladores para el acceso a la BD
			Class.forName(driver);
			//Establecer la conexión con la BD Empresa
			connection = DriverManager.getConnection(url, login, password);
		}
		catch (ClassNotFoundException cnfe)
		{}
		catch (SQLException sqle)
		{}	
	}

	public int consultar(String sentencia)
	{
		int resultado = -1;
		ResultSet rs = null;
		try
		{
			statement = connection.createStatement();
			rs = statement.executeQuery(sentencia);
			if(rs.next())
			{
				resultado = rs.getInt("tipoUsuario");
			}
		}
		catch (SQLException sqle)
		{}
		return resultado;
	}

	public void guardarLog(String usuario, String mensaje)
	{
		Date fecha = new Date();
		String pattern = "dd/MM/YYYY HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		try
		{
			FileWriter fw = new FileWriter("historico.log", true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter salida = new PrintWriter(bw);
			
			salida.println("["+simpleDateFormat.format(fecha)+"]["+
			usuario + "]["+mensaje+"]");

			salida.close();
			bw.close();
			fw.close();
		}
		catch(IOException ioe)
		{
			System.out.println("Error:"+ioe.getMessage());
		}
	}

	public void desconectar()
	{
		try
		{
			if(connection!=null)
			{
				connection.close();
			}
		}
		catch (SQLException e)
		{}
	}
}
