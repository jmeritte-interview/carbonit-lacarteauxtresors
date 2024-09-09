package org.carbonit.jmeritte.entity;

import org.carbonit.jmeritte.LaCarteAuxTresors;
import org.carbonit.jmeritte.logger.LCATLogger;
import org.carbonit.jmeritte.writer.Writable;

import java.util.Collections;
import java.util.List;

public class CaseTresor extends Case implements Writable {

    LCATLogger log = new LCATLogger(LaCarteAuxTresors.class.getName());

    int nombreTresorRestant;

    public CaseTresor(int x, int y, int nombreTresorRestant) {
        super(x, y);
        this.nombreTresorRestant = nombreTresorRestant;
        this.visitable = true;
    }

    @Override
    public void activerEffetCaseSurPersonnage(Personnage personnage) {
        if (nombreTresorRestant > 0) {
            if (personnage.getxPrecedent() == x && personnage.getyPrecedent() == y) {
                return;
            }
            nombreTresorRestant--;
            personnage.acquerirTresor();
            log.info(String.format("Trésor(%1$d,%2$d) trouvé par %3$s", x, y, personnage.getNom()));
        }
    }

    public int getNombreTresorRestant() {
        return nombreTresorRestant;
    }

    @Override
    public List<Object> recupererDefinitionWriter() {
        if (nombreTresorRestant == 0) {
            return Collections.emptyList();
        }
        return List.of("T", x, y, nombreTresorRestant);
    }
}
