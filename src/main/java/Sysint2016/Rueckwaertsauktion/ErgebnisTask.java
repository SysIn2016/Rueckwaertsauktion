package Sysint2016.Rueckwaertsauktion;

import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Diese Klasse soll das auswerten antriggern.
 * 
 * @author arbeit
 * 
 */
public class ErgebnisTask extends TimerTask {

	private ConcurrentHashMap<String, Auktion> auktionsliste;

	/**
	 * Der Konstruktor uss die Auktionsliste übergeben bekommen. Aus dieser kann
	 * er sich dann die Auktion raussuchen, die ausgewertet werden muss.
	 * 
	 * @param auktionsliste
	 */
	ErgebnisTask(ConcurrentHashMap<String, Auktion> auktionsliste) {
		this.auktionsliste = auktionsliste;
	}

	public void run() {
		String datum = AuktionLogik.getDatumAlsString(null);
		if (auktionsliste.containsKey(datum)) {
			auktionsliste.get(datum).auswerten(datum);
			auktionsliste.remove(datum);
		}
	}
}
