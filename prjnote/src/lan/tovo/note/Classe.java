package lan.tovo.note;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.util.ArrayList;

public class Classe {
	
	private String numClasse;
	private String libelleClasse;
	
	private static Connection laConnexion = Connexion.Konnexion();
	private final ArrayList<Classe> results = new ArrayList<Classe>();
		
	public Classe() {
		
	}

	public Classe(String numClasse) {
		this.numClasse = numClasse;
	}

	public Classe(String numClasse, String libelleClasse) {
		
		this.numClasse = numClasse;
		this.libelleClasse = libelleClasse;
	}

	public String getNumClasse() {
		return numClasse;
	}

	public void setNumClasse(String numClasse) {
		this.numClasse = numClasse;
	}

	public String getLibelleClasse() {
		return libelleClasse;
	}

	public void setLibelleClasse(String libelleClasse) {
		this.libelleClasse = libelleClasse;
	}
	
	//****
	public boolean creerCRUD(){
				
		boolean creation = false;
		try{
					Statement state = laConnexion.createStatement();
			String requete = "INSERT INTO Classe(numClasse,libelleClasse)"
			+ "VALUES('"+this.numClasse+"','"+this.libelleClasse+"')";
				
					state.executeUpdate(requete);
					state.close();
					creation=true;
					
		}catch(SQLException e){
					JOptionPane.showMessageDialog(null, "Problème rencontré :" + e.getMessage(),
					"Résultat",JOptionPane.ERROR_MESSAGE);
		}
		return creation;
	}
		
	//***
	public ArrayList<Classe> lireRecupCRUD(){
		try{
			Statement state = laConnexion.createStatement();
			ResultSet rs = state.executeQuery("SELECT * FROM Classe ORDER BY numClasse");
					
			while (rs.next()){
						
				results.add(new Classe(rs.getString(1),
								rs.getString(2))
				);
			}
					
			}catch(SQLException e){
				JOptionPane.showMessageDialog
						(null, "Problème rencontré : " + e.getMessage(),"ERREUR",JOptionPane.ERROR_MESSAGE);
			}
			return results;
	}
	
	public ArrayList<Classe> lireRecupLigne(String numClasse){
		try{
			Statement state = laConnexion.createStatement();
			ResultSet rs = state.executeQuery("SELECT * FROM Classe "
					+ " WHERE numClasse = '" + numClasse + "'");
			
			while (rs.next()){
				
				results.add(new Classe(rs.getString(1),
						rs.getString(2))
				);
			}
			
		}catch(SQLException e){
			JOptionPane.showMessageDialog
				(null, "Problème rencontré : " + e.getMessage(),"ERREUR",JOptionPane.ERROR_MESSAGE);
		}
		return results;
	}
	
	public boolean modifierCRUD(){
		boolean modif = true;
		String requete = null;
		try{
			requete = "UPDATE Classe SET "
					+ "numClasse = '" +this.numClasse+ "',"
					+ "libelleClasse = '" +this.libelleClasse+ "'"				
					
					+ " WHERE numClasse = '" + this.numClasse + "'";
							
			Statement state = laConnexion.createStatement();
			state.executeUpdate(requete);
			state.close();
		}catch(SQLException e){
			modif = false;
			JOptionPane.showMessageDialog(null, "Modification non effectuée :" + e.getMessage(),
					"Problème rencontré",JOptionPane.ERROR_MESSAGE);
		}
		return modif;
	}

	public boolean supprimerCRUD(String numClasse){
		boolean suppr = true;
		try{
			String requete = "DELETE FROM Classe "
							+ " WHERE numClasse = '" + numClasse + "'";
			Statement state = laConnexion.createStatement();
			state.executeUpdate(requete);
		}catch(SQLException e){
			suppr = false;
			JOptionPane.showMessageDialog(null, "Suppression non effectuée :" + e.getMessage(),
					"Problème rencontré",JOptionPane.ERROR_MESSAGE);
		}
		return suppr;
	}

	public ArrayList<Classe> chercherCRUD(String recherche){
		
		String requete ="SELECT * FROM Classe"
				+ " WHERE numClasse LIKE '%" +recherche+ "%'"
				+ " OR libelleClasse LIKE '%" +recherche+ "%'";
		
		try{
			   Statement state=laConnexion.createStatement();
			   ResultSet rs=state.executeQuery(requete);
			   while (rs.next()){
				   String numClasse = rs.getString("numClasse");
				   String libelleClasse = rs.getString("libelleClasse");
				   			  
				   
				   results.add(new Classe(numClasse, libelleClasse));
			   }
		   }
		   catch(SQLException e){	
			   JOptionPane.showMessageDialog(null,"Problème rencontré : "
					   +e.getMessage(),
					   "Résultat",JOptionPane.ERROR_MESSAGE);
		   }
		
		
		return results;
	}
	
}
