package domain;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TipPrevozaTest {
	TipPrevoza tp;
	
	@BeforeEach
	void setUp() throws Exception {
		tp = new TipPrevoza();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testVratiListu() {
		ArrayList<AbstractDomainObject> lista = new ArrayList<>();
		TipPrevoza tp = new TipPrevoza(1L, "Autobus");
        
        lista.add(tp);
        assertEquals(tp, lista.get(0));
	}
	
	@Test
	void testTipPrevoza() {
		tp = new TipPrevoza();
		assertNotNull(tp);
	}

	@Test
	void testTipPrevozaLongString() {
		tp = new TipPrevoza(553L, "Autobuski prevoz");
		assertNotNull(tp);
		assertEquals(553L, tp.getTipPrevozaID());
		assertEquals("Autobuski prevoz", tp.getOpis());
	}

	@Test
	void testSetTipPrevozaID() {
		tp.setTipPrevozaID(552L);
		assertEquals(552L, tp.getTipPrevozaID());
	}
	
	@Test
	void testSetTipPrevozaIDNull() {
		assertThrows(java.lang.NullPointerException.class,
				() -> tp.setTipPrevozaID(null) );
	}

	@Test
	void testSetOpis() {
		tp.setOpis("Tip prevoza je avion");
		assertEquals("Tip prevoza je avion", tp.getOpis());
	}
	
	@Test
	void testSetOpisNull() {
		assertThrows(java.lang.NullPointerException.class,
				() -> tp.setOpis(null) );
	}

	@Test
	void testToString() {
		tp.setOpis("Sopstveni prevoz");
		String s = tp.toString();
		assertTrue(s.contains("Sopstveni prevoz"));
	}

}
