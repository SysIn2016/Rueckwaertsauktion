package Sysint2016.Rueckwaertsauktion;

import java.io.IOException;
import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import java.util.jar.Attributes;
import javax.naming.directory.Attribute;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
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
		credentials.put(Context.SECURITY_PRINCIPAL, "uid=" + nutzername
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
	 * @throws Exception 
	 */
	public boolean registrieren(String vorname, String nachname,
			String username, String emailadresse, String passwort,
			String kontonummer, String iban, String bic) throws Exception {
		boolean ergebnis = true;
		/*
		 * Hier muss der code zur pruefung hin, ob der Username bereits vergeben
		 * ist, und das eventuelle Anlegen des neuen Nutzers.
		 */
		init();
		
        String entryDN = "uid=" + username + ",ou=Users,dc=sysin,dc=de";

	    Attribute cn = new BasicAttribute("cn", vorname + " " + nachname);  
	    Attribute sn = new BasicAttribute("sn", nachname);
	    Attribute givenName = new BasicAttribute("givenName", vorname);
	    Attribute mail = new BasicAttribute("mail", emailadresse);
	    Attribute password = new BasicAttribute("userPassword", passwort);
	    Attribute oc = new BasicAttribute("objectClass", "inetOrgPerson"); 
	    //DirContext ctx = null;  
		
		try {  
			
			// build the entry  
			BasicAttributes entry = new BasicAttributes();  
			entry.put(cn);  
			entry.put(sn);  
			entry.put(mail);  
			entry.put(givenName);
			entry.put(password);
			
			entry.put(oc); 
			
			ctx.createSubcontext(entryDN, entry);
		
		} catch (NamingException e) {  
			System.err.println("AddUser: error adding entry." + e);
			ergebnis = false;
		}
		
		return ergebnis;
	}

	/**
	 * Methode zum Senden einer E-Mail zur bestaetigung der Registrierung.
	 * 
	 * @param emailadresse
	 *            , E-Mailadresse des neuen Nutzers
	 */
	public void sendeRegistrationsmail(String benutzername) throws IOException {
		
		// Welche Adresse?
		String mailFrom = "der_kakerlak@web.de";
		String mailTo = "der_kakerlak@web.de";
		String mailBody = "Hallo " + benutzername + "!\n\n Herzlich willkommen bei der SysIn Rueckwaertsauktion!\n\n Viel Spass beim Bieten.\n\nDiese E-Mail wurde mit Amazon SES versendet.";
		String mailSubject = "Registrierung bei SysIn-Rueckwaertsauktion";
		
		Destination destination = new Destination().withToAddresses(new String[]{mailTo});
		
		Content subject = new Content().withData(mailSubject);
        Content textBody = new Content().withData(mailBody); 
        Body body = new Body().withText(textBody);
        
        Message message = new Message().withSubject(subject).withBody(body);
        
        SendEmailRequest request = new SendEmailRequest().withSource(mailFrom).withDestination(destination).withMessage(message);
        
        try
        {    
	        
            AmazonSimpleEmailServiceClient client = new AmazonSimpleEmailServiceClient();
            
            // Region anpassen?
            Region REGION = Region.getRegion(Regions.US_WEST_2);
            client.setRegion(REGION);
       
            client.sendEmail(request);
        }
        catch (Exception ex) 
        {
            System.out.println("E-Mail wurde nicht gesendet");
            System.out.println("Error message: " + ex.getMessage());
            ex.printStackTrace();
        }
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