# Längste Zweierpotenz-Teilfolge

Sie erhalten ein Array bestehend aus $n$ Ganzzahlen zwischen (und beinhaltend) $0$ und $2^{20} - 1 = 1048575$. 
Ihre Aufgabe ist es, die Länge der längsten Teilfolge zu berechnen, in der die absolute Differenz zwischen zwei aufeinanderfolgenden Elementen eine Zweierpotenz ist (d.h., ein Element von $1, 2, 4, 8$ usw.). 
Beachten Sie, dass die Elemente der Teilfolge sich nicht in aufeinanderfolgenden Positionen im ursprünglichen Array befinden müssen.

Zum Beispiel liefert das Eingabe-Array $1$, $6$, $3$, $7$, $3$, $9$, die Antwort $4$. Dies gilt, da eine gültige Teilfolge der Länge $4$ existiert. 
Es handelt sich dabei um $1$, $3$, $7$, $3$. Andererseits existiert keine gültige Teilfolge der Länge $5$.

Sie müssen Ihre Lösung als Methode **getLongestSubsequence($n$, $A$)** implementieren, wobei $A$ das gegebene Array ist.

**Bewertung** (16 Punkte):

- Sei $h$ der maximale Wert einer Ganzzahl in der Eingabe. D.h., $h$ ist höchstens $2^{20}-1 = 1048575$. 
Eine Implementierung mit Laufzeit $O(h + n \log h)$ wird mit 16 Punkten und eine Implementierung mit Laufzeit $O(n^2)$ mit 6 Punkten bewertet.

**Achtung**!

- Es dürfen KEINE expliziten oder impliziten Importe verwendet werden, die nicht bereits in der Codevorlage vorkommen.
- CodeExpert zeigt anstatt des Punktestands den **Prozentsatz** Ihrer Abgabe an.
