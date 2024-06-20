# Baum-Erweiterung

Gegeben ist ein Baum mit $n$ Knoten, die von $0$ bis $n-1$ durchnummeriert sind, wobei der Index $0$ die Wurzel des Baumes kennzeichnet. Dieser Baum wird als Array mit $n-1$ Tupeln der Form $(U[i], V[i], C[i])$ kodiert. Jedes Tupel entspricht einer Kante von Elternknoten $U[i]$ nach Kindknoten $V[i]$ mit ganzzahligen, positiven Kosten $C[i]$. Des Weiteren ist ein Array mit $n-1$ positiven ganzen Zahlen $D[1],\dots,D[n-1]$ gegeben, deren Gebrauch wir gleich erklären werden.

Man möchte diesen Baum nun um **eine** Kante erweitern, die die Wurzel $0$ mit einem beliebigen anderen Knoten verbindet. Eine zusätzliche Kante von der Wurzel zu Knoten i hat Kosten $D[i]$. 

Um die Struktur des Baumes und den Effekt des Hinzufügens einer Kante zu verstehen, wollen wir folgende Methoden implementieren:

1. **leaves()**: Gibt die Anzahl der Blätter (d.h., der Knoten ohne Kinder) im Baum zurück.

2. **maxChildren()**: Gibt die maximale Anzahl der Kinder eines Knotens im Baum zurück.

3. **longestCycle()**: Gibt die Länge des längsten Kreises zurück, der durch Hinzufügen einer Kante von der Wurzel zu einem anderen Knoten erzeugt werden kann. Die Länge eines Kreises entspricht der Summe der Kosten aller Kanten, die er enthält. (Ein Kreis darf auch aus nur Zwei Knoten bestehen, wenn die zusätzliche Kante die Wurzel mit einem ihrer Kinder verbindet. In diesem Fall ist die Länge gleich der Summe der Kosten von der ursprünglichen Kante und der hinzugefügten Kante.)

4. **minMST()**:  Gibt die minimalen Kosten eines minimalen Spannbaums zurück, der nach Hinzufügen einer Kante von der Wurzel zu einem der anderen Knoten entsteht.  (Beachten Sie, dass, solange keine Kante hinzugefügt wird, der minimale Spannbaum mit dem Baum selbst übereinstimmt, und daher seine Kosten genau der Summe der Kantenkosten im Baum entsprechen. Nur durch Hinzufügen einer Kante können die Kosten eines minimalen Spannbaums gesenkt werden.) Diese Frage ist von der Frage nach dem **longestCycle()** unabhängig -- insbesondere muss die gewählte Kante nicht zwingend einen längsten Kreis erzeugen.

*Tipp*: Unter welchen Bedingungen werden die Kosten eines minimalen Spannbaums durch Hinzufügen einer Kante tatsächlich verringert?

**Bewertungsskala** (24 Punkte):

1.  **leaves()** (4 Punkte): Eine Implementierung mit Laufzeit $O(n)$ gibt $4$ Punkte.
2.  **maxChildren()** (4 Punkte): Eine Implementierung mit Laufzeit $O(n)$ gibt $4$ Punkte.
3.  **longestCycle()** (8 Punkte): Eine Implementierung mit Laufzeit $O(n)$ gibt $8$, eine Implementierung mit Laufzeit $O(n^2)$ gibt $4$ Punkte.
4.  **minMST()** (8 Punkte): Eine Implementierung mit Laufzeit $O(n)$ gibt $8$, eine Implementierung mit Laufzeit $O(n^2 \log n)$ gibt $4$ Punkte.

**Achtung!** Es ist **NICHT** erlaubt, zusätzliche Pakete zu importieren, die nicht bereits Teil der Codevorlage sind.