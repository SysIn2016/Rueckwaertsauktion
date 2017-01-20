package Sysint2016.Rueckwaertsauktion;

/**
 * Diese Klasse dient der Anmeldung der Kunden. Sie ist mit einem Dirctory
 * Service verbunden.
 * 
 */
public class Nutzerverwaltung {

	/**
	 * Diese Methode dient der Anmeldung eines Kunden.
	 * 
	 * @param nutzername
	 *            , Nutzername des Kunden
	 * @param passwort
	 *            , Passwort des Kunden
	 * @return true, Nutzerdaten sind gueltig <br>
	 *         false, Nutzerdaten sind nicht gueltig
	 */
	public boolean anmelden(String nutzername, String passwort) {
		boolean erfolgreich = false;
		if ("Olaf Obst".equals(nutzername) && "Passwort1".equals(passwort)) {
			erfolgreich = true;
		}
		return erfolgreich;
	}

	/**
	 * In dieser Methode muss geprueft werden, ob der Nutzername nicht evtl.
	 * bereits vergeben ist. Sollte das der Fall sein, wird kein neuer Benutzer
	 * angelegt und false zuruekcgegeben.
	 * 
	 * @param vorname
	 * @param nachname
	 * @param username
	 * @param emailadresse
	 * @param passwort
	 * @param kontonummer
	 * @param iban
	 * @param bic
	 * @return true: der Benutzer wurde angelegt,<br>
	 *         false: der Benutzer wurde nicht angelegt
	 */
	public boolean registrieren(String vorname, String nachname,
			String username, String emailadresse, String passwort,
			String kontonummer, String iban, String bic) {
		boolean ergebnis = true;
		/*
		 * Hier muss der code zur pruefung hin, ob der Username bereits vergeben
		 * ist, und das eventuelle Anlegen des neuen Nutzers.
		 */
		return ergebnis;
	}

	/**
	 * Methode zum Senden einer E-Mail zur bestaetigung der Registrierung.
	 * 
	 * @param emailadresse
	 *            , E-Mailadresse des neuen Nutzers
	 */
	public void sendeRegistrationsmail(String emailadresse, String benutzername) {
		System.out.println(emailadresse);
	}
}
