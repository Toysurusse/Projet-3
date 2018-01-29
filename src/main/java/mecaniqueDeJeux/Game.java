package main.java.mecaniqueDeJeux;

import main.java.Configuration;
import java.util.Scanner;
import java.lang.*;

/**
 * La classe game est une classe abstraite qui organise l'architecture commune des classes de jeu RecherchePlusMoins et MasterMind
 *
 * @author Maximilien Le Boiteux
 * @version 1.0
 */

abstract class Game {

    protected String combisecreteia; // Integer pour enregistrer la combinaison secrète IA
    protected String combisecretejoueur = "-2"; // Integer pour enregistrer la combinaison secrète Joueur
    protected int nbChar;
    protected int nbTest;
    protected String regleMode;
    protected String mode;
    protected Configuration configdujeux;
    protected Scanner sc = new Scanner(System.in);
    protected String CodeString = "0"; // String pour enregistrer les entrées clavier
    protected String ResultMM = "";
    protected int nombreEssai;
    protected IA IntelArt = new IA();

    public Game() {
        Configuration cf = new Configuration();
        this.combisecreteia = "-2";
        this.combisecretejoueur = "-2";
        this.nbTest = 0;
        this.configdujeux = cf;
        this.regleMode = "";
        this.mode = "";
    }

    protected String registercombinaisonsecrete() {
        if (this.mode.equals("De") || this.mode.equals("Du")) {
            CodeString = IntelArt.Randomgen(this.nbChar);
            this.combisecreteia = CodeString;
        }
        if (this.mode.equals("Ch") || this.mode.equals("Du")) {
            Dialog(new String("Enregistrer"), this.nbChar, this.regleMode, 0, 0);
            this.combisecretejoueur = CodeString;
        }
        return CodeString;
    }

    protected void findcombinaisonsecrete() {
        nombreEssai = 1;
        String PropositionIA = "-1";
        CodeString = "-1";
        while (!this.combisecreteia.equals(CodeString) && nombreEssai < this.nbTest && !this.combisecretejoueur.equals(PropositionIA)) {

            if (this.mode.equals("Ch") || this.mode.equals("Du")) { //Génération de la proposition par l'IA
                PropositionIA = IntelArt.IACombiProposition(this.nbChar, nombreEssai);
                IntelArt.IAListeCombiProp(PropositionIA);
                IntelArt.IAListeCombiResult(ResultTest(combisecretejoueur, PropositionIA));
                System.out.println("Proposition IA : " + PropositionIA + " " + IntelArt.ListeResult.get(nombreEssai - 1));
            }
            if (this.mode.equals("De") || this.mode.equals("Du")) { //Génération de la proposition par le joueur
                Dialog(new String("Trouver"), this.nbChar, this.regleMode, nombreEssai, this.nbTest);
            }
            nombreEssai = nombreEssai + 1;
        }
        if (CodeString.equals(this.combisecreteia)) {
            System.out.println("Vous avez gagné ! Félicitation !!");
        } else if (PropositionIA.equals(this.combisecretejoueur)) {
            System.out.println("L'ordinateur a gagné, dommage...");
        } else {
            System.out.println("Dommage, vous avez perdu");
        }
    }

    /**
     * Méthode permettant d'initialiser le dialogue avec le joueur et de valider que les entrées clavier sont au format demandé par les règles du jeu
     *
     * @param nbchar           Fixe le nombre de charactère de la combinaison
     * @param EnregistreTrouve Définit si on "enregistre" ou si on veut "Trouver" la combianaison secrète
     * @param regle            Définit si on "enregistre" ou si on veut "Trouver" la combianaison secrète
     * @see protected static boolean IsAvaible(String EnregistreTrouve,int nbchar)
     * @see Integer combiSecrète
     * @see String CodeString
     * @see Scanner sc
     */


    protected void Dialog(String EnregistreTrouve, int nbchar, String regle, int nbTestTrouver, int nbTestPrevu) {
        if (EnregistreTrouve.equals("Enregistrer") == true) {
            System.out.println("------------------Bonne Partie---------------------");
            System.out.println(EnregistreTrouve + " la cominaison secrète");
            CodeString = "0";
            while (IsAvaible(CodeString, nbchar) == false) {
                System.out.println("La combinaison doit comporter " + nbchar + regle);
                CodeString = sc.nextLine();
            }
        }
        if (EnregistreTrouve.equals("Trouver") == true) {
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
     * @param nbchar Fixe le nombre de charactère de la combinaison
     * @param regle  Définit si on "enregistre" ou si on veut "Trouver" la combianaison secrète
     * @see protected static boolean IsAvaible(String EnregistreTrouve,int nbchar)
     * @see protected static String ResultTest(String code,String proposition)
     * @see Integer combiSecrète
     * @see String CodeString
     * @see Scanner sc
     * @see public static Arraylist IAListeCombiRésult (String HistoResult)
     */


    protected String ControlETResultat(int nbchar, String regle) {
        while (IsAvaible(CodeString, nbchar) == false) {
            System.out.println("La combinaison doit comporter " + nbchar + regle);
            CodeString = sc.nextLine();
        }
        String ResultatPlusMoins = ResultTest(String.valueOf(combisecreteia), CodeString);
        return ResultatPlusMoins;
    }

    /**
     * Méthode permettant de comparer l'entrée clavier avec la combinaison secrète
     * Compare si chaque élément de la combinaison est ", >, ou < à la combinaison secrète
     *
     * @param code        Fixe le nombre de charactère de la combinaison
     * @param proposition Définit si on "enregistre" ou si on veut "Trouver" la combianaison secrète
     */

    protected String ResultTest(String code, String proposition) {
        // Initialise le jeu et demande la combinaison
        String result="";
        for (int i = 0; i < code.length(); i++) {
            char chcode = code.charAt(i);
            char chproposition = proposition.charAt(i);
            if (chcode == chproposition) {
                result = result + "=";
            } else if (chcode < chproposition) {
                result = result + "-";
            } else {
                result = result + "+";
            }
        }
        String resultMM = "";
        int present = 0;
        int bienplace = 0;
        int j = 0;
        String bienPlace =bienplace+ " bien placé , ";
        String Present =present+ " présent";

        for (int i = 0; i < code.length(); i++) {
            char chcode = code.charAt(i);
            char chproposition = proposition.charAt(0);
            while (chcode != chproposition && j < code.length()) {
                chproposition = proposition.charAt(j);
                if (chcode == chproposition && i == j) {
                    bienplace = bienplace + 1;
                    bienPlace = bienplace + " bien placé , ";
                    j = code.length();
                } else if (chcode == chproposition && i != j) {
                    if (code.charAt(i) == proposition.charAt(j)) {
                        bienplace = bienplace + 1;
                        bienPlace = bienplace + " bien placé , ";
                        j = code.length();
                    } else {
                        present = present + 1;
                        Present = present + " présent";
                        j = code.length();
                    }
                }
                j = j + 1;
            }
        }
        resultMM = bienPlace + Present;
        return result;
    }

    /**
     * Méthode permettant de Controler que la combinaison a le bon nombre de charactère et
     * qur la combinaison proposée est un nombre
     *
     * @param stri   String à controler (entrées clavier)
     * @param nbchar Fixe le nombre de charactère de la combinaison
     */

    protected boolean IsAvaible(String stri, int nbchar) {
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
     * @param st String à contrôler (entrées clavier)
     */

    protected boolean Isnumeric(String st) {
        String chaine = st;
        for (int i = 0; i < chaine.length(); i++) {
            char s = chaine.charAt(i);
            if (Character.isDigit(s) == false) {
                return false;
            }
        }
        return true;
    }

    protected void Replay() {
        while (!CodeString.equals("O") && !CodeString.equals("N")) {
            System.out.println("Voulez vous rejouer ?");
            CodeString = this.sc.nextLine();
        }
    }
}