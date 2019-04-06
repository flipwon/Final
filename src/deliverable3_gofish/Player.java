/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deliverable3_gofish;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Implementation of a Player
 * @author flipp
 */
public class Player{
    
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
    
    //No books setter because score shouldnt be messed with
    
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
     * Check for a book (4 cards of the same value) remove the cards and add to your score.
     */
    public void checkBook()
    {
        Boolean madeBook=false;
        
        int[] toRemove = new int[hand.size()];
        
        for (int i=0; i<toRemove.length;i++)
            toRemove[i]=-1;
        
        for(Card a: hand)
        {
            int match = 0;
            for(Card b: hand)
            {
                if (a.getValue()==b.getValue())
                {
                    match++;       
                }
            }

            if (match == 4)
            {
                for(int i = 0; i < match; i++)
                {
                    toRemove[hand.indexOf(a)]=hand.indexOf(a);
                }
               
                madeBook=true;
            }
        }
        
        for (int i=toRemove.length-1; i>=0;i--) //Loop backwards so we have no invalid inputs removing from an arraylist
        {
            if (toRemove[i] != -1)
                hand.remove(i);
        }
        
        if (madeBook)
        {
            books++;
            System.out.println(name+" scored a book!! They currently have "+books);
        }
            
    }
    
    /**
     * Pick a specific value from a target Player, return true if a Card of specified Value was found
     * @param val       The Value chosen
     * @param target    The target Player to chose from
     * @return          True if a Card matching that value is found
     */
    public boolean pickCard(Value val, Player target)
    {
        boolean picked=false;
        int[] toRemove = new int[target.hand.size()];
        
        for (int i=0; i<toRemove.length;i++)
            toRemove[i]=-1;
        
        for(Card a: target.hand)
        {
            if (a.getValue().equals(val))
            {
                Card temp = a;
                toRemove[target.hand.indexOf(a)]=target.hand.indexOf(a);
                addCard(temp);
                System.out.println(name+" took "+temp.toString());
                picked=true;
            }
        }
        
        for (int i=toRemove.length-1; i>=0;i--) //Loop backwards so we have no invalid inputs??
        {
            if (toRemove[i] != -1)
                target.hand.remove(i);
        }
        
        checkBook();
        return picked;
    }
    
    /**
     * Print the hand of the Player in order from lowest Value to highest Value Card
     */
    public void printHand()
    {
        /**
         * This is an inline anonymous class I learned to use on a udemy course. Basically instead of making
         * a new comparator class that overrides the comparator method we implement it within the method here since we don't need to
         * reuse it. We can change this to a separate class for reusability but I think this is valuable for everybody
         * to learn, so I left it like this. This is just to sort the hand before we display it.
         */
        
        Collections.sort(hand, new Comparator<Card>() {
            
            @Override
            public int compare(Card o1, Card o2) 
            {
                return o1.getValue().compareTo(o2.getValue());
            } 
        });
        
        
        System.out.println("--------------------------------");
        System.out.println("Your hand:");
        for (int i = 0;i < hand.size(); i++)
        {
            System.out.println(hand.get(i).toString());
        }
        System.out.println("--------------------------------");
    }
}
