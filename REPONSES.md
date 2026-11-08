# Réponses — Évaluation POO : héritage, polymorphisme, transtypage

- filiere :DBE P7
- Nom et prénom : Ramatoulaye Yassawlou Diouf


## Exercice 1 — A. Prédiction

### Affichage (avec N = 10)

```text
A vehicule
F null 0
E moto 110
Moto2
vehicule
moto
C3
C2
D-vehicule
A vehicule
F null 0
E moto 110
true false
true
A vehicule
B vehicule
3
```

### Justifications

- **F null 0 :** `demarrer()` est redéfinie dans Moto et appelée avant l'initialisation des attributs de Moto.
- **C3 :** `comparer(Vehicule)` est redéfinie dans Moto.
- **C2 :** l'argument `(Moto) v` sélectionne la surcharge `comparer(Moto)`.
- **D-vehicule :** `categorie()` est statique et dépend du type déclaré Vehicule.
- **true false :** `equals()` compare le même objet, tandis que `== new Moto()` compare deux objets différents. Le constructeur de la nouvelle Moto s'exécute avant l'affichage.

## B. Transtypage

| N° | Compile | Exécution | Justification |
|---|---|---|---|
| 1 | Oui | OK | Moto est un Vehicule. |
| 2 | Non | - | Vehicule ne peut pas être affecté directement à Moto. |
| 3 | Oui | OK | a contient réellement une Moto. |
| 4 | Oui | ClassCastException | b contient un Camion. |
| 5 | Oui | ClassCastException | c contient une Moto. |
| 6 | Oui | ClassCastException | b contient un Camion, pas un String. |
| 7 | Non | - | Object ne possède pas type(). |
| 8 | Oui | OK | Camion hérite de type() de Vehicule. |
