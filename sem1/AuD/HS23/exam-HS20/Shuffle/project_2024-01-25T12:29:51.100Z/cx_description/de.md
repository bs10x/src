# Shuffle

Gegeben drei Strings $A$, $B$ und $C$ mit $|A|=n$, $|B|=m$ und $|C|=n+m$, sollen Sie entscheiden, ob $C$ ein ***Shuffle*** von $A$ und $B$ ist.
Ein *Shuffle* von $A$ und $B$ ist eine Mischung der Buchstaben aus $A$ und $B$ in einen neuen String, wobei die interne Reihenfolge der Buchstaben aus $A$ beibehalten wird, und ebenso die interne Reihenfolge der Buchstaben von $B$ beibehalten wird.
Zum Beispiel: $C$ = **P**_INE_**AP**_P_**LE** ist ein Schuffle von $A$ = **PAPLE** und $B$ = _INEP_.

**Bewertung** (16 Punkte):
* Eine Implementierung in Zeit $O(n\cdot m)$ oder $O((n+m)^2)$ gibt 16 Punkte. Eine Implementierung in Zeit $O(2^{n+m})$ gibt noch 5 Punkte.

**Hinweis**: Die Indizierung der Inputarrays $A$, $B$ und $C$ startet im Code mit $1$, nicht mit $0$. 

**Achtung**:

- Ausser Imports, die schon im Template vorkommen, dürfen Sie keine weiteren expliziten oder impliziten Imports verwenden.
- CodeExpert zeigt Ihnen keine Punkte an, sondern den **Prozentsatz**, den Ihre Submission erfüllt.
