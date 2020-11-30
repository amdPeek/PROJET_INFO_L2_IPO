package environment;

import java.awt.Color;

import util.Case;
import gameCommons.Game;
import graphicalElements.Element;

public class Car {
	private Game game;
	private Case leftPosition;
	private boolean leftToRight;
	private int length;
	private final Color colorLtR = Color.BLACK;
	private final Color colorRtL = Color.RED;

	//TODO Constructeur(s)

	public Car(Game myGame, Case position, boolean leftToRight)
	{
		this.game = myGame;
		this.leftToRight = leftToRight;
		this.length = this.game.randomGen.nextInt(3) + 1;



		//Pour déterminer la "position" (les coordonnees de la case à gauche en fait) on utilise le booleen leftoright
		if(leftToRight) {
			this.leftPosition = new Case(position.absc - this.length, position.ord);
		}
		else{
			this.leftPosition = new Case(position.absc,position.ord);
		}


	}
	
	//TODO : ajout de methodes
	/*	Méthode moveCar qui bouge la voiture
	* @param test, un booleen pour voir si on peut bouger la voiture
	*  si le timer (celui qui boucle toute les milliseconde) est inférieur à la vitesse de la "lane" associé alors on ne fait rien (rien a eu le temps de bouger)
	* par contre si le timer dépasser la vitesse (i.e une opération élementaire a pu être effectuée) alors on bouge la voiture de nouveau
    */
	public void moveCar(boolean stepIsOver)
	{
		if(stepIsOver)
		{
			//ici l'opération précedente a été effectuée. On bouge en changeant la case gauche (car le positionnement de car se fait en réference avec leftPosition)
			//là encore il faut prendre en compte le sens de la "lane" donc de "car" aussi par conséquent

			if(leftToRight)
			{
				this.leftPosition = new Case(this.leftPosition.absc +  1 , this.leftPosition.ord);
			}
			else
			{
				this.leftPosition = new Case(this.leftPosition.absc -  1 , this.leftPosition.ord);
			}



		}

		//Maintenant il faut pas oublier de l'ajouter au graphisme  car la méthode update de Game efface tout dans le timer
		addToGraphics();
	}

	/*	Méthode pour éviter des "cars" inutiles dans l'arrayList de chaque Lane
	*
	*
	*
	 */
	public boolean isAtBorders()
	{
		return this.leftPosition.absc + this.length > 0 || this.leftPosition.absc < this.game.width;

	}

	public boolean isOnCaseOrnot(Case c)
	{

			return c.absc >= this.leftPosition.absc && c.absc < this.leftPosition.absc + this.length;

	}

	
	/* Fourni : addToGraphics() permettant d'ajouter un element graphique correspondant a la voiture*/
	private void addToGraphics() {
		for (int i = 0; i < length; i++) {
			Color color;
			if (this.leftToRight){
				color = colorLtR;
			}
			else
			{
				color = colorRtL;
			}



			game.getGraphic()
					.add(new Element(leftPosition.absc + i, leftPosition.ord, color));
		}
	}

	public void setY(int givenOrd)
	{
		leftPosition = new Case(leftPosition.absc,givenOrd);
	}
}
