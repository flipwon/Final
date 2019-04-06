/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deliverable3_gofish;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author flipp
 */
public class PlayerTest {
    
    public PlayerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getName method, of class Player.
     */
    @Test
    public void testGetNameGood() {
        System.out.println("getNameGood");
        Player instance = new Player();
        String expResult = "DefaultPlayerName";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
    /**
     * Test of getName method, of class Player.
     */
    @Test
    public void testGetNameBad() {
        System.out.println("getNameBad");
        Player instance = new Player();
        String expResult = "YO MUTHAFUCKA U SUCK AT CODING";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }
    
    /**
     * Test of getName method, of class Player.
     */
    @Test
    public void testGetNameBoundary() {
        System.out.println("player1");
        Player instance = new Player();
        String expResult = "Stuff";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getHand method, of class Player.
     */
    @Test
    public void testGetHand() {
        System.out.println("getHand");
        Player instance = new Player();
        ArrayList<Card> expResult = null;
        ArrayList<Card> result = instance.getHand();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getBooks method, of class Player.
     */
    @Test
    public void testGetBooks() {
        System.out.println("getBooks");
        Player instance = new Player();
        int expResult = 0;
        int result = instance.getBooks();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class Player.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String n = "";
        Player instance = new Player();
        instance.setName(n);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addCard method, of class Player.
     */
    @Test
    public void testAddCard() {
        System.out.println("addCard");
        Card c = null;
        Player instance = new Player();
        instance.addCard(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkHandEmpty method, of class Player.
     */
    @Test
    public void testCheckHandEmpty() {
        System.out.println("checkHandEmpty");
        Player instance = new Player();
        Boolean expResult = null;
        Boolean result = instance.checkHandEmpty();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkBook method, of class Player.
     */
    @Test
    public void testCheckBook() {
        System.out.println("checkBook");
        Player instance = new Player();
        instance.checkBook();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pickCard method, of class Player.
     */
    @Test
    public void testPickCard() {
        System.out.println("pickCard");
        Value val = null;
        Player target = null;
        Player instance = new Player();
        boolean expResult = false;
        boolean result = instance.pickCard(val, target);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printHand method, of class Player.
     */
    @Test
    public void testPrintHand() {
        System.out.println("printHand");
        Player instance = new Player();
        instance.printHand();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
