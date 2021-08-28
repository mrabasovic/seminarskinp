package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TerminTest {
	Termin t;
	
	@BeforeEach
	void setUp() throws Exception {
		t = new Termin();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testVratiListu() {
		ArrayList<AbstractDomainObject> lista = new ArrayList<>();
		Aranzman a = new Aranzman();
		Klijent k = new Klijent();
		TipPrevoza tp = new TipPrevoza();
		Date datumOd = new Date();
		Date datumDo = new Date();
		t = new Termin(a, 22, datumOd, datumDo, 500, 1, 550, k, tp);
		
        lista.add(t);
        assertEquals(t, lista.get(0));
	}

	@Test
	void testTermin() {
		t = new Termin();
		assertNotNull(t);
	}

	@Test
	void testTerminAranzmanIntDateDateDoubleDoubleDoubleKlijentTipPrevoza() {
		Aranzman a = new Aranzman();
		Klijent k = new Klijent();
		TipPrevoza tp = new TipPrevoza();
		Date datumOd = new Date();
		Date datumDo = new Date();
		t = new Termin(a, 22, datumOd, datumDo, 500, 1, 550, k, tp);
		
		assertNotNull(t);
		assertEquals(a, t.getAranzman());
		assertEquals(22, t.getTerminID());
		assertEquals(datumOd, t.getDatumOd());
		assertEquals(datumDo, t.getDatumDo());
		assertEquals(500, t.getCenaBezPDV());
		assertEquals(1, t.getPoreskaStopa());
		assertEquals(550, t.getCenaSaPDV());
		assertEquals(tp, t.getTipPrevoza());
	}

	@Test
	void testSetTerminID() {
		t.setTerminID(123);
		assertEquals(123, t.getTerminID());
	}

	@Test
	void testSetTerminIDNegativan() {
		assertThrows(java.lang.RuntimeException.class,
				() -> t.setTerminID(-2) );
	}
	
	@Test
	void testSetDatumOd() {
		Date d = new Date();
		t.setDatumOd(d);
	}

	@Test
	void testSetDatumOdNull() {
		assertThrows(java.lang.NullPointerException.class,
				() -> t.setDatumOd(null) );
	}
	
	@Test
	void testSetDatumDo() {
		Date d = new Date();
		t.setDatumDo(d);
	}

	@Test
	void testSetDatumDoNull() {
		assertThrows(java.lang.NullPointerException.class,
				() -> t.setDatumDo(null) );
	}
	
	@Test
	void testSetCenaBezPDV() {
		t.setCenaBezPDV(500);
		assertEquals(500, t.getCenaBezPDV());
	}

	@Test
	void testSetCenaBezPDVNegativna() {
		assertThrows(java.lang.RuntimeException.class,
				() -> t.setCenaBezPDV(-200) );
	}
	
	@Test
	void testSetPoreskaStopa() {
		t.setPoreskaStopa(1);
		assertEquals(1, t.getPoreskaStopa());
	}

	@Test
	void testSetPoreskaStopaNegativna() {
		assertThrows(java.lang.RuntimeException.class,
				() -> t.setPoreskaStopa(-2) );
	}
	
	@Test
	void testSetCenaSaPDV() {
		t.setCenaSaPDV(520);
		assertEquals(520, t.getCenaSaPDV());
	}

	@Test
	void testSetCenaSaPDVNegativna() {
		assertThrows(java.lang.RuntimeException.class,
				() -> t.setCenaSaPDV(-2) );
	}
	
	@Test
	void testSetKlijent() {
		Klijent k = new Klijent();
		assertNotNull(k);
	}

	@Test
	void testSetKlijentNull() {
		assertThrows(java.lang.NullPointerException.class,
				() -> t.setKlijent(null) );
	}
	
	@Test
	void testSetTipPrevoza() {
		TipPrevoza tp = new TipPrevoza();
		assertNotNull(tp);
	}

	@Test
	void testSetTipPrevozaNull() {
		assertThrows(java.lang.NullPointerException.class,
				() -> t.setTipPrevoza(null) );
	}
	
	@Test
	void testSetAranzman() {
		Aranzman a = new Aranzman();
		assertNotNull(a);
	}
	
	@Test
	void testSetAranzmanNull() {
		assertThrows(java.lang.NullPointerException.class,
				() -> t.setAranzman(null) );
	}

}
