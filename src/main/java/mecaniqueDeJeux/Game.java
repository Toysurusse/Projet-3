package main.java.mecaniqueDeJeux;

import java.util.Scanner;
import java.lang.*;

public class Game {


    public static int combiSecrète = 0; // Integer pour enregistrer la combinaison secrète
    public static String CodeString; // String pour enregistrer la combinaison secrète
    public static int nombreEssai = 0; // Integer pour enregistrer la combinaison secrète
    public static Scanner sc = new Scanner(System.in);

    public static void registercombinaisonsecrete(){
        // Initialise le jeu et demande la combinaison
        Dialog(new String("Enregistrer"));
        combiSecrète = Integer.parseInt(CodeString);
        System.out.println("Combinaison enregistrée!");
    }

    public static void findcombinaisonsecrete(){
        // Initialise le jeu et demande la combinaison
        Dialog(new String("Trouver"));
        while (combiSecrète != Integer.parseInt(CodeString) && nombreEssai<=Configuration.nbessaiRech){
            System.out.println("Essaye Encore :-)!");
            CodeString = sc.nextLine();
        }
        System.out.println("Vous avez trouvé la combinaison valide");
    }

    public static void Dialog(String EnregistreTrouve){
        // Initialise le jeu et demande la combinaison

        System.out.println(EnregistreTrouve+" la cominaison secrète");
        System.out.println("La combinaison doit comporter " + Configuration.nbcasesRech + " chiffres de 0 à 9");
        CodeString = sc.nextLine();
        while (IsAvaible(CodeString) == false) {
            System.out.println("Vous n'avez malheureusement pas inscrit une combinaison valide");
            System.out.println("La combinaison doit comporter " + Configuration.nbcasesRech + " chiffres de 0 à 9");
            CodeString = sc.nextLine();
        }
    }



    // Controle que la combinaison est bonne
    public static boolean IsAvaible(String stri) {
        Scanner scan = new Scanner(System.in);
        if (stri.length() == Configuration.nbcasesRech) {// Controle que la combinaison a le bon nombre de charactère
            if (Isnumeric(stri)) {// Controle que la combinaison est un nombre
                return true;
            }
        }
        return false;
    }


    public static boolean Isnumeric(String st) {
        String chaine = st;
        for (int i = 0; i < chaine.length(); i++) {
            char s = chaine.charAt(i);
            if (Character.isDigit(s) == false) {
                return false;
            }
        }
        return true;
    }

}