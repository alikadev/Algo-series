package s13;

import java.util.Arrays;

public class PolynomThing {
  private final double[] coef;
  private final boolean isReducible;
  private final String name;

  public PolynomThing(double[] c, boolean red, String s) {
    int n = c.length;
    coef = new double[n];
    System.arraycopy(c, 0, coef, 0, n);
    isReducible = red;
    name = s;
  }

  @Override public int hashCode() {
    // Compute hash code based on all fields used in equals:
    // coef array content, isReducible boolean, and name string.
    int result = Arrays.hashCode(coef);                     // Hash for the array content
    result = 31 * result + Boolean.hashCode(isReducible);   // Include the boolean value
    result = 31 * result + name.hashCode();                 // Include the string value
    return result;
  }

  public double getCoef(int i) {
    return coef[i];
  }
  public int degree() {
    return coef.length;
  }
  public String name() {
    return name;
  }
  public boolean isReducible() {
    return isReducible;
  }
  
  @Override public boolean equals(Object a) {
    if (this == a) return true;
    if (a == null || getClass() != a.getClass()) return false;
    PolynomThing aa = (PolynomThing)a;
    if (aa.isReducible != this.isReducible) return false;
    if (!aa.name.equals(this.name)) return false;
    if (!Arrays.equals(aa.coef, this.coef)) return false;
    return true;
  }
  
  //-------------------------------------------
  public static void main(String[] args) {
    PolynomThing a = new PolynomThing(new double[]{2,3,4}, true, "foo");
    PolynomThing b = new PolynomThing(new double[]{2,3,5}, true, "bar");
    PolynomThing c = new PolynomThing(new double[]{2,3},   true, "demo");
    PolynomThing d = new PolynomThing(new double[]{2,3,4}, false, "foo");
    PolynomThing e = new PolynomThing(new double[]{2,3,4}, true, "foo");
    PolynomThing f = new PolynomThing(new double[]{2,3,4}, true, "foobb");

    PolynomThing[] somePolygs = new PolynomThing[] {a,b,c,d,e,f};
    for(PolynomThing x: somePolygs)
      System.out.print(x.hashCode()+" ");
    System.out.println();
    for(PolynomThing x: somePolygs)
      for(PolynomThing y: somePolygs) {
        if(x.equals(y) && (x.hashCode() != y.hashCode()))
          System.out.println("Oops: logical equality but different hashCodes!!");
        else if(!x.equals(y) && (x.hashCode() == y.hashCode())) 
          System.out.println("Observing a collision; it can happen but should be rare… Please double-check!");
      }
    System.out.println("The end.");
  }

}
