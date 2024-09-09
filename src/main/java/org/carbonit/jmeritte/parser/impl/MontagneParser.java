package org.carbonit.jmeritte.parser.impl;

import org.carbonit.jmeritte.entity.CaseMontagne;
import org.carbonit.jmeritte.enumeration.ParsingTypeEnum;
import org.carbonit.jmeritte.exception.LCATException;
import org.carbonit.jmeritte.parser.LigneParser;
import org.carbonit.jmeritte.util.ParserUtils;

import static org.carbonit.jmeritte.exception.LCATMessage.*;

public final class MontagneParser implements LigneParser<CaseMontagne> {

    @Override
    public CaseMontagne parserDepuisLigne(String ligne) throws LCATException {

        if (!String.valueOf(ligne.charAt(0)).equals(ParsingTypeEnum.M.toString())) {
            throw new LCATException(PARSER_MAUVAIS_ENTITE);
        }

        ligne = ligne.replaceAll("\\s+", "");

        if (ligne.length() < 5) {
            throw new LCATException(PARSER_FORMAT_COURT);
        }

        var entiers = ParserUtils.recupererListeEntierDepuisLigneCase(ligne);

        if (entiers.size() != 2) {
            throw new LCATException(PARSER_FORMAT_INCORRECT);
        }

        return new CaseMontagne(entiers.get(0), entiers.get(1));
    }
}
