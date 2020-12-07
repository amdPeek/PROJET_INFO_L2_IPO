package graphicalElements;

import gameCommons.IFrog;

import javax.swing.*;

public interface IFroggerGraphics {
	
	/**
	 * Ajoute l'�l�ment aux �l�ments � afficher
	 * @param e
	 */
    public void add(Element e);
    
    /**
     * Enl�ve tous les �l�ments actuellement affich�s
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
     * Lie la grenouille � l'environneemnt graphique
     * @param frog
     */
    public void setFrog(IFrog frog);
    
    /**
     * Lance un �cran de fin de partie
     * @param message le texte � afficher
     */
    public void endGameScreen(String message);
}
