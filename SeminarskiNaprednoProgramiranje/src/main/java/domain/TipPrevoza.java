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


public class TipPrevoza extends AbstractDomainObject implements Serializable{
    
    private Long tipPrevozaID;
    private String opis;

    public TipPrevoza() {
    }

    public TipPrevoza(Long tipPrevozaID, String opis) {
        this.tipPrevozaID = tipPrevozaID;
        this.opis = opis;
    }
    
    @Override
    public String vratiUpitZaSve() {
        return "SELECT * FROM TIPPREVOZA";
    }

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

    public Long getTipPrevozaID() {
        return tipPrevozaID;
    }

    public void setTipPrevozaID(Long tipPrevozaID) {
        this.tipPrevozaID = tipPrevozaID;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Override
    public String toString() {
        return opis;
    }
    
}
