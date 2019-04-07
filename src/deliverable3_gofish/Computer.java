/*
 * GoFish baby!
 */
package deliverable3_gofish;

import java.util.Random;

/**
 * An implementation of the Computer class, extending the Player class
 * @author Everyone <3
 */
public class Computer extends GoFishPlayer{
    
    /**
     * Computer constructor
     * @param name  The name of the computer player.
     */
    public Computer(String name)
    {
        super(name);
    }
    
    
    /**
     * Pick a random card Value of a Value currently in hand, then pick that
     * card from target player if found. Return an array of strings to be
     * displayed to the User.
     * @param target
     * @return      Array of type String
     */
    public String[] pickRandom(Player target)
    {
        String[] picked = new String[2];
        for (int i=0; i<2;i++)
        {
            picked[i]=null;
        }
        
        if(this.getHand().size() > 0)
        {
            int randomNum = new Random().nextInt(this.getHand().size());            //Choose a random card from hand
            
            picked[0]="Computer calls for "+this.getHand().get(randomNum).getValue()+"s";
            
            String s = pickCard(this.getHand().get(randomNum).getValue(),target);   //Store the string value of the card picked to return to the game loop to display to the user.
            if (s != null)
                picked[1]=s;                                                        
        }
        
        return picked;
    }   
}
