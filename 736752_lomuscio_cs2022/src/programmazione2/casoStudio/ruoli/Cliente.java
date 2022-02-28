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
public class Cliente extends AbstractPersona {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3949733657807779459L;
	private String codiceFiscale;
	private static int MAX_NOLEGGI;

	/**
	 * @param nome
	 * @param cognome
	 * @param dataDiNascita
	 * @param codiceFiscale
	 * @throws ClienteException
	 */
	public Cliente(String nome, String cognome, Date dataDiNascita, String codiceFiscale) throws ClienteException {
		super(nome, cognome, dataDiNascita);
		if (!codiceFiscale.matches("^[a-zA-Z]{6}[0-9]{2}[a-zA-Z][0-9]{2}[a-zA-Z][0-9]{3}[a-zA-Z]$"))
			throw new ClienteException("Codice fiscale non valido");
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

	/**
	 * @param mAX_NOLEGGI the mAX_NOLEGGI to set
	 */
	public static void setMAX_NOLEGGI(int mAX_NOLEGGI) {
		MAX_NOLEGGI = mAX_NOLEGGI;
	}

	@Override
	public void addNoleggio(int smartphoneDaNoleggiare) throws ClienteException {
		if ((this.smartphoneNoleggiati + smartphoneDaNoleggiare) > MAX_NOLEGGI) {
			throw new ClienteException("numero smartphone noleggiati per cliente oltre il limite");
		}
		this.smartphoneNoleggiati += smartphoneDaNoleggiare;
	}

	@Override
	public String toString() {
		return "Cliente [codiceFiscale=" + codiceFiscale + ", nome=" + nome + ", cognome=" + cognome
				+ ", dataDiNascita=" + dataDiNascita + ", smartphoneNoleggiati=" + smartphoneNoleggiati + "]";
	}

}
