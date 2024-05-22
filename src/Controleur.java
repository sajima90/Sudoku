package src;
import src.metier.Plateau;

public class Controleur
{
	private Plateau plateau;

	public Controleur()
	{
		this.plateau = new Plateau();
		System.out.println("---------------------------- Résolution --------------------------------");

		System.out.println(plateau.toString());
//		plateau.setValeur(0,5, 8);
//		plateau.setValeur(0,4, 8);
		System.out.println(plateau.toString());
		System.out.println(plateau.solution(plateau.getPlateau()));
		System.out.println("Sudoku Fini ! \n" + plateau.toString());

	}

	public static void main(String[] args)
	{
		new Controleur();
	}

}
