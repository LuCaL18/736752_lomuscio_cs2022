package programmazione2.casoStudio.ruoli;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * Classe astratta che rappresenta una persona generica con i suoi attributi:
 * codiceFiscale, nome, cognome, dataDiNascita, luogoDiNascita. Implementa le
 * interfacce Persona, Serializable e Cloneable.
 * 
 * @author Luca Lomuscio
 */
abstract class AbstractPersona implements Persona, Serializable, Cloneable {

	/*
	 * Il SerialVersionUID viene sempre autogenerato dalla JVM. Serve a capire se
	 * una determinata classe che viene serializzata sia compatibile o meno con la
	 * versione della JVM
	 */
	private static final long serialVersionUID = -9074543292718853482L;

	/* Valore che indica il codice fiscale di una persona */
	protected String codiceFiscale;

	/* Valore che indica il nome di una persona */
	protected String nome;

	/* Valore che indica il cognome di una persona */
	protected String cognome;

	/* Valore che indica la data di Nascita di una persona */
	protected Date dataDiNascita;

	/* Valore che indica il luogo di Nascita di una persona */
	protected String luogoDiNascita;

	/* Valore che indica il numero di smartphone noleggiati da una persona */
	protected int smartphoneNoleggiati = 0;

	/**
	 * Costruttore della classe AbstractPersona Permette di istanziare un tipo di
	 * persona con tutti i suoi attributi base. Solleva l'eccezione PersonaException
	 * qualora il codice fiscale non rispetti il pattern corretto o la data di
	 * nascita è precedente alla data minima concessa o posteriore alla data
	 * attuale.
	 * 
	 * @param codiceFiscale
	 * @param nome
	 * @param cognome
	 * @param dataDiNascita
	 * @param luogoDiNascita
	 * @throws PersonaException
	 */
	public AbstractPersona(String codiceFiscale, String nome, String cognome, Date dataDiNascita, String luogoDiNascita)
			throws PersonaException {

		if (!codiceFiscale.matches("^[a-zA-Z]{6}[0-9]{2}[a-zA-Z][0-9]{2}[a-zA-Z][0-9]{3}[a-zA-Z]$"))
			throw new PersonaException("Codice fiscale non valido");

		if (this.checkDataNascita(dataDiNascita))
			throw new PersonaException("Data di nascita non valida");

		this.codiceFiscale = codiceFiscale.toUpperCase();
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
		this.luogoDiNascita = luogoDiNascita;
	}

	/**
	 * Costruttore della classe AbstractPersona. Permette di istanziare un tipo di
	 * persona con il solo codice fiscale. Solleva l'eccezione PersonaException
	 * qualora il codice fiscale non rispetti il pattern corretto.
	 * 
	 * @param codiceFiscale
	 * @throws PersonaException
	 */
	public AbstractPersona(String codiceFiscale) throws PersonaException {
		if (!codiceFiscale.matches("^[a-zA-Z]{6}[0-9]{2}[a-zA-Z][0-9]{2}[a-zA-Z][0-9]{3}[a-zA-Z]$"))
			throw new PersonaException("Codice fiscale non valido");
		this.codiceFiscale = codiceFiscale.toUpperCase();
	}

	/**
	 * Metodo che restituisce il codice fiscale della persona
	 * 
	 * @return codiceFiscale
	 */
	@Override
	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	/**
	 * Metodo che restituisce il nome della persona
	 * 
	 * @return nome
	 */
	@Override
	public String getNome() {
		return nome;
	}

	/**
	 * Metodo che restituisce il cognome della persona
	 * 
	 * @return cognome
	 */
	@Override
	public String getCognome() {
		return cognome;
	}

	/**
	 * Metodo che restituisce la data di Nascita della persona
	 * 
	 * @return dataDiNascita
	 */
	@Override
	public Date getDataDiNascita() {
		return dataDiNascita;
	}

	/**
	 * Metodo che restituisce il luogo di nascita della persona
	 * 
	 * @return luogoDiNascita
	 */
	@Override
	public String getLuogoDiNascita() {
		return luogoDiNascita;
	}

	/**
	 * Metodo che modifica il nome della persona
	 * 
	 * @param nome il nuovo valore da assegnare a nome
	 */
	@Override
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Metodo che modifica il cognome della persona
	 * 
	 * @param cognome il nuovo valore da assegnare a cognome
	 */
	@Override
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	/**
	 * Metodo che modifica la data di nascita della persona. Solleva l'eccezione
	 * PersonaException qualora la data di nascita è precedente alla data minima
	 * concessa o posteriore alla data odierna.
	 * 
	 * @param dataDiNascita il nuovo valore da assegnare alla data di nascita
	 * @throws PersonaException
	 */
	@Override
	public void setDataDiNascita(Date dataDiNascita) throws PersonaException {
		if (checkDataNascita(dataDiNascita)) {
			throw new PersonaException("Data di nascita non valida");
		}
		this.dataDiNascita = dataDiNascita;
	}

	/**
	 * Metodo che modifica il luogo di nascita della persona.
	 * 
	 * @param luogoDiNascita il nuovo valore da assegnare al luogo di nascita
	 */
	@Override
	public void setLuogoDiNascita(String luogoDiNascita) {
		this.luogoDiNascita = luogoDiNascita;
	}

	/**
	 * Metodo che resetta gli smartphone noleggiati dalla persona.
	 */
	@Override
	public void resetSmartphoneNoleggiati() {
		this.smartphoneNoleggiati = 0;
	}

	/**
	 * Override del metodo clone(). Esegue un'operazione di clonazione dell'oggetto
	 * che implementa l'interfaccia Persona.
	 * @return result clone dell'oggetto che rappresenta una Persona
	 */
	@Override
	public Persona clone() {
		Persona result = null;
		try {
			result = (Persona) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * Override del metodo hashCode. Restituisce un valore di codice hash per
	 * l'oggetto. Il metodo deve restituire costantemente lo stesso intero per
	 * persone che hanno lo stesso codice fiscale
	 * @return int codice hash
	 */
	@Override
	public int hashCode() {
		return Objects.hash(codiceFiscale);
	}

	/**
	 * Override del metodo equals(). Indica se qualche altro oggetto è uguale a
	 * questo.
	 * @return boolean
	 */
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
	 * Metodo che controlla la data di nascita. Restituisce true se la data di
	 * nascita è superiore al limite di data concessa e inferiore alla data odierna.
	 * Altrimenti restituisce false.
	 * 
	 * @param dataDiNascita
	 * @return boolean
	 */
	private boolean checkDataNascita(Date dataDiNascita) {
		try {
			return dataDiNascita.before(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/1910"))
					|| dataDiNascita.after(new Date());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Metodo astratto che deve essere implementato nelle classi derivate concrete
	 * della classe AbstractPersona
	 */
	@Override
	public abstract void addNoleggio(int smartphoneDaNoleggiare) throws PersonaException;

}
