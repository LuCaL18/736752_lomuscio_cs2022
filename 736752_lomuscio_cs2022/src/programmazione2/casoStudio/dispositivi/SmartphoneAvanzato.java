/**
 * 
 */
package programmazione2.casoStudio.dispositivi;

/**
 * @author lucal
 *
 */
public class SmartphoneAvanzato extends SmartphoneMedio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3618269235228362726L;
	private boolean riconoscimentoVocale = true;
	private boolean riconoscimentoImpronta = true;

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
	public SmartphoneAvanzato(int iMEI, String modello, int memoria, int ram, String processore, String risoluzione,
			String nomeDispositivo, String marca) {
		super(iMEI, modello, memoria, ram, processore, risoluzione, nomeDispositivo, marca);
	}

	/**
	 * @return the riconoscimentoVocale
	 */
	public boolean isRiconoscimentoVocale() {
		return riconoscimentoVocale;
	}

	/**
	 * @return the riconoscimentoImpronta
	 */
	public boolean isRiconoscimentoImpronta() {
		return riconoscimentoImpronta;
	}

	@Override
	public String toString() {
		return "SmartphoneAvanzato [riconoscimentoVocale=" + riconoscimentoVocale + ", riconoscimentoImpronta="
				+ riconoscimentoImpronta + ", doppiaFotocamera=" + doppiaFotocamera + ", IMEI=" + IMEI + ", modello="
				+ modello + ", memoria=" + memoria + ", ram=" + ram + ", processore=" + processore + ", risoluzione="
				+ risoluzione + ", nomeDispositivo=" + nomeDispositivo + ", marca=" + marca + "]";
	}

}
