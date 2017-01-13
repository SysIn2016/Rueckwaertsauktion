package Sysint2016.Rueckwaertsauktion;

import java.util.concurrent.ConcurrentHashMap;

public class AuktionLogik {

	/*
	 * HashMap, die die Produkte enthaelt, die Versteigert werden sollen. <br>
	 * Der Schluessel ist das Datum an dem das Produkt versteigert werden soll.
	 */
	private static ConcurrentHashMap<String, Integer> auktionsliste;

	static {
		auktionsliste = new ConcurrentHashMap<String, Integer>();
	}

	/**
	 * Methode zum hinzufuegen eines Produkts in die Liste der Auktionen.
	 * 
	 * @param datum
	 *            , Datum an dem die Auktion des Produkts stattfinden soll
	 * @param produktID
	 *            , ID des Produktes, das versteigert werden soll
	 * @return true, wenn das Produkt eingefuegt werden konnte, da an dem Tag
	 *         kein anderes Produkt angeboten werden soll<br>
	 *         false, das Produkt konnte nicht eingefuegt werden, da bereits ein
	 *         anderes Produkt an dem Tag versteigert werden soll
	 */
	public static boolean setProdukt(String datum, int produktID) {
		boolean eingefuegt = false;
		if (!auktionsliste.containsKey(datum)) {
			auktionsliste.put(datum, produktID);
			eingefuegt = true;
		}
		return eingefuegt;
	}

	/**
	 * Methode, die die ProduktID des Produkts zurueckgibt, das an einem
	 * bestimmten Datum versteigert werden soll.
	 * 
	 * @param datum
	 *            , Datum der Versteigerung
	 * @return ProduktID des an dem Datum zu versteigernden Produkts<br>
	 *         -1 symbolisiert, dass kein Produkt an dem entsprechenden Datum
	 *         versteigert werden soll.
	 */
	public static int getProduktID(String datum) {
		int produktID = -1;
		if (auktionsliste.containsKey(datum)) {
			produktID = auktionsliste.get(datum);
		}
		return produktID;
	}
}
