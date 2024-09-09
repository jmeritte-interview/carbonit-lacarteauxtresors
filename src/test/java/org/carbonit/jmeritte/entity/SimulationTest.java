package org.carbonit.jmeritte.entity;

import org.carbonit.jmeritte.enumeration.MouvementEnum;
import org.carbonit.jmeritte.enumeration.OrientationEnum;
import org.carbonit.jmeritte.exception.LCATException;
import org.carbonit.jmeritte.exception.LCATMessage;
import org.carbonit.jmeritte.exception.LCATRuntimeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class SimulationTest {

    @Test
    void testSimulation_casNominal_Ok() throws LCATException {

        var carte = new Carte(2, 2);
        carte.attribuerCase(new CasePlaine(0, 0));
        carte.attribuerCase(new CasePlaine(0, 1));
        carte.attribuerCase(new CasePlaine(1, 0));
        carte.attribuerCase(new CasePlaine(1, 1));

        var listePersonnage = new ArrayList<Personnage>();
        listePersonnage.add(new Personnage(1, 1, "Test", OrientationEnum.N, List.of(MouvementEnum.G)));

        Assertions.assertDoesNotThrow(() -> {
            new Simulation(carte, listePersonnage);
        });
    }

    @Test
    void testSimulation_carteNull_throwsLCATException() {

        var listePersonnage = new ArrayList<Personnage>();
        listePersonnage.add(new Personnage(1, 1, "Test", OrientationEnum.N, List.of(MouvementEnum.G)));

        var exceptionNull = Assertions.assertThrows(LCATException.class, () -> {
            new Simulation(null, listePersonnage);
        });

        Assertions.assertEquals(LCATMessage.CARTE_NON_INITIALISEE, exceptionNull.getMessage());
    }

    @Test
    void testSimulation_carteCaseNonInit_throwsLCATException() throws LCATException {

        var carte = new Carte(2, 2);

        var listePersonnage = new ArrayList<Personnage>();
        listePersonnage.add(new Personnage(1, 1, "Test", OrientationEnum.N, List.of(MouvementEnum.G)));

        var exceptionNull = Assertions.assertThrows(LCATException.class, () -> {
            new Simulation(carte, listePersonnage);
        });

        Assertions.assertEquals(LCATMessage.CARTE_NON_INITIALISEE, exceptionNull.getMessage());
    }

    @Test
    void testSimulation_listePersonnageVide_throwsLCATException() throws LCATException {

        var carte = new Carte(2, 2);
        carte.attribuerCase(new CasePlaine(0, 0));
        carte.attribuerCase(new CasePlaine(0, 1));
        carte.attribuerCase(new CasePlaine(1, 0));
        carte.attribuerCase(new CasePlaine(1, 1));

        var listePersonnage = new ArrayList<Personnage>();

        var exceptionNull = Assertions.assertThrows(LCATException.class, () -> {
            new Simulation(carte, listePersonnage);
        });

        Assertions.assertEquals(LCATMessage.PERSONNAGE_AUCUN_PRET, exceptionNull.getMessage());
    }

    @Test
    void testSimulation_listePersonnageMalPositionne_throwsLCATRunTimeException() throws LCATException {

        var carte = new Carte(2, 2);
        carte.attribuerCase(new CasePlaine(0, 0));
        carte.attribuerCase(new CasePlaine(0, 1));
        carte.attribuerCase(new CasePlaine(1, 0));
        carte.attribuerCase(new CasePlaine(1, 1));

        var listePersonnage = new ArrayList<Personnage>();
        listePersonnage.add(new Personnage(2, 2, "Test", OrientationEnum.N, List.of(MouvementEnum.G)));


        var exceptionNull = Assertions.assertThrows(LCATRuntimeException.class, () -> {
            new Simulation(carte, listePersonnage);
        });

        Assertions.assertEquals(LCATMessage.COORDONNEES_HORS_CARTE, exceptionNull.getMessage());
    }

    @Test
    void testSimulation_listePersonnageSansMouvement_Ok() throws LCATException {

        var carte = new Carte(2, 2);
        carte.attribuerCase(new CasePlaine(0, 0));
        carte.attribuerCase(new CasePlaine(0, 1));
        carte.attribuerCase(new CasePlaine(1, 0));
        carte.attribuerCase(new CasePlaine(1, 1));

        var listePersonnage = new ArrayList<Personnage>();
        listePersonnage.add(new Personnage(1, 1, "Test", OrientationEnum.N, Collections.emptyList()));


        Assertions.assertDoesNotThrow(() -> {
            new Simulation(carte, listePersonnage);
        });
    }

    @Test
    void testSimulation_listePersonnageBougeHorsCarte_Ok() throws LCATException {

        var carte = new Carte(2, 2);
        carte.attribuerCase(new CasePlaine(0, 0));
        carte.attribuerCase(new CasePlaine(0, 1));
        carte.attribuerCase(new CasePlaine(1, 0));
        carte.attribuerCase(new CasePlaine(1, 1));

        var listePersonnage = new ArrayList<Personnage>();
        listePersonnage.add(new Personnage(0, 0, "Test", OrientationEnum.N, List.of(MouvementEnum.A)));

        Assertions.assertDoesNotThrow(() -> {
            new Simulation(carte, listePersonnage);
        });

    }

    @Test
    void testSimulation_listePersonnageBougeVersMontagne_Ok() throws LCATException {

        var carte = new Carte(2, 2);
        carte.attribuerCase(new CaseMontagne(0, 0));
        carte.attribuerCase(new CasePlaine(0, 1));
        carte.attribuerCase(new CasePlaine(1, 0));
        carte.attribuerCase(new CasePlaine(1, 1));

        var listePersonnage = new ArrayList<Personnage>();
        listePersonnage.add(new Personnage(0, 1, "Test", OrientationEnum.N, List.of(MouvementEnum.A)));


        Assertions.assertDoesNotThrow(() -> {
            new Simulation(carte, listePersonnage);
        });
    }

    @Test
    void testSimulation_listePersonnageBougeToutesDirections_Ok() throws LCATException {

        var carte = new Carte(2, 2);
        carte.attribuerCase(new CaseMontagne(0, 0));
        carte.attribuerCase(new CasePlaine(0, 1));
        carte.attribuerCase(new CasePlaine(1, 0));
        carte.attribuerCase(new CasePlaine(1, 1));

        var listePersonnage = new ArrayList<Personnage>();
        listePersonnage.add(new Personnage(0, 1, "Test", OrientationEnum.N, List.of(MouvementEnum.A, MouvementEnum.D, MouvementEnum.A, MouvementEnum.D, MouvementEnum.A, MouvementEnum.D, MouvementEnum.A, MouvementEnum.D)));
        listePersonnage.add(new Personnage(0, 1, "Test", OrientationEnum.O, List.of(MouvementEnum.A, MouvementEnum.G, MouvementEnum.A, MouvementEnum.G, MouvementEnum.A, MouvementEnum.G, MouvementEnum.A, MouvementEnum.G)));
        listePersonnage.add(new Personnage(0, 1, "Test", OrientationEnum.S, List.of(MouvementEnum.A, MouvementEnum.D, MouvementEnum.A, MouvementEnum.D, MouvementEnum.A, MouvementEnum.D, MouvementEnum.A, MouvementEnum.D)));
        listePersonnage.add(new Personnage(0, 1, "Test", OrientationEnum.E, List.of(MouvementEnum.A, MouvementEnum.D, MouvementEnum.A, MouvementEnum.D, MouvementEnum.A, MouvementEnum.D, MouvementEnum.A, MouvementEnum.D)));

        Assertions.assertDoesNotThrow(() -> {
            new Simulation(carte, listePersonnage);
        });
    }

    @ParameterizedTest
    @CsvSource({"5,1", "-1,1", "1,5", "1,-1"})
    void testSimulation_listePersonnageBougeToutesDirections_throwsLCATRuntimeException(int x, int y) throws LCATException {

        var carte = new Carte(2, 2);
        carte.attribuerCase(new CaseMontagne(0, 0));
        carte.attribuerCase(new CasePlaine(0, 1));
        carte.attribuerCase(new CasePlaine(1, 0));
        carte.attribuerCase(new CasePlaine(1, 1));

        var listePersonnage = new ArrayList<Personnage>();
        listePersonnage.add(new Personnage(x, y, "Test", OrientationEnum.N, List.of(MouvementEnum.A, MouvementEnum.D, MouvementEnum.A, MouvementEnum.D, MouvementEnum.A, MouvementEnum.D, MouvementEnum.A, MouvementEnum.D)));

        Assertions.assertThrows(LCATRuntimeException.class,() -> {
            new Simulation(carte, listePersonnage);
        });
    }
}
