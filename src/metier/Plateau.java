package src.metier;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Plateau
{

	/*-------------*/
	/*   Donnee    */
	/*-------------*/

	private int[][] plateau;




	/*-------------*/
	/* Instruction */
	/*-------------*/


	public Plateau()
	{
		this.plateau = initPlateau();
	}



	public int[][] initPlateau()
	{
		int[][] plateau = new int[9][9];

		try {
			Scanner sc = new Scanner(new File("Sudoku/class/src/niveaux/n_1.txt"));
			for (int cptLig = 0; cptLig < 9; cptLig++)
				for (int cptCol = 0; cptCol < 9; cptCol++)
					if (sc.hasNextLine())
						plateau[cptLig][cptCol] = sc.nextInt();

			sc.close();
		}catch (FileNotFoundException e){
			System.out.println("Fichier non trouvé");
		}


		return plateau;
	}

	public int getValeur(int lig, int col)
	{
		return plateau[lig][col];
	}

	public void setValeur(int lig, int col, int valeur)
	{
		plateau[lig][col] = valeur;
	}


	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		for (int cptLig = 0; cptLig < plateau.length; cptLig++){


			for (int cptCol = 0; cptCol< plateau[cptLig].length; cptCol++)
			{
				sb.append((plateau[cptLig][cptCol]));
				if (cptCol % 3 == 0 && cptCol < plateau[cptLig].length - 1 )
				{
					sb.append(" | ");
				} else {
					if (cptCol < plateau[cptLig].length - 1)
						sb.append(" ");
					}
			}
			sb.append("\n");
			if ((cptLig + 1 ) % 3 == 0 && cptLig < plateau.length -1 )
				sb.append("----+----+----\n");
		}
		return sb.toString();
	}

}