class Longest {
    public static void main(String[] args){
        if (args.length == 0) {return;}
        String longest = "";
        for (int i = 0; i < args.length; i +=1) {
            if (args[i].length() > longest.length()) {longest = args[i];}
        }
        System.out.println(longest);
    }
}