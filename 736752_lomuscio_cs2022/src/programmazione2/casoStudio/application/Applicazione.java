/**
 * 
 */
package programmazione2.casoStudio.application;

import java.util.LinkedList;
import java.util.List;

import programmazione2.casoStudio.azioni.Noleggio;
import programmazione2.casoStudio.azioni.Vendita;
import programmazione2.casoStudio.dispositivi.Smartphone;
import programmazione2.casoStudio.dispositivi.SmartphoneAvanzato;

/**
 * @author lucal
 *
 */
public class Applicazione {

	// private Set<Dipendente> dipendenti = new HashSet<Dipendente>();
	// private List<Cliente> clienti = new ArrayList<Cliente>();
	private List<Vendita> vendite = new LinkedList<Vendita>();
	private List<Noleggio> noleggi = new LinkedList<Noleggio>();
	private List<Smartphone> catalogoSmartphone = new LinkedList<Smartphone>();

	public void printMenu() {
		System.out.println("1.vendita telefono\n" + "2.noleggio telefono\n" + "3.elenco smartphone noleggiati\n"
				+ "4.elenco smartphone mai noleggiati\n" + "5.smartphone venduti da un dipendente\n" + "6.esci\n");
	}

	public void addSmartphone(Smartphone smartphone) throws AppException {
		if (catalogoSmartphone.contains(smartphone)) {
			throw new AppException("Smartphone già presente nel catalogo");
		}
		catalogoSmartphone.add(smartphone);
	}

	public void removeSmartphone(Smartphone smartphone) throws AppException {
		if (catalogoSmartphone.contains(smartphone)) {
			throw new AppException("Smartphone non presente nel catalogo");
		}
		catalogoSmartphone.remove(smartphone);
	}

	private boolean isAvailable(Smartphone smartphone) {
		return catalogoSmartphone.contains(smartphone);
	}

	public void venditaSmartphone(Vendita vendita) throws AppException {
		for (Smartphone smartphone : vendita.getSmartphoneVenduti()) {
			if (!this.isAvailable(smartphone) || smartphone.getClass() == SmartphoneAvanzato.class)
				throw new AppException("smartphone non acquistabile");
		}
		this.vendite.add(vendita);
		this.catalogoSmartphone.removeAll(vendita.getSmartphoneVenduti());
	}

	public void noleggiaSmartphone(Noleggio noleggio) throws AppException {
		for (Smartphone smartphone : noleggio.getSmartphoneNoleggiati()) {
			if (!this.isAvailable(smartphone))
				throw new AppException("smartphone non acquistabile");
		}
		this.noleggi.add(noleggio);
		this.catalogoSmartphone.removeAll(noleggio.getSmartphoneNoleggiati());
	}

}
