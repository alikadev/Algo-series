package s15;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class ExoBTree {

  public static String breadthFirstQ(BTNode t) {
    String res="";
    Queue<BTNode> q = new LinkedList<BTNode>();
    q.add(t);
    while(! q.isEmpty()) {
      BTNode crt = q.remove(); 
      if (crt==null) continue;
      res += (" "+crt.elt); 
      q.add(crt.left );
      q.add(crt.right);
    }
    return res;
  }

  public static int size(BTNode t) {
    return 0; // TODO ...
  }

  public static int height(BTNode t) {
    return 0; // TODO ...
  }

  public static String breadthFirstR(BTNode t) {
    return null; // TODO ...
  }

  public static String visitLevel(BTNode t, int level) {
    return null; // TODO ...
  }

  public static void depthFirst(BTNode t) {
    if (t==null) return;
    System.out.print(" "+t.elt);
    depthFirst(t.left );
    depthFirst(t.right);
  }

  public static void rotateRight(BTNode y) {
    // TODO ...
  }
  // ------------------------------------------------------------
  // ------------------------------------------------------------
  // ------------------------------------------------------------
  private static Random rnd = new Random();
  // ------------------------------------------------------------
  public static BTNode rndTree(int size) {
    if (size==0) return null;
    BTNode root = new BTNode(Integer.valueOf(0), null, null, null);
    BTNode t=root;
    boolean isLeft;
    for(int i=1; i<size; i++) {
      t=root;
      while(true) {
        isLeft=rnd.nextBoolean();
        if (isLeft){
          if (t.left ==null) break;
          t=t.left;
        }else{
          if (t.right==null) break;
          t=t.right;
        }
      }
      BTNode newLeaf = new BTNode(Integer.valueOf(i), null, null, t);
      if (isLeft) t.left =newLeaf;
      else        t.right=newLeaf;
    }
    return root;
  }
  // ------------------------------------------------------------
  public static void main(String[] args) {
    int nbOfNodes = 10;
    BTNode t = rndTree(nbOfNodes);
    System.out.println("Tree:" +t);
    System.out.println(t.toReadableString());
    System.out.println("\nDepth first (recursive), preorder:");
    depthFirst(t); 
    System.out.println("\nBreadth first:");
    System.out.println(breadthFirstQ(t));
    System.out.println("\nBreadth first bis:");
    System.out.println(breadthFirstR(t));
    System.out.println("\nSize:" + size(t));
    System.out.println("\nHeight:" + height(t));
  }
}

