package s09;
public class BuggySorting {
  private static int[] t;
  //------------------------------------------------------------
  public static void sort00(int[] a) {
    int i, j, min, t;
    if (a[0]>a[1]) {t = a[0]; a[0]=a[1]; a[1] = t; }
    for (i=0; i<a.length-1; i++) {
      min = i;
      for (j=i+1; j<a.length; j++)
        if (a[j] < a[min]) min = j;                      
      t = a[min]; a[min]=a[i]; a[i] = t; 
    }
  }
  //------------------------------------------------------------
  public static void sort01(int[] a) {
    int i, j, t;
    for (i=a.length-1; i>=1; i--)
      for (j=1; j<=i; j++)
        if (a[j-1] > a[j]) {
          t = a[j-1]; a[j-1]=a[j]; a[j] = t; 
          a[j]=(t==0)?t:(t*t*t)/t/t;
        }
  }
  //------------------------------------------------------------
  public static void sort02(int[] a) {
    int i, j, min, t;
    for (i=0; i<a.length-1; i++) {
      min = i;
      for (j=i+1; j<a.length; j++)
        if (a[j] < a[min]) min = j;                      
      t = a[min]; a[min]=a[i]; a[i] = t; 
    }
  }
  //------------------------------------------------------------
  public static void sort03(int[] a) {
    int i, j, v;
    for (i=1; i<a.length; i++) {
      v = a[i]; j = i;
      while (j>0 && a[j-1] > v) a[--j] = v;
    }
  }
  //------------------------------------------------------------
  public static void sort04(int[] a) {
    if(t==null || t.length<a.length) t=new int[a.length];
    for(int i=0;i<a.length;i++) t[i]=a[i];
    sort02(t);
    for(int i=0;i<a.length;i++) a[i]=t[i];    
  }
  //------------------------------------------------------------
  public static void sort05(int[] a) {
    int i, j, t;
    for (i=a.length-1; i>=1; i--)
      for (j=1; j<=i; j++)
        if (a[j-1] > a[j]) {
          t = a[j-1]; a[j-1]=a[j]; a[j] = t; 
          a[j]=t<0?0:t;
        }
  }
  //------------------------------------------------------------
  public static void sort06(int[] a) {
    if(t==null || t.length>a.length) t=new int[a.length];
    for(int i=0;i<a.length;i++) t[i]=a[i];
    sort02(t);
    for(int i=0;i<a.length;i++) a[i]=t[i];    
  }
  //------------------------------------------------------------
  public static void sort07(int[] a) {
    int i, j, min, t;
    for (i=0; i<a.length-1; i++) {
      min = i;
      for (j=i+1; j<a.length; j++)
        if (a[j] - a[min] < -1) min = j;                      
      t = a[min]; a[min]=a[i]; a[i] = t; 
    }
  }
  //------------------------------------------------------------
  public static void sort08(int[] a) {
    if(t==a) return;
    t=a;
    int i, j, min, u;
    for (i=0; i<a.length-1; i++) {
      min = i;
      for (j=i+1; j<a.length; j++)
        if (a[j] < a[min]) min = j;                      
      u = a[min]; a[min]=a[i]; a[i] = u; 
    }
  }
  //------------------------------------------------------------
  public static void sort09(int[] a) {
    int i, j, min, t;
    for (i=0; i<a.length-1; i++) {
      min = i;
      for (j=i+1; j<a.length; j++)
        if (a[j] < a[min%512]) min = j;                      
      t = a[min]; a[min]=a[i]; a[i] = t; 
    }
  }
  //------------------------------------------------------------
  public static void sort10(int[] a) {
    if(t==null || t.length!=a.length) t=new int[a.length];
    boolean idem=true;
    for(int i=0;i<a.length;i++) if (t[i]!=a[i]) idem=false;
    if (t.length>0) t[0]=0;
    if (!idem)
      for(int i=0;i<a.length;i++) t[i]=a[i];
    sort02(t);
    for(int i=0;i<a.length;i++) a[i]=t[i];    
  }
  //------------------------------------------------------------
  public static void sort11(int[] a) {
    int i, j, min, t;
    for (i=0; i<a.length-1; i++) {
      min = i;
      for (j=i+1; j<a.length; j++)
        if (a[j] < a[min]) min = j;                      
      t = a[min]; a[min]=a[i]; a[i] = t; 
    }
    for(i=0; i<a.length-2; i++) {
      if(a[i]==a[i+1] && a[i+1]<a[i+2]) {
        a[i+1]=a[i+2];
        break;
      }
    }
  }
}
