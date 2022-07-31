/**
 * 
 */
package programmazione2.casoStudio.util;

import java.util.Comparator;

import programmazione2.casoStudio.azioni.Transazione;

/**
 * La classe ComparatorTransazioneData implementa l'interfaccia Comparator e
 * permette l'ordinamento di elementi Transazione.
 * 
 * @author Luca Lomuscio
 *
 */
public class ComparatorTransazioneData implements Comparator<Transazione> {

	/**
	 * Override del metodo "compare" dell'interfaccia Comparator che confronta i
	 * valori della data di 2 transazioni
	 */
	@Override
	public int compare(Transazione o1, Transazione o2) {
		int result = 0;

		result = o1.getData().compareTo(o2.getData());
		return result;
	}

}
