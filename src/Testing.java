import org.junit.Test;
import static org.junit.Assert.*;

import items.*;
import java.util.ArrayList;

/**
 * This Testing class was made for CS067 Final project under the teaching of professor Timothy Urness
 * This class tests some of the functions in the items package to ensure they are working properly
 * @author Joey Zollar
 */
public class Testing {
    @Test
    public void itemTest(){
        item item1 = new item("Hamburger", 900, 1100, "Classic hamburger with fries", "beef, lettuce, tomato, cheddar cheese");

        assertEquals(item1.getPrice(), 900);
    }

    @Test
    public void convertTest(){
        item item1 = new item("Hamburger", 10000, 1100, "Classic hamburger with fries", "beef, lettuce, tomato, cheddar cheese");

        double cost = item1.convertPrice();

        assertEquals(cost, 100.00,0);
    }

    @Test
    public void cartTest(){
        item item1 = new item("Hamburger", 10000, 1100, "Classic hamburger with fries", "beef, lettuce, tomato, cheddar cheese");
        Cart cart1 = new Cart();
        
        cart1.addItem(item1);
        cart1.getItemAt(0).newRequest("No cheese");
        ArrayList<String> testRequest = new ArrayList<String>();
        testRequest.add("No cheese");

        assertEquals(cart1.getItemAt(0).getRequests(),testRequest);
    }
}
