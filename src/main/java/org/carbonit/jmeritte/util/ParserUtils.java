package org.carbonit.jmeritte.util;

import org.carbonit.jmeritte.exception.LCATException;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Character.isDigit;
import static java.lang.Character.isLetter;
import static org.carbonit.jmeritte.exception.LCATMessage.CAS_DEFINITION_NON_RECONNU;

public final class ParserUtils {

    private ParserUtils() {
    }

    public static List<Integer> recupererListeEntierDepuisLigneCase(String ligne) throws LCATException {
        var entiers = new ArrayList<Integer>();

        try {
            var index = 0;
            for (int i = 1; i < ligne.length(); i++, i += index) {
                index = 0;
                if (isDigit(ligne.charAt(i))) {
                    var nouveauChiffreBuilder = new StringBuilder().append(ligne.charAt(i));
                    for (int y = i + 1; y < ligne.length() && isDigit(ligne.charAt(y)); y++, index++) {
                        nouveauChiffreBuilder.append(ligne.charAt(y));
                    }
                    entiers.add(Integer.parseInt(nouveauChiffreBuilder.toString()));
                }
            }
        } catch (NumberFormatException e) {
            throw new LCATException(CAS_DEFINITION_NON_RECONNU);
        }

        return entiers;
    }

    public static List<String> recupererListeLettreDepuisLigneCase(String ligne) {
        var lettres = new ArrayList<String>();

        var index = 0;
        for (int i = 1; i < ligne.length(); i++, i += index) {
            index = 0;
            if (isLetter(ligne.charAt(i))) {
                var nouveauChiffreBuilder = new StringBuilder().append(ligne.charAt(i));
                for (int y = i + 1; y < ligne.length() && isLetter(ligne.charAt(y)); y++, index++) {
                    nouveauChiffreBuilder.append(ligne.charAt(y));
                }
                lettres.add(nouveauChiffreBuilder.toString());
            }
        }

        return lettres;
    }
}
