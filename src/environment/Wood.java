package environment;

import gameCommons.Game;
import util.Case;

public class Wood {

    private Game game;
    private int lenght;
    private Case woodPos;
    private boolean leftToRight;

    public Wood(Game myGame, boolean lToR)
    {
        this.game = myGame;
        this.lenght = this.game.randomGen.nextInt(3) + 1; //les tailles des bois peuvent aller de 1 à 4
        this.leftToRight = lToR;
    }

    public void moveWood()
    {
        if(this.leftToRight)
            this.woodPos = new Case(this.woodPos.absc + 1,this.woodPos.ord);
        else
            this.woodPos = new Case(this.woodPos.absc - 1,this.woodPos.ord);
    }

    public void addToGraphics()
    {

    }

}
