package org.carbonit.jmeritte.entity;

import org.carbonit.jmeritte.enumeration.MouvementEnum;
import org.carbonit.jmeritte.enumeration.OrientationEnum;
import org.carbonit.jmeritte.exception.LCATException;
import org.carbonit.jmeritte.writer.Writable;

import java.util.AbstractMap;
import java.util.List;

import static org.carbonit.jmeritte.exception.LCATMessage.CAS_DEFINITION_NON_RECONNU;

public class Personnage implements Writable {

    int x;
    int y;
    int ordre = 1;

    int xPrecedent = -1;
    int yPrecedent = -1;

    String nom;
    OrientationEnum orientation;
    List<MouvementEnum> mouvements;

    int nombreTresorsCollectes = 0;

    public Personnage(int x, int y, String nom, OrientationEnum orientation, List<MouvementEnum> mouvements) {
        this.nom = nom;
        this.x = x;
        this.y = y;
        this.orientation = orientation;
        this.mouvements = mouvements;
    }

    public void acquerirTresor() {
        nombreTresorsCollectes++;
    }

    public AbstractMap.SimpleEntry<Integer, Integer> calculerProchainePosition(MouvementEnum mouvementEnum, int ancienX, int ancienY) throws LCATException {
        return switch (mouvementEnum) {
            case MouvementEnum.A -> calculerAvancerDepuisPosition(ancienX, ancienY);
            case MouvementEnum.D, MouvementEnum.G -> new AbstractMap.SimpleEntry<>(ancienX, ancienY);
        };
    }

    public void effectuerProchainPosition(MouvementEnum mouvementEnum, int ancienX, int ancienY) throws LCATException {
        switch (mouvementEnum) {
            case MouvementEnum.A -> avancerDepuisPosition(ancienX, ancienY);
            case MouvementEnum.D -> tournerDroite();
            case MouvementEnum.G -> tournerGauche();
        }
    }

    private void tournerGauche() {
        switch (orientation) {
            case OrientationEnum.N -> orientation = OrientationEnum.O;
            case OrientationEnum.E -> orientation = OrientationEnum.N;
            case OrientationEnum.S -> orientation = OrientationEnum.E;
            case OrientationEnum.O -> orientation = OrientationEnum.S;
        }
        xPrecedent = x;
        yPrecedent = y;
    }

    private void tournerDroite() {
        switch (orientation) {
            case OrientationEnum.N -> orientation = OrientationEnum.E;
            case OrientationEnum.E -> orientation = OrientationEnum.S;
            case OrientationEnum.S -> orientation = OrientationEnum.O;
            case OrientationEnum.O -> orientation = OrientationEnum.N;
        }
        xPrecedent = x;
        yPrecedent = y;
    }

    private AbstractMap.SimpleEntry<Integer, Integer> calculerAvancerDepuisPosition(int ancienX, int ancienY) throws LCATException {
        switch (orientation) {
            case OrientationEnum.N -> {
                return new AbstractMap.SimpleEntry<>(ancienX, ancienY - 1);
            }
            case OrientationEnum.E -> {
                return new AbstractMap.SimpleEntry<>(ancienX + 1, ancienY);
            }
            case OrientationEnum.S -> {
                return new AbstractMap.SimpleEntry<>(ancienX, ancienY + 1);
            }
            case OrientationEnum.O -> {
                return new AbstractMap.SimpleEntry<>(ancienX - 1, ancienY);
            }
        }
        throw new LCATException(CAS_DEFINITION_NON_RECONNU);
    }


    private void avancerDepuisPosition(int ancienX, int ancienY) throws LCATException {
        var nouvellePosition = calculerAvancerDepuisPosition(ancienX, ancienY);
        if (x != nouvellePosition.getKey() || y != nouvellePosition.getValue()) {
            xPrecedent = x;
            yPrecedent = y;
            x = nouvellePosition.getKey();
            y = nouvellePosition.getValue();
        }
    }

    @Override
    public List<Object> recupererDefinitionWriter() {
        return List.of("A", nom, x, y, orientation.name(), nombreTresorsCollectes);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getOrdre() {
        return ordre;
    }

    public void setOrdre(int ordre) {
        this.ordre = ordre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public OrientationEnum getOrientation() {
        return orientation;
    }

    public void setOrientation(OrientationEnum orientation) {
        this.orientation = orientation;
    }

    public List<MouvementEnum> getMouvements() {
        return mouvements;
    }

    public void setMouvements(List<MouvementEnum> mouvements) {
        this.mouvements = mouvements;
    }

    public int getNombreTresorsCollectes() {
        return nombreTresorsCollectes;
    }

    public void setNombreTresorsCollectes(int nombreTresorsCollectes) {
        this.nombreTresorsCollectes = nombreTresorsCollectes;
    }

    public int getxPrecedent() {
        return xPrecedent;
    }

    public void setxPrecedent(int xPrecedent) {
        this.xPrecedent = xPrecedent;
    }

    public int getyPrecedent() {
        return yPrecedent;
    }

    public void setyPrecedent(int yPrecedent) {
        this.yPrecedent = yPrecedent;
    }
}
