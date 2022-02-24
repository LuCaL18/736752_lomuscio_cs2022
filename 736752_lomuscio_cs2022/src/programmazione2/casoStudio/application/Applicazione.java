/**
 * 
 */
package programmazione2.casoStudio.application;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import programmazione2.casoStudio.azioni.Noleggio;
import programmazione2.casoStudio.azioni.Vendita;
import programmazione2.casoStudio.dispositivi.Smartphone;
import programmazione2.casoStudio.dispositivi.SmartphoneAvanzato;
import programmazione2.casoStudio.ruoli.Dipendente;
import programmazione2.casoStudio.util.ComparatorTransazioneData;

/**
 * @author lucal
 *
 */
public class Applicazione {

	// private Set<Dipendente> dipendenti = new HashSet<Dipendente>();
	// private List<Cliente> clienti = new ArrayList<Cliente>();
	private List<Vendita> vendite;
	private List<Noleggio> noleggi;
	private Set<Smartphone> catalogoSmartphone;

	public Applicazione() {
		this.vendite = new LinkedList<Vendita>();
		this.noleggi = new LinkedList<Noleggio>();
		this.catalogoSmartphone = new HashSet<Smartphone>();
	}

	/**
	 * @param vendite
	 * @param noleggi
	 * @param catalogoSmartphone
	 */
	public Applicazione(List<Vendita> vendite, List<Noleggio> noleggi, Set<Smartphone> catalogoSmartphone) {
		this.vendite = vendite;
		this.noleggi = noleggi;
		this.catalogoSmartphone = catalogoSmartphone;
	}

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

	public void venditaSmartphone(Vendita vendita) throws AppException {
		for (Smartphone smartphone : vendita.getSmartphone()) {
			if (!catalogoSmartphone.contains(smartphone) || smartphone.getClass() == SmartphoneAvanzato.class)
				throw new AppException("lo smartphone con IMEI" + smartphone.getIMEI() + "non acquistabile");
		}
		this.vendite.add(vendita);
		this.catalogoSmartphone.removeAll(vendita.getSmartphone());
	}

	public void noleggiaSmartphone(Noleggio noleggio) throws AppException {
		for (Smartphone smartphone : noleggio.getSmartphone()) {
			if (!catalogoSmartphone.contains(smartphone))
				throw new AppException("impossibile noleggiare smartphone");
		}
		this.noleggi.add(noleggio);
		this.catalogoSmartphone.removeAll(noleggio.getSmartphone());
	}

	/*
	 * public void addDipendente(Dipendente dipendente) {
	 * this.dipendenti.add(dipendente); }
	 * 
	 * public void removeDipendente(String idDipendente) { for (Dipendente
	 * dipendente : dipendenti) { if (dipendente.getId() == idDipendente) {
	 * this.dipendenti.remove(dipendente); } } }
	 */

	public Set<Smartphone> elencoSmartphoneNoleggiati() {
		Set<Smartphone> elenco = new LinkedHashSet<Smartphone>();
		Set<Noleggio> noleggiOrdinati = new TreeSet<Noleggio>(new ComparatorTransazioneData());
		noleggiOrdinati.addAll(noleggi);

		for (Noleggio noleggio : noleggiOrdinati) {
			for (Smartphone smartphone : noleggio.getSmartphone()) {
				elenco.add(smartphone.clone());
			}
		}
		noleggiOrdinati = null;
		return elenco;
	}

	public Set<Smartphone> elencoSmartphoneMaiNoleggiati() {
		Set<Smartphone> elenco = new HashSet<Smartphone>();
		for (Smartphone smartphone : catalogoSmartphone) {
			elenco.add(smartphone.clone());
		}

		elenco.removeAll(this.elencoSmartphoneNoleggiati());

		return elenco;
	}

	public Set<Smartphone> smartphoneVendutiDaDipendente(Dipendente dipendente) {
		Set<Smartphone> result = new LinkedHashSet<Smartphone>();
		Set<Vendita> venditeOrdinate = new TreeSet<Vendita>(new ComparatorTransazioneData());
		venditeOrdinate.addAll(vendite);

		for (Vendita vendita : venditeOrdinate) {
			if (vendita.getDipendente().equals(dipendente)) {
				for (Smartphone smartphone : vendita.getSmartphone()) {
					result.add(smartphone.clone());
				}
			}
		}
		venditeOrdinate = null;
		return result;
	}

	public Smartphone getSmartphoneDaCodice(int codiceIMEI) {
		for (Smartphone smartphone : catalogoSmartphone) {
			if (smartphone.getIMEI() == codiceIMEI)
				return smartphone;
		}

		return null;
	}

	public boolean fineNoleggio(Noleggio noleggioFinito) {
		boolean result = true;
		for (Noleggio noleggio : noleggi) {
			if (noleggio.equals(noleggioFinito)) {
				if (noleggio.getDataFine().before(new Date()))
					result = false;
				catalogoSmartphone.addAll(noleggio.getSmartphone());
			}
		}
		return result;
	}

}
