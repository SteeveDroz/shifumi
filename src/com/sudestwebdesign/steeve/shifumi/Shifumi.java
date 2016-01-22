package com.sudestwebdesign.steeve.shifumi;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Shifumi extends JPanel implements ActionListener
{
	// Private static attributes

	private static final long serialVersionUID = 5250355092810713756L;

	private static final Color GAIN = new Color(0, 255, 0);
	private static final Color NEUTRE = new Color(200, 200, 255);
	private static final Color PERTE = new Color(255, 0, 0);

	private static final String PAPIER = "/resources/papier.png";
	private static final String PIERRE = "/resources/pierre.png";
	private static final String CISEAUX = "/resources/ciseaux.png";

	// Private attributes

	private IA ia;
	private JButton papier, pierre, ciseaux;
	private JLabel choixJoueur, choixIA;
	private JLabel pointsJoueur, pointsNuls, pointsIA;
	private ImageIcon imagePapier, imagePierre, imageCiseaux;

	// Constructors

	public Shifumi(IA ia)
	{
		this.ia = ia;
		this.imagePapier = new ImageIcon(getClass().getResource(PAPIER));
		this.imagePierre = new ImageIcon(getClass().getResource(PIERRE));
		this.imageCiseaux = new ImageIcon(getClass().getResource(CISEAUX));
		createComponents();
	}

	// Public methods

	@Override
	public void actionPerformed(ActionEvent event)
	{
		Geste gesteIA = ia.calculerGeste();
		Geste gesteJoueur;

		JButton bouton = (JButton) event.getSource();
		if (bouton == papier)
		{
			gesteJoueur = Geste.PAPIER;
		}
		else if (bouton == pierre)
		{
			gesteJoueur = Geste.PIERRE;
		}
		else if (bouton == ciseaux)
		{
			gesteJoueur = Geste.CISEAUX;
		}
		else
		{
			return;
		}

		traiterGestes(gesteJoueur, gesteIA);
	}

	// Private methods

	private void traiterGestes(Geste gesteJoueur, Geste gesteIA)
	{
		switch (gesteJoueur)
		{
			case PAPIER:
				choixJoueur.setIcon(imagePapier);
			break;
			case PIERRE:
				choixJoueur.setIcon(imagePierre);
			break;
			case CISEAUX:
				choixJoueur.setIcon(imageCiseaux);
			break;
			default:
		}
		switch (gesteIA)
		{
			case PAPIER:
				choixIA.setIcon(imagePapier);
			break;
			case PIERRE:
				choixIA.setIcon(imagePierre);
			break;
			case CISEAUX:
				choixIA.setIcon(imageCiseaux);
			break;
			default:
		}
		if (bat(gesteJoueur, gesteIA))
		{
			pointsJoueur.setText(""
					+ (Integer.parseInt(pointsJoueur.getText()) + 1));
			choixJoueur.setBackground(GAIN);
			choixIA.setBackground(PERTE);
		}
		else if (bat(gesteIA, gesteJoueur))
		{
			pointsIA.setText("" + (Integer.parseInt(pointsIA.getText()) + 1));
			choixJoueur.setBackground(PERTE);
			choixIA.setBackground(GAIN);
		}
		else
		{
			pointsNuls.setText(""
					+ (Integer.parseInt(pointsNuls.getText()) + 1));
			choixJoueur.setBackground(NEUTRE);
			choixIA.setBackground(NEUTRE);
		}

		ia.ajouterGeste(gesteJoueur);
	}

	private boolean bat(Geste g1, Geste g2)
	{
		if ((g1 == Geste.PAPIER && g2 == Geste.PIERRE)
				|| (g1 == Geste.PIERRE && g2 == Geste.CISEAUX)
				|| (g1 == Geste.CISEAUX && g2 == Geste.PAPIER))
		{
			return true;
		}
		return false;
	}

	private void createComponents()
	{
		setLayout(new GridLayout(2, 1));

		JPanel choix = new JPanel(new GridLayout(1, 3));

		papier = new JButton();
		pierre = new JButton();
		ciseaux = new JButton();

		papier.setIcon(imagePapier);
		pierre.setIcon(imagePierre);
		ciseaux.setIcon(imageCiseaux);

		choixJoueur = new JLabel();
		choixIA = new JLabel();

		choixJoueur.setHorizontalAlignment(SwingConstants.CENTER);
		choixIA.setHorizontalAlignment(SwingConstants.CENTER);

		pointsJoueur = new JLabel("0");
		pointsNuls = new JLabel("0");
		pointsIA = new JLabel("0");

		pointsJoueur.setHorizontalAlignment(SwingConstants.CENTER);
		pointsNuls.setHorizontalAlignment(SwingConstants.CENTER);
		pointsIA.setHorizontalAlignment(SwingConstants.CENTER);

		papier.addActionListener(this);
		pierre.addActionListener(this);
		ciseaux.addActionListener(this);

		choixJoueur.setOpaque(true);
		choixIA.setOpaque(true);
		choixJoueur.setBackground(NEUTRE);
		choixIA.setBackground(NEUTRE);

		choix.add(papier);
		choix.add(pierre);
		choix.add(ciseaux);

		JPanel resultat = new JPanel(new GridLayout(1, 3));

		JPanel joueur = new JPanel(new BorderLayout());
		JLabel choixJoueurTitre = new JLabel("Votre choix");
		choixJoueurTitre.setHorizontalAlignment(SwingConstants.CENTER);
		joueur.add(choixJoueurTitre, BorderLayout.NORTH);
		joueur.add(choixJoueur, BorderLayout.CENTER);

		JPanel ia = new JPanel(new BorderLayout());
		JLabel choixIATitre = new JLabel("Choix de l'IA");
		choixIATitre.setHorizontalAlignment(SwingConstants.CENTER);
		ia.add(choixIATitre, BorderLayout.NORTH);
		ia.add(choixIA, BorderLayout.CENTER);

		JPanel points = new JPanel(new GridLayout(3, 2));

		JLabel gain = new JLabel("Parties gagnées:");
		gain.setHorizontalAlignment(SwingConstants.RIGHT);
		points.add(gain);
		points.add(pointsJoueur);

		JLabel nul = new JLabel("Parties nulles:");
		nul.setHorizontalAlignment(SwingConstants.RIGHT);
		points.add(nul);
		points.add(pointsNuls);

		JLabel perte = new JLabel("Parties perdues:");
		perte.setHorizontalAlignment(SwingConstants.RIGHT);
		points.add(perte);
		points.add(pointsIA);

		resultat.add(joueur);
		resultat.add(points);
		resultat.add(ia);

		add(choix);
		add(resultat);
	}
}
