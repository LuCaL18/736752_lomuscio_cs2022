/**
 * 
 */
package programmazione2.casoStudio.ruoli;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lucal
 *
 */
abstract class AbstractPersona implements Persona,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9074543292718853482L;
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
	@Override
	public String getNome() {
		return nome;
	}

	/**
	 * @return the cognome
	 */
	@Override
	public String getCognome() {
		return cognome;
	}

	/**
	 * @return the dataDiNascita
	 */
	@Override
	public Date getDataDiNascita() {
		return dataDiNascita;
	}

	/**
	 * @param smartphoneNoleggiati the smartphoneNoleggiati to set
	 */
	@Override
	public void setSmartphoneNoleggiati(int smartphoneNonPiuNoleggiati) {
		this.smartphoneNoleggiati -= smartphoneNonPiuNoleggiati;
	}

	@Override
	public abstract void addNoleggio(int smartphoneDaNoleggiare) throws Exception;

}
