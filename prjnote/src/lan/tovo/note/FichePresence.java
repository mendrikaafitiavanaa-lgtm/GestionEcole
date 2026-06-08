package lan.tovo.note;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.util.ArrayList;

import java.util.Date;
import java.time.*;

public class FichePresence {
	
	private String numFiche;
	private Date dateFiche;
	private String heureFiche;
	private Enseignant numEnseignant;
	
	private static Connection laConnexion = Connexion.Konnexion();
	private final ArrayList<FichePresence> results = new ArrayList<FichePresence>();
	
	public FichePresence() {
		
	}
	
	

	public FichePresence(String numFiche) {
		this.numFiche = numFiche;
	}



	public FichePresence(String numFiche, Date dateFiche) {
		this.numFiche = numFiche;
		this.dateFiche = dateFiche;
	}

	public FichePresence(String numFiche, Date dateFiche, String heureFiche, 
			Enseignant numEnseignant) {
		this.numFiche = numFiche;
		this.dateFiche = dateFiche;
		this.heureFiche = heureFiche;
		this.numEnseignant = numEnseignant;
	}

	public String getNumFiche() {
		return numFiche;
	}

	public void setNumFiche(String numFiche) {
		this.numFiche = numFiche;
	}

	public Date getDateFiche() {
		return dateFiche;
	}

	public void setDateFiche(Date dateFiche) {
		this.dateFiche = dateFiche;
	}

	public String getHeureFiche() {
		return heureFiche;
	}

	public void setHeureFiche(String heureFiche) {
		this.heureFiche = heureFiche;
	}

	public Enseignant getNumEnseignant() {
		return numEnseignant;
	}

	public void setNumEnseignant(Enseignant numEnseignant) {
		this.numEnseignant = numEnseignant;
	}
	
//************
	public boolean creerCRUD(String numFiche, Date dateFiche, 
            String heureFiche, String numEnseignant) {
		boolean creation = false;
		try {
		// ⚡ Conversion de la date en format SQL (yyyy-MM-dd)
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
		String dateSQL = sdf.format(dateFiche);
		
		Statement state = laConnexion.createStatement();
		String requete = "INSERT INTO FichePresence(numFiche, dateFiche, heureFiche, numEnseignant) "
		          + "VALUES('" + numFiche + "','" + dateSQL + "','" + heureFiche + "','" + numEnseignant + "')";
		
		state.executeUpdate(requete);
		state.close();
		creation = true;
		
		} catch (SQLException e) {
		JOptionPane.showMessageDialog(null, "Problème rencontré :" + e.getMessage(),
		   "Résultat", JOptionPane.ERROR_MESSAGE);
		}
		return creation;
}



	//***
	public ArrayList<FichePresence> lireRecupCRUD(){
		try{
				Statement state = laConnexion.createStatement();
							
				ResultSet rs = state.executeQuery("SELECT numFiche,dateFiche,heureFiche,"
						+ " Enseignant.numEnseignant, nomEnseignant, prenomEnseignant " 
						+ " FROM FichePresence INNER JOIN Enseignant ON FichePresence.numEnseignant = Enseignant.numEnseignant ORDER BY numFiche"
						
				);
							
				while (rs.next()){						
					String numFiche = rs.getString("numFiche");
					Date dateFiche = rs.getDate("dateFiche");
					String heureFiche = rs.getString("heureFiche");
					
					String numEnseignant = rs.getString("numEnseignant");
					String nomEnseignant = rs.getString("nomEnseignant");
					String prenomEnseignant = rs.getString("prenomEnseignant");	
					
					results.add(new FichePresence(numFiche,dateFiche,heureFiche,
						new Enseignant(numEnseignant,nomEnseignant,prenomEnseignant)
					));
								
				}
							
		}catch(SQLException e){
				JOptionPane.showMessageDialog
					(null, "Problème rencontré : " + e.getMessage(),"ERREUR",JOptionPane.ERROR_MESSAGE);
		}
		return results;
	}	
	
	//***
	
	public ArrayList<FichePresence> lireRecupLigne(String innumFiche){
		try{
			Statement state = laConnexion.createStatement();
			ResultSet rs = state.executeQuery("SELECT numFiche,dateFiche,heureFiche,"								
					+ " Enseignant.numEnseignant, nomEnseignant, prenomEnseignant "
					+ " FROM FichePresence, Enseignant"
					+ " WHERE numFiche = '" + innumFiche + "'");
							
			while (rs.next()){						
				String numFiche = rs.getString("numFiche");
				Date dateFiche = rs.getDate("dateFiche");
				String heureFiche = rs.getString("heureFiche");
				
				String numEnseignant = rs.getString("numEnseignant");
				String nomEnseignant = rs.getString("nomEnseignant");
				String prenomEnseignant = rs.getString("prenomEnseignant");	
				
				results.add(new FichePresence(numFiche,dateFiche,heureFiche,
					new Enseignant(numEnseignant,nomEnseignant,prenomEnseignant)
				));
					
								
			}
							
		}catch(SQLException e){
				JOptionPane.showMessageDialog
						(null, "Problème rencontré : " + e.getMessage(),"ERREUR",JOptionPane.ERROR_MESSAGE);
		}
		return results;
	}

	//***
	
	public boolean modifierCRUD(String numFiche, Date dateFiche, 
            String heureFiche, String numEnseignant) {
			boolean modif = false;
			try {
			// ⚡ Conversion de la date en format SQL
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
			String dateSQL = sdf.format(dateFiche);
			
			String requete = "UPDATE FichePresence SET "
			+ "dateFiche = '" + dateSQL + "', "
			+ "heureFiche = '" + heureFiche + "', "
			+ "numEnseignant = '" + numEnseignant + "' "
			+ "WHERE numFiche = '" + numFiche + "'";
			
			Statement state = laConnexion.createStatement();
			state.executeUpdate(requete);
			state.close();
			modif = true;
			
			} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Modification non effectuée :" + e.getMessage(),
			"Problème rencontré", JOptionPane.ERROR_MESSAGE);
			}
			return modif;
}

	
	//***
	public boolean supprimerCRUD(String innumFiche){
		boolean suppr = true;
		try{
			String requete = "DELETE FROM FichePresence "
								+ " WHERE numFiche = '" + innumFiche + "'";
			Statement state = laConnexion.createStatement();
			state.executeUpdate(requete);
		}catch(SQLException e){
			suppr = false;
			JOptionPane.showMessageDialog(null, "Suppression non effectuée :" + e.getMessage(),
						"Problème rencontré",JOptionPane.ERROR_MESSAGE);
		}
		return suppr;
	}

	//****
	
	public ArrayList<FichePresence> chercherCRUD(String recherche){
			
			String requete = "SELECT numFiche,dateFiche,heureFiche," 
					+ " Enseignant.numEnseignant, Enseignant.nomEnseignant, Enseignant.prenomEnseignant "
					+ " FROM FichePresence INNER JOIN Enseignant ON FichePresence.numEnseignant = Enseignant.numEnseignant"
					+ " WHERE numMatiere LIKE '%" +recherche+ "%'"
					+ " OR numMatiere LIKE '%" +recherche+ "%'";
			
			try{
				   Statement state=laConnexion.createStatement();
				   ResultSet rs=state.executeQuery(requete);
				   while (rs.next()){						
					    String numFiche = rs.getString("numFiche");
						Date dateFiche = rs.getDate("dateFiche");
						String heureFiche = rs.getString("heureFiche");
						
						String numEnseignant = rs.getString("numEnseignant");
						String nomEnseignant = rs.getString("nomEnseignant");
						String prenomEnseignant = rs.getString("prenomEnseignant");	
						
						results.add(new FichePresence(numFiche,dateFiche,heureFiche,
							new Enseignant(numEnseignant,nomEnseignant,prenomEnseignant)
						));
							
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
