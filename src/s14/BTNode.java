package s15;

import java.util.ArrayList;
import java.util.List;

public class BTNode {
  Object elt;
  BTNode left, right, parent;
  //-------------------------
  public BTNode(Object e, BTNode l, BTNode r, BTNode p) {
    elt = e;left = l; right = r; parent = p;
  }
  //------------------------------------------------------------
  @Override public String toString() {
    String ls = "left:" + ((  left==null) ? "-" : left.elt);
    String rs = " right:" + (( right==null) ? "-" : right.elt);
    String ps = " parent:" + ((parent==null) ? "-" : parent.elt);
    return "" + elt + "(" + ls + "," + rs + "," + ps + ")";
  }
  //------------------------------------------------------------
  public String toReadableString() {
    List<StringBuilder> b = subtreeBlock(this);
    StringBuilder res = new StringBuilder("\n");
    for(StringBuilder s:b) res.append(s).append('\n');
    return res.toString();
  }
  //------------------------------------------------------------
  //------ Non-public methods
  //------------------------------------------------------------
  
  /* ------------ Example: --------------------
                        9             
             2´´´´´´´´´´ ```````4     
          12´ ``8            6´´ ```7 
  3´´´´´´´ `   ´ ``10     14´ `   1´ `
5´ ``0            ´ `11               
    ´ `13                             
   ------------------------------------------ */
  
  private static final char SPACE = '\u00A0'; // the rarer non-breaking space
  private static final char LMARK = '´';
  private static final char RMARK = '`';
  private static final String FIRST_SEP = ""+LMARK+SPACE+RMARK;
  private static final String OTHER_SEP = ""+SPACE+SPACE+SPACE;

  private static List<StringBuilder> subtreeBlock(BTNode n) {
    List<StringBuilder> ls, rs, res=new ArrayList<>();
    if(n==null) {  // empty subtree
      res.add(new StringBuilder("")); 
      return res;
    } 
    String eltStr=String.valueOf(n.elt);
    //if(eltStr.startsWith(""+SPACE) || eltStr.endsWith(""+SPACE))
    //  System.out.println("possible display problem...");
    ls=subtreeBlock(n.left);
    rs=subtreeBlock(n.right);
    int lw=ls.get(0).length();
    int rw=rs.get(0).length();
    if(lw+rw==0) {  // no child
      res.add(new StringBuilder(eltStr)); 
      return res;
    }
    replaceExtremeSpaces(ls.get(0), SPACE, LMARK);  // "    elt´´´´´"
    replaceExtremeSpaces(rs.get(0), RMARK, SPACE);  // "````elt     "
    String sep = FIRST_SEP;
    for(int i=0;  i<ls.size() || i<rs.size(); i++) {
      StringBuilder l=(i<ls.size()) ? ls.get(i) : blockOf(lw, SPACE);
      StringBuilder r=(i<rs.size()) ? rs.get(i) : blockOf(rw, SPACE);
      res.add(l.append(sep).append(r));
      sep=OTHER_SEP;  // only the first line is joined with ´ `
    }
    StringBuilder first=blockOf(lw+1, SPACE).append(eltStr);
    int pad=(lw+rw+sep.length()) - first.length();
    if(pad>=0) {
      first.append(blockOf(pad, SPACE));
    } else {  // special case where "root line" is longer because of its elt
      StringBuilder suffix=blockOf(-pad, SPACE);
      for(StringBuilder z:res)
        z.append(suffix);
    }
    res.add(0, first);
    //assert first.length()==res.get(1).length() : 
    //      (first.length()+" "+res.get(1).length()); 
    return res;
  }    
  
  // "   ...   " --> "ccc...ddd"
  private static void replaceExtremeSpaces(StringBuilder sb, char c, char d) {
    int n=sb.length();
    for(int i=0;   i<n  && sb.charAt(i)==SPACE; i++) sb.setCharAt(i, c);
    for(int i=n-1; i>=0 && sb.charAt(i)==SPACE; i--) sb.setCharAt(i, d);
  }

  // (3,'a') --> "aaa"
  private static StringBuilder blockOf(int w, char c) {
    StringBuilder r=new StringBuilder("");
    while(w-- > 0) r.append(c);
    return r;
  }

}
