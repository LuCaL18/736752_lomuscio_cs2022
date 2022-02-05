/**
 * 
 */
package programmazione2.casoStudio.dispositivi;

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

	/**
	 * @param iMEI
	 * @param modello
	 * @param memoria
	 * @param ram
	 * @param processore
	 * @param risoluzione
	 * @param nomeDispositivo
	 */
	public AbstractSmartphone(int iMEI, String modello, int memoria, int ram, String processore, String risoluzione,
			String nomeDispositivo) {
		IMEI = iMEI;
		this.modello = modello;
		this.memoria = memoria;
		this.ram = ram;
		this.processore = processore;
		this.risoluzione = risoluzione;
		this.nomeDispositivo = nomeDispositivo;
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

}
