package src;
import src.ihm.FrameJeu;
import src.ihm.FrameVictoire;
import src.metier.Plateau;

public class Controleur
{
	private Plateau       plateau;
	private FrameJeu      frameJeu;
	private FrameVictoire frameVictoire;

	Timer timer    = new Timer();

	public Controleur()
	{
		this.plateau = new Plateau();
		//this.frame   = new Frame  ();
	}

	public static void main(String[] args)
	{
		new Controleur();
	}

}
