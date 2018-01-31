package main.java.mecaniqueDeJeux;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.MissingResourceException;

/**
 * Classe permettant d'enregistrer les logs et de les réorienter vers les fichiers Temp
 *
 * @author Maximilien Le Boiteux
 * @version 1.0
 */
public class LogClass {

    /**
     * variable de type Logger permettant d'enregistrer les logs
     */
    public Logger log;

    /**
     * Méthode permettant d'enregistrer les logs et de les réorienter vers les fichiers Temp
     */
    public LogClass() {
        try {
            org.apache.log4j.xml.DOMConfigurator.configure("src/main/resources/log4j.xml");
            this.log = Logger.getLogger(main.java.Configuration.class);
            final StringBuilder sb = new StringBuilder();
            sb.append("Fichier Jeu LOG-");
            SimpleDateFormat str = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
            sb.append(str.format(new Date()));
            final File file;
            try {
                file = File.createTempFile(sb.toString(), ".log");
                final PrintStream printStream = new PrintStream(file);
                System.setErr(printStream);
                log.log(Level.INFO, "Redirection Log vers fuchier Temp");
            } catch (IOException e) {
                System.out.println("-------------Création du fichier log4j impossible : pas de suivi des log-------------");
            }
        } catch (final MissingResourceException x) {
            System.out.println("-------------Configuration log4j impossible : pas de suivi des log-------------");
        }
    }
}
