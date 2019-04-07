/*
 * GoFish baby!
 */
package deliverable3_gofish;

/**
 * Values of a card
 * @author Everyone <3
 */
public enum Value {
    Ace("ace"),
    Two("two"),
    Three("three"),
    Four("four"),
    Five("five"),
    Six("six"),
    Seven("seven"),
    Eight("eight"),
    Nine("nine"),
    Ten("ten"),
    Jack("jack"),
    Queen("queen"),
    King("king");
    
    private String myString;
    
    Value(String myString){
        this.myString=myString;
    }
    
    /**
     * The string value of the Enum
     * @return      The string value of the Enum
     */
    public String getMyString()
    {
        return this.myString;
    }
    
    /**
     * The string Value of the Enum ignoring case sensitivity.
     * @param myString 
     * @return The value of the Enum ignoring case sensitivity.
     */
    public static Value fromString(String myString)
    {
        for (Value i: Value.values())
        {
            if (i.myString.equalsIgnoreCase(myString))
            {
                return i;
            }
        }
        return null;
    }
}
