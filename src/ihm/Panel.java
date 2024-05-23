package src.ihm;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Panel extends JPanel implements ActionListener, ItemListener, AdjustmentListener
{

	private JPanel panelHaut;
	private JPanel panelBas;

	private JRadioButton rbValUn;
	private JRadioButton rbValDeux;

	private JCheckBox cbValUn;
	private JCheckBox cbValDeux;

	private JLabel lblTexte;

	private JTextField txtInput;

	private JScrollBar scBar;

	public Panel()
	{

		ButtonGroup btgRadio;

		this.setLayout(new BorderLayout());

		/* ------------------------ */
		/* Création des composants */
		/* ------------------------ */

		this.panelHaut = new JPanel(new GridLayout(3, 2));
		this.panelBas = new JPanel(new BorderLayout());

		this.panelHaut.setBackground(new Color(254, 128, 47));
		this.panelBas.setBackground(new Color(147, 214, 245));

		btgRadio = new ButtonGroup();
		this.rbValUn = new JRadioButton("Valeur 1", true);
		this.rbValUn.setOpaque(false);

		this.rbValDeux = new JRadioButton("Valeur 2");
		this.rbValDeux.setOpaque(false);

		this.cbValUn = new JCheckBox("Case 1");
		this.cbValUn.setOpaque(false);

		this.cbValDeux = new JCheckBox("Case 2");
		this.cbValDeux.setOpaque(false);

		this.lblTexte = new JLabel("Saisie : ", JLabel.RIGHT);
		/* 15 correspond à la taille du TextField en caractère */
		this.txtInput = new JTextField(15);
		this.txtInput.setBackground(Color.GRAY);
		this.txtInput.setEnabled(false);

		this.scBar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 20, 0, 500);

		/* Ajouter les boutons radio à notre groupe */
		btgRadio.add(this.rbValUn);
		btgRadio.add(this.rbValDeux);

		/* ------------------------------ */
		/* Positionnement des composants */
		/* ------------------------------ */

		this.panelHaut.add(this.rbValUn);
		this.panelHaut.add(this.rbValDeux);

		this.panelHaut.add(this.cbValUn);
		this.panelHaut.add(this.cbValDeux);

		this.panelHaut.add(this.lblTexte);
		this.panelHaut.add(this.txtInput);

		this.panelBas.add(this.scBar);

		this.add(this.panelHaut);
		/*
		 * Positionner dans un Border Layout = BorderLayout.NORTH (Haut) | SOUTH
		 * (Bas) | EAST (DROITE) | WEST (GAUCHE) | CENTER (Centre)
		 */
		this.add(this.panelBas, BorderLayout.SOUTH);

		/* -------------------------- */
		/* Activation des composants */
		/* -------------------------- */

		this.rbValUn.addItemListener(this);
		this.rbValDeux.addItemListener(this);

		this.txtInput.addActionListener(this);

		this.scBar.addAdjustmentListener(this);

	}

	public void itemStateChanged(ItemEvent e)
	{

		if (this.rbValUn.isSelected())
		{
			System.out.println("Item un selectioné");
			this.txtInput.setEnabled(false);
			this.txtInput.setBackground(Color.GRAY);
		}
		else if (this.rbValDeux.isSelected())
		{
			System.out.println("Item deux selectionés");
			this.txtInput.setEnabled(true);
			this.txtInput.setBackground(Color.WHITE);
		}

	}

	public void actionPerformed(ActionEvent e)
	{

		if (e.getSource() == this.txtInput)
		{

			System.out.println("Champs : " + this.txtInput.getText());

		}

	}

	public void adjustmentValueChanged(AdjustmentEvent e)
	{

		if (e.getSource() == this.scBar)
		{

			System.out.println("Scrollbar : " + this.scBar.getValue());

		}

	}

}
