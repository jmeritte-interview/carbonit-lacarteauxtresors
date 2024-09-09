package org.carbonit.jmeritte.parser.impl;

import org.carbonit.jmeritte.exception.LCATException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.carbonit.jmeritte.exception.LCATMessage.*;

class MontagneParserTest {

    MontagneParser montagneParser;

    @BeforeEach
    void setUp() {
        montagneParser = new MontagneParser();
    }

    @Test
    void testParserDepuisLigne_casNominal_Ok() {
        var ligne = "M - 1 - 1";

        Assertions.assertDoesNotThrow(() -> montagneParser.parserDepuisLigne(ligne));
    }

    @Test
    void testParserDepuisLigne_mauvaisType_throwsLCATException() {
        var ligne = "C - 1 - 1";

        var exception = Assertions.assertThrows(LCATException.class, () -> montagneParser.parserDepuisLigne(ligne));

        Assertions.assertEquals(PARSER_MAUVAIS_ENTITE, exception.getMessage());
    }

    @Test
    void testParserDepuisLigne_mauvaisNombreCaracteres_throwsLCATException() {
        var ligne = "M - 1";

        var exception = Assertions.assertThrows(LCATException.class, () -> montagneParser.parserDepuisLigne(ligne));

        Assertions.assertEquals(PARSER_FORMAT_COURT, exception.getMessage());
    }

    @Test
    void testParserDepuisLigne_mauvaisCaracteres_throwsLCATException() {
        var ligne = "M - 1 - 1 - 1";

        var exception = Assertions.assertThrows(LCATException.class, () -> montagneParser.parserDepuisLigne(ligne));

        Assertions.assertEquals(PARSER_FORMAT_INCORRECT, exception.getMessage());
    }
}
