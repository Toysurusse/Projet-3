package main.java.mecaniqueDeJeux;

public class MasterMind extends Game {


    public MasterMind(String rgMode) {
        super();
        this.nbChar = this.configdujeux.nbcasesMasterM;
        this.nbTest = this.configdujeux.nbcouleurMasterM;
        this.regleMode = " Chiffres de 0 à "+this.nbTest;
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
}
