class User{
    String userName;
    String displayName;
    int followers;

    User(String userName, String displayName, int followers) {
        this.userName = userName;
        this.displayName = displayName;
        this.followers = followers;
    }
 
    // combines the displayname and username of the user
    String toText(){
        return displayName + " @" + userName;
    }
}

class Tweet{
    String tweetText;
    int tweetLikes;
    String tweetID;
    User userReference;

    Tweet(String tweetText, User userReference, int tweetLikes, String tweetID) {
        this.tweetText = tweetText;
        this.userReference = userReference;
        this.tweetLikes = tweetLikes;
        this.tweetID = tweetID;
    }

    // returns true if length of tweet is greater than that of other tweet
    boolean longerThan(Tweet other) {
        return this.tweetText.length() > other.tweetText.length();
    }

    // returns true if the number of likes of tweet is more than that of other tweeet
    boolean moreLikes(Tweet other) {
        return this.tweetLikes > other.tweetLikes;
    }

    // returns String of user information relating to the tweet, tweet content, and tweet likes
    String toText() {
        return  this.userReference.toText() + " : " + tweetText + " : " + tweetLikes + " Likes";
    }

    // returns a String of the URl for the tweet
    String toLink() {
        return "https://twitter.com/" + userReference.userName + "/status/" + tweetID;
    }
}

class ExampleTweets{
    User u1 = new User("acmucsd", "ACM @ UCSD", 54);
    User u2 = new User("cassidoo", "Cassidy", 185200);
    User u3 = new User("UCSDJacobs", "UCSD Engineering", 13700);

    //https://twitter.com/acmucsd/status/1394184426817343488        I was able to include all parts of the tweet using this class design.
    Tweet t1 = new Tweet("Ants are cool", u1, 3, "1394184426817343488");

    //https://twitter.com/acmucsd/status/1369743758212800512        I could not include an image from this tweet using this class design.
    Tweet t2 = new Tweet("ONE MORE DAY TILL KICKOFF!!!", u1, 3, "1442345648422998019");

    //https://twitter.com/cassidoo/status/1614732753135865857       I was able to include all parts of the tweet using this class design.
    Tweet t3 = new Tweet("Pi time", u2, 46, "1614732753135865857");

    //https://twitter.com/UCSDJacobs/status/1605334760863068160     I could not include the subtweet to another tweet using this class design.
    Tweet t4 = new Tweet("UC San Diego has the most highly cited resarchers of all UC campuses! And 16 of those are affiliated with the Jacobs School!", u3, 18, "1605334760863068160"); 

    String userToText1 = this.u2.toText();           // expected Cassidy @cassidoo
    String userToText2 = this.u3.toText();           // expected UCSD Engineering @UCSDJacobs

    boolean longerThan1 = this.t1.longerThan(t2);    // expected false
    boolean longerThan2 = this.t4.longerThan(t3);    // expected true

    boolean moreLikes1 = this.t3.moreLikes(t4);      // expected true
    boolean moreLikes2 = this.t2.moreLikes(t3);      // expected false

    String tweetToText1 = this.t1.toText();          // expected "ACM @ UCSD @acmucsd : Ants are cool : 3 Likes"
    String tweetToText2 = this.t3.toText();          // expected "Cassidy @cassidoo : Pi time : 46 Likes"

    String toLink1 = this.t4.toLink();               // expected https://twitter.com/UCSDJacobs/status/1605334760863068160
    String toLink2 = this.t3.toLink();               // expected https://twitter.com/cassidoo/status/1614732753135865857
}