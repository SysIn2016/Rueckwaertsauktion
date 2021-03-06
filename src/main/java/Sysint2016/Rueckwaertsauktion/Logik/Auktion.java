package Sysint2016.Rueckwaertsauktion.Logik;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

public class Auktion {
	private int produktID;
	/*
	 * Die Hashmap enthält die Gebote, die den Nutzern zugeordnet sind.
	 */
	private ConcurrentHashMap<String, LinkedList<Float>> gebote;
	/*
	 * Die Hashmap gibt Auskunft über alle Werte, die geboten wurden. true
	 * bedeutet, dass es nur einmal geboten wurde. false bedeutet, dass es
	 * mehrfach geboten wurde
	 */
	private ConcurrentHashMap<Float, Boolean> auswertung;

	public Auktion(int produktID) {
		gebote = new ConcurrentHashMap<String, LinkedList<Float>>();
		auswertung = new ConcurrentHashMap<Float, Boolean>();
		this.produktID = produktID;
	}

	/**
	 * Methode zum Hinzufügen eines neuen Gebots.
	 * 
	 * @param nutzername
	 * @param gebot
	 * @return gibt die Anzahl der aktuellen Gebote des Nutzers zurück
	 */
	public int neuesGebot(String nutzername, float gebot) {
		if (auswertung.containsKey(gebot)) {
			auswertung.put(gebot, false);
		} else {
			auswertung.put(gebot, true);
		}
		if (!gebote.containsKey(nutzername)) {
			gebote.put(nutzername, new LinkedList<Float>());
		}
		gebote.get(nutzername).add(gebot);
		return gebote.get(nutzername).size();
	}

	/**
	 * Methode zur Auswertung der Auktion
	 * 
	 * @return String, der durch "\t" getrennt den Nutzernamen enthält, der die
	 *         Auktion gewonnen hat und den Betrag, der geboten wurde und die ID
	 *         des Produktes
	 */
	public void auswerten(String datum) {
		Ergebnis ergebnis = null;
		float gewinnsumme = Float.MAX_VALUE;
		for (float wert : auswertung.keySet()) {
			if (auswertung.get(wert) == true) {
				if (gewinnsumme > wert) {
					gewinnsumme = wert;
				}
			}
		}
		for (String nutzer : gebote.keySet()) {
			if (gebote.get(nutzer).contains(gewinnsumme)) {
				ergebnis = new Ergebnis(produktID,nutzer,datum,gewinnsumme);
				break;
			}
		}
		if(ergebnis != null){
			ErgebnisModel model = new ErgebnisModel();
			model.speicherErgebnis(ergebnis);
		}
	}

	public int getProduktID() {
		return produktID;
	}

	/**
	 * Die Methode gibt die Anzahl der Gebote eines Nutzers, bei einer Auktion.
	 * 
	 * @param nutzername
	 * @return, Anzahl der Gebote des Nutzers
	 */
	public int getAnzahlGebote(String nutzername) {
		int anzahl = 0;
		if (gebote.containsKey(nutzername)) {
			anzahl = gebote.get(nutzername).size();
		}
		return anzahl;
	}
}
