package Sysint2016.Rueckwaertsauktion;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AuktionLogikTest {
	AuktionLogik logik;

	@Before
	public void setUp() throws Exception {
		logik = new AuktionLogik();
	}

	@Test
	public void testGetDatumAlsString() {
		Date d;
		DateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
		try {
			d = dateFormatter.parse("05.01.2017");
			Assert.assertEquals("05.01.2017", AuktionLogik.getDatumAlsString(d));
		} catch (ParseException e) {
			// Das muss soweit klappen
			fail("Der Test wurde falsch geschrieben.");
		}
	}

	/*
	 * Testen, ob die Funktion true und FAlse richtig zurückgibt.
	 */
	@Test
	public void testSetzeAuktion() {
		Assert.assertEquals(true, logik.setzeAuktion("05.01.2017", 0));
		Assert.assertEquals(false, logik.setzeAuktion("05.01.2017", 1));
		Assert.assertEquals(true, logik.setzeAuktion("06.01.2017", 1));
	}

	/*
	 * Testen, der Rückgabe, bei der Methode getProduktID
	 */
	@Test
	public void testGetProduktID() {
		Assert.assertEquals(-1, logik.getProduktID("29.01.2017"));
		logik.setzeAuktion("29.01.2017", 1);
		Assert.assertEquals(1, logik.getProduktID("29.01.2017"));
	}

	/*
	 * Test, ob wenn es keine Auktionen für den heutigen Tag gibt -1
	 * zurückkommt.
	 */
	@Test
	public void testGebeGebotAb() {
		Assert.assertEquals(-1, logik.gebeGebotAb("Olaf", 1.67f));
	}

}
