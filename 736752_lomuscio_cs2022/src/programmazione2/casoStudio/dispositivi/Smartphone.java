/**
 * 
 */
package programmazione2.casoStudio.dispositivi;

import java.lang.Cloneable;

/**
 * Interfaccia che rappresenta uno Smartphone generico, estende l'interfaccia
 * Cloneable. La classe che implementa l'interfaccia smartphone deve
 * implementare i seguenti metodi: getIMEI(), getModello(), getProcessore(),
 * getRisoluzione(), getNomeDispositivo(), getMarca(), getRam(), getMemoria(),
 * clone(), toString(), isInNoleggio(), setInNoleggio(boolean inNoleggio).
 * 
 * @author Luca Lomuscio
 *
 */
public interface Smartphone extends Cloneable {

	/**
	 * Metodo che restituisce il codice IMEI, un codice numerico identificativo di
	 * 15 cifre, di uno Smartphone
	 * 
	 * @return IMEI
	 */
	public String getIMEI();

	/**
	 * Metodo che restituisce il modello dello Smartphone
	 * 
	 * @return modello
	 */
	public String getModello();

	/**
	 * Metodo che restituisce il processore dello Smartphone
	 * 
	 * @return processore
	 */
	public String getProcessore();

	/**
	 * Metodo che restituisce la risoluzione dello Smartphone
	 * 
	 * @return risoluzione
	 */
	public String getRisoluzione();

	/**
	 * Metodo che restituisce il nome dello Smartphone
	 * 
	 * @return nomeDispositivo
	 */
	public String getNomeDispositivo();

	/**
	 * Metodo che restituisce la marca dello Smartphone
	 * 
	 * @return marca
	 */
	public String getMarca();

	/**
	 * Metodo che restituisce la ram dello Smartphone
	 * 
	 * @return ram
	 */
	public int getRam();

	/**
	 * Metodo che restituisce la memoria dello Smartphone
	 * 
	 * @return memoria
	 */
	public int getMemoria();

	/**
	 * Override del metodo clone(). Esegue un'operazione di clonazione dell'oggetto
	 * che implementa l'interfaccia Smartphone.
	 * 
	 * @return smartphone clone dell'oggetto
	 */
	public Smartphone clone();

	/**
	 * Metodo che restituisce true se lo smartphone è attualmente in noleggio.
	 * Altrimenti false.
	 * 
	 * @return inNoleggio
	 */
	public boolean isInNoleggio();

	/**
	 * Metodo che permette di modificare lo stato dello smartphone da in
	 * noleggio(true) a disponibile(false) modificando il valore inNoleggio.
	 * 
	 * @param inNoleggio
	 */
	public void setInNoleggio(boolean inNoleggio);

}
