/*
 * GoFish baby!
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
     * Check for a book (four of a kind) of cards in a hand. If found, remove the cards and score a point
     * @return      The value of the cards removed in string form (ex: Ace)
     */
    public String checkBook()
    {
        Boolean madeBook=false;
        String cardValue=null;
        
        int[] toRemove = new int[this.getHand().size()];
        
        for (int i=0; i<toRemove.length;i++)
            toRemove[i]=-1;
        
        for(Card a: this.getHand())                                                 //For each card in our hand, we check for a matching
        {                                                                           //value. We continue to loop through until we find a
            int match = 0;                                                          //book of 4.
            for(Card b: this.getHand())
            {
                if (a.getValue()==b.getValue())
                {
                    match++;       
                }
            }

            if (match == 4)                                                         //If we find 4, we store the index of those cards in
            {                                                                       //a temp array to remove later.
                cardValue = a.getValue().name();
                for(int i = 0; i < match; i++)
                {
                    toRemove[this.getHand().indexOf(a)]=this.getHand().indexOf(a);
                }
               
                madeBook=true;
            }
        }
        
        for (int i=toRemove.length-1; i>=0;i--)                                     //Loop backwards so we have no invalid indexes removing from an arraylist
        {
            if (toRemove[i] != -1)
                this.getHand().remove(i);
        }
        
        if (madeBook)
        {
            this.setBooks(this.getBooks()+1);                                       //Give a point
        }
        
        return cardValue;                                   
    }
        
    /**
    * Pick a specific value from a target Player, return true if a Card of specified Value was found
    * @param val       The Value chosen
    * @param target    The target Player to chose from
    * @return          True if a Card matching that value is found
    */
    public String pickCard(Value val, Player target)
    {
        String picked=null;                                                           
        int[] toRemove = new int[target.getHand().size()];                              //Create an array copy of the targets hand to store the location of any matches.

        for (int i=0; i<toRemove.length;i++)                                            
            toRemove[i]=-1;

        for(Card a: target.getHand())                                                   //For each card in the targets hand, if there are any cards
        {                                                                               //with a matching value, store it in our temp array, and
            if (a.getValue().equals(val))                                               //add the card and return what we took.
            {
                Card temp = a;                                                          
                toRemove[target.getHand().indexOf(a)]=target.getHand().indexOf(a);      
                addCard(temp);                                                          
                picked=getName()+" took "+target.getName()+"'s "+temp.getValue()+"s";                              
            }
        }

        for (int i=toRemove.length-1; i>=0;i--)                                         //Loop backwards so we have no invalid indexes
        {
            if (toRemove[i] != -1)
                target.getHand().remove(i);                                             //If an index in the array holds a value, we remove it
        }
            
        return picked;                                                                 
        
    }
             
}


