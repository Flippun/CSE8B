import tester.*;


interface Tweet {
    public boolean isStartOfThreadBy(String author);
    public int totalLikes();
    public String unrollThread();
}

class TextTweet implements Tweet {
    String contents;
    int likes;
    String author;

    public TextTweet(String contents, int likes, String author) {
        this.contents = contents;
        this.likes = likes;
        this.author = author;
    }

    public boolean isStartOfThreadBy(String author) {
        if (author.equals(this.author)) { return true; }
        return false;
    }

    public int totalLikes() {
        return this.likes;
    }

    public String unrollThread() {
        return this.author + "\n" + this.likes + " likes" + "\n" + this.contents + "\n";
    }
}

class ReplyTweet implements Tweet {
    String contents;
    int likes;
    String author;
    Tweet replyTo;

    public ReplyTweet(String contents, int likes, String author, Tweet replyTo) {
        this.contents = contents;
        this.likes = likes;
        this.author = author;
        this.replyTo = replyTo;
    }

    public boolean isStartOfThreadBy(String author) {
        if (this.author.equals(author) && this.replyTo.isStartOfThreadBy(author)) {
            return true; 
        }
        return false;
    }

    public int totalLikes() {
        return this.likes + replyTo.totalLikes();
    }

    public String unrollThread() {
        return this.replyTo.unrollThread() + this.author + "\n" + this.likes + " likes" + "\n" + this.contents + "\n";
    }
}

class Tweets {
    TextTweet t1 = new TextTweet("hello this is a test", 52, "Flip Frauenzimmer");
    TextTweet t2 = new TextTweet("I am also testing something", 12, "Andrew");

    ReplyTweet r1 = new ReplyTweet("what a cool test!", 4, "Andrew", t1);
    ReplyTweet r2 = new ReplyTweet("Update: still testing", 20, "Flip Frauenzimmer", t1);

    
    void testisStartOfThreadBy(Tester t) {
        t.checkExpect(this.t1.isStartOfThreadBy("Flip Frauenzimmer"), true);
        t.checkExpect(this.t2.isStartOfThreadBy("Flip Frauenzimmer"), false);
        t.checkExpect(this.r1.isStartOfThreadBy("Andrew"), false);
        t.checkExpect(this.r2.isStartOfThreadBy("Flip Frauenzimmer"), true);
    }

    void testtotalLikes(Tester t) {
        t.checkExpect(this.t1.totalLikes(), 52);
        t.checkExpect(this.t2.totalLikes(), 12);
        t.checkExpect(this.r1.totalLikes(), 56);
        t.checkExpect(this.r2.totalLikes(), 72);
    }

    void testunrollThread(Tester t) {
        t.checkExpect(this.t1.unrollThread(), "Flip Frauenzimmer" + "\n" + 52 + " likes" + "\n" + "hello this is a test" + "\n");
        t.checkExpect(this.t2.unrollThread(), "Andrew" + "\n" + 12 + " likes" + "\n" + "I am also testing something" + "\n");
        t.checkExpect(this.r1.unrollThread(), "Flip Frauenzimmer" + "\n" + 52 + " likes" + "\n" + "hello this is a test" + "\n" +
                                                        "Andrew" + "\n" + 4 + " likes" + "\n" + "what a cool test!" + "\n");
        t.checkExpect(this.r2.unrollThread(), "Flip Frauenzimmer" + "\n" + 52 + " likes" + "\n" + "hello this is a test" + "\n" +
                                                        "Flip Frauenzimmer" + "\n" + 20 + " likes" + "\n" + "Update: still testing" + "\n");
    }
}