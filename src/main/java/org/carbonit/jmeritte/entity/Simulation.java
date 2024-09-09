package org.carbonit.jmeritte.entity;

import org.carbonit.jmeritte.LaCarteAuxTresors;
import org.carbonit.jmeritte.exception.LCATException;
import org.carbonit.jmeritte.exception.LCATRuntimeException;
import org.carbonit.jmeritte.logger.LCATLogger;
import org.carbonit.jmeritte.util.ConstantUtils;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.Objects.isNull;
import static org.carbonit.jmeritte.exception.LCATMessage.*;

public class Simulation {

    LCATLogger log = new LCATLogger(LaCarteAuxTresors.class.getName());

    private final Carte carteDuMonde;
    private final List<Personnage> listePersonnage;

    public Simulation(Carte carte, List<Personnage> listePersonnage) throws LCATException {
        this.carteDuMonde = carte;
        this.listePersonnage = listePersonnage;

        this.lancerSimulation();
    }

    private void lancerSimulation() throws LCATException {
        if (isNull(carteDuMonde) || isNull(carteDuMonde.getCoordonnees()[0][0])) {
            throw new LCATException(CARTE_NON_INITIALISEE);
        }

        if (listePersonnage.isEmpty()) {
            throw new LCATException(PERSONNAGE_AUCUN_PRET);
        }

        listePersonnage.forEach(personnage -> {
            if (personnage.getX() > carteDuMonde.getCoordonnees().length - 1 || personnage.getX() < 0
                    || personnage.getY() > carteDuMonde.getCoordonnees()[0].length - 1 || personnage.getY() < 0) {
                throw new LCATRuntimeException(COORDONNEES_HORS_CARTE);
            } else {
                log.info(ConstantUtils.SEPARATEUR_LIGNE);
                log.info(String.format("Partipant n°%1$d est prêt: %2$s[%3$s](%4$d,%5$d)", personnage.getOrdre(), personnage.getNom(), personnage.getOrientation().name(), personnage.getX(), personnage.getY()));
            }
        });

        int maxMouvementCount = listePersonnage.stream()
                .mapToInt(p -> p.getMouvements().size())
                .max()
                .orElse(0);

        if (maxMouvementCount == 0) {
            log.warning("Un personnage n'a pas prévu de bouger ?");
        }

        log.info(ConstantUtils.SEPARATEUR_LIGNE);
        log.info("Départ de la Carte aux Trésor");
        log.info(ConstantUtils.SEPARATEUR_LIGNE);

        IntStream.range(0, maxMouvementCount)
                .forEach(i -> listePersonnage.stream()
                        .filter(p -> i < p.getMouvements().size())
                        .forEach(p -> {
                            try {
                                effectuerTourPersonnageSelonMouvement(p, i);
                            } catch (LCATException e) {
                                throw new LCATRuntimeException(SIMULATION_PROBLEME_SURVENU);
                            }
                        }));
    }

    private void effectuerTourPersonnageSelonMouvement(Personnage personnage, int index) throws LCATException {
        var prochainePosition = personnage.calculerProchainePosition(personnage.getMouvements().get(index), personnage.getX(), personnage.getY());

        log.info(String.format("%1$s[%2$s](%3$d,%4$d) vers (%5$d,%6$d)", personnage.getNom(), personnage.getOrientation().name(), personnage.getX(), personnage.getY(), prochainePosition.getKey(), prochainePosition.getValue()));

        if (prochainePosition.getKey() > carteDuMonde.getCoordonnees().length - 1 || prochainePosition.getKey() < 0
                || prochainePosition.getValue() > carteDuMonde.getCoordonnees()[0].length - 1 || prochainePosition.getValue() < 0) {
            log.warning("Le personnage ne peut pas aller au delà des limites de la carte.");
        } else {
            var prochaineCase = carteDuMonde.recupererCoordonnees(prochainePosition.getKey(), prochainePosition.getValue());
            if (prochaineCase.isVisitable()) {
                personnage.effectuerProchainPosition(personnage.getMouvements().get(index), personnage.getX(), personnage.getY());
                prochaineCase.activerEffetCaseSurPersonnage(personnage);
            } else {
                log.warning("Le personnage n'a pas l'équipement pour gravir cette case");
            }
        }
    }

    public Carte getCarteDuMonde() {
        return carteDuMonde;
    }

    public List<Personnage> getListePersonnage() {
        return listePersonnage;
    }
}
