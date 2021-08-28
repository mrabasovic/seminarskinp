package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VodicTest {

	Vodic v;

	@BeforeEach
	void setUp() throws Exception {
		v = new Vodic();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testVratiListu() {
		ArrayList<AbstractDomainObject> lista = new ArrayList<>();
		Vodic v = new Vodic(123L, "Mladen", "Rabasovic", "0607272919", "mladen@gmail", 5);
        
        lista.add(v);
        assertEquals(v, lista.get(0));
	}


	@Test
	void testVodic() {
		v = new Vodic();
		assertNotNull(v);
	}

	@Test
	void testVodicLongStringStringStringStringInt() {
		v = new Vodic(3351L, "Milan", "Milinkovic", "0607272919","milan@turs.com",6);
		assertNotNull(v);
		assertEquals(3351L, v.getVodicID());
		assertEquals("Milan",v.getIme());
		assertEquals("Milinkovic", v.getPrezime());
		assertEquals("0607272919",v.getBrojTelefona());
		assertEquals("milan@turs.com",v.getEmail());
		assertEquals(6,v.getGodineIskustva());
	}

	@Test
	void testSetVodicID() {
		v.setVodicID(12355L);
		assertEquals(12355L, v.getVodicID());
	}

	@Test
	void testSetVodicIDNull() {
		assertThrows(java.lang.NullPointerException.class,
				() -> v.setVodicID(null) );
	}
	
	@Test
	public void testSetIme() {
		v.setIme("Mladen");
		assertEquals("Mladen",v.getIme());
	}

	@Test
	void testSetImeNull() {
		assertThrows(java.lang.NullPointerException.class,
				() -> v.setIme(null) );
	}
	
	@Test
	void testSetImeKratakString() {
		assertThrows(java.lang.RuntimeException.class,
				() -> v.setIme("A") );
	}

	@Test
	void testSetPrezime() {
		v.setPrezime("Radenovic");
		assertEquals("Radenovic", v.getPrezime());
	}
	
	@Test
	void testSetPrezimeNull() {
		assertThrows(java.lang.NullPointerException.class,
				() -> v.setPrezime(null) );
	}
	
	@Test
	void testSetPrezimeKratakString() {
		assertThrows(java.lang.RuntimeException.class,
				() -> v.setPrezime("A") );
	}

	@Test
	void testSetBrojTelefona() {
		v.setBrojTelefona("0607272919");
		assertEquals("0607272919", v.getBrojTelefona());
	}

	@Test
	void testSetBrojTelefonaNema10() {
		assertThrows(java.lang.RuntimeException.class,
				() -> v.setEmail("060727291") );
	}
	
	@Test
	void testSetEmail() {
		v.setEmail("milan@gmail.com");
		assertEquals("milan@gmail.com", v.getEmail());
	}

	@Test
	void testSetEmailNull() {
		assertThrows(java.lang.NullPointerException.class,
				() -> v.setEmail(null) );
	}
	
	@Test
	void testSetEmailNemaAt() {
		assertThrows(java.lang.RuntimeException.class,
				() -> v.setEmail("milangmail.com") );
	}
	
	@Test
	void testSetGodineIskustva() {
		v.setGodineIskustva(7);
		assertEquals(7, v.getGodineIskustva());
	}
	
	@Test
	void testSetGodineIskustvaNegativan() {
		assertThrows(java.lang.RuntimeException.class,
				() -> v.setGodineIskustva(-2) );
	}

	@ParameterizedTest
	@CsvSource({
		"Marko, Markovic, Marko, Markovic, true",
		"Milan, Milanovic, Marko, Milanovic, false",
		"Marko, Milanovic, Milan, Markovic, false",
		"Marko, Markovic, Marko, Milanovic, false"
	})
	void testEqualsObject(String ime1, String prezime1, String ime2, String prezime2, boolean eq) {
		v.setIme(ime1);
		v.setPrezime(prezime1);
		
		Vodic v2 = new Vodic();
		v2.setIme(ime2);
		v2.setPrezime(prezime2);
		
		assertEquals(eq, v.equals(v2));
	}

	@Test
	void testToString() {
		v.setIme("Milan");
		v.setPrezime("Milanovic");
		
		String s = v.toString();
		assertTrue(s.contains("Milan"));
		assertTrue(s.contains("Milanovic"));
	}

}
