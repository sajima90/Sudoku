package src;
import src.ihm.FrameJeu;
import src.ihm.FrameVictoire;
import src.metier.Plateau;
import src.metier.Timer;

public class Controleur
{
	private Plateau       plateau;
	private FrameJeu      frameJeu;
	private FrameVictoire frameVictoire;

	Timer timer    = new Timer();

	public Controleur()
	{
		this.plateau  = new Plateau();
		this.frameJeu = new FrameJeu(this);
	}

	public int[][] getPlateau()
	{
		return this.plateau.getPlateau();
	}

	public boolean estPossibleModifierCase(int lig, int col)
	{
		return this.plateau.estPossibleModifierCase(lig, col);
	}

	public void setValeur(int lig, int col, int valeur)
	{
		this.plateau.setValeur(lig, col, valeur);
		this.frameJeu.majIHM();
	}

	public boolean estFinie()
	{
		return this.plateau.estFini();
	}

	public void FrameVictoire()
	{
		this.frameVictoire = new FrameVictoire(this);
	}

	public void startTimer()
	{
		this.timer.start();
	}

	public void stopTimer()
	{
		this.timer.stop();
	}

	public String getTime()
	{
		return this.timer.getTime();
	}

	public int getScore()
	{
		return this.plateau.getScore();
	}
	public static void main(String[] args)
	{
		new Controleur();
	}

}
