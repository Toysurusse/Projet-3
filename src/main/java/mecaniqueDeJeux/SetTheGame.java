package main.java.mecaniqueDeJeux;

import main.java.Configuration;
import org.apache.log4j.Level;

import java.util.Scanner;

/**
 * Class to set parameters of the game :
 * Player vs AI
 * AI VS Player
 * Duel (both play)
 *
 * @author Maximilien Le Boiteux
 * @version 1.0
 */
public class SetTheGame extends Configuration {

    /**
     * String who register the kind of game
     */
    private String TypedeJeu = "";

    /**
     * Scanner to register player choices
     */
    private Scanner choixjeu = new Scanner(System.in);

    /**
     * String private to let replay or not the game
     */
    private String replay = "N";

    /**
     * Méthod to choice the game mode
     */
    public SetTheGame() {
        boolean dev=false;
        String Choix = "0";
        this.TypedeJeu = "";
        while (replay.equals("N")) {
            System.out.println("--------------Choisissez votre jeux----------------");
            while (!Choix.equals("M") && !Choix.equals("R") && !Choix.equals("Q")) {
                System.out.println("Voulez-vous jouer au MasterMind (M), à SearchCode (R) ou Quitter le jeu (Q) ?");
                Choix = choixjeu.nextLine();
                if (Choix.equals("Dev")) {
                    dev=modeDev();
                }
            }
            this.TypedeJeu = Choix;
            if (Choix.equals("Q")) System.exit(0);
            System.out.println("------------Choisissez le mode de jeux-------------");
            while (!Choix.equals("Du") && !Choix.equals("De") && !Choix.equals("Ch")) {
                System.out.println("Voulez-vous jouer en mode Duel(Du), Défenseur (De), Challenger (Ch) ?");
                Choix = choixjeu.nextLine();
            }
            replay = "O";
            while (replay.equals("O")) {
                if (this.TypedeJeu.equals("R")) {
                    new SearchCode(Choix,dev);
                } else if (this.TypedeJeu.equals("M")) {
                    new MasterMind(Choix,dev);
                }
                replay();
            }
        }
    }

    /**
     * Méthod to replay or set the starting menu
     */
    protected void replay() {
        log.log(Level.INFO, "INIT Méthode permettant de rejouer ou non");
        replay = "";
        while (!replay.equals("O") && !replay.equals("N") && !replay.equals("Q")) {
            System.out.println("Voulez vous rejouer (O), ou pas (N) ? Vous pouvez également quitter le jeu (Q)");
            replay = this.choixjeu.nextLine();
        }
    }
}
