TODO Readme Complet

## Commencer lancer le programme

Executer le programme java en utilisant comme argument le nom du fichier présent dans les resources:
- Exemple: carte.txt


## Points d'attention
*Questionnements/précisions à demander en cas réel*
- Pas de volume de taille maximal de carte ?
  - Détermine la manière dont lire la carte (mise en mémoire du contenu du fichier entier, ou lecture par ligne)
  - **Choix par défaut:** Cas de carte de millions de lignes
- Quel comportement en cas de plusieurs objets superposés ?
  - Ex: Cas d'un trésor sur une montagne, est-il un cas accepté (trésor inaccessible) ou un cas d'erreur ?
  - **Choix par défaut:** Cas d'erreur
- La carte et ses coordonnées ont-ils des bords reliés ?
  - **Choix par défaut:** Les bords de la carte ne sont pas reliés et agissent comme des obstacles.
    - Les coordonnées sont donc en valeur absolue, les valeurs négatives sont ignorées
- Doit-on vérifier l'unicité des participants ?
  - **Choix par défaut:** Des personnages peuvent avoir le même nom, on les distingue par leur numéro d'inscription (ordre dans la liste des personnages)
- Les aventuriers disposent-ils tous du même nombre de mouvement ?
  - **Choix par défaut:** On prévient que la course est deséquilibrée, mais on ne l'empêche pas (Un aventurier avec une meilleure trajectoire pourrait finir en moins de mouvement, double prestige)

## Notes de conception
*Choix de conception dénués de questionnement client*
- Utilisation de types primitifs plutôt que leurs équivalent objets lorsqu'on ne considère *pas* la nullabilité possible
- Choix de nommage français arbitraire, les concepts du sujet et le nom du programme étant en français
- Aucun cas pertinent d'utilisation de record
- Choix d'utiliser le moins de librairies possibles pour le projet (Lombok, Logback, Mapstruct, Guava, Vavr ...)
  - Pas @VisibleForTesting pour tester les fonctions privées
  - Pas de TUs qui utilisent la récupération des logs
  - Pas de logger SLF4J ni Getter/Setter/EqualsAndHashCode/ etc...
  - Pas de Try.of() permettant de gérer les exceptions des streams (Vavr)

## Evolutions futures possibles:
- Gestion d'argument propre poussée
  - flag debug (--debug) (-d) pour afficher des logs en mode debug
  - flag overwrite (--overwrite) (-ovw) pour permettre ou non d'écraser le fichier de résultat
  - flag result (--result) (-r) pour spécifier un chemin de sortie différent