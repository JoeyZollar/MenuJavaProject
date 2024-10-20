package items;

/**
 * This drink interface was made for CS067 Final project under the teaching of professor Timothy Urness
 * This interface lays out the methods that must be present in the drink class definition
 * @author Joey Zollar
 */
public interface drinkInterface{
    public String getSize();
    public void setSize(int s);
    public drink duplicateAsDrink();
}