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
        
        Scanner myScanner = new Scanner(System.in);
        int wins = 0;                                                               //Variable to store wins
        int losses = 0;                                                             //Variable to store losses
        Boolean playing;                                                            //Is the user still playingÉ
        
        do
        {
            Boolean win = play();                                                   //Our main play method returns if the player won or not
            
            if (win)
                wins++;                                                             //Player won!
            else
                losses++;                                                           //Bad guys won :(
            
            System.out.println("================================================");
            System.out.println("The score is You: "+wins+" The Computer: "+losses);
            System.out.println("================================================");
            System.out.println("Would you like to play again? (y/n)");
            String answer = myScanner.nextLine();

            switch(answer.toUpperCase()){                                           //Switch for the players response
                case "Y":
                    playing = true;
                    System.out.println("1");
                    break;
                case "N":
                    playing = false;
                    System.out.println("2");
                    break;
                default:
                    playing = false;
                    System.out.println("3");
            }
            
        }while (playing);
        
        System.out.println("Thanks for playing!");                                  //A polite goodbye. +5 marks
            
    }
    
    /**
     * This just checks if someone made a book, to keep all the user interaction in one place
     */
    private static void reportBook(GoFishPlayer p)                                          //Some runtime polymorphism rite hurr
    {
        String cardValue = p.checkBook();                                                   //checkBook returns null if nothing was found, or a string of the cards values, so we call it and store its return here
        
        if (cardValue != null)                                                              //If we took a book                 
            System.out.println(p.getName()+" scored a book of "+cardValue+"s! They currently have "+p.getBooks());
    }
    
    /**
     * This is the main method to start and play the Go Fish game. Returns if we want to play again 
     * @return      A boolean if the player wants to play again
     */
    private static Boolean play()
    {
        
        Scanner myScanner = new Scanner(System.in); 
        Deck myDeck = new Deck(52, true);
        
        GoFishPlayer player = new GoFishPlayer("Player");
        Computer computer = new Computer("Computer");
        
        Boolean game = true;
        Boolean fishing = true;
        Boolean win = false;
        
        myDeck.Deal(7, player);
        myDeck.Deal(7, computer);
        
        //Our main game loop
        while (game)
        {
            //Our player game loop
            while (fishing)
            {
                String[] playerHand = player.printHand();
                
                
                System.out.println("================================");
                System.out.println("  Books- Player: "+player.getBooks()+" Computer: "+computer.getBooks());
                System.out.println("================================");
                System.out.println("       Cards in deck: "+myDeck.getCardCount());
                System.out.println("================================");
                System.out.println("           Your Hand");
                System.out.println("================================");
                for (int i=0; i<playerHand.length; i++)
                    System.out.println(playerHand[i]);
                System.out.println("================================");
                
                reportBook(player);                                                 //We have to check for a book as the game starts, because it is a possibility. We do it after we print the hand so there's feedback, I guess
                
                System.out.println("Choose a value to pick from (Ace/Two/Three/Jack/King etc):");
                String choice = myScanner.nextLine();

                if (player.pickCard(Value.fromString(choice),computer))
                {
                    reportBook(player);
                    System.out.println("Go again!");
                    if (player.checkHandEmpty() && myDeck.getCardCount() > 0)
                    {
                        myDeck.Deal(7, player);
                    }
                    else if (player.checkHandEmpty() && myDeck.getCardCount() <= 0)
                    {
                        System.out.println("Out of cards in deck!");
                        fishing = false;
                    }
                }else{
                    
                    System.out.println("Go Fish!");
                    fishing = false;
                    
                    if (myDeck.getCardCount() > 0)
                    {
                        myDeck.Deal(1, player);
                        System.out.println("You picked up a "+player.getHand().get(0).toString());
                        System.out.println("There are "+myDeck.getCardCount()+" cards left in the deck.");
                        if (player.getHand().get(0).getValue() == Value.fromString(choice))         //If the value of the card we picked up is equal to the one we chose, fish our wish!
                        {
                            fishing = true;
                            System.out.println("You fished your wish! Go again!");
                        } 
                    }
                    
                    reportBook(player);
                    if (player.checkHandEmpty() && myDeck.getCardCount() > 0)
                        myDeck.Deal(7, player);;
                    
                }
            }
            
            
            //When the player isn't fishing, the computer is
            while (!fishing)
            {
                System.out.println("Computer has "+computer.getHand().size()+" cards.");
                if (computer.pickRandom(player))
                {
                    reportBook(computer);
                }else{
                    if (computer.checkHandEmpty())
                    {
                        if (myDeck.getCardCount() > 0)
                            myDeck.Deal(7, computer);
                        else
                            fishing=true;
                        
                        reportBook(computer);
                    }
                    else
                    {
                        System.out.println("Computer goes fish!");
                        
                        if (myDeck.getCardCount() > 0)
                            myDeck.Deal(1, computer);
                        
                        if (computer.checkHandEmpty() && myDeck.getCardCount() > 0)
                            myDeck.Deal(7, computer);
                        
                        reportBook(computer);
                        fishing=true;
                    }
                }
            }
            
            if (player.checkHandEmpty() && computer.checkHandEmpty() && myDeck.getCardCount() == 0)
            {
                System.out.println("GAME OVER!!");
                System.out.println("Player has "+player.getBooks()+" Books || Computer has "+computer.getBooks()+" Books!");
                
                if (player.getBooks() > computer.getBooks())
                {
                    System.out.println("Player wins!");
                    win = true;
                }
                else
                {
                    System.out.println("Computer wins!");
                    win = false;
                }
                
                game = false;
            }
        }
        return win;
    }
    
}
