package main.java.mecaniqueDeJeux;

import java.util.Random;

/**
 *
 * Intellignece artificielle recherchant la combinaison secère ou créant la combinaison secrète
 *
 */


public class IA {

public static String IACombiSecrète (int nbchar){
    String CombiIA="";
    Random randomGenerator = new Random();
    int randomInt ;
    System.out.println(CombiIA);
    for (int i=0; i<nbchar;i++){
        randomInt = randomGenerator.nextInt(10);
        CombiIA=CombiIA+Integer.toString(randomInt);
    }
    return CombiIA;
}

}
