/**
 * 
 */
package programmazione2.casoStudio.application;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import programmazione2.casoStudio.azioni.Noleggio;
import programmazione2.casoStudio.azioni.Vendita;
import programmazione2.casoStudio.dispositivi.Smartphone;
import programmazione2.casoStudio.ruoli.Cliente;
import programmazione2.casoStudio.ruoli.ClienteException;
import programmazione2.casoStudio.ruoli.Dipendente;
import programmazione2.casoStudio.ruoli.DipendenteException;

/**
 * @author lucal
 *
 */
public class Main {

	Map<String, Dipendente> dipendenti = new HashMap<String, Dipendente>();
	Map<String, Cliente> clienti = new HashMap<String, Cliente>();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Applicazione app = new Applicazione();
		Main main = new Main();
		Scanner scan = new Scanner(System.in);
		int scelta = 0;

		System.out.println("-------------------------\n" + "BENVENUTI\n" + "-------------------------");

		while (scelta != 6) {
			app.printMenu();
			System.out.print("scelta: ");

			scelta = scan.nextInt();

			switch (scelta) {
			case 1:
				main.venditaSmartphone(app);
				break;
			case 2:
				main.noleggioSmartphone(app);
				break;
			case 3:
				main.elencoSmartphoneNoleggiati(app);
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break;
			default:
				System.out.println("nessuna operazione effettuata");
			}
		}

		System.out.println("ARRIVEDERCI");
		scan.close();

	}

	public void venditaSmartphone(Applicazione app) {
		Vendita vendita = null;
		boolean risp = true;
		Scanner scan = new Scanner(System.in);
		System.out.println("Inserire prezzo:");
		double prezzo = scan.nextFloat();
		while (risp) {
			try {
				System.out.println("Inserire codice dipendente:");
				String codiceDipendente = scan.nextLine();
				vendita = new Vendita(new Date(), prezzo, this.getDipendenteDaCodice(codiceDipendente));
				System.out.println("Inserire numero smartphone venduti");
				int smartphoneVenduti = scan.nextInt();
				while (smartphoneVenduti != 0) {
					System.out.println("Inserire codice IMEI smartphone");
					int codiceIMEI = scan.nextInt();
					vendita.addSmartphone(app.getSmartphoneDaCodice(codiceIMEI));
					smartphoneVenduti--;
				}
				app.venditaSmartphone(vendita);
			} catch (AppException e) {
				e.printStackTrace();
				System.out.println("Noleggio non riuscito. Riprovare?(true/false)");
				risp = scan.nextBoolean();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Vendita non riuscita");
				risp = false;
			}
		}
		scan.close();
	}

	public void noleggioSmartphone(Applicazione app) {
		Noleggio noleggio = null;
		boolean risp = true;
		Scanner scan = new Scanner(System.in);

		System.out.println("Inserire prezzo:");
		double prezzo = scan.nextFloat();

		System.out.println("Inserire data fine noleggio:");
		String dataFineNoleggio = scan.nextLine();

		while (risp) {
			try {
				System.out.println("Inserire codice dipendente:");
				String codiceDipendente = scan.nextLine();

				System.out.println("Inserire codice fiscale cliente:");
				String codiceCliente = scan.nextLine();

				Cliente cliente = this.getClienteDaCodice(codiceCliente);
				Dipendente dipendente = this.getDipendenteDaCodice(codiceDipendente);

				noleggio = new Noleggio(new Date(), prezzo, dipendente,
						new SimpleDateFormat("dd/MM/yyyy").parse(dataFineNoleggio));

				System.out.println("Inserire numero smartphone noleggiati");
				int smartphoneNoleggiati = scan.nextInt();
				cliente.addNoleggio(smartphoneNoleggiati);
				dipendente.addNoleggio(smartphoneNoleggiati);
				while (smartphoneNoleggiati != 0) {
					System.out.println("Inserire codice IMEI smartphone");
					int codiceIMEI = scan.nextInt();
					noleggio.addSmartphone(app.getSmartphoneDaCodice(codiceIMEI));
					smartphoneNoleggiati--;
				}
				app.noleggiaSmartphone(noleggio);
				risp = false;

			} catch (ClienteException | DipendenteException | AppException e) {
				e.printStackTrace();
				System.out.println("Noleggio non riuscito. Riprovare?(true/false)");
				risp = scan.nextBoolean();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Noleggio non riuscito");
				risp = false;
			}
		}
		scan.close();
	}

	public void elencoSmartphoneNoleggiati(Applicazione app) {
		try {
			PrintWriter writeOnFile = new PrintWriter(new BufferedWriter(new FileWriter("smartphoneNoleggiati.txt")));
			for (Smartphone smartphone : app.elencoSmartphoneNoleggiati()) {
				writeOnFile.print(smartphone.toString() + '\n');
			}
			writeOnFile.close();
			System.out.println("elenco stampato");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Dipendente getDipendenteDaCodice(String codiceDipendente) throws Exception {
		if (!this.dipendenti.containsKey(codiceDipendente))
			throw new Exception("il dipendente inserito non esiste");
		return this.dipendenti.get(codiceDipendente);
	}

	private Cliente getClienteDaCodice(String codiceFiscale) throws Exception {
		if (!this.clienti.containsKey(codiceFiscale))
			throw new Exception("il cliente inserito non esiste");
		return this.clienti.get(codiceFiscale);
	}

}
