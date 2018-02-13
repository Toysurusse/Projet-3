package main.java.mecaniqueDeJeux;

import java.util.ArrayList;
import java.util.Random;

/**
 * Computer Player which register or find the code
 *
 * @author Maximilien Le Boiteux
 * @version 1.0
 */
public class IA {

    /**
     * Arraylist to register the list of proposals
     */
    public ArrayList<String> ListeCombi = new ArrayList<String>();
    /**
     * Arraylist to register the list of results for each proposals
     */
    public ArrayList<String> ListeResult = new ArrayList<String>();

    /**
     * Method return String to propose code
     *
     * @param nbchar number of characters of the code
     * @param nbProp number of tries
     * @param nbNumbers fix the numbers allowed
     */
    public String iaCombiPropal(int nbchar, int nbProp, int nbNumbers) {
        String combiIAPropal = "";
        if (nbProp == 1) {
            ListeCombi.clear();
            ListeResult.clear();
            combiIAPropal = randomGen(nbchar, nbNumbers);
        } else {
            combiIAPropal = iaFind(nbProp, nbchar);
        }
        return combiIAPropal;
    }

    /**
     * Method to register the list of proposal
     */
    public void iaCombiPropalList(String historicCombi) {
        ListeCombi.add(historicCombi);
    }

    /**
     * Méthode to register the list of results
     */
    public void iaCombiResultList(String historicResult) {
        ListeResult.add(historicResult);
    }

    /**
     * Méthod to find the code of the player
     */
    private String iaFind(int nbProp, int nbchar) {
        String Propal = "";
        int combiMath = 0;
        double calculComaCombi = 0;
        for (int i = 0; i < nbchar; i++) {
            if (ListeResult.get(nbProp - 2).charAt(i) == '=') {
                Propal = Propal + ListeCombi.get(nbProp - 2).charAt(i);
            } else if (ListeResult.get(nbProp - 2).charAt(i) == '-') {
                combiMath = Character.getNumericValue(ListeCombi.get(nbProp - 2).charAt(i));
                calculComaCombi = Math.round((combiMath / 2) - 0.1);
                combiMath = (int) calculComaCombi;
                Propal = Propal + combiMath;
            } else if (ListeResult.get(nbProp - 2).charAt(i) == '+') {
                combiMath = Character.getNumericValue(ListeCombi.get(nbProp - 2).charAt(i));
                calculComaCombi = Math.round((combiMath + 1));
                combiMath = (int) calculComaCombi;
                Propal = Propal + combiMath;
            }
        }
        return Propal;
    }

    /**
     * Method to make a random KeyCode
     */
    public String randomGen(int nbchar, int nbNumbers) {
        String CombiIA = "";
        Random randomGenerator = new Random();
        int randomInt;
        for (int i = 0; i < nbchar; i++) {
            randomInt = randomGenerator.nextInt(nbNumbers - 1);
            CombiIA = CombiIA + Integer.toString(randomInt);
        }
        return CombiIA;
    }
}