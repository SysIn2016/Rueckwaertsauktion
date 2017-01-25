package Sysint2016.Rueckwaertsauktion;

public class ProduktModel implements Produktverwaltung{
	InitDB initDb;
	
	public ProduktModel(){
		initDb = new InitDB();
	}
	
	public Produkt speicherProdukt(Produkt produkt) {
        this.initDb.getMapper().save(produkt);
        return produkt;
	}

	public Produkt loescheProdukt(int produktID) {
		Produkt produkt = this.initDb.getMapper().load(Produkt.class, produktID);
		this.initDb.getMapper().delete(produkt);
		return produkt;
	}

	public Produkt findeProdukt(int produktID) {
		Produkt produkt = this.initDb.getMapper().load(Produkt.class, produktID);
		return produkt;
	}

}
