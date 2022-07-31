/**
 * 
 */
package programmazione2.casoStudio.ruoli;

/**
 * Le eccezioni sollevate con la classe PersonaException permettono di
 * controllare le eccezioni riguardanti una persona che svolge un ruolo in
 * questa applicazione. Le eccezioni riguardano la data di nascita, il codice
 * fiscale e il numero di smartphone noleggiati di una persona. Inoltre un
 * eccezione riguarda l'id di un dipendente. La classe SmartphoneException
 * estende la classe Exception
 * 
 * @author Luca Lomuscio
 *
 */
public class PersonaException extends Exception {

	/**
	 * Il SerialVersionUID viene sempre autogenerato dalla JVM. Serve a capire se
	 * una determinata classe che viene serializzata sia compatibile o meno con la
	 * versione della JVM
	 */
	private static final long serialVersionUID = 4994161159055909118L;

	/**
	 * costruttore aparametrico dell'eccezione
	 */
	public PersonaException() {
	}

	/**
	 * costruttore dell'eccezione con messaggio
	 * 
	 * @param msg descrive l'errore
	 */
	public PersonaException(String msg) {
		super(msg);
	}
}
