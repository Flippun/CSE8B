import java.nio.file.*;
import java.io.IOException;
class FileHelper {
    static String[] getLines(String path) {
        try {
            return Files.readAllLines(Paths.get(path)).toArray(String[]::new);
        }
        catch(IOException e) {
            System.err.println("Error reading file " + path + ": " + e);
            return new String[]{"Error reading file " + path + ": " + e};
        }
    }
}


class StringSearch{
    public static void main(String[] args) {
        String[] lines = FileHelper.getLines(args[0]);
        for (int i = 0; i < lines.length; i ++) {
            if (args.length == 1) { 
                System.out.println(lines[i]); 
            }

            else if (args.length >= 2) {      
                ContainsQuery cq1 = new ContainsQuery(args[1]);
                if (cq1.matches(lines[i])) { System.out.println(lines[i]); }
            }
        }
    }
}

class ContainsQuery {
    String s1;
    ContainsQuery(String s1){
        this.s1 = s1;
    }

    public boolean matches(String s){
        String s2 = s1.substring(s1.indexOf("'") + 1, s1.length() - 1);
        if (s.contains(s2)) { return true; }
        else { return false; }
    }
}