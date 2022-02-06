/**
 * 
 */
package programmazione2.casoStudio.dispositivi;

/**
 * @author lucal
 *
 */
public class SmartphoneMedio extends AbstractSmartphone {

	protected boolean doppiaFotocamera = true;

	/**
	 * @param iMEI
	 * @param modello
	 * @param memoria
	 * @param ram
	 * @param processore
	 * @param risoluzione
	 * @param nomeDispositivo
	 * @param marca
	 */
	public SmartphoneMedio(int iMEI, String modello, int memoria, int ram, String processore, String risoluzione,
			String nomeDispositivo, String marca) {
		super(iMEI, modello, memoria, ram, processore, risoluzione, nomeDispositivo, marca);
	}

	/**
	 * @return the doppiaFotocamera
	 */
	public boolean isDoppiaFotocamera() {
		return doppiaFotocamera;
	}

	@Override
	public String toString() {
		return "SmartphoneMedio [doppiaFotocamera=" + doppiaFotocamera + ", IMEI=" + IMEI + ", modello=" + modello
				+ ", memoria=" + memoria + ", ram=" + ram + ", processore=" + processore + ", risoluzione="
				+ risoluzione + ", nomeDispositivo=" + nomeDispositivo + ", marca=" + marca + "]";
	}

}