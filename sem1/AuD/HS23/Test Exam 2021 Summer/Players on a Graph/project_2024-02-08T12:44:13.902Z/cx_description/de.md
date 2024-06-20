# Spieler auf einem Graphen

Gegeben sei ein gerichteter Graph mit $n$ Knoten und $n$ Kanten, wobei der Ausgangsgrad (*out-degree*) jedes Knotens $1$ beträgt. Es wird garantiert, dass, wenn die Kanten als ungerichtet betrachtet werden, der Graph zusammenhängend ist. Ein solcher Graph enthält genau einen Kreis. Hier ist ein Beispiel für $n=11$, in welchem die Knoten $4$, $7$, $8$ und $10$ einen Kreis bilden.

![cycle_graph.png](/cx_description/cycle_graph.png)

Am Anfang des Spieles steht genau eine Spielerin auf jedem Knoten. Ein Timer wird dann gestartet, und bei jedem Zeitschritt bewegt sich jede Spielerin zu demjenigen Knoten, auf den die (ausgehende) Kante ihres bisherigen Knotens gezeigt hatte. Der Timer wird nie gestoppt. Beachten Sie, dass mehrere Spielerinnen den gleichen Knoten gleichzeitig erreichen dürfen.

Gegeben sei ein solcher Graph. Sie sollen folgende Abfragen beantworten:

1. **cycleLength()**: Sie sollen die Länge des Kreises im Graphen zurückgeben.

2. **distanceToCycle($x$)**: Angenommen, eine Spielerin startet beim Knoten $x$. Sie sollen die Anzahl Kanten zurückgeben, die die Spielerin besuchen muss, bevor sie den Kreis zum ersten Mal erreicht. (Liegt $x$ bereits im Kreis, ist  $0$ zurückzugeben.)

3. **firstNodeInCycle($x$)**: Nach einigen Zeitschritten erreicht die beim Knoten $x$ startende Spielerin den Kreis. Sie sollen der erste Knoten im Kreis zurückgeben, den diese Spielerin erreicht. (Liegt $x$ bereits im Kreis, ist $x$ zurückzugeben.)

4. **distanceInCycle($x$, $y$)**: Nach einigen Zeitschritten hat sowohl die beim Knoten $x$ als auch die beim Knoten $y$ startende Spielerin den Kreis erreicht. Sie sollen die Distanz zwischen ihnen auf dem Kreis zu dem Zeitpunkt, wo dies zum ersten Mal zutrifft, zurückgeben. (Diese Distanz wird berechnet, indem die Kanten des Graphen als ungerichtet betrachtet werden, d.h., es wird das Minimum der Distanz im Uhrzeigersinn und der Distanz gegen den Uhrzeigersinn entlang des Kreises berechnet.)

    (Beachten Sie, dass die beiden Spielerinnen den Kreis zu unterschiedlichen Zeitpunkten erreichen dürfen.)

Sie sollen die vier oben beschriebenen Methoden implementieren. Sie verfügen zusätzlich über eine **intialize()**-Methode, die genau einmal aufgerufen wird, bevor die erste Abfrage behandelt wird. Fühlen Sie sich frei, diese Methode zu verwenden, etwa um Informationen vorzuberechnen, die zur Beantwortung aller Abfragen benötigt werden.

**Bewertung** (24 Punkte):

1. **cycleLength()** (4 Punkte): Diese Abfrage wird maximal einmal ausgeführt. Eine $O(n)$-Implementierung erhält $4$ Punkte.
2. **distanceToCycle($x$)** (6 Punkte): Sei $q$ die Anzahl Abfragen dieses Typs. Eine $O(n+q)$-Implementierung erhält 6 Punkte, eine $O(nq)$-Implementierung 3 Punkte.
3. **firstNodeInCycle($x$)** (6 Punkte): Sei $q$ die Anzahl Abfragen dieses Typs. Eine $O(n+q)$-Implementierung erhält 6 Punkte, eine $O(nq)$-Implementierung 3 Punkte.
4. **distanceInCycle($x$, $y$)** (8 Punkte): Sei $q$ die Anzahl Abfragen dieses Typs. Eine $O(n+q)$-Implementierung erhält 8 Punkte, eine $O(nq)$-Implementierung 4 Punkte.

**Vorsicht**:

- Explizite oder implizite Imports sind **NICHT** zugelassen, es sei denn, diese sind bereits in der Codevorlage enthalten.
- Nachdem Sie Ihren Code einreichen, wird Ihr Ergebnis in **Prozent** statt in Punkten angezeigt.