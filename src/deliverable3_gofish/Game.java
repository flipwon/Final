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
import java.util.Scanner;

public class Game {
        /** 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        final Scanner myScanner = new Scanner(System.in); //SINGLETON BITCH add a stupid scanner creating method
        Deck myDeck = new Deck(52, true);
        
        Player player = new Player("Player1");
        Computer computer = new Computer("Computer");
        
        Boolean game = true;
        Boolean fishing = true;
        
        myDeck.Deal(7, player);
        myDeck.Deal(7, computer);
        
        //Our main game loop
        while (game)
        {
            //Our player game loop
            while (fishing)
            {
                player.printHand();
                System.out.println("Choose a value to pick from: ");
                String choice = myScanner.nextLine();

                if (player.pickCard(Value.valueOf(choice),computer))
                {
                    System.out.println("Go again!");
                    if (player.checkHandEmpty() && myDeck.getCardCount() > 0)
                        myDeck.Deal(7, player);;
                }else{
                    System.out.println("Go Fish!");
                    fishing = false;
                    if (myDeck.getCardCount() > 0)
                    {
                        myDeck.Deal(1, player);
                        System.out.println("You picked up a "+player.getHand().get(0).toString());
                    }
                    player.checkBook();
                    if (player.checkHandEmpty() && myDeck.getCardCount() > 0)
                        myDeck.Deal(7, player);;
                    
                }
            }
            
            
            //When the player isnt fishing, the computer is
            while (!fishing)
            {
                System.out.println("Computer has "+computer.getHand().size()+" cards.");
                if (computer.pickRandom(player))
                {
                    //Do stuff?? Could be cleaner
                }else{
                    if (computer.checkHandEmpty())
                    {
                        if (myDeck.getCardCount() > 0)
                            myDeck.Deal(7, computer);
                        else
                            fishing=true;
                    }
                    else
                    {
                        System.out.println("Computer goes fish!");
                        
                        if (myDeck.getCardCount() > 0)
                            myDeck.Deal(1, computer);
                        
                        if (computer.checkHandEmpty() && myDeck.getCardCount() > 0)
                            myDeck.Deal(7, computer);
                                
                        fishing=true;
                    }
                }
            }
            
            if (player.checkHandEmpty() && computer.checkHandEmpty() && myDeck.getCardCount() == 0)
            {
                System.out.println("GAME OVER!!");
                System.out.println("Player has "+player.getBooks()+" Books || Computer has "+computer.getBooks()+" Books!");
                
                if (player.getBooks() > computer.getBooks())
                    System.out.println("Player wins!");
                else
                    System.out.println("Computer wins!");
            }
            
        }
        
    }
    
}
