package lan.tovo.note;

import java.sql.*;
import javax.swing.JOptionPane;

public class Connexion {
	
	public static Connection Konnexion(){
		Connection laConnetion = null;
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");			
			laConnetion = DriverManager.getConnection("jdbc:mysql:///note","root","");
						
		}
		catch (ClassNotFoundException ex)
		{
			JOptionPane.showMessageDialog(null, 
					"Classe introuvable " + ex.getMessage());
		}
		catch (SQLException ex)
		{
			JOptionPane.showMessageDialog(null, 
					"Connexion impossible : " + ex.getMessage());
		}
		return laConnetion;
	}

}
