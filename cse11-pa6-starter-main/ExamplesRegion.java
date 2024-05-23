import tester.*;
class Point {
  int x;
  int y;
  Point(int x, int y) {
    this.x = x;
    this.y = y;
  }
  boolean belowLeftOf(Point other) {
    return this.x <= other.x && this.y <= other.y;
  }
  boolean aboveRightOf(Point other) {
    return this.x >= other.x && this.y >= other.y;
  }
  double distance(Point other) {
    return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
  }
}
interface Region { // Added an interface declaration with the shared method
  boolean contains(Point p);
}
class RectRegion implements Region { // Declared "implements Region" (the interface)
  Point lowerLeft;
  Point upperRight;
  RectRegion(Point lowerLeft, Point upperRight) {
    this.lowerLeft = lowerLeft;
    this.upperRight = upperRight;
  }
  public boolean contains(Point p) {
    return this.lowerLeft.belowLeftOf(p) && this.upperRight.aboveRightOf(p);
  }
}


class CircRegion implements Region {
  Point center;
  int radius;
  CircRegion(Point center, int radius) {
    this.center = center;
    this.radius = radius;
  }
  public boolean contains(Point p) {
    return this.center.distance(p) < this.radius;    
  }
}
abstract class ComboRegion implements Region{
  Region r1;
  Region r2;
  ComboRegion(Region r1, Region r2) {
    this.r1 = r1;
    this.r2 = r2;
  }
}
class UnionRegion extends ComboRegion {
  UnionRegion(Region r1, Region r2) {
    super(r1, r2);
  }
  public boolean contains(Point p) {
    return this.r1.contains(p) || this.r2.contains(p);
  }
}
class IntersectRegion extends ComboRegion {
  IntersectRegion(Region r1, Region r2) {
    super(r1, r2);
  }
  public boolean contains(Point p) {
    return this.r1.contains(p) && this.r2.contains(p);
  }
}
class SquareRegion implements Region {
  Point center;
  double sideLength;
  SquareRegion(Point center, double sideLength) {
    this.center = center;
    this.sideLength = sideLength;
  }
  public boolean contains(Point p) {
    int xDiff = Math.abs(p.x - this.center.x);
    int yDiff = Math.abs(p.y - this.center.y);
    boolean xInside = (xDiff < (this.sideLength / 2));
    boolean yInside = (yDiff < (this.sideLength / 2));
    return xInside && yInside;
  }
}
class ExamplesRegion {
  CircRegion firstExample = new CircRegion(new Point(10, 5), 4);
  Region s1 = new SquareRegion(new Point(10, 1), 8.0);
  /*
  This version produced an error that we cannot access center:
  Region s1 = new SquareRegion(new Point(10, 1), 8.0);
  Point centerOfSquare = s1.center;
  */
  // IntersectRegion i1 = new IntersectRegion(this.firstExample, this.s1);
  IntersectRegion i1 = new IntersectRegion(this.s1, this.firstExample);
  boolean testIntersect(Tester t) {
    return t.checkExpect(this.i1.contains(new Point(7, 4)), true) &&
           t.checkExpect(this.i1.contains(new Point(10, 8)), false) &&
           t.checkExpect(this.i1.contains(new Point(1, 7)), false);
  }

  boolean testSquare(Tester t) {
    return t.checkExpect(this.s1.contains(new Point(12, 4)), true) &&
          t.checkExpect(this.s1.contains(new Point(15, -4)), false);
  }

  RectRegion r1 = new RectRegion(new Point(30, 40), new Point(100, 200));
  CircRegion cForBoth = new CircRegion(new Point(50, 50), 50);

  UnionRegion u1 = new UnionRegion(this.r1, this.cForBoth);
  UnionRegion u2 = new UnionRegion(this.u1, new RectRegion(new Point(250, 250), new Point(350, 350)));


  // a point inside both, a point outside both, a point inside one and not the other
  boolean testUnion(Tester t) {
    return t.checkExpect(this.u1.contains(new Point(75, 75)), true) &&
           t.checkExpect(this.u1.contains(new Point(300, 300)), false) &&
           t.checkExpect(this.u1.contains(new Point(75, 25)), true) &&
           t.checkExpect(this.u1.contains(new Point(50, 190)), true) &&

           t.checkExpect(this.u2.contains(new Point(300, 300)), true) &&
           t.checkExpect(this.u2.contains(new Point(400, 400)), false);
  }


  boolean containedInBoth(Region r, Region c, Point p) { // Allowed to use Region as a type
    return c.contains(p) && r.contains(p);
  }

  // Can use RectRegion or CircRegion where a Region is expected
  boolean result1 = this.containedInBoth(this.r1, this.cForBoth, new Point(75, 75));
  boolean result2 = this.containedInBoth(this.r1, this.cForBoth, new Point(300, 300));
  boolean result1reversed = this.containedInBoth(this.cForBoth, this.r1, new Point(75, 75));
  boolean result2reversed = this.containedInBoth(this.cForBoth, this.r1, new Point(300, 300));



  RectRegion r2 = new RectRegion(new Point(10, 10), new Point(50, 50));
  Point p1 = new Point(10, 10);
  Point p2 = new Point(50, 50);
  RectRegion r3 = new RectRegion(p1, p2);

  Point toTest1 = new Point(60, 60);
  Point toTest2 = new Point(20, 20);

  boolean testContains(Tester t) {
    return t.checkExpect(this.r1.contains(this.toTest1), true) &&
           t.checkExpect(this.r2.contains(this.toTest1), false) &&
           t.checkExpect(this.r3.contains(this.toTest1), false) &&
           t.checkExpect(this.r1.contains(this.toTest2), false) &&
           t.checkExpect(this.r2.contains(this.toTest2), true) &&
           t.checkExpect(this.r3.contains(this.toTest2), true);
  }

  CircRegion c1 = new CircRegion(new Point(200, 50), 10);
  CircRegion c2 = new CircRegion(new Point(20, 300), 25);

  Point circleTest1 = new Point(209, 50);
  Point circleTest2 = new Point(20, 315);

  boolean testContainsCirc(Tester t) {
    return t.checkExpect(this.c1.contains(this.circleTest1), true) &&
           t.checkExpect(this.c1.contains(this.circleTest2), false) &&
           t.checkExpect(this.c2.contains(this.circleTest1), false) &&
           t.checkExpect(this.c2.contains(this.circleTest2), true);
  }

}



/*
  boolean containedInBoth(RectRegion r, CircRegion c, Point p) {
    // A
    // return c.center.distance(p) < c.radius && r.lowerLeft.belowLeftOf(p) && r.upperRight.aboveRightOf(p);
    // Prefer B to A here because it re-uses the methods we've already written
    // B
    return c.contains(p) && r.contains(p);
    // C
    //return this.c.contains(p) && this.r.contains(p); // SHOULD NOT USE this. HERE!
    // D
    //return this.c.contains(this.p) && this.r.contains(this.p); // SHOULD NOT USE this. HERE!
    // E
    // None of the above
  }
  */
