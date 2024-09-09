package org.carbonit.jmeritte.entity;

import org.carbonit.jmeritte.enumeration.MouvementEnum;
import org.carbonit.jmeritte.enumeration.OrientationEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.List;
import java.util.Map;

class PersonnageTest {

    @Test
    void testPersonnage_casNominal_Ok() {
        var personnage = Assertions.assertDoesNotThrow(() -> new Personnage(1, 2, "Test", OrientationEnum.N, List.of(MouvementEnum.A)));

        Assertions.assertEquals(1, personnage.getX());
        Assertions.assertEquals(2, personnage.getY());
        Assertions.assertEquals("Test", personnage.getNom());
        Assertions.assertEquals(OrientationEnum.N, personnage.getOrientation());
        Assertions.assertEquals(List.of(MouvementEnum.A), personnage.getMouvements());
        Assertions.assertEquals(-1, personnage.getxPrecedent());
        Assertions.assertEquals(-1, personnage.getyPrecedent());
    }

    @Test
    void testAcquerirTresor_casNominal_Ok() {
        var personnage = new Personnage(1, 2, "Test", OrientationEnum.N, List.of(MouvementEnum.A));

        Assertions.assertDoesNotThrow(() -> personnage.acquerirTresor());

        Assertions.assertEquals(1, personnage.getNombreTresorsCollectes());
    }

    @Test
    void testCalculerProchainePosition_avancerNord_Ok() {
        var personnage = new Personnage(1, 2, "Test", OrientationEnum.N, List.of(MouvementEnum.A));

        var prochainePosition = Assertions.assertDoesNotThrow(() -> personnage.calculerProchainePosition(MouvementEnum.A, 1, 2));

        Assertions.assertEquals(1, prochainePosition.getKey());
        Assertions.assertEquals(1, prochainePosition.getValue());
    }

    @Test
    void testCalculerProchainePosition_rotationDroite_Ok() {
        var personnage = new Personnage(1, 2, "Test", OrientationEnum.N, List.of(MouvementEnum.A));

        var prochainePosition = Assertions.assertDoesNotThrow(() -> personnage.calculerProchainePosition(MouvementEnum.D, 1, 2));

        Assertions.assertEquals(1, prochainePosition.getKey());
        Assertions.assertEquals(2, prochainePosition.getValue());
        Assertions.assertEquals(OrientationEnum.N, personnage.getOrientation());
    }

    @Test
    void testCalculerProchainePosition_rotationGauche_Ok() {
        var personnage = new Personnage(1, 2, "Test", OrientationEnum.N, List.of(MouvementEnum.A));

        var prochainePosition = Assertions.assertDoesNotThrow(() -> personnage.calculerProchainePosition(MouvementEnum.G, 1, 2));

        Assertions.assertEquals(1, prochainePosition.getKey());
        Assertions.assertEquals(2, prochainePosition.getValue());
        Assertions.assertEquals(OrientationEnum.N, personnage.getOrientation());
    }

    @ParameterizedTest
    @EnumSource
    void testEffectuerProchainPosition_avancer_Ok(OrientationEnum orientationEnum) {
        var xMap = recupererPositionXSelonOrientation();
        var yMap = recupererPositionYSelonOrientation();

        var personnage = new Personnage(1, 1, "Test", orientationEnum, List.of(MouvementEnum.A));

        Assertions.assertDoesNotThrow(() -> personnage.effectuerProchainPosition(MouvementEnum.A, 1, 1));

        Assertions.assertEquals(xMap.get(orientationEnum), personnage.getX());
        Assertions.assertEquals(yMap.get(orientationEnum), personnage.getY());
    }

    @ParameterizedTest
    @EnumSource
    void testEffectuerProchainPosition_tournerDroite_Ok(OrientationEnum orientationEnum) {
        var orientationMap = recupererOrientationTournerDroite();

        var personnage = new Personnage(1, 2, "Test", orientationEnum, List.of(MouvementEnum.A));

        Assertions.assertDoesNotThrow(() -> personnage.effectuerProchainPosition(MouvementEnum.D, 1, 2));

        Assertions.assertEquals(1, personnage.getX());
        Assertions.assertEquals(2, personnage.getY());
        Assertions.assertEquals(orientationMap.get(orientationEnum), personnage.getOrientation());
    }

    @ParameterizedTest
    @EnumSource
    void testEffectuerProchainPosition_tournerGauche_Ok(OrientationEnum orientationEnum) {
        var orientationMap = recupererOrientationTournerGauche();

        var personnage = new Personnage(1, 2, "Test", orientationEnum, List.of(MouvementEnum.A));

        Assertions.assertDoesNotThrow(() -> personnage.effectuerProchainPosition(MouvementEnum.G, 1, 2));

        Assertions.assertEquals(1, personnage.getX());
        Assertions.assertEquals(2, personnage.getY());
        Assertions.assertEquals(orientationMap.get(orientationEnum), personnage.getOrientation());
    }

    @Test
    void testRecupererDefinitionWriter_casNominal_Ok() {
        var personnage = new Personnage(1, 2, "Test", OrientationEnum.N, List.of(MouvementEnum.A));

        var definitionWriter = Assertions.assertDoesNotThrow(() -> personnage.recupererDefinitionWriter());

        Assertions.assertEquals(6, definitionWriter.size());
        Assertions.assertEquals("A", definitionWriter.get(0));
        Assertions.assertEquals("Test", definitionWriter.get(1));
        Assertions.assertEquals(1, definitionWriter.get(2));
        Assertions.assertEquals(2, definitionWriter.get(3));
        Assertions.assertEquals(OrientationEnum.N.name(), definitionWriter.get(4));
        Assertions.assertEquals(0, definitionWriter.get(5));
    }

    @Test
    void testX_getterSetter_Ok() {
        var personnage = new Personnage(1, 1, "Test", OrientationEnum.N, List.of(MouvementEnum.A));

        personnage.setX(2);
        var x = personnage.getX();

        Assertions.assertEquals(2, x);
    }

    @Test
    void testY_getterSetter_Ok() {
        var personnage = new Personnage(1, 1, "Test", OrientationEnum.N, List.of(MouvementEnum.A));

        personnage.setY(2);
        var y = personnage.getY();

        Assertions.assertEquals(2, y);
    }

    @Test
    void testOrdre_getterSetter_Ok() {
        var personnage = new Personnage(1, 1, "Test", OrientationEnum.N, List.of(MouvementEnum.A));

        personnage.setOrdre(1);
        var ordre = personnage.getOrdre();

        Assertions.assertEquals(1, ordre);
    }

    @Test
    void testNom_getterSetter_Ok() {
        var personnage = new Personnage(1, 1, "Test", OrientationEnum.N, List.of(MouvementEnum.A));

        personnage.setNom("Test2");
        var nom = personnage.getNom();

        Assertions.assertEquals("Test2", nom);
    }

    @Test
    void testOrientation_getterSetter_Ok() {
        var personnage = new Personnage(1, 1, "Test", OrientationEnum.N, List.of(MouvementEnum.A));

        personnage.setOrientation(OrientationEnum.E);
        var orientation = personnage.getOrientation();

        Assertions.assertEquals(OrientationEnum.E, orientation);
    }

    @Test
    void testMouvements_getterSetter_Ok() {
        var personnage = new Personnage(1, 1, "Test", OrientationEnum.N, List.of(MouvementEnum.A));

        personnage.setMouvements(List.of(MouvementEnum.D));
        var mouvements = personnage.getMouvements();

        Assertions.assertEquals(List.of(MouvementEnum.D), mouvements);
    }

    @Test
    void testNombreTresorCollecte_getterSetter_Ok() {
        var personnage = new Personnage(1, 1, "Test", OrientationEnum.N, List.of(MouvementEnum.A));

        personnage.setNombreTresorsCollectes(6);
        var nombreTresorsCollectes = personnage.getNombreTresorsCollectes();

        Assertions.assertEquals(6, nombreTresorsCollectes);
    }

    static Map<OrientationEnum, OrientationEnum> recupererOrientationTournerDroite() {
        return Map.ofEntries(Map.entry(OrientationEnum.N, OrientationEnum.E),
                Map.entry(OrientationEnum.E, OrientationEnum.S),
                Map.entry(OrientationEnum.S, OrientationEnum.O),
                Map.entry(OrientationEnum.O, OrientationEnum.N));
    }

    static Map<OrientationEnum, OrientationEnum> recupererOrientationTournerGauche() {
        return Map.ofEntries(Map.entry(OrientationEnum.N, OrientationEnum.O),
                Map.entry(OrientationEnum.O, OrientationEnum.S),
                Map.entry(OrientationEnum.S, OrientationEnum.E),
                Map.entry(OrientationEnum.E, OrientationEnum.N));
    }

    static Map<OrientationEnum, Integer> recupererPositionXSelonOrientation() {
        return Map.ofEntries(Map.entry(OrientationEnum.N, 1),
                Map.entry(OrientationEnum.E, 2),
                Map.entry(OrientationEnum.S, 1),
                Map.entry(OrientationEnum.O, 0));
    }

    static Map<OrientationEnum, Integer> recupererPositionYSelonOrientation() {
        return Map.ofEntries(Map.entry(OrientationEnum.N, 0),
                Map.entry(OrientationEnum.E, 1),
                Map.entry(OrientationEnum.S, 2),
                Map.entry(OrientationEnum.O, 1));
    }
}
