package lan.tovo.note;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import java.sql.PreparedStatement;


import java.util.ArrayList;

public class Eleve {
	
	private String numEleve;
	private String numero;
	private String nomEleve;
	private String prenomEleve;
	private Classe numClasse;
	
	private static Connection laConnexion = Connexion.Konnexion();
	private final ArrayList<Eleve> results = new ArrayList<Eleve>();
	
	
	public Eleve() {
		
	}

	public Eleve(String numEleve) {
		this.numEleve = numEleve;
	}

	
	
	public Eleve(String numEleve, String numero, String nomEleve, String prenomEleve) {
		
		this.numEleve = numEleve;
		this.numero = numero;
		this.nomEleve = nomEleve;
		this.prenomEleve = prenomEleve;
	}

	public Eleve(String numEleve, String numero, String nomEleve,
			String prenomEleve, Classe numClasse) {
		this.numEleve = numEleve;
		this.numero = numero;
		this.nomEleve = nomEleve;
		this.prenomEleve = prenomEleve;
		this.numClasse = numClasse;
	}

	public String getNumEleve() {
		return numEleve;
	}

	public void setNumEleve(String numEleve) {
		this.numEleve = numEleve;
	}

	public String getNomEleve() {
		return nomEleve;
	}

	public void setNomEleve(String nomEleve) {
		this.nomEleve = nomEleve;
	}

	public String getPrenomEleve() {
		return prenomEleve;
	}

	public void setPrenomEleve(String prenomEleve) {
		this.prenomEleve = prenomEleve;
	}

	public Classe getNumClasse() {
		return numClasse;
	}

	public void setNumClasse(Classe numClasse) {
		this.numClasse = numClasse;
	}
	
	
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	//************
	public boolean creerCRUD(String numEleve, String numero,String nomEleve, 
			String prenomEleve, String numClasse ){
		
		boolean creation = false;
		try{
			Statement state = laConnexion.createStatement();
			String requete = "INSERT INTO Eleve(numEleve,numero,nomEleve,prenomEleve,"
					+ "numClasse )"
					+ "VALUES('"+numEleve+"','"+numero+"','"+nomEleve+"','"+prenomEleve+"','"+numClasse+"')";
		
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
	public ArrayList<Eleve> lireRecupCRUD(){
		try{
			Statement state = laConnexion.createStatement();
						
			ResultSet rs = state.executeQuery("SELECT numEleve,numero,nomEleve,prenomEleve,"
					+ " Classe.numClasse, libelleClasse " 
					+ " FROM Eleve INNER JOIN Classe ON Eleve.numClasse = Classe.numClasse"
			);
						
			while (rs.next()){						
				String numEleve = rs.getString("numEleve");
				String numero =  rs.getString("numero");
				String nomEleve = rs.getString("nomEleve");
				String prenomEleve = rs.getString("prenomEleve");
				
				String numClasse = rs.getString("numClasse");
				String libelleClasse = rs.getString("libelleClasse");
							
				results.add(new Eleve(numEleve,numero,nomEleve,prenomEleve,
					new Classe(numClasse,libelleClasse)
				));
							
			}
						
		}catch(SQLException e){
			JOptionPane.showMessageDialog
				(null, "Problème rencontré : " + e.getMessage(),"ERREUR",JOptionPane.ERROR_MESSAGE);
		}
		return results;
	}

	
	//***
	
	public ArrayList<Eleve> lireRecupLigne(String innumEleve){
		try{
			Statement state = laConnexion.createStatement();
			ResultSet rs = state.executeQuery("SELECT numEleve,numero, nomEleve,prenomEleve,"								
					+ " Classe.numClasse, libelleClasse "
					+ " FROM Eleve, Classe"
					+ " WHERE numEleve = '" + innumEleve + "'");
						
			while (rs.next()){						
				String numEleve = rs.getString("numEleve");
				String numero =  rs.getString("numero");
				String nomEleve = rs.getString("nomEleve");
				String prenomEleve = rs.getString("prenomEleve");
							
				String numClasse = rs.getString("numClasse");
				String libelleClasse = rs.getString("libelleClasse");
										
				results.add(new Eleve(numEleve,numero,nomEleve,prenomEleve,
						new Classe(numClasse,libelleClasse)
					));
							
			}
						
		}catch(SQLException e){
			JOptionPane.showMessageDialog
					(null, "Problème rencontré : " + e.getMessage(),"ERREUR",JOptionPane.ERROR_MESSAGE);
		}
		return results;
	}

	
	//***
	
	public boolean modifierCRUD(String numEleve, String numero,String nomEleve, String prenomEleve,  String numClasse){
			boolean modif = true;
			String requete = null;
			try{
				requete = "UPDATE Eleve SET "
						+ "numero ='" +numero+ "',"
						+ "nomEleve ='" +nomEleve+ "',"
						+ "prenomEleve = '" +prenomEleve+ "'"
						+ "numClasse = '" +numClasse+ "'"
						+ " WHERE numEleve = '" +numEleve + "'";
								
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

	//***
	
	public boolean supprimerCRUD(String innumEleve){
		boolean suppr = true;
		try{
			String requete = "DELETE FROM Eleve "
							+ " WHERE numEleve = '" + innumEleve + "'";
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
	
	public ArrayList<Eleve> chercherCRUD(String recherche){
			
			String requete = "SELECT numEleve, numero,nomEleve,prenomEleve," + 				
					" Classe.numClasse, Classe.libelleClasse " +
					" FROM Eleve INNER JOIN Classe ON Eleve.numClasse = Classe.numClasse"
					+ " WHERE numEleve LIKE '%" +recherche+ "%'"
					+ " OR nomEleve LIKE '%" +recherche+ "%'";
			
			try{
				   Statement state=laConnexion.createStatement();
				   ResultSet rs=state.executeQuery(requete);
				   while (rs.next()){						
					   String numEleve = rs.getString("numEleve");
					   String numero =  rs.getString("numero");
						String nomEleve = rs.getString("nomEleve");
						String prenomEleve = rs.getString("prenomEleve");
									
						String numClasse = rs.getString("numClasse");
						String libelleClasse = rs.getString("libelleClasse");
												
						results.add(new Eleve(numEleve,numero,nomEleve,prenomEleve,
								new Classe(numClasse,libelleClasse)
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
	
	//***
	
		public ArrayList<Eleve> lireRecupEleveParClasse(String innumClasse){
			try{
				Statement state = laConnexion.createStatement();
				ResultSet rs = state.executeQuery(" SELECT numEleve,numero, nomEleve,prenomEleve"								
								+ " FROM Eleve"
								+ " WHERE Eleve.numClasse = '" + innumClasse + "'");
							
				while (rs.next()){						
					String numEleve = rs.getString("numEleve");
					String numero =  rs.getString("numero");
					String nomEleve = rs.getString("nomEleve");
					String prenomEleve = rs.getString("prenomEleve");
								
					
											
					results.add(new Eleve(numEleve,numero,nomEleve,prenomEleve
							
						));
								
				}
							
			}catch(SQLException e){
				JOptionPane.showMessageDialog
						(null, "Problème rencontré : " + e.getMessage(),"ERREUR",JOptionPane.ERROR_MESSAGE);
			}
			return results;
		}
		
		public ArrayList<Eleve> lireRecupEleveParId(String numEleve) {
	        String query = "SELECT numEleve, numero, nomEleve, prenomEleve FROM Eleve WHERE numEleve = ?";

	        try (PreparedStatement ps = (PreparedStatement) laConnexion.prepareStatement(query)) {
	            ps.setString(1, numEleve);
	            ResultSet rs = ps.executeQuery();

	            while (rs.next()) {
	                results.add(new Eleve(
	                    rs.getString("numEleve"),
	                    rs.getString("numero"),
	                    rs.getString("nomEleve"),
	                    rs.getString("prenomEleve")
	                ));
	            }
	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(null,
	                "Erreur récupération élève par ID : " + e.getMessage(),
	                "ERREUR", JOptionPane.ERROR_MESSAGE);
	        }
	        return results;
	    }
	    public ArrayList<Eleve> lireRecupEleveParNumero(String numero) {
	        String query = "SELECT numEleve, numero, nomEleve, prenomEleve FROM Eleve WHERE numero = ?";

	        try (PreparedStatement ps = (PreparedStatement) laConnexion.prepareStatement(query)) {
	            ps.setString(1, numero);
	            ResultSet rs = ps.executeQuery();

	            while (rs.next()) {
	                results.add(new Eleve(
	                    rs.getString("numEleve"),
	                    rs.getString("numero"),
	                    rs.getString("nomEleve"),
	                    rs.getString("prenomEleve")
	                ));
	            }
	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(null,
	                "Erreur récupération élève par numéro : " + e.getMessage(),
	                "ERREUR", JOptionPane.ERROR_MESSAGE);
	        }
	        return results;
	    }


}
