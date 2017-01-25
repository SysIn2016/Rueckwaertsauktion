package Sysint2016.Rueckwaertsauktion;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "Ergebnis")
public class Ergebnis {
	private int produktID;
	private String username;
	private String datum;
	private float preis;
	
	
	public Ergebnis() {
		super();
	}

	public Ergebnis(int produktID, String username, String datum, float preis) {
		super();
		this.produktID = produktID;
		this.username = username;
		this.datum = datum;
		this.preis = preis;
	}

	@DynamoDBAttribute
	public int getProduktID() {
		return produktID;
	}

	public void setProduktID(int produktID) {
		this.produktID = produktID;
	}

	@DynamoDBAttribute
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@DynamoDBHashKey
	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	@DynamoDBAttribute
	public float getPreis() {
		return preis;
	}

	public void setPreis(float preis) {
		this.preis = preis;
	}
}
