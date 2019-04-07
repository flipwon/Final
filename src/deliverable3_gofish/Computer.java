/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deliverable3_gofish;

import java.util.Random;

/**
 * An implementation of the Computer class, extending the Player class
 * @author flipp
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
     * Pick a random value of a card the computer currently has in hand from target
     * @param target 
     * @return true if a Card was picked
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
            int randomNum = new Random().nextInt(this.getHand().size()); //Choose a random card from hand
            
            picked[0]="Computer calls for "+this.getHand().get(randomNum).getValue();
            
            String s = pickCard(this.getHand().get(randomNum).getValue(),target);
            if (s != null)
                picked[1]=s;//Pick a card from target player of the value of the random card picked.. confusing right?
        }
        
        return picked;
    }   
}
