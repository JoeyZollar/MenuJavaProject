import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

import items.*;

/**
 * This Menu class was made for CS067 Final project under the teaching of professor Timothy Urness
 * This class represents items on a menu that are created from an input file and can be added to a cart
 * 
 * About the input file formatting:
 *   -   This program will input a .txt file of any size
 *   -   The FIRST line of the file will be the name of the menu
 *   -   The lines after will each create an item or drink object depending on what the input is
 *   -   Each line is split into parts using forwards slashes
 *   -   Objects on the bottom of the file will be at TOP of the menu GUI and vice versa
 * 
 * The format of creating objects is as follows:
 *  [1] - The type of object to create (Either DRINK or ITEM)
 *  [2] - The name of the object
 *  [3] - The price of the object in cents
 *  [4] - The number of calories in the item
 *  [5] - A desciption of the item
 *  [6] - The ingredients in the item
 *  [7] - (If the object is a DRINK) the size of the drink
 * 
 * Example of an item: ITEM/The Box Combo/1100/1400/4 Chicken Fingers, Crinkle-Cut Fries, 1 Cane's Sauce, Texas Toast, Coleslaw/Contains: chicken, wheat, soy, peanuts
 * Example of a drink: DRINK/Dr. Pepper/200/600/Classic Dr. Pepper/Contains: soy, root, other natrual flavors/0
 * 
 * @author Joey Zollar
 */
public class Menu {
    // The name of the menu
    private static String name;
    // All of the item objects in the menu generated from the file
    private static ArrayList<item> itemMenu = new ArrayList<item>();
    // All of the drink objects in the menu generated from the file
    private static ArrayList<drink> drinkMenu = new ArrayList<drink>();

    /**
     * Will generate objects to into the menu from the input file given
     * @param fileName the name of the file to read from to generate the menu
     * @throws FileNotFoundException
     */
    public static void readMenu(String fileName) throws FileNotFoundException{
        File menuFile = new File(fileName);
        Scanner fileScanner = new Scanner(menuFile);

        // Setting the name of the menu to be the first line
        name = fileScanner.nextLine();

        // Looping through the file creating objects to add to the menu
        while (fileScanner.hasNext()){
            String nextItem = fileScanner.nextLine(); // Get the next line
            String[] itemElements = nextItem.split("/"); // Split the line by fowards slashes

            if (itemElements[0].equals("ITEM")){ // If the line is for an item object
                int itemPrice = Integer.parseInt(itemElements[2]);
                int itemCalories = Integer.parseInt(itemElements[3]);
    
                item newItem = new item(itemElements[1],itemPrice,itemCalories,itemElements[4],itemElements[5]); // Create the new item
    
                itemMenu.add(newItem); // Add the new object to the item menu

            } else if (itemElements[0].equals("DRINK")){ // If the line is for an drink object
                int itemPrice = Integer.parseInt(itemElements[2]);
                int itemCalories = Integer.parseInt(itemElements[3]);
                int itemSize = Integer.parseInt(itemElements[6]);
    
                drink newItem = new drink(itemElements[1],itemPrice,itemCalories,itemElements[4],itemElements[5],itemSize); // Create the new drink

                drinkMenu.add(newItem); // Add the new object to the drink menu
            }
        }

        fileScanner.close();
    }

    /*-------------------------------------------
        Menu Getter Methods
    -------------------------------------------*/ 
    /**
     * Returns the item menu
     * @return item menu list
     */
    public ArrayList<item> getItemMenu(){
        return itemMenu;
    }

    /**
     * Return item object at given index
     * @param i index to get item from
     * @return item object at the given index
     */
    public item getItemAt(int i){
        return itemMenu.get(i);
    }

    /**
     * Returns the drink menu
     * @return drink menu list
     */
    public ArrayList<drink> getDrinkMenu(){
        return drinkMenu;
    }

    /**
     * Return drink object at given index
     * @param i index to get item from
     * @return drink object at the given index
     */
    public drink getDrinkAt(int i){
        return drinkMenu.get(i);
    }

    /**
     * Returns the name of the menu as a string
     * @return the name of the menu
     */
    public String getName(){
        return name;
    }
}
