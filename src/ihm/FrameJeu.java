package src.ihm;

import src.Controleur;

import javax.swing.*;
import java.awt.*;

public class FrameJeu extends JFrame
{
	public Frame()
	{

		this.panelJeu = new PanelJeu( ctrl, this );


		this.setTitle("Sudoku");
		this.setSize(1400, 1400);
		
		this.setLocation(200, 400);

		this.setLayout(new BorderLayout());

		/* Ajouter notre panel à la Frame */
		this.add(this.panelJeu);

		this.setVisible(true);


		/* Fermer la fenêtre */
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void majIHM()
	{
		this.panelJeu.majIHM();
	}


}
