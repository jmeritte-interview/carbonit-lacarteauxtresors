package org.carbonit.jmeritte.reader;

import org.carbonit.jmeritte.LaCarteAuxTresors;
import org.carbonit.jmeritte.entity.Carte;
import org.carbonit.jmeritte.entity.CaseMontagne;
import org.carbonit.jmeritte.entity.CaseTresor;
import org.carbonit.jmeritte.entity.Personnage;
import org.carbonit.jmeritte.enumeration.ParsingTypeEnum;
import org.carbonit.jmeritte.exception.LCATException;
import org.carbonit.jmeritte.exception.LCATMessage;
import org.carbonit.jmeritte.exception.LCATRuntimeException;
import org.carbonit.jmeritte.logger.LCATLogger;
import org.carbonit.jmeritte.parser.impl.MontagneParser;
import org.carbonit.jmeritte.parser.impl.PersonnageParser;
import org.carbonit.jmeritte.parser.impl.TresorParser;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Character.isWhitespace;
import static java.util.Objects.isNull;
import static org.carbonit.jmeritte.exception.LCATMessage.*;

public class LCATReader {

    LCATLogger log = new LCATLogger(LaCarteAuxTresors.class.getName());

    Carte carteDuMonde = null;
    List<Personnage> listePersonnage = new ArrayList<>();

    public void genererCarteDepuisFichier(String cheminFichier) throws LCATException, LCATRuntimeException {
        if (isNull(cheminFichier) || cheminFichier.isEmpty()) {
            throw new LCATException(LCATMessage.IO_FICHIER_INCONNU);
        }

        Scanner scanner;
        String contenuFichier;
        var listeMontagne = new ArrayList<CaseMontagne>();
        var listeTresor = new ArrayList<CaseTresor>();
        var nombrePersonnage = 0;

        try {
            Path chemin = Paths.get(LCATReader.class.getResource("/" + cheminFichier).toURI());

            contenuFichier = new String(Files.readAllBytes(Paths.get(chemin.toUri())));
        } catch (IOException | URISyntaxException | NullPointerException e) {
            throw new LCATRuntimeException(PROBLEME_LECTURE_FICHIER);
        }

        scanner = new Scanner(contenuFichier);

        try { // On part du principe qu'il est possible de récupérer un fichier d'une très grande taille
            while (scanner.hasNextLine()) {
                var ligne = scanner.nextLine();
                if (!ligne.isEmpty()) {
                    var premierCaractere = ligne.charAt(0);

                    if (premierCaractere == '#') {
                        log.info("Ligne de commentaire détectée");
                        break;
                    }

                    var montagneParser = new MontagneParser();
                    var tresorParser = new TresorParser();
                    var personnageParser = new PersonnageParser();

                    switch (ParsingTypeEnum.valueOf(String.valueOf(premierCaractere))) {
                        case ParsingTypeEnum.C -> initialiserCarteDuMonde(ligne);
                        case ParsingTypeEnum.M -> listeMontagne.add(montagneParser.parserDepuisLigne(ligne));
                        case ParsingTypeEnum.T -> listeTresor.add(tresorParser.parserDepuisLigne(ligne));
                        case ParsingTypeEnum.A -> {
                            var personnageCree = personnageParser.parserDepuisLigne(ligne);
                            nombrePersonnage++;
                            personnageCree.setOrdre(nombrePersonnage);
                            listePersonnage.add(personnageCree);
                        }
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            throw new LCATException(FICHIER_LIGNE_NON_RECONNUE);
        } finally {
            scanner.close();
        }

        if (isNull(carteDuMonde)) {
            throw new LCATException(CARTE_NON_INITIALISEE);
        }

        carteDuMonde.attribuerCaseSpeciale(listeMontagne);
        carteDuMonde.attribuerCaseSpeciale(listeTresor);
        carteDuMonde.comblerCasesRestantes();
    }

    private void initialiserCarteDuMonde(String ligne) throws LCATException {
        log.info("Initialisation de la carte");

        Integer largeur = null;
        Integer longueur = null;

        for (char c : ligne.substring(2).toCharArray()) {
            if (!isWhitespace(c) && c != '-') {
                try {
                    int valeurConvertie = Integer.parseInt(String.valueOf(c));
                    if (isNull(largeur)) {
                        largeur = valeurConvertie;
                    } else if (isNull(longueur)) {
                        longueur = valeurConvertie;
                    } else {
                        throw new LCATException(LCATMessage.CARTE_DONNEES_INCOMPATIBLES);
                    }
                } catch (NumberFormatException e) {
                    throw new LCATException(LCATMessage.CAS_DEFINITION_NON_RECONNU);
                }

            }
        }

        verificationDimensions(largeur, longueur);

        carteDuMonde = new Carte(largeur, longueur);
    }

    private void verificationDimensions(Integer largeur, Integer longueur) throws LCATException {
        if (isNull(largeur) || isNull(longueur)) {
            throw new LCATException(LCATMessage.CARTE_DONNEES_INCOMPATIBLES);
        }

        if (largeur.equals(0) || longueur.equals(0) || (largeur.equals(1) && longueur.equals(1))) {
            throw new LCATException(LCATMessage.CARTE_DONNEES_ILLOGIQUES);
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
