# projet_ia

https://github.com/aurorehusson/projet_ia/

Ce projet est codé en langage JAVA.
Non-fourni sur ce git : la base de données originale, qui doit être présente à la racine du projet pour permettre l'execution du code.
Nous utilisons une librairie de com.apache pour réaliser certains calculs. Cette librairie est incluse dans le git.

## Vue générale du projet

Ce projet traite du sujet YearSong.
L'objectif est de créer un modèle permettant de prédire l'année de sortie d'une musique à partir d'une ensemble de 90 valeurs la décrivant.

Nous avons donc décidé d'utiliser des outils permettant l'analyse de données et la génération de modèles tels que Tanagra et Weka afin de
comparer globalement certaines methodes.
Nous avons retenu dans un premier temps 3 méthodes :
  - Perceptron multi-couches ;
  - Séparateur à vaste marge ;
  - Arbre de régression.
 
Nous avons surtout développé la partie des arbres de régression en testant des methodes précises telles que J48 et ID3 (package 
regression.tree), mais nous avons aussi développé un séparateur à vaste marge (package regression.plan). La partie perceptron
multicouche étant beaucoup trop fastidieuse, elle n'a pas pu être développée, et en reste au simple état d'analyse.

La phase de recherche sur les logiciels Tanagra et Weka a pris beaucoup de temps, car les methodes proposées sont nombreuses,
le format des données a également nécessité de nombreuses retourches tant dans la transformation de la base de données initiale vers les logiciels 
Tanagra et Weka (2 formats différents) que dans la récupération des modèles fournis par les logiciels, modèles qu'il a fallu recoder 
afin d'effectuer des tests.

## Description des dossiers et packages

Le package data contient des classes qui permettent l'acquisition des données brutes, c'est-à-dire le fichier original.

Le package regression.plan contient la méthode utilisée pour générer le modèle de séparateur à vaste marge.
Le package regression.tree contient différentes méthodes pour générer des arbres de regression à partir des modèles obtenus par Tanagra et Weka.
      Ce package contient 3 mains générant les arbres (package regression.tre.main)
      ainsi qu'un certain nombre de main dans le package regression.tree.parsing.file.converter qui permet d'obtenir les différents 
      fichiers dont nous avons eu besoin.
Le package result contient une fonction analysant les résultats afin d'obtenir des indicateurs tels que le rappel et le précision.

Les dossiers treedata et ses sous-dossiers contiennent les modèles résultant des logiciels Tanagra et Weka, tandis que les dossiers results et ses sous-dossiers contiennent les résulats des tests appliqués.

Il est bien important de mettre la base de données originale nommée "YearPredictionMSD.txt" à la racine du projet, et de lancer le main de TanagraFileConverter, afin de couper la base de donnnées en deux fichiers, un contenant les exemples de training et l'autre contenant les exemples de tests.
