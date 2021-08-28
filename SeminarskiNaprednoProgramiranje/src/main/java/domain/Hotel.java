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


public class Hotel extends AbstractDomainObject implements Serializable{
    
    private Long hotelID;
    private String nazivHotela;
    private String grad;

    public Hotel() {
    }

    public Hotel(Long hotelID, String nazivHotela, String grad) {
        this.hotelID = hotelID;
        this.nazivHotela = nazivHotela;
        this.grad = grad;
    }
    

    @Override
    public String vratiUpitZaSve() {
        return "SELECT * FROM HOTEL";
    }

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

    public Long getHotelID() {
        return hotelID;
    }

    public void setHotelID(Long hotelID) {
    	if(hotelID == null) {
    		throw new NullPointerException("ID Hotela ne sme biti null");
    	}
        this.hotelID = hotelID;
    }

    public String getNazivHotela() {
        return nazivHotela;
    }

    public void setNazivHotela(String nazivHotela) {
    	if(nazivHotela == null)
    		throw new NullPointerException("Ne sme biti null!");
        this.nazivHotela = nazivHotela;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
    	if(grad == null)
    		throw new NullPointerException("Naziv grada ne sme biti null!");
        this.grad = grad;
    }

    @Override
    public String toString() {
        return nazivHotela;
    }
    
}
