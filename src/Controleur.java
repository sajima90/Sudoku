package src;
import src.metier.Plateau;

public class Controleur
{
	private Plateau plateau;

	public Controleur()
	{
		this.plateau = new Plateau();
	}

	public static void main(String[] a)
	{
		new Controleur();
	}

}
