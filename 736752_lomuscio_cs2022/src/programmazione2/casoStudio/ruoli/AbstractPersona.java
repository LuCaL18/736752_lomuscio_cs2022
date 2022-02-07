/**
 * 
 */
package programmazione2.casoStudio.ruoli;

import java.util.Date;

import programmazione2.casoStudio.azioni.Noleggio;

/**
 * @author lucal
 *
 */
abstract class AbstractPersona {

	protected String nome;
	protected String cognome;
	protected Date dataDiNascita;
	protected int smartphoneNoleggiati = 0;

	/**
	 * @param nome
	 * @param cognome
	 * @param dataDiNascita
	 */
	public AbstractPersona(String nome, String cognome, Date dataDiNascita) {
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @return the cognome
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * @return the dataDiNascita
	 */
	public Date getDataDiNascita() {
		return dataDiNascita;
	}

	/**
	 * @param smartphoneNoleggiati the smartphoneNoleggiati to set
	 */
	public void setSmartphoneNoleggiati() {
		this.smartphoneNoleggiati = 0;
	}

	public abstract void addNoleggio(Noleggio noleggio) throws Exception;

}
