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


public class Vodic extends AbstractDomainObject implements Serializable{

    private Long vodicID;
    private String ime;
    private String prezime;
    private String brojTelefona;
    private String email;
    private int godineIskustva;

    public Vodic() {
    }

    public Vodic(Long vodicID, String ime, String prezime, String brojTelefona, String email, int godineIskustva) {
        this.vodicID = vodicID;
        this.ime = ime;
        this.prezime = prezime;
        this.brojTelefona = brojTelefona;
        this.email = email;
        this.godineIskustva = godineIskustva;
    }
    
    
    @Override
    public String vratiUpitZaSve() {
        return "SELECT * FROM VODIC";
    }

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

    @Override
    public PreparedStatement vratiUpitZaBrisanje(Connection con) throws SQLException {
        PreparedStatement ps = 
                con.prepareStatement("DELETE FROM VODIC WHERE VODICID = ?");
        
        ps.setLong(1, vodicID);
        
        return ps;
    }

    public Long getVodicID() {
        return vodicID;
    }

    public void setVodicID(Long vodicID) {
        this.vodicID = vodicID;
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

    public int getGodineIskustva() {
        return godineIskustva;
    }

    public void setGodineIskustva(int godineIskustva) {
        this.godineIskustva = godineIskustva;
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

    @Override
    public String toString() {
        return ime + " " + prezime;
    }
    
}
