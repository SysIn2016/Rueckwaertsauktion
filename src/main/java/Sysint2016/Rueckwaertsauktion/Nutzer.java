package Sysint2016.Rueckwaertsauktion;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

/**
 * Diese Klasse repräsentiert einen Nutzer im System.
 * 
 */

@DynamoDBTable(tableName = "Nutzer")
public class Nutzer {
	private String vorname;
	private String nachname;
	private String username;
	private String emailadresse;
	private String passwort;
	private String kontonummer;
	private String iban;
	private String bic;
	private int id;

	/**
	 * Konstruktor für einen Nutzer
	 * 
	 * @param vorname
	 * @param nachname
	 * @param username
	 * @param emailadresse
	 * @param passwort
	 * @param kontonummer
	 * @param iban
	 * @param bic
	 */
	public Nutzer(String vorname, String nachname, String username,
			String emailadresse, String passwort, String kontonummer,
			String iban, String bic) {
		super();
		this.vorname = vorname;
		this.nachname = nachname;
		this.username = username;
		this.emailadresse = emailadresse;
		this.passwort = passwort;
		this.kontonummer = kontonummer;
		this.iban = iban;
		this.bic = bic;
	}

	@DynamoDBHashKey
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@DynamoDBAttribute
	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	@DynamoDBAttribute
	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	@DynamoDBAttribute
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@DynamoDBAttribute
	public String getEmailadresse() {
		return emailadresse;
	}

	public void setEmailadresse(String emailadresse) {
		this.emailadresse = emailadresse;
	}

	@DynamoDBAttribute
	public String getPasswort() {
		return passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	@DynamoDBAttribute
	public String getKontonummer() {
		return kontonummer;
	}

	public void setKontonummer(String kontonummer) {
		this.kontonummer = kontonummer;
	}

	@DynamoDBAttribute
	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	@DynamoDBAttribute
	public String getBic() {
		return bic;
	}

	public void setBic(String bic) {
		this.bic = bic;
	}

}
