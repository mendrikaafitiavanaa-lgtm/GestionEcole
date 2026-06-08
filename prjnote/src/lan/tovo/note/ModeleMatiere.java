package lan.tovo.note;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ModeleMatiere extends AbstractTableModel {
	
	Matiere instanceMatiere = new Matiere();
	
	private ArrayList<Matiere> lesDonnees = instanceMatiere.lireRecupCRUD();
	
	private final String [] lesTitres = {"Numéro", "Libellé", "Coéfficient","N°Enseignant", "Nom"};
	
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
		// TODO Auto-generated method stub
		switch (columnIndex) {
		case 0 :
			return lesDonnees.get(rowIndex).getNumMatiere();
		case 1 :
			return lesDonnees.get(rowIndex).getLibelleMatiere();
		case 2 :
			return lesDonnees.get(rowIndex).getCoefficient();	
		case 3 :
			return lesDonnees.get(rowIndex).getNumEnseignant().getNumEnseignant();
		case 4 :
			return lesDonnees.get(rowIndex).getNumEnseignant().getNomEnseignant();
		default :
			return null;
		}		
		
	}
	
	public int getNumLigne(int vCode) {
		String code = null;
		int numLigne = 0;
		lesDonnees.equals(vCode);
		
		for(int i=0 ; i<lesDonnees.size() ; i++){
			code=lesDonnees.get(i).getNumMatiere();
			if(code.equals(vCode)){
				numLigne=i;
			}
		}
		return numLigne;
		
	}
	
	
	
	public void creerMOD( Matiere laMatiere) {
		lesDonnees.add(laMatiere);
		fireTableRowsInserted(lesDonnees.size()-1,lesDonnees.size()-1);
	}
	
	public void supprimerMOD(int rowIndex) {
		lesDonnees.remove(rowIndex);
		fireTableRowsDeleted(rowIndex,rowIndex);
	}
	
	public void modifierMOD(int firstRow, int lastRow,  Matiere laMatiere) {
		lesDonnees.set(firstRow, laMatiere);
		fireTableRowsUpdated(firstRow, lastRow);
	}
	
	public void lireRecupMOD(ArrayList<Matiere>nouvellesDonnees){
		lesDonnees=nouvellesDonnees;
		fireTableDataChanged();
	}
	

}
