package programmazione2.casoStudio.application.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import programmazione2.casoStudio.application.AppException;
import programmazione2.casoStudio.application.Applicazione;
import programmazione2.casoStudio.application.Main;
import programmazione2.casoStudio.azioni.Vendita;
import programmazione2.casoStudio.dispositivi.Smartphone;
import programmazione2.casoStudio.ruoli.Cliente;
import programmazione2.casoStudio.ruoli.Dipendente;
import programmazione2.casoStudio.util.GestoreFile;

/**
 * La classe VenditaMenu estende la classe AbstractMenu. Gestisce il menu
 * vendite e tutte le operazioni che le riguardano: vendita smartphone e stampa
 * su file dell'elenco di smartphone venduti da un dipendente ordinati per data.
 * 
 * @author Luca Lomuscio
 *
 */
public class VenditaMenu extends AbstractMenu {

	/**
	 * Costruttore della classe VenditaMenu che permette di istanziare un oggetto
	 * VenditaMenu con il suo unico attributo bufferReader
	 * 
	 * @param bufferReader
	 */
	public VenditaMenu(BufferedReader bufferReader) {
		super(bufferReader);
	}

	/**
	 * Override del metodo astratto della classe AbstractMenu. Permette la gestione
	 * del menu vendita. Le operazioni effettuabili in questo menu sono la vendita
	 * di smartphone e la stampa su file dell'elenco di smartphone venduti da un
	 * dipendente. Si esce dal menu vendita selezionando l'opzione esci dal menu
	 * vendita. Solleva l'eccezione IOException se c'è un errore allo stream di
	 * input.
	 * 
	 * @throws IOException
	 */
	@Override
	public void menu() throws IOException {
		Applicazione app = Applicazione.getInstance();

		int scelta = 0;

		System.out.println("-------------------------\n" + "MENU VENDITE\n" + "-------------------------");

		while (scelta != 3) {
			try {
				System.out.println(listaMenu());
				System.out.print("scelta:");

				scelta = Integer.parseInt(stdin.readLine());

				switch (scelta) {
				case 1:
					venditaSmartphone(app);
					break;
				case 2:
					elencoSmartphoneVendutiDaDipendente(app);
					break;
				case 3:
					break;
				default:
					System.out.println("nessuna operazione effettuata");
				}
			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	/**
	 * Metodo che permette la vendita di uno o più smartphone inserendo tutti i dati
	 * che caratterizzano una vendita: data, prezzo, dipendente, cliente e
	 * smartphone noleggiati. Vengono effettuati dei controlli sull'esistenza del
	 * dipendente, del cliente e degli smartphone inseriti, sul valore inserito del
	 * prezzo e sulla disponibilità dello smartphone. Se i valori dei dati non
	 * corrispondono a quelli richiesti verrà chiesto di inserirli nuovamente o
	 * annullare l'operazione ritornando al menu. La data è inserita automaticamente
	 * prendendo la data corrente. Se il numero di smartphone inseriti non
	 * corrisponde a quello dichiarato prima, la vendita non sarà effettuata. Se
	 * l'operazione è conclusa e va a buon fine il metodo scrive la nuova lista di
	 * smartphone su file catalogo_smartphone.ser e catalogo_smartphone.txt e la
	 * lista aggiornata vendite su file vendite.txt. Solleva l'eccezione IOException
	 * se c'è un errore allo stream di input.
	 * 
	 * @param app
	 * @throws IOException
	 */
	private void venditaSmartphone(Applicazione app) throws IOException {
		Vendita vendita = null;
		boolean risp = true;
		Double prezzo = null;
		Dipendente dipendente = null;
		Cliente cliente = null;
		int smartphoneVenduti = 0;
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

				vendita = new Vendita(new Date(), prezzo, dipendente, cliente);
				break;

			} catch (AppException | NumberFormatException e) {
				System.out.println(e.getMessage() + "." + " Riprovare?(true/false)");
				risp = Boolean.parseBoolean(stdin.readLine());
			}
		}

		while (risp) {
			try {
				System.out.println("Inserire numero smartphone da vendere: ");
				smartphoneVenduti = Integer.parseInt(stdin.readLine());
				break;
			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
				System.out.println("Riprovare?(true/false)");
				risp = Boolean.parseBoolean(stdin.readLine());
			}

		}

		int count = smartphoneVenduti;

		while (count != 0) {
			try {
				System.out.println("Inserire codice IMEI smartphone: ");
				String codiceIMEI = String.valueOf(stdin.readLine());
				vendita.addSmartphone(app.getSmartphoneDaCodice(codiceIMEI));
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
			if (vendita.getSmartphone().size() == smartphoneVenduti && smartphoneVenduti > 0) {
				app.venditaSmartphone(vendita);

				List<Smartphone> smartphones = app.getListaSmartphone();
				GestoreFile.serializzaOggetto(smartphones, Main.currentDirectory + "catalogo_smartphone.ser");
				GestoreFile.scriviListSuFile(smartphones, Main.currentDirectory + "catalogo_smartphone.txt");
				GestoreFile.scriviListSuFile(app.getListaVendite(), Main.currentDirectory + "vendite.txt");

				System.out.println("Vendita eseguita con successo");
			} else {
				System.out.println("Vendita non effettuata.");
			}
		} catch (AppException e) {
			System.out.println(e.getMessage());
			System.out.println("Vendita non effettuata");
		} catch (NullPointerException e) {
			System.out.println("Vendita non effettuata");
		}
	}

	/**
	 * Metodo che permette la stampa su file dell'elenco degli smartphone venduti da
	 * un dipendente ordinato per data. Viene effettuato un controllo sull'esistenza
	 * del dipendente inserito. Se il dipendente non esiste si domanda se si vuole
	 * riprovare oppure tornare al menu vendita.
	 * 
	 * @param app
	 * @throws IOException
	 */
	private void elencoSmartphoneVendutiDaDipendente(Applicazione app) throws IOException {
		boolean risp = true;
		while (risp) {
			try {
				System.out.println("Inserire codice dipendente:");
				String codiceDipendente = String.valueOf(stdin.readLine());
				Dipendente dipendente = app.getDipendenteDaCodice(codiceDipendente);
				GestoreFile.scriviListSuFile(app.smartphoneVendutiDaDipendente(dipendente), Main.currentDirectory
						+ "File_Richiesti/" + "smartphone_venduti_da_" + codiceDipendente + ".txt");
				System.out.println("Elenco stampato");
				break;
			} catch (AppException e) {
				System.out.println(e.getMessage());
				System.out.println("Riprovare?(true/false)");
				risp = Boolean.parseBoolean(stdin.readLine());
			}
		}
	}

	/**
	 * Override del metodo astratto "listaMenu" della classe AbstractMenu.
	 * Restituisce una stringa che contiene tutti i valori del menu vendita.
	 * 
	 * @return menu vendita
	 */
	@Override
	public String listaMenu() {
		return "\n1.Vendita smartphone\n" + "2.Elenco smartphone venduti da dipendente\n" + "3.Esci dal menu vendite\n";
	}

}
