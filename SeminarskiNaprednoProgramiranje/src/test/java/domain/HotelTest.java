package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HotelTest {

	Hotel h;
	@BeforeEach
	void setUp() throws Exception {
		h = new Hotel();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testVratiListu() {
		ArrayList<AbstractDomainObject> lista = new ArrayList<>();
		Hotel h = new Hotel(77L, "Iberostar", "Becici");
        
        lista.add(h);
        assertEquals(h, lista.get(0));
	}

	@Test
	void testHotel() {
		h = new Hotel();
		assertNotNull(h);
	}

	@Test
	void testHotelLongStringString() {
		h = new Hotel(123L, "Hilton", "Beograd");
		assertNotNull(h);
		assertEquals(123L, h.getHotelID());
		assertEquals("Hilton", h.getNazivHotela());
		assertEquals("Beograd", h.getGrad());
	}

	@Test
	void testSetHotelID() {
		h.setHotelID(123L);
		assertEquals(123L, h.getHotelID());
	}

	@Test
	void testSetHotelIDNull() {
		assertThrows(java.lang.NullPointerException.class,
				() -> h.setHotelID(null) );
	}
	
	@Test
	void testSetNazivHotela() {
		h.setNazivHotela("Hilton");
		assertEquals("Hilton", h.getNazivHotela());
	}

	@Test
	void testSetNazivHotelaNull() {
		assertThrows(java.lang.NullPointerException.class,
				() -> h.setNazivHotela(null) );
	}
	
	@Test
	void testSetGrad() {
		h.setGrad("Beograd");
		assertEquals("Beograd", h.getGrad());
	}

	@Test
	void testSetGradNull() {
		assertThrows(java.lang.NullPointerException.class,
				() -> h.setGrad(null) );
	}
	
	@Test
	void testToString() {
		h.setNazivHotela("Hilton");
		String s = h.toString();
		assertTrue(s.contains("Hilton"));
	}

}
