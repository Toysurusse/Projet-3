package main.java;

import main.java.mecaniqueDeJeux.LogClass;
import org.apache.log4j.Level;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Fichier organisant la configuration fondamentales du jeu et du log de la classe Game
 *
 * @author Maximilien Le Boiteux
 * @version 1.0
 */
public class Configuration extends LogClass {

    /**
     * Integer paramétrant le nombre de chiffres de la combinaison du jeu RecherchePlusMoins
     */
    public int nbcasesRech = 4;
    /**
     * Integer paramétrant le nombre d'essai du jeu RecherchePlusMoins
     */
    public int nbessaiRech = 10;
    /**
     * Integer paramétrant le nombre de chiffres de la combinaison du jeu MasterMind
     */
    public int nbcasesMasterM = 4;
    /**
     * Integer paramétrant le nombre d'essai du jeu MasterMind
     */
    public int nbessaiMasterM = 10;
    /**
     * Integer paramétrant le nombre de couleurs ou de chiffres disponibles
     */
    public int nbcouleurMasterM = 5;
    /**
     * Boolean configurant le jeu en mode développeur ou en mode normal
     */
    public boolean ModeDev = true;
    /**
     * Boolean configurant le jeu en mode développeur ou en mode normal
     */

    /**
     * Méthode paramétrant l'import du fichier de configuration config.properties
     */
    public Configuration() {
        log.log(Level.INFO, "INIT Chargement classe Game");
        try {
            ResourceBundle fichierpropriete = ResourceBundle.getBundle("main.resources.config"); // prop.properties
            this.nbcasesRech = Integer.parseInt(fichierpropriete.getString("Conf.RecherchePlusMoinsnbcasesRech"));
            this.nbessaiRech = Integer.parseInt(fichierpropriete.getString("Conf.RecherchePlusMoinsnbessaiRech"));
            this.nbcasesMasterM = Integer.parseInt(fichierpropriete.getString("Conf.MasterMindnbcasesMasterM"));
            this.nbessaiMasterM = Integer.parseInt(fichierpropriete.getString("Conf.MasterMindnbessaiMasterM"));
            this.nbcouleurMasterM = Integer.parseInt(fichierpropriete.getString("Conf.MasterMindnbcouleurMasterM"));
            this.ModeDev = Boolean.parseBoolean(fichierpropriete.getString("Conf.JeuxModeDev"));
        } catch (final MissingResourceException x) {
            log.log(Level.ERROR, "-------------Fichier introuvable : chargement de la configuration par défaut-------------");
        }
    }
}
