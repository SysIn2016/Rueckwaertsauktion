package Sysint2016.Rueckwaertsauktion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import javax.naming.directory.Attribute;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;

/**
 * Diese Klasse dient der Anmeldung der Kunden. Sie ist mit einem Dirctory
 * Service verbunden.
 * 
 */
public class Nutzerverwaltung {

	private NutzerModel nm;
	private NutzerDao ndao;
	final static String ADMIN_NAME = "cn=Manager,dc=sysin,dc=de";
	final static String ADMIN_PASSWORD = "M4rioBRB";
	final static String SEARCH_BASE = "ou=Users,dc=sysin,dc=de";

	static LdapContext ctx;

	public Nutzerverwaltung() {
		
	}

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

	public boolean anmelden(String nutzername, String passwort) throws Exception {
		boolean erfolgreich = false;

		init();

		Hashtable credentials = (Hashtable) ctx.getEnvironment().clone();
		credentials.put(Context.SECURITY_PRINCIPAL, "uid=" + nutzername + ",ou=Users,dc=sysin,dc=de");
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
	 * Diese Methode dient der Registrierung eines Kunden.
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
	 * @throws Exception
	 */
	public boolean registrieren(String vorname, String nachname, String username, String emailadresse, String passwort,
			String kontonummer, String iban, String bic) throws Exception {
		
		nm = new NutzerModel();
		ndao = nm;
		
		boolean ergebnis = true;
		
		init();

		String entryDN = "uid=" + username + ",ou=Users,dc=sysin,dc=de";

		Attribute cn = new BasicAttribute("cn", vorname + " " + nachname);
		Attribute sn = new BasicAttribute("sn", nachname);
		Attribute givenName = new BasicAttribute("givenName", vorname);
		Attribute mail = new BasicAttribute("mail", emailadresse);
		Attribute password = new BasicAttribute("userPassword", passwort);
		Attribute oc = new BasicAttribute("objectClass", "inetOrgPerson");

		try {

			// Eintrag zusammensetzen
			BasicAttributes entry = new BasicAttributes();
			entry.put(cn);
			entry.put(sn);
			entry.put(mail);
			entry.put(givenName);
			entry.put(password);

			entry.put(oc);

			ctx.createSubcontext(entryDN, entry);
			Nutzer nutzer = new Nutzer(username, kontonummer, iban, bic);
			ndao.speicherNutzer(nutzer);

		} catch (NamingException e) {
			System.err.println("AddUser: error adding entry." + e);
			ergebnis = false;
		}

		return ergebnis;
	}

	/**
	 * Methode zum Senden einer E-Mail zur Bestaetigung der Registrierung.
	 * 
	 * @param emailadresse
	 *            , E-Mailadresse des neuen Nutzers
	 */
	public void sendeRegistrationsmail(String benutzername) throws IOException {
		File credentials1 = new File("src/main/Webseite/Credentials.txt");
		String line = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(credentials1));
			line = br.readLine();
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AWSCredentials credentials = new BasicAWSCredentials(
				line.split(",")[0], line.split(",")[1]);

		
		String mailFrom = "der_kakerlak@web.de";
		String mailTo = "der_kakerlak@web.de";
		String mailBody = "Hallo " + benutzername
				+ "!\n\n Herzlich willkommen bei der SysIn Rueckwaertsauktion!\n\n Viel Spass beim Bieten.\n\nDiese E-Mail wurde mit Amazon SES versendet.";
		String mailSubject = "Registrierung bei SysIn-Rueckwaertsauktion";

		Destination destination = new Destination().withToAddresses(new String[] { mailTo });

		Content subject = new Content().withData(mailSubject);
		Content textBody = new Content().withData(mailBody);
		Body body = new Body().withText(textBody);

		Message message = new Message().withSubject(subject).withBody(body);

		SendEmailRequest request = new SendEmailRequest().withSource(mailFrom).withDestination(destination)
				.withMessage(message);
		

		try {

			AmazonSimpleEmailServiceClient client = new AmazonSimpleEmailServiceClient(credentials);

			Region REGION = Region.getRegion(Regions.US_WEST_2);
			client.setRegion(REGION);

			client.sendEmail(request);
		} catch (Exception ex) {
			System.out.println("E-Mail wurde nicht gesendet");
			System.out.println("Error message: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	/**
	 * Verbindung zum LDAP Service initialisieren, die zum Zugriff
	 * notwendigen Daten in einer Hashtable ablegen und an
	 * den InitialLdapContext uebergeben, der mit den darin enthaltenen Informationen die
	 * Verbindung aufbaut.
	 * 
	 * @throws Exception
	 */
	static void init() throws Exception {
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, ADMIN_NAME);
		env.put(Context.SECURITY_CREDENTIALS, ADMIN_PASSWORD);
		env.put(Context.PROVIDER_URL, "ldap://52.24.248.222:389");
		ctx = new InitialLdapContext(env, null);
	}
}