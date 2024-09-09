package org.carbonit.jmeritte.writer;

import org.carbonit.jmeritte.entity.Carte;
import org.carbonit.jmeritte.entity.Personnage;
import org.carbonit.jmeritte.enumeration.MouvementEnum;
import org.carbonit.jmeritte.enumeration.OrientationEnum;
import org.carbonit.jmeritte.exception.LCATException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.FileWriter;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class LCATWriterTest {

    Carte carte;
    List<Personnage> listePersonnage;

    @InjectMocks
    LCATWriter writer;

    @Mock
    FileWriter fileWriter;

    @BeforeEach
    void setUp() throws LCATException {
        carte = new Carte(2, 2);
        listePersonnage = List.of(new Personnage(1, 1, "Test", OrientationEnum.N, List.of(MouvementEnum.A)));
        writer = new LCATWriter(carte, listePersonnage);
    }

    @Test
    void testAfficherResultat_casNominal_Ok() {
        Assertions.assertDoesNotThrow(() -> new LCATWriter(carte, listePersonnage));
    }

    @Test
    void testCarteDuMonde_GetterSetter_Ok() throws LCATException {
        var nouvelleCarte = new Carte(3, 3);
        writer.setCarteDuMonde(nouvelleCarte);

        Assertions.assertEquals(nouvelleCarte, writer.getCarteDuMonde());
    }

    @Test
    void testListePersonnage_GetterSetter_Ok() {
        var nouvelleListe = List.of(new Personnage(2, 2, "Test2", OrientationEnum.N, List.of(MouvementEnum.A)));

        writer.setListePersonnage(nouvelleListe);

        Assertions.assertEquals(nouvelleListe, writer.getListePersonnage());
    }
}
