package items;
import java.util.ArrayList;

/**
 * This Cart class was made for CS067 Final project under the teaching of professor Timothy Urness
 * This class represents a cart that holds items and drinks that the user selects and can manipulate
 * @author Joey Zolalr
 */
public class Cart {
    // The list of items the user has chosen
    private ArrayList<item> items = new ArrayList<item>();
    // The list of drinks the user has chosen
    private ArrayList<drink> drinks = new ArrayList<drink>();
    // The current total cost in cents
    private long total;

    /**
     * Default constructor, both ArrayLists are defined as empty to start
     */
    public Cart(){
       total = 0;
    }

    /**
     * Returns a formatted string representation of the total
     * @return formated string representation of the total
     */
    public String convertPrice(){
        double i = total*.01;
        return String.format("%.2f", i);
    }

    /**
     * Creates a new COPY of the object and adds it to the arrayList of items and adds price of item to total
     * @param i the item object being added
     */
    public void addItem(item i){
        item newItem = i.duplciate();

        items.add(newItem);
        total += i.getPrice();
    }

    /**
     * Creates a new COPY of the object and adds it to the arrayList of drinks and adds price of item to total
     * @param i the drink object being added
     */
    public void addDrink(drink i){
        drink newItem = i.duplicateAsDrink();

        drinks.add(newItem);
        total += i.getPrice();
    }

    /**
     * Removes an item object from the arrayList of items and subtracts price of item from total
     * @param i the item object being removed
     */
    public void removeItem(item i){
        items.remove(i);
        total -= i.getPrice();
    }

    /**
     * Removes a drink object from the arrayList of drinks and subtracts price of drink from total
     * @param i the drink object being removed
     */
    public void removeDrink(drink i){
        drinks.remove(i);
        total -= i.getPrice();
    }

    /**
     * Will clear all items and drinks from the cart
     */
    public void clearCart(){
        items.clear();
        drinks.clear();
    }

    /*-------------------------------------------
        Cart Getter Methods
    -------------------------------------------*/ 
    /**
     * Returns the item object at the given index
     * @param i the index of the item in the cart to get
     */
    public item getItemAt(int i){
        return items.get(i);
    }

    /**
     * Returns the drink object at the given index
     * @param i the index of the drink in the cart to get
     */
    public drink getDrinkAt(int i){
        return drinks.get(i);
    }

    /**
     * Returns the number of items in the cart
     * @return integer that is the number of items in the cart
     */
    public int getNumOfItems(){
        return items.size();
    }

    /**
     * Returns the number of drinks in the cart
     * @return integer that is the number of drinks in the cart
     */
    public int getNumOfDrinks(){
        return drinks.size();
    }

    /**
     * Will the total cost of the cart in cents
     * @return The total cost of the cart in cents
     */
    public long getTotal(){
        return total;
    }

    /**
     * Retruns the arraList containing all items ordered
     * @return arrayList of items
     */
    public ArrayList<item> getItems(){
        return items;
    }

    public ArrayList<drink> getDrinks(){
        return drinks;
    }

    /*-------------------------------------------
        Cart Setter Methods
    -------------------------------------------*/ 
    /**
     * Changes the total cost of the cart
     * @param t new total of the cart in cents
     */
    public void setTotal(long t){
        if (t >= 0){
            total = t;
        }
    }
}
