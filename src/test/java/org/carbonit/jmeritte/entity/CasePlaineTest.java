package org.carbonit.jmeritte.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CasePlaineTest {

    @Test
    void testCasePlaine_casNominal_Ok() {
        var caseMontagne = Assertions.assertDoesNotThrow(() -> new CasePlaine(4, 4));
        Assertions.assertTrue(caseMontagne.isVisitable());
        Assertions.assertEquals(4, caseMontagne.getX());
        Assertions.assertEquals(4, caseMontagne.getY());
    }
}
