#########A Végétable Garden#########


Ce projet consiste à simuler un potager en vue de dessus.
Les fonctionnalités de base à développer pour le projet sont les suivantes :
- l’utilisateur doit pouvoir planter/récolter différents légumes sur les cases du potager
- l’environnement doit être simulé (hydrométrie, température, ensoleillement)
- Des variétés doivent être simulées (2 à plusieurs) : conditions de croissance à définir
- La vitesse de la simulation doit pouvoir être paramétrée (vitesse accélérée pour la démo)
Précisions concernant l’implémentation :
- Le plateau est représenté par une grille de cases pour la vue, et par une grille de cases pour le modèle.
- L’utilisateur peut cliquer sur les différentes cases afin de réaliser des actions (planter, cueillir, etc.)

#####Compiler le jar#######

$ gradle jar

Le fichier de sortie est ‘app/build/libs/app.jar’. Il contient déjà toutes les dépendances

nécessaires à son exécution.

#####Générer les diagrammes de classe###

$ gradle javadoc

Sortie dans ‘app/build/docs/javadoc/’.

Bien qu’aucune documentation javadoc ne soit ecrite dans le code, la sortie contient tout de

même un diagramme des classes généré automatiquement à partir du code. Pour cela,

nous avons utilisé umldoclet (https://github.com/talsma-ict/umldoclet)
