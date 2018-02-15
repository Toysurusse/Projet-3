package main.java.mecaniqueDeJeux;

import main.java.Configuration;
import org.apache.log4j.Level;

import java.util.Scanner;

/**
 * Abstract Class which organize the game
 *
 * @author Maximilien Le Boiteux
 * @version 1.0
 */

abstract class Game {

    /**
     * String to register the code of the IA
     */
    protected String secretCombiIA; // Integer pour enregistrer la combinaison secrète IA
    /**
     * String to register the code of the player
     */
    protected String SecretCombiPlayer; // Integer pour enregistrer la combinaison secrète Joueur
    /**
     * Integer to fix the number of character of the code
     */
    protected int nbChar;
    /**
     * Integer to fix the number of try to find the code
     */
    protected int nbTest;
    /**
     * String to fix the number of number of each character of the code
     */
    protected String modeRules;
    /**
     * String to fix the kind of game : Du/Ch/De
     */
    protected String mode;
    /**
     * Object to configure the game(register in the path sec.java.resources.config.properties)
     */
    protected Configuration configGame;
    /**
     * Scanner to communicate with the player
     */
    protected Scanner sc = new Scanner(System.in);
    /**
     * String to register proposals of the player
     */
    protected String codeString = "0"; // String pour enregistrer les entrées clavier
    /**
     * Integer to register the number of try of the player or of the IA
     */
    protected int numberTest;
    /**
     * Initialise IA class to let the computer play
     */
    protected IA intelArt = new IA();
    /**
     * Integer to fix the number max of each character
     */
    protected int nbNumbers = 9;

    /**
     * Méthod to fix essentials parameters of the game.
     */
    public Game() {
        this.secretCombiIA = "-2";
        this.SecretCombiPlayer = "-2";
        this.nbTest = 0;
        this.modeRules = "";
        this.mode = "";
    }

    /**
     * Méthod to register the code of the IA or of the player
     */
    protected void registerSecretCode() {
        if (this.mode.equals("P") || this.mode.equals("D")) {
            codeString = intelArt.randomGen(this.nbChar, nbNumbers);
            this.secretCombiIA = codeString;
        }
        if (this.mode.equals("C") || this.mode.equals("D")) {
            Dialog(new String("Enregistrer"));
            this.SecretCombiPlayer = codeString;
        }
        this.configGame.log.log(Level.INFO, "Mode de jeu = " +this.mode);
        this.configGame.log.log(Level.INFO, "secretCombiIA = " +this.secretCombiIA + "SecretCombiPlayer = " + this.SecretCombiPlayer);
    }

    /**
     * Méthod to find the code of the IA or of the player
     */
    protected void findSecretCode() {
        numberTest = 1;
        String PropositionIA = "-1";
        codeString = "-1";
        while (!this.secretCombiIA.equals(codeString) && numberTest < this.nbTest && !this.SecretCombiPlayer.equals(PropositionIA)) {
            if (this.mode.equals("C") || this.mode.equals("D")) { //Génération de la proposition par l'IA
                PropositionIA = intelArt.iaCombiPropal(this.nbChar, numberTest, nbNumbers);
                intelArt.iaCombiPropalList(PropositionIA);
                intelArt.iaCombiResultList(resultTest(SecretCombiPlayer, PropositionIA));
                System.out.println("Proposition IA : " + PropositionIA + " -> Réponse : " + interFaceResultIA(PropositionIA));
                this.configGame.log.log(Level.INFO, "Proposition IA : " + PropositionIA + " -> Réponse : " + interFaceResultIA(PropositionIA));
            }
            if (this.mode.equals("P") || this.mode.equals("D")) { //Génération de la proposition par le joueur
                Dialog(new String("Trouver"));
            }
            numberTest = numberTest + 1;
        }
        if (codeString.equals(this.secretCombiIA)) {
            System.out.println("");
            System.out.println("Vous avez gagné ! Félicitation !!");
        } else if (PropositionIA.equals(this.SecretCombiPlayer)) {
            System.out.println("");
            System.out.println("L'ordinateur a gagné, dommage...");
            System.out.println("");
            System.out.println("Combinaison secrète Joueur : " + this.SecretCombiPlayer);
            System.out.println("Combinaison secrète IA : " + this.secretCombiIA);
        } else {
            System.out.println("");
            System.out.println("Dommage, vous avez perdu");
            System.out.println("");
            System.out.println("Combinaison secrète Joueur : " + this.SecretCombiPlayer);
            System.out.println("Combinaison secrète IA : " + this.secretCombiIA);
        }
    }


    /**
     * Méthod to adapt the result of the game search or the game MasterMind
     */
    protected abstract String interFaceResultIA(String propositionIA);

    /**
     * Méthod to dialog with the player
     *
     * @param FindRegister organize the method to register or find the code
     */
    private void Dialog(String FindRegister) {
        if (FindRegister.equals("Enregistrer") == true) {
            System.out.println("------------------Bonne Partie---------------------");
            System.out.println(FindRegister + " la cominaison secrète");
            codeString = "-2";
            while (isAvaible(codeString) == false) {
                System.out.println("La combinaison doit comporter " + this.nbChar + this.modeRules);
                System.out.print("Entrer votre combinaison : ");
                codeString = sc.nextLine();
            }
        }
        if (FindRegister.equals("Trouver") == true) {
            System.out.print("Entrer votre combinaison : ");
            codeString = sc.nextLine();
            while (isAvaible(codeString) == false && numberTest < this.nbTest) {
                System.out.println("La combinaison doit comporter " + this.nbChar + this.modeRules);
                System.out.print("Entrer votre proposition de code : ");
                codeString = sc.nextLine();
            }
            System.out.println("Proposition Joueur : " + codeString + " -> Réponse : " + interFaceResultPlayer());
            this.configGame.log.log(Level.INFO, "Proposition Joueur : " + codeString + " -> Réponse : " + interFaceResultPlayer());
        }
    }

    protected abstract String interFaceResultPlayer();

    /**
     * Method to control player proposals
     */
    protected String ControlETResultat() {
        while (isAvaible(codeString) == false) {
            System.out.println("La combinaison doit comporter " + this.nbChar + this.modeRules);
            codeString = sc.nextLine();
        }
        String ResultatPlusMoins = resultTest(String.valueOf(secretCombiIA), codeString);
        return ResultatPlusMoins;
    }

    /**
     * Method to compare the code to the proposal for the searchMode and the IA
     *
     * @param code        Code to find
     * @param proposition proposal of the player
     */
    private String resultTest(String code, String proposition) {
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
        return result;
    }

    /**
     * Method to control the size of the proposal and if the proposal contain only numbers
     *
     * @param stri String à controler (entrées clavier)
     */
    private boolean isAvaible(String stri) {
        if (stri.length() == this.nbChar) {// Controle que la combinaison a le bon nombre de charactère
            if (isNumeric(stri)) {//
                return true;
            }
        }
        return false;
    }

    /**
     * Method to control if each character is numeric
     *
     * @param st String à contrôler (entrées clavier)
     */
    private boolean isNumeric(String st) {
        String chaine = st;
        for (int i = 0; i < chaine.length(); i++) {
            char s = chaine.charAt(i);
            if (Character.isDigit(s) == false) {
                return false;
            } else if (Character.getNumericValue(s) > nbNumbers) {
                return false;
            }
        }
        return true;
    }
}