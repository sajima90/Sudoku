package src.metier;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.spi.AbstractResourceBundleProvider;

public class Plateau
{

	/*-------------*/
	/*   Donnee    */
	/*-------------*/

	private int[][] plateau;
	private boolean[][] valeursBase;




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
		this.valeursBase = new boolean[9][9];

		try {
			Scanner sc = new Scanner(new File("src/niveaux/n_1.txt"));
			int cptLig = 0, cptCol = 0;
			while(sc.hasNext()){
				String next = sc.next();
				for (char c : next.toCharArray()) {
					plateau[cptLig][cptCol] = Character.getNumericValue(c);
					this.valeursBase[cptLig][cptCol] = plateau[cptLig][cptCol] != 0;
					cptCol++;
					if (cptCol == 9) {
						cptCol = 0;
						cptLig++;
					}
					if (cptLig == 9) {
						break;
					}
				}
			}
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
		try
		{
			if ( this.valeursBase[lig][col] )
			{
				System.out.println("La case est déjà remplie par une valeur de base");
				return;
			}
			if (valeur < 0 || valeur > 9)
			{
				System.out.println("La valeur doit être comprise entre 1 et 9");
				return;
			}
			if (lig < 0 || lig > 8 || col < 0 || col > 8)
			{
				System.out.println("La ligne et la colonne doivent être comprises entre 0 et 8");
				return;
			}
			plateau[lig][col] = valeur;
			System.out.println("Placement de la valeur " + valeur + " à la ligne " + (lig + 1) + " et la colonne " + (col + 1));

		}catch (Exception e){
			System.out.println("Erreur");
		}
	}

	public boolean solution(int [][] sudoku)
	{
		for (int lig = 0; lig < 9; lig ++)
		{
			for (int col = 0; col < 9; col++)
			{
				//si la case est un 0
				if (sudoku[lig][col] == 0)
				{
					for (int val = 1; val <=9; val ++)
					{
						if(verif(lig, col, val, sudoku))
						{
							sudoku[lig][col] = val;
							if (solution(sudoku))
							{
								return true;
							}
							else
							{
								sudoku[lig][col] = 0;

							}
						}
					}

					//aucun chiffre dans cette case
					return false;
				}
			}
		}
		//solution trouve
		return true;
	}



	public static boolean verif(int lig, int col, int val, int[][] sudoku)
	{
		// regarder les lignes et les colonnes
		for (int cpt = 0; cpt < 9; cpt++)
		{
			if (sudoku[lig][cpt] == val || sudoku[cpt][col] == val)
			{
				return false;
			}
		}

		// Faire les boites de 3x3
		int boitesLig = (lig / 3) * 3;
		int boitesCol = (col / 3) * 3;

		for( int cptLig = boitesLig; cptLig < boitesLig + 3; cptLig++)
		{
			for (int cptCol = boitesCol; cptCol < boitesCol + 3; cptCol++ )
			{
				if ( sudoku[cptLig][cptCol] == val)
					return false;
			}
		}

		return true;
	}



	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		for (int cptLig = 0; cptLig < plateau.length; cptLig++){


			for (int cptCol = 0; cptCol< plateau[cptLig].length; cptCol++)
			{
				sb.append((plateau[cptLig][cptCol]));
				if ((cptCol +1 ) % 3 == 0 && cptCol < plateau[cptLig].length - 1 )
				{
					sb.append(" | ");
				} else {
					if (cptCol < plateau[cptLig].length - 1)
						sb.append(" ");
					}
			}
			sb.append("\n");
			if ((cptLig + 1 ) % 3 == 0 && cptLig < plateau.length -1 )
				sb.append("------+-------+------\n");
		}
		return sb.toString();
	}

	public int[][] getPlateau()
	{
		return plateau;
	}
}