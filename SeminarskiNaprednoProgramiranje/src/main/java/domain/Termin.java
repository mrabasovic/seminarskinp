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


public class Termin extends AbstractDomainObject implements Serializable {

    private Aranzman aranzman;
    private int terminID;
    private Date datumOd;
    private Date datumDo;
    private double cenaBezPDV;
    private double poreskaStopa;
    private double cenaSaPDV;
    private Klijent klijent;
    private TipPrevoza tipPrevoza;

    public Termin() {
    }

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

    @Override
    public String vratiUpitZaSve() {
        return "SELECT * FROM TERMIN T "
                + "JOIN ARANZMAN A ON (A.ARANZMANID = T.ARANZMANID) "
                + "JOIN TIPPREVOZA TP ON (TP.TIPPREVOZAID = T.TIPPREVOZAID) "
                + "JOIN VODIC V ON (A.VODICID = V.VODICID) "
                + "JOIN HOTEL H ON (H.HOTELID = A.HOTELID) "
                + "JOIN KLIJENT K ON (K.KLIJENTID = T.KLIJENTID)";
    }

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

    @Override
    public PreparedStatement vratiUpitZaBrisanje(Connection con) throws SQLException {
        PreparedStatement ps = 
                con.prepareStatement("DELETE FROM TERMIN WHERE ARANZMANID = ?");
        
        ps.setLong(1, aranzman.getAranzmanID());
        
        return ps;
    }

    public int getTerminID() {
        return terminID;
    }

    public void setTerminID(int terminID) {
    	if(terminID < 0)
    		throw new RuntimeException("ID termina ne sme biti negativan broj");
        this.terminID = terminID;
    }

    public Date getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(Date datumOd) {
    	if(datumOd == null)
    		throw new NullPointerException("DatumOd ne sme biti null");
        this.datumOd = datumOd;
    }

    public Date getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(Date datumDo) {
    	if(datumDo == null)
    		throw new NullPointerException("DatumDo ne sme biti null");
        this.datumDo = datumDo;
    }

    public double getCenaBezPDV() {
        return cenaBezPDV;
    }

    public void setCenaBezPDV(double cenaBezPDV) {
    	if(cenaBezPDV < 0)
    		throw new RuntimeException("Cena mora biti pozitivan broj");
        this.cenaBezPDV = cenaBezPDV;
    }

    public double getPoreskaStopa() {
        return poreskaStopa;
    }

    public void setPoreskaStopa(double poreskaStopa) {
    	if(poreskaStopa < 0)
    		throw new RuntimeException("Poreska stopa mora biti pozitivan broj");
        this.poreskaStopa = poreskaStopa;
    }

    public double getCenaSaPDV() {
        return cenaSaPDV;
    }

    public void setCenaSaPDV(double cenaSaPDV) {
    	if(cenaSaPDV < 0)
    		throw new RuntimeException("Cena mora biti pozitivan broj");
        this.cenaSaPDV = cenaSaPDV;
    }

    public Klijent getKlijent() {
        return klijent;
    }

    public void setKlijent(Klijent klijent) {
    	if(klijent == null)
    		throw new NullPointerException("Klijent ne sme biti null");
        this.klijent = klijent;
    }

    public TipPrevoza getTipPrevoza() {
        return tipPrevoza;
    }

    public void setTipPrevoza(TipPrevoza tipPrevoza) {
    	if(tipPrevoza == null)
    		throw new NullPointerException("Tip prevoza ne sme biti null");
        this.tipPrevoza = tipPrevoza;
    }

    public Aranzman getAranzman() {
        return aranzman;
    }

    public void setAranzman(Aranzman aranzman) {
    	if(aranzman == null)
    		throw new NullPointerException("Aranzman ne sme biti null");
        this.aranzman = aranzman;
    }

}
