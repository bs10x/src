# Two Trees

Ihnen werden zwei gewurzelte Bäume, $A$ und $B$, mit disjunkten Knotenmengen gegeben. Der Baum $A$ hat Knoten, die durch $a_0$, ..., $a_{n-1}$ indiziert sind, wobei die Wurzel den Index $a_0$ hat, und der Baum $B$ hat Knoten, die durch $b_0$, ..., $b_{n-1}$ indiziert sind, mit der Wurzel beim Index $b_0$. Die Kanten in jedem Baum sind mit positiven Ganzzahlen gewichtet.

Sie möchten einige neue Kanten hinzufügen, die Blätter von $A$ mit Blättern von $B$ verbinden, um so einen verbundenen Graphen zu erstellen. Genauer gesagt können Sie eine Kante nur hinzufügen, wenn sie zwischen einem Blatt von $A$ und einem Blatt von $B$ verläuft. Jede Kante, die Sie hinzufügen, hat ein Gewicht von $0$.

Die Distanz zwischen zwei Knoten wird als das minimale Gesamtgewicht eines Pfads definiert, der die beiden Knoten verbindet.

Gegeben diese beiden Bäume, müssen Sie die folgenden Methoden implementieren. Alle Resultate passen garantiert in einen "int"-Typ.

1. **edgeCount()**
    - Gibt die Gesamtzahl der Kanten zurück, die Sie zwischen den beiden Bäumen hinzufügen können.
  
2. **minDistRoots()**
    - Gibt die minimale Distanz zwischen den Wurzeln der beiden Bäume zurück, die Sie erreichen können, indem Sie genau eine Kante hinzufügen.
   
3. **cycle()**
    - Gibt $1$ zurück, falls Sie genau zwei Kanten hinzufügen können, sodass der resultierende Graph einen einfachen Zyklus (keine wiederholten Knoten) enthält, der die beiden Wurzeln beinhaltet.

4. **minDistCycle()**
    - Gibt die minimale Länge eines beide Wurzeln enthaltenden einfachen Zyklus (keine wiederholten Knoten) zurück, den Sie erreichen können, indem Sie genau zwei Kanten hinzufügen. Sie können annehmen, dass ein solcher einfacher Zyklus existiert.

**Bewertung** (24 Punkte)

- **edgeCount()** (4 Punkte): 
    - Eine $O(n)$-Zeit-Implementierung erhält $4$ Punkte.
- **minDistRoots()** (8 Punkte):
    - Eine $O(n)$-Zeit-Implementierung erhält $8$ Punkte und eine $O(n^2)$-Zeit-Implementierung erhält $4$ Punkte.
- **cycle()** (4 Punkte):
    - Eine $O(n)$-Zeit-Implementierung erhält $4$ Punkte.
- **minDistCycle()** (8 Punkte):
    - Eine $O(n)$-Zeit-Implementierung erhält $8$ Punkte und eine $O(n^2)$-Zeit-Implementierung erhält $4$ Punkte.

**Achtung**

Es ist Ihnen **NICHT** erlaubt, zusätzliche Imports zu verwenden, ausser den bereits im Code-Template enthaltenen.
