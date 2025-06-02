package s15;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class ExoBTreeTestJU {
  // ------------------------------------------------------------
  static void checkValidTree(BTNode root) {
    if (root == null) return ;
    HashSet<BTNode> s = new HashSet<>();
    s.add(root);
    assertNull(root.parent);
    checkValidSt(root.left, root, s);
    checkValidSt(root.right, root, s);
  }
  static void checkValidSt(BTNode t, BTNode p, HashSet<BTNode> s) {
    if (t == null) return;
    assertFalse(s.contains(t));
    assertEquals(p, t.parent);
    s.add(t);
    checkValidSt(t.left, t,s);
    checkValidSt(t.right, t,s);
  }
  // ------------------------------------------------------------
  static int h(BTNode t) {
    return (t==null) ? 0 : (1+Math.max( h(t.left), h(t.right) ));
  }
  // ------------------------------------------------------------
  static void rl(BTNode x) {
    assertNotNull(x.right);
    BTNode y = x.right, p = x.parent, b=y.left;
    y.parent = p; y.left = x; x.parent = y; x.right = b;
    if (b!=null) b.parent=x;
    if (p==null) return;
    if (p.left == x)  p.left  = y; else p.right = y;
  }
  // ------------------------------------------------------------
  private static BTNode[] nodesWithLeft(BTNode t) {
    ArrayList<BTNode> v=new ArrayList<>();
    nodesWithLeft(t, v);
    return v.toArray(new BTNode[v.size()]);
  }
  private static void nodesWithLeft(BTNode t, ArrayList<BTNode> v) {
    if (t==null) return;
    if (t.left!=null) v.add(t);
    nodesWithLeft(t.left, v); nodesWithLeft(t.right, v);
  }
  // ------------------------------------------------------------
  static String withoutSpaces(String a) {
    String res = "";
    for(int i=0;i<a.length();i++)
      if (a.charAt(i) != ' ') res += a.charAt(i);
    return res;
  }
  // ------------------------------------------------------------
  @Test void testBreadthSizeHeight() {
    int maxSize = 100;
    for (int i = 1; i < maxSize; i++) {
      BTNode t = ExoBTree.rndTree(i);
      assertEquals(i, ExoBTree.size(t));
      assertEquals(h(t), ExoBTree.height(t));
      String a = ExoBTree.breadthFirstQ(t);
      String b = ExoBTree.breadthFirstR(t);
      assertEquals(withoutSpaces(a), withoutSpaces(b));
      checkValidTree(t);
    }
  }
  // ------------------------------------------------------------
  @Test void testRotate() {
    int maxSize = 100;
    BTNode t;
    String a, at, ar, b, bt, br;
    for (int i = 1; i < maxSize; i++) {
      t = ExoBTree.rndTree(i);
      a = ExoBTree.breadthFirstQ(t);
      at = t.toString(); ar = t.toReadableString();
      BTNode[] rr=nodesWithLeft(t);
      for (BTNode ti: rr) {
        boolean wasRoot = (ti.parent == null);
        ExoBTree.rotateRight(ti);
        checkValidTree(wasRoot ? t.parent : t);
        rl(ti.parent);
        b = ExoBTree.breadthFirstQ(t);
        bt = t.toString(); br = t.toReadableString();
        assertEquals(a, b);
        assertEquals(at, bt);
        assertEquals(ar, br);
      }
    }
  }
}
