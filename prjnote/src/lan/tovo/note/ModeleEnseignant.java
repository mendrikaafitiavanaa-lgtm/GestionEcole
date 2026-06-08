package lan.tovo.note;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ModeleEnseignant extends AbstractTableModel{
Enseignant instanceClasse = new Enseignant();
	
	private ArrayList<Enseignant> lesDonnees = instanceClasse.lireRecupCRUD();
	private final String [] lesTitres = {"Numéro", "Nom", "Prénom"};
	
	
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
			return lesDonnees.get(rowIndex).getNumEnseignant();
		case 1 :
			return lesDonnees.get(rowIndex).getNomEnseignant();
		case 2 :
			return lesDonnees.get(rowIndex).getPrenomEnseignant();		
		
		default :
			return null;
		}		
		
	}
	
	public int getNumLigne(int vCode) {
		String code = null;
		int numLigne = 0;
		lesDonnees.equals(vCode);
		
		for(int i=0 ; i<lesDonnees.size() ; i++){
			code=lesDonnees.get(i).getNumEnseignant();
			if(code.equals(vCode)){
				numLigne=i;
			}
		}
		return numLigne;
		
	}
	
	
	
	
	public void creerMOD( Enseignant laEnseignant) {
		lesDonnees.add(laEnseignant);
		fireTableRowsInserted(lesDonnees.size()-1,lesDonnees.size()-1);
	}
	
	public void supprimerMOD(int rowIndex) {
		lesDonnees.remove(rowIndex);
		fireTableRowsDeleted(rowIndex,rowIndex);
	}
	
	public void modifierMOD(int firstRow, int lastRow, Enseignant laEnseignant) {
		lesDonnees.set(firstRow, laEnseignant);
		fireTableRowsUpdated(firstRow, lastRow);
	}
	
	public void lireRecupMOD(ArrayList<Enseignant>nouvellesDonnees){
		lesDonnees=nouvellesDonnees;
		fireTableDataChanged();
	}

}
