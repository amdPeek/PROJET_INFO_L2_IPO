package environment;

import gameCommons.Game;
import graphicalElements.Element;
import util.Case;

import javax.swing.plaf.ColorUIResource;
import java.util.concurrent.ThreadLocalRandom;

public class winningAreas {
    private Game myGame;
    private Case areaCase;
    private boolean isColorCyan;

    public winningAreas(Game game, int ord)
    {
        this.myGame = game;
        this.areaCase = new Case(ThreadLocalRandom.current().nextInt(0, this.myGame.width + 1),ord);
    }

    public void update()
    {
        if(isColorCyan) {
            this.myGame.getGraphic().add(new Element(this.areaCase.absc, this.areaCase.ord, ColorUIResource.cyan));
            isColorCyan = false;
        }
        else if(!isColorCyan)
        {
            this.myGame.getGraphic().add(new Element(this.areaCase.absc, this.areaCase.ord, ColorUIResource.magenta));
            isColorCyan = true;
        }

    }

    public Case getAreaCase() {
        return areaCase;
    }
}
