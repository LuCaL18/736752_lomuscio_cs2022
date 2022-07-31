/**
 * 
 */
package programmazione2.casoStudio.azioni;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import programmazione2.casoStudio.dispositivi.Smartphone;
import programmazione2.casoStudio.ruoli.Cliente;
import programmazione2.casoStudio.ruoli.Dipendente;

/**
 * La classe AbstractTransazione è una classe astratta che rappresenta una
 * transazione generica con i suoi attributi: data transazione, prezzo,
 * dipendente che ha effettuato la transazione, cliente che ha effettuato la
 * transazione, codice Transazione e la lista degli smartphone. La transazione
 * rappresenta il concetto generale di noleggio e vendita.
 * 
 * @author Luca Lomuscio
 *
 */
abstract class AbstractTransazione implements Transazione, Serializable, Cloneable {

	/*
	 * Il SerialVersionUID viene sempre autogenerato dalla JVM. Serve a capire se
	 * una determinata classe che viene serializzata sia compatibile o meno con la
	 * versione della JVM
	 */
	private static final long serialVersionUID = 2729698442072161211L;
	/**
	 * Valore che indica una lista di smartphone. All'interno non sono presenti
	 * duplicati
	 */
	protected Set<Smartphone> smartphone;
	/**
	 * Valore che indica la data della transazione
	 */
	protected Date data;
	/**
	 * valore che indica il prezzo pagato dal cliente
	 */
	protected double prezzo;
	/**
	 * Valore che indica il dipendente che ha effettuato la transazione
	 */
	protected Dipendente dipendente;
	/**
	 * Valore che indica il codice identificativo della transazione
	 */
	protected long codiceTransazione;
	/**
	 * valore che indica il cliente che ha effettuato la transazione
	 */
	protected Cliente cliente;
	/**
	 * Contatore utilizzato per determinare il codice della transazione
	 */
	private static long codicePrec = 0;

	/**
	 * Costruttore della classe AbstractTransazione che permette di istanziare un
	 * tipo concreto di transazione con i suoi attributi generici ad eccezione della
	 * lista degli smartphone che è vuuota al momento dell'istanziazione.
	 * 
	 * @param data       data della transazione
	 * @param prezzo     valore pagato dal cliente
	 * @param dipendente dipendente che effettua la transazione
	 * @param cliente    cliente che effettua la transazione
	 */
	public AbstractTransazione(Date data, double prezzo, Dipendente dipendente, Cliente cliente) {
		this.smartphone = new HashSet<Smartphone>();
		this.data = data;
		this.prezzo = prezzo;
		this.dipendente = dipendente;
		this.cliente = cliente;
		this.codiceTransazione = ++codicePrec;

	}

	/**
	 * Metodo che restituisce il valore del codice identificativo della transazione
	 * 
	 * @return codiceTransazione
	 */
	public long getCodiceTransazione() {
		return codiceTransazione;
	}

	/**
	 * Metodo che restituisce la lista di smartphone della transazione. Gli
	 * smartphone sono tutti distinti.
	 * 
	 * @return Set<Smartphone>
	 */
	@Override
	public Set<Smartphone> getSmartphone() {
		return smartphone;
	}

	/**
	 * Metodo che restituisce la Data in cui è avvenuta la transazione
	 * 
	 * @return dataTransazione
	 */
	@Override
	public Date getData() {
		return data;
	}

	/**
	 * Metodo che restituisce il prezzo pagato dal cliente.
	 * 
	 * @return prezzo somma di denaro pagata dal cliente
	 */
	@Override
	public double getPrezzo() {
		return prezzo;
	}

	/**
	 * Metodo che restituisce il dipendente che ha effettuato la transazione
	 * 
	 * @return dipendente
	 */
	@Override
	public Dipendente getDipendente() {
		return dipendente;
	}

	/**
	 * Metodo che restituisce il contatore per i codici transazione
	 * 
	 * @return codicePrec contatore codici transazione
	 */
	public static long getCodicePrec() {
		return codicePrec;
	}

	/**
	 * Metodo che permette di modificare il contatore per i codici transazione
	 * 
	 * @param codicePrec il valore da assegnare al contatore codicePrec
	 */
	public static void setCodicePrec(long codicePrec) {
		AbstractTransazione.codicePrec = codicePrec;
	}

	/**
	 * Metodo che restituisce il cliente che ha effettuato la transazione
	 * 
	 * @return cliente
	 */
	@Override
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * Metodo che permette di aggiungere un nuovo smartphone alla transazione,
	 * diverso da quelli già presenti.
	 * 
	 * @param smartphone da aggiungere
	 */
	@Override
	public void addSmartphone(Smartphone smartphone) {
		this.smartphone.add(smartphone);
	}

	/**
	 * Override del metodo hashCode. Restituisce un valore di codice hash per
	 * l'oggetto. Il metodo deve restituire costantemente lo stesso intero per
	 * transazioni che hanno lo stesso codice transazione
	 * 
	 * @return int codice hash
	 */
	@Override
	public int hashCode() {
		return Objects.hash(codiceTransazione);
	}

	/**
	 * Override del metodo clone(). Esegue un'operazione di clonazione dell'oggetto
	 * che implementa l'interfaccia Transazione.
	 * 
	 * @return result clone dell'oggetto che rappresenta la Transazione
	 */
	@Override
	public Transazione clone() {
		Transazione result = null;
		try {
			result = (Transazione) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * Override del metodo equals(). Indica se qualche altro oggetto è uguale a
	 * questo.
	 * 
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
		AbstractTransazione other = (AbstractTransazione) obj;
		return codiceTransazione == other.codiceTransazione;
	}

}
