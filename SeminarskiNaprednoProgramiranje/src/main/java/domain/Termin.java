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
import java.util.Date;

/**
 * Klasa predstavlja termin.
 * Termin ima atribute aranzman tipa Aranzman, terminID tipa int, datumOd tipa date, datumDo tipa Date, cenaBezPDV tipa double, poreskaStopa tipa Double, cenaSaPDV tipa double, klijent tipa Klijent, tipPrevoza tipa TipPrevoza.
 * @author mladen
 *
 */
public class Termin extends AbstractDomainObject implements Serializable {

	/**
	 * aranzman termina kao Aranzman
	 */
    private Aranzman aranzman;
    /**
     * ID termina kao int
     */
    private int terminID;
    /**
     * datumOd kao Date
     */
    private Date datumOd;
    /**
     * datumDo kao Date
     */
    private Date datumDo;
    /**
     * cenaBezPDV kao double
     */
    private double cenaBezPDV;
    /**
     * poreskaStopa kao double
     */
    private double poreskaStopa;
    /**
     * cenaSaPDV kao double
     */
    private double cenaSaPDV;
    /**
     * klijent kao Klijent
     */
    private Klijent klijent;
    /**
     * tipPrevoza kao TipPrevoza
     */
    private TipPrevoza tipPrevoza;
    /**
     * Neparametarski konstruktor koji inicijalizuje objekat
     */
    public Termin() {
    }

    /**
     * Konstruktor koji inicijalizuje objekat i postavlja vrednosti za aranzman, terminID, datumOd
     * datumDo, cenaBezPDV, poreskaStopa, cenaSaPDV, klijent, tipPrevoza
     * @param aranzman aranzman termina kao Aranzman
     * @param terminID ID termina kao int
     * @param datumOd datum od termina kao Date
     * @param datumDo datum do termina kao Date
     * @param cenaBezPDV cena bez pdv-a termina kao double
     * @param poreskaStopa poreska stopa termina kao double
     * @param cenaSaPDV cena sa pdv-om termina kao double
     * @param klijent klijent termina kao Klijent
     * @param tipPrevoza tip prevoza termina kao TipPrevoza
     */
    public Termin(Aranzman aranzman, int terminID, Date datumOd, Date datumDo, double cenaBezPDV, double poreskaStopa, double cenaSaPDV, Klijent klijent, TipPrevoza tipPrevoza) {
        this.aranzman = aranzman;
        this.terminID = terminID;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
        this.cenaBezPDV = cenaBezPDV;
        this.poreskaStopa = poreskaStopa;
        this.cenaSaPDV = cenaSaPDV;
        this.klijent = klijent;
        this.tipPrevoza = tipPrevoza;
    }
    /** Vraca upit za select
     * @return upit koji je tipa String
     */
    @Override
    public String vratiUpitZaSve() {
        return "SELECT * FROM TERMIN T "
                + "JOIN ARANZMAN A ON (A.ARANZMANID = T.ARANZMANID) "
                + "JOIN TIPPREVOZA TP ON (TP.TIPPREVOZAID = T.TIPPREVOZAID) "
                + "JOIN VODIC V ON (A.VODICID = V.VODICID) "
                + "JOIN HOTEL H ON (H.HOTELID = A.HOTELID) "
                + "JOIN KLIJENT K ON (K.KLIJENTID = T.KLIJENTID)";
    }
    /** Vraca listu Termina iz baze.
     * @return lista Lista termina.
     */
    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {

            Hotel h = new Hotel(rs.getLong("HotelID"), rs.getString("NazivHotela"),
                    rs.getString(11));

            Vodic v = new Vodic(rs.getLong("VodicID"),
                    rs.getString(17), rs.getString(18),
                    rs.getString(19), rs.getString(20), rs.getInt("GodineIskustva"));

            Aranzman a = new Aranzman(rs.getLong("AranzmanID"), rs.getString("Opis"), v, h, null);

            TipPrevoza tp = new TipPrevoza(rs.getLong("TipPrevozaID"), rs.getString(15));
            
            Klijent k = new Klijent(rs.getLong("KlijentID"),
                    rs.getString(26), rs.getString(27), 
                    rs.getString(28), rs.getString(29));

            Termin t = new Termin(a, rs.getInt("TerminID"), rs.getDate("DatumOd"),
                    rs.getDate("DatumDo"), rs.getDouble("CenaBezPDV"), rs.getDouble("PoreskaStopa"),
                    rs.getDouble("CenaSaPDV"), k, tp);

            lista.add(t);
        }

        rs.close();
        return lista;
    }
    /**
     * Vraca upit za ubacivanje novog termina u bazu.
     * @return ps
     */
    @Override
    public PreparedStatement vratiUpitZaUbacivanje(Connection con) throws SQLException {
        PreparedStatement ps = 
                con.prepareStatement("INSERT INTO TERMIN("
                        + "ARANZMANID, TERMINID, DATUMOD, DATUMDO,"
                        + "CENABEZPDV, PORESKASTOPA, CENASAPDV, KLIJENTID,"
                        + "TIPPREVOZAID) VALUES (?,?,?,?,?,?,?,?,?)");
        
        ps.setLong(1, aranzman.getAranzmanID());
        ps.setInt(2, terminID);
        ps.setDate(3, new java.sql.Date(datumOd.getTime()));
        ps.setDate(4, new java.sql.Date(datumDo.getTime()));
        ps.setDouble(5, cenaBezPDV);
        ps.setDouble(6, poreskaStopa);
        ps.setDouble(7, cenaSaPDV);
        ps.setLong(8, klijent.getKlijentID());
        ps.setLong(9, tipPrevoza.getTipPrevozaID());
        
        return ps;
    }
    /**
     * Vraca upit za izmenu termina u bazi.
     * @return ps
     */
    @Override
    public PreparedStatement vratiUpitZaIzmenu(Connection con) throws SQLException {
        PreparedStatement ps = 
                con.prepareStatement("UPDATE TERMIN "
                        + "SET CenaBezPDV = ?, PoreskaStopa = ?, CenaSaPDV = ?,"
                        + "TipPrevozaID = ? "
                        + " WHERE aranzmanID = ? and terminID = ?");
        
        ps.setDouble(1, cenaBezPDV);
        ps.setDouble(2, poreskaStopa);
        ps.setDouble(3, cenaSaPDV);
        ps.setLong(4, tipPrevoza.getTipPrevozaID());
        ps.setLong(5, aranzman.getAranzmanID());
        ps.setLong(6, terminID);
        
        return ps;
    }
    /**
     * Vraca upit za brisanje termina u bazi.
     * @return ps
     */
    @Override
    public PreparedStatement vratiUpitZaBrisanje(Connection con) throws SQLException {
        PreparedStatement ps = 
                con.prepareStatement("DELETE FROM TERMIN WHERE ARANZMANID = ?");
        
        ps.setLong(1, aranzman.getAranzmanID());
        
        return ps;
    }
    /**
     * Vraca id termina
     * @return ID termina kao int
     */
    public int getTerminID() {
        return terminID;
    }
    /**
     * Postavlja ID termina na novu vrednost.
     * @param klijentID ID termina kao int.
     * @throws java.lang.RuntimeException ako je uneti ID negativan broj
     */
    public void setTerminID(int terminID) {
    	if(terminID < 0)
    		throw new RuntimeException("ID termina ne sme biti negativan broj");
        this.terminID = terminID;
    }
    /**
     * Vraca datumOd termina
     * @return datumOd kao Date
     */
    public Date getDatumOd() {
        return datumOd;
    }
    /**
     * Postavlja datumOd termina na novu vrednost.
     * @param datumOd datumOd termina kao Date
     * @throws java.lang.NullPointerException ako je uneti datumOd null
     */
    public void setDatumOd(Date datumOd) {
    	if(datumOd == null)
    		throw new NullPointerException("DatumOd ne sme biti null");
        this.datumOd = datumOd;
    }
    /**
     * Vraca datumDo termina
     * @return datumDo kao Date
     */
    public Date getDatumDo() {
        return datumDo;
    }
    /**
     * Postavlja datumDo termina na novu vrednost.
     * @param datumOd datumDo termina kao Date
     * @throws java.lang.NullPointerException ako je uneti datumDo null
     */
    public void setDatumDo(Date datumDo) {
    	if(datumDo == null)
    		throw new NullPointerException("DatumDo ne sme biti null");
        this.datumDo = datumDo;
    }
    /**
     * Vraca cenu bez pdv-a
     * @return cenaBezPDV termina kao String
     */
    public double getCenaBezPDV() {
        return cenaBezPDV;
    }
    /**
     * Postavlja cenu bez pdv-a na novu vrednost.
     * @param cenaBezPDV termina kao double.
     * @throws java.lang.RuntimeException ako je uneta cenaBezPDV negativan broj
     */
    public void setCenaBezPDV(double cenaBezPDV) {
    	if(cenaBezPDV < 0)
    		throw new RuntimeException("Cena mora biti pozitivan broj");
        this.cenaBezPDV = cenaBezPDV;
    }
    /**
     * Vraca poresku stopu termina
     * @return poreskaStopa kao double
     */
    public double getPoreskaStopa() {
        return poreskaStopa;
    }
    /**
     * Postavlja poresku stopu na novu vrednost.
     * @param poreskaStopa termina kao double.
     * @throws java.lang.RuntimeException ako je uneta poreskaStopa negativan broj
     */
    public void setPoreskaStopa(double poreskaStopa) {
    	if(poreskaStopa < 0)
    		throw new RuntimeException("Poreska stopa mora biti pozitivan broj");
        this.poreskaStopa = poreskaStopa;
    }
    /**
     * Vraca cenu sa pdv-om
     * @return cenaSaPDV termina kao String
     */
    public double getCenaSaPDV() {
        return cenaSaPDV;
    }
    /**
     * Postavlja cenu sa pdv-om na novu vrednost.
     * @param cenaSaPDV termina kao double.
     * @throws java.lang.RuntimeException ako je uneta cenaSaPDV negativan broj
     */
    public void setCenaSaPDV(double cenaSaPDV) {
    	if(cenaSaPDV < 0)
    		throw new RuntimeException("Cena mora biti pozitivan broj");
        this.cenaSaPDV = cenaSaPDV;
    }
    /**
     * Vraca klijenta
     * @return klijent termina kao Klijent
     */
    public Klijent getKlijent() {
        return klijent;
    }
    /**
     * Postavlja klijenta termina na novu vrednost.
     * @param klijent klijent termina kao Klijent
     * @throws java.lang.NullPointerException ako je uneti klijent null
     */
    public void setKlijent(Klijent klijent) {
    	if(klijent == null)
    		throw new NullPointerException("Klijent ne sme biti null");
        this.klijent = klijent;
    }
    /**
     * Vraca tip prevoza
     * @return tipPrevoza termina kao TipPrevoza
     */
    public TipPrevoza getTipPrevoza() {
        return tipPrevoza;
    }
    /**
     * Postavlja tip prevoza termina na novu vrednost.
     * @param tipPrevoza tip prevoza termina kao TipPrevoza
     * @throws java.lang.NullPointerException ako je uneti tip prevoza null
     */
    public void setTipPrevoza(TipPrevoza tipPrevoza) {
    	if(tipPrevoza == null)
    		throw new NullPointerException("Tip prevoza ne sme biti null");
        this.tipPrevoza = tipPrevoza;
    }
    /**
     * Vraca aranzman
     * @return aranzman termina kao Aranzman
     */
    public Aranzman getAranzman() {
        return aranzman;
    }
    /**
     * Postavlja aranzman termina na novu vrednost.
     * @param aranzman aranzman termina kao Aranzman
     * @throws java.lang.NullPointerException ako je uneti aranzman null
     */
    public void setAranzman(Aranzman aranzman) {
    	if(aranzman == null)
    		throw new NullPointerException("Aranzman ne sme biti null");
        this.aranzman = aranzman;
    }

}
