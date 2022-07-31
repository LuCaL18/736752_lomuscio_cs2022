/**
 * 
 */
package programmazione2.casoStudio.dispositivi;

import java.io.Serializable;
import java.util.Objects;

/**
 * La classe SmartphoneBase rappresenta lo smartphone di tipo Base e contiene
 * tutti gli attributi di uno smatphone generico. Implementa le interfacce
 * Smartphone e Serrializable. I suoi attributi sono : IMEI (codice numerico
 * identificativo di 15 cifre), modello, memoria, ram, processore, risoluzione,
 * nome del dispositivo, marca e infine un flag che indica se lo smartphone è
 * momentaneamente in noleggio.
 * 
 * @author Luca Lomuscio
 *
 */
public class SmartphoneBase implements Smartphone, Serializable {

	/**
	 * Il SerialVersionUID viene sempre autogenerato dalla JVM. Serve a capire se
	 * una determinata classe che viene serializzata sia compatibile o meno con la
	 * versione della JVM.
	 */
	private static final long serialVersionUID = 2863242326712831696L;

	/*
	 * Valore che indica il codice IMEI dello smartphone
	 */
	protected String IMEI;
	/*
	 * Valore che indica il modello dello smartphone
	 */
	protected String modello;
	/*
	 * Valore che indica il memoria dello smartphone
	 */
	protected int memoria;
	/*
	 * Valore che indica il ram dello smartphone
	 */
	protected int ram;
	/*
	 * Valore che indica il processore dello smartphone
	 */
	protected String processore;
	/*
	 * Valore che indica il risoluzione dello smartphone
	 */
	protected String risoluzione;
	/*
	 * Valore che indica il nome dispositivo dello smartphone
	 */
	protected String nomeDispositivo;
	/*
	 * Valore che indica il marca dello smartphone
	 */
	protected String marca;
	/*
	 * Valore che indica se lo smartphone è in noleggio
	 */
	protected boolean inNoleggio;

	/**
	 * Costruttore della classe SmartphoneBase. Permette di istanziare uno
	 * smartphone Base con tutti i suoi attributi. Solleva l'eccezione
	 * SmartphoneException qualora il codice IMEI non sia numerico o non abbia esattamente
	 * 15 cifre.
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
	public SmartphoneBase(String iMEI, String modello, int memoria, int ram, String processore, String risoluzione,
			String nomeDispositivo, String marca) throws SmartphoneException {

		if (!checkIMEI(iMEI)) {
			throw new SmartphoneException("Codice IMEI non valido (deve contenere esattamente 15 caratteri numerici)");
		}

		IMEI = iMEI;
		this.modello = modello;
		this.memoria = memoria;
		this.ram = ram;
		this.processore = processore;
		this.risoluzione = risoluzione;
		this.nomeDispositivo = nomeDispositivo;
		this.marca = marca;
		this.inNoleggio = false;
	}

	/**
	 * Metodo che restituisce il codice IMEI, un codice numerico identificativo di
	 * 15 cifre, di uno Smartphone
	 * 
	 * @return IMEI
	 */
	@Override
	public String getIMEI() {
		return IMEI;
	}

	/**
	 * Metodo che restituisce il modello dello Smartphone
	 * 
	 * @return modello
	 */
	@Override
	public String getModello() {
		return modello;
	}

	/**
	 * Metodo che restituisce il processore dello Smartphone
	 * 
	 * @return processore
	 */
	@Override
	public String getProcessore() {
		return processore;
	}

	/**
	 * Metodo che restituisce la risoluzione dello Smartphone
	 * 
	 * @return risoluzione
	 */
	@Override
	public String getRisoluzione() {
		return risoluzione;
	}

	/**
	 * Metodo che restituisce il nome dello Smartphone
	 * 
	 * @return nomeDispositivo
	 */
	@Override
	public String getNomeDispositivo() {
		return nomeDispositivo;
	}

	/**
	 * Metodo che restituisce la ram dello Smartphone
	 * 
	 * @return ram
	 */
	@Override
	public int getRam() {
		return ram;
	}

	/**
	 * Metodo che restituisce la memoria dello Smartphone
	 * 
	 * @return memoria
	 */
	@Override
	public int getMemoria() {
		return memoria;
	}

	/**
	 * Metodo che restituisce la marca dello Smartphone
	 * 
	 * @return marca
	 */
	@Override
	public String getMarca() {
		return marca;
	}

	/**
	 * Metodo che restituisce true se lo smartphone è attualmente in noleggio.
	 * Altrimenti false.
	 * 
	 * @return inNoleggio
	 */
	@Override
	public boolean isInNoleggio() {
		return inNoleggio;
	}

	/**
	 * Metodo che permette di modificare lo stato dello smartphone da in
	 * noleggio(true) a disponibile(false) modificando il valore inNoleggio.
	 * 
	 * @param inNoleggio
	 */
	@Override
	public void setInNoleggio(boolean inNoleggio) {
		this.inNoleggio = inNoleggio;
	}

	/**
	 * Override del metodo hashCode. Restituisce un valore di codice hash per
	 * l'oggetto. Il metodo deve restituire costantemente lo stesso intero per
	 * smartphone che hanno lo stesso codice IMEI
	 * 
	 * @return int codice hash
	 */
	@Override
	public int hashCode() {
		return Objects.hash(IMEI);
	}

	/**
	 * Override del metodo equals(). Indica se qualche altro oggetto è uguale a
	 * questo.
	 * 
	 * @return boolean
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SmartphoneBase other = (SmartphoneBase) obj;
		return IMEI == other.IMEI;
	}

	/**
	 * Override del metodo clone(). Esegue un'operazione di clonazione dell'oggetto
	 * che implementa l'interfaccia Smartphone.
	 * 
	 * @return smartphone clone dell'oggetto
	 */
	@Override
	public Smartphone clone() {
		Smartphone result = null;
		try {
			result = (Smartphone) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * Override del metodo "toString" . Restituisce una stringa che rappresenta
	 * l'istanza di uno smartphone Base con le sue caratteristiche
	 * 
	 * @return stringa composta dai parametri identificativi dello smartphone Base
	 */
	@Override
	public String toString() {
		return "SmartphoneBase [IMEI=" + IMEI + ", modello=" + modello + ", memoria=" + memoria + ", ram=" + ram
				+ ", processore=" + processore + ", risoluzione=" + risoluzione + ", nomeDispositivo=" + nomeDispositivo
				+ ", marca=" + marca + ", inNoleggio=" + inNoleggio + "]";
	}

	/**
	 * Metodo che controlla che l'imei sia numerico e abbia 15 cifre.
	 * 
	 * @param imei
	 * @return boolean
	 */
	private boolean checkIMEI(String imei) {
		return imei.matches("[0-9]+") && imei.length() == 15;
	}

}
