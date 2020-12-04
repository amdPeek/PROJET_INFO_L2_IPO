package environment;

import java.util.ArrayList;

import util.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;

public class Environment implements IEnvironment {
		
	//attributs
    private Game myGame;
    private ArrayList<Lane> myRoads;
    private ArrayList<WaterRoads> myWaterRoads;
    public int ordOfCurrentLane;
    public Case woodToFollow;

    public Case getWoodToFollow() {
        return woodToFollow;
    }

    public Environment(Game game)
    {
        this.myGame = game;
        this.myRoads = new ArrayList();
        this.myWaterRoads = new ArrayList<>();

        //Maintenant on doit créer height - 2 lane et les stocker dans this.myRoads

        //Mais il ne faut pas oublier qu'il existe deux "lane" avec 0 voitures donc une densité nulle par conséquent aussi, cela correspond à la route de départ et d'arrivée

        //La "lane" de départ
        this.myRoads.add(new Lane(game,0,0));


        //Maintenant on peut itérer pour ajouter les "lanes" contenant des véhicules
        for(int i = 1; i < game.getHeight() - 2; i++)
        {
            if(game.randomGen.nextBoolean() == true)
                this.myRoads.add(new Lane(game,i));
            else
                this.myWaterRoads.add(new WaterRoads(game,i,0,true));
        }


        //maintenant la "lane" d'arrivée
        this.myRoads.add(new Lane(game,game.height - 1,0));


    }

    @Override
    public String currentTypeRoad(Case c)
    {

        String toReturn = "";

        for(Lane tmpRoad : this.myRoads)
        {
            if(tmpRoad.getOrd() == c.ord)
            {
                toReturn = tmpRoad.getType();
                this.ordOfCurrentLane = tmpRoad.getOrd();
            }
        }

        for(WaterRoads tmpWaterRoad : this.myWaterRoads)
        {
            if(tmpWaterRoad.getOrd() == c.ord)
            {
                toReturn = tmpWaterRoad.getType();
                this.ordOfCurrentLane = tmpWaterRoad.getOrd();
            }
        }

        return toReturn;

    }






    @Override
    public boolean isSafe(Case c) {
        //Là on va utiliser la fonction se trouvant dans Lane pour savoir si la case c dans sa lane est safe

        if(this.currentTypeRoad(c).equals("route"))
        {
            //Faut regarder la WR qui correspond à l'ordonnée de Frog
            Lane myCurrentLane = new Lane(this.myGame,100,0.00);

            for(Lane tmpLane : this.myRoads)
            {
                if(tmpLane.getOrd() == c.ord)
                {
                    //System.out.println("L'ord de la current lane est : " + tmpWR.getOrd());
                    myCurrentLane = tmpLane;
                }
            }

            Boolean bool = myCurrentLane.isSafe(c);

            if(bool) {
                //System.out.println("Sur la route");
                this.myGame.getFrog().setOnWood(false);
                return true;
            }
            else
            {
                //System.out.println("Sur une voiture");
                return false;
            }
        }
        else if(this.currentTypeRoad(c).equals("riviere"))
        {
            //Pour les water roads maintenant
            if(this.myWaterRoads.size() != 0)
            {

                //Faut regarder la WR qui correspond à l'ordonnée de Frog
                WaterRoads myCurrentWr = new WaterRoads(this.myGame,this.myGame.height + 10,0,true);

                for(WaterRoads tmpWR : this.myWaterRoads)
                {
                    if(tmpWR.getOrd() == c.ord)
                    {
                        //System.out.println("L'ord de la current lane est : " + tmpWR.getOrd());
                        myCurrentWr = tmpWR;
                    }
                }

                Boolean boolWR = myCurrentWr.isSafe(c);

                if(boolWR) {
                    //System.out.println("Dans l'eau");
                    return false;
                }
                else
                {
                    //System.out.println("Sur du bois");
                    woodToFollow = c;
                    this.myGame.getFrog().setToFollow(c);
                    this.myGame.getFrog().setOnWood(true);
                    return true;
                }

            }

        }

        return true;

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

        for(WaterRoads wR : this.myWaterRoads)
        {
            wR.update();
        }

    }
}
