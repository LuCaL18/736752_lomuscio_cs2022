/**
 * 
 */
package programmazione2.casoStudio.dispositivi;

/**
 * La classe SmartphoneMedio rappresenta il tipo di smartphone medio cioè uno
 * smartphone base che ha in più una doppia fotocamera. La classe
 * SmartphoneMedio estende la classe SmartphoneBase.
 * 
 * @author Luca Lomuscio
 *
 */
public class SmartphoneMedio extends SmartphoneBase {

	/**
	 * Il SerialVersionUID viene sempre autogenerato dalla JVM. Serve a capire se
	 * una determinata classe che viene serializzata sia compatibile o meno con la
	 * versione della JVM.
	 */
	private static final long serialVersionUID = -6467384657363053232L;
	/**
	 * Valore che indica la presenza della doppia fotocamera nello smartphone
	 */
	protected boolean doppiaFotocamera = true;

	/**
	 * Costruttore della classe SmartphoneMedio. Permette di istanziare uno
	 * smartphone medio con tutti i suoi attributi. Solleva l'eccezione
	 * SmartphoneException qualora il costruttore della classe padre SmartphoneBase
	 * lo sollevi. L'eccezione è sollevata se il codice imei non è numrico o non ha
	 * esattamente 15 cifre.
	 * 
	 * @param iMEI
	 * @param modello
	 * @param memoria
	 * @param ram
	 * @param processore
	 * @param risoluzione
	 * @param nomeDispositivo
	 * @param marca
	 * @throws SmartphoneException
	 */
	public SmartphoneMedio(String iMEI, String modello, int memoria, int ram, String processore, String risoluzione,
			String nomeDispositivo, String marca) throws SmartphoneException {
		super(iMEI, modello, memoria, ram, processore, risoluzione, nomeDispositivo, marca);
	}

	/**
	 * Metodo che restituisce il valore doppiaFotocamera
	 * 
	 * @return doppiaFotocamera valore che indica la presenza della doppia
	 *         fotocamera
	 */
	public boolean isDoppiaFotocamera() {
		return doppiaFotocamera;
	}

	/**
	 * Override del metodo "toString" . Restituisce una stringa che rappresenta
	 * l'istanza di uno smartphone Medio con le sue caratteristiche
	 * 
	 * @return stringa composta dai parametri identificativi dello smartphone Medio
	 */
	@Override
	public String toString() {
		return "SmartphoneMedio [IMEI=" + IMEI + ", modello=" + modello + ", memoria=" + memoria + ", ram=" + ram
				+ ", processore=" + processore + ", risoluzione=" + risoluzione + ", nomeDispositivo=" + nomeDispositivo
				+ ", marca=" + marca + ", doppiaFotocamera=" + doppiaFotocamera + ", inNoleggio=" + inNoleggio + "]";
	}

}
