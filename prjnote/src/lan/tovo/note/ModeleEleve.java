package lan.tovo.note;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;


public class ModeleEleve extends AbstractTableModel{

Eleve instanceEleve = new Eleve();
	
	private ArrayList<Eleve> lesDonnees = instanceEleve.lireRecupCRUD();
	private final String [] lesTitres = {"NuméroEleve", "numero","nomEleve","PrenomEleve","NumClasse","LibelleClase"};
	
	Classe instanceClasse = new Classe();
	String numclasse=instanceClasse.getNumClasse();
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
			return lesDonnees.get(rowIndex).getNumEleve();
		case 1 :
			return lesDonnees.get(rowIndex).getNumero();
		case 2:
			return lesDonnees.get(rowIndex).getNomEleve();
		case 3 :
			return lesDonnees.get(rowIndex).getPrenomEleve();
		case 4 :
			return lesDonnees.get(rowIndex).getNumClasse().getNumClasse();
		case 5 :
			return lesDonnees.get(rowIndex).getNumClasse().getLibelleClasse();
		
		default :
			return null;
		}		
		
	}
	
	public int getNumLigne(int vCode) {
		String code = null;
		int numLigne = 0;
		lesDonnees.equals(vCode);
		
		for(int i=0 ; i<lesDonnees.size() ; i++){
			code=lesDonnees.get(i).getNumEleve();
			if(code.equals(vCode)){
				numLigne=i;
			}
		}
		return numLigne;
		
	}
	
	
	public void creerMOD( Eleve laEleve) {
		lesDonnees.add(laEleve);
		fireTableRowsInserted(lesDonnees.size()-1,lesDonnees.size()-1);
	}
	
	public void supprimerMOD(int rowIndex) {
		lesDonnees.remove(rowIndex);
		fireTableRowsDeleted(rowIndex,rowIndex);
	}
	
	public void modifierMOD(int firstRow, int lastRow, Eleve laEleve) {
		lesDonnees.set(firstRow, laEleve);
		fireTableRowsUpdated(firstRow, lastRow);
	}
	
	public void lireRecupMOD(ArrayList<Eleve>nouvellesDonnees){
		lesDonnees=nouvellesDonnees;
		fireTableDataChanged();
	}
}
