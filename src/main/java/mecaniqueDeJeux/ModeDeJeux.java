package main.java.mecaniqueDeJeux;

import java.util.Scanner;

import static main.java.mecaniqueDeJeux.MasterMind.RechercheMMChall;
import static main.java.mecaniqueDeJeux.RecherchePlusMoins.RecherchePlusMoinsCodeDef;
import static main.java.mecaniqueDeJeux.RecherchePlusMoins.RecherchePlusMoinsCodeDev;
import static main.java.mecaniqueDeJeux.RecherchePlusMoins.RecherchePlusMoinsDef;

/**
 *Classe de jeux permettant de choisir les différents modes de jeux :
 * Joueur vs AI
 * AI VS Joueur
 * Duel (chacun son tour)
 *
*/

public class ModeDeJeux {

    public ModeDeJeux (){
        String Choix="0";
        String TypedeJeu="";
        boolean Boucle=true;
        Scanner choixjeu = new Scanner(System.in);

        System.out.println("--------------Choisissez votre jeux----------------");
        while (Choix.charAt(0) != 'M' && Choix.charAt(0) != 'R') {
            System.out.println("Voulez-vous jouer au MasterMind (M) ou à RecherchePlusMoins (R) ?");
            Choix=choixjeu.nextLine();
        }
        TypedeJeu = Choix;
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
            RecherchePlusMoinsCodeDef();
            if(Configuration.ModeDev == true) System.out.println(RecherchePlusMoinsCodeDev);
            System.out.println("------------------Trouver le code------------------");
            RecherchePlusMoinsDef();
        }
        if (TypeJ.equals("M"))RechercheMMChall();
        new ModeDeJeux ();
    }

    public static void Challenger (String TypeJ){
    }

    public static void Duel (String TypeJ){
    }


}
