/**
 * 
 */
package programmazione2.casoStudio.dispositivi;

/**
 * Le eccezioni sollevate con la classe SmartphoneException permettono di
 * controllare le eccezioni riguardanti uno smartphone. Le eccezioni
 * riguardano la validità del codice IMEI. La classe SmartphoneException estende
 * la classe Exception
 * 
 * @author Luca Lomuscio
 *
 */
public class SmartphoneException extends Exception {

	/**
	 * Il SerialVersionUID viene sempre autogenerato dalla JVM. Serve a capire se
	 * una determinata classe che viene serializzata sia compatibile o meno con la
	 * versione della JVM
	 */
	private static final long serialVersionUID = 1931884748912860942L;

	/**
	 * costruttore aparametrico dell'eccezione
	 */
	public SmartphoneException() {
	}

	/**
	 * costruttore dell'eccezione con messaggio
	 * 
	 * @param msg descrive l'errore
	 */
	public SmartphoneException(String message) {
		super(message);
	}

}
