package com.sudestwebdesign.steeve.shifumi;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Standalone extends JFrame {
	// Private static attributes

	private static final long serialVersionUID = -2511865820937760685L;
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;

	// Constructor

	public Standalone(IA ia) {
		setLayout(new BorderLayout());
		Shifumi shifumi = new Shifumi(ia);
		add(shifumi, BorderLayout.CENTER);

		setTitle("Papier - Pierre - Ciseaux");
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	// Public static methods

	public static void main(String[] args) {
		int gestesMax = 0;
		IA ia = null;
		switch (args.length) {
			case 2:
				gestesMax = Integer.parseInt(args[1]);
			case 1:
				if (args[0].toLowerCase().equals("expert")) {
					ia = (gestesMax > 0) ? new IAComplexe(gestesMax)
							: new IAComplexe();
				}
				else if (args[0].toLowerCase().equals("novice")) {
					ia = (gestesMax > 0) ? new IASimple(gestesMax)
							: new IASimple();
				}
				else {
					ia = new IAComplexe();
				}
			break;

			default:
				ia = new IAComplexe();
		}
		new Standalone(ia);
	}
}
