package org.carbonit.jmeritte.parser.impl;

import org.carbonit.jmeritte.entity.CaseTresor;
import org.carbonit.jmeritte.enumeration.ParsingTypeEnum;
import org.carbonit.jmeritte.exception.LCATException;
import org.carbonit.jmeritte.parser.LigneParser;
import org.carbonit.jmeritte.util.ParserUtils;

import static org.carbonit.jmeritte.exception.LCATMessage.*;

public final class TresorParser implements LigneParser<CaseTresor> {

    @Override
    public CaseTresor parserDepuisLigne(String ligne) throws LCATException {

        if (!String.valueOf(ligne.charAt(0)).equals(ParsingTypeEnum.T.toString())) {
            throw new LCATException(PARSER_MAUVAIS_ENTITE);
        }

        ligne = ligne.replaceAll("\\s+", "");

        if (ligne.length() < 7) {
            throw new LCATException(PARSER_FORMAT_COURT);
        }

        var entiers = ParserUtils.recupererListeEntierDepuisLigneCase(ligne);

        if (entiers.size() != 3) {
            throw new LCATException(PARSER_FORMAT_INCORRECT);
        }

        return new CaseTresor(entiers.get(0), entiers.get(1), entiers.get(2));
    }
}
