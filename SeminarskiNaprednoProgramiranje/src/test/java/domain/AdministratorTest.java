package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class AdministratorTest {
	Administrator a;
	@BeforeEach
	void setUp() throws Exception {
		a = new Administrator();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testVratiListu() {
		ArrayList<AbstractDomainObject> lista = new ArrayList<>();
		Administrator a = new Administrator(55L, "Mladen", "Rabasovic", "mrabasovic", "mladen123");
        
        lista.add(a);
        assertEquals(a, lista.get(0));
	}

	@Test
	void testAdministrator() {
		a = new Administrator();
		assertNotNull(a);
	}

	@Test
	void testAdministratorLongStringStringStringString() {
		a = new Administrator(667L, "Mladen", "Rabasovic", "mrabasovic", "mladen123");
		assertNotNull(a);
		assertEquals(667L, a.getAdministratorID());
		assertEquals("Mladen", a.getImeAdministratora());
		assertEquals("Rabasovic", a.getPrezimeAdministratora());
		assertEquals("mrabasovic", a.getUsername());
		assertEquals("mladen123", a.getPassword());
	}

	@Test
	void testSetAdministratorID() {
		a.setAdministratorID(667L);
		assertEquals(667L, a.getAdministratorID());
	}

	@Test
	void testSetImeAdministratora() {
		a.setImeAdministratora("Mladen");
		assertEquals("Mladen", a.getImeAdministratora());
	}

	@Test
	void testSetImeAdministratoraNull() {
		assertThrows(java.lang.NullPointerException.class,
				() -> a.setImeAdministratora(null) );
	}
	
	@Test
	void testSetImeAdministratoraKratko() {
		assertThrows(java.lang.RuntimeException.class,
				() -> a.setImeAdministratora("M") );
	}
	
	@Test
	void testSetPrezimeAdministratora() {
		a.setPrezimeAdministratora("Rabasovic");
		assertEquals("Rabasovic", a.getPrezimeAdministratora());
	}
	
	@Test
	void testSetPrezimeAdministratoraNull() {
		assertThrows(java.lang.NullPointerException.class,
				() -> a.setPrezimeAdministratora(null) );
	}
	
	@Test
	void testSetPrezimeAdministratoraKratko() {
		assertThrows(java.lang.RuntimeException.class,
				() -> a.setPrezimeAdministratora("R") );
	}

	@Test
	void testSetUsername() {
		a.setUsername("mrabasovic");
		assertEquals("mrabasovic", a.getUsername());
	}

	@Test
	void testSetUsernameNull() {
		assertThrows(java.lang.NullPointerException.class,
				() -> a.setUsername(null) );
	}
	
	@Test
	void testSetPassword() {
		a.setPassword("mladen123");
		assertEquals("mladen123", a.getPassword());
	}

	@Test
	void testSetPasswordNull() {
		assertThrows(java.lang.NullPointerException.class,
				() -> a.setPassword(null) );
	}
	
	@Test
	void testSetPasswordKratka() {
		assertThrows(java.lang.RuntimeException.class,
				() -> a.setPassword("abc") );
	}
	
	@Test
	void testToString() {
		a.setImeAdministratora("Mladen");
		a.setPrezimeAdministratora("Rabasovic");
		String s = a.toString();
		assertTrue(s.contains("Mladen"));
		assertTrue(s.contains("Rabasovic"));
	}

	@ParameterizedTest
	@CsvSource({
		"Mladen, Rabasovic, Mladen, Rabasovic, true",
		"Mladen, Milanovic, Mladen, Rabasovic, false",
		"Mladen, Rabasovic, Milan, Markovic, false",
		"Mladen, Markovic, Marko, Milanovic, false"
	})
	void testEqualsObject(String ime1, String prezime1, String ime2, String prezime2, boolean eq) {
		a.setImeAdministratora(ime1);
		a.setPrezimeAdministratora(prezime1);
		
		Administrator a2 = new Administrator();
		a2.setImeAdministratora(ime2);
		a2.setPrezimeAdministratora(prezime2);
		
		assertEquals(eq, a.equals(a2));
	}

}
