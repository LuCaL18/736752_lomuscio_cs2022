/**
 * 
 */
package programmazione2.casoStudio.ruoli;

import java.util.Date;

/**
 * @author lucal
 *
 */
public interface Persona {

	public String getNome();

	public String getCognome();

	public Date getDataDiNascita();

	public void setSmartphoneNoleggiati(int smartphoneNonPiuNoleggiati);

	public void addNoleggio(int smartphoneDaNoleggiare) throws Exception;
}
