package main.java.mecaniqueDeJeux;

import java.util.ArrayList;
import java.util.Random;

/**
 * Intellignece artificielle recherchant la combinaison secère ou créant la combinaison secrète
 *
 * @author Maximilien Le Boiteux
 * @version 1.0
 */
public class IA {
    /**
     * Arraylist enregistrant la liste des combinaisons
     */
    public ArrayList<String> ListeCombi = new ArrayList<String>();
    /**
     * Arraylist enregistrant la liste du résultat de chaque combinaison
     */
    public ArrayList<String> ListeResult = new ArrayList<String>();

    /**
     * Méthode de type string permettant de proposer les combinaisons de l'IA
     */
    public String IACombiProposition(int nbchar, int nbprop, int nbChiffres) {
        String CombiIAProposition = "";
        if (nbprop == 1) {
            ListeCombi.clear();
            ListeResult.clear();
            CombiIAProposition = Randomgen(nbchar, nbChiffres);
        } else {
            CombiIAProposition = IAfind(nbprop, nbchar);
        }
        return CombiIAProposition;
    }

    /**
     * Méthode enregistrant la liste des combinaisons proposées
     */
    public void IAListeCombiProp(String historiqueCombi) {
        ListeCombi.add(historiqueCombi);
    }

    /**
     * Méthode enregistrant la liste des résultats suite aux combinaisons proposées
     */
    public void IAListeCombiResult(String historiqueResult) {
        ListeResult.add(historiqueResult);
    }

    /**
     * Méthode de type string permettant de proposer une combinaison intelligente
     */
    public String IAfind(int nbprop, int nbchar) {
        String Propal = "";
        int calculentiercombinaison = 0;
        double calculvirgulecombinaison = 0;
        for (int i = 0; i < nbchar; i++) {
            if (ListeResult.get(nbprop - 2).charAt(i) == '=') {
                Propal = Propal + ListeCombi.get(nbprop - 2).charAt(i);
            } else if (ListeResult.get(nbprop - 2).charAt(i) == '-') {
                calculentiercombinaison = Character.getNumericValue(ListeCombi.get(nbprop - 2).charAt(i));
                calculvirgulecombinaison = Math.round((calculentiercombinaison / 2) - 0.1);
                calculentiercombinaison = (int) calculvirgulecombinaison;
                Propal = Propal + calculentiercombinaison;
            } else if (ListeResult.get(nbprop - 2).charAt(i) == '+') {
                calculentiercombinaison = Character.getNumericValue(ListeCombi.get(nbprop - 2).charAt(i));
                calculvirgulecombinaison = Math.round((calculentiercombinaison + 1));
                calculentiercombinaison = (int) calculvirgulecombinaison;
                Propal = Propal + calculentiercombinaison;
            }
        }
        return Propal;
    }

    /**
     * Générateur de combinaison aléatoire
     */
    public String Randomgen(int nbchar, int nbChiffres) {
        String CombiIA = "";
        Random randomGenerator = new Random();
        int randomInt;
        for (int i = 0; i < nbchar; i++) {
            randomInt = randomGenerator.nextInt(nbChiffres-1);
            CombiIA = CombiIA + Integer.toString(randomInt);
        }
        return CombiIA;
    }
}