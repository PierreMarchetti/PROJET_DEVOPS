# PROJET DEVOPS



[![codecov](https://img.shields.io/codecov/c/github/PierreMarchetti/PROJET_DEVOPS)](https://codecov.io/gh/PierreMarchetti/PROJET_DEVOPS)
[![Build Status](https://travis-ci.com/PierreMarchetti/PROJET_DEVOPS.svg?branch=master)](https://travis-ci.com/PierreMarchetti/PROJET_DEVOPS)
[![GitHub release (latest by date)](https://img.shields.io/github/v/release/pierremarchetti/PROJET_DEVOPS)](https://github.com/PierreMarchetti/PROJET_DEVOPS/releases/latest)



## fonctionnalités fournies par le service
Le service propose les fonctionalités de base demandées dans le sujet:
 - créer un dataframe à partir d'un fichier csv ou d'un tableau
 - créer un dataframe à partir d'un ensemble de ligne ou de label d'un autre dataframe
- afficher les premières, les dernières ou toutes les lignes d'un dataframe
- afficher la somme, la moyenne, le minimum et le maximum des valeurs d'une colonne

## choix d’outils


- Maven : Utilisé pour le déploiement du logiciel, l'éxécution des tests et la création des rapports de couverture.  
- JUnit : Utilisé comme dépendance dans Maven pour automatiser les tests
- JaCoCo : Plugin de Maven qui gère la couverture de code.
- Travis-Ci : Service d'intégration continue, éxécute les tests avec Maven et déploie une release et une image docker si les tests et le taux de couverture de code sont correctes
- Codecov : Service Github, récupère les rapports générés par Maven à l'éxécution de Travis et offre des statistiques et un badge de couverture de code.

## images Docker hub
2 dépôts dockerhub sont disponibles: [projetdevops](https://hub.docker.com/repository/docker/pierremarchetti/projetdevops) et [projetdevops2](https://hub.docker.com/repository/docker/pierremarchetti/projetdevops2). Les 2 images éxécutent les tests JUnit, mais elles n'ont pas les mêmes conditions de déploiement:
- projetdevops est directement lié au github et se met à jour à chaque push, même si le build échoue dans Travis.
- projetdevops2 est mis à jour à chaque build réussi par Travis. Le service construit l'image et la télécharge dans le dépôt.

  
## Feedback

- Maven a une structure très rigide mais simple à utiliser quand on l'a comprise. La documentation des différents plugins est par contre difficile à trouver. 
- Travis propose beaucoup d'options et de liens vers différents services pour le déploiement.


