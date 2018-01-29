package main.java.mecaniqueDeJeux;

public class RecherchePlusMoins extends Game {

    public RecherchePlusMoins(String rgMode) {
        super();
        this.regleMode = " Chiffres de 0 à 9";
        this.nbChar = this.configdujeux.nbcasesRech;
        this.nbTest = this.configdujeux.nbessaiRech;

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
