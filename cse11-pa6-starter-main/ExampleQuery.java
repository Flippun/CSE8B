class ContainsQuery{
    String s1;
    ContainsQuery(String s1){
        this.s1 = s1;
    }

    boolean matches(String s){
        if (s.contains(s1)) { return true;}
        else { return false;}
    }
}

class ExampleQuery {
    ContainsQuery query = new ContainsQuery("hello");
    boolean result1 = query.matches("helloworld"); // true
    boolean result2 = query.matches("Hello world"); //false
}