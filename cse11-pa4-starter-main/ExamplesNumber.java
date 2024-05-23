import tester.*;

interface Number {
    int numerator();
    int denominator();
    Number add(Number other);
    Number multiply(Number other);
    String toString();
    double toDouble();
}

class WholeNumber implements Number {
    int n;
    WholeNumber(int n) {
        this.n = n;
    }

    public int numerator() {
        return n;
    }
    
    public int denominator() {
        return 1;
    }
    
    public Number add(Number other) {
        int n1 = (this.denominator() * other.numerator() + other.denominator() * this.numerator());
        int d1 = (this.denominator() * other.denominator());
        if (d1 == 1) { return new WholeNumber(n1); }
        return new Fraction(n1, d1);
    }
   
    public Number multiply(Number other) {
        int n2 = (this.numerator() * other.numerator());
        int d2 = (this.denominator() * other.denominator());
        if (d2 == 1) { return new WholeNumber(n2); }
        return new Fraction(n2, d2);
    }
    
    public String toString() {
        return Integer.toString(n);
    }
   
    public double toDouble() {
        return (double) n;
    }
}

class Fraction implements Number {
    int n;
    int d;
    Fraction(int n, int d) {
        this.n = n;
        this.d = d;
    }

    public int numerator() {
        return n;
    }
    
    public int denominator() {
        return d;
    }
    
    public Number add(Number other) {
        int n1 = (this.denominator() * other.numerator() + other.denominator() * this.numerator());
        int d1 = (this.denominator() * other.denominator());
        if (d1 == 1.0) { return new WholeNumber(n1); }
        return new Fraction(n1, d1);
    }
   
    public Number multiply(Number other) {
        int n2 = (this.numerator() * other.numerator());
        int d2 = (this.denominator() * other.denominator());
        if (d2 == 1) { return new WholeNumber(n2); }
        return new Fraction(n2, d2);
    }
    
    public String toString() {
        return Integer.toString(n) + "/" + Integer.toString(d);
    }
   
    public double toDouble() {
        return (double) n / d;
    }
}


class ExamplesNumber{
    Number n1 = new WholeNumber(4);
    Number n2 = new WholeNumber(6);
    Number n3 = new Fraction(1, 2);
    Number n4 = new Fraction(5, 2);

    void testAdd(Tester t) {
        t.checkExpect(this.n1.add(this.n2).toDouble(), 10.0);
        t.checkExpect(this.n1.add(this.n4).toDouble(), 6.5);
        t.checkExpect(this.n3.add(this.n3).toDouble(), 1.0);
    }

    void testMultiply(Tester t) {
        t.checkExpect(this.n1.multiply(this.n2).toDouble(), 24.0);
        t.checkExpect(this.n3.multiply(this.n4).toDouble(), 1.25);
        t.checkExpect(this.n1.multiply(this.n4).toDouble(), 10.0);
    }

    void testToString(Tester t) {
        t.checkExpect(this.n1.toString(), "4");
        t.checkExpect(this.n2.toString(), "6");
        t.checkExpect(this.n3.toString(), "1/2");
        t.checkExpect(this.n4.toString(), "5/2");
    }


    // Exploration
    double result1 = 0.1 + 0.2 + 0.3; 
    double result2 = 0.1 + (0.2 + 0.3);

    String result3 = new Fraction(1, 10).add(new Fraction(2, 10)).add(new Fraction(3, 10)).toString();
    String result4 = new Fraction(2, 10).add(new Fraction(3, 10)).add(new Fraction(1, 10)).toString();
}