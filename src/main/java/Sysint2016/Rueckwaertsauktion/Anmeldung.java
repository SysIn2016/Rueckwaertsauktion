package Sysint2016.Rueckwaertsauktion;

/**
 * Diese Klasse dient der Anmeldung der Kunden. Sie ist mit einem Dirctory
 * Service verbunden.
 * 
 */
public class Anmeldung {

	/**
	 * Diese Methode dient der Anmeldung eines Kunden.
	 * 
	 * @param name
	 *            , Nutzername des Kunden
	 * @param passwort
	 *            , Passwort des Kunden
	 * @return true, Nutzerdaten sind gueltig <br>
	 *         false, Nutzerdaten sind nicht gueltig
	 */
	public boolean anmelden(String name, String passwort) {
		boolean erfolgreich = false;
		if ("Olaf Obst".equals(name) && "Passwort1".equals(passwort)) {
			erfolgreich = true;
		}
		return erfolgreich;
	}
}
