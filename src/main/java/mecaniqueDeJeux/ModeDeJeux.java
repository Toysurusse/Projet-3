package main.java.mecaniqueDeJeux;

import main.java.Configuration;

import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Classe de jeux permettant de choisir les différents modes de jeux :
 * Joueur vs AI
 * AI VS Joueur
 * Duel (chacun son tour)
 *
 * @author Maximilien Le Boiteux
 * @version 1.0
 * @see Game
 * @see IA
 * @see RecherchePlusMoins
 * @see MasterMind
 */

public class ModeDeJeux {

    private char rejouer = ' ';
    private String TypedeJeu = "";



    public ModeDeJeux() {
        String Choix = "0";
        this.rejouer = ' ';
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
            Game jeuEnCours;
            if (this.TypedeJeu.equals("R")) {
                jeuEnCours = new RecherchePlusMoins(Choix);
            } else if (this.TypedeJeu.equals("M")) {
                jeuEnCours = new MasterMind(Choix);
            }
        }
    }
}
