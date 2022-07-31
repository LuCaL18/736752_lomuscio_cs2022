/**
 * 
 */
package programmazione2.casoStudio.azioni;

import java.util.Date;

import programmazione2.casoStudio.ruoli.Cliente;
import programmazione2.casoStudio.ruoli.Dipendente;

/**
 * Classe che rappresenta il tipo Transazione Vendita. Estende la classe
 * AbstractTransazione.
 * 
 * @author Luca Lomuscio
 *
 */
public class Vendita extends AbstractTransazione {

	/*
	 * Il SerialVersionUID viene sempre autogenerato dalla JVM. Serve a capire se
	 * una determinata classe che viene serializzata sia compatibile o meno con la
	 * versione della JVM
	 */
	private static final long serialVersionUID = -8637109900101954444L;

	/**
	 * Costruttore della classe Vnedita che permette di sitanziare un oggetto di
	 * tipo Vendita con tutti i suoi attributi ad eccezione della lista degli
	 * smartphone che è vuuota al momento dell'istanziazione..
	 * 
	 * @param data       data della vendita
	 * @param prezzo     somma di denaro che deve pagare il cliente
	 * @param dipendente dipendente che ha effettuato la vendita
	 * @param cliente    cliente che ha effettuato la vendita
	 */
	public Vendita(Date data, double prezzo, Dipendente dipendente, Cliente cliente) {
		super(data, prezzo, dipendente, cliente);
	}

	/**
	 * Override del metodo "toString" . Restituisce una stringa che rappresenta
	 * l'istanza di una Vendita con le sue caratteristiche
	 * 
	 * @return stringa composta dai parametri identificativi della Vendita.
	 */
	@Override
	public String toString() {
		return "Vendita [smartphone=" + smartphone + ", data=" + data + ", prezzo=" + prezzo + ", dipendente="
				+ dipendente + ", " + cliente + ", codiceTransazione=" + codiceTransazione + "]";
	}

}
