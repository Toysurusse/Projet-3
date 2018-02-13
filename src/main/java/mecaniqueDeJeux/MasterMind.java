package main.java.mecaniqueDeJeux;

import org.apache.log4j.Level;

/**
 * MasterMind Class to initialize this kind of Game Class
 *
 * @author Maximilien Le Boiteux
 * @version 1.0
 */

public class MasterMind extends Game {

    /**
     * Method to fix parameters of this Class
     *
     * @param rgMode fix the kind of this game (Du/De/Ch)
     */
    public MasterMind(String rgMode) {
        super();
        this.configGame.log.log(Level.INFO, "INIT Ouverture classe");
        this.nbChar = this.configGame.nbChainMasterM;
        this.nbTest = this.configGame.nbTestMasterM;
        this.modeRules = " Chiffres de 0 à " + this.configGame.nbColorMasterM;
        nbNumbers = this.configGame.nbColorMasterM;
        this.mode = rgMode;

        registerSecretCode();
        if (this.configGame.modeDev) {
            System.out.println("Combinaison secrète Joueur : " + this.SecretCombiPlayer);
            System.out.println("Combinaison secrète IA : " + this.secretCombiIA);
        }
        System.out.println("------------------Trouver le code------------------");
        findSecretCode();
    }

    /**
     * Method to register and create the result of the porposal of IA
     *
     * @param propIA is a kind of String to register the proposal of the IA
     */
    protected String interFaceResultIA(String propIA) {
        String interFaceIA = "";
        interFaceIA = ResultMM(String.valueOf(SecretCombiPlayer), propIA);
        return interFaceIA;
    }

    /**
     * Method to register and create the result of the porposal of the player
     */
    protected String interFaceResultPlayer() {
        String interFaceJ;
        ControlETResultat();
        interFaceJ = ResultMM(String.valueOf(secretCombiIA), codeString);
        return interFaceJ;
    }

    /**
     * Method to analyze the result of each proposal during the game
     *
     * @param code        is the code to find
     * @param proposition proposition of code
     */
    private String ResultMM(String code, String proposition) {
        String resultMM = "";
        int countcode = 0;
        int countprop = 0;
        int present = 0;
        int bienplace = 0;
        String bienPlace = bienplace + " bien placé , ";
        String Present = present + " présent";
        char chcode = code.charAt(0);
        char chproposition = proposition.charAt(0);

        for (int i = 0; i < configGame.nbColorMasterM + 1; i++) {
            for (int j = 0; j < code.length(); j++) {
                if (i == Character.getNumericValue(code.charAt(j))) {
                    countcode = countcode + 1;
                }
                if ((char) i == Character.getNumericValue(proposition.charAt(j))) {
                    countprop = countprop + 1;
                }
            }
            if (countprop > 0) {
                if (countcode >= countprop) {
                    present = present + countprop;
                } else {
                    present = present + countcode;
                }
            }
            countcode = 0;
            countprop = 0;
        }
        Present = present + " présent ";
        for (int i = 0; i < code.length(); i++) {
            chcode = code.charAt(i);
            chproposition = proposition.charAt(i);
            if (chcode == chproposition) {
                bienplace = bienplace + 1;
                present = present - 1;
                Present = present + " présent ";
                bienPlace = bienplace + " bien placé, ";
            }
        }
        if (bienplace > 1) {
            bienPlace = bienplace + " bien placés, ";
        }
        if (present > 1) {
            Present = present + " présents ";
        }
        resultMM = bienPlace + Present;
        return resultMM;
    }
}
