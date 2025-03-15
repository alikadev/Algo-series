# S04, Ex.3

Parmi les 4 algorithmes discutés au cours (sélection, insertion, bubble, Shell), lesquels sont instables ?

Justifiez votre réponse !

## SelectionSort

Généralement stable mais dépend de la téchnique permettant de trouver "l'index du maximum".

- Si on compare `maxValue > array[i]`, alors le maximum sera la premier dans la liste (donc stable)
- Si on compare  `maxValue >= array[i]`, alors le maximum sera le dernier dans la liste (donc instable)

Vu que la second option est très rarement utilisé et donc on peut se permettre de dire que *SelectionSort est 
généralement stable*

## InsertionSort

Cette méthode est stable. 

Vu que la nouvelle valeur à inséré va être swap un minimum de fois (pour ne pas iterer à travers des éléments similaires), 
alors la dernière valeur `k` insérer sera à la fin du "chunk" des `k`

## BubbleSort

Cette méthode est stable.

Vu que durant chaque itération, deux éléments similaires ne vont pas être swap, alors la méthode est stable

## ShellSort

Cette méthode est instable pour un k-sorting différent de 1

Si le k-sorting est différent de 1, alors le tableau est vituellement découpé en plusieurs sous-tableau où chaque table est
trié séparément. Cela peut causer un échange de valeurs similaires dans le véritable tableau.
