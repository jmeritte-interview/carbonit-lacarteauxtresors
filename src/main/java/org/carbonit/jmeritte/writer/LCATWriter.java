package org.carbonit.jmeritte.writer;

import org.carbonit.jmeritte.LaCarteAuxTresors;
import org.carbonit.jmeritte.entity.Carte;
import org.carbonit.jmeritte.entity.CaseMontagne;
import org.carbonit.jmeritte.entity.CaseTresor;
import org.carbonit.jmeritte.entity.Personnage;
import org.carbonit.jmeritte.exception.LCATException;
import org.carbonit.jmeritte.exception.LCATRuntimeException;
import org.carbonit.jmeritte.logger.LCATLogger;
import org.carbonit.jmeritte.util.ConstantUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static org.carbonit.jmeritte.exception.LCATMessage.WRITER_CONCATENATION_DEFINITION;
import static org.carbonit.jmeritte.exception.LCATMessage.WRITER_ERREUR_GENERATION_RESULTAT;
import static org.carbonit.jmeritte.util.ConstantUtils.SEPARATEUR_RESULTAT_TXT;

public class LCATWriter {

    LCATLogger log = new LCATLogger(LaCarteAuxTresors.class.getName());

    Carte carteDuMonde;
    List<Personnage> listePersonnage;

    FileWriter fileWriter = null;
    String nomFichierSortie = null;

    public LCATWriter(Carte carte, List<Personnage> listePersonnage) {
        this.carteDuMonde = carte;
        this.listePersonnage = listePersonnage;
    }

    public void afficherResultat() throws LCATException {
        log.info(ConstantUtils.SEPARATEUR_LIGNE);
        log.info("Ecriture des résultats");

        try {
            nomFichierSortie = System.getProperty("user.dir") + "/resultat.txt";
            var fichierCible = new File(nomFichierSortie);

            if (!fichierCible.createNewFile()) {
                log.warning("Le fichier existe déjà.");
            }

            fileWriter = new FileWriter(nomFichierSortie);

            ecrireLigneResultatDepuisDefinition(carteDuMonde.recupererDefinitionWriter());
            ecrireClasseSpecifiquePresentCarteDuMonde(CaseMontagne.class);
            ecrireClasseSpecifiquePresentCarteDuMonde(CaseTresor.class);

            listePersonnage.forEach(personnage -> ecrireLigneResultatDepuisDefinition(personnage.recupererDefinitionWriter()));

            fileWriter.close();

            log.info(ConstantUtils.SEPARATEUR_LIGNE);
            log.info("Ecriture terminée");
        } catch (IOException e) {
            throw new LCATException(WRITER_ERREUR_GENERATION_RESULTAT);
        }
    }

    private void ecrireClasseSpecifiquePresentCarteDuMonde(Class<?> c) {
        Arrays.stream(carteDuMonde.getCoordonnees()).flatMap(Arrays::stream).filter(c::isInstance).forEach(objet -> ecrireLigneResultatDepuisDefinition(((Writable) objet).recupererDefinitionWriter()));
    }

    private void ecrireLigneResultatDepuisDefinition(List<Object> definition) {
        if (definition.isEmpty()) {
            return;
        }

        AtomicReference<String> resultat = new AtomicReference<>("");

        definition.forEach(entree -> resultat.set(definition.stream().map(String::valueOf).collect(Collectors.joining(SEPARATEUR_RESULTAT_TXT))));

        try {
            fileWriter.write(resultat.get() + "\n");
        } catch (IOException e) {
            throw new LCATRuntimeException(WRITER_CONCATENATION_DEFINITION);
        }
    }

    public Carte getCarteDuMonde() {
        return carteDuMonde;
    }

    public void setCarteDuMonde(Carte carteDuMonde) {
        this.carteDuMonde = carteDuMonde;
    }

    public List<Personnage> getListePersonnage() {
        return listePersonnage;
    }

    public void setListePersonnage(List<Personnage> listePersonnage) {
        this.listePersonnage = listePersonnage;
    }
}
