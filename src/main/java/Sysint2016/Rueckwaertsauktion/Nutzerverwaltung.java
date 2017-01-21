package Sysint2016.Rueckwaertsauktion;

import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

/**
 * Diese Klasse dient der Anmeldung der Kunden. Sie ist mit einem Dirctory
 * Service verbunden.
 * 
 */
public class Nutzerverwaltung {

	// Der Vollqualifizierte Name des Administrators im AD
	final static String ADMIN_NAME = "cn=Manager,dc=sysin,dc=de";
	final static String ADMIN_PASSWORD = "M4rioBRB";
	// User Standardpfad von dem die Suche im AD ausgehen soll
	final static String SEARCH_BASE = "ou=Users,dc=sysin,dc=de";

	static LdapContext ctx;

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
	public boolean anmelden(String nutzername, String passwort)
			throws Exception {
		boolean erfolgreich = false;

		init();

		Hashtable credentials = (Hashtable) ctx.getEnvironment().clone();
		credentials.put(Context.SECURITY_PRINCIPAL, "cn=" + nutzername
				+ ",ou=Users,dc=sysin,dc=de");
		credentials.put(Context.SECURITY_CREDENTIALS, passwort);
		try {
			DirContext dirContext = new InitialDirContext(credentials);
			erfolgreich = true;
		} catch (Exception e) {
			/*
			 * Eine Exception bedeutet, dass kein Nutzer mit dem namen gefunden
			 * werden konnte, oder dass das Passwort nicht stimmt.
			 */
			erfolgreich = false;
		}

		ctx.close();

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

	/**
	 * Wir initialisieren die Verbindung zum LDAP Service Die zum Zugriff
	 * notwendigen Daten legen wir in einer Hashtable ab und übergeben diese an
	 * den InitialLdapContext, der mit den darin enthaltenen Informationen die
	 * Verbindung aufbaut.
	 * 
	 * @throws Exception
	 */
	static void init() throws Exception {
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, ADMIN_NAME);
		env.put(Context.SECURITY_CREDENTIALS, ADMIN_PASSWORD);
		// Der entsprechende Domänen-Controller
		env.put(Context.PROVIDER_URL, "ldap://52.24.248.222:389");
		ctx = new InitialLdapContext(env, null);
	}
}