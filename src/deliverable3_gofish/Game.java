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
        int wins = 0;
        int losses = 0;
        Boolean playing;
        
        do
        {
            Boolean win = play();
            
            if (win)
                wins++;
            else
                losses++;
            
            System.out.println("================================================");
            System.out.println("The score is You: "+wins+" The Computer: "+losses);
            System.out.println("================================================");
            System.out.println("Would you like to play again? (y/n)");
            String answer = myScanner.nextLine();

            switch(answer.toUpperCase()){
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
        
        System.out.println("Thanks for playing!");
            
    }
    
    /**
     * This just checks if someone made a book, to keep all the user interaction in one place
     */
    private static void reportBook(GoFishPlayer p)
    {
        String cardValue = p.checkBook();
        
        if (cardValue != null)
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
        
        GoFishPlayer player = new GoFishPlayer("Player1");
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
                player.printHand();
                reportBook(player); //We have to check for a book as the game starts, because it is a possibility. We do it after we print the hand so there's feedback, I guess
                
                System.out.println("Choose a value to pick from: ");
                String choice = myScanner.nextLine();

                if (player.pickCard(Value.valueOf(choice),computer))
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
                    }
                    reportBook(player);
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
