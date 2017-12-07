# projet_ia

https://github.com/aurorehusson/projet_ia/

Ce projet est codé en langage JAVA.
Non-fournit sur ce git : la base de données originale, qui doit être présente à la racine du projet pour permettre l'execution du code.
Nous utilisons une librairie de com.apache pour réaliser certains calculs. Cette librairie est inclus dans le git.

## Vue générale du projet

Ce projet traite du sujet YearSong
L'objectif est de créer un modèle permettant de prédire l'année de sortie d'une musique à partir d'une ensemble de 90 valeurs la décrivant.

Nous avons donc décidé d'utiliser des outils permettant l'analyse de données et la génération de modèles tels que tanagra et weka afin de
comparer globalement certaines methodes.
Nous avons retenu dans un premier temps 3 méthodes :
  - Perceptron multi-couches;
  - Séparateur à vaste marge;
  - Arbre de régression;
 
Nous avons surtout développé la partie des arbres de régression en testant des methodes précises telles que J48 et ID3 (package 
regression.tree), mais aussi nous avons tenté de développer un séparateur à vaste marge (package regression.plan). La partie perceptron
multicouche étant beaucoup trop fastidieuse, elle n'a pas pu être développée, et en reste à la simple état d'analyse.

La phase de recherche sur les logiciels tanagra et weka a pris beaucoup de temps, car les methodes proposées sont nombreuses et aussi,
le format données a nécessité de nombreuses retourches tant dans la transformation de la base de donnée initiale vers les logiciels 
tanagra et weka (2 formats différents) que dans la récupération des modèles fournit par ces lgiciels, modèles qu'il a fallut recoder 
afin d'effectuer des tests.

## Description des dossiers et packages

Le package data contient des classes qui permettent l'acquisition des données brut, du fichier original

Le package regression.plan contient la methode utilisée pour générer le modèle de séparateur à vaste marge.
Le package regression.tree contient differentes methodes pour générer des arbres de regression à partir des modèles obtenu par tanagra et weka.
      Ce package contient 3 mains générant les arbres (package regression.tre.main)
      ainsi qu'un certain nombre de main dans le package regression.tree.parsing.file.converter qui permet d'obtenir les différents 
      fichiers dont nous avons eu besoin.
Le package result contient un fonction analysant les résultats afin d'obtenir des indicateurs tels que rappels et précisions.

Les dossiers treedata et ses sous dossiers contiennent les modèles résultats des logiciels tanagra et weka, tandis que les dossiers results et ses sous dossiers contiennent les résulats des tests appliqués.

Il est bien important de mettre la base de données originale nommée "YearPredictionMSD.txt" à la racine du projet, et de lancer le main de TanagraFileConverter, afin de couper la base de donnnées deux fichiers, un contenant les exemples training et l'autre contenant les exemples tests.
