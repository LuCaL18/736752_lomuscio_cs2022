/**
 * 
 */
package programmazione2.casoStudio.application.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import programmazione2.casoStudio.application.AppException;
import programmazione2.casoStudio.application.Applicazione;
import programmazione2.casoStudio.application.Main;
import programmazione2.casoStudio.dispositivi.Smartphone;
import programmazione2.casoStudio.dispositivi.SmartphoneAvanzato;
import programmazione2.casoStudio.dispositivi.SmartphoneBase;
import programmazione2.casoStudio.dispositivi.SmartphoneException;
import programmazione2.casoStudio.dispositivi.SmartphoneMedio;
import programmazione2.casoStudio.util.GestoreFile;

/**
 * La classe SmartphoneMenu estende la classe AbstractMenu. Gestisce il menu
 * smartphone e le operazioni che si possono effettuare su di essi: aggiunta al
 * catalogo e visualizzazione.
 * 
 * @author Luca Lomuscio
 *
 */
public class SmartphoneMenu extends AbstractMenu {

	/**
	 * Costruttore della classe SmartphoneMenu che permette di istanziare un oggetto
	 * SmartphoneMenu con il suo unico attributo bufferReader
	 * 
	 * @param bufferReader
	 */
	public SmartphoneMenu(BufferedReader bufferReader) {
		super(bufferReader);
	}

	/**
	 * Override del metodo astratto della classe AbstractMenu. Permette la gestione
	 * del menu smartphone. Le operazioni effettuabili in questo menu sono
	 * l'aggiunta di un nuovo smartphone al catalogo e la visualizzazione dei dati
	 * di uno smartphone. Si esce dal menu smartphone selezionando l'opzione esci
	 * dal menu smartphone. Solleva l'eccezione IOException se c'è un errore allo
	 * stream di input.
	 * 
	 * @throws IOException
	 */
	@Override
	public void menu() throws IOException {

		Applicazione app = Applicazione.getInstance();

		int scelta = 0;

		System.out.println("-------------------------\n" + "MENU SMARTPHONE\n" + "-------------------------");

		while (scelta != 3) {
			try {
				System.out.println(listaMenu());
				System.out.print("scelta:");

				scelta = Integer.parseInt(stdin.readLine());

				switch (scelta) {
				case 1:
					addSmartphone(app);
					break;
				case 2:
					visualizzaSmartphone(app);
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
	 * Metodo che permette di aggiungere un nuovo smartphone di ogni tipo (Base,
	 * Medio, Avanzato) al catalogo inserendo tutti i suoi dati: codice IMEI,
	 * memoria, ram, modello, processore, risoluzione, nome dispositivo, marca, la
	 * presenza della doppia fotocamera e del riconoscimento vocale e del
	 * riconoscimento con impronta se presente la doppia fotocamera. Vengono
	 * effettuati dei controlli sulla validità del codice IMEI e del valore intero
	 * per la memoria e la ram. Entrambi i valori sono espressi in gigaByte. Se i
	 * valori non corrispondono a quelli richiesti verrà chiesto di continuare con
	 * l'operazione oppure tornare al menu. Se l'operazione è conclusa e va a buon
	 * fine scrive la nuova lista degli smartphone dell'app sui file
	 * catalogo_smartphone.ser e catalogo_smartphone.txt. Solleva l'eccezione
	 * IOException se c'è un errore allo stream di input.
	 * 
	 * @param app
	 * @throws IOException
	 */
	private void addSmartphone(Applicazione app) throws IOException {
		Smartphone smartphone = null;
		boolean risp = true;
		int memoria = 0;
		int ram = 0;
		String codiceIMEI = null;
		String modello = null;
		String processore = null;
		String risoluzione = null;
		String nomeDispositivo = null;
		String marca = null;

		while (risp) {
			try {

				if (codiceIMEI == null) {
					System.out.print("inserire codice IMEI:");
					codiceIMEI = String.valueOf(stdin.readLine());
				}

				if (memoria <= 0) {
					System.out.print("inserire memoria:");
					memoria = Integer.parseInt(stdin.readLine());
				}

				if (ram <= 0) {
					System.out.print("inserire ram:");
					ram = Integer.parseInt(stdin.readLine());
				}

				if (modello == null) {
					System.out.print("inserire modello:");
					modello = String.valueOf(stdin.readLine());
				}

				if (processore == null) {
					System.out.print("inserire processore:");
					processore = String.valueOf(stdin.readLine());
				}

				if (risoluzione == null) {
					System.out.print("inserire risoluzione:");
					risoluzione = String.valueOf(stdin.readLine());
				}

				if (nomeDispositivo == null) {
					System.out.print("inserire nome dispositivo:");
					nomeDispositivo = String.valueOf(stdin.readLine());
				}

				if (marca == null) {
					System.out.print("inserire marca:");
					marca = String.valueOf(stdin.readLine());
				}

				System.out.print("doppia fotocamera?(true/false):");
				if (Boolean.parseBoolean(stdin.readLine())) {
					System.out.print("riconoscimento vocale e riconoscimento impronta?(true/false):");
					if (Boolean.parseBoolean(stdin.readLine())) {
						smartphone = new SmartphoneAvanzato(codiceIMEI, modello, memoria, ram, processore, risoluzione,
								nomeDispositivo, marca);
					} else {
						smartphone = new SmartphoneMedio(codiceIMEI, modello, memoria, ram, processore, risoluzione,
								nomeDispositivo, marca);
					}
				} else {
					smartphone = new SmartphoneBase(codiceIMEI, modello, memoria, ram, processore, risoluzione,
							nomeDispositivo, marca);
				}

				app.addSmartphone(smartphone);

				List<Smartphone> smartphones = app.getListaSmartphone();
				GestoreFile.serializzaOggetto(smartphones, Main.currentDirectory + "catalogo_smartphone.ser");
				GestoreFile.scriviListSuFile(smartphones, Main.currentDirectory + "catalogo_smartphone.txt");
				System.out.println("Smartphone aggiunto con successo");

				break;
			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
				System.out.println("Riprovare(true/false)?");
				risp = Boolean.parseBoolean(stdin.readLine());
			} catch (SmartphoneException e) {
				System.out.println(e.getMessage());
				System.out.println("Riprovare(true/false)?");
				risp = Boolean.parseBoolean(stdin.readLine());
				codiceIMEI = null;
			} catch (AppException e) {
				System.out.println(e.getMessage());
				break;
			}

		}

	}

	/**
	 * Metodo che permette di visualizzare i dati di uno smartphone cercato mediante
	 * codice IMEI. Solleva l'eccezione IOException se c'è un errore allo stream di
	 * input.
	 * 
	 * @param app
	 * @throws IOException
	 */
	public void visualizzaSmartphone(Applicazione app) throws IOException {
		try {
			System.out.print("Inserire codice IMEI smartphone: ");
			String codice = String.valueOf(stdin.readLine());

			System.out.println(app.getSmartphoneDaCodice(codice));

		} catch (AppException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Override del metodo astratto "listaMenu" della classe AbstractMenu.
	 * Restituisce una stringa che contiene tutti i valori del menu smartphone.
	 * 
	 * @return menu smartphone
	 */
	@Override
	public String listaMenu() {
		return "\n1.Aggiungi\n" + "2.Visualizza\n" + "3.Esci dal menu smartphone";
	}

}
