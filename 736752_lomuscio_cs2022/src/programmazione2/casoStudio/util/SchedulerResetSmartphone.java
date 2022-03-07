package programmazione2.casoStudio.util;

import java.util.TimerTask;

import programmazione2.casoStudio.application.Applicazione;
import programmazione2.casoStudio.ruoli.Cliente;
import programmazione2.casoStudio.ruoli.Dipendente;

public class SchedulerResetSmartphone extends TimerTask {

	private Applicazione app;

	/**
	 * @param name
	 */
	public SchedulerResetSmartphone(Applicazione app) {
		this.app = app;
	}

	@Override
	public void run() {

		for (Cliente cliente : app.getClienti()) {
			cliente.resetSmartphoneNoleggiati();
		}
		for (Dipendente dipendente : app.getDipendenti()) {
			dipendente.resetSmartphoneNoleggiati();
		}

	}

}
