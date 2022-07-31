/**
 * 
 */
package programmazione2.casoStudio.azioni;

import java.util.Date;
import java.util.Set;

import programmazione2.casoStudio.dispositivi.Smartphone;
import programmazione2.casoStudio.ruoli.Cliente;
import programmazione2.casoStudio.ruoli.Dipendente;

/**
 * Interfaccia che rappresenta una transazione, cioè la generalizzazione di
 * noleggio e vendita. Le classi che implementano l'interfaccia Transazione
 * devono implementare i seguenti metodi: getSmartphone(),
 * getCodiceTransazione(), getData(), getPrezzo(), getDipendente(),
 * getCliente(), addSmartphone(Smartphone smartphone)
 * 
 * @author Luca Lomuscio
 *
 */
public interface Transazione {

	/**
	 * Metodo che restituisce la lista di smartphone della transazione. Gli
	 * smartphone sono tutti distinti.
	 * 
	 * @return Set<Smartphone>
	 */
	public Set<Smartphone> getSmartphone();

	/**
	 * Metodo che restituisce il codice identificativo della Transazione
	 * 
	 * @return codiceTransazione
	 */
	public long getCodiceTransazione();

	/**
	 * Metodo che restituisce la Data in cui è avvenuta la transazione
	 * 
	 * @return dataTransazione
	 */
	public Date getData();

	/**
	 * Metodo che restituisce il prezzo pagato dal cliente.
	 * 
	 * @return prezzo somma di denaro pagata dal cliente
	 */
	public double getPrezzo();

	/**
	 * Metodo che restituisce il dipendente che ha effettuato la transazione
	 * 
	 * @return dipendente
	 */
	public Dipendente getDipendente();

	/**
	 * Metodo che restituisce il cliente che ha effettuato la transazione
	 * 
	 * @return cliente
	 */
	public Cliente getCliente();

	/**
	 * Metodo che permette di aggiungere un nuovo smartphone alla transazione,
	 * diverso da quelli già presenti.
	 * 
	 * @param smartphone da aggiungere
	 */
	public void addSmartphone(Smartphone smartphone);

}
