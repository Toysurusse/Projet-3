package main.java.mecaniqueDeJeux;

import java.util.ArrayList;
import java.util.Random;

/**
 * Intellignece artificielle recherchant la combinaison secère ou créant la combinaison secrète
 */


public class IA {

    public ArrayList<String> ListeCombi = new ArrayList<String>();
    public ArrayList<String> ListeResult = new ArrayList<String>();

    public String IACombiProposition(int nbchar, int nbprop) {
        String CombiIAProposition = "";
        if (nbprop == 1) {
            ListeCombi.clear();
            ListeResult.clear();
            CombiIAProposition = Randomgen(nbchar);
        } else {
            CombiIAProposition = IAfind(nbprop, nbchar);
        }
        return CombiIAProposition;
    }

    public void IAListeCombiProp(String historiqueCombi) {
        ListeCombi.add(historiqueCombi);
    }

    public void IAListeCombiResult(String historiqueResult) {
        ListeResult.add(historiqueResult);
    }

    public String IAfind(int nbprop, int nbchar) {
        String Propal = "";
        int calculentiercombinaison = 0;
        double calculvirgulecombinaison = 0;

    /*for (int d=0;d<ListeCombi.size();d++){
        System.out.print(" "+ListeCombi.get(d));
        //System.out.print(" = : "+ListeResult.get(d));
        }
    for (int e=0;e<ListeResult.size();e++){
    System.out.print(" "+ListeResult.get(e));}*/

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


    public String Randomgen(int nbchar) {
        String CombiIA = "";
        Random randomGenerator = new Random();
        int randomInt;
        for (int i = 0; i < nbchar; i++) {
            randomInt = randomGenerator.nextInt(10);
            CombiIA = CombiIA + Integer.toString(randomInt);
        }
        return CombiIA;
    }
}