package lan.tovo.note;

import java.io.File;
import java.sql.Connection;
import java.util.Collection;
import java.util.HashMap;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;;


public class JasperMySql_ParametresClasse {
	
	private static Connection laConnexion = Connexion.Konnexion();
	
	private static JasperDesign design=null;
	private static JasperReport report=null;
	private static JasperPrint print=null;
	
	private String numClasse;
	private String libelleClasse;
	
	
	//Etapes 1 à 3 du processus Jasper
	
	public String getNumClasse() {
		return numClasse;
	}

	public void setNumClasse(String numClasse) {
		this.numClasse = numClasse;
	}

	public String getLibelleClasse() {
		return libelleClasse;
	}

	public void setLibelleClasse(String libelleClasse) {
		this.libelleClasse = libelleClasse;
	}

		public static void chargerEtcompiler(String rapport){
			try{
			//Etape 1
				design=JRXmlLoader.load(Systeme.getRepertoireCourant()+Systeme.getSeparateur()+
						"jasper"+Systeme.getSeparateur()+rapport);
			//Etape 2
				report=JasperCompileManager.compileReport(design);
			//Etape 3
				HashMap<String, Object> mesParametres=new HashMap<String, Object>();
				
				mesParametres.put("numrespo", new String("%"));
				
				
				print=JasperFillManager.fillReport(report, mesParametres, laConnexion);
				
			} catch(JRException e){
				JOptionPane.showMessageDialog(null, "La compilation du rapport a échoué : \n"+
												e.getMessage()+"\nVeuillez contacter votre administrateur",
												"Erreur",JOptionPane.ERROR_MESSAGE);
			}
		}
		
		

		//apercu avant impression
		public static void apercu(String rapport){
			chargerEtcompiler(rapport);
			try{
				JasperViewer.viewReport(print, false);
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "Erreur lors de l'aperçu : \n"+
						e.getMessage()+"\nVeuillez contacter votre administrateur",
						"Erreur",JOptionPane.ERROR_MESSAGE);
			}
		}
		
		//Impression du rapport
		public static void imprimer(String rapport){
			chargerEtcompiler(rapport);
			try{
				JasperPrintManager.printReport(print, true);
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "L'impression a échoué : \n"+
						e.getMessage()+"\nVeuillez contacter votre administrateur",
						"Erreur",JOptionPane.ERROR_MESSAGE);
			}
		}
		
		//Export en PDF
		public static void exporterPDF(String rapport){
			JFileChooser save= new JFileChooser();
			save.setSelectedFile(new File("Fichier.pdf"));
			int retour=save.showSaveDialog(save);
			if(retour==JFileChooser.APPROVE_OPTION){
				try{
					chargerEtcompiler(rapport);
					JasperExportManager.exportReportToPdfFile(print, save.getSelectedFile().getAbsolutePath());
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, "L'export au format PDF a échoué : \n"+
							e.getMessage()+"\nVeuillez contacter votre administrateur",
							"Erreur",JOptionPane.ERROR_MESSAGE);
				}
			}
		}

}
