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
 * Klasa predstavlja Administratora.
 * Administrator ima atribute administratorID tipa long, imeAdministratora tipa string,
 * prezimeAdministratora tipa string, username tipa string, password tipa string.
 * @author mladen
 *
 */
public class Administrator extends AbstractDomainObject implements Serializable {
    /**
     * ID administratora kao long
     */
    private Long administratorID;
    /**
     * ime administratora kao string
     */
    private String imeAdministratora;
    /**
     * prezime administratora kao string
     */
    private String prezimeAdministratora;
    /**
     * username administratora kao string
     */
    private String username;
    /**
     * password administratora kao string
     */
    private String password;

    /**
     * Neparametarski konstruktor koji inicijalizuje objekat
     */
    public Administrator() {
    }

    /**
     * Konstruktor koji inicijalizuje objekat i postavlja vrednosti za ID, ime, prezime, username, password.
     * @param administratorID ID administratora kao long
     * @param imeAdministratora ime administratora kao string
     * @param prezimeAdministratora prezime administratora kao string
     * @param username username administratora kao string
     * @param password password administratora kao string
     */
    public Administrator(Long administratorID, String imeAdministratora, String prezimeAdministratora, String username, String password) {
        this.administratorID = administratorID;
        this.imeAdministratora = imeAdministratora;
        this.prezimeAdministratora = prezimeAdministratora;
        this.username = username;
        this.password = password;
    }
    /** Vraca upit za select
     * @return upit koji je tipa String
     */
    @Override
    public String vratiUpitZaSve() {
        return "SELECT * FROM ADMINISTRATOR";
    }
    /** Vraca listu Administratora iz baze.
     * @return lista Lista administratora.
     */
    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        
        while(rs.next()){
            Administrator a = new Administrator(rs.getLong("AdministratorID"),
                    rs.getString("Ime"), rs.getString("Prezime"), 
                    rs.getString("KorisnickoIme"), rs.getString("Lozinka"));
            
            lista.add(a);
        }
        
        rs.close();
        return lista;
        
    }
    /**
     * Vraca upit za ubacivanje novog administratora u bazu.
     * @return ps
     */
    @Override
    public PreparedStatement vratiUpitZaUbacivanje(Connection con) throws SQLException {
        PreparedStatement ps = 
                con.prepareStatement("INSERT INTO ADMINISTRATOR("
                        + "IME, PREZIME,KORISNICKOIME,LOZINKA) VALUES (?,?,?,?)");
        
        ps.setString(1, imeAdministratora);
        ps.setString(2, prezimeAdministratora);
        ps.setString(3, username);
        ps.setString(4, password);
        
        return ps;
    }
    /**
     * Vraca upit za izmenu administratora u bazi.
     * @return ps
     */
    @Override
    public PreparedStatement vratiUpitZaIzmenu(Connection con) throws SQLException {
        PreparedStatement ps = 
                con.prepareStatement("UPDATE ADMINISTRATOR "
                        + "SET Ime = ?, Prezime = ?, KorisnickoIme = ?, Lozinka = ?"
                        + " WHERE administratorid = ?");
        
        ps.setString(1, imeAdministratora);
        ps.setString(2, prezimeAdministratora);
        ps.setString(3, username);
        ps.setString(4, password);
        ps.setLong(5, administratorID);
        
        return ps;
    }
    /**
     * Vraca upit za brisanje administratora u bazi.
     * @return ps
     */
    @Override
    public PreparedStatement vratiUpitZaBrisanje(Connection con) throws SQLException {
        PreparedStatement ps = 
                con.prepareStatement("DELETE FROM ADMINISTRATOR WHERE ADMINISTRATORID = ?");
        
        ps.setLong(1, administratorID);
        
        return ps;
    }
    /**
     * Vraca id administratora
     * @return ID administratora kao long
     */
    public long getAdministratorID() {
        return administratorID;
    }
    /**
     * Postavlja ID administratora na novu vrednost.
     * @param administratorID ID administratora kao long.
     */
    public void setAdministratorID(long administratorID) {
        this.administratorID = administratorID;
    }
    /**
     * Vraca ime administratora
     * @return ime administratora kao String
     */
    public String getImeAdministratora() {
        return imeAdministratora;
    }
    /**
     * Postavlja ime administratora na novu vrednost.
     * @param ime ime administratora kao string.
     * @throws java.lang.NullPointerException ako je uneto ime null
     * @throws java.lang.RuntimeException ako je uneto ime krace od 2 karaktera.
     */
    public void setImeAdministratora(String imeAdministratora) {
    	if(imeAdministratora == null)
    		throw new NullPointerException("Ime admina ne sme biti null");
    	if(imeAdministratora.length() < 2)
    		throw new RuntimeException("Ime mora biti duze od 1 slova");
        this.imeAdministratora = imeAdministratora;
    }
    /**
     * Vraca prezime administratora
     * @return prezime administratora kao string
     */
    public String getPrezimeAdministratora() {
        return prezimeAdministratora;
    }
    /**
     * Postavlja prezime administratora na novu vrednost.
     * @param prezime prezime administratora kao string.
     * @throws java.lang.NullPointerException ako je uneto prezime null
     * @throws java.lang.RuntimeException ako je uneto prezime krace od 2 karaktera.
     */
    public void setPrezimeAdministratora(String prezimeAdministratora) {
    	if(prezimeAdministratora == null)
    		throw new NullPointerException("Prezime admina ne sme biti null");
    	if(prezimeAdministratora.length() < 2)
    		throw new RuntimeException("Prezime mora biti duze od 1 slova");
        this.prezimeAdministratora = prezimeAdministratora;
    }
    /**
     * Vraca username administratora
     * @return username administratora kao string
     */
    public String getUsername() {
        return username;
    }
    /**
     * Postavlja username administratora na novu vrednost.
     * @param username username administratora kao string.
     */
    public void setUsername(String username) {
    	if(username == null)
    		throw new NullPointerException("Username admina ne sme biti null");
        this.username = username;
    }
    /**
     * Vraca password administratora
     * @return password administratora kao string
     */
    public String getPassword() {
        return password;
    }
    /**
     * Postavlja password administratora na novu vrednost.
     * @param password password administratora kao string.
     * @throws java.lang.NullPointerException ako je uneti password null
     * @throws java.lang.RuntimeException ako je uneti password krace od 5 karaktera.
     */
    public void setPassword(String password) {
    	if(password == null)
    		throw new NullPointerException("Sifra admina ne sme biti null");
    	if(password.length() < 5)
    		throw new RuntimeException("Sifra admina ne sme biti kraca od 5 karaktera");
        this.password = password;
    }

    /**
     * @return vraca string sa podacima o imenu i prezimenu administratora.
     */
    @Override
    public String toString() {
        return imeAdministratora + " " + prezimeAdministratora;
    }

    /**
     * Poredi dva administratora i vraca true ako su isti i false ako nisu
     * Administratori se porede po imenu, prezimenu, username-u i password-u, i svi moraju biti isti.
     * @return true ako su oba objekta klase Administrator i imaju ista imena, prezimena, username i password.
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
        final Administrator other = (Administrator) obj;
        if (!Objects.equals(this.imeAdministratora, other.imeAdministratora)) {
            return false;
        }
        if (!Objects.equals(this.prezimeAdministratora, other.prezimeAdministratora)) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return true;
    }
    
    
    
}
