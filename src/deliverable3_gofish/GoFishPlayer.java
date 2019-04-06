/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deliverable3_gofish;

/**
 *
 * @author Flip
 */
public class GoFishPlayer extends Player {
    
    public GoFishPlayer(String name)
    {
        super(name);
    }
    
    /**
     * Check for a book (4 cards of the same value) remove the cards and add to your score.
     */
    public String checkBook()
    {
        Boolean madeBook=false;
        String cardValue=null;
        
        int[] toRemove = new int[this.getHand().size()];
        
        for (int i=0; i<toRemove.length;i++)
            toRemove[i]=-1;
        
        for(Card a: this.getHand())
        {
            int match = 0;
            for(Card b: this.getHand())
            {
                if (a.getValue()==b.getValue())
                {
                    match++;       
                }
            }

            if (match == 4)
            {
                cardValue = a.getValue().name();
                for(int i = 0; i < match; i++)
                {
                    toRemove[this.getHand().indexOf(a)]=this.getHand().indexOf(a);
                }
               
                madeBook=true;
            }
        }
        
        for (int i=toRemove.length-1; i>=0;i--) //Loop backwards so we have no invalid inputs removing from an arraylist
        {
            if (toRemove[i] != -1)
                this.getHand().remove(i);
        }
        
        if (madeBook)
        {
            this.setBooks(this.getBooks()+1);
        }
        
        return cardValue;
    }
        
    /**
    * Pick a specific value from a target Player, return true if a Card of specified Value was found
    * @param val       The Value chosen
    * @param target    The target Player to chose from
    * @return          True if a Card matching that value is found
    */
    public boolean pickCard(Value val, Player target)
    {
        boolean picked=false;                                                           //Did we pick a card?
        int[] toRemove = new int[target.getHand().size()];                              //Create an array copy of the targets hand to store the location of any matches.

        for (int i=0; i<toRemove.length;i++)                                            //init the array
            toRemove[i]=-1;

        for(Card a: target.getHand())                                                   //For each card in the targets hand
        {
            if (a.getValue().equals(val))                                               //If there are any cards with a matching value
            {
                Card temp = a;                                                          //Store it in a temp card
                toRemove[target.getHand().indexOf(a)]=target.getHand().indexOf(a);      //Store the card location in the same location in our temp array
                addCard(temp);                                                          //Add the card to our hand
                System.out.println(getName()+" took "+temp.toString());                 //Tell the user who took what
                picked=true;                                                            //Return true so the player can go again
            }
        }

        for (int i=toRemove.length-1; i>=0;i--)                                         //Loop backwards so we have no invalid indexes
        {
            if (toRemove[i] != -1)                                                      //If the array has a value
                target.getHand().remove(i);                                             //remove it
        }

        return picked;                                                                  //Return if we picked or not
        
    }
             
}


