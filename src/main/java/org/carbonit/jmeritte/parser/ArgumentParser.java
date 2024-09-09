package org.carbonit.jmeritte.parser;

import org.carbonit.jmeritte.exception.LCATException;

public interface ArgumentParser {

    /**
     * Recupere Le Chemin du fichier à parser depuis les arguments du programme
     *
     * @param arguments Arguments reçu par la fonction main()
     * @return {String} Le chemin a renvoyer
     * @throws LCATException Remonte une erreur lors du parsing des arguments ou liée au nombre d'arguments
     */
    String recupererCheminFichierDepuisListeArgument(String[] arguments) throws LCATException;
}
