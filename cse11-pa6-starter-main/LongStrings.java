import tester.*;
import java.util.Arrays;

class LongStrings {
    String[] longStrings(String[] strs, int n) {
        String[] result = {};
        for (int i = 0; i < strs.length; i += 1) {
            if (strs[i].length() >= n) {
                result = Arrays.copyOf(result, result.length + 1);
                result[result.length - 1] = strs[i];
            }
        }
        return result;
    }

    void testlongStrings(Tester t) {
        t.checkExpect(this.longStrings(new String[] {"One", "Two", "Three"}, 2), new String[] {"One", "Two", "Three"});
        t.checkExpect(this.longStrings(new String[] {"Hi", "Hello", "Goodbye"}, 5), new String[] {"Hello", "Goodbye"});
        t.checkExpect(this.longStrings(new String[] {"Yes", "No"}, 10), new String[] {});
        t.checkExpect(this.longStrings(new String[] {}, 4), new String[] {});
    }
}



