##Projet de raccourcissement d'URL
Ce projet est une application Spring Boot qui fournit un service de raccourcissement d'URL. L'application utilise une base de données H2 intégrée pour stocker les URL raccourcies et leurs correspondances avec les URL complètes.

##Fonctionnalités

Génération d'URL raccourcies à partir d'une URL complète
Récupération de l'URL complète à partir d'une URL raccourcie
Stockage persistant des URL raccourcies dans une base de données H2

##Technologies utilisées
Java 17
Spring Boot 2.5.4
H2 Database 1.4.200
JUnit 5
Mockito

##Configuration
  #Pour exécuter l'application localement, assurez-vous d'avoir Java 17 installé sur votre machine.
    Clonez le dépôt Git sur votre machine locale en utilisant la commande suivante :
    git clone git@github.com:Darkenshin/UrlShortener.git
    Ouvrez le projet dans votre IDE préféré.
    Exécutez la classe MainApplication.java pour démarrer l'application.
##Utilisation
Pour raccourcir une URL, envoyez une requête POST à l'URL /api/url/shorten avec le corps de la requête contenant l'URL complète à raccourcir.
Pour récupérer l'URL complète à partir d'une URL raccourcie, envoyez une requête GET à l'URL /api/url/{shortUrl} où {shortUrl} est l'URL raccourcie.
Documentation API
La documentation de l'API est générée automatiquement à l'aide de Swagger. Vous pouvez accéder à la documentation de l'API en visitant l'URL suivante dans votre navigateur : http://localhost:8080/api/swagger-ui/index.html
Tests
Les tests unitaires sont inclus dans le projet et peuvent être exécutés en utilisant JUnit 5.
Les tests utilisent Mockito pour simuler le comportement du UrlMappingRepository.
