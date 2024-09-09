package org.carbonit.jmeritte.reader;

import org.carbonit.jmeritte.entity.Carte;
import org.carbonit.jmeritte.entity.Personnage;
import org.carbonit.jmeritte.enumeration.MouvementEnum;
import org.carbonit.jmeritte.enumeration.OrientationEnum;
import org.carbonit.jmeritte.exception.LCATException;
import org.carbonit.jmeritte.exception.LCATMessage;
import org.carbonit.jmeritte.exception.LCATRuntimeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

class LCATReaderTest {

    @Test
    void testLCATReader_casNominal_Ok() {
        Assertions.assertDoesNotThrow(() -> {
            new LCATReader();
        });
    }

    @Test
    void testGenererCarteDepuisFichier_nomFichierNull_throwsLCATException() {
        var exceptionNull = Assertions.assertThrows(LCATException.class, () -> {
            new LCATReader().genererCarteDepuisFichier(null);
        });

        Assertions.assertEquals(LCATMessage.IO_FICHIER_INCONNU, exceptionNull.getMessage());
    }

    @Test
    void testGenererCarteDepuisFichier_nomFichierEmpty_throwsLCATException() {
        var exceptionEmpty = Assertions.assertThrows(LCATException.class, () -> {
            new LCATReader().genererCarteDepuisFichier("");
        });

        Assertions.assertEquals(LCATMessage.IO_FICHIER_INCONNU, exceptionEmpty.getMessage());
    }


    @Test
    void testGenererCarteDepuisFichier_mauvaisFichier_throwsLCATRunTimeException() {
        var reader = new LCATReader();

        var exceptionNull = Assertions.assertThrows(LCATRuntimeException.class, () -> {
            reader.genererCarteDepuisFichier("carte.text");
        });

        Assertions.assertEquals(LCATMessage.PROBLEME_LECTURE_FICHIER, exceptionNull.getMessage());
    }

    @Test
    void testGenererCarteDepuisFichier_fichierLigneIncorrect_throwsLCATException() {
        var exceptionNull = Assertions.assertThrows(LCATException.class, () -> {
            new LCATReader().genererCarteDepuisFichier("carte-mauvaise-ligne.txt");
        });

        Assertions.assertEquals(LCATMessage.FICHIER_LIGNE_NON_RECONNUE, exceptionNull.getMessage());
    }

    @Test
    void testGenererCarteDepuisFichier_carteNull_throwsLCATException() {

        var exceptionNull = Assertions.assertThrows(LCATException.class, () -> {
            new LCATReader().genererCarteDepuisFichier("carte-sans-carte.txt");
        });

        Assertions.assertEquals(LCATMessage.CARTE_NON_INITIALISEE, exceptionNull.getMessage());
    }

    @Test
    void testGenererCarteDepuisFichier_carteMauvaiseLargeur_throwsLCATException() {

        var exceptionNull = Assertions.assertThrows(LCATException.class, () -> {
            new LCATReader().genererCarteDepuisFichier("carte-mauvaise-largeur.txt");
        });

        Assertions.assertEquals(LCATMessage.CARTE_DONNEES_INCOMPATIBLES, exceptionNull.getMessage());
    }

    @Test
    void testGenererCarteDepuisFichier_carteMauvaiseLongueur_throwsLCATException() {

        var exceptionNull = Assertions.assertThrows(LCATException.class, () -> {
            new LCATReader().genererCarteDepuisFichier("carte-mauvaise-longueur.txt");
        });

        Assertions.assertEquals(LCATMessage.CARTE_DONNEES_INCOMPATIBLES, exceptionNull.getMessage());
    }

    @Test
    void testGenererCarteDepuisFichier_carteFormatCarteIncorrect_throwsLCATException() {

        var exceptionNull = Assertions.assertThrows(LCATException.class, () -> {
            new LCATReader().genererCarteDepuisFichier("carte-format-carte-incorrect.txt");
        });

        Assertions.assertEquals(LCATMessage.CAS_DEFINITION_NON_RECONNU, exceptionNull.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"carte-largeur-0.txt", "carte-longueur-0.txt", "carte-largeur-longueur-1.txt"})
    void testGenererCarteDepuisFichier_carteTailleIllogique_throwsLCATException() {

        var exceptionNull = Assertions.assertThrows(LCATException.class, () -> {
            new LCATReader().genererCarteDepuisFichier("carte-largeur-0.txt");
        });

        Assertions.assertEquals(LCATMessage.CARTE_DONNEES_ILLOGIQUES, exceptionNull.getMessage());
    }

    @Test
    void testCarteDuMonde_GetterSetter_Ok() throws LCATException {

        var reader = new LCATReader();
        reader.genererCarteDepuisFichier("carte.txt");
        var carte = new Carte(10, 10);

        reader.setCarteDuMonde(carte);

        Assertions.assertEquals(carte, reader.getCarteDuMonde());
    }

    @Test
    void testListePersonnage_GetterSetter_Ok() throws LCATException {

        var reader = new LCATReader();
        reader.genererCarteDepuisFichier("carte.txt");

        var liste = new ArrayList<Personnage>();
        liste.add(new Personnage(1, 2, "Test", OrientationEnum.O, List.of(MouvementEnum.A)));

        reader.setListePersonnage(liste);

        Assertions.assertEquals(liste, reader.getListePersonnage());
    }
}
