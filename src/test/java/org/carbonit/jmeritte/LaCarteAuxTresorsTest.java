package org.carbonit.jmeritte;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class LaCarteAuxTresorsTest {

    @Test
    void testLaCarteAuxTresors_casNominal_Ok() {

        var args = new String[]{"carte.txt"};
        Assertions.assertDoesNotThrow(() -> {
            new LaCarteAuxTresors(new String[]{Arrays.toString(args)});
        });
    }

    @Test
    void testMain_casNominal_Ok() {

        var args = new String[]{"carte.txt"};
        Assertions.assertDoesNotThrow(() -> {
            LaCarteAuxTresors.main(args);
        });
    }
}
