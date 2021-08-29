/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import domain.*;;
public class DBBroker {
    private static DBBroker instance;
    private Connection connection;

    public DBBroker() {
        try {
            connection=
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/turistickaagencija", "root", "");
            connection.setAutoCommit(false);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DBBroker getInstance() {
        if(instance==null)
            instance=new DBBroker();
        return instance;
    }

	public static ArrayList<Hotel> vratiHotele() {
		ArrayList<Hotel> lista = new ArrayList<>();
        String upit = "SELECT * FROM Hotel";
        try {
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while(rs.next()){
            	Hotel h = new Hotel(rs.getLong("hotelID"), rs.getString("nazivHotela"), rs.getString("grad"));
                lista.add(h);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lista;
	}

	public static ArrayList<Vodic> vratiVodice() {
		ArrayList<Vodic> lista = new ArrayList<>();
        String upit = "SELECT * FROM Vodic";
        try {
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while(rs.next()){
            	Vodic v = new Vodic(rs.getLong("VodicID"),rs.getString("Ime"),rs.getString("Prezime"), rs.getString("BrojTelefona"), rs.getString("Email"), rs.getInt("GodineIskustva"));
                lista.add(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lista;
	}

	public static ArrayList<TipPrevoza> vratiPrevoz() {
		ArrayList<TipPrevoza> lista = new ArrayList<>();
        String upit = "SELECT * FROM TipPrevoza";
        try {
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while(rs.next()){
            	TipPrevoza tp = new TipPrevoza(rs.getLong("tipPrevozaID"), rs.getString("opis"));
                lista.add(tp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lista;
	}

	public static ArrayList<Termin> vratiTermine() {
		ArrayList<Termin> lista = new ArrayList<>();
        String upit = "SELECT * FROM Termin";
        try {
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while(rs.next()){
            	//Aranzman a = new Aranzman(null, upit, null, null, lista)
            	//Termin t = new Termin(a, 0, null, null, 0, 0, 0, null, null)
            	//Aranzman a = new Aranzman(null, upit, null, null, lista)
                //lista.add(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lista;
	}

	public static ArrayList<Klijent> vratiKlijente() {
		ArrayList<Klijent> lista = new ArrayList<>();
        String upit = "SELECT * FROM Klijent";
        try {
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while(rs.next()){
            	Klijent k = new Klijent(rs.getLong("klijentID"), rs.getString("Ime"), rs.getString("Prezime"), rs.getString("brojTelefona"), rs.getString("Email"));
                lista.add(k);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lista;
	}
    
    
    
}
