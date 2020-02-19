# kata-mutation

Lancement des tests (pour obtention stats code coverage)
> mvn clean test

Lancement des tests par mutation (pour obtention stats pitest)
> mvn clean test pitest:mutationCoverage
>

## Objectifs du kata

1. Lancement des tests (sans et avec mutation)
2. Analyse des résultats
3. Corriger les pbs dans les tests
4. Corriger les classes business
5. Kill all mutants !


## Tests smells

* CompanyTest -> companyRenamed() = mauvais usage d'un mock (teste de COMMENT plutôt que le QUOI) 
* CompanyTest -> leadingTrailingSpacesRemovedFromEmployeeName() = oubli d'un cas de test (espace au mieu du mot)
* CompanyTest -> employeeAdded() = assertions pas assez précises
* CompanyTest -> everybodyGetsRaise() = reprise de l'algo dans le test
* CompanyTest -> findEmployeeById() = pas d'assertions !
* CompanyTest -> employeeNameChanged() = utilisation d'un print à la place d'une assertion