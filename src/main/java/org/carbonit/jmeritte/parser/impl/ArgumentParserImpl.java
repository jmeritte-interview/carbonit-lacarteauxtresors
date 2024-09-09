package org.carbonit.jmeritte.parser.impl;

import org.carbonit.jmeritte.LaCarteAuxTresors;
import org.carbonit.jmeritte.exception.LCATException;
import org.carbonit.jmeritte.logger.LCATLogger;
import org.carbonit.jmeritte.parser.ArgumentParser;

import static org.carbonit.jmeritte.exception.LCATMessage.PROBLEME_NOMBRE_ARGUMENT_FICHIER;

public final class ArgumentParserImpl implements ArgumentParser {

    LCATLogger log = new LCATLogger(LaCarteAuxTresors.class.getName());

    @Override
    public String recupererCheminFichierDepuisListeArgument(String[] arguments) throws LCATException {
        log.info("Gestion des arguments du programme");

        if (arguments.length == 0) {
            throw new LCATException(PROBLEME_NOMBRE_ARGUMENT_FICHIER);
        }

        return arguments[0];
    }
}
