package main.java.mecaniqueDeJeux;

import com.sun.javafx.tools.packager.bundlers.Bundler;

import java.io.IOException;
import java.util.Enumeration;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Configuration {

    public int nbcasesRech ;
    public int nbessaiRech ;
    public int nbcasesMasterM ;
    public int nbessaiMasterM ;
    public int nbcouleurMasterM ;
    public boolean ModeDev ;

    public Configuration() {
        this.nbcasesRech = 4;
        this.nbessaiRech = 10;
        this.nbcasesMasterM = 4;
        this.nbessaiMasterM = 10;
        this.nbcouleurMasterM = 5;
        this.ModeDev = true;
        ResourceBundle fichierpropriete = ResourceBundle.getBundle("main.resources.config"); // prop.properties
        this.nbcasesRech = Integer.parseInt(fichierpropriete.getString("Conf.RecherchePlusMoinsnbcasesRech"));
        this.nbessaiRech = Integer.parseInt(fichierpropriete.getString("Conf.RecherchePlusMoinsnbessaiRech"));
        this.nbcasesMasterM = Integer.parseInt(fichierpropriete.getString("Conf.MasterMindnbcasesMasterM"));
        this.nbessaiMasterM = Integer.parseInt(fichierpropriete.getString("Conf.MasterMindnbessaiMasterM"));
        this.nbcouleurMasterM = Integer.parseInt(fichierpropriete.getString("Conf.MasterMindnbcouleurMasterM"));
        this.ModeDev = Boolean.parseBoolean(fichierpropriete.getString("Conf.JeuxModeDev"));
    }
}
