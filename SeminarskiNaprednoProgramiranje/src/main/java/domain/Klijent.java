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


public class Klijent extends AbstractDomainObject implements Serializable{

    private Long klijentID;
    private String ime;
    private String prezime;
    private String brojTelefona;
    private String email;

    public Klijent() {
    }

    public Klijent(Long klijentID, String ime, String prezime, String brojTelefona, String email) {
        this.klijentID = klijentID;
        this.ime = ime;
        this.prezime = prezime;
        this.brojTelefona = brojTelefona;
        this.email = email;
    }
    
    @Override
    public String vratiUpitZaSve() {
        return "SELECT * FROM KLIJENT";
    }

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

    @Override
    public PreparedStatement vratiUpitZaBrisanje(Connection con) throws SQLException {
        PreparedStatement ps = 
                con.prepareStatement("DELETE FROM KLIJENT WHERE KLIJENTID = ?");
        
        ps.setLong(1, klijentID);
        
        return ps;
    }

    public Long getKlijentID() {
        return klijentID;
    }

    public void setKlijentID(Long klijentID) {
        this.klijentID = klijentID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    @Override
    public String toString() {
        return ime + " " + prezime;
    }
    
}
