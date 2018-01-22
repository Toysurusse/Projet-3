package main.java.mecaniqueDeJeux;

public class RecherchePlusMoins extends Game {

    public static String RecherchePlusMoinsCodeDev;


    public static void RecherchePlusMoinsCodeDef() {
        RecherchePlusMoinsCodeDev=registercombinaisonsecrete(Configuration.nbcasesRech, " Chiffres de 0 à 9", "AvecIA");
    }

    public static void RecherchePlusMoinsDef() {
        findcombinaisonsecrete(Configuration.nbcasesRech, Configuration.nbessaiRech, " Chiffres de 0 à 9");
    }

}
