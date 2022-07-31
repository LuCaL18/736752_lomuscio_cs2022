/**
 * 
 */
package programmazione2.casoStudio.azioni;

import java.util.Date;

import programmazione2.casoStudio.ruoli.Cliente;
import programmazione2.casoStudio.ruoli.Dipendente;

/**
 * Classe che rappresenta il tipo Transazione Noleggio. Estende la classe
 * AbstractTransazione. Oltre agli attributi generici di una transazione ha
 * anche una data di fine noleggio e un valore che indica se il noleggio è
 * concluso.
 * 
 * @author Luca Lomuscio
 *
 */
public class Noleggio extends AbstractTransazione {

	/*
	 * Il SerialVersionUID viene sempre autogenerato dalla JVM. Serve a capire se
	 * una determinata classe che viene serializzata sia compatibile o meno con la
	 * versione della JVM
	 */
	private static final long serialVersionUID = -3657175547272007713L;
	/**
	 * Valore che indica la data di fine Noleggio
	 */
	private Date dataFine;
	/**
	 * Valore che indica se il noleggio è concluso
	 */
	private boolean concluso;

	/**
	 * Costruttore della classe Noleggio che permette di istanziare un oggetto di
	 * tipo Noleggio con tutti i suoi attributi ad eccezione della lista degli
	 * smartphone che è vuuota al momento dell'istanziazione. Solleva l'eccezione
	 * TransazioneException se la data di fine noleggio è precedente alla data di
	 * inizio.
	 * 
	 * @param dataInizio data di inzio noleggio
	 * @param prezzo     costo del noleggio
	 * @param dipendente dipendente che effettua il noleggio
	 * @param cliente    cliente che effettua il noleggio
	 * @param dataFine   data di fine noleggio
	 * @throws TransazioneException
	 */
	public Noleggio(Date dataInizio, double prezzo, Dipendente dipendente, Cliente cliente, Date dataFine)
			throws TransazioneException {
		super(dataInizio, prezzo, dipendente, cliente);
		if (!checkDataFine(dataInizio, dataFine)) {
			throw new TransazioneException("Data fine noleggio inferiore alla data odierna");
		}
		this.dataFine = dataFine;
		this.concluso = false;
	}

	/**
	 * Costruttore della classe Noleggio che permette di istanziare un oggetto di
	 * tipo Noleggio con gli attributi richiesti dal costruttore della superclasse
	 * AbstractTransazione.
	 * 
	 * @param data       data di inzio Noleggio
	 * @param prezzo     costo del Noleggio
	 * @param dipendente dipendente che effettua il noleggio
	 * @param cliente    cliente che effettua il noleggio
	 */
	public Noleggio(Date data, double prezzo, Dipendente dipendente, Cliente cliente) {
		super(data, prezzo, dipendente, cliente);
	}

	/**
	 * Metodo che permette di modificare il valore della data di fine noleggio.
	 * Solleva l'eccezione se la data di fine noleggio sia precedente a quella di
	 * inzio
	 * 
	 * @param dataFine nuovo valore di data fine noleggio da assegnare a dataFine
	 * @throws TransazioneException
	 */
	public void setDataFine(Date dataFine) throws TransazioneException {
		if (!checkDataFine(this.data, dataFine)) {
			throw new TransazioneException("Data fine noleggio inferiore alla prima data disponibile");
		}
		this.dataFine = dataFine;
	}

	/**
	 * Metodo che restituisce la data di fine noleggio.
	 * 
	 * @return the dataFine
	 */
	public Date getDataFine() {
		return dataFine;
	}

	/**
	 * Metodo che indica se il noleggio è concluso
	 * 
	 * @return the concluso
	 */
	public boolean isConcluso() {
		return concluso;
	}

	/**
	 * Metodo che permette di modifcare lo stato del noleggio da concluso a non
	 * concluso o viceversa tramite valori booleani
	 * 
	 * @param concluso nuovo valore da assegnare a this.concluso
	 */
	public void setConcluso(boolean concluso) {
		this.concluso = concluso;
	}

	/**
	 * Override del metodo "toString" . Restituisce una stringa che rappresenta
	 * l'istanza di un Noleggio con le sue caratteristiche
	 * 
	 * @return stringa composta dai parametri identificativi del Noleggio
	 */
	@Override
	public String toString() {
		return "Noleggio [dataFine=" + dataFine + ", smartphone=" + smartphone + ", data=" + data + ", prezzo=" + prezzo
				+ ", dipendente=" + dipendente + ", " + cliente + ", concluso=" + concluso + ", codiceTransazione="
				+ codiceTransazione + "]";
	}

	/**
	 * Metodo che indica se la data di fine Noleggio è precedente a quella di inzio.
	 * 
	 * @param dataInizio data di inzio noleggio
	 * @param dataFine   data di fine noleggio
	 * @return true se la data di fine noleggio è posteriore a quella di inizio,
	 *         altrimenti false
	 */
	private boolean checkDataFine(Date dataInizio, Date dataFine) {
		return dataFine.after(dataInizio);
	}

}
