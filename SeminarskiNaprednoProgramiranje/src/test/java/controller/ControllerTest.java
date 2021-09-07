package controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import db.DBBroker;
import domain.Hotel;
import domain.Klijent;
import domain.Termin;
import domain.TipPrevoza;
import domain.Vodic;

class ControllerTest {
	DBBroker db;
	@BeforeEach
	void setUp() throws Exception {
		db = new DBBroker();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testController() {
		Controller c = new Controller();
		assertNotNull(c);
	}

	@Test
	void testVratiHotele() {
		Hotel h = new Hotel();
		h.setHotelID(1l);
		h.setGrad("Beograd");
		h.setNazivHotela("Hilton");
		
		db.dodajHotel(h);
		
		List<Hotel> hoteli = db.vratiHotele();
		
		assertEquals(3, hoteli.size());
		assertEquals(h.getNazivHotela(), hoteli.get(0).getNazivHotela());
	}

	@Test
	void testVratiVodice() {
		Vodic v = new Vodic();
		v.setVodicID(11l);
		v.setIme("Milan");
		
		db.dodajVodica(v);
		List<Vodic> vodici = db.vratiVodice();
		
		assertEquals(3, vodici.size());
		assertEquals(v.getIme(), vodici.get(0).getIme());
	}

	@Test
	void testVratiPrevoz() {
		TipPrevoza tp = new TipPrevoza();
		tp.setOpis("Avion");
		db.dodajPrevoz(tp);
		
		List<TipPrevoza> prevozi = db.vratiPrevoz();
		
		assertEquals(3, prevozi.size());
		assertEquals(tp.getOpis(), prevozi.get(0).getOpis());
	}

	@Test
	void testVratiTermine() {
		Termin t = new Termin();
		t.setTerminID(1);
		
		db.dodajTermin(t);
		List<Termin> termini = db.vratiTermine();
		
		assertEquals(3, termini.size());
	}

	@Test
	void testVratiKlijente() {
		Klijent k = new Klijent();
		k.setIme("Radovan");
		
		db.dodajKlijenta(k);
		List<Klijent> klijenti = db.vratiKlijente();
		
		assertEquals(3, klijenti.size());
		assertEquals(k.getIme(), klijenti.get(0).getIme());
	}

}
