/**
 * 
 */
package programmazione2.casoStudio.util;

import java.util.Comparator;

import programmazione2.casoStudio.azioni.Transazione;

/**
 * @author lucal
 *
 */
public class ComparatorTransazioneData implements Comparator<Transazione> {

	@Override
	public int compare(Transazione o1, Transazione o2) {
		int result = 0;

		result = o1.getData().compareTo(o2.getData());
		return result;
	}

}
