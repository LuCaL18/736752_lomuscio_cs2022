package programmazione2.casoStudio.application.menu;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * La classe astratta AbstractMenu rappresenta la base per le classi concrete
 * che rappresentano un tipo di menu. Ha un solo attributo cioè lo stream di
 * input.
 * 
 * @author Luca Lomuscio
 *
 */
abstract class AbstractMenu {

	/**
	 * valore che indica lo stream di input
	 */
	protected BufferedReader stdin;

	/**
	 * Costruttore della classe AbstractMenu che permette di istanziare una classe
	 * concreta di un tipo di menu con i suoi attributi generici.
	 * 
	 * @param bufferReader stream di input
	 */
	public AbstractMenu(BufferedReader bufferReader) {
		stdin = bufferReader;
	}

	/**
	 * Metodo che gestisce il menu, astratto che deve essere implementato dalle
	 * classi concrete derivate. Solleva l'eccezione IOException se c'è un errore
	 * allo stream di input.
	 * 
	 * @throws IOException
	 */
	public abstract void menu() throws IOException;

	/**
	 * Metodo che restituisce la stringa con i valori del menu.
	 * 
	 * @return stringa con i valori del menu
	 */
	public abstract String listaMenu();

}
