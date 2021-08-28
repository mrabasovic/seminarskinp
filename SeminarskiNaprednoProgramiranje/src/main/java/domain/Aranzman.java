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
import java.sql.Statement;
import java.util.ArrayList;


public class Aranzman extends AbstractDomainObject implements Serializable {
    
    private Long aranzmanID;
    private String opis;
    private Vodic vodic;
    private Hotel hotel;
    private ArrayList<Termin> termini;

    public Aranzman() {
    }

    public Aranzman(Long aranzmanID, String opis, Vodic vodic, Hotel hotel, ArrayList<Termin> termini) {
        this.aranzmanID = aranzmanID;
        this.opis = opis;
        this.vodic = vodic;
        this.hotel = hotel;
        this.termini = termini;
    }

    @Override
    public String vratiUpitZaSve() {
        return "SELECT * FROM ARANZMAN A JOIN VODIC V ON (A.VODICID = V.VODICID) "
                + "JOIN HOTEL H ON (H.HOTELID = A.HOTELID)";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        
        while(rs.next()){
            
            Hotel h = new Hotel(rs.getLong("HotelID"), rs.getString("NazivHotela"),
                                    rs.getString("Opis"));
            
            Vodic v = new Vodic(rs.getLong("VodicID"),
                    rs.getString("Ime"), rs.getString("Prezime"), 
                    rs.getString("BrojTelefona"), rs.getString("Email"), rs.getInt("GodineIskustva"));
            
            Aranzman a = new Aranzman(rs.getLong("AranzmanID"), rs.getString("Opis"), v, h, null);
            
            lista.add(a);
        }
        
        rs.close();
        return lista;
    }

    @Override
    public PreparedStatement vratiUpitZaUbacivanje(Connection con) throws SQLException {
        PreparedStatement ps = 
                con.prepareStatement("INSERT INTO ARANZMAN("
                        + "OPIS, VODICID, HOTELID) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
        
        ps.setString(1, opis);
        ps.setLong(2, vodic.getVodicID());
        ps.setLong(3, hotel.getHotelID());
        
        return ps;
    }

    @Override
    public PreparedStatement vratiUpitZaIzmenu(Connection con) throws SQLException {
        PreparedStatement ps = 
                con.prepareStatement("UPDATE ARANZMAN "
                        + "SET Opis = ?, VodicID = ? "
                        + " WHERE aranzmanID = ?");
        
        ps.setString(1, opis);
        ps.setLong(2, vodic.getVodicID());
        ps.setLong(3, aranzmanID);
        
        return ps;
    }

    @Override
    public PreparedStatement vratiUpitZaBrisanje(Connection con) throws SQLException {
        PreparedStatement ps = 
                con.prepareStatement("DELETE FROM ARANZMAN WHERE ARANZMANID = ?");
        
        ps.setLong(1, aranzmanID);
        
        return ps;
    }

    public Long getAranzmanID() {
        return aranzmanID;
    }

    public void setAranzmanID(Long aranzmanID) {
    	if(aranzmanID == null)
    		throw new NullPointerException("ID aranzmana ne sme biti null");
        this.aranzmanID = aranzmanID;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
    	if(opis == null)
    		throw new NullPointerException("Opis aranzmana ne sme biti null");
        this.opis = opis;
    }

    public Vodic getVodic() {
        return vodic;
    }

    public void setVodic(Vodic vodic) {
    	if(vodic == null)
    		throw new NullPointerException("Vodic aranzmana ne sme biti null");
        this.vodic = vodic;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
    	if(hotel == null)
    		throw new NullPointerException("Hotel aranzmana ne sme biti null");
        this.hotel = hotel;
    }

    public ArrayList<Termin> getTermini() {
        return termini;
    }

    public void setTermini(ArrayList<Termin> termini) {
    	if(termini == null)
    		throw new NullPointerException("Termini aranzmana ne sme biti null");
        this.termini = termini;
    }
    
}
