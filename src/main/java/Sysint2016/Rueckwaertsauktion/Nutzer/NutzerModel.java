package Sysint2016.Rueckwaertsauktion.Nutzer;

import Sysint2016.Rueckwaertsauktion.Logik.InitDB;

public class NutzerModel implements NutzerDao{
	InitDB initDb;
	
	public NutzerModel(){
		initDb = new InitDB();
	}
	
	public Nutzer speicherNutzer(Nutzer nutzer) {
		this.initDb.getMapper().save(nutzer);
		return nutzer;
	}

}
