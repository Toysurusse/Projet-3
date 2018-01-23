package main.java.mecaniqueDeJeux;

public class RecherchePlusMoins extends Game {

    public static void RecherchePlusMoinsCodeDef(String IAOrNot) {
        registercombinaisonsecrete(Configuration.nbcasesRech, " Chiffres de 0 à 9", IAOrNot);
    }

    public static void RecherchePlusMoinsDef(String IAOrNot) {
        findcombinaisonsecrete(Configuration.nbcasesRech, Configuration.nbessaiRech, " Chiffres de 0 à 9",IAOrNot);
    }

}
