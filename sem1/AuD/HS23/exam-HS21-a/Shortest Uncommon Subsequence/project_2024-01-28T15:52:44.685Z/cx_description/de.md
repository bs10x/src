# Kürzeste nicht-gemeinsame Teilfolge

Gegeben ein String $A$ der Länge $n$ und ein String $B$ der Länge $m$, sodass $A$ keine Teilfolge von $B$ ist und $n \leq m$, ist Ihre Aufgabe, die Länge der **kürzesten nicht-gemeinsamen Teilfolge** von $A$ bezogen auf $B$ zu berechnen, also die Länge einer kürzesten Teilfolge von $A$, die keine Teilfolge von $B$ ist. 
Zum Beispiel, wenn $A = EETTT$ und $B=TETTE$, dann ist $EET$ eine kürzeste nicht-gemeinsame Teilfolge von $A$ bezogen auf $B$ und sie hat Länge 3.


**Bewertung** (16 points):
- Eine Implementierung mit Laufzeit $O(nm^2)$ erhält 16 Punkte, während eine  Implementierung mit Laufzeit $O(2^n \cdot m)$ noch 6 Punkte erhält.


**HINWEIS**
- Sie können eine $(n+1)*(m+1)$ grosse DP-Tabelle verwenden, in der beispielsweise $\mathrm{DP}[i][j]$ die Antwort für die ersten $i$ Zeichen von $A$ und die ersten $j$ Zeichen von $B$ speichert.
- Für ein dynamisches Programm können sie das Problem basierend auf der grössten Position in $B$, die mit dem letzten Zeichen in $A$ übereinstimmt, zerlegen.

**Bemerkung** 
- Das Eingabearray $A$ ist von $0$ aus indiziert und alle Zeichen sind Grossbuchstaben.


**Achtung**!

- Es dürfen KEINE expliziten oder impliziten Importe verwendet werden, die nicht bereits in der Codevorlage vorkommen.
- CodeExpert zeigt anstatt des Punktestands den **Prozentsatz** Ihrer Abgabe an.
