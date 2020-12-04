package environment;

import gameCommons.Game;
import graphicalElements.Element;
import util.Case;

import java.awt.*;

public class Wood {

    private Game game;
    private int length;
    private Case leftPosition;
    private boolean leftToRight;
    private Color woodColor = new Color(102,51,0);;

    public Wood(Game myGame,Case position ,boolean lToR)
    {
        this.game = myGame;
        this.length = this.game.randomGen.nextInt(5) + 1; //les tailles des bois peuvent aller de 1 à 4
        this.leftToRight = lToR;

        //Pour déterminer la "position" (les coordonnees de la case à gauche en fait) on utilise le booleen leftoright
        if(leftToRight) {
            this.leftPosition = new Case(position.absc - this.length, position.ord);
        }
        else{
            this.leftPosition = new Case(position.absc,position.ord);
        }
    }

    public Case getLeftPos()
    {
        return this.leftPosition;
    }

    public int getLength() {
        return this.length;
    }

    public void moveWood(boolean stepIsOver)
    {

        if(stepIsOver)
        {
            if(this.leftToRight)
                this.leftPosition = new Case(this.leftPosition.absc + 1,this.leftPosition.ord);
            else
                this.leftPosition = new Case(this.leftPosition.absc - 1,this.leftPosition.ord);
        }



        addToGraphics();
    }

    public void addToGraphics()
    {
        for (int i = 0; i < this.length; i++)
        {
            game.getGraphic().add(new Element(this.leftPosition.absc + i, this.leftPosition.ord, this.woodColor));
      }
    }

    public boolean isOnCaseOrnot(Case c)
    {
        return c.absc >= this.leftPosition.absc && c.absc < this.leftPosition.absc + this.length;

    }

    //Pour le mode infini
    public void setY(int givenOrd)
    {
        this.leftPosition = new Case(this.leftPosition.absc,givenOrd);
    }

}

