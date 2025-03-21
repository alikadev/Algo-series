package s06;
public interface ShortToStringMapItr {

  public boolean hasMoreKeys();
  
  /** PRE-condition: hasMoreKeys() */
  public short   nextKey();
}
