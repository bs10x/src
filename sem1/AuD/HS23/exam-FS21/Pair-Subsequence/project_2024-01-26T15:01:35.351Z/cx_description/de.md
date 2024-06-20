# Paar-Teilfolge

Gegeben sei ein Array $A$ mit $n$ Integers, davon einige positiv und einige negativ, mit Indizes zwischen $0$ und $n-1$.

Sie erhalten den Auftrag, die maximale Summe einer *Paar-Teilfolge* von $A$ zu berechnen. Eine Paar-Teilfolge ist eine Teilfolge, die folgende Eigenschaften erfüllt:

- gehört das Element mit Index $i$ zur Teilfolge, dann gehört auch entweder das Element mit Index $i-1$ oder das Element mit Index $i+1$ dazu
- keine drei aufeinanderfolgende Indizes gehören zur Teilfolge

Informeller gesagt erhält man eine Paar-Teilfolge, indem man nichtadjazente Paare von aufeinanderfolgenden Elementen von $A$ auswählt. Eine Paar-Teilfolge darf auch leer sein; in diesem Fall beträgt die Summe ihrer Elemente $0$.

Betrachten wir zum Beispiel die Folge $A=[1, 2, 3, 3, 2, 2, -10, 10]$ mit $n=8$. Die maximale Summe einer Paar-Teilfolge von $A$ erhält man für die Indizes $1, 2, 4, 5$, die den Werten $2, 3, 2, 2$ entsprechen, und die maximale Summe $9$ ergeben.

In dieser Aufgabe sollen Sie die die maximale Summe einer Paar-Teilfolge von $A$ zurückgeben.

Ihre Lösung implementieren Sie in einer Methode **getMaximumSum($n$, $A$)**.

**Bewertung** (16 Punkte):

- Eine $O(n^2)$-Lösung erhält 6 Punkte, eine $O(n)$-Lösung erhält 16 Punkte.

**Vorsicht**: 

- Explizite oder implizite Imports sind **NICHT** zugelassen, es sei denn, diese sind bereits in der Codevorlage enthalten.
- Nachdem Sie Ihren Code einreichen, wird Ihr Ergebnis in **Prozent** statt in Punkten angezeigt.