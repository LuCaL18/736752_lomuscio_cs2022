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
public class Vendita extends AbstractTransazione {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8637109900101954444L;

	/**
	 * @param data
	 * @param prezzo
	 * @param dipendente
	 */
	public Vendita(Date data, double prezzo, Dipendente dipendente) {
		super(data, prezzo, dipendente);
	}

	@Override
	public String toString() {
		return "Vendita [smartphone=" + smartphone + ", data=" + data + ", prezzo=" + prezzo + ", dipendente="
				+ dipendente + "]";
	}

}
