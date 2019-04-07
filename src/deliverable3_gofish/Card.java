/*
 * GoFish baby!
 */
package deliverable3_gofish;

/**
 * Implementation of a Card
 * @author Everyone <3
 */
public class Card {
    
    private Suit suit;
    private Value value;
    
    /**
     * Default Card constructor
     * @param givenSuit     the suit of the card
     * @param givenValue    the value of the card
     */
    public Card(Suit givenSuit, Value givenValue)
    {
        this.suit = givenSuit;
        this.value = givenValue;
    }
    
    /**
     * Return the Card value
     * @return      Value of Card
     */
    public Value getValue()
    {
        return value;
    }
    
    /**
     * Return the Card suit
     * @return      Suit of Card
     */
    public Suit getSuit()
    {
        return suit;
    }
    
    /**
//     * Return the Card value and suit as a string
     * @return 
     */
    public String toString()
    {
        return value.toString()+" of "+suit.toString();
    }
    
}
