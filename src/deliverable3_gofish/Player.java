/*
 * GoFish baby!
 */
package deliverable3_gofish;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Implementation of a Player
 * @author Everyone <3
 */
public abstract class Player{
    
    private String name;
    private ArrayList<Card> hand = new ArrayList<Card>();
    private int books;
   
    /**
     * Default Player constructor
    */
    public Player()
    {
        this.name = "DefaultPlayerName";
    }
    
    /**
     * Player Constructor that takes a specific name
     * @param name      The name of the Player
     */
    public Player(String name)
    {
        this.name = name;
    }
    
    /**
     * Return the name of the Player
     * @return      The name of the Player
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Return the hand of the Player
     * @return      The hand ArrayList of Cards of the Player
     */
    public ArrayList<Card> getHand()
    {
        return hand;
    }
    
    /**
     * Return the number of books (points) a Player has
     * @return      The books of the Player
     */
    public int getBooks()
    {
        return books;
    }
    
    /**
     * Set the Players name
     * @param n     The Name of the Player
     */
    public void setName(String n)
    {
        name = n;
    }
    
    //No hand setter because stuff will break
    
    public void setBooks(int b)
    {
        books = b;
    }
    
    /**
     * Add a Card to the Player hand
     * @param c     The Card to add to the hand
     */
    public void addCard(Card c)
    {
        this.hand.add(0,c);
        //checkBook();
    }
    
    /**
     * Check if the Players hand is empty
     * @return      True if empty, false if not
     */
    public Boolean checkHandEmpty()
    {
        if (hand.size() == 0)
            return true;
        else
            return false;     
    }
    
    

    /**
     * Return the hand of the player in order of value within an array
     * @return      An array of Strings of the players hand
     */
    public String[] printHand()
    {
        String[] myHand = new String[hand.size()];
        
        /**
         * This is an inline anonymous class I learned to use on a udemy course. Basically instead of making
         * a new comparator class that overrides the comparator method we implement it within the method here since we don't need to
         * reuse it and override the compare method already there. We can change this to a separate class for reusability but I think 
         * this is valuable for everybody to learn, so I left it like this. 
         * 
         * This is just to sort the hand before we display it.
         */
        Collections.sort(hand, new Comparator<Card>() 
        {
            
            @Override
            public int compare(Card card1, Card card2) 
            {
                return card1.getValue().compareTo(card2.getValue());
            } 
        });
        
        for (int i = 0;i < hand.size(); i++)
        {
            myHand[i]=hand.get(i).toString();
        }
        
        return myHand;
    }
}
