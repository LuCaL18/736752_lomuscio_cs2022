/**
 * 
 */
package programmazione2.casoStudio.application;

/**
 * Le eccezioni sollevate con la classe AppException permettono di controllare
 * le eccezioni riguardanti la logica dell'applicazione. Le eccezioni riguardano
 * la vendita di smartphone avanzati, l'esistenza di clienti, dipendenti,
 * noleggi, smartphone, la disponibilità di questi ultimi e la fine dei noleggi.
 * La classe AppException estende la classe Exception.
 * 
 * @author Luca Lomuscio
 *
 */
public class AppException extends Exception {

	/**
	 * Il SerialVersionUID viene sempre autogenerato dalla JVM. Serve a capire se
	 * una determinata classe che viene serializzata sia compatibile o meno con la
	 * versione della JVM
	 */
	private static final long serialVersionUID = 8246431304735845208L;

	/**
	 * costruttore aparametrico dell'eccezione
	 */
	public AppException() {

	}

	/**
	 * costruttore dell'eccezione con messaggio
	 * 
	 * @param msg descrive l'errore
	 */
	public AppException(String msg) {
		super(msg);
	}
}
