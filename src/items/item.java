package items;
import java.util.ArrayList;

/**
 * This Item class was made for CS067 Final project under the teaching of professor Timothy Urness
 * This class represents a food item on a menu that can be added to a cart and displayed visually
 * @author Joey Zollar
 */
public class item{
    private String name; // Name of the item
    private int price; // Int that represents the price of the item in cents
    private int calories; // Int that represents the number of calories in the item
    private String desc; // Description of the item
    private String ingredients; // String that will list out ingredients
    private ArrayList<String> requests; // An arraylist of requests for the item given by the user for the item

    /**
     * Constructor for item object
     * @param n name of item
     * @param p price in cents
     * @param d description of item
     * @param i ArrayList of ingredients
     */
    public item(String n, int p, int c, String d, String i){
        name = n;
        price = p;
        calories = c;
        desc = d;
        ingredients = i;
        requests = new ArrayList<String>(); // Requests is defined as an empty ArrayList
    }

    /**
     * Constructor for item object that includes requests
     * @param n name of item
     * @param p price in cents
     * @param d description of ite
     * @param i ArrayList of ingredients
     * @param r ArrayList of requests in String form
     */
    public item(String n, int p, int c, String d, String i, ArrayList<String> r){
        name = n;
        price = p;
        calories = c;
        desc = d;
        ingredients = i;
        requests = r;
    }

    /**
     * Creates a new item with the same fields, with a deep copied list of requests
     * @return A new item that has arequest ArrayList deep copied
     */
    public item duplciate(){
        ArrayList<String> newReqs = new ArrayList<String>(); // Create new ArrayList in memory

        for (int i = 0; i < requests.size(); i++){ // Add each request from old list into new list
            newReqs.add(requests.get(i)); 
        }

        item newItem = new item(name, price, calories, desc, ingredients, newReqs); // Create new item

        return newItem;
    }

    /**
     * Converts the price from cents into dollars
     * @return A double that is the price in dollars
     */
    public double convertPrice(){
        double priceInDollars;
        priceInDollars = price*.01;
        
        return priceInDollars;
    }

    /**
     * Adds a new request to the list of requests for the item
     * @param req new request string 
     */
    public void newRequest(String req){
        this.requests.add(req);
    }

    /**
     * Will remove all item requests
     */
    public void clearRequests(){
        requests.clear();
    }

    /**
     * Returns a string representation of the item's request ArrayList
     * @return String representation of the requests ArraList
     */
    public String requestToString(){
        String returnString = "Requests:\n";

        for (int i = 0; i < requests.size(); i++){
            returnString += requests.get(i) + "\n";
        }

        return returnString;
    }

    /*-------------------------------------------
        Item Getter Methods
    -------------------------------------------*/ 
    /**
     * Will return the name of the item
     * @return The name of the item
     */
    public String getName(){
        return name;
    }

    /**
     * Will return the price of the item in cents
     * @return The price of the item in cents
     */
    public int getPrice(){
        return price;
    }

    /**
     * Will return the calories of the item
     * @return The number of calories in the item
     */
    public int getCal(){
        return calories;
    }

    /**
     * Will return the description of the item
     * @return The description of the item
     */
    public String getDesc(){
        return desc;
    }

    /**
     * Will return the ArrayList of the ingredients
     * @return The ingredients of the item in an ArrayList
     */
    public String getIngredients(){
        return ingredients;
    }

    /**
     * Will return the ArrayList of the requests for the item
     * @return The requests of the item in an ArrayList
     */
    public ArrayList<String> getRequests(){
        return requests;
    }

    /*-------------------------------------------
        Item Setter Methods
    -------------------------------------------*/ 
    /**
     * Changes the name of the item
     * @param n new name of the item
     */
    public void setName(String n){
        name = n;
    }

    /**
     * Changes the price of the item
     * @param p new price of the item in cents
     */
    public void setPrice(int p){
        price = p;
    }

    /**
     * ToString method
     * Returns a string representation of a item object
     * @return string representation of item
     */
    public String toString(){
        String returnString = "";

        returnString += name + ":\n" ;
        returnString += String.format("   $%.2f  |", this.convertPrice());
        returnString +=  "   " + calories + " calories\n";
        returnString +=  "   " + desc + "\n";
        returnString +=  "   " + ingredients + "\n";

        // Adds requests to the string if it is not empty
        if (!requests.isEmpty()){ 
            returnString +=  "   " + requests + "\n";
        }
        return returnString;
    }
}
