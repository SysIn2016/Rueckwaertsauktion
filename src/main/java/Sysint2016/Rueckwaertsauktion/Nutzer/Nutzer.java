package Sysint2016.Rueckwaertsauktion.Nutzer;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

/**
 * Diese Klasse repräsentiert einen Nutzer im System.
 * 
 */

@DynamoDBTable(tableName = "Nutzer")
public class Nutzer {

	private String username;
	private String kontonummer;
	private String iban;
	private String bic;

	/**
	 * Konstruktor für einen Nutzer
	 * 
	 * @param username
	 * @param kontonummer
	 * @param iban
	 * @param bic
	 */
	public Nutzer(String username, String kontonummer, String iban, String bic) {
		this.username = username;
		this.kontonummer = kontonummer;
		this.iban = iban;
		this.bic = bic;
	}

	@DynamoDBHashKey
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
