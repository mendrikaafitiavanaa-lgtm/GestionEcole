package lan.tovo.note;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class FenEleve extends JFrame {

	private JPanel contentPane;
	private JButton btnNouveau;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenEleve frame = new FenEleve();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FenEleve() {
		setTitle("Eleve\r\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnNouveau());
	}
	private JButton getBtnNouveau() {
		if (btnNouveau == null) {
			btnNouveau = new JButton("Nouveau");
			btnNouveau.setBounds(574, 11, 89, 23);
		}
		return btnNouveau;
	}
}
