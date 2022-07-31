/**
 * 
 */
package programmazione2.casoStudio.application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
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
import programmazione2.casoStudio.ruoli.Dipendente;
import programmazione2.casoStudio.ruoli.PersonaException;
import programmazione2.casoStudio.util.ComparatorTransazioneData;
import programmazione2.casoStudio.util.GestoreFile;
import programmazione2.casoStudio.util.SchedulerResetSmartphone;

/**
 * La classe Applicazione rappresenta la logica dell'applicazione che permette
 * la gestione di vendite e noleggi di samrtphone. I suoi attributi sono:
 * dipendenti, clienti, vendite, noleggi, catalogo smartphone. Implementa uno
 * scheduler per la gestione dei numeri di smartphone al giorno che possono
 * noleggiare i clienti e i dipendenti. La classe Applicazione è un singleton.
 * 
 * @author Luca Lomuscio
 *
 */
public class Applicazione {

	/**
	 * Valore che indica i dipendenti dell'azienda
	 */
	private Set<Dipendente> dipendenti;
	/**
	 * valore che indica i clienti dell'azienda
	 */
	private Set<Cliente> clienti;
	/**
	 * valore che indica le vendite dell'azienda
	 */
	private List<Vendita> vendite;
	/**
	 * valore che indica i noleggi dell'azienda
	 */
	private List<Noleggio> noleggi;
	/**
	 * valore che indica gli smartphone che l'azienda vuole noleggiare o vendere
	 */
	private List<Smartphone> catalogoSmartphone;
	/**
	 * valore che indica il comando che deve essere eseguito dopo un ritardo di
	 * tempo
	 */
	private ScheduledExecutorService service;
	/**
	 * valore che indica il risultato di un azione pianificata del service
	 */
	private ScheduledFuture<?> schedFuture;
	/**
	 * costante che indica i millisecondi in un giorno
	 */
	private static final long DAY_IN_MILLISECONDS = 86400000;

	/**
	 * unica istanza della classe Applicazione durante tutto il processo
	 */
	private static final Applicazione app = new Applicazione();

	/**
	 * Costruttore privato della classe Applicazione che permette di istanziare
	 * l'istanza di un oggetto di tipo Applicazione. Vengono recuperati i dati degli
	 * attributi dell'applicazione, impostato lo scheduler calcolando il ritardo e
	 * impostato il valore del contatore del codice delle transazioni recuperato da
	 * file.
	 */
	private Applicazione() {
		fetchData();
		service = Executors.newScheduledThreadPool(1);
		schedFuture = service.scheduleAtFixedRate(new SchedulerResetSmartphone(this), this.calculateDelay(),
				DAY_IN_MILLISECONDS, TimeUnit.MILLISECONDS);
		Noleggio.setCodicePrec(Long.parseLong(GestoreFile.leggiElementoXML("data_app.xml", "last_transaction_code")));
	}

	/**
	 * Metodo che restituisce l'istanza della classe Applicazione
	 * 
	 * @return app
	 */
	public static Applicazione getInstance() {
		return app;
	}

	/**
	 * Metodo che recupera i dati dei dipendenti dal file serializzato dipendenti, i
	 * dati dei clienti dal file serializzato clienti, i dati del catalogo
	 * smartphone dal file serializzato catalogo_smartphone, i dati dei noleggi dal
	 * file serializzato noleggi e i dati delle vendite dal file serializzato
	 * vendite. Se non viene trovato nessun dato o file viene impostato il valore di
	 * lista vuota.
	 */
	@SuppressWarnings("unchecked")
	private void fetchData() {

		Set<Cliente> clienti = null;
		try {
			clienti = (Set<Cliente>) GestoreFile.deserializzaOggetto(Main.currentDirectory + "clienti.ser");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		this.setClienti(clienti != null ? clienti : new HashSet<Cliente>());

		Set<Dipendente> dipendenti = null;
		try {
			dipendenti = (Set<Dipendente>) GestoreFile.deserializzaOggetto(Main.currentDirectory + "dipendenti.ser");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		this.setDipendenti(dipendenti != null ? dipendenti : new HashSet<Dipendente>());

		List<Smartphone> smartphone = null;
		try {
			smartphone = (List<Smartphone>) GestoreFile
					.deserializzaOggetto(Main.currentDirectory + "catalogo_smartphone.ser");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		this.setCatalogoSmartphone(smartphone != null ? smartphone : new ArrayList<Smartphone>());

		List<Noleggio> noleggi = null;
		try {
			noleggi = (List<Noleggio>) GestoreFile.deserializzaOggetto(Main.currentDirectory + "noleggi.ser");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		this.setNoleggi(noleggi != null ? noleggi : new LinkedList<Noleggio>());

		List<Vendita> vendite = null;
		try {
			vendite = (List<Vendita>) GestoreFile.deserializzaOggetto(Main.currentDirectory + "vendite.ser");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		this.setVendite(vendite != null ? vendite : new LinkedList<Vendita>());
	}

	/**
	 * Metodo che calcola il ritardo della prossima azione dello scheduler. Recupera
	 * il timestamp dell'ultima chiusura da file , confronta questo valore con la
	 * data corrente. Recupera il ritardo della prossima azione dello scheduler al
	 * momento della precedente chiusura e fa la differenza tra quest'ultimo valore
	 * e la differenza di date calcolata precedentemente.
	 * 
	 * @return ritardo in millisecondi della prossima azione dello scheduler
	 */
	private long calculateDelay() {
		try {

			long delay = Long.parseLong(GestoreFile.leggiElementoXML("data_app.xml", "delay"));
			Date timestamp = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH)
					.parse(GestoreFile.leggiElementoXML("data_app.xml", "close_timestamp"));
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
	 * Metodo che restituisce i dipendenti dell'azienda
	 * 
	 * @return dipendenti
	 */
	public Set<Dipendente> getDipendenti() {
		return dipendenti;
	}

	/**
	 * Metodo che restituisce i clienti dell'azienda
	 * 
	 * @return clienti
	 */
	public Set<Cliente> getClienti() {
		return clienti;
	}

	/**
	 * Metodo che restituisce la lista delle vendite dell'azienda
	 * 
	 * @return vendite
	 */
	public List<Vendita> getVendite() {
		return vendite;
	}

	/**
	 * Metodo che restituisce la lista dei noleggi dell'azienda
	 * 
	 * @return noleggi
	 */
	public List<Noleggio> getNoleggi() {
		return noleggi;
	}

	/**
	 * Metodo che restituisce il catalogo degli smartphone dell'azienda
	 * 
	 * @return catalogo Smartphone
	 */
	public List<Smartphone> getCatalogoSmartphone() {
		return catalogoSmartphone;
	}

	/**
	 * Metodo che restituisce una lista clone dei dipendenti.
	 * 
	 * @return elenco degli oggetti dipendente clonati
	 */
	public Set<Dipendente> getListaDipendenti() {
		Set<Dipendente> dipendenti = new HashSet<Dipendente>();

		for (Dipendente dip : this.dipendenti) {
			dipendenti.add((Dipendente) dip.clone());
		}

		return dipendenti;
	}

	/**
	 * Metodo che permette di imposatre i dipendenti dell'azienda
	 * 
	 * @param dipendenti lista dipendenti da impostare
	 */
	public void setDipendenti(Set<Dipendente> dipendenti) {
		this.dipendenti = dipendenti;
	}

	/**
	 * Metodo che restituisce una lista clone dei clienti.
	 * 
	 * @return elenco degli oggetti cliente clonati
	 */
	public Set<Cliente> getListaClienti() {
		Set<Cliente> clienti = new HashSet<Cliente>();

		for (Cliente cliente : this.clienti) {
			clienti.add((Cliente) cliente.clone());
		}

		return clienti;
	}

	/**
	 * Metodo che permette di imposatre i clienti dell'azienda
	 * 
	 * @param clienti lista clienti da impostare
	 */
	public void setClienti(Set<Cliente> clienti) {
		this.clienti = clienti;
	}

	/**
	 * Metodo che restituisce una lista clone delle vendite.
	 * 
	 * @return elenco degli oggetti vendita clonati
	 */
	public List<Vendita> getListaVendite() {

		List<Vendita> vendite = new LinkedList<Vendita>();

		for (Vendita vendita : this.vendite) {
			vendite.add((Vendita) vendita.clone());
		}
		return vendite;
	}

	/**
	 * Metodo che permette di imposatre le vendite dell'azienda
	 * 
	 * @param vendite lista vendite da impostare
	 */
	public void setVendite(List<Vendita> vendite) {
		this.vendite = vendite;
	}

	/**
	 * Metodo che restituisce una lista clone dei noleggi.
	 * 
	 * @return elenco degli oggetti noleggio clonati
	 */
	public List<Noleggio> getListaNoleggi() {
		List<Noleggio> noleggi = new LinkedList<Noleggio>();

		for (Noleggio noleggio : this.noleggi) {
			noleggi.add((Noleggio) noleggio.clone());
		}
		return noleggi;
	}

	/**
	 * Metodo che permette di imposatre i noleggi dell'azienda
	 * 
	 * @param noleggi lista vendite da impostare
	 */
	public void setNoleggi(List<Noleggio> noleggi) {
		this.noleggi = noleggi;
	}

	/**
	 * Metodo che restituisce una lista clone degli smartphone.
	 * 
	 * @return elenco degli oggetti smartphone clonati
	 */
	public List<Smartphone> getListaSmartphone() {
		List<Smartphone> smartphones = new ArrayList<Smartphone>();

		for (Smartphone smartphone : this.catalogoSmartphone) {
			smartphones.add(smartphone.clone());
		}
		return smartphones;
	}

	/**
	 * Metodo che permette di imposatre gli smartphone dell'azienda
	 * 
	 * @param catalogo smartphone lista smartphone da impostare
	 */
	public void setCatalogoSmartphone(List<Smartphone> catalogoSmartphone) {
		this.catalogoSmartphone = catalogoSmartphone;
	}

	/**
	 * Metodo che permette di aggiungere uno smartphone al catalogo. Solleva
	 * l'eccezione AppException qualora lo smartphone sia già presente nel catalogo
	 * 
	 * @param smartphone nuovo elemento da aggiungere al catalogo
	 * @throws AppException
	 */
	public void addSmartphone(Smartphone smartphone) throws AppException {

		for (Smartphone phone : this.catalogoSmartphone) {
			if (phone.getIMEI().equals(smartphone.getIMEI())) {
				throw new AppException("Smartphone già presente nel catalogo");
			}
		}
		catalogoSmartphone.add(smartphone);
	}

	/**
	 * Metodo che permette di rimuovere uno Smartphone dal catalogo. Solleva
	 * l'eccezione AppException se lo smartphone non è presente nel catalogo.
	 * 
	 * @param smartphone smartphone da rimuovere
	 * @throws AppException
	 */
	public void removeSmartphone(Smartphone smartphone) throws AppException {
		if (catalogoSmartphone.contains(smartphone)) {
			throw new AppException("Smartphone non presente nel catalogo");
		}
		catalogoSmartphone.remove(smartphone);
	}

	/**
	 * Metodo che permette la vendita di uno o più smartphone. Aggiunge la vendita
	 * alla lista delle vendite e rimuove gli smartphone venduti dal catalogo .
	 * Solleva l'eccezione AppException se lo smartphone venduto è di tipo Avanzato
	 * o non è presente nel catalogo o è lo smartphone è attualmente in noleggio e
	 * quindi non disponibile.
	 * 
	 * @param vendita oggetto che rappresenta la vendita con i suoi attributi
	 *                caratterizzanti
	 * @throws AppException
	 */
	public void venditaSmartphone(Vendita vendita) throws AppException {
		for (Smartphone smartphone : vendita.getSmartphone()) {
			if (!catalogoSmartphone.contains(smartphone) || smartphone.getClass() == SmartphoneAvanzato.class)
				throw new AppException("Smartphone con IMEI " + smartphone.getIMEI() + " non acquistabile");
			else if (smartphone.isInNoleggio())
				throw new AppException("Smartphone con IMEI " + smartphone.getIMEI() + " attualmente in noleggio");
		}
		this.vendite.add(vendita);
		this.catalogoSmartphone.removeAll(vendita.getSmartphone());
	}

	/**
	 * Metodo che permette il noleggio di uno o più smartphone. Aggiunge il noleggio
	 * alla lista dei noleggi, imposta la variabile inNoleggio a true negli
	 * smartphone noleggiati e aggiunge il numero degli smartphone noleggiati al
	 * cliente e al dipendente. Solleva l'eccezione AppException se gli smartphone
	 * non sono presenti nel catalogo o sono già in noleggio. Solleva l'eccezione
	 * PersonaException se il cliente o il dipendente superano il limite di
	 * smartphone noleggiati al giorno.
	 * 
	 * @param noleggio oggetto che rappresenta il noleggio con i suoi attributi
	 *                 caratterizzanti
	 * @throws AppException
	 * @throws PersonaException
	 */
	public void noleggiaSmartphone(Noleggio noleggio) throws AppException, PersonaException {
		int numSmartphone = noleggio.getSmartphone().size();
		noleggio.getCliente().addNoleggio(numSmartphone);
		noleggio.getDipendente().addNoleggio(numSmartphone);

		for (Smartphone smartphone : noleggio.getSmartphone()) {

			if (!catalogoSmartphone.contains(smartphone) || smartphone.isInNoleggio())
				throw new AppException("Smartphone non disponibile");
		}

		for (Smartphone smartphone : noleggio.getSmartphone()) {
			smartphone.setInNoleggio(true);
		}

		this.noleggi.add(noleggio);
	}

	/**
	 * Metodo che permette di aggiungere un nuovo dipendente alla lista dipendenti
	 * dell'azienda. Essendo una Set il dipendente non sarà aggiunto se già
	 * presente.
	 * 
	 * @param dipendente da aggiungere
	 */
	public void addDipendente(Dipendente dipendente) {
		this.dipendenti.add(dipendente);
	}

	/**
	 * Metodo che permette la rimozione del dipendente dalla lista dipendenti.
	 * Solleva l'eccezione AppException se il dipendente non esiste.
	 * 
	 * @param idDipendente codice identificativo del dipendente da rimuovere
	 * @throws AppException
	 */
	public void removeDipendente(String idDipendente) throws AppException {
		dipendenti.remove(this.getDipendenteDaCodice(idDipendente));
	}

	/**
	 * Metodo che permette di aggiungere un nuovo cliente alla lista clienti
	 * dell'azienda. Essendo una Set il cliente non sarà aggiunto se già presente.
	 * 
	 * @param cliente da aggiungere
	 */
	public void addCliente(Cliente cliente) {
		this.clienti.add(cliente);
	}

	/**
	 * Metodo che restituisce la lista degli smartphone noleggiati fino ad oggi
	 * ordinati per data inizio noleggio. La lista contiene oggetti clone degli
	 * smartphone.
	 * 
	 * @return lista smartphone noleggiati fino ad oggi
	 */
	public List<Smartphone> elencoSmartphoneNoleggiati() {
		List<Smartphone> elenco = new LinkedList<Smartphone>();
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

	/**
	 * Metodo che restituisce la lista degli smartphone mai noleggiati fino a questo
	 * momento. La lista contiene oggetti clone degli smartphone.
	 * 
	 * @return lista degli smartphone mai noleggiati
	 */
	public List<Smartphone> elencoSmartphoneMaiNoleggiati() {
		List<Smartphone> elenco = new ArrayList<Smartphone>();
		for (Smartphone smartphone : catalogoSmartphone) {
			elenco.add(smartphone.clone());
		}

		elenco.removeAll(this.elencoSmartphoneNoleggiati());

		return elenco;
	}

	/**
	 * Metodo che restituisce la lista degli smartphone venduti da un dipendente
	 * dell'azienda. La lista è ordinata per data. La lista contiene oggetti clone
	 * degli smartphone.
	 * 
	 * @param dipendente che ha effettuato le vendite
	 * @return
	 */
	public List<Smartphone> smartphoneVendutiDaDipendente(Dipendente dipendente) {
		List<Smartphone> result = new LinkedList<Smartphone>();
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

	/**
	 * Metodo che restituisce lo smartphone che ha come codice IMEI quello passato
	 * per parametro. Solleva l'eccezione AppException se lo smartphone non esiste
	 * nel catalogo.
	 * 
	 * @param codiceIMEI smartphone da cercare
	 * @return Smartphone con codice IMEI uguale al parametro
	 * @throws AppException
	 */
	public Smartphone getSmartphoneDaCodice(String codiceIMEI) throws AppException {
		for (Smartphone smartphone : catalogoSmartphone) {
			if (smartphone.getIMEI().equals(codiceIMEI))
				return smartphone;
		}

		throw new AppException("lo smartphone inserito non esiste");
	}

	/**
	 * Metodo che permette di concludere il noleggio. Recupera il noleggio dalla
	 * lista dei noleggi confrontando il codice transazione passato per parametro.
	 * Imposta a true l'attributo concluso del noleggio e imposta a false
	 * l'attributo inNoleggio degli smartphone noleggiati. Informa se sono stati
	 * rispettati i tempi del noleggio. Solleva l'eccezione AppException se il
	 * noleggio è già concluso.
	 * 
	 * @param codiceNoleggio codice transazione del noleggio
	 * @return true se il noleggio è finito nei tempi previsti, altrimenti false.
	 * @throws AppException
	 */
	public boolean fineNoleggio(int codiceNoleggio) throws AppException {
		boolean result = true;
		Noleggio noleggio = this.getNoleggioDaCodice(codiceNoleggio);

		if (noleggio.isConcluso()) {
			throw new AppException("Noleggio già concluso");
		}

		noleggio.setConcluso(true);
		if (noleggio.getDataFine().before(new Date()))
			result = false;

		for (Smartphone smartphoneCatalogo : this.catalogoSmartphone) {
			for (Smartphone smartphone : noleggio.getSmartphone()) {
				if (smartphoneCatalogo.getIMEI().equals(smartphone.getIMEI())) {
					smartphoneCatalogo.setInNoleggio(false);
				}
			}
		}
		return result;
	}

	/**
	 * Metodo che restituisce il Noleggio che ha come codice transazione quello
	 * passato per parametro. Solleva l'eccezione AppException se il noleggio non
	 * esiste nella lista noleggi.
	 * 
	 * @param codiceNoleggio codice transazione del noleggio da cercare
	 * @return Noleggio con codice transazione uguale al parametro
	 * @throws AppException
	 */
	public Noleggio getNoleggioDaCodice(int codiceNoleggio) throws AppException {
		for (Noleggio noleggio : noleggi) {
			if (noleggio.getCodiceTransazione() == codiceNoleggio) {
				return noleggio;
			}
		}
		throw new AppException("Codice transazione non valido");
	}

	/**
	 * Metodo che restituisce il Dipendente che ha come codice dipendente quello
	 * passato per parametro. Solleva l'eccezione AppException se il dipendente non
	 * esiste nella lista dipendenti.
	 * 
	 * @param codiceDipendente del dipendente da cercare
	 * @return Dipendente con codice dipendente uguale al parametro
	 * @throws AppException
	 */
	public Dipendente getDipendenteDaCodice(String codiceDipendente) throws AppException {
		for (Dipendente dipendente : dipendenti) {
			if (dipendente.getId().contentEquals(codiceDipendente)) {
				return dipendente;
			}
		}
		throw new AppException("il dipendente inserito non esiste");
	}

	/**
	 * Metodo che restituisce il Cliente che ha come codice fiscale quello passato
	 * per parametro. Solleva l'eccezione AppException se il cliente non esiste
	 * nella lista clienti.
	 * 
	 * @param codiceFiscale del cliente da cercare
	 * @return Cliente con codice fiscale uguale al parametro
	 * @throws AppException
	 */
	public Cliente getClienteDaCodice(String codiceFiscale) throws AppException {
		for (Cliente cliente : clienti) {
			if (cliente.getCodiceFiscale().contentEquals(codiceFiscale)) {
				return cliente;
			}
		}
		throw new AppException("il cliente inserito non esiste");
	}

	/**
	 * Metodo eseguito alla chiusura dell'applicazione. Ferma il thread dello
	 * scheduler, scrive su file data_app.xml il timestamp di chiusura, il ritardo
	 * della prossima esecuzione dell'azione dello scheduler e l'ultimo codice
	 * transazione. Effettua la serializzazione della lista vendite e della lista
	 * noleggi su file.
	 */
	public void close() {
		long delay = this.schedFuture.getDelay(TimeUnit.MILLISECONDS);
		this.service.shutdown();
		Map<String, Object> args = new LinkedHashMap<String, Object>();
		args.put("close_timestamp", new Date());
		args.put("delay", delay);
		args.put("last_transaction_code", Noleggio.getCodicePrec());

		GestoreFile.scriviSuXML("data_app.xml", args);

		GestoreFile.serializzaOggetto(this.noleggi, Main.currentDirectory + "noleggi.ser");
		GestoreFile.serializzaOggetto(this.vendite, Main.currentDirectory + "vendite.ser");

	}

}
