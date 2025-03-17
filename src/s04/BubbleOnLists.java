package s04;

import s03.List;
import s03.ListItr;

public class BubbleOnLists {

    static void bubbleSortList(List l) {
        if (l.isEmpty()) return;
        boolean goOn = true;
        while (goOn) {
            goOn = false;
            ListItr li = new ListItr(l); // Réinitialisation de l'itérateur à chaque passe

            while (!li.isLast()) { // Parcours jusqu'à l'avant-dernier élément
                if (bubbleSwapped(li)) {
                    goOn = true; // Un échange a eu lieu, il faut refaire une passe
                }
                li.goToNext(); // Passer à l'élément suivant
            }
        }
    }

    static boolean bubbleSwapped(ListItr li) {
        if (li.isFirst() || li.isLast()) return false;

        //todo
        return false;
    }

}


