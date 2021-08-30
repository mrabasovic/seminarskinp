/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Klasa predstavlja klijenta.
 * Klijent ima atribute klijentID koji je tipa long, ime tipa string, prezime tipa string, brojTelefona tipa string, email tipa string.
 * @author mladen
 *
 */
public class Klijent extends AbstractDomainObject implements Serializable{

	/**
	 * ID klijenta kao long
	 */
    private Long klijentID;
    /**
     * ime klijenta kao string
     */
    private String ime;
    /**
     * prezime klijenta kao string
     */
    private String prezime;
    /**
     * broj telefona kao string
     */
    private String brojTelefona;
    /**
     * email kao string
     */
    private String email;

    /**
     * Neparametarski konstruktor koji inicijalizuje objekat
     */
    public Klijent() {
    }

    /**
     * Konstruktor koji inicijalizuje objekat i postavlja vrednosti za ID, ime, prezime, broj telefona i email.
     * @param klijentID ID klijenta kao long
     * @param ime ime klijenta kao string
     * @param prezime prezime klijenta kao string
     * @param brojTelefona broj telefona klijenta kao string
     * @param email email klijenta kao string
     */
    public Klijent(Long klijentID, String ime, String prezime, String brojTelefona, String email) {
        this.klijentID = klijentID;
        this.ime = ime;
        this.prezime = prezime;
        this.brojTelefona = brojTelefona;
        this.email = email;
    }
    /** Vraca upit za select
     * @return upit koji je tipa String
     */
    @Override
    public String vratiUpitZaSve() {
        return "SELECT * FROM KLIJENT";
    }
    /** Vraca listu Klijenata iz baze.
     * @return lista Lista klijenata.
     */
    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        
        while(rs.next()){
            Klijent k = new Klijent(rs.getLong("KlijentID"),
                    rs.getString("Ime"), rs.getString("Prezime"), 
                    rs.getString("BrojTelefona"), rs.getString("Email"));
            
            lista.add(k);
        }
        
        rs.close();
        return lista;
    }
    /**
     * Vraca upit za ubacivanje novog klijenta u bazu.
     * @return ps
     */
    @Override
    public PreparedStatement vratiUpitZaUbacivanje(Connection con) throws SQLException {
        PreparedStatement ps = 
                con.prepareStatement("INSERT INTO KLIJENT("
                        + "IME, PREZIME, BROJTELEFONA,EMAIL) VALUES (?,?,?,?)");
        
        ps.setString(1, ime);
        ps.setString(2, prezime);
        ps.setString(3, brojTelefona);
        ps.setString(4, email);
        
        return ps;
    }
    /**
     * Vraca upit za izmenu klijenta u bazi.
     * @return ps
     */
    @Override
    public PreparedStatement vratiUpitZaIzmenu(Connection con) throws SQLException {
        PreparedStatement ps = 
                con.prepareStatement("UPDATE KLIJENT "
                        + "SET BrojTelefona = ?, Email = ?"
                        + " WHERE klijentid = ?");
        
        ps.setString(1, brojTelefona);
        ps.setString(2, email);
        ps.setLong(3, klijentID);
        
        return ps;
    }
    /**
     * Vraca upit za brisanje klijenta u bazi.
     * @return ps
     */
    @Override
    public PreparedStatement vratiUpitZaBrisanje(Connection con) throws SQLException {
        PreparedStatement ps = 
                con.prepareStatement("DELETE FROM KLIJENT WHERE KLIJENTID = ?");
        
        ps.setLong(1, klijentID);
        
        return ps;
    }

    /**
     * Vraca id klijenta
     * @return ID klijenta kao long
     */
    public Long getKlijentID() {
        return klijentID;
    }
    /**
     * Postavlja ID klijenta na novu vrednost.
     * @param klijentID ID klijenta kao long.
     * @throws java.lang.NullPointerException ako je uneti ID null
     */
    public void setKlijentID(Long klijentID) {
    	if(klijentID == null)
    		throw new NullPointerException("ID klijenta ne sme biti null");
        this.klijentID = klijentID;
    }
    /**
     * Vraca ime klijenta
     * @return ime klijenta kao String
     */
    public String getIme() {
        return ime;
    }
    /**
     * Postavlja ime klijenta na novu vrednost.
     * @param ime ime klijenta kao string.
     * @throws java.lang.NullPointerException ako je uneto ime null
     * @throws java.lang.RuntimeException ako je uneto ime krace od 2 karaktera.
     */
    public void setIme(String ime) {
    	if(ime == null){
    		throw new NullPointerException("Ime klijenta ne sme biti null");
    	}
    	if(ime.length() < 2)
    		throw new RuntimeException("Klijentovo ime mora biti duze od 1 slova");
        this.ime = ime;
    }
    /**
     * Vraca prezime klijenta
     * @return prezime klijenta kao string
     */
    public String getPrezime() {
        return prezime;
    }
    /**
     * Postavlja prezime klijenta na novu vrednost.
     * @param prezime prezime klijenta kao string.
     * @throws java.lang.NullPointerException ako je uneto prezime null
     * @throws java.lang.RuntimeException ako je uneto prezime krace od 2 karaktera.
     */
    public void setPrezime(String prezime) {
    	if(prezime == null){
    		throw new NullPointerException("Prezime klijenta ne sme biti null");
    	}
    	if(prezime.length() < 2)
    		throw new RuntimeException("Klijentovo prezime mora biti duze od 1 slova");
        this.prezime = prezime;
    }
    /**
     * Vraca broj telefona klijenta
     * @return brojTelefona klijenta kao string
     */
    public String getBrojTelefona() {
        return brojTelefona;
    }
    /**
     * Postavlja broj telefona klijenta na novu vrednost.
     * @param brojTelefona broj telefona klijenta kao string.
     * @throws java.lang.RuntimeException ako je unet broj telefona kraci od 10 cifara.
     */
    public void setBrojTelefona(String brojTelefona) {
    	if(brojTelefona.length() != 10)
    		throw new RuntimeException("Klijentov telefon mora biti tacno 10 cifara");
        this.brojTelefona = brojTelefona;
    }
    /**
     * Vraca email klijenta
     * @return email klijenta kao string
     */
    public String getEmail() {
        return email;
    }
    /**
     * Postavlja email klijenta na novu vrednost.
     * @param email email klijenta kao string.
     * @throws java.lang.NullPointerException ako je uneti email null
     * @throws java.lang.RuntimeException ako uneti email ne sadrzi znak @.
     */
    public void setEmail(String email) {
    	if(email == null)
    		throw new NullPointerException("Email ne sme biti null");
    	if(!(email.contains("@")))
    		throw new RuntimeException("Email mora sadrzati @");
        this.email = email;
    }

    /**
     * Poredi dva klijenta i vraca true ako su isti i false ako nisu
     * Klijenti se porede po imenu, prezimenu, broju telefon i email-u, i svi moraju biti isti.
     * @return true ako su oba objekta klase Klijent i imaju ista imena, prezimena, broj telefona i email.
     * false u svim ostalim slucajevima
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Klijent other = (Klijent) obj;
        if (!Objects.equals(this.ime, other.ime)) {
            return false;
        }
        if (!Objects.equals(this.prezime, other.prezime)) {
            return false;
        }
        if (!Objects.equals(this.brojTelefona, other.brojTelefona)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }

    /**
     * @return vraca string sa imenom i prezimenom klijenta
     */
    @Override
    public String toString() {
        return ime + " " + prezime;
    }
    
}
