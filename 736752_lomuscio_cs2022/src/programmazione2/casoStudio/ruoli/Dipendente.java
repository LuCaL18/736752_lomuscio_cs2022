/**
 * 
 */
package programmazione2.casoStudio.ruoli;

import java.util.Date;
import java.util.Objects;

/**
 * @author lucal
 *
 */
public class Dipendente extends AbstractPersona {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6181282734302979351L;
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
	public void addNoleggio(int smartphoneDaNoleggiare) throws DipendenteException {
		if ((this.smartphoneNoleggiati + smartphoneDaNoleggiare) > MAX_NOLEGGI) {
			throw new DipendenteException("numero smartphone noleggiati per dipendente oltre il limite");
		}
		this.smartphoneNoleggiati += smartphoneDaNoleggiare;
	}

	@Override
	public String toString() {
		return "Dipendente [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", dataDiNascita=" + dataDiNascita
				+ ", smartphoneNoleggiati=" + smartphoneNoleggiati + "]";
	}

}
