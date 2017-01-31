package Sysint2016.Rueckwaertsauktion;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AuktionTest {
	Auktion auktion;
	
	@Before
	public void setUp() throws Exception {
		auktion = new Auktion(1);
	}

	/*
	 * Test, ob die Anzahl der abgegebenen Gebote richtig erfasst wird.
	 */
	@Test
	public void testNeuesGebot() {
		assertEquals(1, auktion.neuesGebot("Harald", 4.5f));
		assertEquals(2, auktion.neuesGebot("Harald", 1.5f));
		assertEquals(3, auktion.neuesGebot("Harald", 2.5f));
		assertEquals(4, auktion.neuesGebot("Harald", 4.4f));
		assertEquals(1, auktion.neuesGebot("Stefan", 4.4f));
	}
	
	@Test
	public void testGetAnzahlGebote() {
		assertEquals(0, auktion.getAnzahlGebote("Harald"));
		assertEquals(0, auktion.getAnzahlGebote("Sebastian"));
		auktion.neuesGebot("Harald", 4.5f);
		assertEquals(1, auktion.getAnzahlGebote("Harald"));
	}

}
