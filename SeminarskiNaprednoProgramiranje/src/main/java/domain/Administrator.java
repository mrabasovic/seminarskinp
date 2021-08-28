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


public class Administrator extends AbstractDomainObject implements Serializable {
    
    private Long administratorID;
    private String imeAdministratora;
    private String prezimeAdministratora;
    private String username;
    private String password;

    public Administrator() {
    }

    public Administrator(Long administratorID, String imeAdministratora, String prezimeAdministratora, String username, String password) {
        this.administratorID = administratorID;
        this.imeAdministratora = imeAdministratora;
        this.prezimeAdministratora = prezimeAdministratora;
        this.username = username;
        this.password = password;
    }

    @Override
    public String vratiUpitZaSve() {
        return "SELECT * FROM ADMINISTRATOR";
    }

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

    @Override
    public PreparedStatement vratiUpitZaBrisanje(Connection con) throws SQLException {
        PreparedStatement ps = 
                con.prepareStatement("DELETE FROM ADMINISTRATOR WHERE ADMINISTRATORID = ?");
        
        ps.setLong(1, administratorID);
        
        return ps;
    }

    public long getAdministratorID() {
        return administratorID;
    }

    public void setAdministratorID(long administratorID) {
        this.administratorID = administratorID;
    }

    public String getImeAdministratora() {
        return imeAdministratora;
    }

    public void setImeAdministratora(String imeAdministratora) {
    	if(imeAdministratora == null)
    		throw new NullPointerException("Ime admina ne sme biti null");
    	if(imeAdministratora.length() < 2)
    		throw new RuntimeException("Ime mora biti duze od 1 slova");
        this.imeAdministratora = imeAdministratora;
    }

    public String getPrezimeAdministratora() {
        return prezimeAdministratora;
    }

    public void setPrezimeAdministratora(String prezimeAdministratora) {
    	if(prezimeAdministratora == null)
    		throw new NullPointerException("Prezime admina ne sme biti null");
    	if(prezimeAdministratora.length() < 2)
    		throw new RuntimeException("Prezime mora biti duze od 1 slova");
        this.prezimeAdministratora = prezimeAdministratora;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
    	if(username == null)
    		throw new NullPointerException("Username admina ne sme biti null");
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
    	if(password == null)
    		throw new NullPointerException("Sifra admina ne sme biti null");
    	if(password.length() < 5)
    		throw new RuntimeException("Sifra admina ne sme biti kraca od 5 karaktera");
        this.password = password;
    }

    @Override
    public String toString() {
        return imeAdministratora + " " + prezimeAdministratora;
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
