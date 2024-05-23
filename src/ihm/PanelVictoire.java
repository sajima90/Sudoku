package src.ihm;

import src.Controleur;

import javax.swing.*;

import java.awt.*;

public class PanelVictoire extends JPanel
{
	private Controleur ctrl;

	private JPanel panelTemps;
	private JPanel panelScore;


	private	JLabel texteTemps;
	private	JLabel texteScore;



	public PanelVictoire(Controleur ctrl)
	{

		this.setLayout(new BorderLayout());

		/* ------------------------ */
		/* Création des composants  */
		/* ------------------------ */

		this.ctrl = ctrl;

		this.panelTemps = new JPanel(new BorderLayout());
		this.panelScore = new JPanel(new BorderLayout());


		this.texteTemps = new JLabel("Bravo vous avez fini le Sudoku en : " + ctrl.getTime() + " secondes");
		this.texteScore = new JLabel(String.valueOf("Votre score est de : "  + ctrl.getScore()));


		/* ------------------------------ */
		/* Positionnement des composants  */
		/* ------------------------------ */

		this.panelTemps.add(texteTemps, BorderLayout.CENTER);
		this.panelScore.add(texteScore, BorderLayout.CENTER);


		this.add(this.panelTemps, BorderLayout.EAST);
		this.add(this.panelScore, BorderLayout.WEST);


	}

}

