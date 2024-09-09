package org.carbonit.jmeritte.entity;

import org.carbonit.jmeritte.exception.LCATException;
import org.carbonit.jmeritte.exception.LCATMessage;
import org.carbonit.jmeritte.exception.LCATRuntimeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

class CarteTest {

    @Test
    void testCarte_casNominal_Ok() {
        Assertions.assertDoesNotThrow(() -> new Carte(4, 4));
    }

    @Test
    void testCarte_casMauvaiseLargeur_Ok() {
        var exception = Assertions.assertThrows(LCATException.class, () -> new Carte(0, 1));

        Assertions.assertEquals(LCATMessage.CARTE_DONNEES_ILLOGIQUES, exception.getMessage());
    }

    @Test
    void testCarte_casMauvaiseLongueur_Ok() {
        var exception = Assertions.assertThrows(LCATException.class, () -> new Carte(1, 0));

        Assertions.assertEquals(LCATMessage.CARTE_DONNEES_ILLOGIQUES, exception.getMessage());
    }

    @Test
    void testAttribuerCaseSpeciale_casMontagne_Ok() throws LCATException {
        var listeMontage = List.of(new CaseMontagne(0, 0), new CaseMontagne(1, 1));

        var carte = new Carte(2, 2);

        Assertions.assertDoesNotThrow(() -> carte.attribuerCaseSpeciale(listeMontage));

        Assertions.assertInstanceOf(CaseMontagne.class, carte.getCoordonnees()[0][0]);
        Assertions.assertNull(carte.getCoordonnees()[0][1]);
        Assertions.assertNull(carte.getCoordonnees()[1][0]);
        Assertions.assertInstanceOf(CaseMontagne.class, carte.getCoordonnees()[1][1]);
    }

    @ParameterizedTest
    @CsvSource({"5,1", "-1,1", "1,5", "1,-1"})
    void testAttribuerCaseSpeciale_casCaseIncorrecte_throwsLCATRuntimeException(int x, int y) throws LCATException {
        var listeMontage = List.of(new CaseMontagne(x, y));

        var carte = new Carte(2, 2);

        var exception = Assertions.assertThrows(LCATRuntimeException.class, () -> carte.attribuerCaseSpeciale(listeMontage));

        Assertions.assertEquals(LCATMessage.COORDONNEES_HORS_CARTE, exception.getMessage());
    }

    @Test
    void testAttribuerCaseSpeciale_casCaseDejaAttribuee_throwsLCATRuntimeException() throws LCATException {
        var listeMontage = List.of(new CaseMontagne(0, 0), new CaseMontagne(0, 0));

        var carte = new Carte(2, 2);

        var exception = Assertions.assertThrows(LCATRuntimeException.class, () -> carte.attribuerCaseSpeciale(listeMontage));

        Assertions.assertEquals(LCATMessage.CARTE_CASE_DEJA_ATTRIBUEE, exception.getMessage());
    }

    @Test
    void testComblerCasesRestantes_casNominal_Ok() throws LCATException {
        var listeMontage = List.of(new CaseMontagne(0, 0), new CaseMontagne(1, 1));

        var carte = new Carte(2, 2);

        carte.attribuerCaseSpeciale(listeMontage);

        Assertions.assertDoesNotThrow(carte::comblerCasesRestantes);

        Assertions.assertInstanceOf(CaseMontagne.class, carte.getCoordonnees()[0][0]);
        Assertions.assertInstanceOf(CasePlaine.class, carte.getCoordonnees()[0][1]);
        Assertions.assertInstanceOf(CasePlaine.class, carte.getCoordonnees()[1][0]);
        Assertions.assertInstanceOf(CaseMontagne.class, carte.getCoordonnees()[1][1]);
    }

    @Test
    void testRecupererDefinitionWriter_casNominal_Ok() throws LCATException {
        var carte = new Carte(2, 2);

        var definition = carte.recupererDefinitionWriter();

        Assertions.assertEquals(3, definition.size());
        Assertions.assertEquals("C", definition.get(0));
        Assertions.assertEquals(2, definition.get(1));
        Assertions.assertEquals(2, definition.get(2));
    }
}
