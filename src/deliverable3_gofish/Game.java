/*
 * GoFish baby!
 */
package deliverable3_gofish;

/**
 * @author Everyone <3
 */
import java.util.Scanner;

public class Game {
     
    public static void main(String[] args) {
        
        Scanner myScanner = new Scanner(System.in);
        int wins = 0;                                                               //Variable to store wins
        int losses = 0;                                                             //Variable to store losses
        Boolean playing;                                                            //Is the user still playingÉ
        
        do
        {
            System.out.println("================================");  
            System.out.println("=======Welcome to GO FISH=======");
            System.out.println("========By Travis, Mike=========");
            System.out.println("========Mas and Jeremy==========");
            System.out.println("================================");  
            System.out.println("   <<Press Enter to start>>   ");
            System.out.println("================================");
            
            try                                                                     //Wait for enter to start the game
            {
                System.in.read();
            }
            catch(Exception exception)
            {
                exception.printStackTrace();
            }
            
            sleep(300);
            Boolean win = play();                                                   //Our main play method returns if the player won or not
            
            
            if (win)
                wins++;                                                             //Player won!
            else
                losses++;                                                           //Bad guys won :(
            
            System.out.println("================================");
            System.out.println("  Score You: "+wins+" The Computer: "+losses);
            System.out.println("================================");
            sleep(500);
            System.out.println("=======Play again? (Y/N)========");
            String answer = myScanner.nextLine();
            sleep(300);

            switch(answer.toUpperCase()){                                           //Switch for the players response
                case "Y":
                    playing = true;
                    break;
                case "N":
                    playing = false;
                    break;
                default:
                    playing = false;
            }
            
        }while (playing);
        
        System.out.println("Thanks for playing!");                                  //A polite goodbye. +5 marks
            
    }
    
    
    /**
     * Checks if someone made a book, to keep all the user interaction in one place
     */
    private static void reportBook(GoFishPlayer p)                                          //Some runtime polymorphism rite hurr
    {
        String cardValue = p.checkBook();                                                   
        
        if (cardValue != null)                                                              //If we took a book                 
            System.out.println(p.getName()+" scored a book of "+cardValue+"s! They currently have "+p.getBooks());
    }
    
    
    /**
     * Check if everyone is out of cards.
     * @param a player
     * @param b computer
     * @param d deck
     * @return If everyone is out of cards
     */
    private static Boolean outOfCards(GoFishPlayer a, GoFishPlayer b, Deck d)
    {
        if (a.checkHandEmpty() && b.checkHandEmpty() && d.getCardCount() == 0)
            return true;
        else
            return false;
    }
    
    /**
     * A method to pause the main thread, to add some pacing to the game.
     * Not necessary but I was just trying new things, though this is definitely
     * not something I would use in a program of substance.
     * @param l time in miliseconds to sleep
     */
    private static void sleep(long l)
    {
        try
        {
            Thread.sleep(l);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }
    
    
    /**
     * Double check everyone is out of cards, get score and return the winner (true for player, false for computer)
     * @param a player
     * @param b computer
     * @param d deck
     * @return Boolean winner of game (t for player, f for comp)
     */
    private static Boolean checkGameOver(GoFishPlayer a, GoFishPlayer b, Deck d)
    {
        
        Boolean win = null;
        if (outOfCards(a,b,d))
        {
            System.out.println("GAME OVER!!");
            System.out.println("Player has "+a.getBooks()+" Books || Computer has "+b.getBooks()+" Books!");

            if (a.getBooks() > b.getBooks())
            {
                System.out.println("Player wins!");
                win = true;
            }
            else
            {
                win = false;
                System.out.println("Computer wins!");
            }

        }
        return win;
    }
    
    /**
     * This is the main method to start and play the Go Fish game. Returns if we want to play again 
     * @return      The winner of the game, true for player, false for computer
     */
    private static Boolean play()
    {
        
        Scanner myScanner = new Scanner(System.in); 
        Deck myDeck = new Deck(52, true);
        
        GoFishPlayer player = new GoFishPlayer("Player");
        Computer computer = new Computer("Computer");
        
        Boolean game = true;                                                        //Keep track of if we're still player
        Boolean fishing = true;                                                     //True for players turn, false for computers turn
        Boolean win = false;                                                        //True for player win, false for computer win, to be returned 
        
        myDeck.Deal(7, player);                                                     //Deal 7 cards to both players
        myDeck.Deal(7, computer);
        
        //Our main game loop
        while (game)
        {  
            //Our player game loop
            while (fishing)
            {
                if (outOfCards(player,computer,myDeck))
                    break;
                
                String[] playerHand = player.printHand();                           //Store the players hand
                
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
                String choice = myScanner.nextLine();                               //Store the users input for later
                String chosen = player.pickCard(Value.fromString(choice),computer); //Store the picked card for later
                
                if (chosen != null)                                                 //If we guess correctly, we take the card, check for a book and quere the player to go again
                {
                    System.out.println(chosen);   
                    
                    reportBook(player);
                    System.out.println("Go again!");
                    sleep(700);
                    if (player.checkHandEmpty())                                    //If our hand is empty and the deck still has cards, deal up to 7 to the player
                    {
                        if (myDeck.getCardCount() == 0)
                        {
                            System.out.println("Deck is out of cards!");            //Else its out of cards and we swap turns
                            sleep(700);
                            fishing = false;
                        }
                    }
                    
                }else{
                    
                    System.out.println("Go Fish!");                                 //Otherwise we go fish
                    sleep(500);
                    fishing = false;
                    if (myDeck.getCardCount() > 0)
                    {
                        myDeck.Deal(1, player);
                        System.out.println("You picked up a "+player.getHand().get(0).toString());
                        sleep(400);
                        System.out.println("There are "+myDeck.getCardCount()+" cards left in the deck.");
                        sleep(400);
                        if (player.getHand().get(0).getValue() == Value.fromString(choice))         //If the value of the card we picked up is equal to the one we chose, we get to go again!
                        {
                            System.out.println("You fished your wish! Go again!");
                            sleep(400);
                            fishing = true;
                        } 
                    }
                    reportBook(player);
                }
            }
            
           
            
            //When the player isn't fishing, the computer is
            while (!fishing)
            {
                if (outOfCards(player,computer,myDeck))
                    break;
                
                System.out.println("Computer has "+computer.getHand().size()+" cards.");
                sleep(700);
                
                String[] compPicked = computer.pickRandom(player);                  //The computer picks his card randomly and returns an array of 
                                                                                    //strings to display to the user, to keep all user interaction
                                                                                    //in one place!
                if (compPicked[1] != null)
                {
                    System.out.println(compPicked[0]);                              //If he gets a card, display what he got and check for a book
                    sleep(700);
                    System.out.println(compPicked[1]);
                    sleep(700);
                    reportBook(computer);
                }
                else
                {
                    if (compPicked[0] != null)  {System.out.println(compPicked[0]);} //Show what value was picked, if they had a card in hand
                    sleep(700);
                    System.out.println("Computer goes fish!");
                    sleep(1200);

                    if (myDeck.getCardCount() > 0)
                        myDeck.Deal(1, computer);                                   //Give them a card and check for a book
                    reportBook(computer);
                    
                    if (computer.checkHandEmpty() && myDeck.getCardCount() > 0)
                        myDeck.Deal(7, computer);

                    reportBook(computer);
                    fishing=true;

                }

            }
            
            if (outOfCards(player,computer,myDeck))                                 //If everyones out of cards break out of all the loops
            {                                                                       //and display the winner. return the winner of our play
                game = false;                                                       //method to keep track of score in the main
                fishing = null;
                win = checkGameOver(player,computer,myDeck);
                break;
            }
        } 
        return win;
    }
}

