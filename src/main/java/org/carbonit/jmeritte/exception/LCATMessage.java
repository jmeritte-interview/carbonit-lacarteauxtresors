package org.carbonit.jmeritte.exception;

public final class LCATMessage {
    private LCATMessage() {
    }

    public static final String PROBLEME_LECTURE_FICHIER = "Erreur lors de la lecture du fichier";
    public static final String PROBLEME_NOMBRE_ARGUMENT_FICHIER = "Veuillez lancer le programme avec un argument";

    public static final String IO_FICHIER_INCONNU = "Impossible de récupérer le fichier en paramètre.";
    public static final String FICHIER_LIGNE_NON_RECONNUE = "Ligne non reconnue dans le fichier";

    public static final String CARTE_DONNEES_INCOMPATIBLES = "Les données de la carte sont incompatibles.";
    public static final String CARTE_DONNEES_ILLOGIQUES = "Les coordonnées de la carte sont illogiques.";
    public static final String CARTE_NON_INITIALISEE = "La carte n'est pas initialisée.";

    public static final String PERSONNAGE_AUCUN_PRET = "Les personnages ne semblent pas présent.";

    public static final String SIMULATION_PROBLEME_SURVENU = "Un problème survenu dans la simulation des mouvements de personnage";

    public static final String CAS_DEFINITION_NON_RECONNU = "Un des cas de caractère n'est pas reconnu";

    public static final String COORDONNEES_HORS_CARTE = "Les coordonnées sont en dehors de la carte";

    public static final String CARTE_CASE_DEJA_ATTRIBUEE = "La case est déjà attribuée";

    public static final String PARSER_MAUVAIS_ENTITE = "La mauvaise case essaie de s'initialiser";
    public static final String PARSER_FORMAT_COURT = "Le format de la ligne est trop court";
    public static final String PARSER_FORMAT_INCORRECT = "Le format de la ligne est incorrect";
    public static final String PARSER_VALEUR_NON_RECONNUE = "Valeurs non reconnues dans la ligne Personnage";
    public static final String PARSER_PERSONNAGE_ECHEC = "Un problème est survenu dans traitant les informations d'un personnage";

    public static final String WRITER_NON_INITIALISE = "Le Writer n'est pas initialisé";
    public static final String WRITER_CONCATENATION_DEFINITION = "Problème lors de la concaténation de la définition";
    public static final String WRITER_ERREUR_CASE = "Erreur lors de la récupération des cases à écrire";
    public static final String WRITER_ERREUR_GENERATION_RESULTAT = "Un problème est survenu en essayant de générer les résultats";
}
