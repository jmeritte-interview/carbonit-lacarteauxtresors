package org.carbonit.jmeritte.parser;

import org.carbonit.jmeritte.exception.LCATException;

public interface LigneParser<T> {

    /**
     * Initialise un objet depuis la ligne de fichier à parser
     *
     * @param ligne Ligne à parser
     * @return {T} Le type à renvoyer après parsing
     * @throws LCATException Remonte des erreurs de parsing et de format liés à l'initialisation de l'objet
     */
    T parserDepuisLigne(String ligne) throws LCATException;
}
