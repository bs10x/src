a			-> 1 = D_{i, j}
b c			-> 2
d e f		-> 3



Z, S > 1		-> smallest matrix: 2x2


Die Klasse Triangle erlaubt die Darstellung von Z × S Dreiecksmatrizen (von int Werten).
Z und S sind immer strikt grösser als 1 (d.h., > 1). Eine Z × S Dreiecksmatrize hat Z Zeilen X0, X1, ..., XZ−1, wobei Zeile Xi genau (i * (S − 1)/(Z − 1)) + 1 viele Elemente hat.

Dieser Ausdruck wird nach den Regeln für int Ausdrücke in Java ausgewertet. Für eine Dreiecksmatrix D ist D_{i,j} das (j + 1)-te Element in der (i + 1)-ten Zeile. D_{0,0} ist das erste Element in der ersten Zeile [die immer genau 1 Element hat]. Abbildung 1 zeigt Beispiele von Dreiecksmatrizen. Beachten Sie, dass es möglich ist, dass zwei (aufeinanderfolgende) Zeilen die selbe Anzahl Elemente haben.

In der Datei Triangle.java finden Sie die Klasse Triangle mit einem Konstruktor Triangle(int z, int s), der eine z × s Dreiecksmatrix erstellt. Dieser Konstruktor setzt die Werte aller Elemente auf 0. Vervollständigen Sie diese Klasse, so dass die folgenden Methoden unterstützt werden:
1. int get(int i, int j) gibt das Element Di,j zurück.
2. void put(int i, int j, int value) setzt das Element Di,j auf den Wert value.
3. int[] linear() liefert die Elemente in der kanonischen Reihenfolge (die Elemente jeder Zeile mit steigendem Index, und die Zeilen in steigender Reihenfolge).
4. void init(int[] data) ersetzt die Elemente von D durch die Werte in data. Sie dürfen annehmen, dass data genauso viele Elemente hat wie D. Die Methode setzt die Elemente von D, so dass die Folge D.init(data); int[] y = D.linear(); in einen Array y resultiert für
den Arrays.equals(y, data) den Wert true ergibt.
5. void add(Triangle t) Ein Aufruf D.add(t) addiert zu jedem Element D_{i,j} den Wert von t_{i,j}, falls t_{i,j} existiert. Falls t_{i,j} nicht existiert, dann bleibt D_{i,j} unverändert.
