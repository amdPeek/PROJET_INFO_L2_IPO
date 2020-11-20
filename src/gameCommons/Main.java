package gameCommons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;


import frog.Frog;
import givenEnvironment.GivenEnvironment;
import graphicalElements.FroggerGraphic;
import graphicalElements.IFroggerGraphics;
import util.Case;
import util.Direction;

public class Main {




	public static void main(String[] args) {

		//Caract�ristiques du jeu
		int width = 26;
		int height = 20;
		int tempo = 100;
		int minSpeedInTimerLoops = 3;
		double defaultDensity = 0.2;

		
		//Cr�ation de l'interface graphique
		IFroggerGraphics graphic = new FroggerGraphic(width, height);
		//Cr�ation de la partie
		Game game = new Game(graphic, width, height, minSpeedInTimerLoops, defaultDensity);
		//Cr�ation et liaison de la grenouille
		if(width % 2 == 0)
		{
			IFrog frog = new Frog(game, Direction.up, new Case(width/2,0));
			game.setFrog(frog);
			graphic.setFrog(frog);
		}
		else
		{
			IFrog frog = new Frog(game, Direction.up, new Case(Math.floorDiv(width,2),0));
			game.setFrog(frog);
			graphic.setFrog(frog);
		}


		//Cr�ation et liaison de l'environnement
		IEnvironment env = new GivenEnvironment(game);

		//Cr�eation et liaison de l�environnement
		//IEnvironment env = new Environment(game);
		game.setEnvironment(env);


		//Boucle principale : l'environnement s'acturalise tous les tempo milisecondes
		Timer timer = new Timer(tempo, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.update();
				graphic.repaint();
				if(game.testWin())
				{
					graphic.endGameScreen("BIEN JOUE");
				}

				if(game.testLose())
				{
					graphic.endGameScreen("PERDU");
				}
			}
		});
		timer.setInitialDelay(0);
		timer.start();
	}
}
