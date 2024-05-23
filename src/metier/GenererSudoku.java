package src.metier;

import java.util.*;

public class GenererSudoku
{
	private int[][] sudoku;
	private Random random;

	public GenererSudoku()
	{
		this.sudoku = new int[9][9];
		this.random = new Random();
		this.genererAleatoirementSudoku();
	}

	public int[][] genererAleatoirementSudoku()
	{
		// Remplir le sudoku avec des 0
		for (int cpt = 0; cpt < 9; cpt ++)
			for (int cpt2 = 0; cpt2 < 9; cpt2 ++)
				this.sudoku[cpt][cpt2] = 0;

		// Placement des chiffres
		remplirSudoku(0, 0);

		// Retirage + difficulté
		int cpt = 0;
		while (cpt < 20)
		{
			int lig = this.random.nextInt(9);
			int col = this.random.nextInt(9);
			if (this.sudoku[lig][col] != 0)
			{
				this.sudoku[lig][col] = 0;
				cpt++;
			}
		}

		return this.sudoku;


	}

	private boolean remplirSudoku(int lig, int col)
	{
		if (lig == 9)
			return true;

		if (col == 9)
			return remplirSudoku(lig + 1, 0);

		if (this.sudoku[lig][col] != 0)
			return remplirSudoku(lig, col + 1);

		List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
		Collections.shuffle(numbers, this.random);

		for (int num : numbers)
		{
			// verif
			if (Plateau.verif(lig, col, num, this.sudoku))
			{
				// Placer le chiffre
				this.sudoku[lig][col] = num;

				// newt
				if (remplirSudoku(lig, col + 1))
					return true;

				// Si != remplir, enlever le chiffre et next
				this.sudoku[lig][col] = 0;
			}
		}

		return false;
	}
}