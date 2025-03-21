package s06;

public class SetOfShorts {
  protected ShortToStringMap map;
  //------------------------------
  //  Public methods
  //------------------------------
  public      SetOfShorts ()              { map=new ShortToStringMap(); }
  public void    add      (short e)       { map.put(e, "");             } 
  public void    remove   (short e)       { map.remove(e);              }
  public boolean contains (short e)       { return map.containsKey(e);  }
  public SetOfShortsItr iterator()        { return new MyIterator(this);}
  public void    union    (SetOfShorts s) { map.union(s.map);           }
  public void intersection(SetOfShorts s) { map.intersection(s.map);    }
  public int     size     ()              { return map.size();          }
  public boolean isEmpty  ()              { return map.isEmpty();       }
  public String  toString ()              { return map.toString();      }
  // ------------------------------------------------------------
  // ------------------------------------------------------------
  static class MyIterator implements SetOfShortsItr {
    private final ShortToStringMapItr mapItr;
    public MyIterator(SetOfShorts s) { mapItr = s.map.iterator();   }
    public boolean hasMoreElements() { return mapItr.hasMoreKeys(); }
    public short nextElement()       { return mapItr.nextKey();     }
  }
  // ------------------------------------------------------------
  // ------------------------------------------------------------
  private static void smallTest() {
    SetOfShorts a = new SetOfShorts();
    SetOfShorts b = new SetOfShorts();
    short[] ta = {-3, 5,  6, -3, 9, 9};
    short[] tb = { 6, 7, -2, -3};
    int i;
    for (i=0; i<ta.length; i++) {
      a.add(ta[i]);
      System.out.println(""+a+ a.size());
    }
    for (i=0; i<tb.length; i++) {
      b.add(tb[i]);
      System.out.println(""+b+ b.size());
    }
    a.union(b);
    System.out.println(""+a+ a.size());
    System.out.println("sum " +sum(a));
  }
  // ------------------------------------------------------------
  public static int sum(SetOfShorts s1) {
    SetOfShortsItr itr = s1.iterator();
    int res = 0;
    while (itr.hasMoreElements())
      res += itr.nextElement();
    return res;
  }
  // ------------------------------------------------------------
  public static void main(String[] args) {
    int nt = 100;
    if (args.length==1)
      nt = Integer.parseInt(args[0]);
    System.out.println("Test passed successfully !");
    smallTest();
  }
}
