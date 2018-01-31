package main.java.mecaniqueDeJeux;

import org.apache.log4j.Level;

/**
 * Classe MasterMind organisant ce mode de jeu
 *
 * @author Maximilien Le Boiteux
 * @version 1.0
 */

public class MasterMind extends Game {

    /**
     * Méthode organisant les paramètres du mode de jeu MasterMind
     * @param rgMode relaye le paramètre du jeu (Du/De/Ch)
     */
    public MasterMind(String rgMode) {
        super();
        this.configdujeux.log.log(Level.INFO, "INIT Ouverture classe");
        this.nbChar = this.configdujeux.nbcasesMasterM;
        this.nbTest = this.configdujeux.nbessaiMasterM;
        this.regleMode = " Chiffres de 0 à " + this.configdujeux.nbcouleurMasterM;
        nombreChiffres = this.configdujeux.nbcouleurMasterM;
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

    /**
     * Méthode enregistrant le résultat de l'IA du mode de jeu MasterMind après chaque proposition
     */
    protected String InterFaceResultatIA(String propIA) {
        String interFaceIA = "";
        interFaceIA = RésultatMM(String.valueOf(combisecretejoueur), propIA);
        return interFaceIA;
    }

    /**
     * Méthode enregistrant le résultat du joueur du mode de jeu MasterMind après chaque proposition
     */
    protected String InterFaceResultatJoueur() {
        String interFaceJ;
        ControlETResultat();
        interFaceJ = RésultatMM(String.valueOf(combisecreteia), CodeString);
        return interFaceJ;
    }

    /**
     * Méthode calculant le résultat du jeu MasterMind
     *
     * @param code correspondant au code à trouver
     * @param code correspondant au code proposé
     */
    private String RésultatMM(String code, String proposition) {
        String resultMM = "";
        int countcode = 0;
        int countprop = 0;
        int present = 0;
        int bienplace = 0;
        String bienPlace = bienplace + " bien placé , ";
        String Present = present + " présent";
        char chcode = code.charAt(0);
        char chproposition = proposition.charAt(0);

        for (int i = 0; i < configdujeux.nbcouleurMasterM + 1; i++) {
            for (int j = 0; j < code.length(); j++) {
                if (i==Character.getNumericValue(code.charAt(j))) {
                    countcode = countcode + 1;
                }
                if ((char)i==Character.getNumericValue(proposition.charAt(j))) {
                    countprop = countprop + 1;
                }
                //System.out.println("i="+i+" présent : "+present+" countcode : "+countcode+" countMM : "+countprop);
            }
            if (countprop > 0) {
                if (countcode >= countprop) {
                    present=present+countprop;
                }else {
                    present=present+countcode;
                }
            }
            countcode=0;
            countprop=0;
            //System.out.println("------i="+i+" présent : "+present+" countcode : "+countcode+" countMM : "+countprop);
        }
        Present = present + " présent ";
        for (int i = 0; i < code.length(); i++) {
            chcode = code.charAt(i);
            chproposition = proposition.charAt(i);
            if (chcode == chproposition) {
                bienplace = bienplace + 1;
                present=present-1;
                Present = present + " présent ";
                bienPlace = bienplace + " bien placé, ";
            }
        }
        resultMM = bienPlace + Present;
        return resultMM;
    }
}
