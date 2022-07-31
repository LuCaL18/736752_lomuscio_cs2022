package programmazione2.casoStudio.application.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import programmazione2.casoStudio.application.AppException;
import programmazione2.casoStudio.application.Applicazione;
import programmazione2.casoStudio.application.Main;
import programmazione2.casoStudio.ruoli.Cliente;
import programmazione2.casoStudio.ruoli.PersonaException;
import programmazione2.casoStudio.util.GestoreFile;

/**
 * La classe ClienteMenu estende la classe AbstractMenu. Gestisce il menu del
 * cliente e delle operazioni che si possono effettuare su di esso:
 * registrazione, modifica e visualizzazione.
 * 
 * @author Luca Lomuscio
 *
 */
public class ClienteMenu extends AbstractMenu {

	/**
	 * Costruttore della classe ClienteMenu che permette di istanziare un oggetto
	 * ClienteMenu con il suo unico attributo bufferReader
	 * 
	 * @param bufferReader
	 */
	public ClienteMenu(BufferedReader bufferReader) {
		super(bufferReader);
	}

	/**
	 * Override del metodo astratto della classe AbstractMenu. Permette la gestione
	 * del menu cliente. Le operazioni effettuabili in questo menu sono la
	 * registrazione di un nuovo cliente, la modifca dei dati di un cliente, eccetto
	 * il codice fiscale, e la visualizzazione dei dati di un cliente. Si esce dal
	 * menu cliente selezionando l'opzione esci dal menu cliente. Solleva
	 * l'eccezione IOException se c'è un errore allo stream di input.
	 * 
	 * @throws IOException
	 */
	@Override
	public void menu() throws IOException {

		Applicazione app = Applicazione.getInstance();

		int scelta = 0;

		System.out.println("-------------------------\n" + "MENU CLIENTE\n" + "-------------------------");

		while (scelta != 4) {
			try {
				System.out.println(listaMenu());
				System.out.print("scelta:");

				scelta = Integer.parseInt(stdin.readLine());

				switch (scelta) {
				case 1:
					creaCliente(app);
					break;
				case 2:
					modificaCliente(app);
					break;
				case 3:
					visualizzaCliente(app);
					break;
				case 4:
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
	 * Metodo che permette la creazione del cliente inserendo tutti i suoi dati:
	 * codice fiscale, nome, cognome, data di nascita, luogo di nascita. Vengono
	 * effettuati dei controlli sulla validità del pattern del codice fiscale e del
	 * formato della data di nascita inserita. Se i valori non corrispondono a
	 * quelli richiesti verrà chiesto di continuare con l'operazione oppure tornare
	 * al menu. Scrive la nuova lista clienti dell'app sui file clienti.ser e
	 * clienti.txt. Solleva l'eccezione IOException se c'è un errore allo stream di
	 * input.
	 * 
	 * @param app
	 * @throws IOException
	 */
	private void creaCliente(Applicazione app) throws IOException {
		boolean risp = true;
		Date dataDiNascita = new Date();
		Cliente newCliente = null;

		while (risp) {
			try {
				System.out.print("Inserire codice fiscale: ");
				String codiceFiscale = String.valueOf(stdin.readLine());
				newCliente = new Cliente(codiceFiscale);

				System.out.print("Inserire nome: ");
				String nome = String.valueOf(stdin.readLine());
				newCliente.setNome(nome);

				System.out.print("Inserire cognome: ");
				String cognome = String.valueOf(stdin.readLine());
				newCliente.setCognome(cognome);

				System.out.print("Inserire luogo di nascita: ");
				String luogoDiNascita = String.valueOf(stdin.readLine());
				newCliente.setLuogoDiNascita(luogoDiNascita);
				break;
			} catch (PersonaException e) {
				System.out.println(e.getMessage());
				System.out.println("Riprovare(true/false)?");
				risp = Boolean.parseBoolean(stdin.readLine());
			}
		}

		while (risp) {
			try {
				System.out.print("Inserire data di nascita(dd/MM/yyyy): ");
				dataDiNascita = new SimpleDateFormat("dd/MM/yyyy").parse(stdin.readLine());
				newCliente.setDataDiNascita(dataDiNascita);

				int numClienti = app.getClienti().size();
				app.addCliente(newCliente);
				if (numClienti < app.getClienti().size()) {

					Set<Cliente> clienti = app.getListaClienti();
					GestoreFile.serializzaOggetto(clienti, Main.currentDirectory + "clienti.ser");
					GestoreFile.scriviSetSuFile(clienti, Main.currentDirectory + "clienti.txt");

					System.out.println("Cliente registrato con successo");
				} else {
					System.out.println("Cliente già registrato");
				}

				break;
			} catch (ParseException | PersonaException e) {
				System.out.println(e.getMessage());
				System.out.println("Riprovare(true/false)?");
				risp = Boolean.parseBoolean(stdin.readLine());
			}
		}
	}

	/**
	 * Metodo che permette la modifica dei dati di un cliente selezionato inserendo
	 * il suo codice fiscale. Se non si vuole modificare il dato basterà cliccare
	 * invio. Viene effettuato un controllo sul formato della data di nascita
	 * inserita, in caso di errore verrà richiesto di inserire nuovamente la data
	 * oppure continuare con l'operazione di modifica. Non è possibile modificare il
	 * codice fiscale. Scrive la nuova lista clienti dell'app sui file clienti.ser e
	 * clienti.txt. Solleva l'eccezione IOException se c'è un errore allo stream di
	 * input.
	 * 
	 * @param app
	 * @throws IOException
	 */
	private void modificaCliente(Applicazione app) throws IOException {
		try {
			System.out.print("Inserire codice fiscale cliente: ");
			String codiceFiscale = stdin.readLine();
			Cliente cliente = app.getClienteDaCodice(codiceFiscale);
			String line = "";

			System.out.print("Inserire nome o cliccare INVIO per saltare: ");
			line = stdin.readLine();

			if (!line.isEmpty()) {
				cliente.setNome(line);
			}

			System.out.print("Inserire cognome o cliccare INVIO per saltare: ");
			line = stdin.readLine();

			if (!line.isEmpty()) {
				cliente.setCognome(line);
			}

			System.out.print("Inserire luogo di nascita o cliccare INVIO per saltare: ");
			line = stdin.readLine();

			if (!line.isEmpty()) {
				cliente.setLuogoDiNascita(line);
			}

			System.out.print("Inserire data di nascita(dd/MM/yyyy) o cliccare INVIO per saltare: ");
			line = stdin.readLine();

			if (!line.isEmpty()) {
				boolean risp = true;

				while (risp) {
					try {
						cliente.setDataDiNascita(new SimpleDateFormat("dd/MM/yyyy").parse(line));
						break;
					} catch (ParseException | PersonaException e) {
						System.out.println(e.getMessage());
						System.out.println("Riprovare(true/false)?");
						risp = Boolean.parseBoolean(stdin.readLine());

						if (risp) {
							System.out.print("Inserire data di nascita(dd/MM/yyyy): ");
							line = stdin.readLine();
						}
					}
				}
			}

			Set<Cliente> clienti = app.getListaClienti();
			GestoreFile.serializzaOggetto(clienti, Main.currentDirectory + "clienti.ser");
			GestoreFile.scriviSetSuFile(clienti, Main.currentDirectory + "clienti.txt");

		} catch (AppException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Metodo che permette di visualizzare i dati di un cliente cercato mediante
	 * codice fiscale. Solleva l'eccezione IOException se c'è un errore allo stream
	 * di input.
	 * 
	 * @param app
	 * @throws IOException
	 */
	private void visualizzaCliente(Applicazione app) throws IOException {
		try {
			System.out.print("Inserire codice fiscale cliente: ");
			String codiceFiscale = String.valueOf(stdin.readLine());

			System.out.println(app.getClienteDaCodice(codiceFiscale).toString());

		} catch (AppException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Override del metodo astratto "listaMenu" della classe AbstractMenu.
	 * Restituisce una stringa che contiene tutti i valori del menu Cliente.
	 * 
	 * @return menu cliente
	 */
	@Override
	public String listaMenu() {
		return "\n1.Registra\n" + "2.Modifica\n" + "3.Visualizza\n" + "4.Esci dal Menu cliente\n";
	}
}
