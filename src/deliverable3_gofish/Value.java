/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deliverable3_gofish;

/**
 *
 * @author flipp
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
    
    public String getMyString()
    {
        return this.myString;
    }
    
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
