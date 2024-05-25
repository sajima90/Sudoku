package src.ihm;

import src.Controleur;

import javax.swing.*;
import java.awt.*;

public class FrameVictoire extends JFrame
{
	private PanelVictoire panelVictoire;

	public FrameVictoire( Controleur ctrl)
	{

		this.panelVictoire = new PanelVictoire( ctrl );


		this.setTitle("Sudoku");
		this.setSize(1173 , 1170);

		this.setLocation(200, 400);

		this.setLayout(new BorderLayout());

		/* Ajouter notre panel à la Frame */
		this.add(this.panelVictoire);

		this.setVisible(true);


		/* Fermer la fenêtre */
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
