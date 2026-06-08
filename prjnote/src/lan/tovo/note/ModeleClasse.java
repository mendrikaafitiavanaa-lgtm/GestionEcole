package lan.tovo.note;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ModeleClasse extends AbstractTableModel {
	
	Classe instanceClasse = new Classe();
	
	private ArrayList<Classe> lesDonnees = instanceClasse.lireRecupCRUD();
	private final String [] lesTitres = {"Numéro", "Libellé"};
	
	
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
			return lesDonnees.get(rowIndex).getNumClasse();
		case 1 :
			return lesDonnees.get(rowIndex).getLibelleClasse();
		
		default :
			return null;
		}		
		
	}
	
	public int getNumLigne(int vCode) {
		String code = null;
		int numLigne = 0;
		lesDonnees.equals(vCode);
		
		for(int i=0 ; i<lesDonnees.size() ; i++){
			code=lesDonnees.get(i).getNumClasse();
			if(code.equals(vCode)){
				numLigne=i;
			}
		}
		return numLigne;
		
	}
	
	
	
	
	public void creerMOD( Classe laClasse) {
		lesDonnees.add(laClasse);
		fireTableRowsInserted(lesDonnees.size()-1,lesDonnees.size()-1);
	}
	
	public void supprimerMOD(int rowIndex) {
		lesDonnees.remove(rowIndex);
		fireTableRowsDeleted(rowIndex,rowIndex);
	}
	
	public void modifierMOD(int firstRow, int lastRow, Classe laClasse) {
		lesDonnees.set(firstRow, laClasse);
		fireTableRowsUpdated(firstRow, lastRow);
	}
	
	public void lireRecupMOD(ArrayList<Classe>nouvellesDonnees){
		lesDonnees=nouvellesDonnees;
		fireTableDataChanged();
	}

}
