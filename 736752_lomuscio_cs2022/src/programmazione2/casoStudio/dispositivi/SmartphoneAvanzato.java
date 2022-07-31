/**
 * 
 */
package programmazione2.casoStudio.dispositivi;

/**
 * La classe SmartphoneAvanzato rappresenta il tipo di smartphone avanzato cioè
 * uno smartphone medio che ha in più il riconoscimento vocale e il
 * riconoscimento con impronta. La classe SmartphoneAvanzato estende la classe
 * SmartphoneMedio.
 * 
 * @author Luca Lomuscio
 *
 */
public class SmartphoneAvanzato extends SmartphoneMedio {

	/**
	 * Il SerialVersionUID viene sempre autogenerato dalla JVM. Serve a capire se
	 * una determinata classe che viene serializzata sia compatibile o meno con la
	 * versione della JVM.
	 */
	private static final long serialVersionUID = 3618269235228362726L;
	/*
	 * Valore che indica la presenza del riconoscimento vocale
	 */
	private boolean riconoscimentoVocale = true;
	/*
	 * Valore che indica la presenza del riconoscimento con impronta
	 */
	private boolean riconoscimentoImpronta = true;

	/**
	 * Costruttore della classe SmartphoneAvanzato. Permette di istanziare uno
	 * smartphone avanzato con tutti i suoi attributi. Solleva l'eccezione
	 * SmartphoneException qualora il costruttore della superclasse SmartphoneBase
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
	public SmartphoneAvanzato(String iMEI, String modello, int memoria, int ram, String processore, String risoluzione,
			String nomeDispositivo, String marca) throws SmartphoneException {
		super(iMEI, modello, memoria, ram, processore, risoluzione, nomeDispositivo, marca);
	}

	/**
	 * Metodo che restituisce il valore riconoscimentoVocale
	 * 
	 * @return riconoscimentoVocale valore che indica la presenza del riconoscimento
	 *         vocale
	 */
	public boolean isRiconoscimentoVocale() {
		return riconoscimentoVocale;
	}

	/**
	 * Metodo che restituisce il valore riconoscimentoImpronta
	 * 
	 * @return riconoscimentoImpronta valore che indica la presenza del
	 *         riconoscimento con impronta
	 */
	public boolean isRiconoscimentoImpronta() {
		return riconoscimentoImpronta;
	}

	/**
	 * Override del metodo "toString" . Restituisce una stringa che rappresenta
	 * l'istanza di uno smartphone Avanzato con le sue caratteristiche
	 * 
	 * @return stringa composta dai parametri identificativi dello smartphone
	 *         Avanzato
	 */
	@Override
	public String toString() {
		return "SmartphoneAvanzato [IMEI=" + IMEI + ", modello=" + modello + ", memoria=" + memoria + ", ram=" + ram
				+ ", processore=" + processore + ", risoluzione=" + risoluzione + ", nomeDispositivo=" + nomeDispositivo
				+ ", marca=" + marca + ", riconoscimentoVocale=" + riconoscimentoVocale + ", riconoscimentoImpronta="
				+ riconoscimentoImpronta + ", doppiaFotocamera=" + doppiaFotocamera + ", inNoleggio=" + inNoleggio
				+ "]";
	}

}
