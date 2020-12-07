package gameCommons;

import java.awt.Color;
import java.util.Random;

import environment.winningAreas;
import frog.Frog;
import graphicalElements.Element;
import graphicalElements.IFroggerGraphics;
import infFrogger.EnvInf;
import util.Case;

public class Game {

	public final Random randomGen = new Random();

	// Caracteristique de la partie
	public final int width;
	public final int height;
	public final int minSpeedInTimerLoops;
	public final double defaultDensity;

	// Lien aux objets utilisés
	private IEnvironment environment;
	private IFrog frog;
	private IFroggerGraphics graphic;

	private boolean gameIsOver = false;

	private LauchGame lg;


	public IEnvironment getEnvironment() {
		return this.environment;
	}

	/**
	 * 
	 * @param graphic
	 *            l'interface graphique
	 * @param width
	 *            largeur en cases
	 * @param height
	 *            hauteur en cases
	 * @param minSpeedInTimerLoop
	 *            Vitesse minimale, en nombre de tour de timer avant déplacement
	 * @param defaultDensity
	 *            densite de voiture utilisee par defaut pour les routes
	 */
	public Game(IFroggerGraphics graphic, int width, int height, int minSpeedInTimerLoop, double defaultDensity, LauchGame lauch) {
		super();
		this.graphic = graphic;
		this.width = width;
		this.height = height;
		this.minSpeedInTimerLoops = minSpeedInTimerLoop;
		this.defaultDensity = defaultDensity;
		this.lg = lauch;

	}

	public IFrog getFrog() {
		return frog;
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	/**
	 * Lie l'objet frog à la partie
	 * 
	 * @param frog
	 */
	public void setFrog(IFrog frog) {
		this.frog = frog;
	}

	/**
	 * Lie l'objet environment a la partie
	 * 
	 * @param environment
	 */
	public void setEnvironment(IEnvironment environment) {
		this.environment = environment;
	}

	/**
	 * 
	 * @return l'interface graphique
	 */
	public IFroggerGraphics getGraphic() {
		return graphic;
	}

	/**
	 * Teste si la partie est perdue et lance un écran de fin approprié si tel
	 * est le cas
	 * 
	 * @return true si le partie est perdue
	 */
	public boolean testLose() {
		// TODO
		if(!environment.isSafe(this.frog.getPosition()) || (this.getFrog().getPosition().absc >= this.width) || (this.getFrog().getPosition().absc < 0) || (this.getGraphic().getElapsedTime() == 30 && this.lg.part4))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Teste si la partie est gagnee et lance un écran de fin approprié si tel
	 * est le cas
	 * 
	 * @return true si la partie est gagnée
	 */
	public boolean testWin() {
		// TODO
		if(environment.isWinningPosition(this.frog.getPosition()))
		{
			return true;
		}
		else
		{
			return false;
		}

	}

	public String currentTypeRoad(Case c)
	{
		return this.getEnvironment().currentTypeRoad(c);
	}

	/**
	 * Actualise l'environnement, affiche la grenouille et verifie la fin de
	 * partie.
	 */
	public void update() {


		graphic.clear();
		environment.update();
		this.graphic.add(new Element(frog.getPosition(), Color.GREEN));

		if(this.lg.part4)
		{
			for(winningAreas wA : this.getEnvironment().getWinningPos())
			{
				if( (this.frog.getPosition().absc == wA.getAreaCase().absc) && (this.frog.getPosition().ord == wA.getAreaCase().ord) )
				{
					//La grenouille est sur une case point bonus
					this.getGraphic().setScore(this.getGraphic().getScore() + 15);
					System.out.println("CASE BONUS ! + 15 points bravo !");
					//this.getGraphic().animWarning();
				}
			}
		}



		if(testLose())
			if(environment.getClass() == EnvInf.class)
				graphic.endGameScreen("PERDU \n Ton score: " + frog.getScore());
			else
			{
				graphic.endGameScreen("PERDU ! Ton score : " + getGraphic().getScore());
			}


			if(testWin())
				graphic.endGameScreen("GAGNE");



	}

	public int sentFrogOrd()
	{
		return frog.getOrd();
	}

}
