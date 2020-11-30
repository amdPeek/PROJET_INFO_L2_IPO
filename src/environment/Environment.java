package environment;

import java.util.ArrayList;

import util.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;

public class Environment implements IEnvironment {
		
	//attributs
    private Game myGame;
    private ArrayList<Lane> myRoads;

    public Environment(Game game)
    {
        this.myGame = game;
        this.myRoads = new ArrayList();

        //Maintenant on doit créer height - 2 lane et les stocker dans this.myRoads

        //Mais il ne faut pas oublier qu'il existe deux "lane" avec 0 voitures donc une densité nulle par conséquent aussi, cela correspond à la route de départ et d'arrivée

        //La "lane" de départ
        this.myRoads.add(new Lane(game,0,0));

        //Maintenant on peut itérer pour ajouter les "lanes" contenant des véhicules
        for(int i = 1; i < game.height - 1; i++)
        {
            this.myRoads.add(new Lane(game,i));
        }

        //maintenant la "lane" d'arrivée
        this.myRoads.add(new Lane(game,game.height - 1,0));





    }

    @Override
    public boolean isSafe(Case c) {
        //Là on va utiliser la fonction se trouvant dans Lane pour savoir si la case c dans sa lane est safe

        Lane tmpLane = ((Lane)this.myRoads.get(c.ord));
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
        if(c.ord == this.myGame.height -1)
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
        //Pour update l'environnement il faut update les "lanes" qui elles aussi à leur tour vont update leurs "cars"

        for(Lane tmpLane : this.myRoads)
        {
            tmpLane.update();
        }

    }
}
