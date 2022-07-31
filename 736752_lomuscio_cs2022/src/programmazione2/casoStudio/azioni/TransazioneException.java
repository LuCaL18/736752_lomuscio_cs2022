package programmazione2.casoStudio.azioni;

/**
 * Le eccezioni sollevate con la classe TransazioneException permettono di
 * controllare le eccezioni riguardanti una transazione. Le eccezioni riguardano
 * la validità della data di fine Noleggio. La classe SmartphoneException
 * estende la classe Exception
 * 
 * @author Luca Lomuscio
 *
 */
public class TransazioneException extends Exception {

	/**
	 * Il SerialVersionUID viene sempre autogenerato dalla JVM. Serve a capire se
	 * una determinata classe che viene serializzata sia compatibile o meno con la
	 * versione della JVM
	 */
	private static final long serialVersionUID = -7038848961896462366L;

	/**
	 * costruttore aparametrico dell'eccezione
	 */
	public TransazioneException() {
	}

	/**
	 * costruttore dell'eccezione con messaggio
	 * 
	 * @param msg descrive l'errore
	 */
	public TransazioneException(String message) {
		super(message);
	}

}
