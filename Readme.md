## Projet 3 : Mettez à l'épreuve votre logique

Ce projet se décompose en deux mini-jeu (SearchCode et MasterMind). L'objectif est de trouver la combinaison ou de faire trouver une combinaison secrète. Le jeu se joue directement avec l'EDI.

Le jeu est paramétrable à l'aide d'un fichier config.properties situé dans le dossier src/main/resources.

Le jeu se joue avec la console. Les logs sont redirigés vers un fichier LOG enregistré dans le dossier src/log ou à l'emplacement du jar si le jeu est lancé à partir du jar. Les logs sont organisés avec log4j via une configuration xml (src/main/resources).

Le programme utilise apache log4j situé dans la bibliothèque src/main/lib.

## Configuration :

Le jeu peut se jouer à l'aide d'un fichier .jar situé dans le dossier src/jarfile. Il convient d'exécuter le jeu via la console, de se placer dans le dossier et d'entrer l'une des commandes suivantes :
java -jar Projet3MettezVotreLogiqueALEpreuve.jar --d   => mode développeur
java -jar Projet3MettezVotreLogiqueALEpreuve.jar --d   => mode normal

## Contributeur :

Maximilien LeBoiteux alias ToyTheRusse : https://github.com/Toysurusse

Ce projet est libre de droit et disponible gratuitement : https://github.com/Toysurusse/Projet-3.git
