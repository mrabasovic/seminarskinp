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

/**
 * Klasa koja predstavlja Aranzman. 
 * Aranzman ima atribute ID(long), opis(String), vodica, hotel, i listu termina koji su vezani za taj aranzman.
 * @author mladen
 *
 */
public class Aranzman extends AbstractDomainObject implements Serializable {
    /**
     * ID aranzmana kao long
     */
    private Long aranzmanID;
    /**
     * Opis aranzmana kao String
     */
    private String opis;
    /**
     * Vodic aranzmana koji je tip Vodic
     */
    private Vodic vodic;
    /**
     * Hotel aranzmana koji je tipa Hotel
     */
    private Hotel hotel;
    /**
     * Lista termina koja je tipa Termin
     */
    private ArrayList<Termin> termini;

    /**
     * Neparametarski konstruktor koji inicijalizuje objekat 
     */
    public Aranzman() {
    }

    /**
     * Konstruktor koji inicijalizuje objekat i postavlja vrednosti za ID, opis, vodica, hotel i termine aranzmana.
     * @param aranzmanID ID aranzmana kao long
     * @param opis Opis aranzmana kao String
     * @param vodic Vodic aranzmana koji je tip Vodic
     * @param hotel Hotel aranzmana koji je tipa Hotel
     * @param termini Lista termina koja je tipa Termin
     */
    public Aranzman(Long aranzmanID, String opis, Vodic vodic, Hotel hotel, ArrayList<Termin> termini) {
        this.aranzmanID = aranzmanID;
        this.opis = opis;
        this.vodic = vodic;
        this.hotel = hotel;
        this.termini = termini;
    }
    
    /** Vraca upit za select
     * @return upit koji je tipa String
     */
    @Override
    public String vratiUpitZaSve() {
        return "SELECT * FROM ARANZMAN A JOIN VODIC V ON (A.VODICID = V.VODICID) "
                + "JOIN HOTEL H ON (H.HOTELID = A.HOTELID)";
    }
    
    /** Vraca listu aranzmana iz baze.
     * @return lista Lista aranzmana.
     */
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

    /**
     * Vraca upit za ubacivanje novog aranzmana u bazu.
     * @return ps
     */
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

    /**
     * Vraca upit za izmenu aranzmana u bazi.
     * @return ps
     */
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

    /**
     * Vraca upit za brisanje aranzmana iz baze.
     * @return ps
     */
    @Override
    public PreparedStatement vratiUpitZaBrisanje(Connection con) throws SQLException {
        PreparedStatement ps = 
                con.prepareStatement("DELETE FROM ARANZMAN WHERE ARANZMANID = ?");
        
        ps.setLong(1, aranzmanID);
        
        return ps;
    }

    /**
     * Vraca ID aranzmana.
     * @return ID aranzmana kao long
     */
    public Long getAranzmanID() {
        return aranzmanID;
    }

    /**
     * Postavlja ID aranzmana na novu vrednost.
     * @param aranzmanID ID aranzmana kao long.
     * @throws java.lang.NullPointerException ako je uneti ID null
     */
    public void setAranzmanID(Long aranzmanID) {
    	if(aranzmanID == null)
    		throw new NullPointerException("ID aranzmana ne sme biti null");
        this.aranzmanID = aranzmanID;
    }
    /**
     * Vraca opis aranzmana.
     * @return opis aranzmana kao String
     */
    public String getOpis() {
        return opis;
    }
    /**
     * Postavlja Opis aranzmana na novu vrednost.
     * @param opis opis aranzmana kao String.
     * @throws java.lang.NullPointerException ako je uneti opis null
     */
    public void setOpis(String opis) {
    	if(opis == null)
    		throw new NullPointerException("Opis aranzmana ne sme biti null");
        this.opis = opis;
    }
    /**
     * Vraca Vodica aranzmana.
     * @return vodic aranzmana kao Vodic
     */
    public Vodic getVodic() {
        return vodic;
    }
    /**
     * Postavlja Vodica aranzmana na novu vrednost.
     * @param vodic Vodic aranzmana kao Vodic.
     * @throws java.lang.NullPointerException ako je uneti Vodic null
     */
    public void setVodic(Vodic vodic) {
    	if(vodic == null)
    		throw new NullPointerException("Vodic aranzmana ne sme biti null");
        this.vodic = vodic;
    }
    /**
     * Vraca Hotel aranzmana.
     * @return hotel aranzmana kao Hotel
     */
    public Hotel getHotel() {
        return hotel;
    }
    /**
     * Postavlja Hotel aranzmana na novu vrednost.
     * @param hotel Hotel aranzmana kao Hotel.
     * @throws java.lang.NullPointerException ako je uneti hotel null.
     */
    public void setHotel(Hotel hotel) {
    	if(hotel == null)
    		throw new NullPointerException("Hotel aranzmana ne sme biti null");
        this.hotel = hotel;
    }
    /**
     * Vraca Listu termina aranzmana.
     * @return termini aranzmana kao ArrayList Termin
     */
    public ArrayList<Termin> getTermini() {
        return termini;
    }
    /**
     * Postavlja termine aranzmana na novu vrednost.
     * @param termini Termini aranzmana kao lista.
     * @throws java.lang.NullPointerException ako su uneti termini null.
     */
    public void setTermini(ArrayList<Termin> termini) {
    	if(termini == null)
    		throw new NullPointerException("Termini aranzmana ne sme biti null");
        this.termini = termini;
    }
    
}
