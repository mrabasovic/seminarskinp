package forms;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;

import javax.swing.JComboBox;
import domain.*;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GlavnaForma extends JFrame {

	private JPanel contentPane;
	long aranzmanID = 0;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GlavnaForma frame = new GlavnaForma();
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
	public GlavnaForma() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox cmbHoteli = new JComboBox();
		cmbHoteli.setBounds(29, 20, 145, 27);
		contentPane.add(cmbHoteli);
		
		JButton btnDodajVodica = new JButton("New button");
		btnDodajVodica.setText("Dodaj vodica");
		btnDodajVodica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// ovde je kad se klikne na dodajVodica
				DodajVodicaForma dvf = new DodajVodicaForma();
				dvf.setVisible(true);
			}
		});
		btnDodajVodica.setBounds(208, 77, 117, 29);
		contentPane.add(btnDodajVodica);
		
		JComboBox cmbVodic = new JComboBox();
		cmbVodic.setBounds(29, 78, 145, 27);
		contentPane.add(cmbVodic);
		
		JComboBox cmbTermin = new JComboBox();
		cmbTermin.setBounds(29, 117, 145, 27);
		contentPane.add(cmbTermin);
		
		JComboBox cmbTipPrevoza = new JComboBox();
		cmbTipPrevoza.setBounds(39, 159, 135, 27);
		contentPane.add(cmbTipPrevoza);
		
		JComboBox cmbKlijent = new JComboBox();
		cmbKlijent.setBounds(29, 200, 141, 27);
		contentPane.add(cmbKlijent);
		
		JButton btnDodajTermin = new JButton("New button");
		btnDodajTermin.setBounds(208, 118, 117, 29);
		contentPane.add(btnDodajTermin);
		
		JButton btnDodajAranzman = new JButton("New button");
		btnDodajAranzman.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ovo je kad se klikne dodaj aranzman
				Hotel h = (Hotel) cmbHoteli.getSelectedItem();
				Vodic v = (Vodic) cmbVodic.getSelectedItem();
				Termin t = null;
				TipPrevoza tp = (TipPrevoza) cmbTipPrevoza.getSelectedItem();
				Klijent k = (Klijent) cmbKlijent.getSelectedItem();
				ArrayList<Termin> termini = new ArrayList<>();
				Aranzman a = new Aranzman(++aranzmanID, h.getGrad(), v, h, termini);
			}
		});
		btnDodajAranzman.setBounds(304, 237, 117, 29);
		contentPane.add(btnDodajAranzman);
		btnDodajAranzman.setText("Dodaj aranzman");
		
		//popuniHotele
		cmbHoteli.removeAllItems();
		ArrayList<Hotel> listaHotela = Controller.getInstance().vratiHotele();
		for (Hotel hotel : listaHotela) {
			cmbHoteli.addItem(hotel);
		}
		
		// popuniVodice
		cmbVodic.removeAllItems();
		ArrayList<Vodic> listaVodica = Controller.getInstance().vratiVodice();
		for (Vodic vodic : listaVodica) {
			cmbVodic.addItem(vodic);
		}
		
		// popuni termine
		cmbTermin.removeAllItems();
		ArrayList<Termin> termini = Controller.getInstance().vratiTermine();
		for (Termin t : termini) {
			cmbTermin.addItem(t);
		}
		// popuni tipove prevoza
		cmbTipPrevoza.removeAllItems();
		ArrayList<TipPrevoza> listaPrevoza = Controller.getInstance().vratiPrevoz();
		for (TipPrevoza tp : listaPrevoza) {
			cmbTipPrevoza.addItem(tp);
		}
		// popuni klijente
		cmbKlijent.removeAllItems();
		ArrayList<Klijent> klijenti = Controller.getInstance().vratiKlijente();
		for (Klijent k : klijenti) {
			cmbKlijent.addItem(k);
		}
		
	}
}
