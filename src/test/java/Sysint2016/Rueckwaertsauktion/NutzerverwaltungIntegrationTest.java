package Sysint2016.Rueckwaertsauktion;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class NutzerverwaltungTest {
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

	/*
	 * Registrierungsversuch mit bereits vorhandenem Nutzernamen
	 * Erwartetes Ergebnis: Es erfolgt keine neue Registrierung
	 */
	@Test
	public void testRegistrieren() {
		try {
			assertEquals(false, nv.registrieren("Peter", "Pan", "PeterPan", "email@adres.se", "passwort", "123", "123", "123"));
		}
		catch (Exception e) {
			fail("Test-Registrierung fehlgeschlagen.");
		}
	}
}
