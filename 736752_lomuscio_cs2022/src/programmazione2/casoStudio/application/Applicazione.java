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
import programmazione2.casoStudio.ruoli.Cliente;
import programmazione2.casoStudio.ruoli.ClienteException;
import programmazione2.casoStudio.ruoli.Dipendente;
import programmazione2.casoStudio.ruoli.DipendenteException;
import programmazione2.casoStudio.util.ComparatorTransazioneData;

/**
 * @author lucal
 *
 */
public class Applicazione {

	private Set<Dipendente> dipendenti;
	private Set<Cliente> clienti;
	private List<Vendita> vendite;
	private List<Noleggio> noleggi;
	private Set<Smartphone> catalogoSmartphone;

	public Applicazione() {
		this.vendite = new LinkedList<Vendita>();
		this.noleggi = new LinkedList<Noleggio>();
		this.catalogoSmartphone = new HashSet<Smartphone>();
		this.clienti = new HashSet<Cliente>();
		this.dipendenti = new HashSet<Dipendente>();
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

	/**
	 * @return the dipendenti
	 */
	public Set<Dipendente> getDipendenti() {
		return dipendenti;
	}

	/**
	 * @param dipendenti the dipendenti to set
	 */
	public void setDipendenti(Set<Dipendente> dipendenti) {
		this.dipendenti = dipendenti;
	}

	/**
	 * @return the clienti
	 */
	public Set<Cliente> getClienti() {
		return clienti;
	}

	/**
	 * @param clienti the clienti to set
	 */
	public void setClienti(Set<Cliente> clienti) {
		this.clienti = clienti;
	}

	/**
	 * @return the vendite
	 */
	public List<Vendita> getVendite() {
		return vendite;
	}

	/**
	 * @param vendite the vendite to set
	 */
	public void setVendite(List<Vendita> vendite) {
		this.vendite = vendite;
	}

	/**
	 * @return the noleggi
	 */
	public List<Noleggio> getNoleggi() {
		return noleggi;
	}

	/**
	 * @param noleggi the noleggi to set
	 */
	public void setNoleggi(List<Noleggio> noleggi) {
		this.noleggi = noleggi;
	}

	/**
	 * @return the catalogoSmartphone
	 */
	public Set<Smartphone> getCatalogoSmartphone() {
		return catalogoSmartphone;
	}

	/**
	 * @param catalogoSmartphone the catalogoSmartphone to set
	 */
	public void setCatalogoSmartphone(Set<Smartphone> catalogoSmartphone) {
		this.catalogoSmartphone = catalogoSmartphone;
	}

	public String menu() {
		String result = "1.aggiungi Smartphone\n" + "2.nuovo cliente\n" + "3.nuovo dipendente\n"
				+ "4.rimuovi dipendente\n" + "5.vendita telefono\n" + "6.noleggio telefono\n"
				+ "7.elenco smartphone noleggiati\n" + "8.elenco smartphone mai noleggiati\n"
				+ "9.smartphone venduti da un dipendente\n" + "10.fine noleggio\n" + "11.esci\n";
		return result;
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

	public void venditaSmartphone(Vendita vendita, Cliente cliente) throws AppException {
		for (Smartphone smartphone : vendita.getSmartphone()) {
			if (!catalogoSmartphone.contains(smartphone) || smartphone.getClass() == SmartphoneAvanzato.class)
				throw new AppException("lo smartphone con IMEI" + smartphone.getIMEI() + "non acquistabile");
		}
		this.vendite.add(vendita);
		this.catalogoSmartphone.removeAll(vendita.getSmartphone());
	}

	public void noleggiaSmartphone(Noleggio noleggio, Cliente cliente)
			throws AppException, ClienteException, DipendenteException {
		int numSmartphone = noleggio.getSmartphone().size();
		cliente.addNoleggio(numSmartphone);
		noleggio.getDipendente().addNoleggio(numSmartphone);
		for (Smartphone smartphone : noleggio.getSmartphone()) {
			if (!catalogoSmartphone.contains(smartphone))
				throw new AppException("impossibile noleggiare smartphone");
		}
		this.noleggi.add(noleggio);
		this.catalogoSmartphone.removeAll(noleggio.getSmartphone());
	}

	public void addDipendente(Dipendente dipendente) {
		this.dipendenti.add(dipendente);
	}

	public void removeDipendente(String idDipendente) throws AppException {
		dipendenti.remove(this.getDipendenteDaCodice(idDipendente));
	}

	public void addCliente(Cliente cliente) {
		this.clienti.add(cliente);
	}

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

	public boolean fineNoleggio(int codiceNoleggio) {
		boolean result = true;
		for (Noleggio noleggio : noleggi) {
			if (noleggio.getCodiceTransazione() == codiceNoleggio) {
				if (noleggio.getDataFine().before(new Date()))
					result = false;
				catalogoSmartphone.addAll(noleggio.getSmartphone());
			}
		}
		return result;
	}

	public Dipendente getDipendenteDaCodice(String codiceDipendente) throws AppException {
		for (Dipendente dipendente : dipendenti) {
			if (dipendente.getId() == codiceDipendente) {
				return dipendente;
			}
		}
		throw new AppException("il dipendente inserito non esiste");
	}

	public Cliente getClienteDaCodice(String codiceFiscale) throws AppException {
		for (Cliente cliente : clienti) {
			if (cliente.getCodiceFiscale() == codiceFiscale) {
				return cliente;
			}
		}
		throw new AppException("il dipendente inserito non esiste");
	}

}
