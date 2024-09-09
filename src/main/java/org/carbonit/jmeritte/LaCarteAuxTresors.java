package org.carbonit.jmeritte;

import org.carbonit.jmeritte.entity.Simulation;
import org.carbonit.jmeritte.exception.LCATException;
import org.carbonit.jmeritte.exception.LCATRuntimeException;
import org.carbonit.jmeritte.logger.LCATLogger;
import org.carbonit.jmeritte.parser.impl.ArgumentParserImpl;
import org.carbonit.jmeritte.reader.LCATReader;
import org.carbonit.jmeritte.writer.LCATWriter;

public class LaCarteAuxTresors {

    LCATLogger log = new LCATLogger(LaCarteAuxTresors.class.getName());

    public LaCarteAuxTresors(String[] args) {
        try {
            var cheminCarte = new ArgumentParserImpl().recupererCheminFichierDepuisListeArgument(args);

            var reader = new LCATReader();

            reader.genererCarteDepuisFichier(cheminCarte);

            var simulation = new Simulation(reader.getCarteDuMonde(), reader.getListePersonnage());

            var writer = new LCATWriter(simulation.getCarteDuMonde(), simulation.getListePersonnage());

            writer.afficherResultat();

        } catch (LCATException | LCATRuntimeException exception) {
            this.log.severe(exception.getMessage());
        }
    }

    public static void main(String[] args) {
        new LaCarteAuxTresors(args);
    }
}