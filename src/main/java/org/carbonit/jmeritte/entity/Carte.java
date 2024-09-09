package org.carbonit.jmeritte.entity;

import org.carbonit.jmeritte.exception.LCATException;
import org.carbonit.jmeritte.exception.LCATRuntimeException;
import org.carbonit.jmeritte.writer.Writable;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.Objects.isNull;
import static org.carbonit.jmeritte.exception.LCATMessage.*;

public class Carte implements Writable {

    Case[][] coordonnees;

    public Carte(int largeur, int longueur) throws LCATException {
        if (largeur > 0 && longueur > 0) {
            this.coordonnees = new Case[largeur][longueur];
        } else {
            throw new LCATException(CARTE_DONNEES_ILLOGIQUES);
        }
    }

    public void attribuerCaseSpeciale(List<? extends Case> cases) {
        cases.forEach(c -> {
            if (c.getX() > coordonnees.length - 1 || c.getX() < 0
                    || c.getY() > coordonnees[0].length - 1 || c.getY() < 0) {
                throw new LCATRuntimeException(COORDONNEES_HORS_CARTE);
            }
            if (isNull(recupererCoordonnees(c.getX(), c.getY()))) {
                attribuerCase(c);
            } else {
                throw new LCATRuntimeException(CARTE_CASE_DEJA_ATTRIBUEE);
            }
        });
    }

    public void comblerCasesRestantes() {
        IntStream.range(0, coordonnees.length)
                .forEach(i -> IntStream.range(0, coordonnees[i].length)
                        .filter(y -> isNull(coordonnees[i][y]))
                        .forEach(y -> attribuerCase(new CasePlaine(i, y)))
                );
    }

    public Case recupererCoordonnees(int x, int y) {
        return coordonnees[x][y];
    }

    public void attribuerCase(Case caseAttribution) {
        coordonnees[caseAttribution.getX()][caseAttribution.getY()] = caseAttribution;
    }

    public Case[][] getCoordonnees() {
        return coordonnees;
    }

    @Override
    public List<Object> recupererDefinitionWriter() {
        return List.of("C", coordonnees.length, coordonnees[0].length);
    }
}
