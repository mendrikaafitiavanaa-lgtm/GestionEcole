package lan.tovo.note;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FenClasse extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextField txtnumClasse;
	private JTextField txtlibelleClasse;
	private JButton btnNouveau;
	private JButton btnModifier;
	private JButton btnSupprimer;
	private JButton btnRechercher;
	private JButton btnQuitter;
	private JButton btnValider;
	private JButton btnImprimer;
	private JButton btnAnnuler;
	
	private ModeleClasse leModeleClasse = new ModeleClasse();
	private int numLigne;
	private static String actionDemandee;
	private JScrollPane scrollPane;
	private JTable table;
	private JTextField txtRechercher;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenClasse frame = new FenClasse();
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
	public FenClasse() {
		setResizable(false);
		setTitle("Classe");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblNewLabel());
		contentPane.add(getLblNewLabel_1());
		contentPane.add(getTxtnumClasse());
		contentPane.add(getTxtlibelleClasse());
		contentPane.add(getBtnNouveau());
		contentPane.add(getBtnModifier());
		contentPane.add(getBtnSupprimer());
		contentPane.add(getBtnRechercher());
		contentPane.add(getBtnQuitter());
		contentPane.add(getBtnValider());
		contentPane.add(getBtnImprimer());
		contentPane.add(getBtnAnnuler());
		contentPane.add(getScrollPane_1());
		contentPane.add(getTxtRechercher());
		
		//permet de masquer les composant
		masquerComposant();
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Num\u00E9ro");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNewLabel.setBounds(32, 11, 99, 24);
		}
		return lblNewLabel;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("Libell\u00E9");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNewLabel_1.setBounds(165, 16, 99, 14);
		}
		return lblNewLabel_1;
	}
	private JTextField getTxtnumClasse() {
		if (txtnumClasse == null) {
			txtnumClasse = new JTextField();
			txtnumClasse.setBounds(32, 31, 86, 20);
			txtnumClasse.setColumns(10);
		}
		return txtnumClasse;
	}
	private JTextField getTxtlibelleClasse() {
		if (txtlibelleClasse == null) {
			txtlibelleClasse = new JTextField();
			txtlibelleClasse.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent arg0) {
					afficherBtn();
				}
			});
			txtlibelleClasse.setBounds(165, 31, 259, 20);
			txtlibelleClasse.setColumns(10);
		}
		return txtlibelleClasse;
	}
	private JButton getBtnNouveau() {
		if (btnNouveau == null) {
			btnNouveau = new JButton("Nouveau");
			btnNouveau.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent arg0) {
					btnNouveau.setIcon(new ImageIcon(FenClasse.class.getResource("/images/icons/carre-plus1.png")));
				}
				@Override
				public void mouseExited(MouseEvent arg0) {
					btnNouveau.setIcon(new ImageIcon(FenClasse.class.getResource("/images/icons/carre-plus.png")));
				}
			});
			btnNouveau.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					afficherTxt();
					txtnumClasse.requestFocus();
					actionDemandee = "Ajout";
					
				}
			});
			btnNouveau.setHorizontalAlignment(SwingConstants.LEFT);
			btnNouveau.setIcon(new ImageIcon(FenClasse.class.getResource("/images/icons/carre-plus.png")));
			btnNouveau.setBounds(526, 16, 140, 33);
		}
		return btnNouveau;
	}
	private JButton getBtnModifier() {
		if (btnModifier == null) {
			btnModifier = new JButton("Modifier");
			btnModifier.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					txtlibelleClasse.setEditable(true);
					txtlibelleClasse.requestFocus();
					actionDemandee="Modifier";
				}
			});
			btnModifier.setIcon(new ImageIcon(FenClasse.class.getResource("/images/icons/carre-de-stylo.png")));
			btnModifier.setHorizontalAlignment(SwingConstants.LEFT);
			btnModifier.setBounds(526, 74, 140, 33);
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
								+ " le District?"
								+ "\nNuméro : " + table.getValueAt(NumLigne, 0)
								, "SUPRESSION"
								, JOptionPane.YES_NO_OPTION);
						
						if ( choix == 0) {
							String vNum;
							vNum = String.valueOf(table.getValueAt(NumLigne, 0));
							
							Classe leParticipant = new Classe(vNum);
							
							leParticipant.supprimerCRUD(vNum);
							
							leModeleClasse.supprimerMOD(NumLigne);
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
			btnSupprimer.setIcon(new ImageIcon(FenClasse.class.getResource("/images/icons/corbeille-xmark (1).png")));
			btnSupprimer.setHorizontalAlignment(SwingConstants.LEFT);
			btnSupprimer.setBounds(526, 125, 140, 33);
		}
		return btnSupprimer;
	}
	private JButton getBtnRechercher() {
		if (btnRechercher == null) {
			btnRechercher = new JButton("Rechercher");
			btnRechercher.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Classe ledistrict = new Classe();
					ArrayList<Classe> nouvelleliste = ledistrict.chercherCRUD(txtRechercher.getText());
					leModeleClasse.lireRecupMOD(nouvelleliste);
				}
			});
			btnRechercher.setIcon(new ImageIcon(FenClasse.class.getResource("/images/icons/chercher (1).png")));
			btnRechercher.setHorizontalAlignment(SwingConstants.LEFT);
			btnRechercher.setBounds(526, 183, 140, 33);
		}
		return btnRechercher;
	}
	private JButton getBtnQuitter() {
		if (btnQuitter == null) {
			btnQuitter = new JButton("Quitter");
			btnQuitter.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
			btnQuitter.setIcon(new ImageIcon(FenClasse.class.getResource("/images/icons/croix-cercle (2).png")));
			btnQuitter.setHorizontalAlignment(SwingConstants.LEFT);
			btnQuitter.setBounds(526, 277, 140, 33);
		}
		return btnQuitter;
	}
	private JButton getBtnValider() {
		if (btnValider == null) {
			btnValider = new JButton("Valider");
			btnValider.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Classe classe = new Classe(
							txtnumClasse.getText(),
							txtlibelleClasse.getText());
					if (actionDemandee.equals("Ajout")){
						if ( classe.creerCRUD()) {
							leModeleClasse.creerMOD(classe);
							masquerComposant();
							txtnumClasse.setText("");
							txtlibelleClasse.setText("");
						}
					}
					
					if (actionDemandee.equals("Modifier")){
						if ( classe.modifierCRUD()) {
							leModeleClasse.modifierMOD(numLigne, numLigne,classe);
							masquerComposant();
							txtnumClasse.setText("");
							txtlibelleClasse.setText("");
							
						}
					}
				}
			});
			btnValider.setBounds(98, 287, 89, 23);
		}
		return btnValider;
	}
	private JButton getBtnImprimer() {
		if (btnImprimer == null) {
			btnImprimer = new JButton("Imprimer");
			btnImprimer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JasperMySql_ParametresClasse.apercu("tableclasse.jrxml");
				}
			});
			btnImprimer.setIcon(new ImageIcon(FenClasse.class.getResource("/images/icons/impression (1).png")));
			btnImprimer.setHorizontalAlignment(SwingConstants.LEFT);
			btnImprimer.setBounds(526, 227, 140, 33);
		}
		return btnImprimer;
	}
	private JButton getBtnAnnuler() {
		if (btnAnnuler == null) {
			btnAnnuler = new JButton("Annuler");
			btnAnnuler.setBounds(238, 287, 89, 23);
		}
		return btnAnnuler;
	}
	
	private void masquerComposant() {
		btnValider.setVisible(false);
		btnAnnuler.setVisible(false);
		txtnumClasse.setEditable(false);
		txtlibelleClasse.setEditable(false);
		
	}
	
	private void afficherTxt() {
		txtnumClasse.setEditable(true);
		txtlibelleClasse.setEditable(true);
		
	}
	
	private void afficherBtn() {
		btnValider.setVisible(true);
		btnAnnuler.setVisible(true);
		
	}
	private JScrollPane getScrollPane_1() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(42, 94, 382, 166);
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}
	private JTable getTable() {
		if (table == null) {
			//table = new JTable();
			table = new JTable(leModeleClasse);
			
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					numLigne=table.getSelectedRow();
					afficher(numLigne);
				}
			});
			
			table.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent evt) {
					numLigne = table.getSelectedRow();
					
					if( evt.getKeyCode() == evt.VK_DOWN) {
						numLigne++;
						if(numLigne > leModeleClasse.getRowCount()){
							numLigne = leModeleClasse.getRowCount()-1;
							
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
			
			
			
		}
		return table;
	}
	
	private void afficher(int numLigne){
		txtnumClasse.setText((String) leModeleClasse.getValueAt(numLigne, 0));
		txtlibelleClasse.setText((String) leModeleClasse.getValueAt(numLigne, 1));
		
	}
	private JTextField getTxtRechercher() {
		if (txtRechercher == null) {
			txtRechercher = new JTextField();
			txtRechercher.setBounds(32, 63, 140, 20);
			txtRechercher.setColumns(10);
		}
		return txtRechercher;
	}
}
