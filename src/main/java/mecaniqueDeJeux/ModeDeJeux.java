package main.java.mecaniqueDeJeux;

import org.apache.log4j.Level;

import java.util.Scanner;

/**
 * Classe de jeux permettant de choisir les différents modes de jeux :
 * Joueur vs AI
 * AI VS Joueur
 * Duel (chacun son tour)
 *
 * @author Maximilien Le Boiteux
 * @version 1.0
 */
public class ModeDeJeux extends LogClass {

    /**
     * Variable de type String organisant le type de jeu
     */
    private String TypedeJeu = "";

    /**
     * Méthode organisant le mode de jeux avant de lancer la partie de jeu
     */
    public ModeDeJeux() {
        log.log(Level.INFO, "Ouverture classe");
        String Choix = "0";
        this.TypedeJeu = "";
        Scanner choixjeu = new Scanner(System.in);
        while (true) {
            System.out.println("--------------Choisissez votre jeux----------------");
            while (!Choix.equals("M") && !Choix.equals("R") && !Choix.equals("Q")) {
                System.out.println("Voulez-vous jouer au MasterMind (M), à RecherchePlusMoins (R) ou Quitter le jeu (Q) ?");
                Choix = choixjeu.nextLine();
            }
            this.TypedeJeu = Choix;
            if (Choix.equals("Q")) System.exit(0);
            System.out.println("------------Choisissez le mode de jeux-------------");
            while (!Choix.equals("Du") && !Choix.equals("De") && !Choix.equals("Ch")) {
                System.out.println("Voulez-vous jouer en mode Duel(Du), Défenseur (De), Challenger (Ch) ?");
                Choix = choixjeu.nextLine();
            }
            if (this.TypedeJeu.equals("R")) {
                new RecherchePlusMoins(Choix);
            } else if (this.TypedeJeu.equals("M")) {
                new MasterMind(Choix);
            }
        }
    }
}
