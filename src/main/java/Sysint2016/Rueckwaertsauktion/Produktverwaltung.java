package Sysint2016.Rueckwaertsauktion;

/**
 * Diese Klasse enthaelt die Logik zum Verwalten der Produkte. <br>
 * D.h. Produkte in der Datenbank ablegen, Produkte aus der Datenbank holen,
 * Produkte aus der Datenbank loeschen
 * 
 * @author arbeit
 * 
 */
public interface Produktverwaltung {

	/**
	 * Diese Methode speichert das Produkt in der Datenbank
	 * @param produkt
	 */
	public Produkt speicherProdukt(Produkt produkt);
	
	/**
	 * Diese Methode loescht das Produkt aus der Datenbank
	 * @param produktID
	 */
	public Produkt loescheProdukt(int produktID);
	
	/**
	 * Diese Methode liest das Produkt aus der Datenbank
	 * @param produktID
	 */
	public Produkt findeProdukt(int produktID);
}
