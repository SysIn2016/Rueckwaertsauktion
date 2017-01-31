package Sysint2016.Rueckwaertsauktion;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Sysint2016.Rueckwaertsauktion.Produkt.Produkt;

public class ProduktTest {
	Produkt pr1;
	
	@Before
	public void setUp() throws Exception {
		pr1 = new Produkt("1","1","1","1");
	}
	
	/*
	 * Test, ob die Vergabe der ProduktIDs immer nichtnegativ ist.
	 */
	@Test
	public void testGetProduktID() {
		assertEquals(0, pr1.getProduktID());
		pr1.setProduktID(Integer.MAX_VALUE);
		assertEquals(Integer.MAX_VALUE, pr1.getProduktID());
		Produkt pr2 = new Produkt("1","1","1","1");
		assertEquals(0, pr2.getProduktID());
		pr1.setProduktID(-12);
		Produkt pr3 = new Produkt("1","1","1","1");
		assertEquals(1, pr3.getProduktID());
		Produkt pr4 = new Produkt("1","1","1","1");
		assertEquals(2, pr4.getProduktID());
	}

}
