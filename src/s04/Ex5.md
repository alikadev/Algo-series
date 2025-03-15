# S04 Ex.5

Expliquer l'effet de la méthode f().

``` java
/// Remove and returns the minimum from a list from an iterator. Also rewinds
/// The iterator to it's original position at the end
/// PRE: li != null
/// PRE: !li.isLast()
/// @param li The iterator to the start of the list
/// @return The minimum value just removed from the list
static int f(ListItr li) {
    // The minimum index
    int m = li.consultAfter();
    // i: the distance to the current minimal value
    // j: the distance to the initial position
    int i=1, j=1;
    // Iterate and find the minimal
    li.goToNext();
    while(!li.isLast()) {
        i++; j++;
        int e=li.consultAfter();
        if (e<m) {m=e; i=1;}
            li.goToNext();
    }
    // Remove the minimum
    while(i>0) {li.goToPrev(); i--; j--;}
    li.removeAfter();
    // Rewind to the initial position
    while(j>0) {li.goToPrev(); j--;}
    return m;
}
```
    
