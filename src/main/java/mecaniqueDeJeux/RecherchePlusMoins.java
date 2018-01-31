package main.java.mecaniqueDeJeux;

import org.apache.log4j.Level;

/**
 * Classe RecherchePlusMoins organisant ce mode de jeu
 *
 * @author Maximilien Le Boiteux
 * @version 1.0
 */

public class RecherchePlusMoins extends Game {
    /**
     * Méthode organisant les paramètres du mode de jeu RecherchePlusMoins
     *
     * @param rgMode relaye le paramètre du jeu (Du/De/Ch)
     */
    public RecherchePlusMoins(String rgMode) {
        super();
        this.configdujeux.log.log(Level.INFO, "INIT Ouverture classe");
        this.regleMode = " Chiffres de 0 à 9";
        this.nbChar = this.configdujeux.nbcasesRech;
        this.nbTest = this.configdujeux.nbessaiRech;
        this.mode = rgMode;

        while (!CodeString.equals("N")&&!CodeString.equals("Q")) {
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
