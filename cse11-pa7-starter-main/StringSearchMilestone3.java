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
    static Query readQuery(String q) {
        if (q.contains("not"))          { return new NotQuery(q); }
        if (q.contains("contains"))     { return new ContainsQuery(q); }
        if (q.contains("length"))       { return new LengthQuery(q); }
        if (q.contains("less"))         { return new LessQuery(q); }
        if (q.contains("starts"))       { return new StartsQuery(q); }
        if (q.contains("end"))          { return new EndQuery(q); }
        else                               { return new GreaterQuery(q); }
    }
    public static void main(String[] args) {
        String[] lines = FileHelper.getLines(args[0]);
        for (int i = 0; i < lines.length; i ++) {
            if (args.length == 1) { 
                System.out.println(lines[i]); 
            }
           
            else if (args.length >= 2) {   
                Query q1 = readQuery(args[1]);
                if (q1.matches(lines[i])) { System.out.println(lines[i]); }
            }
        }
    }
}

class ContainsQuery implements Query {
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

class LengthQuery implements Query {
    String s1;
    LengthQuery(String s1) {
        this.s1 = s1;
    }

    public boolean matches(String s) {
        int i1 = Integer.parseInt(s1.substring(s1.indexOf("=") + 1, s1.length()));
        if (s.length() == i1) { return true; }
        else { return false; }
    }

}

class GreaterQuery implements Query {
    String s1;
    GreaterQuery(String s1) {
        this.s1 = s1;
    }

    public boolean matches(String s) {
        int i1 = Integer.parseInt(s1.substring(s1.indexOf("=") + 1, s1.length()));
        if (s.length() > i1) { return true; }
        else { return false; }
    }
}

class LessQuery implements Query {
    String s1;
    LessQuery(String s1) {
        this.s1 = s1;
    }

    public boolean matches(String s) {
        int i1 = Integer.parseInt(s1.substring(s1.indexOf("=") + 1, s1.length()));
        if (s.length() < i1) { return true; }
        else { return false; }
    }
}

class StartsQuery implements Query {
    String s1;
    StartsQuery(String s1){
        this.s1 = s1;
    }

    public boolean matches(String s){
        String s2 = s1.substring(s1.indexOf("'") + 1, s1.length() - 1);
        if (s.startsWith(s2)) { return true; }
        else { return false; }
    }
}

class EndQuery implements Query {
    String s1;
    EndQuery(String s1){
        this.s1 = s1;
    }

    public boolean matches(String s){
        String s2 = s1.substring(s1.indexOf("'") + 1, s1.length() - 1);
        if (s.endsWith(s2)) { return true; }
        else { return false; }
    }
}

class NotQuery implements Query {
    String s1;
    NotQuery(String s1){
        this.s1 = s1;
    }

    public boolean matches(String s){
        String s2 = s1.substring(s1.indexOf("(") + 1, s1.indexOf(")"));
        if (StringSearch.readQuery(s2).matches(s)) { return false;}
        else {return true;}
    }

}

interface Query {
    boolean matches(String s);
}