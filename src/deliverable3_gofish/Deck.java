/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deliverable3_gofish;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Implementation of a Deck
 * @author flipp
 */
public class Deck {
    
    
    private ArrayList<Card> cards;
    
    /**
     * Default constructor creates deck of 52 cards, shuffled.
     */
    public Deck()
    {
        this(52, true);
    }
    
    /**
     * Constructor that takes a number of cards and if it should be shuffled by default
     * @param cardCount     The number of cards when the deck is constructed
     * @param isShuffled    If the deck is shuffled or not
     */
    public Deck(int cardCount, boolean isShuffled)
    {
        this.cards = new ArrayList<Card>(cardCount);
        
        for (int i = 0; i < Suit.values().length; i++)
        {
            for (int ii = 0; ii < Value.values().length; ii++)
            {
                cards.add(new Card(Suit.values()[i], Value.values()[ii]));
            }
        }
        
        if (isShuffled)
            Shuffle();
    }
    
    /**
     * Return the amount of cards left in the deck
     * @return 
     */
    public int getCardCount()
    {
        return cards.size();
    }
    
    public void Shuffle()
    {
        Collections.shuffle(cards);
    }
    
    /**
     * Deal a number a cards to a player
     * 
     * @param numCards      Number of cards to deal
     * @param p             Player to deal cards to
     */
    public void Deal(int numCards, Player p)
    {
        if (!cards.isEmpty())
        {
            for (int i = 0; i < numCards; i++)
            {
                if (!cards.isEmpty()) //Have to check if it's empty in the loop as well to stop a crash
                {
                    //store top card in a temp variable
                    Card temp = cards.get(0);
                    //remove the top card
                    cards.remove(0);
                    //add card to players hand
                    p.addCard(temp);
                }
            }
        }else{
            System.out.println("Out of cards, check for win???????????");   //Debug
        }  
    }
   
    /**
     * Print a number of cards from the top of the deck; primarily used for debugging
     * @param n     The number of cards from the top to print
     */
    public void printDeck(int n)
    {
        for (int i = 0;i < n; i++)
        {
            System.out.println(i+1+" "+cards.get(i).toString());
        }
    }
}
