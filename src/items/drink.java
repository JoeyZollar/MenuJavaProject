package items;

import java.util.ArrayList;

/**
 * This drink class was made for CS067 Final project under the teaching of professor Timothy Urness
 * This class represents a drink item on a menu that can be added to a cart and displayed visually
 * @author Joey Zolalr
 */
public class drink extends item implements drinkInterface{
    private int size; // int that represents the size of the drink
    private int smallPrice; // price of the item in cents if the size is small
    private int mediumPrice; // price of the item in cents if the size is medium
    private int largePrice; // price of the item in cents if the size is large

    /**
     * Constructor for drink object
     * @param n name of drink
     * @param p price of drink in cents
     * @param c calories of the drink
     * @param d description of the drink
     * @param i ingredients of the dirnk
     * @param s size of the drink
     */
    public drink(String n, int p, int c, String d, String i, int s){
        super(n, p, c, d, i);
        size = s;
        smallPrice = p; // Setting small price as default
        mediumPrice = p * 2; // Setting medium price
        largePrice = p * 3; // Setting large price
    }

    /**
     * Constructor for drink object that includes a requests
     * @param n name of drink
     * @param p price of drink in cents
     * @param c calories of the drink
     * @param d description of the drink
     * @param i ingredients of the dirnk
     * @param r requests ArrayList
     * @param s size of the drink
     */
    public drink(String n, int p, int c, String d, String i, ArrayList<String> r,int s){
        super(n, p, c, d, i, r);
        size = s;
        smallPrice = p; // Setting small price as the default
        mediumPrice = p * 2; // Setting medium price
        largePrice = p * 3; // Setting large price
    }

    /**
    * Will return the size of the item as a string. Will return small, medium, or large depending on the value of size
    * 0 is small, less than 0 is medium, and greater than 0 will be large
    * @return A string that is the size of the item 
    */
    public String getSize(){
        String curSize = "Small";

        if(size < 0){
            curSize = "Medium";
            return curSize;

        } else if (size > 0){
            curSize = "Large";
            return curSize;

        } else {
            return curSize;
        }
    }

    /**
     * Will change the size of the item and adjust the price depedning on what it was changed to
     * @param s The new size of the drink
     */
    public void setSize(int s){
        if (s == 0){ // If changing to small drink
            this.setPrice(smallPrice);

        } else if (s < 0){ // If changing to Medium
            this.setPrice(mediumPrice);

        } else if (s > 0){ // If changing to large
            this.setPrice(largePrice);
        }

        size = s; // Set the new size
    }

    /**
     * Creates a new drink with the same fields, with a deep copied list of requests
     * @return A new drink that has the request ArrayList deep copied
     */
    public drink duplicateAsDrink(){
        ArrayList<String> newReqs = new ArrayList<String>(); // Create new ArrayList in memory

        for (int i = 0; i < this.getRequests().size(); i++){ // Add each request from old list into new list
            newReqs.add(this.getRequests().get(i));
        }

        drink newDrink = new drink(this.getName(), this.getPrice(), this.getCal(),this.getDesc(), this.getIngredients(), newReqs,size); // Create new dirnk

        return newDrink;
    }

    /**
     * Returns a string representation of a item object
     * @return string representation of drink
     */
    @Override
    public String toString(){
        String returnString = "";

        returnString += this.getSize() + " " + this.getName() + ":\n" ;
        returnString += String.format("   $%.2f  |", this.convertPrice());
        returnString +=  "   " + this.getCal() + " calories\n";
        returnString +=  "   " + this.getDesc() + "\n";
        returnString +=  "   " + this.getIngredients() + "\n";

        // Adds requests to the string if it is not empty
        if (!this.getRequests().isEmpty()){
            returnString +=  "   " + this.getRequests() + "\n";
        }
        return returnString;
    }
}
