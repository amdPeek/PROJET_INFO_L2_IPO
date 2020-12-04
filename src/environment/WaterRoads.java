package environment;

import gameCommons.Game;
import graphicalElements.Element;
import util.Case;

import java.awt.*;
import java.util.ArrayList;

public class WaterRoads {

    private Game game;
    private int ord;
    private int speed;
    private boolean leftToRight;
    private ArrayList<Wood> woods = new ArrayList<>();
    private double density;
    private int tempo;

    private String type = "riviere";



    public WaterRoads (Game myGame, int myOrd, int mySpeed, boolean lToR)
    {
        this.game = myGame;
        this.ord = myOrd;
        this.speed = game.randomGen.nextInt(game.minSpeedInTimerLoops) + 1;
        this.leftToRight = lToR;
        this.density = myGame.defaultDensity;


        //Maintenant on peut ajouter nos wood sur chaque waterRoads
        for(int i = 0; i < 1; i++) {
            this.moveWoodsLane(true);
            this.mayAddWood();
        }


        this.update();

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
            this.moveWoodsLane(false);

        } else {
            this.moveWoodsLane(true);
            this.mayAddWood();
            this.tempo = 0;
            //Faut checker si la frog est sur cette même waterRoads. Sinon on effectue +1 autant de fois qu'il y a de WR
            if(this.game.getFrog().getPosition().ord == this.ord)
                this.game.getFrog().update();
        }



        drawWater();

    }

    private void drawWater()
    {
        //Il faut obtenir les cases où il ne faut pas dessiner
        ArrayList<Integer> forbiddenAsb = new ArrayList<>();

        for(Wood tmpWood : this.woods)
        {
            for(int i = 0; i < tmpWood.getLength(); i++)
            {
                forbiddenAsb.add(tmpWood.getLeftPos().absc + i);
            }
        }



        for(int i = 0; i < this.game.width; i++)
        {
            if(!forbiddenAsb.contains(i))
            {
                this.game.getGraphic().add(new Element(i, this.ord, Color.BLUE));
            }
        }



    }

    private void moveWoodsLane(boolean stepIsOver)
    {
        for(Wood wd : this.woods)
        {
            wd.moveWood(stepIsOver);
        }


    }

    private void mayAddWood() {
        if (isSafe(getFirstCase()) && isSafe(getBeforeFirstCase())) {
            if (game.randomGen.nextDouble() < density) {
                this.woods.add(new Wood(this.game,getBeforeFirstCase(),this.leftToRight));
            }
        }
    }

    public boolean isSafe(Case caseEnQuestion) {

        for(Wood tmpWood : this.woods)
        {
            if(this.leftToRight)
            {
                for(int i = 0; i < tmpWood.getLength(); i++)
                {
                    if(caseEnQuestion.absc == (tmpWood.getLeftPos().absc + i))
                    {
                        return false;
                    }
                }
            }
            else
            {
                for(int i = 0; i < tmpWood.getLength(); i++)
                {
                    if(caseEnQuestion.absc == (tmpWood.getLeftPos().absc - i))
                    {
                        return false;
                    }
                }
            }

        }
        return true;

    }

    private Case getFirstCase() {
        if (leftToRight) {
            return new Case(0, this.ord);
        } else
            return new Case(game.width - 1, this.ord);
    }

    private Case getBeforeFirstCase() {
        if (leftToRight) {
            return new Case(-1, this.ord);
        } else
            return new Case(game.width, this.ord);
    }

}
