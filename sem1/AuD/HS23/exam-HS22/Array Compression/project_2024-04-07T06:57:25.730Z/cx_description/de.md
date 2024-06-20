# Array-Komprimierung

Gegeben ist ein Array bestehend aus $n$ ganzen Zahlen $A[0]$, ..., $A[n-1]$.

Auf dem Array darf folgende Komprimierungsoperation durchgeführt werden: zwei Arrayindizes i < j, für die $A[i] = A[j]$ gilt, können die Elemente $A[i]$, $A[i+1]$, ..., $A[j-1]$, $A[j]$ gemeinsam entfernt werden. Danach wird das Array neu indiziert und weitere Kompressionsschritte dürfen durchgeführt werden.

Sie sind von Komprimierungsoperation regelrecht begeistert. Geben Sie die maximale Anzahl von Komprimierungsoperation aus, die Sie auf dem Array ausführen können.

Zum Beispiel für $n=6$ und $A=[4,5,6,4,6,5]$ ist die Antwort 2: Sie können das Teilarray $[6,4,6]$ entfernen und erhalten als Resultat das komprimierte Array $[4, 5, 5]$. Anschliessend können sie noch [5, 5] entfernen.

Implementieren Sie Ihre Lösung in einer **maxCompressions($n$, $A$)** implementieren.

Tipp: Verwenden sie dynamische Programmierung mit einer Tabelle, deren Eintrag $D[i][j]$ die maximale Anzahl von Komprimierungsoperation, die für das Teilarray $A[i], A[i+1], \dots, A[j-1], A[j]$ ausgeführt werden können, enthält.

**Bewertung** (16 Punkte): Eine Implementierung mit Laufzeit $O(n^3)$ gibt $16$ Punkte. Eine Implementierung, die für $n \leq 10$ unter 0.1 Sekunden braucht, gibt $6$ Punkte.

**Achtung!** Es ist **NICHT** erlaubt zusätzliche Pakete, die nicht bereits Teil der Codevorlage sind, zu importieren.