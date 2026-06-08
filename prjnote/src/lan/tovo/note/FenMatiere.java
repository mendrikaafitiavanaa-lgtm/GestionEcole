package lan.tovo.note;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FenMatiere extends JFrame {

	private JPanel contentPane;
	private JLabel lblNumro;
	private JLabel lblLibell;
	private JLabel lblCofficient;
	private JTextField txtnumMatiere;
	private JTextField txtlibelleMatiere;
	private JTextField txtcoefficient;
	private JLabel lblNEnseigant;
	private JLabel lblNewLabel;
	private JComboBox cmbnumEnseignant;
	private JTextField txtnomEnseignant;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnValider;
	private JButton btnAnnuler;
	private JButton btnNouveau;
	private JButton btnModifier;
	private JButton btnSupprimer;
	private JButton btnRechercher;
	private JButton btnImprimer;
	private JButton btnQuitter;
	
	private ModeleMatiere leModeleMatiere = new ModeleMatiere();
	private int numLigne;
	private static String actionDemandee;
	
	private ArrayList<Enseignant> lesEnseignants;
	private JTextField txtRechercher;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenMatiere frame = new FenMatiere();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					
					//int num = Matiere.recupNumEnseignant();
					//JOptionPane.showMessageDialog(null, num);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FenMatiere() {
		setResizable(false);
		setTitle("Enseignant");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblNumro());
		contentPane.add(getLblLibell());
		contentPane.add(getLblCofficient());
		contentPane.add(getTxtnumMatiere());
		contentPane.add(getTxtlibelleMatiere());
		contentPane.add(getTxtcoefficient());
		contentPane.add(getLblNEnseigant());
		contentPane.add(getLblNewLabel());
		contentPane.add(getCmbnumEnseignant());
		contentPane.add(getTxtnomEnseignant());
		contentPane.add(getScrollPane());
		contentPane.add(getBtnValider());
		contentPane.add(getBtnAnnuler());
		contentPane.add(getBtnNouveau());
		contentPane.add(getBtnModifier());
		contentPane.add(getBtnSupprimer());
		contentPane.add(getBtnRechercher());
		contentPane.add(getBtnImprimer());
		contentPane.add(getBtnQuitter());
		contentPane.add(getTxtRechercher());
		
		//permet de masquer les composant
		masquerComposant();
	}
	private JLabel getLblNumro() {
		if (lblNumro == null) {
			lblNumro = new JLabel("Num\u00E9ro");
			lblNumro.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNumro.setBounds(22, 13, 89, 14);
		}
		return lblNumro;
	}
	private JLabel getLblLibell() {
		if (lblLibell == null) {
			lblLibell = new JLabel("Libell\u00E9");
			lblLibell.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblLibell.setBounds(128, 13, 73, 14);
		}
		return lblLibell;
	}
	private JLabel getLblCofficient() {
		if (lblCofficient == null) {
			lblCofficient = new JLabel("Co\u00E9fficient");
			lblCofficient.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblCofficient.setBounds(299, 13, 78, 14);
		}
		return lblCofficient;
	}
	private JTextField getTxtnumMatiere() {
		if (txtnumMatiere == null) {
			txtnumMatiere = new JTextField();
			txtnumMatiere.setBounds(22, 38, 86, 20);
			txtnumMatiere.setColumns(10);
		}
		return txtnumMatiere;
	}
	private JTextField getTxtlibelleMatiere() {
		if (txtlibelleMatiere == null) {
			txtlibelleMatiere = new JTextField();
			txtlibelleMatiere.setBounds(128, 38, 163, 20);
			txtlibelleMatiere.setColumns(10);
		}
		return txtlibelleMatiere;
	}
	private JTextField getTxtcoefficient() {
		if (txtcoefficient == null) {
			txtcoefficient = new JTextField();
			txtcoefficient.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent arg0) {
					afficherBtn();
				}
			});
			txtcoefficient.setBounds(299, 38, 86, 20);
			txtcoefficient.setColumns(10);
		}
		return txtcoefficient;
	}
	private JLabel getLblNEnseigant() {
		if (lblNEnseigant == null) {
			lblNEnseigant = new JLabel("N\u00B0 Enseignant");
			lblNEnseigant.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNEnseigant.setBounds(22, 69, 106, 20);
		}
		return lblNEnseigant;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Nom");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNewLabel.setBounds(128, 69, 163, 20);
		}
		return lblNewLabel;
	}
	private JComboBox getCmbnumEnseignant() {
		if (cmbnumEnseignant == null) {
			cmbnumEnseignant = new JComboBox();
			cmbnumEnseignant.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
					Enseignant enseignant = new Enseignant();
					String numEnseignant = (String) cmbnumEnseignant.getSelectedItem();
					ArrayList<Enseignant> nomEnseignant = enseignant.lireRecupLigne(numEnseignant);
					for(Enseignant elem:nomEnseignant)
						txtnomEnseignant.setText(elem.getNomEnseignant());
				}
				
				
			});
			Enseignant enseignant = new Enseignant();
			lesEnseignants = enseignant.lireRecupCRUD();
			cmbnumEnseignant.addItem("");
			for(Enseignant elem:lesEnseignants){
				cmbnumEnseignant.addItem(elem.getNumEnseignant());
			}
			cmbnumEnseignant.setBounds(22, 89, 99, 20);
		}
		return cmbnumEnseignant;
	}
	private JTextField getTxtnomEnseignant() {
		if (txtnomEnseignant == null) {
			txtnomEnseignant = new JTextField();
			txtnomEnseignant.setBounds(128, 89, 257, 20);
			txtnomEnseignant.setColumns(10);
		}
		return txtnomEnseignant;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(22, 153, 488, 120);
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}
	private JTable getTable() {
		if (table == null) {
			//table = new JTable();
			table = new JTable(leModeleMatiere);
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					numLigne=table.getSelectedRow();
					afficher(numLigne);
				}
			});
		}
		return table;
	}
	private JButton getBtnValider() {
		if (btnValider == null) {
			btnValider = new JButton("Valider");
			btnValider.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String inenseignant = (String) cmbnumEnseignant.getSelectedItem();					
					Enseignant enseignant = new Enseignant(inenseignant);
					
					Matiere matiere = new Matiere(txtnumMatiere.getText(),
							txtlibelleMatiere.getText(), txtcoefficient.getText(),
							enseignant);
							
					if (actionDemandee.equals("Ajout")){
						if ( matiere.creerCRUD(txtnumMatiere.getText(), txtlibelleMatiere.getText(),
								txtcoefficient.getText(),enseignant.getNumEnseignant())) {
							leModeleMatiere.creerMOD(matiere);
							masquerComposant();
							txtnumMatiere.setText("");
							txtlibelleMatiere.setText("");
							txtcoefficient.setText("");
							txtnomEnseignant.setText("");
						}
					}
					
					if (actionDemandee.equals("Modifier")){
						if ( matiere.modifierCRUD(txtnumMatiere.getText(), txtlibelleMatiere.getText(),
								txtcoefficient.getText(),enseignant.getNumEnseignant())) {
							leModeleMatiere.modifierMOD(numLigne, numLigne,matiere);
							masquerComposant();
							txtnumMatiere.setText("");
							txtlibelleMatiere.setText("");
							txtcoefficient.setText("");
							txtnomEnseignant.setText("");
							
						}
					}
				}
			});
			btnValider.setBounds(63, 287, 89, 23);
		}
		return btnValider;
	}
	private JButton getBtnAnnuler() {
		if (btnAnnuler == null) {
			btnAnnuler = new JButton("Annuler");
			btnAnnuler.setBounds(226, 287, 89, 23);
		}
		return btnAnnuler;
	}
	private JButton getBtnNouveau() {
		if (btnNouveau == null) {
			btnNouveau = new JButton("Nouveau");
			btnNouveau.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					afficherTxt();
					//txtnumMatiere.requestFocus();
					actionDemandee = "Ajout";
					Integer num = Matiere.recupNumEnseignant();
					String numatiere= num.toString();
					txtnumMatiere.setText(numatiere);
					txtnumMatiere.setEditable(false);
					txtlibelleMatiere.requestFocus();
				}
			});
			btnNouveau.setBounds(552, 11, 116, 23);
		}
		return btnNouveau;
	}
	private JButton getBtnModifier() {
		if (btnModifier == null) {
			btnModifier = new JButton("Modifier");
			btnModifier.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					txtlibelleMatiere.setEditable(true);
					txtcoefficient.setEditable(true);
					txtlibelleMatiere.requestFocus();
					actionDemandee="Modifier";
				}
			});
			btnModifier.setBounds(552, 54, 116, 23);
		}
		return btnModifier;
	}
	private JButton getBtnSupprimer() {
		if (btnSupprimer == null) {
			btnSupprimer = new JButton("Supprimer");
			btnSupprimer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int NumLigne = -1;
					NumLigne = table.getSelectedRow();
					
					if ( NumLigne != -1 ) {
						int choix = JOptionPane.showConfirmDialog(null,"Voulez -vous Supprimez"
								+ " une Matiere?"
								+ "\nNuméro : " + table.getValueAt(NumLigne, 0)
								, "SUPRESSION"
								, JOptionPane.YES_NO_OPTION);
						
						if ( choix == 0) {
							String vNum;
							vNum = String.valueOf(table.getValueAt(NumLigne, 0));
							
							Matiere leMatiere = new Matiere(vNum);
							
							leMatiere.supprimerCRUD(vNum);
							
							leModeleMatiere.supprimerMOD(NumLigne);
						}
					}
					
					if ( NumLigne == -1) {
						JOptionPane.showMessageDialog
						(null , "Sélectionnez une ligne avant.",
								"Suppression",
								JOptionPane.INFORMATION_MESSAGE);
					}
					
				}
			});
			btnSupprimer.setBounds(552, 100, 116, 23);
		}
		return btnSupprimer;
	}
	private JButton getBtnRechercher() {
		if (btnRechercher == null) {
			btnRechercher = new JButton("Rechercher");
			btnRechercher.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Matiere lematiere = new Matiere();
					ArrayList<Matiere> nouvelleliste = lematiere.chercherCRUD(txtRechercher.getText());
					leModeleMatiere.lireRecupMOD(nouvelleliste);
					
				}
			});
			btnRechercher.setBounds(552, 148, 116, 23);
		}
		return btnRechercher;
	}
	private JButton getBtnImprimer() {
		if (btnImprimer == null) {
			btnImprimer = new JButton("Imprimer");
			btnImprimer.setBounds(552, 192, 116, 23);
		}
		return btnImprimer;
	}
	private JButton getBtnQuitter() {
		if (btnQuitter == null) {
			btnQuitter = new JButton("Quitter");
			btnQuitter.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
			btnQuitter.setBounds(552, 287, 116, 23);
		}
		return btnQuitter;
	}
	
	private void masquerComposant() {
		btnValider.setVisible(false);
		btnAnnuler.setVisible(false);
		txtnumMatiere.setEditable(false);
		txtlibelleMatiere.setEditable(false);
		txtcoefficient.setEditable(false);
		txtnomEnseignant.setEditable(false);
	}
	
	private void afficherTxt() {
		txtnumMatiere.setEditable(true);
		txtlibelleMatiere.setEditable(true);
		txtcoefficient.setEditable(true);
		
	}
	
	private void afficherBtn() {
		btnValider.setVisible(true);
		btnAnnuler.setVisible(true);
		
	}
	private void afficher(int numLigne){
		txtnumMatiere.setText((String) leModeleMatiere.getValueAt(numLigne, 0));
		txtlibelleMatiere.setText((String) leModeleMatiere.getValueAt(numLigne, 1));
		txtcoefficient.setText((String) leModeleMatiere.getValueAt(numLigne, 2));
		cmbnumEnseignant.setSelectedItem(leModeleMatiere.getValueAt(numLigne, 3));
		txtnomEnseignant.setText((String) leModeleMatiere.getValueAt(numLigne, 4));
		
		
	}
	private JTextField getTxtRechercher() {
		if (txtRechercher == null) {
			txtRechercher = new JTextField();
			txtRechercher.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					Matiere lematiere = new Matiere();
					ArrayList<Matiere> nouvelleliste = lematiere.chercherCRUD(txtRechercher.getText());
					leModeleMatiere.lireRecupMOD(nouvelleliste);
				}
			});
			txtRechercher.setFont(new Font("Tahoma", Font.ITALIC, 11));
			txtRechercher.setBounds(22, 120, 363, 20);
			txtRechercher.setColumns(10);
		}
		return txtRechercher;
	}
}
