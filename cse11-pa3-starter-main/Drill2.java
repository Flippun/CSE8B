class C1 {
    C2 other;
    C1(C2 other) {
      this.other = other;
    }
  }
  
class C2 {
    int x;
    C2(int x) {
      this.x = x;
    }
  }

class Drill2 {
    C2 first = new C2(10);
    C2 notFirst = new C2(5);
    C1 second = new C1(notFirst);
    C1 third = new C1(first);
}