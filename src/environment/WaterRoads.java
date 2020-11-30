package environment;

import gameCommons.Game;

import java.util.ArrayList;

public class WaterRoads {

    private Game game;
    private int ord;
    private int speed;
    private boolean leftToRight;
    private ArrayList<Wood> woods;

    public WaterRoads (Game myGame, int myOrd, int mySpeed, boolean lToR)
    {
        this.game = myGame;
        this.ord = myOrd;
        this.speed = mySpeed;
        this.leftToRight = lToR;

        //On ajoute des wood et on les updates
        for(Wood tmpWood : this.woods)
        {
            tmpWood.moveWood();

        }
    }

}
