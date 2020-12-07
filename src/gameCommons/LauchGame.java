package gameCommons;

import environment.Environment;
import frog.Frog;
import givenEnvironment.GivenEnvironment;
import graphicalElements.FroggerGraphic;
import graphicalElements.IFroggerGraphics;
import infFrogger.EnvInf;
import infFrogger.FrogInf;
import util.Case;
import util.Direction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LauchGame {

    public boolean part1, part2, part3, part4;

    public LauchGame(boolean partie1, boolean partie2, boolean partie3, boolean partie4)
    {
        //Caractéristiques du jeu
        int width = 50;
        int height = 20;
        int tempo = 100;
        int minSpeedInTimerLoops = 3;
        double defaultDensity = 0.2;

        this.part1 = partie1; // environnement déjà donné
        this.part2 = partie2; // codage de l'environnement
        this.part3 = partie3; // version infini
        this.part4 = partie4; // route d'eau







        //Création de l'interface graphique
        IFroggerGraphics graphic = new FroggerGraphic(width, height);
        //Création de la partie
        Game game = new Game(graphic, width, height, minSpeedInTimerLoops, defaultDensity,this);
        //Création et liaison de la grenouille
        if(width % 2 == 0)
        {
            if(part2 || part1 || part4)
            {
                IFrog frog = new Frog(game, Direction.up, new Case(width/2,0));
                game.setFrog(frog);
                graphic.setFrog(frog);
            }
            else if(part3)
            {
                IFrog frog = new FrogInf(game,new Case(width/2,1));
                game.setFrog(frog);
                graphic.setFrog(frog);
            }


        }
        else
        {
            if(part2 || part1 || part4)
            {
                IFrog frog = new Frog(game, Direction.up, new Case(Math.floorDiv(width,2),0));
                game.setFrog(frog);
                graphic.setFrog(frog);
            }
            else if(part3)
            {
                IFrog frog = new FrogInf(game,new Case(Math.floorDiv(width,2),1));
                game.setFrog(frog);
                graphic.setFrog(frog);
            }



        }


        //Création et liaison de l'environnement
        if(part1)
        {
            IEnvironment env = new GivenEnvironment(game);
            game.setEnvironment(env);
        }
        else if(part2)
        {
            //Cr´eation et liaison de l’environnement
            IEnvironment env = new Environment(game,this);
            game.setEnvironment(env);
        }
        else if(part3)
        {
            IEnvironment env = new EnvInf(game,this);
            game.setEnvironment(env);
        }
        else if(part4)
        {
            //Cr´eation et liaison de l’environnement
            IEnvironment env = new Environment(game,this);
            game.setEnvironment(env);
            graphic.setPart4(true);
        }






        //Boucle principale : l'environnement s'acturalise tous les tempo milisecondes
        Timer timer = new Timer(tempo, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.update();
                graphic.repaint();
				/*if(game.testWin())
				{
					graphic.endGameScreen("BIEN JOUE");
				}*/

				/*if(game.testLose())
				{
					graphic.endGameScreen("PERDU");
				}*/

            }
        });
        timer.setInitialDelay(0);
        timer.start();
    }

}
