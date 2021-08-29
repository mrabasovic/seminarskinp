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

/**
 * Klasa koja predstavlja Hotel. 
 * Hotel ima atribute hotelID(long), nazivHotela(String), grad(String).
 * @author mladen
 *
 */
public class Hotel extends AbstractDomainObject implements Serializable{
    /**
     * ID hotela kao long.
     */
    private Long hotelID;
    /**
     * nazivHotela kao String.
     */
    private String nazivHotela;
    /**
     * grad hotela kao String.
     */
    private String grad;
    /**
     * Neparametarski konstruktor koji inicijalizuje objekat 
     */
    public Hotel() {
    }
/**
 * Konstruktor koji inicijalizuje objekat i postavlja vrednosti za ID, naziv i grad hotela.
 * @param hotelID
 * @param nazivHotela
 * @param grad
 */
    public Hotel(Long hotelID, String nazivHotela, String grad) {
        this.hotelID = hotelID;
        this.nazivHotela = nazivHotela;
        this.grad = grad;
    }
    
    /** Vraca upit za select
     * @return upit koji je tipa String
     */
    @Override
    public String vratiUpitZaSve() {
        return "SELECT * FROM HOTEL";
    }
    /** Vraca listu hotela iz baze.
     * @return lista Lista hotela.
     */
    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        
        while(rs.next()){
            Hotel h = new Hotel(rs.getLong("HotelID"), rs.getString("NazivHotela"),
                                    rs.getString("Grad"));
            lista.add(h);
        }
        
        rs.close();
        return lista;
    }
    
    @Override
    public PreparedStatement vratiUpitZaUbacivanje(Connection con) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PreparedStatement vratiUpitZaIzmenu(Connection con) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PreparedStatement vratiUpitZaBrisanje(Connection con) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /**
     * Vraca ID hotela.
     * @return ID hotela kao long
     */
    public Long getHotelID() {
        return hotelID;
    }
    /**
     * Postavlja ID hotela na novu vrednost.
     * @param hotelID ID hotela kao long.
     * @throws java.lang.NullPointerException ako je uneti ID null
     */
    public void setHotelID(Long hotelID) {
    	if(hotelID == null) {
    		throw new NullPointerException("ID Hotela ne sme biti null");
    	}
        this.hotelID = hotelID;
    }
    /**
     * Vraca naziv hotela.
     * @return naziv hotela kao String
     */
    public String getNazivHotela() {
        return nazivHotela;
    }
    /**
     * Postavlja naziv hotela na novu vrednost.
     * @param nazivHotela naziv hotela kao String.
     * @throws java.lang.NullPointerException ako je uneti naziv hotela null
     */
    public void setNazivHotela(String nazivHotela) {
    	if(nazivHotela == null)
    		throw new NullPointerException("Ne sme biti null!");
        this.nazivHotela = nazivHotela;
    }
    /**
     * Vraca grad hotela.
     * @return grad hotela kao String
     */
    public String getGrad() {
        return grad;
    }
    /**
     * Postavlja grad hotela na novu vrednost.
     * @param grad grad hotela kao String.
     * @throws java.lang.NullPointerException ako je uneti grad hotela null
     */
    public void setGrad(String grad) {
    	if(grad == null)
    		throw new NullPointerException("Naziv grada ne sme biti null!");
        this.grad = grad;
    }
/**
 * @return vraca string sa podacima o nazivu hotela
 */
    @Override
    public String toString() {
        return nazivHotela;
    }
    
}
