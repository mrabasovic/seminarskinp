package forms;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import controller.Controller;

import javax.swing.JComboBox;
import domain.*;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

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
		
		JLabel cenaLbl = new JLabel("New label");
		cenaLbl.setBounds(218, 170, 203, 16);
		contentPane.add(cenaLbl);
		cenaLbl.setText("Cena u din: ");
		
		JLabel cenaEurLbl = new JLabel("New label");
		cenaEurLbl.setBounds(215, 204, 206, 16);
		contentPane.add(cenaEurLbl);
		cenaEurLbl.setText("Cena u EUR: ");
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
		cenaEurLbl.setText("Cena u EUR: "+termini.get(0).getCenaSaPDV());
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
		
		
		// API
		
		String base_url = "http://api.currencylayer.com";
		String api_key = "31373032763246a143fc1bae4f30e9f1";
		
		
		try {
			Gson gson = new Gson();
			URL url = new URL(base_url+"/live?access_key="+api_key);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			JsonObject rez = gson.fromJson(reader, JsonObject.class);
			double kurs = rez.get("quotes").getAsJsonObject().get("USDRSD").getAsDouble();
			String rezultat = String.format("%.1f",termini.get(0).getCenaSaPDV()*kurs);
			cenaLbl.setText("Cena u din: "+rezultat);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
