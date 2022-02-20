/**
 * 
 */
package programmazione2.casoStudio.azioni;

import java.util.Date;

import programmazione2.casoStudio.ruoli.Dipendente;

/**
 * @author lucal
 *
 */
public class Noleggio extends AbstractTransazione {

	private Date dataFine;

	/**
	 * @param data
	 * @param prezzo
	 * @param dipendente
	 * @param dataFine
	 */
	public Noleggio(Date data, double prezzo, Dipendente dipendente, Date dataFine) {
		super(data, prezzo, dipendente);
		this.dataFine = dataFine;
	}

	/**
	 * @return the dataFine
	 */
	public Date getDataFine() {
		return dataFine;
	}

	@Override
	public String toString() {
		return "Noleggio [dataFine=" + dataFine + ", smartphone=" + smartphone + ", data=" + data + ", prezzo=" + prezzo
				+ ", dipendente=" + dipendente + "]";
	}

}
