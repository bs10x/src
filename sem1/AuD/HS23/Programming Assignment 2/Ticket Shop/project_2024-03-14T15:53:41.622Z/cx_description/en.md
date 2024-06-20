# Ticket Shop

You want to go to a place that is distance $D$ away. There is an array $A$ of $n$ tickets that are availble, where $A[i]$ is the distance that ticket $i$ can cover. You also have $k$ vouchers that allow you to double the distance of **any** $k$ of the tickets that you buy (you can also feel free to not use all of them). Your goal is to calculate the minimum amount of tickets that you need to buy to reach **EXACTLY** distance $D$ (you can assume that this is always possible).

For example, for $D=20$ and $A=[2,4,2,3,1, 3,3,4,3]$. With $k=0$, you can reach 20 with 6 tickets $[3,3,3,3,4,4]$. With $k=1$, you can reach 20 with 5 tickets $[3 \cdot 2,3,3,4,4]$. With $k=2$, you can reach 20 with 4 tickets $[3 \cdot 2, 3 \cdot 2,4,4]$. With $k=3$, you can reach 20 with 3 tickets $[3 \cdot 2, 3 \cdot 2, 4 \cdot 2]$.

You need to implement your solution as a method **minTicket($D$, $k$, $n$, $A$)** in the file Main.java. You get one point for each passing test set. To pass both test sets, your solution is expected to run in time $O(n \cdot D \cdot k)$.

**Attention**: You are **NOT** allowed to use additional imports, other than the imports already included in the code template.