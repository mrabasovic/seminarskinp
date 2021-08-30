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
 * Klasa predstavlja vodica.
 * Vodic ima atribute vodicID koji je tipa long, ime tipa string, prezime tipa string, brojTelefona tipa string, email tipa string, godineIskustva tipa int.
 * @author mladen
 *
 */
public class Vodic extends AbstractDomainObject implements Serializable{
	/**
	 * ID vodica kao long
	 */
    private Long vodicID;
    /**
     * ime vodica kao string
     */
    private String ime;
    /**
     * prezime vodica kao string
     */
    private String prezime;
    /**
     * broj telefona vodica kao string
     */
    private String brojTelefona;
    /**
     * email vodica kao string
     */
    private String email;
    /**
     * godine iskustva vodica kao int
     */
    private int godineIskustva;
    /**
     * Neparametarski konstruktor koji inicijalizuje objekat
     */
    public Vodic() {
    }
    /**
     * Konstruktor koji inicijalizuje objekat i postavlja vrednosti za ID, ime, prezime, broj telefona, email i godine iskustva.
     * @param vodicID ID vodica kao long
     * @param ime ime vodica kao string
     * @param prezime prezime vodica kao string
     * @param brojTelefona broj telefona vodica kao string
     * @param email email vodica kao string
     * @param godineIskustva godine iskustva vodica kao int
     */
    public Vodic(Long vodicID, String ime, String prezime, String brojTelefona, String email, int godineIskustva) {
        this.vodicID = vodicID;
        this.ime = ime;
        this.prezime = prezime;
        this.brojTelefona = brojTelefona;
        this.email = email;
        this.godineIskustva = godineIskustva;
    }
    
    /** Vraca upit za select
     * @return upit koji je tipa String
     */
    @Override
    public String vratiUpitZaSve() {
        return "SELECT * FROM VODIC";
    }
    /** Vraca listu Vodica iz baze.
     * @return lista Lista vodica.
     */
    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        
        while(rs.next()){
            Vodic v = new Vodic(rs.getLong("VodicID"),
                    rs.getString("Ime"), rs.getString("Prezime"), 
                    rs.getString("BrojTelefona"), rs.getString("Email"), rs.getInt("GodineIskustva"));
            
            lista.add(v);
        }
        
        rs.close();
        return lista;
    }
    /**
     * Vraca upit za ubacivanje novog vodica u bazu.
     * @return ps
     */
    @Override
    public PreparedStatement vratiUpitZaUbacivanje(Connection con) throws SQLException {
        PreparedStatement ps = 
                con.prepareStatement("INSERT INTO VODIC("
                        + "IME, PREZIME, BROJTELEFONA,EMAIL,GODINEISKUSTVA) VALUES (?,?,?,?,?)");
        
        ps.setString(1, ime);
        ps.setString(2, prezime);
        ps.setString(3, brojTelefona);
        ps.setString(4, email);
        ps.setInt(5, godineIskustva);
        
        return ps;
    }
    /**
     * Vraca upit za izmenu vodica u bazi.
     * @return ps
     */
    @Override
    public PreparedStatement vratiUpitZaIzmenu(Connection con) throws SQLException {
        PreparedStatement ps = 
                con.prepareStatement("UPDATE VODIC "
                        + "SET BrojTelefona = ?, Email = ?, GodineIskustva = ? "
                        + " WHERE vodicid = ?");
        
        ps.setString(1, brojTelefona);
        ps.setString(2, email);
        ps.setInt(3, godineIskustva);
        ps.setLong(4, vodicID);
        
        return ps;
    }
    /**
     * Vraca upit za brisanje vodica u bazi.
     * @return ps
     */
    @Override
    public PreparedStatement vratiUpitZaBrisanje(Connection con) throws SQLException {
        PreparedStatement ps = 
                con.prepareStatement("DELETE FROM VODIC WHERE VODICID = ?");
        
        ps.setLong(1, vodicID);
        
        return ps;
    }
    /**
     * Vraca id vodica
     * @return ID vodica kao long
     */
    public Long getVodicID() {
        return vodicID;
    }
    /**
     * Postavlja ID vodica na novu vrednost.
     * @param vodicID ID vodica kao long.
     * @throws java.lang.NullPointerException ako je uneti ID null
     */
    public void setVodicID(Long vodicID) {
    	if(vodicID == null)
    		throw new NullPointerException("ID vodica ne sme biti null");
        this.vodicID = vodicID;
    }
    /**
     * Vraca ime vodica
     * @return ime vodica kao String
     */
    public String getIme() {
        return ime;
    }
    /**
     * Postavlja ime vodica na novu vrednost.
     * @param ime ime vodica kao string.
     * @throws java.lang.NullPointerException ako je uneto ime null
     * @throws java.lang.RuntimeException ako je uneto ime krace od 2 karaktera.
     */
    public void setIme(String ime) {
    	if(ime == null)
    		throw new NullPointerException("Ime ne sme biti null!");
    	if(ime.length() <2)
    		throw new RuntimeException("Ime mora imati barem 2 slova");
        this.ime = ime;
    }
    /**
     * Vraca prezime vodica
     * @return prezime vodica kao string
     */
    public String getPrezime() {
        return prezime;
    }
    /**
     * Postavlja prezime vodica na novu vrednost.
     * @param prezime prezime vodica kao string.
     * @throws java.lang.NullPointerException ako je uneto prezime null
     * @throws java.lang.RuntimeException ako je uneto prezime krace od 2 karaktera.
     */
    public void setPrezime(String prezime) {
    	if(prezime == null)
    		throw new NullPointerException("Prezime ne sme biti null!");
    	if(prezime.length() <2)
    		throw new RuntimeException("Prezime mora imati barem 2 slova");
        this.prezime = prezime;
    }
    /**
     * Vraca broj telefona vodica
     * @return brojTelefona vodica kao string
     */
    public String getBrojTelefona() {
        return brojTelefona;
    }
    /**
     * Postavlja broj telefona vodica na novu vrednost.
     * @param brojTelefona broj telefona vodica kao string.
     * @throws java.lang.RuntimeException ako je unet broj telefona nije 10 cifara.
     */
    public void setBrojTelefona(String brojTelefona) {
    	if(!(brojTelefona.length() == 10))
    		throw new RuntimeException("Broj telefona mora imati 10 cifara");
        this.brojTelefona = brojTelefona;
    }
    /**
     * Vraca email vodica
     * @return email vodica kao string
     */
    public String getEmail() {
        return email;
    }
    /**
     * Postavlja email vodica na novu vrednost.
     * @param email email vodica kao string.
     * @throws java.lang.NullPointerException ako je uneti email null
     * @throws java.lang.RuntimeException ako uneti email ne sadrzi znak @.
     */
    public void setEmail(String email) {
    	if(email == null) {
    		throw new NullPointerException("Email ne sme biti null!");
    	}
    	if(!email.contains("@"))
    		throw new RuntimeException("Email mora sadrzati znak @!");
        this.email = email;
    }

    /**
     * Vraca godineIskustva vodica.
     * @return godineIskustva kao int
     */
    public int getGodineIskustva() {
        return godineIskustva;
    }
    /**
     * Postavlja godineIskustva vodica na novu vrednost.
     * @param godineIskustva godine iskustva vodica kao int.
     * @throws java.lang.RuntimeException su unete godineIskustva negativan broj.
     */
    public void setGodineIskustva(int godineIskustva) {
    	if(godineIskustva < 0)
    		throw new RuntimeException("Godine iskustva ne smeju biti negativan broj");
        this.godineIskustva = godineIskustva;
    }
    /**
     * Poredi dva vodica i vraca true ako su isti i false ako nisu
     * Vodici se porede po imenu, prezimenu, broju telefon i email-u, i svi moraju biti isti.
     * @return true ako su oba vodica klase Vodic i imaju ista imena, prezimena, broj telefona i email.
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
        final Vodic other = (Vodic) obj;
        if (this.godineIskustva != other.godineIskustva) {
            return false;
        }
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
     * @return vraca string sa imenom i prezimenom vodica.
     */
    @Override
    public String toString() {
        return ime + " " + prezime;
    }
    
}
