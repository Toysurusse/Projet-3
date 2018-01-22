package main.java.mecaniqueDeJeux;

import java.util.Scanner;
import java.lang.*;

/**
 * La classe game est une classe abstraite qui organise l'architecture commune des classes RecherchePlusMoins et MasterMind
 * @see RecherchePlusMoins
 * @see MasterMind
 *
 *
 * @author Maximilien Le Boiteux
 * @version 1.0
 */





abstract class Game {

    /**
     * Integer servant de combinaison secrète
     * @see protected static void registercombinaisonsecrete (int nbchar)
     * @see protected static void findcombinaisonsecrete(int nbchar, int nbessai)
     * @see protected static void Dialog(String EnregistreTrouve,int nbchar)
     * @see protected static String ControlETResultat(int nbchar)
     */

    protected static int combiSecrète = 0; // Integer pour enregistrer la combinaison secrète

    /**
     * Scanner servant à enregistrer les entrées clavier de l'utilisateur
     * @see protected static void registercombinaisonsecrete (int nbchar)
     * @see protected static void findcombinaisonsecrete(int nbchar, int nbessai)
     * @see protected static void Dialog(String EnregistreTrouve,int nbchar)
     * @see protected static String ControlETResultat(int nbchar)
     */

    protected static Scanner sc = new Scanner(System.in);

    /**
     * String servant à enregistrer les entrées clavier de l'utilisateur
     * @see protected static void registercombinaisonsecrete (int nbchar)
     * @see protected static void findcombinaisonsecrete(int nbchar, int nbessai)
     * @see protected static void Dialog(String EnregistreTrouve,int nbchar)
     * @see protected static String ControlETResultat(int nbchar)
     */

    protected static String CodeString; // String pour enregistrer la combinaison secrète

    /**
     * Integer servant de comptabiliser le nombre d'essai de l'utilisateur au cours du jeu
     * @see protected static void findcombinaisonsecrete(int nbchar, int nbessai)
     */

    protected static int nombreEssai ; // Integer pour enregistrer la combinaison secrète

    /**
     * Méthode permettant d'enregistrer la combinaison secrète
     *
     * @param nbchar
     *              Fixe le nombre de charactère de la combinaison
     *
     * @see protected static void Dialog(String EnregistreTrouve,int nbchar)
     * @see Integer combiSecrète
     * @see String CodeString
     */


    protected static void registercombinaisonsecrete(int nbchar,String regle){
        // Initialise le jeu et demande la combinaison
        Dialog(new String("Enregistrer"), nbchar, regle);
        combiSecrète = Integer.parseInt(CodeString);
        System.out.println("Combinaison enregistrée!");
    }

    /**
     * Méthode permettant au joueur ou à l'ordinateur de rechercher la combinaison secrète
     *
     * @param nbchar
     *              Fixe le nombre de charactère de la combinaison
     * @param nbessai
     *              Fixe le nombre d'essai prévu dans ce mode de jeu
     *
     * @see protected static void Dialog(String EnregistreTrouve,int nbchar)
     * @see Integer combiSecrète
     * @see Integer nombre d'essai réalisés par l'utilisateur
     * @see String CodeString
     */

    protected static void findcombinaisonsecrete(int nbchar, int nbessai,String regle){
        // Initialise le jeu et demande la combinaison
        Dialog(new String("Trouver"), nbchar, regle);
        nombreEssai=0;
        while (combiSecrète != Integer.parseInt(CodeString) && nombreEssai<=nbessai){
            nombreEssai=nombreEssai+1;
            System.out.println("Presque! Il reste "+(nbessai-nombreEssai)+" essais");
            System.out.println(ControlETResultat(nbchar,regle));
        }

        if (combiSecrète == Integer.parseInt(CodeString)){
            System.out.println("Vous avez gagné ! Félicitation !!");
            }
            else {
            System.out.println("Dommage, vous avez presque trouvé");
        }
    }

    /**
     * Méthode permettant d'initialiser le dialogue avec le joueur et de valider que les entrées clavier sont au format demandé par les règles du jeu
     *
     * @param nbchar
     *              Fixe le nombre de charactère de la combinaison
     * @param EnregistreTrouve
     *              Définit si on "enregistre" ou si on veut "Trouver" la combianaison secrète
     * @param regle
     *              Définit si on "enregistre" ou si on veut "Trouver" la combianaison secrète
     *
     * @see protected static boolean IsAvaible(String EnregistreTrouve,int nbchar)
     * @see Integer combiSecrète
     * @see String CodeString
     * @see Scanner sc
     */


    protected static void Dialog(String EnregistreTrouve,int nbchar, String regle){
        // Initialise le jeu et demande la combinaison

        System.out.println(EnregistreTrouve+" la cominaison secrète");
        System.out.println("La combinaison doit comporter " + nbchar + regle);
        CodeString = sc.nextLine();
        while (IsAvaible(CodeString, nbchar) == false) {
            System.out.println("Vous n'avez malheureusement pas inscrit une combinaison valide");
            System.out.println("La combinaison doit comporter " + nbchar + regle);
            CodeString = sc.nextLine();
        }
        if(EnregistreTrouve.equals("Trouver")==true){
            System.out.println(ResultTest(String.valueOf(combiSecrète),CodeString));
        }
    }

    /**
     * Méthode permettant de valider que les entrées clavier sont au format demandé par les règles du jeu
     * et de contrôler si la combinaison est égale/inférieure ou supérieure à la combinaison secrète
     *
     * @param nbchar
     *              Fixe le nombre de charactère de la combinaison
     * @param regle
     *              Définit si on "enregistre" ou si on veut "Trouver" la combianaison secrète
     *
     * @see protected static boolean IsAvaible(String EnregistreTrouve,int nbchar)
     * @see protected static String ResultTest(String code,String proposition)
     * @see Integer combiSecrète
     * @see String CodeString
     * @see Scanner sc
     */


    protected static String ControlETResultat(int nbchar, String regle){
        CodeString = sc.nextLine();
        while (IsAvaible(CodeString,nbchar) == false) {
            System.out.println("Vous n'avez malheureusement pas inscrit une combinaison valide");
            System.out.println("La combinaison doit comporter " + nbchar + regle);
            CodeString = sc.nextLine();
        }
        String ResultatPlusMoins =ResultTest(String.valueOf(combiSecrète),CodeString);
        return ResultatPlusMoins;
    }

    /**
     * Méthode permettant de comparer l'entrée clavier avec la combinaison secrète
     * Compare si chaque élément de la combinaison est ", >, ou < à la combinaison secrète
     *
     * @param code
     *              Fixe le nombre de charactère de la combinaison
     * @param proposition
     *              Définit si on "enregistre" ou si on veut "Trouver" la combianaison secrète
     *
     */

    protected static String ResultTest(String code,String proposition){
        // Initialise le jeu et demande la combinaison
        String result="";
        for (int i = 0; i < code.length(); i++) {
            char chcode = code.charAt(i);
            char chproposition = proposition.charAt(i);
            if (chcode == chproposition) {
                result=result+'=';
            }
            else if (chcode < chproposition){
                result=result+'+';
            }
            else {
                result=result+'-';
            }
        }
        return result;}

    /**
     * Méthode permettant de Controler que la combinaison a le bon nombre de charactère et
     * qur la combinaison proposée est un nombre
     *
     * @param stri
     *              String à controler (entrées clavier)
     * @param nbchar
     *              Fixe le nombre de charactère de la combinaison
     *
     */

    protected static boolean IsAvaible(String stri, int nbchar) {
        Scanner scan = new Scanner(System.in);
        if (stri.length() == nbchar) {// Controle que la combinaison a le bon nombre de charactère
            if (Isnumeric(stri)) {//
                return true;
            }
        }
        return false;
    }

    /**
     * Méthode permettant de Controler que caractère par caractère que la combinaison proposée est un nombre
     *
     * @param st
     *              String à contrôler (entrées clavier)
     *
     */

    protected static boolean Isnumeric(String st) {
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