package Sysint2016.Rueckwaertsauktion;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Diese Klasse enthaelt die Auktionslogik. <br>
 * D.h. die Verwaltung der Produktlisten, die Methoden zum Einstellen eines
 * neuen Produkts, die Methoden zum abgeben eines Gebots
 * 
 * @author arbeit
 * 
 */
public class AuktionLogik {

	/*
	 * HashMap, die die Produkte enthaelt, die Versteigert werden sollen. <br>
	 * Der Schluessel ist das Datum an dem das Produkt versteigert werden soll.
	 */
	private static ConcurrentHashMap<String, Auktion> auktionsliste;

	private static Produktverwaltung produktverwaltung;

	static {
		auktionsliste = new ConcurrentHashMap<String, Auktion>();
		produktverwaltung = new Produktverwaltung();
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
			auktionsliste.put(datum, new Auktion(produktID));
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
			produktID = auktionsliste.get(datum).getProduktID();
		}
		return produktID;
	}

	/**
	 * Diese Methode wird benutzt um ein neues Produkt zu speichern.
	 * 
	 * @param produktname
	 * @param produktbeschreibung
	 * @param einstellerID
	 * @param bildID
	 */
	public void erzeugeProdukt(String produktname, String produktbeschreibung,
			String einstellerID, String bildID) {
		produktverwaltung.speicherProdukt(new Produkt(produktname,
				produktbeschreibung, einstellerID, bildID));
	}

	public int gebeGebotAb(String nutzername, float gebot) {
		Date datum = new Date();
		String heute = datum.getDate() + "." + (datum.getMonth() + 1) + "."
				+ (datum.getYear() + 1900);
		return auktionsliste.get(heute).neuesGebot(nutzername, gebot);
	}
}
