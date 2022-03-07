/**
 * 
 */
package programmazione2.casoStudio.azioni;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import programmazione2.casoStudio.dispositivi.Smartphone;
import programmazione2.casoStudio.ruoli.Dipendente;

/**
 * @author lucal
 *
 */
abstract class AbstractTransazione implements Transazione, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2729698442072161211L;
	protected Set<Smartphone> smartphone;
	protected Date data;
	protected double prezzo;
	protected Dipendente dipendente;
	protected int codiceTransazione;
	private static int codicePrec = 0;

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
		this.codiceTransazione = ++codicePrec;

	}

	/**
	 * @return the codiceTransazione
	 */
	public int getCodiceTransazione() {
		return codiceTransazione;
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

	/**
	 * @return the codicePrec
	 */
	public static int getCodicePrec() {
		return codicePrec;
	}

	/**
	 * @param codicePrec the codicePrec to set
	 */
	public static void setCodicePrec(int codicePrec) {
		AbstractTransazione.codicePrec = codicePrec;
	}

	@Override
	public void addSmartphone(Smartphone smartphone) {
		this.smartphone.add(smartphone);
	}

	@Override
	public int hashCode() {
		return Objects.hash(codiceTransazione);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractTransazione other = (AbstractTransazione) obj;
		return codiceTransazione == other.codiceTransazione;
	}

}
