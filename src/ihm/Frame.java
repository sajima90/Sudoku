package src.ihm;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame
{
	public Frame()
	{

		this.setTitle("Sudoku");
		this.setSize(500, 500);
		
		this.setLocation(400, 400);

		this.setLayout(new BorderLayout());

		/* Ajouter notre panel à la Frame */
		this.add( new Panel());

		this.setVisible(true);


		/* Fermer la fenêtre */
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}


	public static void main(String[] args)
	{
		new Frame();
	}
}
