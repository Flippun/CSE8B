
import java.nio.file.*;
import java.util.Arrays;
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
        else                              { return new GreaterQuery(q); }
    }

    static Transform readTransform(String t) {
        if (t.contains("upper"))         { return new UpperTransform(t);}
        if (t.contains("lower"))         { return new LowerTransform(t);}
        if (t.contains("first"))         { return new FirstTransform(t);}
        if (t.contains("last"))          { return new LastTransform(t);}
        else                               { return new ReplaceTransform(t);}
    }

    static boolean matchesAll(Query[] qs, String s) {
        int i = 0;
        for (Query qry: qs) {                                            // iterates through each query and sees if the given line matches the query
            if (qry.matches(s)) { i ++; }
        }
        if (i == qs.length) { return true; }
        else { return false; }
    }

    static String applyAll(Transform[] ts, String s) {              
        String tL = s;
        for (Transform t: ts) {                                          // iterates through each transform and applys in order
            tL = t.transform(tL);
        }
        return tL;
    }

    public static void main(String[] args) {
        String[] lines = FileHelper.getLines(args[0]);
        for (String L: lines) {     
            if (args.length == 1) {                                     // prints if no queries
                System.out.println(L); 
            }
            
            if (args.length >= 2) {    
                String[] Strings = args[1].split("&"); 
                Query[] qs = new Query[] {};                            // creates qs = array of every query
                for (String s: Strings) {
                    Query qry = readQuery(s);
                    qs = Arrays.copyOf(qs, qs.length + 1);
                    qs[qs.length - 1] = qry;
                }
                if (matchesAll(qs, L)) {
                    if (args.length == 2) {                             // prints if no transforms
                        System.out.println(L); 
                    }             
                    else {
                        String[] Strings2 = args[2].split("&");           
                        Transform[] ts = new Transform[] {};                     // creates ts = array of every transform
                        for (String s: Strings2) {
                            Transform t = readTransform(s);
                            ts = Arrays.copyOf(ts, ts.length + 1);
                            ts[ts.length - 1] = t;
                        }
                        System.out.println(applyAll(ts, L));                     // prints after all transforms
                    }
                }
            }
        }
    }
}


interface Query {
    boolean matches(String s);
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


interface Transform {
    String transform(String s);
}

class UpperTransform implements Transform {
    String s1;
    UpperTransform(String s1){
        this.s1 = s1;
    }
    public String transform(String s) {
        return s.toUpperCase();
    }
}

class LowerTransform implements Transform {
    String s1;
    LowerTransform(String s1){
        this.s1 = s1;
    }
    public String transform(String s) {
        return s.toLowerCase();
    }
}

class FirstTransform implements Transform {
    String s1;
    FirstTransform(String s1){
        this.s1 = s1;
    }
    public String transform(String s) {
        int s2 = Integer.parseInt(s1.substring(s1.indexOf("=") + 1, s1.length()));
        if (s2 > s.length()) { return s;}
        else {
            return s.substring(0, s2);
        }
    }
}

class LastTransform implements Transform {
    String s1;
    LastTransform(String s1){
        this.s1 = s1;
    }
    public String transform(String s) {
        int s2 = Integer.parseInt(s1.substring(s1.indexOf("=") + 1, s1.length()));
        if (s2 > s.length()) { return s;}
        else {
            return s.substring(s.length() - s2);
        }
    }
}

class ReplaceTransform implements Transform {
    String s1;
    ReplaceTransform(String s1){
        this.s1 = s1;
    }
    public String transform(String s) {
        String s2 = s1.substring(s1.indexOf("=") + 2, s1.indexOf(";") - 1);
        String s3 = s1.substring(s1.indexOf(";") + 2, s1.length() - 1);
        return s.replaceAll(s2, s3);
    }
}