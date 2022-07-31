/**
 * 
 */
package programmazione2.casoStudio.ruoli;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * La classe Cliente estende la classe AbstractPersona e rappresenta un cliente
 * generico
 * 
 * @author Luca Lomuscio
 */
public class Cliente extends AbstractPersona {

	/**
	 * Il SerialVersionUID viene sempre autogenerato dalla JVM. Serve a capire se
	 * una determinata classe che viene serializzata sia compatibile o meno con la
	 * versione della JVM.
	 */
	private static final long serialVersionUID = -3949733657807779459L;
	/**
	 * Valore che rappresenta il numero massimo di noleggi che può effettuare il
	 * cliente al giorno
	 */
	private static int MAX_NOLEGGI = 5;

	/**
	 * Costruttore della classe Cliente. Permette di istanziare un cliente con tutti
	 * i suoi attributi. Solleva l'eccezione PersonaException qualora il costruttore
	 * della classe padre AbstractPersona lo sollevi. L'eccezione è sollevata se il
	 * codice fiscale non rispetta il pattern corretto o la data di nascita è
	 * precedente alla data minima concessa o posteriore alla data attuale.
	 * 
	 * @param codiceFiscale
	 * @param nome
	 * @param cognome
	 * @param dataDiNascita
	 * @param luogoDiNascita
	 * @throws PersonaException
	 */
	public Cliente(String codiceFiscale, String nome, String cognome, Date dataDiNascita, String luogoDiNascita)
			throws PersonaException {
		super(codiceFiscale, nome, cognome, dataDiNascita, luogoDiNascita);
	}

	/**
	 * Costruttore della classe Cliente. Permette di istanziare un cliente con il
	 * codice fiscale. Solleva l'eccezione PersonaException qualora il costruttore
	 * della classe padre AbstractPersona lo sollevi. L'eccezione è sollevata se il
	 * codice fiscale non rispetta il pattern corretto.
	 * 
	 * @param codiceFiscale
	 * @throws PersonaException
	 */
	public Cliente(String codiceFiscale) throws PersonaException {
		super(codiceFiscale);
	}

	/**
	 * Metodo che permette di modificare il numero di noleggi massimi al giorno che
	 * può effettuare il cliente
	 * 
	 * @param mAX_NOLEGGI il nuovo valore di noleggi massimi che può effettuare un
	 *                    cliente
	 */
	public static void setMAX_NOLEGGI(int mAX_NOLEGGI) {
		MAX_NOLEGGI = mAX_NOLEGGI;
	}

	/**
	 * Override del metodo astratto addNoleggio della classe AbstractPersona. Metodo
	 * che aggiunge il numero di smartphone noleggiati dal cliente. Solleva
	 * l'eccezione PersonaException qualora il numero di smartphone noleggiati in
	 * quel giorno superi il numero massimo consentito per la classe Cliente.
	 */
	@Override
	public void addNoleggio(int smartphoneDaNoleggiare) throws PersonaException {
		if ((this.smartphoneNoleggiati + smartphoneDaNoleggiare) > MAX_NOLEGGI) {
			throw new PersonaException("numero smartphone noleggiati per cliente oltre il limite");
		}
		this.smartphoneNoleggiati += smartphoneDaNoleggiare;
	}

	/**
	 * Override del metodo "toString" . Restituisce una stringa che rappresenta
	 * l'istanza di un cliente con le sue generalità
	 * 
	 * @return stringa composta dai parametri identificativi del cliente
	 */
	@Override
	public String toString() {
		return "Cliente [codiceFiscale=" + codiceFiscale + ", nome=" + nome + ", cognome=" + cognome
				+ ", dataDiNascita=" + (new SimpleDateFormat("dd/MM/yyyy")).format(dataDiNascita) + ", luogoDiNascita="
				+ luogoDiNascita + "]";
	}

}
