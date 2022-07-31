/**
 * 
 */
package programmazione2.casoStudio.ruoli;

import java.util.Date;

/**
 * Interfaccia che rappresenta una Persona. La classe che implementa
 * l'interfaccia Persona deve implementare i metodi : getNome(), getCognome(),
 * getDataDiNascita(), getLuogoDiNascita(), setNome(String nome),
 * setCognome(String cognome), setDataDiNascita(Date dataDiNascita),
 * setLuogoDiNascita(String luogoDiNascita), resetSmartphoneNoleggiati(),
 * addNoleggio(int smartphoneDaNoleggiare), getCodiceFiscale()
 * 
 * @author Luca Lomuscio
 *
 */
public interface Persona {

	/**
	 * Metodo che restituisce il nome della persona
	 * 
	 * @return nome
	 */
	public String getNome();

	/**
	 * Metodo che restituisce il cognome della persona
	 * 
	 * @return cognome
	 */
	public String getCognome();

	/**
	 * Metodo che restituisce la data di Nascita della persona
	 * 
	 * @return dataDiNascita
	 */
	public Date getDataDiNascita();

	/**
	 * Metodo che restituisce il luogo di nascita della persona
	 * 
	 * @return luogoDiNascita
	 */
	public String getLuogoDiNascita();

	/**
	 * Metodo che modifica il nome della persona
	 * 
	 * @param nome il nuovo valore da assegnare a nome
	 */
	public void setNome(String nome);

	/**
	 * Metodo che modifica il cognome della persona
	 * 
	 * @param cognome il nuovo valore da assegnare a cognome
	 */
	public void setCognome(String cognome);

	/**
	 * Metodo che modifica la data di nascita della persona. Solleva l'eccezione
	 * PersonaException qualora la data di nascita è precedente alla data minima
	 * concessa o posteriore alla data odierna.
	 * 
	 * @param dataDiNascita il nuovo valore da assegnare alla data di nascita
	 * @throws PersonaException
	 */
	public void setDataDiNascita(Date dataDiNascita) throws PersonaException;

	/**
	 * Metodo che modifica il luogo di nascita della persona.
	 * 
	 * @param luogoDiNascita il nuovo valore da assegnare al luogo di nascita
	 */
	public void setLuogoDiNascita(String luogoDiNascita);

	/**
	 * Metodo che resetta gli smartphone noleggiati dalla persona.
	 */
	public void resetSmartphoneNoleggiati();

	/**
	 * Metodo che aggiunge il numero di smartphone noleggiati da una persona.
	 * Solleva l'eccezione PersonaException qualora il numero di smartphone
	 * noleggiati in quel giorno superi il numero massimo consentito.
	 */
	public void addNoleggio(int smartphoneDaNoleggiare) throws PersonaException;

	/**
	 * Metodo che restituisce il codice fiscale della persona
	 * 
	 * @return codiceFiscale
	 */
	public String getCodiceFiscale();
}
