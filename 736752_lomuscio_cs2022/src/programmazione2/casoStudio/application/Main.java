/**
 * 
 */
package programmazione2.casoStudio.application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import programmazione2.casoStudio.azioni.Noleggio;
import programmazione2.casoStudio.azioni.Vendita;
import programmazione2.casoStudio.dispositivi.Smartphone;
import programmazione2.casoStudio.dispositivi.SmartphoneAvanzato;
import programmazione2.casoStudio.dispositivi.SmartphoneBase;
import programmazione2.casoStudio.dispositivi.SmartphoneMedio;
import programmazione2.casoStudio.ruoli.Cliente;
import programmazione2.casoStudio.ruoli.ClienteException;
import programmazione2.casoStudio.ruoli.Dipendente;
import programmazione2.casoStudio.ruoli.DipendenteException;
import programmazione2.casoStudio.util.Serializator;

/**
 * @author lucal
 *
 */
public class Main {

	private static String currentDirectory = "..//fileSerializzati/";
	private static BufferedReader stdin;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			Applicazione app = new Applicazione();
			fetchData(app);
			stdin = new BufferedReader(new InputStreamReader(System.in));

			int scelta = 0;

			System.out.println("-------------------------\n" + "BENVENUTI\n" + "-------------------------");

			while (scelta != 11) {
				try {
					System.out.println(app.menu());
					System.out.print("scelta:");

					scelta = Integer.parseInt(stdin.readLine());

					switch (scelta) {
					case 1:
						addSmartphone(app);
						break;
					case 2:
						creaCliente(app);
						break;
					case 3:
						creaDipendente(app);
						break;
					case 4:
						removeDipendente(app);
						break;
					case 5:
						venditaSmartphone(app);
						break;
					case 6:
						noleggioSmartphone(app);
						break;
					case 7:
						elencoSmartphoneNoleggiati(app);
						break;
					case 8:
						elencoSMartphoneMaiNoleggiati(app);
						break;
					case 9:
						elencoSmartphoneVendutiDaDipendente(app);
						break;
					case 10:
						fineNoleggio(app);
						break;
					case 11:
						Serializator.serializzaOggetto(app.getNoleggi(), currentDirectory + "noleggi");
						Serializator.serializzaOggetto(app.getVendite(), currentDirectory + "vendite");
						app.close();
						break;
					default:
						System.out.println("nessuna operazione effettuata");
					}
				} catch (NumberFormatException e) {
					System.out.println(e.getMessage());
				}
			}
			System.out.println("ARRIVEDERCI");
			stdin.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @param app
	 */
	@SuppressWarnings("unchecked")
	private static void fetchData(Applicazione app) {

		Set<Cliente> clienti = (Set<Cliente>) Serializator.deserializzaOggetto(currentDirectory + "clienti");
		app.setClienti(clienti != null ? clienti : new HashSet<Cliente>());

		Set<Dipendente> dipendenti = (Set<Dipendente>) Serializator
				.deserializzaOggetto(currentDirectory + "dipendenti");
		app.setDipendenti(dipendenti != null ? dipendenti : new HashSet<Dipendente>());

		Set<Smartphone> smartphone = (Set<Smartphone>) Serializator
				.deserializzaOggetto(currentDirectory + "catalogo_smartphone");
		app.setCatalogoSmartphone(smartphone != null ? smartphone : new HashSet<Smartphone>());

		List<Noleggio> noleggi = (List<Noleggio>) Serializator.deserializzaOggetto(currentDirectory + "noleggi");
		app.setNoleggi(noleggi != null ? noleggi : new LinkedList<Noleggio>());

		List<Vendita> vendite = (List<Vendita>) Serializator.deserializzaOggetto(currentDirectory + "vendite");
		app.setVendite(vendite != null ? vendite : new LinkedList<Vendita>());
	}

	private static void creaCliente(Applicazione app) throws IOException {
		boolean risp = true;

		while (risp) {
			try {
				System.out.print("Inserire nome: ");
				String nome = stdin.readLine();

				System.out.print("Inserire cognome: ");
				String cognome = stdin.readLine();

				System.out.print("Inserire data di nascita(dd/MM/yyyy): ");
				Date dataDiNascita = new SimpleDateFormat("dd/MM/yyyy").parse(stdin.readLine());

				System.out.print("Inserire luogo di nascita: ");
				String luogoDiNascita = stdin.readLine();

				System.out.print("Inserire codice fiscale: ");
				String codiceFiscale = stdin.readLine();
				Cliente newCliente = new Cliente(nome, cognome, dataDiNascita, luogoDiNascita, codiceFiscale);

				app.addCliente(newCliente);
				Serializator.serializzaOggetto(app.getClienti(), currentDirectory + "clienti");
				risp = false;

			} catch (ParseException | ClienteException e) {
				System.out.println(e.getMessage());
				System.out.println("Riprovare(true/false)?");
				risp = Boolean.parseBoolean(stdin.readLine());
			}
		}
	}

	private static void creaDipendente(Applicazione app) throws IOException {
		boolean risp = true;

		while (risp) {
			try {
				System.out.print("Inserire nome: ");
				String nome = stdin.readLine();

				System.out.print("Inserire cognome: ");
				String cognome = stdin.readLine();

				System.out.print("Inserire data di nascita(dd/MM/yyyy): ");
				Date dataDiNascita = new SimpleDateFormat("dd/MM/yyyy").parse(stdin.readLine());

				System.out.print("Inserire luogo di nascita: ");
				String luogoDiNascita = stdin.readLine();

				System.out.print("Inserire codice dipendente: ");
				String codiceDipendente = stdin.readLine();
				Dipendente newDipendente = new Dipendente(nome, cognome, dataDiNascita, luogoDiNascita,
						codiceDipendente);

				app.addDipendente(newDipendente);
				Serializator.serializzaOggetto(app.getDipendenti(), currentDirectory + "dipendenti");
				risp = false;

			} catch (ParseException | DipendenteException e) {
				System.out.println(e.getMessage());
				System.out.println("Riprovare(true/false)?");
				risp = Boolean.parseBoolean(stdin.readLine());
			}
		}
	}

	private static void removeDipendente(Applicazione app) throws IOException {
		try {
			System.out.println("inserire codice dipendente da eliminare");
			String codice = stdin.readLine();
			app.removeDipendente(codice);
			Serializator.serializzaOggetto(app.getDipendenti(), currentDirectory + "dipendenti");
		} catch (AppException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void addSmartphone(Applicazione app) throws IOException {
		Smartphone smartphone = null;
		boolean risp = true;

		while (risp) {
			try {
				System.out.print("inserire codice IMEI:");
				long codiceIMEI = Long.parseLong(stdin.readLine());

				System.out.print("inserire modello:");
				String modello = stdin.readLine();

				System.out.print("inserire memoria:");
				int memoria = Integer.parseInt(stdin.readLine());

				System.out.print("inserire ram:");
				int ram = Integer.parseInt(stdin.readLine());

				System.out.print("inserire processore:");
				String processore = stdin.readLine();

				System.out.print("inserire risoluzione:");
				String risoluzione = stdin.readLine();

				System.out.print("inserire nome dispositivo:");
				String nomeDispositivo = stdin.readLine();

				System.out.print("inserire marca:");
				String marca = stdin.readLine();

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
				Serializator.serializzaOggetto(app.getCatalogoSmartphone(), currentDirectory + "catalogo_smartphone");
				risp = false;
			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
				System.out.println("Riprovare(true/false)?");
				risp = Boolean.parseBoolean(stdin.readLine());
			} catch (Exception e) {
				e.printStackTrace();
				risp = false;
			}

		}

	}

	private static void venditaSmartphone(Applicazione app) throws IOException {
		Vendita vendita = null;
		boolean risp = true;
		while (risp) {
			try {
				System.out.println("Inserire prezzo:");
				double prezzo = Double.parseDouble(stdin.readLine());
				System.out.println("Inserire codice dipendente:");
				String codiceDipendente = stdin.readLine();
				Dipendente dipendente = app.getDipendenteDaCodice(codiceDipendente);

				System.out.println("Inserire codice fiscale cliente:");
				String codiceCliente = stdin.readLine();
				Cliente cliente = app.getClienteDaCodice(codiceCliente);

				vendita = new Vendita(new Date(), prezzo, dipendente);
				System.out.println("Inserire numero smartphone venduti");
				int smartphoneVenduti = Integer.parseInt(stdin.readLine());
				while (smartphoneVenduti != 0) {
					System.out.println("Inserire codice IMEI smartphone");
					long codiceIMEI = Long.parseLong(stdin.readLine());
					vendita.addSmartphone(app.getSmartphoneDaCodice(codiceIMEI));
					smartphoneVenduti--;
				}
				app.venditaSmartphone(vendita, cliente);
				Serializator.serializzaOggetto(app.getCatalogoSmartphone(), currentDirectory + "catalogo_smartphone");
				risp = false;
			} catch (AppException | NumberFormatException e) {
				System.out.println(e.getMessage() + "." + " Riprovare?(true/false)");
				risp = Boolean.parseBoolean(stdin.readLine());
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Vendita non riuscita");
				risp = false;
			}
		}
	}

	private static void noleggioSmartphone(Applicazione app) throws IOException {
		Noleggio noleggio = null;
		boolean risp = true;

		while (risp) {
			try {
				System.out.println("Inserire prezzo:");
				double prezzo = Double.parseDouble(stdin.readLine());

				System.out.println("Inserire data fine noleggio:");
				String dataFineNoleggio = stdin.readLine();
				System.out.println("Inserire codice dipendente:");
				String codiceDipendente = stdin.readLine();
				Dipendente dipendente = app.getDipendenteDaCodice(codiceDipendente);

				System.out.println("Inserire codice fiscale cliente:");
				String codiceCliente = stdin.readLine();
				Cliente cliente = app.getClienteDaCodice(codiceCliente);

				noleggio = new Noleggio(new Date(), prezzo, dipendente,
						new SimpleDateFormat("dd/MM/yyyy").parse(dataFineNoleggio));

				System.out.println("Inserire numero smartphone noleggiati");
				int smartphoneNoleggiati = Integer.parseInt(stdin.readLine());
				while (smartphoneNoleggiati != 0) {
					System.out.println("Inserire codice IMEI smartphone");
					long codiceIMEI = Long.parseLong(stdin.readLine());
					noleggio.addSmartphone(app.getSmartphoneDaCodice(codiceIMEI));
					smartphoneNoleggiati--;
				}
				app.noleggiaSmartphone(noleggio, cliente);
				Serializator.serializzaOggetto(app.getCatalogoSmartphone(), currentDirectory + "catalogo_smartphone");
				risp = false;

			} catch (ClienteException | DipendenteException | AppException | NumberFormatException e) {
				System.out.println(e.getMessage());
				System.out.println("Noleggio non riuscito. Riprovare?(true/false)");
				risp = Boolean.parseBoolean(stdin.readLine());
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Noleggio non riuscito");
				risp = false;
			}
		}
	}

	private static void elencoSmartphoneNoleggiati(Applicazione app) {
		try {
			PrintWriter writeOnFile = new PrintWriter(
					new BufferedWriter(new FileWriter(currentDirectory + "//smartphoneNoleggiati.txt")));
			for (Smartphone smartphone : app.elencoSmartphoneNoleggiati()) {
				writeOnFile.print(smartphone.toString() + '\n');
			}
			writeOnFile.close();
			System.out.println("elenco stampato");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void elencoSMartphoneMaiNoleggiati(Applicazione app) {
		try {
			PrintWriter writeOnFile = new PrintWriter(
					new BufferedWriter(new FileWriter(currentDirectory + "//smartphone_mai_noleggiati.txt")));
			for (Smartphone smartphone : app.elencoSmartphoneMaiNoleggiati()) {
				writeOnFile.print(smartphone.toString() + '\n');
			}
			writeOnFile.close();
			System.out.println("elenco stampato");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void elencoSmartphoneVendutiDaDipendente(Applicazione app) throws IOException {
		boolean risp = true;
		while (risp) {
			try {
				System.out.println("Inserire codice dipendente:");
				String codiceDipendente = stdin.readLine();
				Dipendente dipendente = app.getDipendenteDaCodice(codiceDipendente);
				PrintWriter writeOnFile = new PrintWriter(new BufferedWriter(new FileWriter(
						currentDirectory + "//smartphone_noleggiati_da_dipendente_" + codiceDipendente + ".txt")));
				for (Smartphone smartphone : app.smartphoneVendutiDaDipendente(dipendente)) {
					writeOnFile.print(smartphone.toString() + '\n');
				}
				writeOnFile.close();
				System.out.println("elenco stampato");
				risp = false;
			} catch (AppException e) {
				System.out.println("Riprovare?(true/false)");
				risp = Boolean.parseBoolean(stdin.readLine());
				e.printStackTrace();
			}
		}
	}

	private static void fineNoleggio(Applicazione app) throws IOException {
		boolean risp = true;
		while (risp) {
			try {
				System.out.println("scrivere codice noleggio:");
				int codiceNoleggio = Integer.parseInt(stdin.readLine());
				if (!app.fineNoleggio(app.getNoleggioDaCodice(codiceNoleggio)))
					System.out.println("Noleggio finito dopo il tempo stabilito");
				Serializator.serializzaOggetto(app.getCatalogoSmartphone(), currentDirectory + "catalogo_smartphone");
				risp = false;
			} catch (NumberFormatException | AppException e) {
				System.out.println(e.getMessage());
				System.out.println("Riprovare?(true/false)");
				risp = Boolean.parseBoolean(stdin.readLine());
			}
		}
	}

}
