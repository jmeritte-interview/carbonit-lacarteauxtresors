package org.carbonit.jmeritte.parser.impl;

import org.carbonit.jmeritte.exception.LCATException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ArgumentParserImplTest {

    ArgumentParserImpl argumentParserImpl;

    @BeforeEach
    void setUp() {
        argumentParserImpl = new ArgumentParserImpl();
    }

    @Test
    void testRecupererCheminFichierDepuisListeArgument_casNominal_Ok() {
        var arguments = new String[]{"carte.txt"};

        Assertions.assertDoesNotThrow(() -> argumentParserImpl.recupererCheminFichierDepuisListeArgument(arguments));
    }

    @Test
    void testRecupererCheminFichierDepuisListeArgument_aucunArgument_throwLCATException() {
        var arguments = new String[]{};

        Assertions.assertThrows(LCATException.class,
                () -> argumentParserImpl.recupererCheminFichierDepuisListeArgument(arguments));
    }
}
