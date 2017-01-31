package Sysint2016.Rueckwaertsauktion;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Sysint2016.Rueckwaertsauktion.Nutzer.Nutzerverwaltung;

public class NutzerverwaltungIntegrationTest {
	Nutzerverwaltung nv;
	
	@Before
	public void setUp() throws Exception {
		nv = new Nutzerverwaltung();
	}

	/*
	 * Anmeldung mit bekannten Zugangsdaten
	 * Erwartetes Ergebnis: Erfolgreiche Anmeldung
	 */
	@Test
	public void testAnmelden() {
		try {
			assertEquals(true, nv.anmelden("PeterPan", "qweqweqwe"));
		}
		catch (Exception e) {
			fail("Test-Anmeldung fehlgeschlagen.");
		}
	}
}
