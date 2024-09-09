package org.carbonit.jmeritte.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CaseMontagneTest {

    @Test
    void testCaseMontagne_casNominal_Ok() {
        var caseMontagne = Assertions.assertDoesNotThrow(() -> new CaseMontagne(4, 4));
        Assertions.assertFalse(caseMontagne.isVisitable());
    }

    @Test
    void testRecupererDefinitionWriter_casNominal_Ok() {
        var caseMontagne = new CaseMontagne(4, 4).recupererDefinitionWriter();

        Assertions.assertEquals(3, caseMontagne.size());
        Assertions.assertEquals("M", caseMontagne.get(0));
        Assertions.assertEquals(4, caseMontagne.get(1));
        Assertions.assertEquals(4, caseMontagne.get(2));
    }

    @Test
    void testVisitable_GetterSetter_Ok() {
        var caseMontagne = new CaseMontagne(4, 4);

        caseMontagne.setVisitable(true);

        Assertions.assertTrue(caseMontagne.isVisitable());
    }
}
