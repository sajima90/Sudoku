package src.metier;


import java.util.Arrays;
import java.util.Scanner;

public class Plateau
{
	private final int[][] plateau;
	private int[][] solution;
	private final boolean[][] valeursBase;
	private int score;
	private boolean estcaseAvant;
	private int[] caseAvant;

	public Plateau()
	{
		GenererSudoku sudoku = new GenererSudoku();

		this.valeursBase = new boolean[9][9];
		this.solution = new int[9][9];
		this.plateau = sudoku.genererAleatoirementSudoku();
		this.score = 0;
		this.estcaseAvant = false;
		this.caseAvant = new int[2];


		int[][] plateauCopy = new int[9][];
		for (int i = 0; i < 9; i++)
			plateauCopy[i] = Arrays.copyOf(this.plateau[i], 9);

		this.solution = solution(plateauCopy);

		for (int cptLig = 0; cptLig < 9; cptLig++)
			for (int cptCol = 0; cptCol < 9; cptCol++)
				this.valeursBase[cptLig][cptCol] = this.plateau[cptLig][cptCol] != 0;

		System.out.println(afficherTableau(this.plateau));

//				this.startGame();
	}

	public int getValeur(int lig, int col)
	{
		return plateau[lig][col];
	}

	public boolean estPossibleModifierCase(int lig, int col)
	{
		return !valeursBase[lig][col];
	}

	public void setValeur(int lig, int col, int valeur)
	{
		try
		{
			if (!estPossibleModifierCase(lig, col))
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
			this.updateScore(lig, col, valeur);
			plateau[lig][col] = valeur;
			System.out.println(
					"Placement de la valeur " + valeur + " à la ligne " + (lig + 1) + " et la colonne " + (col + 1));
			System.out.println(afficherTableau(this.plateau));

		} catch (Exception e)
		{
			System.out.println("Erreur");
		}
	}

	public int[][] solution(int[][] sudoku)
	{
		for (int lig = 0; lig < 9; lig++)
		{
			for (int col = 0; col < 9; col++)
			{
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
					return null;
				}
			}
		}
		return sudoku;
	}

	public static boolean verif(int lig, int col, int val, int[][] sudoku)
	{
		for (int cpt = 0; cpt < 9; cpt++)
		{
			if (sudoku[lig][cpt] == val || sudoku[cpt][col] == val)
			{
				return false;
			}
		}
		int boitesLig = (lig / 3) * 3;
		int boitesCol = (col / 3) * 3;

		for (int cptLig = boitesLig; cptLig < boitesLig + 3; cptLig++)
		{
			for (int cptCol = boitesCol; cptCol < boitesCol + 3; cptCol++)
			{
				if (sudoku[cptLig][cptCol] == val)
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

		System.out.println("Sudoku | Sajima - Betonfixe");
		System.out.println();
		System.out.println("Pour quitter, entrez 0 0 0");

		while (true)
		{
			if (this.estFini())
			{
				System.out.println("Bravo, vous avez gagné en  secondes");
				System.out.println(
						"-------------------------------------\nSolution : \n" + afficherTableau(solution));
				break;
			}

			System.out.println("Il reste " + (81 - this.nbCasesRemplies()) + " cases à remplir");
			System.out.println();
			System.out.println("----------------------------");
			System.out.println(afficherTableau(this.plateau));
			System.out.println("----------------------------");

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

	private void updateScore(int lig, int col, int valeur)
	{
		int valeurCorrecte = this.solution[lig][col];
		if (valeur == valeurCorrecte)
		{
			if (this.estcaseAvant)
			{
				this.score += 2;
				System.out.println("Bravo, vous avez trouvé la bonne valeur + 2 ");
			}
			else
			{
				this.score+=10;
				System.out.println("Bravo, vous avez trouvé la bonne valeur + 10 ");
			}
			this.estcaseAvant = false;
		}
		else
		{
			if (!estcaseAvant || (caseAvant[0] != lig || caseAvant[1] != col))
			{
				score -= 5;
				System.out.println("Mauvaise valeur - 5 ");
				estcaseAvant = true;
			}
		}
		caseAvant = new int[] {lig, col};
	}
	public boolean estFini()
	{
		if (this.nbCasesRemplies() != 81)
			return false;

		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++)
				if (this.plateau[i][j] != this.solution[i][j])
					return false;

		return true;
	}



	public int getScore()
	{
		return this.score;
	}

	public static String afficherTableau(int[][] plateau)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("    1 2 3 ║ 4 5 6 ║ 7 8 9\n");
		sb.append("  ╔═══════╬═══════╬═══════╗\n");
		for (int cptLig = 0; cptLig < plateau.length; cptLig++)
		{
			sb.append(cptLig + 1);
			sb.append(" ║ ");
			for (int cptCol = 0; cptCol < plateau[cptLig].length; cptCol++)
			{
				sb.append((plateau[cptLig][cptCol]));
				if ((cptCol + 1) % 3 == 0 && cptCol < plateau[cptLig].length - 1)
				{
					sb.append(" | ");
				}
				else
				{
					if (cptCol < plateau[cptLig].length - 1)
						sb.append(" ");
				}
			}
			sb.append(" ║ ");
			sb.append("\n");
			if ((cptLig + 1) % 3 == 0 && cptLig < plateau.length - 1)
				sb.append("  ╠ ───── ╬ ───── ╬ ───── ╣\n");
		}
		sb.append("  ╚═══════╩═══════╩═══════╝\n");
		return sb.toString();
	}
}
