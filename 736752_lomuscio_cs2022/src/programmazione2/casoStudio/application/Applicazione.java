/**
 * 
 */
package programmazione2.casoStudio.application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import programmazione2.casoStudio.azioni.Noleggio;
import programmazione2.casoStudio.azioni.Vendita;
import programmazione2.casoStudio.dispositivi.Smartphone;
import programmazione2.casoStudio.dispositivi.SmartphoneAvanzato;
import programmazione2.casoStudio.ruoli.Cliente;
import programmazione2.casoStudio.ruoli.ClienteException;
import programmazione2.casoStudio.ruoli.Dipendente;
import programmazione2.casoStudio.ruoli.DipendenteException;
import programmazione2.casoStudio.util.ComparatorTransazioneData;
import programmazione2.casoStudio.util.SchedulerResetSmartphone;
import programmazione2.casoStudio.util.Serializator;

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
	private ScheduledExecutorService service;
	private ScheduledFuture<?> schedFuture;
	private static final long DAY_IN_MILLISECONDS = 86400000;

	public Applicazione() {
		this.vendite = new LinkedList<Vendita>();
		this.noleggi = new LinkedList<Noleggio>();
		this.catalogoSmartphone = new HashSet<Smartphone>();
		this.clienti = new HashSet<Cliente>();
		this.dipendenti = new HashSet<Dipendente>();
		service = Executors.newScheduledThreadPool(1);
		schedFuture = service.scheduleAtFixedRate(new SchedulerResetSmartphone(this), this.calculateDelay(), DAY_IN_MILLISECONDS,
				TimeUnit.MILLISECONDS);
	}

	/**
	 * @param dipendenti
	 * @param clienti
	 * @param vendite
	 * @param noleggi
	 * @param catalogoSmartphone
	 */
	public Applicazione(Set<Dipendente> dipendenti, Set<Cliente> clienti, List<Vendita> vendite, List<Noleggio> noleggi,
			Set<Smartphone> catalogoSmartphone) {
		this.dipendenti = dipendenti;
		this.clienti = clienti;
		this.vendite = vendite;
		this.noleggi = noleggi;
		this.catalogoSmartphone = catalogoSmartphone;
	}

	private long calculateDelay() {
		try {

			long delay = Long.parseLong(Serializator.readElementToXML("data_app", "delay"));
			Date timestamp = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH)
					.parse(Serializator.readElementToXML("data_app", "close_timestamp"));
			long difference = new Date().getTime() - timestamp.getTime();

			long result = delay - difference;

			if (result > 0) {
				return result;
			}

		} catch (ParseException | NumberFormatException e) {
			System.out.println(e.getMessage());
		}
		return 0;
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
		return "\n1.aggiungi Smartphone\n" + "2.nuovo cliente\n" + "3.nuovo dipendente\n" + "4.rimuovi dipendente\n"
				+ "5.vendita telefono\n" + "6.noleggio telefono\n" + "7.elenco smartphone noleggiati\n"
				+ "8.elenco smartphone mai noleggiati\n" + "9.smartphone venduti da un dipendente\n"
				+ "10.fine noleggio\n" + "11.esci\n";
	}

	public void addSmartphone(Smartphone smartphone) {
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
				throw new AppException("lo smartphone con IMEI " + smartphone.getIMEI() + " non acquistabile");
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

	public Smartphone getSmartphoneDaCodice(long codiceIMEI) {
		for (Smartphone smartphone : catalogoSmartphone) {
			if (smartphone.getIMEI() == codiceIMEI)
				return smartphone;
		}

		return null;
	}

	public boolean fineNoleggio(Noleggio noleggio) {
		boolean result = true;
		if (noleggio.getDataFine().before(new Date()))
			result = false;
		catalogoSmartphone.addAll(noleggio.getSmartphone());

		return result;
	}

	public Noleggio getNoleggioDaCodice(int codiceNoleggio) throws AppException {
		for (Noleggio noleggio : noleggi) {
			if (noleggio.getCodiceTransazione() == codiceNoleggio) {
				return noleggio;
			}
		}
		throw new AppException("Codice noleggio non valido");
	}

	public Dipendente getDipendenteDaCodice(String codiceDipendente) throws AppException {
		for (Dipendente dipendente : dipendenti) {
			if (dipendente.getId().contentEquals(codiceDipendente)) {
				return dipendente;
			}
		}
		throw new AppException("il dipendente inserito non esiste");
	}

	public Cliente getClienteDaCodice(String codiceFiscale) throws AppException {
		for (Cliente cliente : clienti) {
			if (cliente.getCodiceFiscale().contentEquals(codiceFiscale)) {
				return cliente;
			}
		}
		throw new AppException("il cliente inserito non esiste");
	}

	public void close() {
		long delay = this.schedFuture.getDelay(TimeUnit.MILLISECONDS);
		this.service.shutdown();
		Map<String, Object> args = new LinkedHashMap<String, Object>();
		args.put("close_timestamp", new Date());
		args.put("delay", delay);
		args.put("last_transaction_code", Noleggio.getCodicePrec());
		Serializator.writeToXML("data_app", args);
	}

}
