/**
 * 
 */
package programmazione2.casoStudio.dispositivi;

import java.util.Objects;

/**
 * @author lucal
 *
 */
abstract class AbstractSmartphone implements Smartphone {

	protected int IMEI;
	protected String modello;
	protected int memoria;
	protected int ram;
	protected String processore;
	protected String risoluzione;
	protected String nomeDispositivo;
	protected String marca;

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
	public AbstractSmartphone(int iMEI, String modello, int memoria, int ram, String processore, String risoluzione,
			String nomeDispositivo, String marca) {
		IMEI = iMEI;
		this.modello = modello;
		this.memoria = memoria;
		this.ram = ram;
		this.processore = processore;
		this.risoluzione = risoluzione;
		this.nomeDispositivo = nomeDispositivo;
		this.marca = marca;
	}

	@Override
	public int getIMEI() {
		return IMEI;
	}

	@Override
	public String getModello() {
		return modello;
	}

	@Override
	public String getProcessore() {
		return processore;
	}

	@Override
	public String getRisoluzione() {
		return risoluzione;
	}

	@Override
	public String getNomeDispositivo() {
		return nomeDispositivo;
	}

	@Override
	public int getRam() {
		return ram;
	}

	@Override
	public int getMemoria() {
		return memoria;
	}

	@Override
	public String getMarca() {
		return marca;
	}

	@Override
	public int hashCode() {
		return Objects.hash(IMEI);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractSmartphone other = (AbstractSmartphone) obj;
		return IMEI == other.IMEI;
	}

	@Override
	public String toString() {
		return "AbstractSmartphone [IMEI=" + IMEI + ", modello=" + modello + ", memoria=" + memoria + ", ram=" + ram
				+ ", processore=" + processore + ", risoluzione=" + risoluzione + ", nomeDispositivo=" + nomeDispositivo
				+ ", marca=" + marca + "]";
	}

}
