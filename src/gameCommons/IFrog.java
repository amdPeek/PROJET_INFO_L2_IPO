package gameCommons;

import util.Case;
import util.Direction;

public interface IFrog {
	
	/**
	 * Donne la position actuelle de la grenouille
	 * @return
	 */
	public Case getPosition();
	
	/**
	 * Donne la direction de la grenouille, c'est à dire de son dernier mouvement 
	 * @return
	 */
	public Direction getDirection();

	public IFrog getFrog();

	public void setOnWood(boolean onWood);

	/**
	 * Déplace la grenouille dans la direction donnée et teste la fin de partie
	 * @param key
	 */
	public void move(Direction key);

	public boolean isLftToRiIfOnWood();

	public boolean isOnWood();

	public void update();

	public void setToFollow(Case toFollow);

	public int getScore();

	public int getOrd();

}
