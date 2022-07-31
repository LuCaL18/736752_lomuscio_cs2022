package programmazione2.casoStudio.util;

import java.util.Set;
import java.util.TimerTask;

import programmazione2.casoStudio.application.Applicazione;
import programmazione2.casoStudio.application.Main;
import programmazione2.casoStudio.ruoli.Cliente;
import programmazione2.casoStudio.ruoli.Dipendente;

/**
 * La classe SchedulerResetSmartphone estende la classe TimerTask. Si occupa di
 * resettare il numero di smartphone noleggiati da clienti e dipendenti ogni 24
 * ore.
 * 
 * @author Luca Lomuscio
 *
 */
public class SchedulerResetSmartphone extends TimerTask {

	/**
	 * valore che indica l'applicazione
	 */
	private Applicazione app;

	/**
	 * Il Costruttore della classe SchedulerResetSmartphone permette di istaziare un
	 * oggetto di tipo SchedulerResetSmartphone con il suo unico attributo app.
	 * 
	 * @param app
	 */
	public SchedulerResetSmartphone(Applicazione app) {
		this.app = app;
	}

	/**
	 * Metodo che rappresenta l'azione svolta dal task SchedulerResetSmartphone dopo
	 * un intervallo di tempo. Resetta il numero di smartphone noleggiati da ogni
	 * cliente e dipendente dell'app. Serializza le liste aggiornate clienti e
	 * dipendenti rispettivamente sui file clienti.ser e dipendenti.ser
	 */
	@Override
	public void run() {

		Set<Cliente> clienti = app.getClienti();
		Set<Dipendente> dipendenti = app.getDipendenti();

		for (Cliente cliente : clienti) {
			cliente.resetSmartphoneNoleggiati();
		}
		for (Dipendente dipendente : dipendenti) {
			dipendente.resetSmartphoneNoleggiati();
		}

		GestoreFile.serializzaOggetto(clienti, Main.currentDirectory + "clienti.ser");
		GestoreFile.serializzaOggetto(dipendenti, Main.currentDirectory + "dipendenti.ser");

	}

}
