package frog;

import gameCommons.Game;
import gameCommons.IFrog;
import util.Case;
import util.Direction;

import java.awt.*;

public class Frog implements IFrog {
	
	private Game game;
	private final Color colorFrog;
	private Case frogCase;
	private Direction frogDirection;

	public Frog(Game game,Direction initDirection,Case currentCase)
	{
		this.game = game;
		this.colorFrog = Color.green;
		this.frogDirection = initDirection;
		this.frogCase = currentCase;
	}


	@Override
	public Case getPosition() {
		return null;
	}

	@Override
	public Direction getDirection() {
		return null;
	}

	@Override
	public void move(Direction key) {

	}
}
