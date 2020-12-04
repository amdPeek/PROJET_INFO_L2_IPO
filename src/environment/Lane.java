package environment;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import graphicalElements.Element;
import util.Case;
import gameCommons.Game;

public class Lane {
	private Game game;
	private int ord;
	private int speed;
	private ArrayList<Car> cars = new ArrayList<>();
	private boolean leftToRight;
	private double density;
	private int tempo;
	private boolean laneWaterType = false;
	private String type = "route";

	// TODO : Constructeur(s)
	public Lane(Game myGame, int myOrd, double myDensity)
	{


		this.game = myGame;
		this.ord = myOrd;
		this.density = myDensity;
		this.leftToRight = game.randomGen.nextBoolean();
		this.speed = game.randomGen.nextInt(game.minSpeedInTimerLoops) + 1;



		//Maintenant on peut ajouter nos car sur chaque lane
		for(int i = 0; i < 24 * game.width; i++) {
			this.moveCarsLane(true);
			this.mayAddCar();
		}

		update();

	}

	//surcharge car dans le constructeur de Environment on n'a pas à préciser la densité
	public Lane(Game myGame, int myOrd)
	{
		this.game = myGame;
		this.ord = myOrd;
		this.density = myGame.defaultDensity;
		this.leftToRight = game.randomGen.nextBoolean();
		this.speed = game.randomGen.nextInt(game.minSpeedInTimerLoops) + 1 ;

		//Maintenant on peut ajouter nos car sur chaque lane
		for(int i = 0; i < 24 * game.width; i++) {
			this.moveCarsLane(true);
			this.mayAddCar();
		}

		update();
	}

	public int getOrd() {
		return this.ord;
	}

	public String getType() {
		return this.type;
	}


	public void update() {



			++this.tempo;
			if (this.tempo <= this.speed) {
				this.moveCarsLane(false);
			} else {
				this.moveCarsLane(true);
				this.mayAddCar();
				this.tempo = 0;
			}

	}

	private void moveCarsLane(boolean stepIsOver)
	{
		for(Car tmpCar : this.cars)
		{
			tmpCar.moveCar(stepIsOver);
		}

		//Il faut surtout pas oublier d'enlever les cars dans l'ancienne ArrayList
		deleteOldCars();
	}

	private void deleteOldCars()
	{
		ArrayList<Car> whichCarToBeRemoved = new ArrayList();

		for(Car tmpCar : this.cars)
		{
			if(!tmpCar.isAtBorders())
			{
				whichCarToBeRemoved.add(tmpCar);
			}
		}

		//Maintenant on les supprime

		for(Car carToDelete : whichCarToBeRemoved)
		{
			this.cars.remove(carToDelete);
		}
	}

	public boolean getLeftToRight()
	{
		return this.leftToRight;
	}

	// TODO : ajout de methodes

	/*
	 * Fourni : mayAddCar(), getFirstCase() et getBeforeFirstCase() 
	 */

	/**
	 * Ajoute une voiture au début de la voie avec probabilité égale à la
	 * densité, si la première case de la voie est vide
	 */
	private void mayAddCar() {
		if (isSafe(getFirstCase()) && isSafe(getBeforeFirstCase())) {
			if (game.randomGen.nextDouble() < density) {
				cars.add(new Car(game, getBeforeFirstCase(), leftToRight));
			}
		}
	}

	public boolean isSafe(Case caseEnQuestion) {

		for(Car tmpCar : this.cars)
		{
			if(tmpCar.isOnCaseOrnot(caseEnQuestion))
			{
				return false;
			}
		}
		return true;

	}

	private Case getFirstCase() {
		if (leftToRight) {
			return new Case(0, ord);
		} else
			return new Case(game.width - 1, ord);
	}

	private Case getBeforeFirstCase() {
		if (leftToRight) {
			return new Case(-1, ord);
		} else
			return new Case(game.width, ord);
	}

	public void setOrdLane(int givenOrg)
	{
		this.ord = givenOrg;
		for(Car tmpCar : this.cars)
		{
			tmpCar.setY(givenOrg);
		}
	}

}
