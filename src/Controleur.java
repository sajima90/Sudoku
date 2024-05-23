package src;
import src.ihm.Frame;
import src.metier.Plateau;

public class Controleur
{
	private Plateau plateau;
	private Frame   frame;


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
