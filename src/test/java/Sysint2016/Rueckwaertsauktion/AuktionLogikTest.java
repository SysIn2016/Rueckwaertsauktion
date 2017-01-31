package Sysint2016.Rueckwaertsauktion;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

public class AuktionLogikTest {
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
}
