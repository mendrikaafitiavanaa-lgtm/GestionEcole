package lan.tovo.note;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.util.ArrayList;

import java.util.Date;


public class Presence {
	private Eleve numEleve;
	private FichePresence numFiche;
	private String  present;
	
	private static Connection laConnexion = Connexion.Konnexion();
	private final ArrayList<Presence> results = new ArrayList<Presence>();
	
	public Presence() {
		
	}

	public Presence(Eleve numEleve, FichePresence numFiche) {
		this.numEleve = numEleve;
		this.numFiche = numFiche;
	}

	public Presence(Eleve numEleve, FichePresence numFiche, String present) {
		this.numEleve = numEleve;
		this.numFiche = numFiche;
		this.present = present;
	}

	public Eleve getNumEleve() {
		return numEleve;
	}

	public void setNumEleve(Eleve numEleve) {
		this.numEleve = numEleve;
	}

	public FichePresence getNumFiche() {
		return numFiche;
	}

	public void setNumFiche(FichePresence numFiche) {
		this.numFiche = numFiche;
	}

	public String getPresent() {
		return present;
	}

	public void setPresent(String present) {
		this.present = present;
	}
	 // ----- SELECT filtr� par classe -----
    public ArrayList<Presence> lirePresencesParNumClasse(String numClasseChoisi) {
        ArrayList<Presence> results = new ArrayList<>();

        String query = "SELECT Presence.numFiche, Presence.present, " +
                       "Eleve.numEleve, Eleve.numero, Eleve.nomEleve, Eleve.prenomEleve, " +
                       "Classe.numClasse, Classe.libelleClasse " +
                       "FROM Presence " +
                       "INNER JOIN Eleve ON Presence.numEleve = Eleve.numEleve " +
                       "INNER JOIN Classe ON Eleve.numClasse = Classe.numClasse " +
                       "WHERE Presence.numFiche = ?";

        try (PreparedStatement ps = laConnexion.prepareStatement(query)) {
            ps.setString(1, numClasseChoisi);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String numFicheStr = rs.getString("numFiche");

                String present = rs.getString("present");

                String numEleve = rs.getString("numEleve");
                String numero = rs.getString("numero");
                String nom = rs.getString("nomEleve");
                String prenom = rs.getString("prenomEleve");

                String numClasse = rs.getString("numClasse");
                String libClasse = rs.getString("libelleClasse");

                Eleve eleve = new Eleve(numEleve, numero, nom, prenom);
                FichePresence fiche = new FichePresence(numFicheStr);
                Classe classe = new Classe(numClasse, libClasse);

                results.add(new Presence(eleve, fiche, present));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                "Erreur filtrage par num�ro de classe : " + e.getMessage(),
                "ERREUR", JOptionPane.ERROR_MESSAGE);
        }
        return results;
    }
	
	//************
	public boolean creerCRUD(String numEleve, String numFiche, 
						String present ){
					
		boolean creation = false;
		try{
				Statement state = laConnexion.createStatement();
				String requete = "INSERT INTO Presence(numEleve,numFiche,present)"
							+ "VALUES('"+numEleve+"','"+numFiche+"','"+present+"')";
					
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
	public ArrayList<Presence> lireRecupCRUD(){
		try{
				Statement state = laConnexion.createStatement();
							
				ResultSet rs = state.executeQuery("SELECT numEleve,numFiche,present"						 
						+ " FROM Presence  ORDER BY numFiche,numEleve ASC"
						
				);
							
				while (rs.next()){						
					String numEleve = rs.getString("numEleve");
					String numFiche = rs.getString("numFiche");
					String present = rs.getString("present");
					
						
					
					results.add(new Presence(new Eleve(numEleve),
							new FichePresence(numFiche),
							present )
					);
								
				}
							
		}catch(SQLException e){
				JOptionPane.showMessageDialog
					(null, "Problème rencontré : " + e.getMessage(),"ERREUR",JOptionPane.ERROR_MESSAGE);
		}
		return results;
	}	
	
	//***
	
		public ArrayList<Presence> lireRecupLigneParFiche(String innumFiche){
			try{
				Statement state = laConnexion.createStatement();
				ResultSet rs = state.executeQuery("SELECT numEleve,numFiche, present"								
						
						+ " FROM Presence"
						+ " WHERE numFiche = '" + innumFiche + "'");
							
				while (rs.next()){						
					String numEleve = rs.getString("numEleve");
					String numFiche =  rs.getString("numFiche");
					String present = rs.getString("present");
					
											
					results.add(new Presence(new Eleve(numEleve),
							new FichePresence(numFiche),
							present )
					);
								
				}
							
			}catch(SQLException e){
				JOptionPane.showMessageDialog
						(null, "Problème rencontré : " + e.getMessage(),"ERREUR",JOptionPane.ERROR_MESSAGE);
			}
			return results;
		}

	
	
	
}
