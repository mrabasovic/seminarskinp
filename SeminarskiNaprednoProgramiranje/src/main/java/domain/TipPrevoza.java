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
 * Klasa predstavlja tip prevoza.
 * Tip Prevoza ima atribute tipPrevozaID koji je tipa long i opis koji je tipa string.
 * @author mladen
 *
 */
public class TipPrevoza extends AbstractDomainObject implements Serializable{
    /**
     * ID tipa prevoza kao long
     */
    private Long tipPrevozaID;
    /**
     * opis kao string
     */
    private String opis;
    /**
     * Neparametarski konstruktor koji inicijalizuje objekat
     */
    public TipPrevoza() {
    }

    /**
     * Konstruktor koji inicijalizuje objekat i postavlja vrednosti za tipPrevozaID i opis.
     * @param tipPrevozaID
     * @param opis
     */
    public TipPrevoza(Long tipPrevozaID, String opis) {
        this.tipPrevozaID = tipPrevozaID;
        this.opis = opis;
    }
    /** Vraca upit za select
     * @return upit koji je tipa String
     */
    @Override
    public String vratiUpitZaSve() {
        return "SELECT * FROM TIPPREVOZA";
    }
    /** Vraca listu tipa prevoza iz baze.
     * @return lista Lista tipa prevoza.
     */
    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        
        while(rs.next()){
            TipPrevoza tp = new TipPrevoza(rs.getLong("TipPrevozaID"), rs.getString("Opis"));
            lista.add(tp);
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
     * Vraca id tipa prevoza
     * @return tipPrevozaID tip prevoza ID kao long
     */
    public Long getTipPrevozaID() {
        return tipPrevozaID;
    }
    /**
     * Postavlja ID tipa prevoza na novu vrednost.
     * @param tipPrevozaID ID tipa prevoza kao long.
     * @throws java.lang.NullPointerException ako je uneti ID null
     */
    public void setTipPrevozaID(Long tipPrevozaID) {
    	if(tipPrevozaID == null)
    		throw new NullPointerException("ID tipa prevoza ne sme biti null");
        this.tipPrevozaID = tipPrevozaID;
    }
    /**
     * Vraca opis tipa prevoza
     * @return opis opis tipa prevoza kao string
     */
    public String getOpis() {
        return opis;
    }
    /**
     * Postavlja opis tipa prevoza na novu vrednost.
     * @param opis opis tipa prevoza kao string.
     * @throws java.lang.NullPointerException ako je uneti opis null
     */
    public void setOpis(String opis) {
    	if(opis == null)
    		throw new NullPointerException("Opis tipa prevoza ne sme biti null");
        this.opis = opis;
    }

    /**
     * @return vraca string sa podacima o opisu tipa prevoza
     */
    @Override
    public String toString() {
        return opis;
    }
    
}
