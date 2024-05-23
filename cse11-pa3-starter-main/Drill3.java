class TextTweet {
    String contents;
    int likes;

    TextTweet(String contents, int likes){
        this.contents = contents;
        this.likes = likes;
    }

    // checks if the string, "@" + username, apppears in the Tweet contents, returning true if it does and false otherwise
    boolean hasMention(String username){
        String newString = "@" + username;
        if (contents.contains(newString)) {
            if (contents.contains(newString + " ") == true) { return true;}
            if (contents.charAt(contents.length() - 1) == newString.charAt(newString.length() - 1)) { return true;} }
        return false;
    }

    // returns true if the tweet has one or more likes, false otherwise.
    boolean hasLike(){
        if (this.likes > 0) { return true;}
        return false;
    }

    // returns a String containing the substring between the first appearance of the @ character in the contents and the first space character after that
    String firstMention(){
        if (contents.contains("@") == false) { return "";}
        int begin = contents.indexOf("@") + 1;
        int end = contents.substring(begin).indexOf(" ");
        if (end == -1 || begin == -1) { 
            return ""; }
        return contents.substring(begin, begin + end);
    }
}


class ReplyTweet {
    TextTweet replyTo;
    String contents;
    int likes;

    ReplyTweet(TextTweet replyTo, String contents, int likes) {
        this.replyTo = replyTo;
        this.contents = contents;
        this.likes = likes;
    }

    //returns true if this ReplyTweet has more likes than the TextTweet it is replying to
    boolean morePopularReply() {
        return this.likes > this.replyTo.likes;
    }

    //returns the sum of the likes on this ReplyTweet and on the TextTweet it is replying to
    int allLikes(){
        return this.likes + this.replyTo.likes;
    }

    //checks if the string, "@" + username, apppears in this ReplyTweet’s contents or in the TextTweet that is being replied to
    boolean hasMention(String username) {
        String newString = "@" + username;
        if (contents.contains(newString)) {
            if (contents.contains(newString + " ") == true) { return true;}
            if (contents.charAt(contents.length() - 1) == newString.charAt(newString.length() - 1)) { return true;} }
        if (replyTo.hasMention(username)) { return true;}
        return false;
    }
}


class Drill3 {
    TextTweet tt1 = new TextTweet("@dummy1 @dummy2", 10);
    TextTweet tt2 = new TextTweet("CSE 11 is a cool class @dummy", 0);
    TextTweet tt3 = new TextTweet("@dummy @dummy2", 5);
    
    ReplyTweet rt1 = new ReplyTweet(tt1, "hi", 2);
    ReplyTweet rt2 = new ReplyTweet(tt3, "@dummy1", 100);

    boolean hasMention1 = tt1.hasMention("dummy");      // false
    boolean hasMention2 = tt2.hasMention("dummy");      // true
    boolean hasMention3 = tt3.hasMention("dummy");      // true


    boolean hasLike1 = tt1.hasLike();       // true
    boolean hasLike2 = tt2.hasLike();       // false

    String firstMention1 = tt1.firstMention();      // "dummy"  
    String firstMention2 = tt3.firstMention();      // "" 

    boolean morePopularReply1 = rt1.morePopularReply();     // false
    boolean morePopularReply2 = rt2.morePopularReply();     // true

    int allLikes1 = rt1.allLikes();     // 12
    int allLikes2 = rt2.allLikes();     // 105

    boolean hasMentionr1 = rt1.hasMention("dummy");     // false
    boolean hasMentionr2 = rt2.hasMention("dummy");     // false
}