# Binärer Suchbaum

Während des Semesters haben Sie binäre Suchbäume kennen gelernt und eine Programmieraufgabe dazu implementiert. In dieser Aufgabe sollen Sie die folgenden vier Arten von Querys implementieren:

1. **min()**: Falls der binäre Suchbaum nicht leer ist, geben Sie den **kleinsten Schlüssel** im Suchbaum zurück; andernfalls geben Sie *-1* zurück.
2. **depth($k$)**: Gegeben sei eine natürliche Zahl $k$. Falls der binäre Suchbaum einen Knoten mit Schlüssel $k$ enthält, geben Sie die **Tiefe** des Knotens zurück; andernfalls geben Sie *-1* zurück. Beachten Sie, dass die *Wurzel* Tiefe **0** hat.
3. **children_of_node($k$)**: Gegeben sei eine natürliche Zahl $k$. Falls der binäre Suchbaum einen Knoten mit Schlüssel $k$ enthält, geben Sie **die Zahl der Kinder** dieses Knotens zurück; sonst geben Sie *-1* zurück.
4. **key_of_rank($r$)**: Gegeben sei eine natürlich Zahl *r*. Falls die Zahl der Knoten im binären Suchbaum mindestens $r$ ist, geben Sie **den $r$'t-kleinsten Schlüssel** im binären Suchbaum zurück; sonst geben Sie *-1* zurück.

**Hinweis**:
* Für die letzte Aufgabe, *key_of_rank($r$)*, müssen Sie die Implementierung des binären Suchbaums ergänzen. 
* Sie können davon ausgehen, dass alle Schlüssel im binären Suchbaum verschieden und nicht-negativ sind. 


**Bewertung** (24 Punkte): Sei $h$ die Höhe des binären Suchbaums.

1. **min()** (5 Punkte): Eine Implementierung in Zeit $O(h)$ gibt 5 Punkte. Wenn Randfälle falsch behandelt werden, gibt es stattdessen 4 Punkte.
2. **depth($k$)** (5 Punkte): Eine Implementierung in Zeit $O(h)$ gibt 5 Punkte. Wenn Randfälle falsch behandelt werden, gibt es stattdessen 4 Punkte.
3.  **children_of_node($k$)** (5 Punkte): Eine Implementierung in Zeit $O(h)$ gibt 5 Punkte. Wenn Randfälle falsch behandelt werden, gibt es stattdessen 4 Punkte.
4.  **key_of_rank($r$)** (9 Punkte): Eine Implementierung in Zeit $O(h)$ gibt 9 Punkte, eine Implementierung in Zeit $O(n)$ gibt 5 Punkte. Wenn Randfälle falsch behandelt werden, gibt es stattdessen 8 bzw. 4 Punkte.

**Achtung**:

- Ausser Imports, die schon im Template vorkommen, dürfen Sie keine weiteren expliziten oder impliziten Imports verwenden.
- CodeExpert zeigt Ihnen keine Punkte an, sondern den **Prozentsatz**, den Ihre Submission erfüllt.
