package Sysint2016.Rueckwaertsauktion;

/**
 * Diese Klasse stellt ein Produkt dar.
 * 
 * @author Mario Kaulmann
 * 
 */
public class Produkt {
	private static int naechsteID;

	private int produktID;
	private String produktname;
	private String produktbeschreibung;
	/*
	 * Die KundenID des Einstellers des Produkts
	 */
	private int einstellerID;
	/*
	 * ID des Bildes zum Produkt.
	 */
	private int bildID;

	static {
		naechsteID = 0;
	}

	/**
	 * Konstrukter eines Produkts
	 * 
	 * @param produktname
	 * @param produktbeschreibung
	 * @param einstellerID
	 * @param bildID
	 */
	public Produkt(String produktname, String produktbeschreibung,
			int einstellerID, int bildID) {
		setProduktname(produktname);
		setProduktbeschreibung(produktbeschreibung);
		setEinstellerID(einstellerID);
		setBildID(bildID);
		generiereProduktID();
	}

	/**
	 * Diese Methode generiert eine ID fuer das Produkt. Sie soll nur in
	 * Konstruktoren zum Einsatz kommen. Weil die Gefahr besteht, dass mehrere
	 * Threads gleichzeitig auf diese Methode zugreifen wolen ist diese
	 * synchronized.
	 */
	private synchronized void generiereProduktID() {
		this.produktID = naechsteID++;
		/*
		 * Die IDs sollen nicht negativ werden. Sollte die Variable naechsteID
		 * den Sprung von der groessten zur kleinsten Integerzahl machen, wird
		 * sie auf 0 zurueckgesetzt.
		 */
		if (naechsteID < 0) {
			naechsteID = 0;
		}
	}

	public String getProduktname() {
		return produktname;
	}

	public void setProduktname(String produktname) {
		this.produktname = produktname;
	}

	public String getProduktbeschreibung() {
		return produktbeschreibung;
	}

	public void setProduktbeschreibung(String produktbeschreibung) {
		this.produktbeschreibung = produktbeschreibung;
	}

	public int getEinstellerID() {
		return einstellerID;
	}

	public void setEinstellerID(int einstellerID) {
		this.einstellerID = einstellerID;
	}

	public int getBildID() {
		return bildID;
	}

	public void setBildID(int bildID) {
		this.bildID = bildID;
	}

	public int getProduktID() {
		return produktID;
	}
}