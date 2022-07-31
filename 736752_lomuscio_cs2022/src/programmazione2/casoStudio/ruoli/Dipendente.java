/**
 * 
 */
package programmazione2.casoStudio.ruoli;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * La classe Dipendente estende la classe AbstractPersona e rappresenta un
 * dipendente generico
 * 
 * @author Luca Lomuscio
 *
 */
public class Dipendente extends AbstractPersona {

	/**
	 * Il SerialVersionUID viene sempre autogenerato dalla JVM. Serve a capire se
	 * una determinata classe che viene serializzata sia compatibile o meno con la
	 * versione della JVM.
	 */
	private static final long serialVersionUID = -6181282734302979351L;

	/**
	 * Valore che indica il codice identificativo del dipendente
	 */
	private String id;

	/**
	 * Valore che indica il numero massimo di noleggi che può effettuare il
	 * dipendente al giorno
	 */
	private static int MAX_NOLEGGI = 10;

	/**
	 * Costruttore della classe Dipendente. Permette di istanziare un dipendente con
	 * tutti i suoi attributi. Solleva l'eccezione PersonaException qualora il
	 * costruttore della classe padre AbstractPersona lo sollevi se il codice
	 * fiscale non rispetta il pattern corretto o la data di nascita è precedente
	 * alla data minima concessa o posteriore alla data attuale, o l'id non sia
	 * alfanumerico e quindi non rispetti la regex "[a-zA-Z0-9]*" o non abbia 10
	 * caratteri.
	 * 
	 * @param codiceFiscale
	 * @param nome
	 * @param cognome
	 * @param dataDiNascita
	 * @param luogoDiNascita
	 * @param id
	 * @throws PersonaException
	 */
	public Dipendente(String codiceFiscale, String nome, String cognome, Date dataDiNascita, String luogoDiNascita,
			String id) throws PersonaException {
		super(codiceFiscale, nome, cognome, dataDiNascita, luogoDiNascita);
		if (!id.matches("[a-zA-Z0-9]*") || id.length() != 10) {
			throw new PersonaException("ID dipendente non valido");
		}
		this.id = id;
	}

	/**
	 * Costruttore della classe Cliente. Permette di istanziare un cliente con il
	 * codice fiscale. Solleva l'eccezione PersonaException qualora il costruttore
	 * della classe padre AbstractPersona lo sollevi se il codice fiscale non
	 * rispetta il pattern corretto o l'id non sia alfanumerico e quindi non
	 * rispetti la regex "[a-zA-Z0-9]*" o non abbia 10 caratteri.
	 * 
	 * @param codiceFiscale
	 * @param id
	 * @throws PersonaException
	 */
	public Dipendente(String codiceFiscale, String id) throws PersonaException {
		super(codiceFiscale);
		if (!id.matches("[a-zA-Z0-9]*") || id.length() != 10) {
			throw new PersonaException("ID dipendente non valido");
		}
		this.id = id;
	}

	/**
	 * Metodo che restituisce l'id del dipendente
	 * 
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/*
	 * Override del metodo hashCode. Restituisce un valore di codice hash per
	 * l'oggetto. Il metodo deve restituire costantemente lo stesso intero per
	 * dipendenti che hanno lo stesso id
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/**
	 * Oveeride del metodo equals(). Indica se qualche altro oggetto è uguale a
	 * questo.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dipendente other = (Dipendente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/*
	 * Metodo che permette di modificare il numero di noleggi massimi al giorno che
	 * può effettuare il dipendente
	 * 
	 * @param mAX_NOLEGGI il nuovo valore di noleggi massimi che può effettuare un
	 * dipendente
	 */
	public static void setMAX_NOLEGGI(int mAX_NOLEGGI) {
		MAX_NOLEGGI = mAX_NOLEGGI;
	}

	/**
	 * Override del metodo astratto addNoleggio della classe AbstractPersona. Metodo
	 * che aggiunge il numero di smartphone noleggiati dal dipendente. Solleva
	 * l'eccezione PersonaException qualora il numero di smartphone noleggiati in
	 * quel giorno superi il numero massimo consentito per la classe dipendente.
	 */
	@Override
	public void addNoleggio(int smartphoneDaNoleggiare) throws PersonaException {
		if ((this.smartphoneNoleggiati + smartphoneDaNoleggiare) > MAX_NOLEGGI) {
			throw new PersonaException("numero smartphone noleggiati per dipendente oltre il limite");
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
		return "Dipendente [id=" + id + ", codiceFiscale=" + codiceFiscale + ", nome=" + nome + ", cognome=" + cognome
				+ ", dataDiNascita=" + (new SimpleDateFormat("dd/MM/yyyy")).format(dataDiNascita) + ", luogoDiNascita="
				+ luogoDiNascita + "]";
	}

}
