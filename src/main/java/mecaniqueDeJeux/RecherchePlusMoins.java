package main.java.mecaniqueDeJeux;

public class RecherchePlusMoins extends Game {

    public RecherchePlusMoins (){

        System.out.println("----------------Enregistrer le code----------------");
        registercombinaisonsecrete(Configuration.nbcasesRech," chiffres de 0 à 9");
        System.out.println("------------------Trouver le code------------------");
        findcombinaisonsecrete(Configuration.nbcasesRech, Configuration.nbessaiRech," chiffres de 0 à 9");

    }


}
