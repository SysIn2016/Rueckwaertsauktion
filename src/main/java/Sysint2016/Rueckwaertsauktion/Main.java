package Sysint2016.Rueckwaertsauktion;

public class Main {
	public static void main(String[] args) throws Exception {
		ProduktModel model = new ProduktModel();
		Produktverwaltung pw = model;
		AuktionLogik al = new AuktionLogik(pw);
		al.erzeugeProdukt("Schuhe1", "Eine Schuhe1", "EinstellerID1", "BildID1");
		al.erzeugeProdukt("Schuhe2", "Eine Schuhe2", "EinstellerID2", "BildID2");
		al.erzeugeProdukt("Schuhe3", "Eine Schuhe3", "EinstellerID3", "BildID3");
		al.erzeugeProdukt("Schuhe4", "Eine Schuhe4", "EinstellerID4", "BildID4");
		al.erzeugeProdukt("Schuhe5", "Eine Schuhe5", "EinstellerID5", "BildID5");
	}
}
