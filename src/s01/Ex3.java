package s01;

public class Ex3 {
    /// Reverse a string using a CharStack
    /// @param w The input string
    /// @return The reversed string
    static String f(String w) {
        String r = "";
        CharStack s = new CharStack();

        // Pushes the w's chars on the stack
        for (int i=0; i < w.length(); i++)
            s.push(w.charAt(i));

        // Pops the chars to r
        while (!s.isEmpty())
            r += s.pop();

        return r;
    }

    /// Counts the number of occurrences of the char e in the stack s
    /// @param s The character stack where to search
    /// @param e The character to find
    /// @return The occurrences' count
    static int g(CharStack s, char e) {
        CharStack r = new CharStack();
        int c = 0;

        // Transfer s into r (reversed)
        while (!s.isEmpty())
            r.push(s.pop());

        // Transfer back r to s and verify if each character equates to e
        while (!r.isEmpty()) {
            if (r.top() == e) c++;
            s.push(r.pop());
        }

        return c;
    }

}
