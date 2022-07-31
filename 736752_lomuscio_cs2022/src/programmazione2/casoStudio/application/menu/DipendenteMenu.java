/**
 * 
 */
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
import programmazione2.casoStudio.ruoli.Dipendente;
import programmazione2.casoStudio.ruoli.PersonaException;
import programmazione2.casoStudio.util.GestoreFile;

/**
 * La classe DipendenteMenu estende la classe AbstractMenu. Gestisce il menu del
 * dipendente e delle operazioni che si possono effettuare su di esso:
 * registrazione, modifica, visualizzazione e eliminazione.
 * 
 * @author Luca Lomuscio
 *
 */
public class DipendenteMenu extends AbstractMenu {

	/**
	 * Costruttore della classe DipendenteMenu che permette di istanziare un oggetto
	 * DipendenteMenu con il suo unico attributo bufferReader
	 * 
	 * @param bufferReader
	 */
	public DipendenteMenu(BufferedReader bufferReader) {
		super(bufferReader);
	}

	/**
	 * Override del metodo astratto della classe AbstractMenu. Permette la gestione
	 * del menu dipendente. Le operazioni effettuabili in questo menu sono la
	 * registrazione di un nuovo dipendente, la modifca dei dati di un dipendente,
	 * eccetto il codice fiscale e il codice dipendente, la visualizzazione dei dati
	 * di un dipendente e l'eliminazione di un dipendente. Si esce dal menu
	 * dipendente selezionando l'opzione esci dal menu dipendente. Solleva
	 * l'eccezione IOException se c'è un errore allo stream di input.
	 * 
	 * @throws IOException
	 */
	@Override
	public void menu() throws IOException {

		Applicazione app = Applicazione.getInstance();

		int scelta = 0;

		System.out.println("-------------------------\n" + "MENU DIPENDENTE\n" + "-------------------------");

		while (scelta != 5) {
			try {
				System.out.println(listaMenu());
				System.out.print("scelta:");

				scelta = Integer.parseInt(stdin.readLine());

				switch (scelta) {
				case 1:
					creaDipendente(app);
					break;
				case 2:
					modificaDipendente(app);
					break;
				case 3:
					visualizzaDipendente(app);
					break;
				case 4:
					eliminaDipendente(app);
					break;
				case 5:
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
	 * Metodo che permette la creazione del dipendente inserendo tutti i suoi dati:
	 * codice dipendente, codice fiscale, nome, cognome, data di nascita, luogo di
	 * nascita. Vengono effettuati dei controlli sulla validità del pattern del
	 * codice fiscale, del formato della data di nascita inserita e della validità
	 * del codice dipendente. Se i valori non corrispondono a quelli richiesti verrà
	 * chiesto di continuare con l'operazione oppure tornare al menu. Scrive la
	 * nuova lista dipendenti dell'app sui file dipendenti.ser e dipendenti.txt.
	 * Solleva l'eccezione IOException se c'è un errore allo stream di input.
	 * 
	 * @param app
	 * @throws IOException
	 */
	private void creaDipendente(Applicazione app) throws IOException {
		boolean risp = true;
		Date dataDiNascita = new Date();
		Dipendente newDipendente = null;

		while (risp) {
			try {
				System.out.print("Inserire codice fiscale: ");
				String codiceFiscale = String.valueOf(stdin.readLine());

				System.out.print("Inserire codice dipendente: ");
				String codiceDipendente = String.valueOf(stdin.readLine());

				newDipendente = new Dipendente(codiceFiscale, codiceDipendente);

				System.out.print("Inserire nome: ");
				String nome = String.valueOf(stdin.readLine());
				newDipendente.setNome(nome);

				System.out.print("Inserire cognome: ");
				String cognome = String.valueOf(stdin.readLine());
				newDipendente.setCognome(cognome);

				System.out.print("Inserire luogo di nascita: ");
				String luogoDiNascita = String.valueOf(stdin.readLine());
				newDipendente.setLuogoDiNascita(luogoDiNascita);

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
				newDipendente.setDataDiNascita(dataDiNascita);

				int numDipendenti = app.getDipendenti().size();
				app.addDipendente(newDipendente);

				if (numDipendenti < app.getDipendenti().size()) {

					Set<Dipendente> dipendenti = app.getListaDipendenti();
					GestoreFile.serializzaOggetto(dipendenti, Main.currentDirectory + "dipendenti.ser");
					GestoreFile.scriviSetSuFile(dipendenti, Main.currentDirectory + "dipendenti.txt");

					System.out.println("Dipendente registrato con successo");
				} else {
					System.out.println("Dipendente già registrato");
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
	 * Metodo che permette la modifica dei dati di un dipendente selezionato
	 * inserendo il suo codice dipendente. Se non si vuole modificare il dato
	 * basterà cliccare invio. Viene effettuato un controllo sul formato della data
	 * di nascita inserita, in caso di errore verrà richiesto di inserire nuovamente
	 * la data oppure continuare con l'operazione di modifica. Non è possibile
	 * modificare il codice cliente e il codice fiscale.Scrive la nuova lista
	 * dipendenti dell'app sui file dipendenti.ser e dipendenti.txt. Solleva
	 * l'eccezione IOException se c'è un errore allo stream di input.
	 * 
	 * @param app
	 * @throws IOException
	 */
	private void modificaDipendente(Applicazione app) throws IOException {
		try {
			System.out.print("Inserire codice dipendente: ");
			String codice = String.valueOf(stdin.readLine());
			Dipendente dipendente = app.getDipendenteDaCodice(codice);
			String line = "";

			System.out.print("Inserire nome o cliccare INVIO per saltare: ");
			line = stdin.readLine();

			if (!line.isEmpty()) {
				dipendente.setNome(line);
			}

			System.out.print("Inserire cognome o cliccare INVIO per saltare: ");
			line = stdin.readLine();

			if (!line.isEmpty()) {
				dipendente.setCognome(line);
			}

			System.out.print("Inserire luogo di nascita o cliccare INVIO per saltare: ");
			line = stdin.readLine();

			if (!line.isEmpty()) {
				dipendente.setLuogoDiNascita(line);
			}

			System.out.print("Inserire data di nascita(dd/MM/yyyy) o cliccare INVIO per saltare: ");
			line = stdin.readLine();

			if (!line.isEmpty()) {

				boolean risp = true;

				while (risp) {
					try {
						dipendente.setDataDiNascita(new SimpleDateFormat("dd/MM/yyyy").parse(line));
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

			Set<Dipendente> dipendenti = app.getListaDipendenti();
			GestoreFile.serializzaOggetto(dipendenti, Main.currentDirectory + "dipendenti.ser");
			GestoreFile.scriviSetSuFile(dipendenti, Main.currentDirectory + "dipendenti.txt");

		} catch (AppException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Metodo che permette di visualizzare i dati di un dipendente cercato mediante
	 * codice dipendente. Solleva l'eccezione IOException se c'è un errore allo
	 * stream di input.
	 * 
	 * @param app
	 * @throws IOException
	 */
	private void visualizzaDipendente(Applicazione app) throws IOException {
		try {
			System.out.print("Inserire codice dipendente: ");
			String codice = String.valueOf(stdin.readLine());

			System.out.println(app.getDipendenteDaCodice(codice).toString());
		} catch (AppException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Metodo che permette di eliminare i dati di un dipendente cercato mediante
	 * codice dipendente. Scrive la nuova lista dipendenti dell'app sui file
	 * dipendenti.ser e dipendenti.txt. Solleva l'eccezione IOException se c'è un
	 * errore allo stream di input.
	 * 
	 * @param app
	 * @throws IOException
	 */
	private void eliminaDipendente(Applicazione app) throws IOException {
		try {
			System.out.println("inserire codice dipendente da eliminare: ");
			String codice = String.valueOf(stdin.readLine());
			app.removeDipendente(codice);

			System.out.println("Dipendente eliminato con successo");

			Set<Dipendente> dipendenti = app.getListaDipendenti();
			GestoreFile.serializzaOggetto(dipendenti, Main.currentDirectory + "dipendenti.ser");
			GestoreFile.scriviSetSuFile(dipendenti, Main.currentDirectory + "dipendenti.txt");
		} catch (AppException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Override del metodo astratto "listaMenu" della classe AbstractMenu.
	 * Restituisce una stringa che contiene tutti i valori del menu dipendente.
	 * 
	 * @return menu dipendente
	 */
	@Override
	public String listaMenu() {
		return "\n1.Registra\n" + "2.Modifica\n" + "3.Visualizza\n" + "4.Elimina\n" + "5.Esci dal Menu dipendente\n";
	}

}
