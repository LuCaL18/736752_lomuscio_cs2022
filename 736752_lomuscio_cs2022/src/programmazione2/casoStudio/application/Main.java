/**
 * 
 */
package programmazione2.casoStudio.application;

import java.util.Scanner;

/**
 * @author lucal
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Applicazione app = new Applicazione();
		Scanner scan = new Scanner(System.in);
		int scelta = 0;

		System.out.println("-------------------------\n" + "BENVENUTI\n" + "-------------------------");

		while (scelta != 6) {
			app.printMenu();
			System.out.print("scelta: ");

			scelta = scan.nextInt();

			switch (scelta) {
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			default:
				System.out.println("nessuna operazione effettuata");
			}
		}

		System.out.println("ARRIVEDERCI");
		scan.close();

	}

}
