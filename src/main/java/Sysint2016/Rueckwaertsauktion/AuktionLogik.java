package Sysint2016.Rueckwaertsauktion;

import java.util.Date;
import java.util.LinkedList;
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

	private ProduktModel model;
	private Produktverwaltung produktverwaltung;

	static {
		auktionsliste = new ConcurrentHashMap<String, Auktion>();
		// produktverwaltung = new Produktverwaltung();
	}

	public AuktionLogik() {
		model = new ProduktModel();
		this.produktverwaltung = model;
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
	public static boolean setzeAuktion(String datum, int produktID) {
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
		Produkt produkt = new Produkt(produktname, produktbeschreibung,
				einstellerID, bildID);
		produktverwaltung.speicherProdukt(produkt);

		/*
		 * Nur Übergangslösung
		 */
		Date datum = new Date();
		String heute = getDatumAlsString(null);
		while (auktionsliste.containsKey(heute)) {
			datum.setDate(datum.getDate() + 1);
			heute = getDatumAlsString(datum);
		}
		auktionsliste.put(heute, new Auktion(produkt.getProduktID()));
	}

	/**
	 * Die Funktion gibt die naechsten 10 Auktionen aus, die in der Liste der
	 * Auktionen anstehen.
	 * 
	 * @return, Liste der Auktionen nach dem Muster <Datum>\t<Produkt>
	 */
	public LinkedList<String> gibNaechsteZehn() {
		LinkedList<Date> datumsliste = new LinkedList<Date>();
		LinkedList<String> ergListe = new LinkedList<String>();
		for (String datum : auktionsliste.keySet()) {
			Date d = new Date(Integer.parseInt(datum.split("\\.")[2]) - 1900,
					Integer.parseInt(datum.split("\\.")[1]) - 1,
					Integer.parseInt(datum.split("\\.")[0]));
			boolean added = false;
			for (Date da : datumsliste) {
				if (d.before(da)) {
					ergListe.add(datumsliste.indexOf(da), datum + "\t"
							+ auktionsliste.get(datum).getProduktID());
					datumsliste.add(datumsliste.indexOf(da), d);
					if (datumsliste.size() > 10) {
						datumsliste.remove(10);
						ergListe.remove(10);
					}
					added = true;
					break;
				}
			}
			/*
			 * Deckt auch den Fall ab, dass die Liste noch leer ist
			 */
			if (!added) {
				datumsliste.add(d);
				ergListe.add(datum + "\t"
						+ auktionsliste.get(datum).getProduktID());
				if (datumsliste.size() > 10) {
					datumsliste.remove(10);
					ergListe.remove(10);
				}
			}
		}
		return ergListe;
	}

	public int gebeGebotAb(String nutzername, float gebot) {
		String heute = getDatumAlsString(null);
		return auktionsliste.get(heute).neuesGebot(nutzername, gebot);
	}

	public static ConcurrentHashMap<String, Auktion> getAuktionsliste() {
		return auktionsliste;
	}

	/**
	 * Methode zur einheitlichen Erstellung des Zeitstempels.
	 * 
	 * @param datum
	 *            , Datum, das zum String werden soll <br>
	 *            Wenn null, dann wird das aktuelle Datum genommen
	 * @return Stringrepräsentation des Datums
	 */
	public String getDatumAlsString(Date datum) {
		if (datum == null) {
			datum = new Date();
		}
		String tag = String.valueOf(datum.getDate());
		if (tag.length() == 1) {
			tag = '0' + tag;
		}
		String monat = String.valueOf(datum.getMonth() + 1);
		if (monat.length() == 1) {
			monat = '0' + monat;
		}
		String jahr = String.valueOf(datum.getYear() + 1900);

		String heute = tag + "." + monat + "." + jahr;
		return heute;
	}
}
