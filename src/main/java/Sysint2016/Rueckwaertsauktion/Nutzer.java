package Sysint2016.Rueckwaertsauktion;

/**
 * Diese Klasse repräsentiert einen Nutzer im System.
 * 
 */
public class Nutzer {
	private String vorname;
	private String nachname;
	private String username;
	private String emailadresse;
	private String passwort;
	private String kontonummer;
	private String iban;
	private String bic;

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

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmailadresse() {
		return emailadresse;
	}

	public void setEmailadresse(String emailadresse) {
		this.emailadresse = emailadresse;
	}

	public String getPasswort() {
		return passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	public String getKontonummer() {
		return kontonummer;
	}

	public void setKontonummer(String kontonummer) {
		this.kontonummer = kontonummer;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getBic() {
		return bic;
	}

	public void setBic(String bic) {
		this.bic = bic;
	}

}
