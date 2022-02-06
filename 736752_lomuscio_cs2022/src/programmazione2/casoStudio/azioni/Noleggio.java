/**
 * 
 */
package programmazione2.casoStudio.azioni;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import programmazione2.casoStudio.dispositivi.Smartphone;
import programmazione2.casoStudio.ruoli.Dipendente;

/**
 * @author lucal
 *
 */
public class Noleggio {

	private Set<Smartphone> smartphoneNoleggiati = new HashSet<Smartphone>();
	private Date dataInizio;
	private Date dataFine;
	private double prezzo;
	private Dipendente dipendente;

	/**
	 * @param smartphoneNoleggiati
	 * @param dataInizio
	 * @param dataFine
	 * @param prezzo
	 * @param dipendente
	 */
	public Noleggio(Set<Smartphone> smartphoneNoleggiati, Date dataInizio, Date dataFine, double prezzo,
			Dipendente dipendente) {
		this.smartphoneNoleggiati = smartphoneNoleggiati;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.prezzo = prezzo;
		this.dipendente = dipendente;
	}

	/**
	 * @return the smartphoneNoleggiati
	 */
	public Set<Smartphone> getSmartphoneNoleggiati() {
		return smartphoneNoleggiati;
	}

	/**
	 * @return the dataInizio
	 */
	public Date getDataInizio() {
		return dataInizio;
	}

	/**
	 * @return the dataFine
	 */
	public Date getDataFine() {
		return dataFine;
	}

	/**
	 * @return the prezzo
	 */
	public double getPrezzo() {
		return prezzo;
	}

	/**
	 * @return the dipendente
	 */
	public Dipendente getDipendente() {
		return dipendente;
	}

	@Override
	public String toString() {
		return "Noleggio [smartphoneNoleggiati=" + smartphoneNoleggiati + ", dataInizio=" + dataInizio + ", dataFine="
				+ dataFine + ", prezzo=" + prezzo + ", dipendente=" + dipendente + "]";
	}

}
