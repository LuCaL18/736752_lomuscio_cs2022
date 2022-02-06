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
public class Cliente extends AbstractPersona {

	private String codiceFiscale;
	private static int MAX_NOLEGGI;

	/**
	 * @param nome
	 * @param cognome
	 * @param dataDiNascita
	 * @param codiceFiscale
	 */
	public Cliente(String nome, String cognome, Date dataDiNascita, String codiceFiscale) {
		super(nome, cognome, dataDiNascita);
		this.codiceFiscale = codiceFiscale;
	}

	/**
	 * @return the codiceFiscale
	 */
	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codiceFiscale);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(codiceFiscale, other.codiceFiscale);
	}

	@Override
	public String toString() {
		return "Cliente [codiceFiscale=" + codiceFiscale + ", nome=" + nome + ", cognome=" + cognome
				+ ", dataDiNascita=" + dataDiNascita + "]";
	}

	/**
	 * @param mAX_NOLEGGI the mAX_NOLEGGI to set
	 */
	public static void setMAX_NOLEGGI(int mAX_NOLEGGI) {
		MAX_NOLEGGI = mAX_NOLEGGI;
	}

	@Override
	public void addNoleggio(Noleggio noleggio) throws ClienteException {
		if ((this.smartphoneNoleggiati + noleggio.getSmartphoneNoleggiati().size()) > MAX_NOLEGGI) {
			throw new ClienteException("numero smartphone noleggiati per cliente oltre il limite");
		}
		noleggi.add(noleggio);
		this.smartphoneNoleggiati += noleggio.getSmartphoneNoleggiati().size();
	}

}