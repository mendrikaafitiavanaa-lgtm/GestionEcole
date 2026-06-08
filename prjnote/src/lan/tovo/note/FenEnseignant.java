package lan.tovo.note;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FenEnseignant extends JFrame {

	private JPanel contentPane;
	private JButton btnNouveau;
	private JButton btnModifier;
	private JButton btnSupprimer;
	private JButton btnRechercher;
	private JButton btnImprimer;
	private JButton btnQuitter;
	private JButton btnValider;
	private JButton btnAnnuler;
	private JLabel lblNumro;
	private JLabel lblNom;
	private JLabel lblPrnom;
	private JTextField txtnumEnseignant;
	private JTextField txtnomEnseignant;
	private JTextField txtprenomEnseignant;
	private JScrollPane scrollPane;
	private JTable table;
	
	private ModeleEnseignant leModeleEnseignant = new ModeleEnseignant();
	private int numLigne;
	private static String actionDemandee;
	private JTextField txtRechercher;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenEnseignant frame = new FenEnseignant();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FenEnseignant() {
		setResizable(false);
		setTitle("Enseignant");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnNouveau());
		contentPane.add(getBtnModifier());
		contentPane.add(getBtnSupprimer());
		contentPane.add(getBtnRechercher());
		contentPane.add(getBtnImprimer());
		contentPane.add(getBtnQuitter());
		contentPane.add(getBtnValider());
		contentPane.add(getBtnAnnuler());
		contentPane.add(getLblNumro());
		contentPane.add(getLblNom());
		contentPane.add(getLblPrnom());
		contentPane.add(getTxtnumEnseignant());
		contentPane.add(getTxtnomEnseignant());
		contentPane.add(getTxtprenomEnseignant());
		contentPane.add(getScrollPane());
		contentPane.add(getTxtRechercher());
		
		//permet de masquer les composant
		masquerComposant();
	}
	private JButton getBtnNouveau() {
		if (btnNouveau == null) {
			btnNouveau = new JButton("Nouveau");
			btnNouveau.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					afficherTxt();
					txtnumEnseignant.requestFocus();
					actionDemandee = "Ajout";
				}
			});
			btnNouveau.setBounds(564, 11, 107, 23);
		}
		return btnNouveau;
	}
	private JButton getBtnModifier() {
		if (btnModifier == null) {
			btnModifier = new JButton("Modifier");
			btnModifier.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					txtnomEnseignant.setEditable(true);
					txtprenomEnseignant.setEditable(true);
					txtprenomEnseignant.requestFocus();
					actionDemandee="Modifier";
				}
			});
			btnModifier.setBounds(564, 59, 107, 23);
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
								+ " le Enseignant?"
								+ "\nNuméro : " + table.getValueAt(NumLigne, 0)
								, "SUPRESSION"
								, JOptionPane.YES_NO_OPTION);
						
						if ( choix == 0) {
							String vNum;
							vNum = String.valueOf(table.getValueAt(NumLigne, 0));
							
							Enseignant leEnseignant = new Enseignant(vNum);
							
							leEnseignant.supprimerCRUD(vNum);
							
							leModeleEnseignant.supprimerMOD(NumLigne);
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
			btnSupprimer.setBounds(564, 106, 107, 23);
		}
		return btnSupprimer;
	}
	private JButton getBtnRechercher() {
		if (btnRechercher == null) {
			btnRechercher = new JButton("Rechercher");
			btnRechercher.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Enseignant ledistrict = new Enseignant();
					ArrayList<Enseignant> nouvelleliste = ledistrict.chercherCRUD(txtRechercher.getText());
					leModeleEnseignant.lireRecupMOD(nouvelleliste);
					
					
				}
			});
			btnRechercher.setBounds(564, 157, 107, 23);
		}
		return btnRechercher;
	}
	private JButton getBtnImprimer() {
		if (btnImprimer == null) {
			btnImprimer = new JButton("Imprimer");
			btnImprimer.setBounds(564, 204, 107, 23);
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
			btnQuitter.setBounds(564, 287, 107, 23);
		}
		return btnQuitter;
	}
	private JButton getBtnValider() {
		if (btnValider == null) {
			btnValider = new JButton("Valider");
			btnValider.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Enseignant enseignant = new Enseignant(
							txtnumEnseignant.getText(),
							txtnomEnseignant.getText(),
							txtprenomEnseignant.getText());
					
					if (actionDemandee.equals("Ajout")){
						if ( enseignant.creerCRUD()) {
							leModeleEnseignant.creerMOD(enseignant);
							masquerComposant();
							txtnumEnseignant.setText("");
							txtnomEnseignant.setText("");
							txtprenomEnseignant.setText("");
						}
					}
					
					if (actionDemandee.equals("Modifier")){
						if ( enseignant.modifierCRUD()) {
							leModeleEnseignant.modifierMOD(numLigne, numLigne,enseignant);
							masquerComposant();
							txtnumEnseignant.setText("");
							txtnomEnseignant.setText("");
							txtprenomEnseignant.setText("");
							
						}
					}
				}
			});
			btnValider.setBounds(106, 287, 89, 23);
		}
		return btnValider;
	}
	private JButton getBtnAnnuler() {
		if (btnAnnuler == null) {
			btnAnnuler = new JButton("Annuler");
			btnAnnuler.setBounds(268, 287, 89, 23);
		}
		return btnAnnuler;
	}
	private JLabel getLblNumro() {
		if (lblNumro == null) {
			lblNumro = new JLabel("Num\u00E9ro");
			lblNumro.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNumro.setBounds(22, 15, 74, 19);
		}
		return lblNumro;
	}
	private JLabel getLblNom() {
		if (lblNom == null) {
			lblNom = new JLabel("Nom");
			lblNom.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNom.setBounds(165, 15, 137, 19);
		}
		return lblNom;
	}
	private JLabel getLblPrnom() {
		if (lblPrnom == null) {
			lblPrnom = new JLabel("Pr\u00E9nom");
			lblPrnom.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblPrnom.setBounds(369, 19, 124, 14);
		}
		return lblPrnom;
	}
	private JTextField getTxtnumEnseignant() {
		if (txtnumEnseignant == null) {
			txtnumEnseignant = new JTextField();
			txtnumEnseignant.setBounds(22, 45, 86, 20);
			txtnumEnseignant.setColumns(10);
		}
		return txtnumEnseignant;
	}
	private JTextField getTxtnomEnseignant() {
		if (txtnomEnseignant == null) {
			txtnomEnseignant = new JTextField();
			txtnomEnseignant.setBounds(165, 45, 192, 20);
			txtnomEnseignant.setColumns(10);
		}
		return txtnomEnseignant;
	}
	private JTextField getTxtprenomEnseignant() {
		if (txtprenomEnseignant == null) {
			txtprenomEnseignant = new JTextField();
			txtprenomEnseignant.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent arg0) {
					btnValider.setVisible(true);
					btnAnnuler.setVisible(true);
				}
			});
			txtprenomEnseignant.setBounds(368, 45, 186, 20);
			txtprenomEnseignant.setColumns(10);
		}
		return txtprenomEnseignant;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(22, 100, 459, 176);
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}
	private JTable getTable() {
		if (table == null) {
			//table = new JTable();
			table = new JTable(leModeleEnseignant);
			table.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent evt) {
					numLigne = table.getSelectedRow();
					
					if( evt.getKeyCode() == evt.VK_DOWN) {
						numLigne++;
						if(numLigne > leModeleEnseignant.getRowCount()){
							numLigne = leModeleEnseignant.getRowCount()-1;
							
						}
						afficher(numLigne);
					}
					
					if( evt.getKeyCode() == evt.VK_UP){
						numLigne--;
						if(numLigne < 0){
							numLigne = 0;
						}
						afficher(numLigne);
					}
				}
			});
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
	
	
	private void masquerComposant() {
		btnValider.setVisible(false);
		btnAnnuler.setVisible(false);
		txtnumEnseignant.setEditable(false);
		txtnomEnseignant.setEditable(false);
		txtprenomEnseignant.setEditable(false);
		
	}
	
	private void afficherTxt() {
		txtnumEnseignant.setEditable(true);
		txtnomEnseignant.setEditable(true);
		txtprenomEnseignant.setEditable(true);
		
	}
	
	private void afficherBtn() {
		btnValider.setVisible(true);
		btnAnnuler.setVisible(true);
		
	}
	private void afficher(int numLigne){
		txtnumEnseignant.setText((String) leModeleEnseignant.getValueAt(numLigne, 0));
		txtnomEnseignant.setText((String) leModeleEnseignant.getValueAt(numLigne, 1));
		txtprenomEnseignant.setText((String) leModeleEnseignant.getValueAt(numLigne, 2));
	}
	private JTextField getTxtRechercher() {
		if (txtRechercher == null) {
			txtRechercher = new JTextField();
			txtRechercher.setBounds(22, 69, 335, 20);
			txtRechercher.setColumns(10);
		}
		return txtRechercher;
	}
}
