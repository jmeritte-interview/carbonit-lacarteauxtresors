package org.carbonit.jmeritte.parser.impl;

import org.carbonit.jmeritte.entity.Personnage;
import org.carbonit.jmeritte.enumeration.MouvementEnum;
import org.carbonit.jmeritte.enumeration.OrientationEnum;
import org.carbonit.jmeritte.enumeration.ParsingTypeEnum;
import org.carbonit.jmeritte.exception.LCATException;
import org.carbonit.jmeritte.parser.LigneParser;
import org.carbonit.jmeritte.util.ParserUtils;

import java.util.ArrayList;

import static org.carbonit.jmeritte.exception.LCATMessage.*;

public final class PersonnageParser implements LigneParser<Personnage> {

    @Override
    public Personnage parserDepuisLigne(String ligne) throws LCATException {

        if (!String.valueOf(ligne.charAt(0)).equals(ParsingTypeEnum.A.toString())) {
            throw new LCATException(PARSER_MAUVAIS_ENTITE);
        }

        ligne = ligne.replaceAll("\\s+", "");

        if (ligne.length() < 11) {
            throw new LCATException(PARSER_FORMAT_COURT);
        }

        var listeEntier = ParserUtils.recupererListeEntierDepuisLigneCase(ligne);

        if (listeEntier.size() != 2) {
            throw new LCATException(PARSER_FORMAT_INCORRECT);
        }

        var listeLettre = ParserUtils.recupererListeLettreDepuisLigneCase(ligne);

        if (listeLettre.size() != 3) {
            throw new LCATException(PARSER_FORMAT_INCORRECT);
        }

        OrientationEnum orientation;

        ArrayList<MouvementEnum> listeMouvements;

        try {
            orientation = OrientationEnum.valueOf(listeLettre.get(1));
            var chaineCaractereMouvementBrute = listeLettre.get(2);
            listeMouvements = new ArrayList<>(chaineCaractereMouvementBrute.chars()
                    .mapToObj(c -> MouvementEnum.valueOf(String.valueOf((char) c)))
                    .toList());
        } catch (IllegalArgumentException e) {
            throw new LCATException(PARSER_VALEUR_NON_RECONNUE);
        }

        return new Personnage(listeEntier.get(0), listeEntier.get(1), listeLettre.get(0), orientation, listeMouvements);
    }
}
