import java.util.Arrays;
import tester.*;

class Pair {
    int a;
    int b;
    Pair(int a, int b) {
        this.a = a;
        this.b = b;
    }
}

class PairSelect {
    int[] getAs(Pair[] pairs) {
        int[] result = {};
        for (int i = 0; i < pairs.length; i += 1) {
            int a = pairs[i].a;
            result = Arrays.copyOf(result, result.length + 1);
            result[result.length - 1] = a;
        }
        return result;
    }

    void testgetAs(Tester t) {
        t.checkExpect(this.getAs(new Pair[] {new Pair(1, 2), new Pair(2, 3), new Pair(3, 4)}), new int[] {1, 2, 3});
        t.checkExpect(this.getAs(new Pair[] {new Pair(5, 2), new Pair(5, 4)}), new int[] {5, 5});
        t.checkExpect(this.getAs(new Pair[] {new Pair(0, 0), new Pair(-1, 5), new Pair(3, 4), new Pair(100, 100)}), new int[] {0, -1, 3, 100});
        t.checkExpect(this.getAs(new Pair[] {}), new int[] {});
    }
}
