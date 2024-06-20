# Palindromic Edit Distance

Given a sequence $A$ of $n$ characters, your task is to compute the minimum number of operations that is required to turn $A$ into a *palindrome*.
A ***palindrome*** is a sequence of characters which reads the same backward as forward, e.g., "level", "noon","racecar". 
We consider the following operations:

- Change the character at any position
- Remove the character at any a position
- Insert a character at any position. 

For example, if $A$ is "ETHZETHZ", the answer is 3.


Please see the Main.java file. The task is to implement the method "Palindromic_Edit_Distance". You are free to create auxiliary methods. You do not need to change other methods. 

You get one point for each passing test set. 
To pass both test sets correctly, your solution has to be in $O(n^2)$ time.


**Attention**: The index of an array in the code template starts at **0** following the convention of Java. 