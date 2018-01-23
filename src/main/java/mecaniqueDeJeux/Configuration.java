package main.java.mecaniqueDeJeux;

import com.sun.javafx.tools.packager.bundlers.Bundler;

import java.util.Enumeration;
import java.util.ResourceBundle;

public class Configuration {

    public static int nbcasesRech = 0;
    public static int nbessaiRech = 0;
    public static int nbcasesMasterM = 0;
    public static int nbessaiMasterM = 0;
    public static int nbcouleurMasterM = 0;
    public static boolean ModeDev = true;

    public Configuration (){
        ResourceBundle fichierpropriete = ResourceBundle.getBundle("main.resources.config"); // prop.properties
        nbcasesRech = Integer.parseInt(fichierpropriete.getString("Conf.RecherchePlusMoinsnbcasesRech"));
        nbessaiRech = Integer.parseInt(fichierpropriete.getString("Conf.RecherchePlusMoinsnbessaiRech"));
        nbcasesMasterM = Integer.parseInt(fichierpropriete.getString("Conf.MasterMindnbcasesMasterM"));
        nbessaiMasterM = Integer.parseInt(fichierpropriete.getString("Conf.MasterMindnbessaiMasterM"));
        nbcouleurMasterM =Integer.parseInt(fichierpropriete.getString("Conf.MasterMindnbcouleurMasterM"));
        ModeDev = Boolean.parseBoolean(fichierpropriete.getString("Conf.JeuxModeDev"));
    System.out.println(nbcasesRech+" "+nbessaiRech+" "+nbessaiRech+" "+nbcasesMasterM+" "+nbessaiMasterM+" "+nbcouleurMasterM+" "+ModeDev);
    }



}
