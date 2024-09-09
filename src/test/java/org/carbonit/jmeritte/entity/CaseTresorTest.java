package org.carbonit.jmeritte.entity;

import org.carbonit.jmeritte.enumeration.MouvementEnum;
import org.carbonit.jmeritte.enumeration.OrientationEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class CaseTresorTest {

    @Test
    void testCaseTresor_casNominal_Ok() {
        var caseTresor = Assertions.assertDoesNotThrow(() -> new CaseTresor(4, 5, 3));

        Assertions.assertEquals(4, caseTresor.getX());
        Assertions.assertEquals(5, caseTresor.getY());
        Assertions.assertEquals(3, caseTresor.getNombreTresorRestant());
    }

    @Test
    void testActiverEffetCaseSurPersonnage_casNominal_Ok() {

        var personnage = new Personnage(2, 2, "Test", OrientationEnum.O, List.of(MouvementEnum.A));

        var caseTresor = new CaseTresor(1, 1, 1);

        caseTresor.activerEffetCaseSurPersonnage(personnage);

        Assertions.assertEquals(0, caseTresor.getNombreTresorRestant());
        Assertions.assertEquals(1, personnage.getNombreTresorsCollectes());

        caseTresor.activerEffetCaseSurPersonnage(personnage);

        Assertions.assertEquals(0, caseTresor.getNombreTresorRestant());
        Assertions.assertEquals(1, personnage.getNombreTresorsCollectes());
    }

    @Test
    void testActiverEffetCaseSurPersonnage_positionPrecedenteEgale_Ok() {

        var personnage = new Personnage(2, 2, "Test", OrientationEnum.O, List.of(MouvementEnum.A));

        personnage.setxPrecedent(1);
        personnage.setyPrecedent(1);

        var caseTresor = new CaseTresor(1, 1, 1);

        caseTresor.activerEffetCaseSurPersonnage(personnage);

        Assertions.assertEquals(1, caseTresor.getNombreTresorRestant());
        Assertions.assertEquals(0, personnage.getNombreTresorsCollectes());
    }

    @Test
    void testRecupererDefinitionWriter_casNominal_Ok() {
        var definitionTresor = new CaseTresor(1, 2, 3).recupererDefinitionWriter();

        Assertions.assertEquals(4, definitionTresor.size());
        Assertions.assertEquals("T", definitionTresor.get(0));
        Assertions.assertEquals(1, definitionTresor.get(1));
        Assertions.assertEquals(2, definitionTresor.get(2));
        Assertions.assertEquals(3, definitionTresor.get(3));
    }

    @Test
    void testRecupererDefinitionWriter_aucunTresorRestant_returnListVide() {
        var definitionTresor = new CaseTresor(1, 2, 0).recupererDefinitionWriter();

        Assertions.assertEquals(0, definitionTresor.size());
    }
}
