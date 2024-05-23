package src.metier;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.spi.AbstractResourceBundleProvider;

public class Plateau
{
	private int    [][] plateau;
	private int    [][] solution;
	private boolean[][] valeursBase;

	public Plateau()
	{
		GenererSudoku sudoku = new GenererSudoku();

		this.valeursBase = new boolean[9][9];
		this.solution = new int[9][9];
		this.plateau = sudoku.genererAleatoirementSudoku();
		
		int[][] plateauCopy = new int[9][];
		for (int i = 0; i < 9; i++)		
			plateauCopy[i] = Arrays.copyOf(this.plateau[i], 9);
	
		this.solution = solution(plateauCopy);

		for (int cptLig = 0; cptLig < 9; cptLig++)
			for (int cptCol = 0; cptCol < 9; cptCol++)
				this.valeursBase[cptLig][cptCol] = this.plateau[cptLig][cptCol] != 0;

		this.startGame();

		/* Lecture fichier
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

		*/

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

	public int[][] solution(int[][] sudoku)
	{
		for (int lig = 0; lig < 9; lig++)
		{
			for (int col = 0; col < 9; col++)
			{
				// si la case est un 0
				if (sudoku[lig][col] == 0)
				{
					for (int val = 1; val <= 9; val++)
					{
						if (verif(lig, col, val, sudoku))
						{
							sudoku[lig][col] = val;
							if (solution(sudoku) != null)
							{
								return sudoku;
							}
							else
							{
								sudoku[lig][col] = 0;
							}
						}
					}

					// aucun chiffre dans cette case
					return null;
				}
			}
		}
		// solution trouve
		return sudoku;
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

	public int[][] getPlateau()
	{
		return this.plateau;
	}


	public void startGame()
	{
		Scanner sc = new Scanner(System.in);
		Timer timer = new Timer();
		new Thread(timer).start();
		System.out.println("Sudoku | Sajima - Betonfixe");
		System.out.println();
		System.out.println("Pour quitter, entrez 0 0 0");


		while (true)
		{
			if (this.estFini())
			{
				timer.stop();
				System.out.println("Bravo, vous avez gagné en " + timer.getSeconds() + " secondes");
				
				System.out.println("-------------------------------------\nSolution : \n" + this.afficherTableau(solution));
				break;
			}
			
			System.out.println("Il reste " + (81 - this.nbCasesRemplies()) + " cases à remplir");
			System.out.println();
			System.out.println("----------------------------");
			System.out.println(this.afficherTableau(this.plateau));
			System.out.println("----------------------------");
			System.out.println("Temps écoulé : " + timer.getSeconds() + " secondes");
			System.out.print("Entrez la ligne   :  ");
            int lig = sc.nextInt();
			System.out.print("Entrez la colonne :  ");
            int col = sc.nextInt();
			System.out.print("Entrez la valeur  :  ");
            int valeur = sc.nextInt();
			if (lig == 0 && col == 0 && valeur == 0)
			{
                break;
            }
			this.setValeur(lig - 1, col - 1, valeur);
		}
		sc.close();
		System.out.println();


	}

	// Renvoit les nombres de cases 
	private int nbCasesRemplies()
	{
		int cpt = 0;
        for (int i = 0; i < 9; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                if (this.plateau[i][j] != 0)
                {
                    cpt++;
                }
            }
        }
        return cpt;
	}

	// Vérifie si le sudoku est fini
	private boolean estFini()
	{
	
		if (this.nbCasesRemplies() != 81)
			return false;

		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++)
				if (this.plateau[i][j] != this.solution[i][j])
					return false;
		
		return true;
	}



	public String afficherTableau(int[][] plateau)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("    1 2 3 ║ 4 5 6 ║ 7 8 9\n");
		sb.append("  ╔═══════╬═══════╬═══════╗\n");
		for (int cptLig = 0; cptLig < plateau.length; cptLig++){
			sb.append(cptLig + 1);
			sb.append(" ║ ");
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
			sb.append(" ║ ");

			sb.append("\n");
			if ((cptLig + 1 ) % 3 == 0 && cptLig < plateau.length -1 )
				sb.append("  ╠ ───── ╬ ───── ╬ ───── ╣\n");
		}
		sb.append("  ╚═══════╩═══════╩═══════╝\n");
		return sb.toString();
	}


}