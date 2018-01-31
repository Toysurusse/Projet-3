package main.java.mecaniqueDeJeux;

import main.java.Configuration;
import org.apache.log4j.Level;

import java.util.Scanner;

/**
 * La classe game est une classe abstraite qui organise l'architecture commune des classes de jeu RecherchePlusMoins et MasterMind
 *
 * @author Maximilien Le Boiteux
 * @version 1.0
 */

abstract class Game {

    /**
     * String enregistrant la combinaison secrète de l'intelligence artificielle
     */
    protected String combisecreteia; // Integer pour enregistrer la combinaison secrète IA
    /**
     * String enregistrant la combinaison secrète du joueur
     */
    protected String combisecretejoueur = "-2"; // Integer pour enregistrer la combinaison secrète Joueur
    /**
     * Integer paramétrant le nombre de chiffres de la combinaison
     */
    protected int nbChar;
    /**
     * Integer paramétrant le nombre d'essai lors de la partie
     */
    protected int nbTest;
    /**
     * String paramétrant le nombre de chiffres disponibles
     */
    protected String regleMode;
    /**
     * String paramétrant le mode de jeux (Mastermind ou rechercheplumoins)
     */
    protected String mode;
    /**
     * Objet configurant le jeu (ebregistré dans le path sec.java.resources.config.properties)
     */
    protected Configuration configdujeux;
    /**
     * Scanner permettant les entrées clavier avec la console
     */
    protected Scanner sc = new Scanner(System.in);
    /**
     * String enregistrant les entrées clavier avec la console
     */
    protected String CodeString = "0"; // String pour enregistrer les entrées clavier
    /**
     * String enregistrant la combinaison pour le MasterMind
     */
    protected String ResultMM = "";
    /**
     * Integer enregistrant le nombre d'essai lors de la partie
     */
    protected int nombreEssai;
    /**
     * Initialisation de la classe IA comptenant les dispositions de l'intelligence artificielle
     */
    protected IA IntelArt = new IA();

    /**
     * Méthode principale rattachant les différents paramètres nécessaires à la configuration des méthodes du jeu.
     */
    public Game() {
        Configuration cf = new Configuration();
        this.combisecreteia = "-2";
        this.combisecretejoueur = "-2";
        this.nbTest = 0;
        this.configdujeux = cf;
        this.regleMode = "";
        this.mode = "";
        cf.log.log(Level.INFO, "INIT Chargement classe Game");
    }

    /**
     * Méthode permettant d'enregistrer la combinaison secrète de l'IA ou du joueur
     */
    protected void registercombinaisonsecrete() {
        this.configdujeux.log.log(Level.INFO, "INIT Méthode d'enregistrement de la combinaison");
        if (this.mode.equals("De") || this.mode.equals("Du")) {
            CodeString = IntelArt.Randomgen(this.nbChar);
            this.combisecreteia = CodeString;
        }
        if (this.mode.equals("Ch") || this.mode.equals("Du")) {
            Dialog(new String("Enregistrer"));
            this.combisecretejoueur = CodeString;
        }
    }

    /**
     * Méthode permettant de trouver la combinaison secrète de l'IA ou du joueur
     */
    protected void findcombinaisonsecrete() {
        this.configdujeux.log.log(Level.INFO, "INIT Méthode de recherche de la combinaison");
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
                Dialog(new String("Trouver"));
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
     */
    protected void Dialog(String EnregistreTrouve) {
        this.configdujeux.log.log(Level.INFO, "INIT Méthode de dialogue avec le joueur");
        if (EnregistreTrouve.equals("Enregistrer") == true) {
            System.out.println("------------------Bonne Partie---------------------");
            System.out.println(EnregistreTrouve + " la cominaison secrète");
            CodeString = "0";
            while (IsAvaible(CodeString) == false) {
                System.out.println("La combinaison doit comporter " + this.nbChar + this.regleMode);
                CodeString = sc.nextLine();
            }
        }
        if (EnregistreTrouve.equals("Trouver") == true) {
            CodeString = sc.nextLine();
            while (IsAvaible(CodeString) == false && nombreEssai < this.nbTest) {
                System.out.println("La combinaison doit comporter " + this.nbChar + this.regleMode);
                CodeString = sc.nextLine();
            }
            System.out.println("Proposition Joueur : " + CodeString + " " + ControlETResultat());
        }
    }

    /**
     * Méthode permettant de valider que les entrées clavier sont au format demandé par les règles du jeu
     * et de contrôler si la combinaison est égale/inférieure ou supérieure à la combinaison secrète
     */
    protected String ControlETResultat() {
        this.configdujeux.log.log(Level.INFO, "INIT Méthode de Contrôle de la proposition du joueur");
        while (IsAvaible(CodeString) == false) {
            System.out.println("La combinaison doit comporter " + this.nbChar + this.regleMode);
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
        this.configdujeux.log.log(Level.INFO, "INIT Méthode de comparaison entre code secret et proposition");
        // Initialise le jeu et demande la combinaison
        String result = "";
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
        String bienPlace = bienplace + " bien placé , ";
        String Present = present + " présent";

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
     * Méthode permettant de Controler que la combinaison a le bon nombre de charactère et que la combinaison proposée est un nombre
     *
     * @param stri String à controler (entrées clavier)
     */
    protected boolean IsAvaible(String stri) {
        this.configdujeux.log.log(Level.INFO, "INIT Méthode de controle de la conformité de la combinaison proposée");
        if (stri.length() == this.nbChar) {// Controle que la combinaison a le bon nombre de charactère
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
        this.configdujeux.log.log(Level.INFO, "INIT Contrôle du caractère numérique de la combinaison proposée");
        String chaine = st;
        for (int i = 0; i < chaine.length(); i++) {
            char s = chaine.charAt(i);
            if (Character.isDigit(s) == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * Méthode permettant de rejouer ou de revenir au menu principal
     */
    protected void Replay() {
        this.configdujeux.log.log(Level.INFO, "INIT Méthode permettant de rejouer ou non");
        while (!CodeString.equals("O") && !CodeString.equals("N")) {
            System.out.println("Voulez vous rejouer ?");
            CodeString = this.sc.nextLine();
        }
    }
}