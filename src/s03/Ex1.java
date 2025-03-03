package s03;

public class Ex1 {
    /// Gets the i-th element of the list l
    /// PRE: l != null
    /// PRE: i >= 0
    /// PRE: i < l.size()
    /// @param l The input list
    /// @param i The element's index
    /// @return The value of the i-th element
    static int g(List l, int i) {
        ListItr li = new ListItr(l);
        for(int k=0; k<i; k++)
            li.goToNext();
        return li.consultAfter();
    }

    /// Verify if the element e is present in the list l
    /// PRE: l != null
    /// @param l The input list
    /// @param e The element to find
    /// @return true if the element is present in the list, false otherwise
    static boolean f(List l, int e) {
        ListItr li = new ListItr(l);
        while(!li.isLast()) {
            if (li.consultAfter()==e) return true;
            li.goToNext();
        }
        return false;
    }
}
