/**
 * 
 */
package programmazione2.casoStudio.application;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

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

/**
 * @author lucal
 *
 */
public class Main {

	String currentDirectory = "..//fileSerializzati";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Applicazione app = new Applicazione();
		Main main = new Main();
		Scanner scan = new Scanner(System.in);
		int scelta = 0;

		System.out.println("-------------------------\n" + "BENVENUTI\n" + "-------------------------");

		while (scelta != 11) {
			System.out.println(app.menu());
			System.out.print("scelta: ");

			scelta = scan.nextInt();

			switch (scelta) {
			case 1:
				main.addSmartphone(app);
				break;
			case 2:
				main.creaCliente(app);
				break;
			case 3:
				main.creaDipendente(app);
				break;
			case 4:
				main.removeDipendente(app);
				break;
			case 5:
				main.venditaSmartphone(app);
				break;
			case 6:
				main.noleggioSmartphone(app);
				break;
			case 7:
				main.elencoSmartphoneNoleggiati(app);
				break;
			case 8:
				main.elencoSMartphoneMaiNoleggiati(app);
				break;
			case 9:
				main.elencoSmartphoneVendutiDaDipendente(app);
				break;
			case 10:
				main.fineNoleggio(app);
				break;
			case 11:
				main.serializzaOggetto(app.getNoleggi(), "noleggi_serializzati");
				main.serializzaOggetto(app.getVendite(), "vendite_serializzate");
				break;
			default:
				System.out.println("nessuna operazione effettuata");
			}
		}

		System.out.println("ARRIVEDERCI");
		scan.close();

	}

	public void creaCliente(Applicazione app) {
		Scanner scan = new Scanner(System.in);
		boolean risp = true;

		while (risp) {
			try {
				System.out.print("Inserire nome");
				String nome = scan.nextLine();

				System.out.print("Inserire cognome");
				String cognome = scan.nextLine();

				System.out.print("Inserire data di nascita");
				Date dataDiNascita = new SimpleDateFormat("dd/MM/yyyy").parse(scan.nextLine());

				System.out.print("Inserire codice fiscale");
				String codiceFiscale = scan.nextLine();
				Cliente newCliente = new Cliente(nome, cognome, dataDiNascita, codiceFiscale);

				app.addCliente(newCliente);

			} catch (ParseException | ClienteException e) {
				System.out.println(e.getMessage());
				System.out.println("Riprovare(true/false)?");
				risp = scan.nextBoolean();
			}
		}

		scan.close();
	}

	public void creaDipendente(Applicazione app) {
		Scanner scan = new Scanner(System.in);
		boolean risp = true;

		while (risp) {
			try {
				System.out.print("Inserire nome");
				String nome = scan.nextLine();

				System.out.print("Inserire cognome");
				String cognome = scan.nextLine();

				System.out.print("Inserire data di nascita");
				Date dataDiNascita = new SimpleDateFormat("dd/MM/yyyy").parse(scan.nextLine());

				System.out.print("Inserire codice dipendente");
				String codiceDipendente = scan.nextLine();
				Dipendente newDipendente = new Dipendente(nome, cognome, dataDiNascita, codiceDipendente);

				app.addDipendente(newDipendente);

			} catch (ParseException | DipendenteException e) {
				System.out.println(e.getMessage());
				System.out.println("Riprovare(true/false)?");
				risp = scan.nextBoolean();
			}
		}

		scan.close();
	}

	public void removeDipendente(Applicazione app) {
		Scanner scan = new Scanner(System.in);
		try {
			System.out.println("inserire codice dipendente da eliminare");
			String codice = scan.nextLine();
			app.removeDipendente(codice);
		} catch (AppException e) {
			System.out.println(e.getMessage());
		}
		scan.close();
	}

	public void addSmartphone(Applicazione app) {
		Scanner scan = new Scanner(System.in);
		Smartphone smartphone = null;
		boolean risp = true;

		while (risp) {
			try {
				System.out.print("inserire codice IMEI:");
				int codiceIMEI = scan.nextInt();

				System.out.print("inserire modello:");
				String modello = scan.nextLine();

				System.out.print("inserire memoria:");
				int memoria = scan.nextInt();

				System.out.print("inserire ram:");
				int ram = scan.nextInt();

				System.out.print("inserire processore:");
				String processore = scan.nextLine();

				System.out.print("inserire risoluzione:");
				String risoluzione = scan.nextLine();

				System.out.print("inserire nome dispositivo:");
				String nomeDispositivo = scan.nextLine();

				System.out.print("inserire marca:");
				String marca = scan.nextLine();

				System.out.print("doppia fotocamera?(true/false):");
				if (scan.nextBoolean()) {
					System.out.print("riconoscimento vocale e riconoscimento impronta?(true/false):");
					if (scan.nextBoolean()) {
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
				risp = false;
			} catch (AppException e) {
				System.out.println(e.getMessage());
				System.out.println("Riprovare?(true/false)");
				risp = scan.nextBoolean();
			} catch (Exception e) {
				e.printStackTrace();
				risp = false;
			}

		}
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
				Dipendente dipendente = app.getDipendenteDaCodice(codiceDipendente);

				System.out.println("Inserire codice fiscale cliente:");
				String codiceCliente = scan.nextLine();
				Cliente cliente = app.getClienteDaCodice(codiceCliente);

				vendita = new Vendita(new Date(), prezzo, dipendente);
				System.out.println("Inserire numero smartphone venduti");
				int smartphoneVenduti = scan.nextInt();
				while (smartphoneVenduti != 0) {
					System.out.println("Inserire codice IMEI smartphone");
					int codiceIMEI = scan.nextInt();
					vendita.addSmartphone(app.getSmartphoneDaCodice(codiceIMEI));
					smartphoneVenduti--;
				}
				app.venditaSmartphone(vendita, cliente);
				risp = false;
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
				Dipendente dipendente = app.getDipendenteDaCodice(codiceDipendente);

				System.out.println("Inserire codice fiscale cliente:");
				String codiceCliente = scan.nextLine();
				Cliente cliente = app.getClienteDaCodice(codiceCliente);

				noleggio = new Noleggio(new Date(), prezzo, dipendente,
						new SimpleDateFormat("dd/MM/yyyy").parse(dataFineNoleggio));

				System.out.println("Inserire numero smartphone noleggiati");
				int smartphoneNoleggiati = scan.nextInt();
				while (smartphoneNoleggiati != 0) {
					System.out.println("Inserire codice IMEI smartphone");
					int codiceIMEI = scan.nextInt();
					noleggio.addSmartphone(app.getSmartphoneDaCodice(codiceIMEI));
					smartphoneNoleggiati--;
				}
				app.noleggiaSmartphone(noleggio, cliente);
				risp = false;

			} catch (ClienteException | DipendenteException | AppException e) {
				System.out.println(e.getMessage());
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

	public void elencoSMartphoneMaiNoleggiati(Applicazione app) {
		try {
			PrintWriter writeOnFile = new PrintWriter(
					new BufferedWriter(new FileWriter("smartphone_mai_noleggiati.txt")));
			for (Smartphone smartphone : app.elencoSmartphoneMaiNoleggiati()) {
				writeOnFile.print(smartphone.toString() + '\n');
			}
			writeOnFile.close();
			System.out.println("elenco stampato");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void elencoSmartphoneVendutiDaDipendente(Applicazione app) {
		Scanner scan = new Scanner(System.in);
		boolean risp = true;
		while (risp) {
			try {
				System.out.println("Inserire codice dipendente:");
				String codiceDipendente = scan.nextLine();
				Dipendente dipendente = app.getDipendenteDaCodice(codiceDipendente);
				PrintWriter writeOnFile = new PrintWriter(new BufferedWriter(
						new FileWriter("smartphone_noleggiati_da_dipendente_" + codiceDipendente + ".txt")));
				for (Smartphone smartphone : app.smartphoneVendutiDaDipendente(dipendente)) {
					writeOnFile.print(smartphone.toString() + '\n');
				}
				writeOnFile.close();
				System.out.println("elenco stampato");
				risp = false;
			} catch (IOException e) {
				e.printStackTrace();
			} catch (AppException e) {
				System.out.println("Riprovare?(true/false)");
				risp = scan.nextBoolean();
				e.printStackTrace();
			}
		}
		scan.close();
	}

	public void fineNoleggio(Applicazione app) {
		Scanner scan = new Scanner(System.in);
		System.out.println("scrivere codice noleggio:");
		int codiceNoleggio = scan.nextInt();
		if (!app.fineNoleggio(codiceNoleggio))
			System.out.println("Noleggio finito dopo il tempo stabilito");
		scan.close();
	}

	public void serializzaOggetto(Object obj, String fileSerializzato) {
		FileOutputStream outFile;
		try {
			outFile = new FileOutputStream(currentDirectory + "/" + fileSerializzato);
			ObjectOutputStream outStream = new ObjectOutputStream(outFile);
			outStream.writeObject(obj);
			outFile.close();
			outStream.close();
		} catch (FileNotFoundException e) {
			System.out.println("ERRORE: file non trovato");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Object deserializzaOggetto(String fileSerializzato) {
		Object risultato = null;
		try {
			FileInputStream inFile = new FileInputStream(currentDirectory + "/" + fileSerializzato);
			ObjectInputStream inStream = new ObjectInputStream(inFile);
			risultato = inStream.readObject();
			inFile.close();
			inStream.close();
		} catch (FileNotFoundException e) {
			System.out.println("ERRORE: file non trovato");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return risultato;
	}

}
