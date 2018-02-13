package main.java;

import main.java.mecaniqueDeJeux.LogClass;
import org.apache.log4j.Level;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Class to fill defaut config from the file resources.config.properties
 *
 * @author Maximilien Le Boiteux
 * @version 1.0
 */
public class configuration extends LogClass {

    /**
     * Integer to fix the size of the game from SearchCode
     */
    public int nbChainSearch = 4;
    /**
     * Integer to fix the number of try of the game from SearchCode
     */
    public int nbTrySearch = 10;
    /**
     * Integer to fix the size of the game from MasterMind
     */
    public int nbChainMasterM = 4;
    /**
     * Integer to fix the number of try of the game from MasterMind
     */
    public int nbTestMasterM = 10;
    /**
     * Integer to fix the number of number of the game from MasterMind
     */
    public int nbColorMasterM = 5;
    /**
     * Boolean to configure the game in devMode or not
     */
    public boolean modeDev = true;

    /**
     * Méthod to import resources.config.properties and the logger log4j
     */
    public configuration() {
        log.log(Level.INFO, "INIT Chargement classe Game");
        try {
            ResourceBundle fichierpropriete = ResourceBundle.getBundle("main.resources.config"); // prop.properties
            this.nbChainSearch = Integer.parseInt(fichierpropriete.getString("Conf.RecherchePlusMoinsnbcasesRech"));
            this.nbTrySearch = Integer.parseInt(fichierpropriete.getString("Conf.RecherchePlusMoinsnbessaiRech"));
            this.nbChainMasterM = Integer.parseInt(fichierpropriete.getString("Conf.MasterMindnbcasesMasterM"));
            this.nbTestMasterM = Integer.parseInt(fichierpropriete.getString("Conf.MasterMindnbessaiMasterM"));
            this.nbColorMasterM = Integer.parseInt(fichierpropriete.getString("Conf.MasterMindnbcouleurMasterM"));
            this.modeDev = Boolean.parseBoolean(fichierpropriete.getString("Conf.JeuxModeDev"));
        } catch (final MissingResourceException x) {
            log.log(Level.ERROR, "-------------Fichier introuvable : chargement de la configuration par défaut-------------");
        }
    }
}
