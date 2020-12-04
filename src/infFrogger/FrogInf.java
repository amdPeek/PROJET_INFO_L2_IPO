package infFrogger;

import frog.Frog;
import gameCommons.Game;
import gameCommons.IFrog;
import gameCommons.Main;
import util.Case;
import util.Direction;

public class FrogInf implements IFrog{

    private Game game;
    private Case frogCase;
    private int frogScore = 0;
    //ici on introduit l'ordonnée de la grenouille à part pour modifier le score plus facilement
    private int ordFrog;
    private Case toFollow;
    private boolean isOnWood = false;
    private boolean lftToRiIfOnWood;


    public FrogInf (Game myGame, Case initCase)
    {
        this.game = myGame;
        this.frogCase = initCase;
        this.ordFrog = 1; //Dans la démo on part de y = 1

    }

    public IFrog getFrog()
    {
        return new FrogInf(this.game,new Case(0,0));
    }

    @Override
    public int getOrd() {
        return ordFrog;
    }

    public void setToFollow(Case toFollow) {
        this.toFollow = toFollow;
    }

    public boolean isLftToRiIfOnWood() {
        return lftToRiIfOnWood;
    }

    @Override
    public boolean isOnWood() {
        return this.isOnWood;
    }

    @Override
    public int getScore() {
        return this.frogScore;
    }

    @Override
    public Case getPosition() {
        return this.frogCase;
    }

    public void setOnWood(boolean onWood) {
        isOnWood = onWood;
    }

    @Override
    public Direction getDirection() {
        return null;
    }

    @Override
    public void move(Direction key) {
        //là on va utiliser un switch c'est plus souple que le if else des parties 1 et 2 !
        switch(key)
        {
            case up:

                this.ordFrog++;
                this.frogScore = this.ordFrog - 1;
                break;
            case down:
                if(this.ordFrog > 1 )   //Pour éviter d'avoir un score négatif
                {
                    this.ordFrog--;

                }

                break;
            case right:
                if(this.frogCase.absc < game.width)
                    this.frogCase = new Case(this.frogCase.absc + 1, this.frogCase.ord);
                break;
            case left:
                if(this.frogCase.absc > 0)
                    this.frogCase = new Case(this.frogCase.absc - 1, this.frogCase.ord);
                break;
        }

        System.out.println("x = " + this.frogCase.absc + " y = " + this.frogScore );
    }

    @Override
    public void update() {
        return;
    }
}
