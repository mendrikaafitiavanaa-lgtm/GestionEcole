package lan.tovo.note;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.util.ArrayList;

public class Enseignant {
	
	private String numEnseignant;
	private String nomEnseignant;
	private String prenomEnseignant;
	
	private static Connection laConnexion = Connexion.Konnexion();
	private final ArrayList<Enseignant> results = new ArrayList<Enseignant>();
	
	public Enseignant() {
		
	}

	public Enseignant(String numEnseignant) {
		this.numEnseignant = numEnseignant;
	}

	public Enseignant(String numEnseignant, String nomEnseignant, String prenomEnseignant) {
		
		this.numEnseignant = numEnseignant;
		this.nomEnseignant = nomEnseignant;
		this.prenomEnseignant = prenomEnseignant;
	}

	public String getNumEnseignant() {
		return numEnseignant;
	}

	public void setNumEnseignant(String numEnseignant) {
		this.numEnseignant = numEnseignant;
	}

	public String getNomEnseignant() {
		return nomEnseignant;
	}

	public void setNomEnseignant(String nomEnseignant) {
		this.nomEnseignant = nomEnseignant;
	}

	public String getPrenomEnseignant() {
		return prenomEnseignant;
	}

	public void setPrenomEnseignant(String prenomEnseignant) {
		this.prenomEnseignant = prenomEnseignant;
	}
	
	//****
	public boolean creerCRUD(){
				
		boolean creation = false;
		try{
					Statement state = laConnexion.createStatement();
			String requete = "INSERT INTO Enseignant(numEnseignant, nomEnseignant, prenomEnseignant)"
			+ "VALUES('"+this.numEnseignant+"','"+this.nomEnseignant+"','"+this.prenomEnseignant+"')";
				
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
	public ArrayList<Enseignant> lireRecupCRUD(){
		try{
			Statement state = laConnexion.createStatement();
			ResultSet rs = state.executeQuery("SELECT * FROM Enseignant ORDER BY numEnseignant");
						
			while (rs.next()){
							
				results.add(new Enseignant(rs.getString(1),
							rs.getString(2),
							rs.getString(3))
				);
			}
						
		}catch(SQLException e){
				JOptionPane.showMessageDialog
					(null, "Problème rencontré : " + e.getMessage(),"ERREUR",JOptionPane.ERROR_MESSAGE);
		}
		return results;
	}

	public ArrayList<Enseignant> lireRecupLigne(String numEnseignant){
		try{
			Statement state = laConnexion.createStatement();
			ResultSet rs = state.executeQuery("SELECT * FROM Enseignant "
					+ " WHERE numEnseignant = '" + numEnseignant + "'");
			
			while (rs.next()){
				results.add(new Enseignant(rs.getString(1),
						rs.getString(2),
						rs.getString(3))
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
			requete = "UPDATE Enseignant SET "
					+ "numEnseignant ='" +this.numEnseignant+ "',"
					+ "nomEnseignant = '" +this.nomEnseignant+ "', "
					+ "prenomEnseignant = '" +this.prenomEnseignant+ "'"					
					+ " WHERE numEnseignant = '" + this.numEnseignant + "'";
							
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

	public boolean supprimerCRUD(String numEnseignant){
		boolean suppr = true;
		try{
			String requete = "DELETE FROM Enseignant "
							+ " WHERE numEnseignant = '" + numEnseignant + "'";
			Statement state = laConnexion.createStatement();
			state.executeUpdate(requete);
		}catch(SQLException e){
			suppr = false;
			JOptionPane.showMessageDialog(null, "Suppression non effectuée :" + e.getMessage(),
					"Problème rencontré",JOptionPane.ERROR_MESSAGE);
		}
		return suppr;
	}
	
public ArrayList<Enseignant> chercherCRUD(String recherche){
		
		String requete ="SELECT * FROM Enseignant"
				+ " WHERE numEnseignant LIKE '%" +recherche+ "%'"
				+ " OR nomEnseignant LIKE '%" +recherche+ "%'";
		
		try{
			   Statement state=laConnexion.createStatement();
			   ResultSet rs=state.executeQuery(requete);
			   while (rs.next()){
				   String numEnseignant = rs.getString("numEnseignant");
				   String nomEnseignant = rs.getString("nomEnseignant");
				   String prenomEnseignant = rs.getString("prenomEnseignant");			  
				   
				   results.add(new Enseignant(numEnseignant, nomEnseignant, prenomEnseignant));
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
