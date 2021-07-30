# Where to put the service?

This question leads to the package structure related answer. Package structure is defined in turn by the chosen
architecture. Good architecture decreases the time required to read and change the source code. Bad architecture makes
developers struggle with the code and waste time on accidental technical complexity instead of essential one coming from
business requirements.

0. Know different architecture styles.
1. Choose your architecture consciously.
2. Enforce it properly.
3. Evolve it when needed.
4. Watch it throughout the project lifetime.

## The Goal

This repository contains the main assets of meetup presentation held by DataArt in Voronezh Russia July 28, 2021. You
will find here:

1. `ecommerce-app` - examples of different architecture implementations.
2. `Where to put the service.pptm` - presentation.
3. `Where to put the service.drawio` - presentation diagrams.

## Technologies used
### Implementation
* Java 16, records
* Gradle
* Spring (Boot, Data JPA, Web)
* PostgreSQL
* Flyway
* Lombok (although be careful with Lombok and JPA: https://dzone.com/articles/lombok-and-jpa-what-may-go-wrong)
* Mapstruct

### Testing
* Testcontainers
* Spring Cloud Contracts (yetanother-arch only)

