package Sysint2016.Rueckwaertsauktion;

import java.util.List;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;

public class ErgebnisModel implements Ergebnisverwaltung {
	InitDB initDb;
	
	public ErgebnisModel(){
		initDb = new InitDB();
	}
	
	public Ergebnis speicherErgebnis(Ergebnis ergebnis) {
		this.initDb.getMapper().save(ergebnis);
		return ergebnis;
	}

	public Ergebnis findeErgebnis(String datum) {
		Ergebnis ergebnis = this.initDb.getMapper().load(Ergebnis.class, datum);
		return ergebnis;
	}

	public List<Ergebnis> findeAlle() {
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		List<Ergebnis> scanResult = initDb.getMapper().scan(Ergebnis.class, scanExpression);
		return scanResult;
	}

}
