package main.java.mecaniqueDeJeux;

import main.java.configuration;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.MissingResourceException;

/**
 * Class to register the LogClass
 *
 * @author Maximilien Le Boiteux
 * @version 1.0
 */
public class LogClass {

    /**
     * Logger to register logs
     */
    public Logger log;

    /**
     * Method to register logs in the path src.log
     */
    public LogClass() {
        try {
            org.apache.log4j.xml.DOMConfigurator.configure("src/main/resources/log4j.xml");
            this.log = Logger.getLogger(configuration.class);
            final StringBuilder sb = new StringBuilder();
            sb.append("Fichier Jeu LOG-");
            SimpleDateFormat str = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
            sb.append(str.format(new Date()));
            try {
                java.io.File fichier = new java.io.File("src/log/"+sb+".log");
                fichier.createNewFile();
                final PrintStream printStream = new PrintStream(fichier);
                System.setErr(printStream);
                log.log(Level.INFO, "Redirection Log vers fichier src/log/"+sb+".log");
            } catch (IOException e) {
                System.out.println("-------------Création du fichier log4j impossible : pas de suivi des log-------------");
            }
        } catch (final MissingResourceException x) {
            System.out.println("-------------configuration log4j impossible : pas de suivi des log-------------");
        }
    }
}
