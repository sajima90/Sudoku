package src.metier;

import src.metier.GenererSudoku;
import src.metier.Plateau;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Timer
{
	private Instant debutTemps;
	private Instant finTemps;
	private boolean running;
	private boolean affiche;
	private ScheduledExecutorService horloge;


	public Timer()
	{
		this.running = false;
		this.affiche = false;
		this.horloge = null;
		this.debutTemps = null;
		this.finTemps = null;
	}

	public void start()
	{
		if (!running)
		{
			debutTemps = Instant.now();
			running = true;
//			debutAffichage();
		}
	}

	public void stop()
	{
		if (running)
		{
			finTemps = Instant.now();
			running = false;
			arretAffichage();
		}
	}

	public void reset()
	{
		running = false;
		debutTemps = null;
		finTemps = null;
	}

	public long getElapsedMillis()
	{
		if (running)
		{
			return Duration.between(debutTemps, Instant.now()).toMillis();
		}
		else if (debutTemps != null && finTemps != null)
		{
			return Duration.between(debutTemps, finTemps).toMillis();
		}
		else
		{
			return 0;
		}
	}

	public String getTime()
	{
		long millis = getElapsedMillis();
		long heure = millis / 3600000;
		long minutes = (millis % 3600000) / 60000;
		long seconde = (millis % 60000) / 1000;
		millis = millis % 1000;

		return String.format("%02d:%02d:%02d.%03d", heure, minutes, seconde, millis);
	}

	private void debutAffichage() {
		affiche = true;
		horloge = Executors.newScheduledThreadPool(1);
		horloge.scheduleAtFixedRate(() -> {
			if (affiche) {
				System.out.print("\r" + getTime());
			}
		}, 0, 100, TimeUnit.MILLISECONDS);
	}

	private void arretAffichage() {
		affiche = false;
		if (horloge != null) {
			horloge.shutdown();
		}
		System.out.println();
	}

}
