package graphicalElements;

import gameCommons.IFrog;

import javax.swing.*;

public interface IFroggerGraphics {
	
	/**
	 * Ajoute l'élément aux éléments à afficher
	 * @param e
	 */
    public void add(Element e);
    
    /**
     * Enlève tous les éléments actuellement affichés
     */
    public void clear();


    public int getScore();
    public void setScore(int score);

    public void animWarning();

    /***
     * Actualise l'affichage
     */
    public void repaint();

    public int getElapsedTime();

    public void setPart4(boolean part4);

    /**
     * Lie la grenouille à l'environneemnt graphique
     * @param frog
     */
    public void setFrog(IFrog frog);
    
    /**
     * Lance un écran de fin de partie
     * @param message le texte à afficher
     */
    public void endGameScreen(String message);
}
