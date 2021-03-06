# Cloud System
Livello in cui confluiscono tutte le informazioni relative a tutti gli edge. 
Il cloud è altamente scalabile, per gestire il carico di richieste che riceve in ogni istante. 
Con i digital twin di ogni casina sempre aggiornati è possibile aggregare dati su larga scala, 
oltre ad effettuare analisi di dati sia su scala temporale che spaziale. 
Grazie alle informazioni che riceve e alle sue risorse computazionali, il cloud mantiene uno storico dei dati al fine di poter visualizzare le condizioni atmosferiche di una particolare zona del territorio nel passato.
Il cloud può essere interrogato da qualsiasi computer della rete relativamente alle casine, al loro storico ed alle loro informazioni aggregate; 
questo indirizza il progetto verso una soluzione open data, in cui i dati raccolti dagli asset fisici presenti sul territorio sono disponibili a chiunque ne sia interessato.

#### Software Info

![GitHub](https://img.shields.io/github/license/PC-ProgettoMIA/cloud)
![GitHub language count](https://img.shields.io/github/languages/count/PC-ProgettoMIA/cloud)
![GitHub top language](https://img.shields.io/github/languages/top/PC-ProgettoMIA/cloud)
![GitHub pull requests](https://img.shields.io/github/issues-pr/PC-ProgettoMIA/cloud)
![GitHub issues](https://img.shields.io/github/issues/PC-ProgettoMIA/cloud)
![GitHub code size in bytes](https://img.shields.io/github/languages/code-size/PC-ProgettoMIA/cloud)
![GitHub repo size](https://img.shields.io/github/repo-size/PC-ProgettoMIA/cloud)
![GitHub contributors](https://img.shields.io/github/contributors/PC-ProgettoMIA/cloud)

#### Software Progress
![GitHub issues](https://img.shields.io/github/issues/PC-ProgettoMIA/cloud)
![GitHub closed issues](https://img.shields.io/github/issues-closed/PC-ProgettoMIA/cloud)
![GitHub pull requests](https://img.shields.io/github/issues-pr/PC-ProgettoMIA/cloud)
![GitHub closed pull requests](https://img.shields.io/github/issues-pr-closed/PC-ProgettoMIA/cloud)
![GitHub commits since latest release (by date)](https://img.shields.io/github/commits-since/PC-ProgettoMIA/cloud/latest/develop)
![GitHub last commit](https://img.shields.io/github/last-commit/PC-ProgettoMIA/cloud/develop)


## Requirements

Il **_cloud_** è messo in esecuzione su un VM con i seguenti requisiti:

- 32 GB RAM
- 16 core logici  (core e RAM sono riconfigurabili)
- 100 GB disco
- OS:  ubuntu server 20.04

## Deployment
Il server è sempre online ed il cloud sempre attivo ma, in caso di arresto del server, il riavvio di tale dovrà essere eseguito seguendo i seguenti passaggi:
```bash
#Abilitare i permessi per l'esecuzione degli script.
chmod 755 docker-ditto.sh
chmod 755 cloud.sh

#Esecuzione per l'avvio del servizio docker Eclipse Ditto
./docker-ditto.sh

#Esecuzione per l'avvio del middleware.
./cloud.sh
```

In questo modo viene effettuato il deployment solo di:
- subscriber MQTT
- server per esporre la Rest API sulla rete locale, in modo da essere raggiunta facilmente dagli applicativi Snap!


# License
See the [License File](./LICENSE).

## Author and Copyright
Author:
- [Battistini Ylenia](https://github.com/yleniaBattistini)
- [Gnagnarella Enrico](https://github.com/enrignagna)
- [Scucchia Matteo](https://github.com/scumatteo)

Copyright (c) 2021.
