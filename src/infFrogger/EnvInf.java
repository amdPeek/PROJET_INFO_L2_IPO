package infFrogger;

import environment.Lane;
import gameCommons.Game;
import gameCommons.IEnvironment;
import util.Case;

import java.util.ArrayList;

public class EnvInf implements IEnvironment {
    private Game game;
    private ArrayList<Lane> roadsToBeShown = new ArrayList<>();
    private ArrayList<Lane> myRoads = new ArrayList<>();

    public EnvInf(Game myGame)
    {
        this.game = myGame;

        //La "lane" de départ
        this.myRoads.add(new Lane(game,0,0));
        this.myRoads.add(new Lane(game,1,0));

        //Maintenant on peut itérer pour ajouter les "lanes" contenant des véhicules
        for(int i = 2; i < game.height ; i++)
        {
            this.myRoads.add(new Lane(game,i));
        }



        this.roadsToBeShown = this.myRoads;
    }

    @Override
    public boolean isSafe(Case c) {
        //Là on va utiliser la fonction se trouvant dans Lane pour savoir si la case c dans sa lane est safe

        if(c.ord == 0 || c.ord == game.height)
            return true;

        Lane tmpLane = ((Lane)this.roadsToBeShown.get(c.ord));
        Boolean bool = tmpLane.isSafe(c);

        if(bool)
        {
            return true;
        }
        else
        {
            return false;
        }

    }

    @Override
    public boolean isWinningPosition(Case c) {

        //là il suffit juste de vérifier si frog.case.ord == game.height - 1
        if(c.ord == this.game.height -1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public void update() {

        int baseOrd = game.sentFrogOrd() - 1;

        if(baseOrd + this.game.getHeight() > this.myRoads.size())
        {
            this.myRoads.add(new Lane(this.game,baseOrd+this.game.height, game.defaultDensity));
            System.out.println("On ajoute une ligne !");
        }


        this.roadsToBeShown = new ArrayList<>();

        for(int i = 0; i < this.game.height; i++)
        {
            this.myRoads.get(i + baseOrd).setOrdLane(i);
            this.roadsToBeShown.add(this.myRoads.get(i+baseOrd));
        }

        for(int i = 0; i < this.roadsToBeShown.size(); i++)
        {
            this.roadsToBeShown.get(i).update();
        }
    }
}
