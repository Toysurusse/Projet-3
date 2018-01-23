package main.java.mecaniqueDeJeux;

import java.util.Scanner;
import java.lang.*;

import static main.java.mecaniqueDeJeux.IA.*;

/**
 * La classe game est une classe abstraite qui organise l'architecture commune des classes de jeu RecherchePlusMoins et MasterMind
 * @see RecherchePlusMoins
 * @see MasterMind
 * @see IA
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

    protected static String combisecrèteia = "-2"; // Integer pour enregistrer la combinaison secrète IA
    protected static String combisecrètejoueur = "-2"; // Integer pour enregistrer la combinaison secrète Joueur

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
     * @see public static Arraylist IAListeCombiProp (String Histocombi)
     */


    protected static String registercombinaisonsecrete(int nbchar,String regle,String IACode){
        // Initialise le jeu et demande la combinaison
        if (IACode.equals("Avec IA")||IACode.equals("Duel")){
            CodeString = IACombiSecrète (nbchar);
            combisecrèteia = CodeString;
        }
        if (IACode.equals("Sans IA")||IACode.equals("Duel")) {
            Dialog(new String("Enregistrer"), nbchar, regle,0,0);
            combisecrètejoueur = CodeString;
            }
    return CodeString;}

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

    protected static void findcombinaisonsecrete(int nbchar, int nbessai,String regle, String IAfind){
        // Initialise le jeu et demande la combinaison

        nombreEssai=1;
        String PropositionIA="-1";
        CodeString="-1";
        while (!combisecrèteia.equals(CodeString) && nombreEssai < nbessai && !combisecrètejoueur.equals(PropositionIA)) {
        if (IAfind.equals("Avec IA")||IAfind.equals("Duel")){ //Génération de la proposition par l'IA
                PropositionIA = IACombiProposition(nbchar, nombreEssai);
                IAListeCombiProp(PropositionIA);
                IAListeCombiRésult (ResultTest(combisecrètejoueur, PropositionIA));
                System.out.println("Proposition IA : " + PropositionIA+" "+ListeResult.get(nombreEssai-1));
        }
        if (IAfind.equals("Sans IA")||IAfind.equals("Duel")) { //Génération de la proposition par le joueur
            Dialog(new String("Trouver"), nbchar, regle,nombreEssai,nbessai);
            }
            nombreEssai = nombreEssai + 1;
        }

        //Fin de partie
        if (combisecrèteia.equals(CodeString)){
            System.out.println("Vous avez gagné ! Félicitation !!");
            }
            else if (combisecrètejoueur.equals(PropositionIA)) {
            System.out.println("L'ordinateur a gagné, dommage...");
            }
            else{
            System.out.println("Dommage, vous avez perdu");
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


    protected static void Dialog(String EnregistreTrouve,int nbchar, String regle,int nbTestTrouver,int nbTestPrevu){
        // Initialise le jeu et demande la combinaison
        if(EnregistreTrouve.equals("Enregistrer")==true) {
            System.out.println("------------------Bonne Partie---------------------");
            System.out.println(EnregistreTrouve + " la cominaison secrète");
            CodeString="0";
            while (IsAvaible(CodeString, nbchar) == false) {
                System.out.println("La combinaison doit comporter " + nbchar + regle);
                CodeString = sc.nextLine();
            }
        }
        if(EnregistreTrouve.equals("Trouver")==true){
                CodeString = sc.nextLine();
                while (IsAvaible(CodeString, nbchar) == false && nbTestTrouver < nbTestPrevu) {
                    System.out.println("La combinaison doit comporter " + nbchar + regle);
                    CodeString = sc.nextLine();
                }
                System.out.println("Proposition Joueur : " + CodeString + " " + ControlETResultat(nbchar, regle));

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
     * @see public static Arraylist IAListeCombiRésult (String HistoResult)
     */


    protected static String ControlETResultat(int nbchar, String regle){
        while (IsAvaible(CodeString,nbchar) == false) {
            System.out.println("La combinaison doit comporter " + nbchar + regle);
            CodeString = sc.nextLine();
        }
        String ResultatPlusMoins =ResultTest(String.valueOf(combisecrèteia),CodeString);
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
                result=result+'-';
            }
            else {
                result=result+'+';
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