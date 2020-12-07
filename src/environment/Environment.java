package environment;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import gameCommons.LauchGame;
import util.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;

import java.util.concurrent.ThreadLocalRandom;

public class Environment implements IEnvironment {
		
	//attributs
    private Game myGame;
    private ArrayList<Lane> myRoads;
    private ArrayList<WaterRoads> myWaterRoads;
    private ArrayList<winningAreas> winningPos;
    public int ordOfCurrentLane;
    public Case woodToFollow;
    public LauchGame lauchG;


    public Case getWoodToFollow() {
        return woodToFollow;
    }

    public LauchGame getLauchG() {
        return lauchG;
    }



    public Environment(Game game, LauchGame lG)
    {
        this.myGame = game;
        this.myRoads = new ArrayList();
        this.myWaterRoads = new ArrayList<>();
        this.winningPos = new ArrayList<>();
        this.lauchG = lG;



        //Maintenant on doit créer height - 2 lane et les stocker dans this.myRoads

        //Mais il ne faut pas oublier qu'il existe deux "lane" avec 0 voitures donc une densité nulle par conséquent aussi, cela correspond à la route de départ et d'arrivée

        //La "lane" de départ
        this.myRoads.add(new Lane(game,0,0));


        //Maintenant on peut itérer pour ajouter les "lanes" contenant des véhicules
        for(int i = 1; i < game.getHeight() - 2; i++)
        {

            if(this.lauchG.part4)
            {
                if(game.randomGen.nextBoolean() == true)
                    this.myRoads.add(new Lane(game,i));
                else
                    this.myWaterRoads.add(new WaterRoads(game,i,0,true));
            }
            else
            {
                this.myRoads.add(new Lane(game,i));
            }


        }


        //maintenant la "lane" d'arrivée
        this.myRoads.add(new Lane(game,game.height - 1,0));


        if(this.lauchG.part4)
        {
            ArrayList<Integer> tmpAuthorizedOrd = new ArrayList();
            ArrayList<Integer> usedOrd = new ArrayList();

            for(Lane tmpRoad : this.myRoads)
            {
                tmpAuthorizedOrd.add(tmpRoad.getOrd());
            }

            for(int i = 0; i < 4; i++)
            {
                usedOrd.add(tmpAuthorizedOrd.get(ThreadLocalRandom.current().nextInt(0,  tmpAuthorizedOrd.size()+ 1)));
            }

            for(int tmpI : usedOrd)
            {
                this.winningPos.add(new winningAreas(this.myGame,tmpI));
            }

        }


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
    public ArrayList<winningAreas> getWinningPos() {
        return this.winningPos;
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


        if(this.lauchG.part4)
        {
            for(WaterRoads wR : this.myWaterRoads)
            {
                wR.update();
            }
        }

        if(this.lauchG.part4)
        {
            for(winningAreas wP : this.winningPos)
            {
                wP.update();
            }
        }

    }
}
