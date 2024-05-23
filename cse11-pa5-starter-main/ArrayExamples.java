import tester.*;

class ArrayExamples {
    String joinWith(String[] strs, String sep) { 
        String s = "";
        for (int i = 0; i < strs.length; i += 1) {
            if (i != strs.length - 1) { s = s.concat(strs[i] + sep); }
            else { s = s.concat(strs[i]); }
        }
        return s;
    }

    boolean allTrue(boolean[] boos) {
        for (int i = 0; i < boos.length; i += 1) {
            if (boos[i] == false) {return false;}
        }
        return true;
    }

    boolean allWithinRange(double[] dbl, double low, double high) {
        for (int i = 0; i < dbl.length; i += 1) {
            if (dbl[i] > high || dbl[i] < low) {return false;}
        }
        return true;
    }
    
    Pair maxmin(int[] ints) {
        int lowest = ints[0];
        int highest = ints[0];
        for (int i = 0; i < ints.length; i += 1) {
            if (ints[i] > highest) {highest = ints[i];}
            if (ints[i] < lowest) {lowest = ints[i];}
        }
        return new Pair(lowest, highest);
    }

    String earliest(String[] strs) {
        String earliest = strs[0];
        for (int i = 0; i < strs.length; i += 1) {
            if (earliest.compareTo(strs[i]) > 0) {earliest = strs[i];}
        }
        return earliest;
    }

    int lookup(String[] keys, int[] values, String key) {
        int index = -1;
        for (int i = 0; i < keys.length; i += 1) {
            if (keys[i].equals(key)) {index = i;}
        }
        if (index != -1) {index = values[index];}
        return index;
    }


    // ----------------------------TESTS----------------------------------


    void testjoinWith(Tester t) {
        t.checkExpect(this.joinWith(new String[] {"a", "b", "c"}, ":"), "a:b:c");
        t.checkExpect(this.joinWith(new String[] {"Wow."}, "----"), "Wow.");
        t.checkExpect(this.joinWith(new String[] {}, "test"), "");
    }

    void testallTrue(Tester t) {
        t.checkExpect(this.allTrue(new boolean[] {true, false, true}), false);
        t.checkExpect(this.allTrue(new boolean[] {true, true}), true);
        t.checkExpect(this.allTrue(new boolean[] {}), true);
    }

    void testallWithinRange(Tester t) {
        t.checkExpect(this.allWithinRange(new double[] {1, 3, 5}, 0, 5), true);
        t.checkExpect(this.allWithinRange(new double[] {5, 10}, 4, 8), false);
        t.checkExpect(this.allWithinRange(new double[] {}, 0, 5), true);
    } 

    void testmaxmin(Tester t) {
        t.checkExpect(this.maxmin(new int[] {1, 3, 5}), new Pair(1, 5));
        t.checkExpect(this.maxmin(new int[] {5, 10}), new Pair(5, 10));
        t.checkExpect(this.maxmin(new int[] {1}), new Pair(1, 1));
    } 

    void testearliest(Tester t) {
        t.checkExpect(this.earliest(new String[] {"alpha", "beta", "gamma"}), "alpha");
        t.checkExpect(this.earliest(new String[] {"phi", "omicron"}), "omicron");
        t.checkExpect(this.earliest(new String[] {"zeta"}), "zeta");
    } 

    void testlookup(Tester t) {
        t.checkExpect(this.lookup(new String[] {"a", "b", "c"}, new int[] {5, 6, 7}, "b"), 6);
        t.checkExpect(this.lookup(new String[] {"a", "b"}, new int[] {10, 20}, "c"), -1);
        t.checkExpect(this.lookup(new String[] {}, new int[] {}, ""), -1);
    }
}

class Pair {
    int a;
    int b;
    Pair(int a, int b) {
        this.a = a;
        this.b = b;
    }
}
