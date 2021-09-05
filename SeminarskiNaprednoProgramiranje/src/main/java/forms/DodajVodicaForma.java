package forms;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import domain.Vodic;

public class DodajVodicaForma extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtIme;
	private JTextField txtPrezime;
	private JTextField txtTelefon;
	private JTextField txtEmail;
	private JTextField txtGodIsk;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DodajVodicaForma dialog = new DodajVodicaForma();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	long vodicID = 0;
	
	/**
	 * Create the dialog.
	 */
	public DodajVodicaForma() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblIme = new JLabel("New label");
		lblIme.setBounds(18, 22, 61, 16);
		contentPanel.add(lblIme);
		
		JLabel lblPrezime = new JLabel("New label");
		lblPrezime.setBounds(18, 53, 61, 16);
		contentPanel.add(lblPrezime);
		
		JLabel lblTelefon = new JLabel("New label");
		lblTelefon.setBounds(18, 87, 61, 16);
		contentPanel.add(lblTelefon);
		
		JLabel lblEmai = new JLabel("New label");
		lblEmai.setBounds(18, 126, 61, 16);
		contentPanel.add(lblEmai);
		
		JLabel lblGodineIsk = new JLabel("New label");
		lblGodineIsk.setBounds(18, 162, 61, 16);
		contentPanel.add(lblGodineIsk);
		
		txtIme = new JTextField();
		txtIme.setBounds(119, 17, 130, 26);
		contentPanel.add(txtIme);
		txtIme.setColumns(10);
		
		txtPrezime = new JTextField();
		txtPrezime.setBounds(119, 48, 130, 26);
		contentPanel.add(txtPrezime);
		txtPrezime.setColumns(10);
		
		txtTelefon = new JTextField();
		txtTelefon.setBounds(119, 82, 130, 26);
		contentPanel.add(txtTelefon);
		txtTelefon.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(119, 121, 130, 26);
		contentPanel.add(txtEmail);
		txtEmail.setColumns(10);
		
		txtGodIsk = new JTextField();
		txtGodIsk.setBounds(119, 157, 130, 26);
		contentPanel.add(txtGodIsk);
		txtGodIsk.setColumns(10);
		
		lblIme.setText("Ime");
		lblPrezime.setText("Prezime");
		lblEmai.setText("Email");
		lblGodineIsk.setText("Godine iskustva");
		lblTelefon.setText("Telefon");
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// kad klikne ok
						String ime = txtIme.getText();
						String prezime = txtPrezime.getText();
						String telefon = txtTelefon.getText();
						String email = txtEmail.getText();
						int godine = Integer.parseInt(txtGodIsk.getText());
						
						Vodic v = new Vodic(++vodicID, ime, prezime, telefon, email, godine);
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
