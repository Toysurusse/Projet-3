package main.java.mecaniqueDeJeux;

/**
 * Classe MasterMind organisant ce mode de jeu
 *
 * @author Maximilien Le Boiteux
 * @version 1.0
 */

public class MasterMind extends Game {

    /**
     * Méthode organisant les paramètres du mode de jeu MasterMind
     */
    public MasterMind(String rgMode) {
        super();
        this.nbChar = this.configdujeux.nbcasesMasterM;
        this.nbTest = this.configdujeux.nbcouleurMasterM;
        this.regleMode = " Chiffres de 0 à "+this.nbTest;
        this.mode = rgMode;

        while (!CodeString.equals("N")) {
            registercombinaisonsecrete();
            if (this.configdujeux.ModeDev) {
                System.out.println("Combinaison secrète Joueur : " + this.combisecretejoueur);
                System.out.println("Combinaison secrète IA : " + this.combisecreteia);
            }
            System.out.println("------------------Trouver le code------------------");
            findcombinaisonsecrete();
            Replay();
        }
    }
}
