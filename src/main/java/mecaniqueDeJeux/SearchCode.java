package main.java.mecaniqueDeJeux;

import org.apache.log4j.Level;

/**
 * SearchCode Class to specify Game Class
 *
 * @author Maximilien Le Boiteux
 * @version 1.0
 */

public class SearchCode extends Game {
    /**
     * Method to fix parameters of this game
     *
     * @param rgMode parameter (Du/De/Ch)
     */
    public SearchCode(String rgMode) {
        super();
        this.configGame.log.log(Level.INFO, "INIT Ouverture classe");
        this.modeRules = " Chiffres de 0 à 9";
        this.nbChar = this.configGame.nbChainSearch;
        this.nbTest = this.configGame.nbTrySearch;
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
     * Method to calculate the result of the IA
     *
     * @param propIA is a String whiche register the proposal of the IA
     */
    protected String interFaceResultIA(String propIA) {
        String interFaceIA = "";
        interFaceIA = intelArt.ListeResult.get(numberTest - 1);
        return interFaceIA;
    }

    /**
     * Method to register the result of the player
     */
    protected String interFaceResultPlayer() {
        String interFaceJ;
        interFaceJ = ControlETResultat();
        return interFaceJ;
    }
}
