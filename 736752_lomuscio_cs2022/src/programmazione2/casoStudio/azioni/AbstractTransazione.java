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
abstract class AbstractTransazione implements Transazione {

	protected Set<Smartphone> smartphone;
	protected Date data;
	protected double prezzo;
	protected Dipendente dipendente;

	/**
	 * @param smartphone
	 * @param data
	 * @param prezzo
	 * @param dipendente
	 */
	public AbstractTransazione(Date data, double prezzo, Dipendente dipendente) {
		this.smartphone = new HashSet<Smartphone>();
		this.data = data;
		this.prezzo = prezzo;
		this.dipendente = dipendente;
	}

	@Override
	public Set<Smartphone> getSmartphone() {
		return smartphone;
	}

	@Override
	public Date getData() {
		return data;
	}

	@Override
	public double getPrezzo() {
		return prezzo;
	}

	@Override
	public Dipendente getDipendente() {
		return dipendente;
	}

}
