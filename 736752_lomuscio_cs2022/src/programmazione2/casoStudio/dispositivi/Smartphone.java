/**
 * 
 */
package programmazione2.casoStudio.dispositivi;

/**
 * @author lucal
 *
 */
public interface Smartphone {

	public int getIMEI();

	public String getModello();

	public String getProcessore();

	public String getRisoluzione();

	public String getNomeDispositivo();
	
	public String getMarca();

	public int getRam();
	
	public int getMemoria();
	
	@Override
	public String toString();
}
