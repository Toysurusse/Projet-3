package main.java.mecaniqueDeJeux;

import java.util.Scanner;

import static main.java.mecaniqueDeJeux.MasterMind.RechercheMMChall;
import static main.java.mecaniqueDeJeux.RecherchePlusMoins.RecherchePlusMoinsCodeDef;
import static main.java.mecaniqueDeJeux.RecherchePlusMoins.RecherchePlusMoinsDef;

/**
 *Classe de jeux permettant de choisir les différents modes de jeux :
 * Joueur vs AI
 * AI VS Joueur
 * Duel (chacun son tour)
 *
 * @see Game
 * @see IA
 * @see RecherchePlusMoins
 * @see MasterMind
 *
 * @author Maximilien Le Boiteux
 * @version 1.0
*/

public class ModeDeJeux {

    public static char rejouer=' ';
    public static Scanner scanRejouer = new Scanner(System.in);
    private static String TypedeJeu="";

    public ModeDeJeux (){
        String Choix="0";
        Game.combisecrèteia="-2";
        Game.combisecrètejoueur="-2";

        boolean Boucle=true;
        Scanner choixjeu = new Scanner(System.in);

        System.out.println("--------------Choisissez votre jeux----------------");
        while (Choix.charAt(0) != 'M' && Choix.charAt(0) != 'R' && Choix.charAt(0) != 'Q') {
            System.out.println("Voulez-vous jouer au MasterMind (M), à RecherchePlusMoins (R) ou Quitter le jeu (Q) ?");
            Choix=choixjeu.nextLine();
        }
        TypedeJeu = Choix;
        if(TypedeJeu.charAt(0) =='Q')System.exit (0);
        System.out.println("------------Choisissez le mode de jeux-------------");
        while (!Choix.equals("Du")&&!Choix.equals("De")&&!Choix.equals("Ch")) {
            System.out.println("Voulez-vous jouer en mode Duel(Du), Défenseur (De), Challenger (Ch) ?");
                Choix = choixjeu.nextLine();
        }
        if (Choix.equals("Du")) Duel(TypedeJeu);
        else if (Choix.equals("De")) Defenseur(TypedeJeu);
        else if (Choix.equals("Ch")) Challenger(TypedeJeu);
    }

    public static void Defenseur (String TypeJ){
        if (TypeJ.equals("R")){
            RecherchePlusMoinsCodeDef("Avec IA");
            if(Configuration.ModeDev == true) {
                System.out.println("Combinaison secrète Joueur : "+Game.combisecrètejoueur);
                System.out.println("Combinaison secrète IA : "+Game.combisecrèteia);
            }
            System.out.println("------------------Trouver le code------------------");
            RecherchePlusMoinsDef("Sans IA");
        }
        if (TypeJ.equals("M"))RechercheMMChall(); // Construire MasterMind__________________
        rejouer=' ';
        if (Replay())Defenseur (TypedeJeu);


        new ModeDeJeux ();
    }

    public static void Challenger (String TypeJ){
        if (TypeJ.equals("R")){
            RecherchePlusMoinsCodeDef("Sans IA");
            if(Configuration.ModeDev == true) {
                System.out.println("Combinaison secrète Joueur : "+Game.combisecrètejoueur);
                System.out.println("Combinaison secrète IA : "+Game.combisecrèteia);
            }
            System.out.println("------------------Trouver le code------------------");
            RecherchePlusMoinsDef("Avec IA");
        }
        rejouer=' ';
        if (Replay())Challenger (TypedeJeu);
        new ModeDeJeux ();
    }

    public static void Duel (String TypeJ){
        if (TypeJ.equals("R")){
            RecherchePlusMoinsCodeDef("Duel");
            if(Configuration.ModeDev == true) {
                System.out.println("Combinaison secrète Joueur : "+Game.combisecrètejoueur);
                System.out.println("Combinaison secrète IA : "+Game.combisecrèteia);
            }
            System.out.println("------------------Trouver le code------------------");
            RecherchePlusMoinsDef("Duel");
        }
        rejouer=' ';
        if (Replay())Duel (TypedeJeu);
        new ModeDeJeux ();
    }

    public static boolean Replay (){
        boolean RP=false;
        while (rejouer!='O'&&rejouer!='N'){
            System.out.print("Souhaitez vous rejouer ?");
            rejouer= scanRejouer.nextLine().charAt(0);
        }
        if (rejouer=='O')RP=true;
    return RP;}
}
