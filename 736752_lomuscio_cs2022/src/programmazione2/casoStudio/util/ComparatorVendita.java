/**
 * 
 */
package programmazione2.casoStudio.util;

import java.util.Comparator;

import programmazione2.casoStudio.azioni.Vendita;

/**
 * @author lucal
 *
 */
public class ComparatorVendita implements Comparator<Vendita> {

	@Override
	public int compare(Vendita o1, Vendita o2) {
		int result = 0;

		result = o1.getDataDiVendita().compareTo(o2.getDataDiVendita());
		return result;
	}

}
