class R{
    String String;
    R R;

    R(String String, R R){
        this.String = String;
        this.R = R;
    }
}

class ExamplesR{
    R example = new R("String", R);


    // I was not able to construct an example R object. I think it's not possible because an R object references something of type String and of type R. 
    // So, in order to create an R object I would need a reference to an R object, which none exists yet.
    // Without an R object to reference, I am unable to complete the creation of an example R object.


    // I would be unable to construct an example of a reply to a reply to a Tweet, at least without making a new constructor.
    // This is because any ReplyTweet objects reference only something of type TextTweet, String, and int.
    // A reply to a tweet would be of type ReplyTweet, and so a different object of type ReplyTweet would be unable to use that object of type ReplyTweet in the replyto field, as this field can obly be of type TextTweet.
    // Objects of type TextTweet do not consist of a reply to another tweet, and so a reference to a TextTweet object is also not a reply to a reply to a tweet.
}