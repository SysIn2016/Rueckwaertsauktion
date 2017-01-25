package Sysint2016.Rueckwaertsauktion;

import java.util.List;

public interface Ergebnisverwaltung {
	
	public Ergebnis speicherErgebnis(Ergebnis ergebnis);
	public Ergebnis findeErgebnis(String datum);
	public List<Ergebnis> findeAlle();
}
