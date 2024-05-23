
import tester.*;
import java.util.List;
import java.util.Arrays;
import java.util.Comparator;
import java.util.ArrayList;

class Point {
  int x, y;
  Point(int x, int y) {
    this.x = x;
    this.y = y;
  }
  double distance(Point other) {
    return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
  }
}

class PointCompare implements Comparator<Point> {                  
  public int compare(Point p1, Point p2) {
    if (p1.y > p2.y) { return  1; }
    if (p1.y < p2.y) { return -1; }
    if (p1.x > p2.x) { return  1; }
    if (p1.x < p2.x) { return -1; }
    return 0;
  }
}

class PointDistanceCompare implements Comparator<Point> {
  public int compare(Point p1, Point p2) {
    Point p0 = new Point(0, 0);
    if (p1.distance(p0) > p2.distance(p0)) { return 1; }
    else if (p1.distance(p0) < p2.distance(p0)) { return -1; }
    else { return 0; }
  }
}

class StringCompare implements Comparator<String> {
  public int compare(String s1, String s2) {
    return s1.compareTo(s2);
  }
}

class StringLengthCompare implements Comparator<String> {
  public int compare(String s1, String s2) {
    int i1 = s1.length();
    int i2 = s2.length();
    if (i1 > i2) { return  1; }
    if (i1 < i2) { return -1; }
    if (i1 > i2) { return  1; }
    if (i1 < i2) { return -1; }
    return 0;
  }
}

class BooleanCompare implements Comparator<Boolean> {
  public int compare(Boolean b1, Boolean b2) {
    if (b1) { 
      if (b2) { return  0; }
      else    { return  1; } }
    else {
      if (b2) { return -1; } 
      else    { return  0; } }
  }
}


class CompareLists {  
  // iterates through list and returns smallest element
  static <E> E minimum(List<E> L, Comparator<E> C) {         
    if (L.size() == 0) {
      return null;
    }
    E smallest = L.get(0);
    for (E e: L) {
      if (C.compare(smallest, e) > 0) { smallest = e;}
    } 
    return smallest;
  }

  // overload of minimum if given an array instead of list
  static <E> E minimum(E[] a, Comparator<E> C) {            
    if (a.length == 0) {
      return null;
    }
    E smallest = a[0];
    for (E e: a) {
      if (C.compare(smallest, e) > 0) { smallest = e;}
    } 
    return smallest;
  }

  // returns new List containing only elements from list l and larger than given element e1
  static <E> List<E> greaterThan(List<E> l, Comparator<E> C, E e1) {           
    ArrayList<E> E2 =  new ArrayList<E>();
    for (E e: l) {
      if (C.compare(e, e1) > 0) { E2.add(e); }
    }
    return E2;                                                        
  }

  // returns true if elements in list l are in increasing order, false otherwise, throws if any elements are null
  static <E> boolean inOrder(List<E> l, Comparator<E> C) {
    for (E e: l) {
      if (e == null) { 
        throw new IllegalArgumentException("null value in list"); 
      }
    }  
    for (int i = 0; i < l.size() - 1; i ++ ) {
      if (C.compare(l.get(i), l.get(i + 1)) > 0) { return false;}
    }
    return true;
  }

  //overlaod of inOrder if given an array instead of list, still throws
  static <E> boolean inOrder(E[] a, Comparator<E> C) {
    for (E e: a) {
      if (e == null) { 
        throw new IllegalArgumentException("null value in array"); 
      }
    }  
    for (int i = 0; i < a.length - 1; i ++ ) {
      if (C.compare(a[i], a[i + 1]) > 0) { return false;}
    }
    return true;
  }

  // Assumes both l1 and l2 are in increasing order according to C,
  // returns a new list that contains all elements from l1 and l2 in increasing order, throws
  static <E> List<E> merge(Comparator<E> C, List<E> l1, List<E> l2) {
    for (E e: l1) {
      if (e == null) { 
        throw new IllegalArgumentException("null value in first list"); 
      }
    }  
    for (E e: l2) {
      if (e == null) { 
        throw new IllegalArgumentException("null value in second list"); 
      }
    }  
    ArrayList<E> E2 =  new ArrayList<E>();
    for (E e: l1) { E2.add(e); }
    for (E e: l2) { E2.add(e); }
    E2.sort(C);  
    return E2;
  }

  
}


class CompareListTests {
  static Point p1 = new Point(0, 0);                // Points for PointCompare and 
  static Point p2 = new Point(1, 1);                // PointDistanceCompare Tests
  static Point p3 = new Point(2, 2);
  static Point p4 = new Point(1, 2);
  
  void testPointCompare(Tester t) {                          // PointCompare tests
    Comparator<Point> pc = new PointCompare();        
    t.checkExpect(pc.compare(p1, p2), -1);
    t.checkExpect(pc.compare(p3, p1),  1);
    t.checkExpect(pc.compare(p4, p2),  1);
    t.checkExpect(pc.compare(p3, p3),  0);
  }

  void testPointDistanceCompare(Tester t) {                     // PointDistanceCompare tests
    Comparator<Point> pdc = new PointDistanceCompare();
    t.checkExpect(pdc.compare(p1, p2), -1);
    t.checkExpect(pdc.compare(p3, p1),  1);
    t.checkExpect(pdc.compare(p4, p2),  1);
    t.checkExpect(pdc.compare(p3, p3),  0);
  }

  void testStringCompare(Tester t) {                           // StringCompare tests
    Comparator<String> sc = new StringCompare();
    t.checkExpect(sc.compare("B", "A"), 1);
    t.checkExpect(sc.compare("A", "B"), -1);
    t.checkExpect(sc.compare("Hello", "Hi"), -4);
    t.checkExpect(sc.compare("CSE8B", "CSE8B"), 0);
  }

  void testStringLengthCompare(Tester t) {                             // StringLengthCompare tests
    Comparator<String> slc = new StringLengthCompare();
    t.checkExpect(slc.compare("", " "), -1);
    t.checkExpect(slc.compare(" ", ""),  1);
    t.checkExpect(slc.compare("test words", "just test"), 1);
    t.checkExpect(slc.compare("I like turtles.", "I like turtles."), 0);
  }

  void testBooleanCompare(Tester t) {                              // BooleanCompare tests
    Comparator<Boolean> bc = new BooleanCompare();    
    t.checkExpect(bc.compare(true, false),  1);
    t.checkExpect(bc.compare(false, true), -1);
    t.checkExpect(bc.compare(false, false), 0);
    t.checkExpect(bc.compare(true, true),   0);
  }


  static List<Boolean> el1 = Arrays.asList(true, false, true);         // Lists for testing
  static List<Point> el2 = Arrays.asList(p1, p2, p3, p4);                          
  static List<String> el3 = Arrays.asList("CSE8B", "CSE12", "ABCD", "Dog");
  static List<Point> el4 = Arrays.asList();    
  static List<Point> el5 = Arrays.asList(p1, p2, p4, p3);
  static List<Point> el6 = Arrays.asList(p1, p2, null, p3);
  static List<Point> el7 = Arrays.asList(p1, p2);
  static List<Point> el8 = Arrays.asList(p3, p4);
  static List<Boolean> el9 = Arrays.asList(false, false);
  static List<String> el10 = Arrays.asList("Puppy", "Cat");

  void testminimum(Tester t) {                                                      // minimum tests
    t.checkExpect(CompareLists.minimum(el1, new BooleanCompare()),  false);
    t.checkExpect(CompareLists.minimum(el2, new PointCompare()),  p1);
    t.checkExpect(CompareLists.minimum(el3, new StringCompare()),  "ABCD");
    t.checkExpect(CompareLists.minimum(el3, new StringLengthCompare()),  "Dog");
    t.checkExpect(CompareLists.minimum(el4, new PointDistanceCompare()), null);
  }


  static Point[] ea1 = new Point[] {p3, p4};                                       // Arrays for testing
  static String[] ea2 = new String[] {"This is a test", "Hello", "I love cats"};
  static Boolean[] ea3 = new Boolean[] {};
  static String[] ea4 = new String[] {"A", "B", "C"};
  static Boolean[] ea5 = new Boolean[] {false, true, false, null};

  void testminimum2(Tester t) {                                                      // minimum array tests
    t.checkExpect(CompareLists.minimum(ea1, new PointCompare()),  p4);
    t.checkExpect(CompareLists.minimum(ea2, new StringCompare()),  "Hello");
    t.checkExpect(CompareLists.minimum(ea2, new StringLengthCompare()),  "Hello");
    t.checkExpect(CompareLists.minimum(ea3, new BooleanCompare()),  null);
  }

  void testgreaterThan(Tester t) {                                                                // greaterThan tests
    t.checkExpect(CompareLists.greaterThan(el2, new PointCompare(), p2), Arrays.asList(p3, p4));
    t.checkExpect(CompareLists.greaterThan(el1, new BooleanCompare(), false), Arrays.asList(true, true));
    t.checkExpect(CompareLists.greaterThan(el3, new StringLengthCompare(), "I Love CS!"), Arrays.asList());
    t.checkExpect(CompareLists.greaterThan(el4, new PointCompare(), p1), Arrays.asList());
  }

  void testinOrder(Tester t) {                                                                // inOrder tests
    t.checkExpect(CompareLists.inOrder(el1, new BooleanCompare()), false);
    t.checkExpect(CompareLists.inOrder(el2, new PointCompare()), false);
    t.checkExpect(CompareLists.inOrder(el5, new PointCompare()), true);
    t.checkException(new IllegalArgumentException("null value in list"), new CompareLists(), "inOrder", el6, new PointCompare());                 
    t.checkExpect(CompareLists.inOrder(el3, new StringCompare()), false);
  }

  void testinOrder2(Tester t) {                                                                       // inOrder array tests
    t.checkExpect(CompareLists.inOrder(ea1, new PointCompare()), false);
    t.checkExpect(CompareLists.inOrder(ea2, new StringCompare()), false);
    t.checkExpect(CompareLists.inOrder(ea4, new StringCompare()), true);
    t.checkExpect(CompareLists.inOrder(ea3, new BooleanCompare()), true);
    t.checkException(new IllegalArgumentException("null value in array"), new CompareLists(), "inOrder", ea5, new BooleanCompare());    
  }

  void testmerge(Tester t) {                                                                       // merge tests
    t.checkExpect(CompareLists.merge(new BooleanCompare(), el1, el9), Arrays.asList(false, false, false, true, true));
    t.checkExpect(CompareLists.merge(new StringCompare(), el3, el10), Arrays.asList("ABCD", "CSE12", "CSE8B", "Cat", "Dog", "Puppy"));
    t.checkExpect(CompareLists.merge(new PointCompare(), el7, el8), Arrays.asList(p1, p2, p4, p3));
    t.checkException(new IllegalArgumentException("null value in second list"), new CompareLists(), "merge", new BooleanCompare(), el8, el6); 
    t.checkException(new IllegalArgumentException("null value in first list"), new CompareLists(), "merge", new BooleanCompare(), el6, el7); 
  }
}