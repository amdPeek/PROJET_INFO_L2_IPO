package frog;

import gameCommons.Game;
import gameCommons.IFrog;
import gameCommons.Main;
import util.Case;
import util.Direction;

import java.awt.*;

public class Frog implements IFrog {

	private Game game;
	private Case frogCase;
	private Direction frogDirection;

	public Frog(Game game,Direction initDirection,Case currentCase)
	{
		this.game = game;
		this.frogDirection = initDirection;
		this.frogCase = currentCase;
	}


	@Override
	public Case getPosition() {
		return this.frogCase;
	}

	@Override
	public Direction getDirection() {
		return this.frogDirection;
	}

	@Override
	public void move(Direction key) {

		if((key == Direction.down) && (this.frogCase.ord - 1 >= 0))
		{
			this.frogCase = new Case(this.frogCase.absc ,this.frogCase.ord - 1);
			System.out.println("x = " + this.frogCase.absc + " y = " + this.frogCase.ord);
		}
		else if((key == Direction.up) && (this.frogCase.ord + 1 < this.game.height))
		{
			this.frogCase = new Case(this.frogCase.absc ,this.frogCase.ord + 1);
			System.out.println("x = " + this.frogCase.absc + " y = " + this.frogCase.ord);
		}
		else if((key == Direction.left) && (this.frogCase.absc - 1 >= 0))
		{
			this.frogCase = new Case(this.frogCase.absc - 1,this.frogCase.ord);
			System.out.println("x = " + this.frogCase.absc + " y = " + this.frogCase.ord);
		}
		else if((key == Direction.right) && (this.frogCase.absc + 1 < this.game.width))
		{
			this.frogCase = new Case(this.frogCase.absc + 1 ,this.frogCase.ord);
			System.out.println("x = " + this.frogCase.absc + " y = " + this.frogCase.ord);
		}

	}
}
