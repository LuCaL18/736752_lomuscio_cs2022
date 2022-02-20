/**
 * 
 */
package programmazione2.casoStudio.dispositivi;

import java.lang.Cloneable;

/**
 * @author lucal
 *
 */
public interface Smartphone extends Cloneable {

	public int getIMEI();

	public String getModello();

	public String getProcessore();

	public String getRisoluzione();

	public String getNomeDispositivo();

	public String getMarca();

	public int getRam();

	public int getMemoria();

	public Smartphone clone();

	public String toString();

}
