# Ein ungerichteter, ungewichteter Graph

Im Laufe des Semesters haben Sie die Grundlagen der Graphentheorie sowie verschiedene Graphenalgorithmen kennengelernt. In dieser Aufgabe betrachten wir einen **ungerichteten**, **ungewichteten** Graphen $G = (V,E)$, der als Adjazenzliste gegeben ist.
Folgende Funktionen sind zu implementieren:

1. **IsPath()**: Prüfen Sie, ob $G$ ein **Pfadgraph** ist, wobei angenommen wird, dass $G$ zusammenhängend ist. $G$ ist genau dann ein Pfadgraph, wenn er einen Pfad enthält, der alle Knoten von $G$ verbindet, und $G$ keine Kanten außerhalb dieses Pfades enthält.
2. **EdgeOfTriangle($u$, $v$)**: Gegeben sei eine Kante $e=(u,\,v)$. Prüfen Sie, ob $G$ ein ***Dreieck*** enthält, welches $e$ enthält, d.h., ob $V$ einen Knoten $w$ enthält, sodass alle drei Kanten $(u,\,w)$, $(u,\,v)$ und $(v,\,w)$ in $E$ enthalten sind.
3. **NumberOfComponents()**: Berechnen Sie die Anzahl der Zusammenhangskomponenten von $G$.
4. **LargestPerimeter($v$)**: Gegeben sei ein Knoten $v$. Berechnen Sie den ***größten Perimeter*** von $v$ in $G$, wobei angenommen wird, dass $G$ zusammenhängend ist. Der größte Perimeter von $v$ in $G$ ist die Mächtigkeit der größten Menge von Knoten aus $G$, die alle die gleiche Distanz zu $v$ in $G$ haben.
<!--- Pedantischere Variante: die paarweise abstandsgleich zu $v$ in $G$ sind --->

**Bewertung** (24 Punkte):

1.  **IsPath()** (4 Punkte): Eine Implementierung mit Laufzeit $O(|V|+|E|)$ wird mit 4 Punkten bewertet.
2. **EdgeOfTriangle($u$, $v$)** (6 points): Eine Implementierung mit Laufzeit $O(|V|)$ wird mit 6 Punkten, eine Implementierung mit Laufzeit $O(|V|^2)$ mit nur 3 Punkten bewertet.
3.  **NumberOfComponents()** (6 points): Eine Implementierung mit Laufzeit $O(|V|+|E|)$ wird mit 6 Punkten bewertet.
4.  **LargestPerimeter($v$)** (8 points): Eine Implementierung mit Laufzeit $O(|E|)$ wird mit 8 Punkten, eine Implementierung mit Laufzeit $O(|V|\cdot|E|)$ mit nur 4 Punkten bewertet.

**Achtung**!

- Es dürfen KEINE explizite oder implizite Importe verwendet werden, die nicht bereits in der Codevorlage vorkommen.
- CodeExpert zeigt anstatt des Punktestands den **Prozentsatz** Ihrer Abgabe an.
