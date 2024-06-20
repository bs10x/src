# Left and Right

Gegeben sei ein Array $A$ mit $n$ Ganzzahlen, indiziert von $0$ bis $n-1$.

Sie spielen folgendes Spiel. Sie starten mit einem Punktestand von $0$. In jedem Schritt des Spiels können Sie einen der folgenden Züge ausführen:

1. Wenn $A$ mindestens zwei Elemente enthält, können Sie das **äußerst linke** und das **äußerst rechte** Element von $A$ entfernen und zu Ihrem Punktestand den absoluten Wert ihrer Differenz hinzufügen. Das heißt, wenn das äußerst linke und das äußerst rechte Element die Werte $x$ und $y$ haben, können Sie $|x-y|$ zu Ihrem Punktestand hinzufügen.
2. Sie können das **äußerst linke** Element von $A$ entfernen, ohne Ihren Punktestand zu ändern.
3. Sie können das **äußerst rechte** Element von $A$ entfernen, ohne Ihren Punktestand zu ändern.

Ihre Aufgabe ist es, den maximalen Punktestand zu bestimmen, der im Spiel erreicht werden kann. Sie müssen Ihre Lösung als Methode **getMaximumScore($n$, $A$)** implementieren.

*Tipp*: Verwenden Sie dynamische Programmierung, wobei jeder Eintrag $D[i][j]$ in der DP-Tabelle den maximalen Punktestand darstellt, den Sie mit $A[i], ..., A[j]$ erreichen können.

## Bewertung (16 Punkte):

- Eine $O(n^2)$-Implementierung erhält 16 Punkte und eine $O(n^3)$-Implementierung erhält 6 Punkte.

**Achtung**: Es ist Ihnen **NICHT** erlaubt, zusätzliche Imports zu verwenden, die nicht bereits im Code-Template enthalten sind.