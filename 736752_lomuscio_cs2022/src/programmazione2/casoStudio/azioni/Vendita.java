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
public class Vendita {

	private Set<Smartphone> smartphoneVenduti = new HashSet<Smartphone>();
	private Date dataDiVendita;
	private double prezzoDiVendita;
	private Dipendente dipendente;

	/**
	 * @param smartphoneVenduti
	 * @param dataDiVendita
	 * @param prezzoDiVendita
	 * @param dipendente
	 */
	public Vendita(Set<Smartphone> smartphoneVenduti, Date dataDiVendita, double prezzoDiVendita,
			Dipendente dipendente) {
		this.smartphoneVenduti = smartphoneVenduti;
		this.dataDiVendita = dataDiVendita;
		this.prezzoDiVendita = prezzoDiVendita;
		this.dipendente = dipendente;
	}

	/**
	 * @return the smartphoneVenduti
	 */
	public Set<Smartphone> getSmartphoneVenduti() {
		return smartphoneVenduti;
	}

	/**
	 * @return the dataDiVendita
	 */
	public Date getDataDiVendita() {
		return dataDiVendita;
	}

	/**
	 * @return the prezzoDiVendita
	 */
	public double getPrezzoDiVendita() {
		return prezzoDiVendita;
	}

	/**
	 * @return the dipendente
	 */
	public Dipendente getDipendente() {
		return dipendente;
	}

	@Override
	public String toString() {
		return "Vendita [smartphoneVenduti=" + smartphoneVenduti + ", dataDiVendita=" + dataDiVendita
				+ ", prezzoDiVendita=" + prezzoDiVendita + ", dipendente=" + dipendente + "]";
	}

}
