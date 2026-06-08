package lan.tovo.note;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ModelePresence extends AbstractTableModel{
	
	private String numFiche;
	
	public ModelePresence() {
		
	}

	
	public ModelePresence(String numFiche) {
		this.numFiche = numFiche;
	}
	
	

	public String getNumFiche() {
		return numFiche;
	}


	public void setNumFiche(String numFiche) {
		this.numFiche = numFiche;
	}



	Presence instancePresence = new Presence();
	
	private ArrayList<Presence> lesDonnees = instancePresence.lireRecupCRUD();
	//public ArrayList<Presence> lesDonnees = instancePresence.lireRecupLigneParFiche(this.getNumFiche());
	
	private final String [] lesTitres = {"N° Eleve", "N° Fiche", "Present"};
	
	
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return lesTitres.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return lesDonnees.size();
	}
	@Override
	public String getColumnName(int columnIndex){
		
		return lesTitres[columnIndex];
	}
	


	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		switch (columnIndex) {
		case 0 :
			return lesDonnees.get(rowIndex).getNumEleve().getNumEleve();
		case 1 :
			return lesDonnees.get(rowIndex).getNumFiche().getNumFiche();
		case 2 :
			return lesDonnees.get(rowIndex).getPresent();	
		
		default :
			return null;
		}		
		
	}
	
	public int getNumLigne(int vCode) {
		String code = null;
		int numLigne = 0;
		lesDonnees.equals(vCode);
		
		for(int i=0 ; i<lesDonnees.size() ; i++){
			code=lesDonnees.get(i).getNumEleve().toString();
			if(code.equals(vCode)){
				numLigne=i;
			}
		}
		return numLigne;
		
	}
	
	
	
	public void creerMOD( Presence laPresence) {
		lesDonnees.add(laPresence);
		fireTableRowsInserted(lesDonnees.size()-1,lesDonnees.size()-1);
		
	}
	
	public void supprimerMOD(int rowIndex) {
		lesDonnees.remove(rowIndex);
		fireTableRowsDeleted(rowIndex,rowIndex);
	}
	
	public void modifierMOD(int firstRow, int lastRow,  Presence laMatiere) {
		lesDonnees.set(firstRow, laMatiere);
		fireTableRowsUpdated(firstRow, lastRow);
	}

    // ----- filtrer par classe -----
    public void filtrerParNumClasse(String numClasse) {
        Presence p = new Presence();
        ArrayList<Presence> nouvellesDonnees = p.lirePresencesParNumClasse(numClasse);
        lireRecupMOD(nouvellesDonnees); // fireTableDataChanged() est déjà appelé
    }
	

    public void lireRecupMOD(ArrayList<Presence> nouvellesDonnees) {
        lesDonnees = nouvellesDonnees;
        fireTableDataChanged();
    }


}
