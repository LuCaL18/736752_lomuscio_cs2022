/**
 * 
 */
package programmazione2.casoStudio.azioni;

import java.util.Date;
import java.util.Set;

import programmazione2.casoStudio.dispositivi.Smartphone;
import programmazione2.casoStudio.ruoli.Dipendente;

/**
 * @author lucal
 *
 */
public interface Transazione {

	public Set<Smartphone> getSmartphone();

	public Date getData();

	public double getPrezzo();

	public Dipendente getDipendente();

	public void addSmartphone(Smartphone smartphone);

}
