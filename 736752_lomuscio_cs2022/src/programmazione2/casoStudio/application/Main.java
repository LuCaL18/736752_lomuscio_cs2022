/**
 * 
 */
package programmazione2.casoStudio.application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import programmazione2.casoStudio.application.menu.MainMenu;

/**
 * La classe Main contiene il metodo main(String[] args) e due attributi
 * statici: la directory dei file e lo stream di input stdin.
 * 
 * @author Luca Lomuscio
 *
 */
public class Main {

	/**
	 * Valore che indica la directory dei file
	 */
	public static String currentDirectory = "file/";
	/**
	 * valore che indica lo stream di input
	 */
	private static BufferedReader stdin;

	/**
	 * Metodo main che istanzia l'oggetto applicazione e avvalora l'attributo stdin
	 * con il valore dello stream dello standard input. Rappresenta il punto di
	 * accesso al menu principale dell'applicazione.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		Applicazione app = Applicazione.getInstance();

		try {
			stdin = new BufferedReader(new InputStreamReader(System.in));

			MainMenu menu = new MainMenu(stdin);
			menu.menu();

			stdin.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		app.close();
	}

}
