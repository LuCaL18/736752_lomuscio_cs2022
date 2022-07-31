package programmazione2.casoStudio.application.menu;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * La classe MainMenu estende la classe AbstractMenu. Gestisce il menu
 * principale ed è un punto di accesso a tutti gli altri menu: menu cliente,
 * menu dipendente, menu noleggio, menu smartphone e menu vendita.
 * 
 * @author Luca Lomuscio
 *
 */
public class MainMenu extends AbstractMenu {

	/**
	 * Costruttore della classe MainMenu che permette di istanziare un oggetto
	 * MainMenu con il suo unico attributo bufferReader
	 * 
	 * @param bufferReader
	 */
	public MainMenu(BufferedReader bufferReader) {
		super(bufferReader);
	}

	/**
	 * Override del metodo astratto della classe AbstractMenu. Permette la gestione
	 * del menu principale. È un punto di accesso a tutti gli altri menu
	 * dell'applicazione. Solleva l'eccezione IOException se c'è un errore allo
	 * stream di input.
	 * 
	 * @throws IOException
	 */
	@Override
	public void menu() throws IOException {
		ClienteMenu cm = new ClienteMenu(stdin);
		DipendenteMenu dm = new DipendenteMenu(stdin);
		NoleggioMenu nm = new NoleggioMenu(stdin);
		SmartphoneMenu sm = new SmartphoneMenu(stdin);
		VenditaMenu vm = new VenditaMenu(stdin);

		int scelta = 0;

		System.out.println("-------------------------\n" + "BENVENUTI\n" + "-------------------------");

		while (scelta != 6) {
			try {
				System.out.println(listaMenu());
				System.out.print("scelta:");

				scelta = Integer.parseInt(stdin.readLine());

				switch (scelta) {
				case 1:
					cm.menu();
					break;
				case 2:
					dm.menu();
					break;
				case 3:
					sm.menu();
					break;
				case 4:
					nm.menu();
					break;
				case 5:
					vm.menu();
					break;
				case 6:
					break;
				default:
					System.out.println("nessuna operazione effettuata");
				}
			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
			}
		}
		System.out.println("ARRIVEDERCI");
	}

	/**
	 * Override del metodo astratto "listaMenu" della classe AbstractMenu.
	 * Restituisce una stringa che contiene tutti i valori del menu principale.
	 * 
	 * @return menu principale
	 */
	@Override
	public String listaMenu() {
		return "\n1.Cliente menu\n" + "2.Dipendente menu\n" + "3.Smartphone menu\n" + "4.Noleggio menu\n"
				+ "5.Vendita menu\n" + "6.Chiudi applicazione\n";
	}

}
