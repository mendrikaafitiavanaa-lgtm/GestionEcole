package lan.tovo.note;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;

public class ModeleFichePresence extends AbstractTableModel {
	
	FichePresence instanceFichePresence = new FichePresence();
	
	private ArrayList<FichePresence> lesDonnees = instanceFichePresence.lireRecupCRUD();
	
	private final String [] lesTitres = {"Numéro", "Date", "Heure","N°Enseignant", "Nom"};
	
	Enseignant instanceEnseignant = new Enseignant();
	String numenseigant = instanceEnseignant.getNumEnseignant();
	//private ArrayList<District> laDonnee = instanceDistrict.lireRecupLigne(numcommune);
	
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
	    switch (columnIndex) {
	        case 0:
	            return lesDonnees.get(rowIndex).getNumFiche();

	        case 1: // ⚡ Date formatée yyyy-MM-dd
	            Date d = lesDonnees.get(rowIndex).getDateFiche();
	            return  new java.text.SimpleDateFormat("yyyy-MM-dd").format(d) ;

	        case 2: // ⚡ Heure formatée HH:mm
	            String h = lesDonnees.get(rowIndex).getHeureFiche();
	            return  h ;

	        case 3:
	            return lesDonnees.get(rowIndex).getNumEnseignant().getNumEnseignant();

	        case 4:
	            return lesDonnees.get(rowIndex).getNumEnseignant().getNomEnseignant();

	        default:
	            return null;
	    }
	}

	
	public int getNumLigne(int vCode) {
		String code = null;
		int numLigne = 0;
		lesDonnees.equals(vCode);
		
		for(int i=0 ; i<lesDonnees.size() ; i++){
			code=lesDonnees.get(i).getNumFiche();
			if(code.equals(vCode)){
				numLigne=i;
			}
		}
		return numLigne;
		
	}
	
	
	
	public void creerMOD( FichePresence laFichePresence) {
		lesDonnees.add(laFichePresence);
		fireTableRowsInserted(lesDonnees.size()-1,lesDonnees.size()-1);
	}
	
	public void supprimerMOD(int rowIndex) {
		lesDonnees.remove(rowIndex);
		fireTableRowsDeleted(rowIndex,rowIndex);
	}
	
	public void modifierMOD(int firstRow, int lastRow,  FichePresence laFichePresence) {
		lesDonnees.set(firstRow, laFichePresence);
		fireTableRowsUpdated(firstRow, lastRow);
	}
	
	public void lireRecupMOD(ArrayList<FichePresence>nouvellesDonnees){
		lesDonnees=nouvellesDonnees;
		fireTableDataChanged();
	}

}
