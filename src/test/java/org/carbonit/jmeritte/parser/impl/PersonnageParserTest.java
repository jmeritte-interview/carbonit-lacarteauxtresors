package org.carbonit.jmeritte.parser.impl;

import org.carbonit.jmeritte.exception.LCATException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.carbonit.jmeritte.exception.LCATMessage.*;

class PersonnageParserTest {

    PersonnageParser personnageParser;

    @BeforeEach
    void setUp() {
        personnageParser = new PersonnageParser();
    }

    @Test
    void testParserDepuisLigne_casNominal_Ok() {
        var ligne = "A - Laurent- 1 - 1 - S - AADADAGGA";

        Assertions.assertDoesNotThrow(() -> personnageParser.parserDepuisLigne(ligne));
    }

    @Test
    void testParserDepuisLigne_mauvaisType_throwsLCATException() {
        var ligne = "C - Laurent- 1 - 1 - S - AADADAGGA";

        var exception = Assertions.assertThrows(LCATException.class, () -> personnageParser.parserDepuisLigne(ligne));

        Assertions.assertEquals(PARSER_MAUVAIS_ENTITE, exception.getMessage());
    }

    @Test
    void testParserDepuisLigne_mauvaisNombreCaracteres_throwsLCATException() {
        var ligne = "A - L - 1 - 1 - A";

        var exception = Assertions.assertThrows(LCATException.class, () -> personnageParser.parserDepuisLigne(ligne));

        Assertions.assertEquals(PARSER_FORMAT_COURT, exception.getMessage());
    }

    @Test
    void testParserDepuisLigne_mauvaisCaracteresChiffre_throwsLCATException() {
        var ligne = "A - Laurent- 1 - A - S - AADADAGGA";

        var exception = Assertions.assertThrows(LCATException.class, () -> personnageParser.parserDepuisLigne(ligne));

        Assertions.assertEquals(PARSER_FORMAT_INCORRECT, exception.getMessage());
    }

    @Test
    void testParserDepuisLigne_mauvaisCaracteresLettre_throwsLCATException() {
        var ligne = "A - Laurent- 1 - 1 - S -";

        var exception = Assertions.assertThrows(LCATException.class, () -> personnageParser.parserDepuisLigne(ligne));

        Assertions.assertEquals(PARSER_FORMAT_INCORRECT, exception.getMessage());
    }

    @Test
    void testParserDepuisLigne_mauvaisMouvement_throwsLCATException() {
        var ligne = "A - Laurent- 1 - 1 - S - AADADAGGF";

        var exception = Assertions.assertThrows(LCATException.class, () -> personnageParser.parserDepuisLigne(ligne));

        Assertions.assertEquals(PARSER_VALEUR_NON_RECONNUE, exception.getMessage());
    }
}
