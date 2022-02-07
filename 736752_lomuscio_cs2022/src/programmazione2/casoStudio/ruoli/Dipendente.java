/**
 * 
 */
package programmazione2.casoStudio.ruoli;

import java.util.Date;
import java.util.Objects;

import programmazione2.casoStudio.azioni.Noleggio;

/**
 * @author lucal
 *
 */
public class Dipendente extends AbstractPersona {

	private String id;
	private static int MAX_NOLEGGI;

	/**
	 * @param nome
	 * @param cognome
	 * @param dataDiNascita
	 * @param id
	 * @throws DipendenteException
	 */
	public Dipendente(String nome, String cognome, Date dataDiNascita, String id) throws DipendenteException {
		super(nome, cognome, dataDiNascita);
		if (id.length() != 10) {
			throw new DipendenteException("l'id deve contenere esattamente 10 caratteri");
		}
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dipendente other = (Dipendente) obj;
		return Objects.equals(id, other.id);
	}

	public static void setMAX_NOLEGGI(int mAX_NOLEGGI) {
		MAX_NOLEGGI = mAX_NOLEGGI;
	}

	@Override
	public void addNoleggio(Noleggio noleggio) throws DipendenteException {
		if ((this.smartphoneNoleggiati + noleggio.getSmartphoneNoleggiati().size()) > MAX_NOLEGGI) {
			throw new DipendenteException("numero smartphone noleggiati per dipendente oltre il limite");
		}
		this.smartphoneNoleggiati += noleggio.getSmartphoneNoleggiati().size();
	}

	@Override
	public String toString() {
		return "Dipendente [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", dataDiNascita=" + dataDiNascita
				+ ", smartphoneNoleggiati=" + smartphoneNoleggiati + "]";
	}

}
