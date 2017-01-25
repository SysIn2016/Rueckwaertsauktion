package Sysint2016.Rueckwaertsauktion;


public class Main {
	public static void main(String[] args) throws Exception {
		ErgebnisModel model = new ErgebnisModel();
		model.speicherErgebnis(new Ergebnis(12, "Mein Ergebnis1", "12.09.2010", 12.5f));
		model.speicherErgebnis(new Ergebnis(13, "Mein Ergebnis2", "12.09.2011", 12.5f));
		model.speicherErgebnis(new Ergebnis(14, "Mein Ergebnis3", "12.09.2012", 12.5f));
		model.speicherErgebnis(new Ergebnis(15, "Mein Ergebnis4", "12.09.2013", 12.5f));
		model.findeAlle();
	}
}
