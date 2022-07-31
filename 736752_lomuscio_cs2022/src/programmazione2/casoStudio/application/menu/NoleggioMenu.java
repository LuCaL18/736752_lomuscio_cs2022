package programmazione2.casoStudio.application.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import programmazione2.casoStudio.application.AppException;
import programmazione2.casoStudio.application.Applicazione;
import programmazione2.casoStudio.application.Main;
import programmazione2.casoStudio.azioni.Noleggio;
import programmazione2.casoStudio.azioni.TransazioneException;
import programmazione2.casoStudio.dispositivi.Smartphone;
import programmazione2.casoStudio.ruoli.Cliente;
import programmazione2.casoStudio.ruoli.Dipendente;
import programmazione2.casoStudio.ruoli.PersonaException;
import programmazione2.casoStudio.util.GestoreFile;

/**
 * La classe NoleggioMenu estende la classe AbstractMenu. Gestisce il menu dei
 * noleggi e tutte le operazioni che li riguardano: noleggio smartphone, elenco
 * smartphone noleggiati, elenco smartphone mai noleggiati e fine noleggio.
 * 
 * @author Luca Lomuscio
 *
 */
public class NoleggioMenu extends AbstractMenu {

	/**
	 * Costruttore della classe NoleggioMenu che permette di istanziare un oggetto
	 * NoleggioMenu con il suo unico attributo bufferReader
	 * 
	 * @param bufferReader
	 */
	public NoleggioMenu(BufferedReader bufferReader) {
		super(bufferReader);
	}

	/**
	 * Override del metodo astratto della classe AbstractMenu. Permette la gestione
	 * del menu noleggio. Le operazioni effettuabili in questo menu sono il noleggio
	 * di smartphone, la stampa su file dell'elenco di smartphone noleggiati fino ad
	 * oggi ordinati per data, la stampa su file dell'elenco di smartphone mai
	 * noleggiati e la conclusione di un noleggio. Si esce dal menu noleggio
	 * selezionando l'opzione esci dal menu noleggio. Solleva l'eccezione
	 * IOException se c'è un errore allo stream di input.
	 * 
	 * @throws IOException
	 */
	@Override
	public void menu() throws IOException {
		Applicazione app = Applicazione.getInstance();

		int scelta = 0;

		System.out.println("-------------------------\n" + "MENU NOLEGGIO\n" + "-------------------------");

		while (scelta != 5) {
			try {
				System.out.println(listaMenu());
				System.out.print("scelta:");

				scelta = Integer.parseInt(stdin.readLine());

				switch (scelta) {
				case 1:
					noleggioSmartphone(app);
					break;
				case 2:
					GestoreFile.scriviListSuFile(app.elencoSmartphoneNoleggiati(),
							Main.currentDirectory + "File_Richiesti/" + "smartphoneNoleggiati" + '_'
									+ String.valueOf(new Date().getTime()) + ".txt");
					System.out.println("Elenco stampato");
					break;
				case 3:
					GestoreFile.scriviListSuFile(app.elencoSmartphoneMaiNoleggiati(),
							Main.currentDirectory + "File_Richiesti/" + "smartphone_mai_noleggiati" + '_'
									+ String.valueOf(new Date().getTime()) + ".txt");
					System.out.println("Elenco stampato");
					break;
				case 4:
					fineNoleggio(app);
					break;
				case 5:
					break;
				default:
					System.out.println("nessuna operazione effettuata");
				}
			} catch (NumberFormatException | NullPointerException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	/**
	 * Metodo che permette il noleggio di uno o più smartphone inserendo tutti i
	 * dati che caratterizzano un noleggio: data inizio, prezzo, dipendente,
	 * cliente, data fine noleggio e smartphone noleggiati. Vengono effettuati dei
	 * controlli sull'esistenza del dipendente, del cliente e degli smartphone
	 * inseriti, sulla validità della data di fine noleggio, sul valore inserito del
	 * prezzo, sulla disponibilità dello smartphone. Se i valori dei dati non
	 * corrispondono a quelli richiesti verrà chiesto di inserirli nuovamente o
	 * annullare l'operazione ritornando al menu. La data di inzio noleggio è
	 * inserita automaticamente prendendo la data corrente. Se il numero di
	 * smartphone inseriti non corrisponde a quello dichiarato prima, il noleggio
	 * non sarà effettuato. Se l'operazione è conclusa e va a buon fine il metodo
	 * scrive la nuova lista di smartphone su file catalogo_smartphone.ser e
	 * catalogo_smartphone.txt, la lista aggiornata clienti su file clienti.ser e
	 * clienti.txt, la lista aggiornata dipendenti su file dipendenti.ser e
	 * dipendenti.txt e la lista aggiornata noleggi su file noleggi.txt. Solleva
	 * l'eccezione IOException se c'è un errore allo stream di input.
	 * 
	 * @param app
	 * @throws IOException
	 */
	private void noleggioSmartphone(Applicazione app) throws IOException {
		boolean risp = true;
		Noleggio noleggio = null;
		Double prezzo = null;
		Date dataFineNoleggio = null;
		Dipendente dipendente = null;
		Cliente cliente = null;
		int smartphoneNoleggiati = 0;

		while (risp) {
			try {

				if (prezzo == null) {
					System.out.println("Inserire prezzo:");
					prezzo = Double.parseDouble(stdin.readLine());
				}

				if (dipendente == null) {
					System.out.println("Inserire codice dipendente:");
					String codiceDipendente = String.valueOf(stdin.readLine());
					dipendente = app.getDipendenteDaCodice(codiceDipendente);
				}

				if (cliente == null) {
					System.out.println("Inserire codice fiscale cliente:");
					String codiceCliente = String.valueOf(stdin.readLine()).toUpperCase();
					cliente = app.getClienteDaCodice(codiceCliente);
				}

				noleggio = new Noleggio(new Date(), prezzo, dipendente, cliente);

				if (dataFineNoleggio == null) {
					System.out.println("Inserire data fine noleggio(dd/MM/yyyy):");
					dataFineNoleggio = new SimpleDateFormat("dd/MM/yyyy").parse(stdin.readLine());
				}

				noleggio.setDataFine(dataFineNoleggio);

				break;
			} catch (NumberFormatException | AppException | ParseException | NullPointerException e) {
				System.out.println(e.getMessage());
				System.out.println("Riprovare?(true/false)");
				risp = Boolean.parseBoolean(stdin.readLine());
			} catch (TransazioneException e) {
				System.out.println(e.getMessage());
				System.out.println("Riprovare?(true/false)");
				dataFineNoleggio = null;
				risp = Boolean.parseBoolean(stdin.readLine());
			}
		}

		while (risp) {
			try {
				System.out.println("Inserire numero smartphone da noleggiare:");
				smartphoneNoleggiati = Integer.parseInt(stdin.readLine());
				break;
			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
				System.out.println("Riprovare?(true/false)");
				risp = Boolean.parseBoolean(stdin.readLine());
			}

		}

		int count = smartphoneNoleggiati;

		while (count != 0) {
			try {
				System.out.println("Inserire codice IMEI smartphone: ");
				String codiceIMEI = String.valueOf(stdin.readLine());
				noleggio.addSmartphone(app.getSmartphoneDaCodice(codiceIMEI));
				count--;
			} catch (AppException e) {
				System.out.println(e.getMessage());
				System.out.println("Riprovare?(true/false)");
				risp = Boolean.parseBoolean(stdin.readLine());

				if (!risp) {
					count = 0;
				}
			}
		}

		try {
			if (noleggio.getSmartphone().size() == smartphoneNoleggiati && smartphoneNoleggiati > 0) {
				app.noleggiaSmartphone(noleggio);

				Set<Cliente> clienti = app.getListaClienti();
				GestoreFile.serializzaOggetto(clienti, Main.currentDirectory + "clienti.ser");
				GestoreFile.scriviSetSuFile(clienti, Main.currentDirectory + "clienti.txt");

				List<Smartphone> smartphones = app.getListaSmartphone();
				GestoreFile.serializzaOggetto(smartphones, Main.currentDirectory + "catalogo_smartphone.ser");
				GestoreFile.scriviListSuFile(smartphones, Main.currentDirectory + "catalogo_smartphone.txt");
				GestoreFile.scriviListSuFile(app.getListaNoleggi(), Main.currentDirectory + "noleggi.txt");

				Set<Dipendente> dipendenti = app.getListaDipendenti();
				GestoreFile.serializzaOggetto(dipendenti, Main.currentDirectory + "dipendenti.ser");
				GestoreFile.scriviSetSuFile(dipendenti, Main.currentDirectory + "dipendenti.txt");

				System.out.println("Noleggio eseguito con successo");
			} else {
				System.out.println("Noleggio non effettuato.");
			}

		} catch (AppException | PersonaException e) {
			System.out.println(e.getMessage());
			System.out.println("Noleggio non effettuato");
		} catch (NullPointerException e) {
			System.out.println("Noleggio non effettuato");
		}
	}

	/**
	 * Metodo che permette di concludere un noleggio inserendo il codice transazione
	 * di quest'ultimo. Notifica se il noleggio è stato concluso entro la data
	 * stabilita oppure no. Se l'operazione va a buon fine scrive la lista
	 * aggiornata degli smartphone su file catalogo_smartphone.ser e
	 * catalogo_smartphone.txt e la lista aggiornata dei noleggi su file
	 * noleggi.txt. Solleva l'eccezione IOException se c'è un errore allo stream di
	 * input.
	 * 
	 * @param app
	 * @throws IOException
	 */
	private void fineNoleggio(Applicazione app) throws IOException {
		boolean risp = true;
		while (risp) {
			try {
				System.out.println("scrivere codice transazione:");
				int codiceNoleggio = Integer.parseInt(stdin.readLine());

				if (!app.fineNoleggio(codiceNoleggio)) {
					System.out.println("Noleggio finito dopo il tempo stabilito");
				} else {
					System.out.println("Noleggio finito entro il tempo stabilito");
				}

				List<Smartphone> smartphones = app.getListaSmartphone();
				GestoreFile.serializzaOggetto(smartphones, Main.currentDirectory + "catalogo_smartphone.ser");
				GestoreFile.scriviListSuFile(smartphones, Main.currentDirectory + "catalogo_smartphone.txt");
				GestoreFile.scriviListSuFile(app.getListaNoleggi(), Main.currentDirectory + "noleggi.txt");

				break;
			} catch (NumberFormatException | AppException e) {
				System.out.println(e.getMessage());
				System.out.println("Riprovare?(true/false)");
				risp = Boolean.parseBoolean(stdin.readLine());
			}
		}
	}

	/**
	 * Override del metodo astratto "listaMenu" della classe AbstractMenu.
	 * Restituisce una stringa che contiene tutti i valori del menu Noleggio.
	 * 
	 * @return menu noleggio
	 */
	@Override
	public String listaMenu() {
		return "\n1.Noleggia smartphone\n" + "2.Elenco smartphone noleggiati\n" + "3.Smartphone mai noleggiati\n"
				+ "4.Fine noleggio\n" + "5.Esci dal Menu noleggio\n";
	}

}
