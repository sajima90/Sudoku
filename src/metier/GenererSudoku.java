package src.metier;

import java.util.*;

public class GenererSudoku extends Plateau
{
	private Random random;

	public GenererSudoku()
	{
		this.random = new Random();
		this.genererAleatoirementSudoku();
	}

	public void genererAleatoirementSudoku()
	{
		// Remplir le sudoku avec des 0
		for (int cpt = 0; cpt < 9; cpt ++)
			for (int cpt2 = 0; cpt2 < 9; cpt2 ++)
				this.setValeur(cpt, cpt2, 0);

		// Placement des chiffres
		remplirSudoku(0, 0);

		// Retirage + difficulté
		int cpt = 0;
		while (cpt < 40)
		{
			int lig = this.random.nextInt(9);
			int col = this.random.nextInt(9);
			if (this.getValeur(lig, col) != 0)
			{
				this.setValeur(lig, col, 0);
				cpt++;
			}
		}
	}

	private boolean remplirSudoku(int lig, int col)
	{
		if (lig == 9)
			return true;

		if (col == 9)
			return remplirSudoku(lig + 1, 0);

		if (this.getValeur(lig, col) != 0)
			return remplirSudoku(lig, col + 1);

		List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
		Collections.shuffle(numbers, this.random);

		for (int num : numbers)
		{
			// verif
			if (verif(lig, col, num, this.getPlateau()))
			{
				// Placer le chiffre
				this.setValeur(lig, col, num);

				// newt
				if (remplirSudoku(lig, col + 1))
					return true;

				// Si != remplir, enlever le chiffre et next
				this.setValeur(lig, col, 0);
			}
		}

		return false;
	}
}