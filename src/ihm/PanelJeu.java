package src.ihm;

import src.Controleur;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelJeu extends JPanel  implements ActionListener
{

	private Controleur ctrl;
	private Frame      frame;

	private int[][] plateau;

	private JPanel panelJeu;
	private JPanel panelSelection;
	private JPanel panelGauche;
	private JPanel panelDroite;
	private JPanel panelHaut;

	private JButton[][] tabBoutonjeu;
	private JButton[]   tabPioche;

	private int valeurChoisi;

	// panel haut
	private JLabel timerLabel;
	private JLabel scoreLabel;
	private int score;
	private boolean previousMistake;
	private int timeElapsed;


	public PanelJeu(Controleur ctrl, FrameJeu frame)
	{

		this.setLayout(new BorderLayout());

		/* ------------------------ */
		/* Création des composants */
		/* ------------------------ */

		this.ctrl  = ctrl;
		this.frame = frame;
		this.ctrl.startTimer();

		this.plateau = ctrl.getPlateau();

		this.tabBoutonjeu = new JButton[9][9];
		this.tabPioche    = new JButton[9];

		this.valeurChoisi = -1;


		this.panelJeu       = new JPanel(new GridLayout(3, 3));
		this.panelSelection = new JPanel(new FlowLayout());
		this.panelGauche	= new JPanel(new GridLayout(3, 3));
		this.panelGauche.setPreferredSize(new Dimension(100, 100));

		this.panelDroite	= new JPanel(new GridLayout(3, 3));
		this.panelDroite.setPreferredSize(new Dimension(100, 100));

		this.panelHaut	= new JPanel(new GridLayout(3, 3));
		this.panelHaut.setPreferredSize(new Dimension(100, 100));

		this.timerLabel = new JLabel("Temps: 0");
		this.scoreLabel = new JLabel("Score: 0");
		this.panelHaut.add(timerLabel);
		this.panelHaut.add(scoreLabel);

		new javax.swing.Timer(50,e -> {
			timerLabel.setText("Time: "  + ctrl.getTime());
			scoreLabel.setText("Score: " + ctrl.getScore());
		}).start();

		// Création des boutons
		for (int blockLig = 0; blockLig < 3; blockLig++)
		{
			for (int blockCol = 0; blockCol < 3; blockCol++)
			{
				JPanel block = new JPanel(new GridLayout(3, 3));
				block.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
				for (int celluleLig = 0; celluleLig < 3; celluleLig++)
				{
					for (int celluleCol = 0; celluleCol < 3; celluleCol++)
					{
						int lig = blockLig * 3 + celluleLig;
						int col = blockCol * 3 + celluleCol;
						if (this.plateau[lig][col] != 0)
						{
							this.tabBoutonjeu[lig][col] = new JButton(
									new ImageIcon("../images/" + this.plateau[lig][col] + ".png"));
						}
						else
						{
							this.tabBoutonjeu[lig][col] = new JButton();
						}
						this.tabBoutonjeu[lig][col].setContentAreaFilled(false);



						block.add(this.tabBoutonjeu[lig][col]);
					}
				}
				this.panelJeu.add(block);
			}
		}


		// Création des boutons de pioche
		for (int cpt = 1; cpt <= this.tabPioche.length; cpt++){
			this.tabPioche[cpt - 1] = new JButton(new ImageIcon("../images/" + cpt + ".png"));
			this.tabPioche[cpt - 1].setContentAreaFilled(false);
//			this.tabPioche[cpt - 1].setBorderPainted(false);
		}

		/* ------------------------------ */
		/* Positionnement des composants  */
		/* ------------------------------ */

		for (int cpt = 1; cpt <= this.tabPioche.length; cpt++)
		{
			this.panelSelection.add(this.tabPioche[cpt-1]);
		}

		this.add(this.panelJeu, BorderLayout.CENTER);
		this.add(this.panelSelection, BorderLayout.SOUTH);
		this.add(this.panelGauche, BorderLayout.WEST);
		this.add(this.panelDroite, BorderLayout.EAST);
		this.add(this.panelHaut, BorderLayout.NORTH);

		/* -------------------------- */
		/* Activation des composants  */
		/* -------------------------- */

		// Plateau
		for (int lig = 0; lig < this.tabBoutonjeu.length; lig++)
		{
			for (int col = 0; col < this.tabBoutonjeu.length; col++)
			{
				this.tabBoutonjeu[lig][col].addActionListener(this);
			}
		}

		// Pioche
		for (int cpt = 1; cpt <= this.tabPioche.length; cpt++)
		{
			this.tabPioche[cpt-1].addActionListener(this);
			this.tabPioche[cpt - 1].setBorder(BorderFactory.createEmptyBorder());
		}
	}


	public void actionPerformed(ActionEvent e)
	{

		if (valeurChoisi != -1)
			for( int lig = 0; lig < this.tabBoutonjeu.length; lig ++)
			{
				for (int col = 0; col < this.tabBoutonjeu.length; col++)
				{
					if (e.getSource() == this.tabBoutonjeu[lig][col])
					{
						if (ctrl.estPossibleModifierCase(lig, col))
						{
							this.ctrl.setValeur(lig, col, valeurChoisi);
						}

					}
				}
			}


		for(int cpt = 1; cpt <= this.tabPioche.length; cpt++)
		{

			if(e.getSource() == this.tabPioche[cpt - 1])
			{
				if ( valeurChoisi != -1 )
					this.tabPioche[valeurChoisi-1].setBorder(BorderFactory.createEmptyBorder());

				valeurChoisi = cpt;

				this.tabPioche[cpt - 1].setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
			}
		}

	}


	public void majIHM()
	{
		timerLabel.setText("Time: " + ctrl.getTime());
		for ( int lig=0; lig< this.tabBoutonjeu.length; lig++)
			for ( int col=0; col< this.tabBoutonjeu[lig].length; col++)
			{
				if (this.plateau[lig][col] != 0)
				{
					Icon img = new ImageIcon("../images/" + this.plateau[lig][col] + ".png");

					this.tabBoutonjeu[lig][col].setIcon(img);
				}
			}

		if ( ctrl.estFinie() )
		{
			this.ctrl.stopTimer();
			this.ctrl.FrameVictoire();
			this.frame.dispose();
		}
	}



}
