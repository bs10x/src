# Graphen Mengen

Gegeben sei ein ungerichteter, zusammenhängender Graph mit $n$ Knoten und $m$ Kanten. Die Knoten sind von $0$ bis $n-1$ 
nummeriert. 

Die Knoten des Graphen sind auf die folgende Weise in Mengen unterteilt. Der Knoten $0$ bildet alleine eine Menge.
Stellen Sie sich jetzt vor, dass Knoten $0$ entfernt wird. Die Knoten in jeder Zusammenhangskomponente des daraus resultierenden Graphen bilden jeweils eine weitere Menge.

Unten sehen Sie ein Beispiel, in dem die Knoten entsprechend der Menge, in der sie sich befinden, eingefärbt sind. Insbesondere sind die Mengen in diesem Beispiel $\{0\}$, $\{1\}$, $\{2, 3, 5, 6\}$ und $\{4, 7\}$.

![graph_sets.png](/cx_project_file/X3CJPbWziH2phLoFv)

Gegeben einen solchen Graphen, ist Ihre Aufgabe, Anfragen der folgenden Art zu beantworten:

1. **hasCycle()**: Geben Sie $1$ aus falls der Graph einen Kreis hat. Ansonsten geben Sie $0$ aus.

2. **hasCycleWithoutNodeZero()**: Stellen Sie sich vor, dass Knoten $0$ entfernt wird. Geben Sie $1$ aus, falls der daraus resultierende Graph einen Kreis hat. Ansonsten geben Sie $0$ aus.

3. **isSameSet($x$, $y$)**: Für zwei gegebene Knoten $x$ und $y$, geben Sie $1$ aus falls sie in der gleichen Menge sind. Ansonsten geben Sie $0$ aus.

4. **getShortestPath($x$, $y$)**: Für zwei gegebene Knoten $x$ und $y$, die in **unterschiedlichen Menge sind**, geben Sie die Länge eines kürzesten Pfades zwischen den beiden Knoten aus.

Ihre Aufgabe besteht darin, die vier oben beschriebenen Methoden zu implementieren. 
Ausserdem stellen wir Ihnen eine Methode **initialize()** zur Verfügung, welche garantiert ausgeführt wird bevor die erste Anfrage gestellt wird. 
Sie dürfen diese Methode verändern, um z.B. Informationen zu initialisieren, die Sie in allen Anfragen zur Verfügung haben wollen. 
Natürlich zählt die Zeit, die für **initialize()** verstreicht, für das Zeitlimit des Problems.

Um Ihre Implementierung zu vereinfachen, **denken Sie daran und benutzen Sie**, dass der Graph zusammenhängend ist.

**Bewertung** (24 Punkte):

1. **hasCycle()** (4 Punkte): Diese Anfrage wird höchstens ein Mal gestellt. Eine $O(n+m)$-Zeit Implementierung gibt $4$ Punkte.
2. **hasCycleWithoutNodeZero()** (4 Punkte): Diese Anfrage wird höchstens ein Mal gestellt. Eine $O(n+m)$-Zeit Implementierung gibt $4$ Punkte.
3. **isSameSet($x$, $y$)** (8 points): Sei $q$ die Anzahl Anfragen dieser Art. Eine $O(n+m+q)$-Zeit Implementierung gibt 8 Punkte und eine $O(q(n+m))$-Zeit Implementierung 4 Punkte.
4. **getShortestPath($x$, $y$)** (8 points): Sei $q$ die Anzahl Anfragen dieser Art. Eine $O(n+m+q)$-Zeit Implementierung gibt 8 Punkte und eine $O(q(n+m))$-Zeit Implementierung 4 Punkte.

**Achtung**!

- Es dürfen KEINE expliziten oder impliziten Importe verwendet werden, die nicht bereits in der Codevorlage vorkommen.
- CodeExpert zeigt anstatt des Punktestands den **Prozentsatz** Ihrer Abgabe an.
