import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;

import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.io.FileNotFoundException;
import items.*;

/**
 * This program was made for CS067 Final project Fall 2023 under the teaching of professor Timothy Urness
 * This is the main file of the program, responsible for creating the visuals and simulating the shopping inplementation
 * 
 * Summary of the project:
 *     This project reads a .txt file and creates a GUI using javaFx menu that the user can interact with add things to their cart 
 *     It has a secondary screen where the user can edit their cart in multiple ways and then complete their order
 *     javaFx is used in combination with normal java object orientated programming files to complete the interactivity
 *     Completing this project required me to learn many aspects of javaFx as well as apply my knowledge in OOP and CSS
 *     I hope you enjoy my project!
 * 
 * ---------------------------------------------------------------
 * The starting function is the Start() method SARTING AT LINE 371
 * ---------------------------------------------------------------
 * @author Joey Zollar
 */
public class Main extends Application {
    /* ---------------------------------------------------------------------------------
     * See Menu.java to learn more about how to format the .txt file to fit this program
     * -------------------------------------------------------------------------------*/
    String menuFile = "src/menus/culversMenu.txt"; //Name of the menu file to create the menu from
    int screenWidth = 1700; // The width that the scene will be in pixels

    /**
     * This "honkin" method is responsible for building the entire GUI for the cart/checkout screen
     * as well as interactive compontents used to complete and edit the user's cart
     * It is called any time the user switches from the menu scene to the cart scene
     * @param cart the cart being displayed
     * @param button the button object that is used to go back to the menu scene
     * @return the refreshed cart scene
     */
    public Scene refreshCart(Cart cart, Button button){
        /*-------------------------------------------
            Building the top bar of the cart screen
        -------------------------------------------*/ 
        // Creating the cart label
        Label menuName = new Label("Cart");
        menuName.setFont(Font.font("Arial", FontWeight.BLACK, FontPosture.REGULAR ,48));

        // Creating cart label container
        HBox menuNameContainer = new HBox(menuName);
        HBox.setHgrow(menuNameContainer, Priority.ALWAYS);
        menuNameContainer.setAlignment(Pos.CENTER_LEFT);
        HBox.setMargin(menuNameContainer, new Insets(0,0,0,30));

        // Creating section for the menu button to be in
        StackPane buttonContainer = new StackPane(button);
        HBox.setHgrow(buttonContainer, Priority.ALWAYS);
        buttonContainer.setAlignment(Pos.CENTER_RIGHT);
        HBox.setMargin(buttonContainer, new Insets(0,50,0,0));
        button.getStyleClass().add("Cart-Button"); // Add CSS class to apply styles
        button.setFont(Font.font("Arial", FontWeight.BLACK, FontPosture.REGULAR, 18));

        // Setting up the bar 
        HBox menuBar = new HBox(menuNameContainer,buttonContainer); // Add name and button container to menu bar
        menuBar.getStyleClass().add("Bar"); // Add CSS class to apply styles
        menuBar.setStyle("-fx-background-color: #e4e4e4;\n");
        menuBar.setMinHeight(150);

        /*-------------------------------------------
            Building the checkout portion
        -------------------------------------------*/ 
        // Creating total label
        Label total = new Label("Total: $" + cart.convertPrice());
        total.setFont(Font.font("Arial", FontWeight.BLACK, FontPosture.REGULAR, 36));

        // Creating total label container
        HBox totalContainer = new HBox(total);
        totalContainer.setAlignment(Pos.CENTER);
        totalContainer.setPadding(new Insets(50, 0, 20, 0));

        // Creating complete button
        Button complete = new Button("Complete Purchase");
        complete.setFont(Font.font("Arial", FontWeight.BLACK, FontPosture.REGULAR, 18));
        complete.getStyleClass().add("Cart-Button"); // Add CSS style class to ad styles
        
        // Creating checkout message
        Label checkoutMessage = new Label("");
        checkoutMessage.setFont(Font.font("Arial", FontWeight.BLACK, FontPosture.REGULAR, 18));
        checkoutMessage.setPadding(new Insets(20));

        // Adding the elements into a VBox
        VBox checkoutBox = new VBox(totalContainer,complete,checkoutMessage);
        checkoutBox.setMaxWidth(screenWidth/2);
        checkoutBox.setAlignment(Pos.TOP_CENTER);
        HBox.setHgrow(checkoutBox, Priority.ALWAYS);

        /*-------------------------------------------
            Building the the cart base portion
        -------------------------------------------*/ 
        VBox cartBox = new VBox(); // VBox that will contain all of the items
        cartBox.setMaxWidth(screenWidth/2);

        // Scroll pane that contains the cartBox
        ScrollPane cartPane = new ScrollPane(cartBox);
        cartPane.setFitToWidth(true);
        cartPane.setMaxWidth(screenWidth/2);
        HBox.setHgrow(cartPane, Priority.ALWAYS);

        /*-------------------------------------------
            Creating the cart items
        -------------------------------------------*/ 
        // Loop will go through each item in the cart and create a box for it
        for (int i = 0; i < cart.getNumOfItems(); i++){
            item currentItem = cart.getItemAt(i); // Current item being created

            // Creating the item name
            Label itemName = new Label(currentItem.getName());
            itemName.setWrapText(true);
            itemName.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR ,24));

            // Creating name container box
            HBox nameBox = new HBox(itemName);
            HBox.setMargin(nameBox,new Insets(20,20,0,0));
            nameBox.setStyle("-fx-padding: 10 0 5 0;\n");

            // Creating the item price
            Label itemPrice = new Label(String.format("$%.2f", currentItem.convertPrice()));
            itemPrice.setFont(Font.font("Arial", FontWeight.MEDIUM, FontPosture.REGULAR ,20));

            // Creating delete button
            Button delButton = new Button("Delete");
            delButton.setFont(Font.font("Arial", FontWeight.BLACK, FontPosture.REGULAR, 16));
            delButton.getStyleClass().add("Delete-Button"); // Add CSS class to add style properties

            // Creating delete button container
            HBox delButtonContainer = new HBox(delButton);
            delButtonContainer.setMinWidth(100);
            delButtonContainer.setAlignment(Pos.CENTER);
            HBox.setHgrow(delButtonContainer, Priority.ALWAYS);

            // Adding input box for requests
            TextField requestInput = new TextField();
            Button addRequest = new Button("Add request");
            
            // Adding request clear button
            Button clearButton = new Button("Clear requests");
            HBox requestBox = new HBox(requestInput,addRequest,clearButton);

            // Creating request label
            Label requestText = new Label("");
            VBox.setVgrow(requestText, Priority.ALWAYS);
            requestText.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR ,16));

            // Creating request label container
            HBox requestContainer = new HBox(requestText);
            requestContainer.setMaxWidth(500);
            VBox.setVgrow(requestContainer,Priority.ALWAYS);
            VBox.setMargin(requestContainer, new Insets(0,0,5,0));
    
            // Adding all elements into a VBox
            VBox infoBox = new VBox(nameBox,itemPrice, requestContainer, requestBox);
            HBox.setHgrow(infoBox, Priority.ALWAYS);

            // Adding info and delete button in an HBox
            HBox itemBox = new HBox(infoBox,delButtonContainer);
            HBox.setHgrow(itemBox, Priority.ALWAYS);
            itemBox.getStyleClass().add("Item-Container");
            itemBox.setPadding(new Insets(20,20,36,20));
            VBox.setMargin(itemBox, new Insets(0,0,25,0));

            // Add the final item box to the cart VBox 
            cartBox.getChildren().add(itemBox);

            /*-------------------------------------------
            Coding button interactivity using lambda expressions
            -------------------------------------------*/ 
            // Adding functionality to the delete button
            delButton.setOnAction(event -> {
                cart.getItems().remove(currentItem); // Remove the item from the cart
                cartBox.getChildren().remove(itemBox); // Remove the item visually from the cartBox
                cart.setTotal(cart.getTotal() - currentItem.getPrice()); // Remove the price of the item from the total
                total.setText("Total: $" + cart.convertPrice()); // Refresh the total label to the new total
            });
            // Adding functionality to clear request button
            clearButton.setOnAction(event -> {
                currentItem.clearRequests(); // Clear the item's requests
                requestText.setText(""); // Clear the label's value
            });

            // Adding functionality to add request button and input box
            addRequest.setOnAction(event -> {
                if (requestInput.getText().length() > 0){ // If the input box has anything inside of it
                    currentItem.newRequest(requestInput.getText()); // Add request to the item's requests
                    requestText.setText(currentItem.requestToString()); // Set the label's value to the item's current requests
                    requestInput.setText(""); // Clear the input box
                }
            });
        }
        /*-------------------------------------------
            Creating the cart drinks
        -------------------------------------------*/ 
        // Loop will go through each drink in the cart and create a box for it
        for (int i = 0; i < cart.getNumOfDrinks(); i++){
            drink currentDrink = cart.getDrinkAt(i); // The current drink object being created

             // Creating the drink name
            Label drinkName = new Label(currentDrink.getSize() + " " + currentDrink.getName());
            drinkName.setWrapText(true);
            drinkName.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR ,24));

            // Creating the name container
            HBox nameBox = new HBox(drinkName);
            HBox.setMargin(nameBox,new Insets(20,20,0,0));
            nameBox.setStyle("-fx-padding: 10 0 5 0;\n");

            // Creating the drink price
            Label drinkPrice = new Label(String.format("$%.2f", currentDrink.convertPrice()));
            drinkPrice.setFont(Font.font("Arial", FontWeight.MEDIUM, FontPosture.REGULAR ,20));

            // Creating the delete button
            Button delButton = new Button("Delete");
            delButton.setFont(Font.font("Arial", FontWeight.BLACK, FontPosture.REGULAR, 16));
            delButton.getStyleClass().add("Delete-Button");

            // Creating the delete button container
            HBox delButtonContainer = new HBox(delButton);
            delButtonContainer.setMinWidth(100);
            delButtonContainer.setAlignment(Pos.CENTER);
            HBox.setHgrow(delButtonContainer, Priority.ALWAYS);

            // Creating the request input box
            TextField requestInput = new TextField();
            Button addRequest = new Button("Add request");
            
            // Creatting the clear requests button
            Button clearButton = new Button("Clear requests");
            HBox requestBox = new HBox(requestInput,addRequest,clearButton);

            // Creating the request label
            Label requestText = new Label("");
            requestText.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR ,16));
            VBox.setVgrow(requestText, Priority.ALWAYS);

            // Creating the request container
            HBox requestContainer = new HBox(requestText);
            requestContainer.setMaxWidth(500);
            VBox.setVgrow(requestContainer,Priority.ALWAYS);
            VBox.setMargin(requestContainer, new Insets(0,0,5,0));

            // Creating text to display the size
            Label sizeText = new Label("Size: ");
            sizeText.setFont(Font.font("Arial", FontWeight.MEDIUM, FontPosture.REGULAR, 16));

            // Creating the size text container
            HBox sizeTextContainer = new HBox(sizeText);
            sizeTextContainer.setPadding(new Insets(0,5,0,0));
            sizeTextContainer.setAlignment(Pos.CENTER);

            // Creating combo box to select size option
            ComboBox<String> sizeOptions = new ComboBox<String>();
            sizeOptions.getItems().addAll("Small","Medium","Large"); // Adding size options
            HBox sizeBox = new HBox(sizeTextContainer,sizeOptions); // Adding comboBox and text to HBox
            sizeBox.setPadding(new Insets(5,0,0,0));
    
            // Adding all elements into a VBox
            VBox infoBox = new VBox(nameBox,drinkPrice, sizeBox, requestContainer, requestBox);
            HBox.setHgrow(infoBox, Priority.ALWAYS);

            // Adding info and delete button into an HBox
            HBox itemBox = new HBox(infoBox,delButtonContainer);
            itemBox.getStyleClass().add("Item-Container");
            itemBox.setPadding(new Insets(20,20,36,20));
            VBox.setMargin(itemBox, new Insets(0,0,25,0));
            HBox.setHgrow(itemBox, Priority.ALWAYS);

            // Adding the final itemBox into the cart
            cartBox.getChildren().add(itemBox);

            /*-------------------------------------------
            Coding button interactivity using lambda expressions
            -------------------------------------------*/ 
            // Adding functionality to the delete button
            delButton.setOnAction(event -> {
                cart.getDrinks().remove(currentDrink); // Remove drink from the cart
                cartBox.getChildren().remove(itemBox); // Remove drink visually from the cartBox
                cart.setTotal(cart.getTotal() - currentDrink.getPrice()); // Subtract drink cost from the cart total
                total.setText("Total: $" + cart.convertPrice()); // Refresh total to show new value
            });

            //  Adding functionality to the clear requests button
            clearButton.setOnAction(event -> {
                currentDrink.clearRequests(); // Clear requests from the current drink
                requestText.setText(""); // Clear the value of the requests label 
            });

            //  Adding functionality to the add request button and input box
            addRequest.setOnAction(event -> {
                if (requestInput.getText().length() > 0){ // If there is something in the input box
                    currentDrink.newRequest(requestInput.getText()); // Add the request to the drink object
                    requestText.setText(currentDrink.requestToString() + "\n"); // Add request to the requests label
                    requestInput.setText(""); // Clear the input box's value
                }
            });

            // Adding functionality to the size combo box
            sizeOptions.setOnAction(event -> {
                long currentTotal = cart.getTotal(); // The CURRENT total of the cart

                currentTotal -= currentDrink.getPrice(); // Subtract the current price of the drink from the current total

                // Chnage the size of the drink to what is selected
                if (sizeOptions.getValue().equals("Medium")){ // If the medium option is selected
                    currentDrink.setSize(-1); // Set the size of the drink to medium
                } else if (sizeOptions.getValue().equals("Large")){ // If the large option is selected
                    currentDrink.setSize(1); // Set the size of the drink to large
                } else { // If nothing else, the small option is the default
                    currentDrink.setSize(0); // Set the size of the drink to small
                }

                currentTotal += currentDrink.getPrice(); // Add the new price of the drink back to the current total
                cart.setTotal(currentTotal); // Set the total to be the current total that has been calculated

                drinkName.setText(currentDrink.getSize() + " " + currentDrink.getName()); // Change the name of the drink to match the size
                total.setText("Total: $" + cart.convertPrice()); // Update the total label
                drinkPrice.setText(String.format("$%.2f", currentDrink.convertPrice())); // Update the price label for the drink
            });
        }

        /*-------------------------------------------
           Coding the complete order section
        -------------------------------------------*/ 
        // Adding functionality to the complete button
        complete.setOnAction(event ->{
            if (cart.getTotal() > 0){ // If the cart's total is greater than 0
                cartBox.getChildren().clear(); // Remove all items and drinks visually from the cartBox
                cart.clearCart(); // Clear the cart object's items and drinks
                cart.setTotal(0); // Reset the total
                total.setText("Total: $0.00"); // Update the total
                checkoutMessage.setText("Your order has been recieved, thank you!"); // Change complete message
            } else { // If the cart is empty
                checkoutMessage.setText("You must add items to your cart before you can order."); // Change the complete message
            }
        });

        /*-------------------------------------------
           Creating the base structure of the scene
        -------------------------------------------*/ 
        HBox mainBox = new HBox(cartPane,checkoutBox); // Add cart and checkout parts to an HBox
        HBox.setHgrow(mainBox, Priority.ALWAYS);
        VBox.setVgrow(mainBox, Priority.ALWAYS);

        VBox mainPage = new VBox(menuBar,mainBox); // Add the menu bar and mainBox to a VBox

        // Setting up the root of the scene
        Scene cartScene = new Scene(mainPage,screenWidth,1000); // Create the scene with the VBox as the root
        cartScene.getStylesheets().add("styles.css"); // ADD CSS STYLSHEET TO THE SCENE
        return cartScene; // Return the scene object
    }

    /**
     * The main start function of the program
     * Will read the menu .txt file and create the menu object
     * Then will display the menu visually in a scene
     * Adds interactivity to buttons on the scene
     * Responsible for switching between the scenes
     * @Override
     */
    public void start(Stage stage) throws FileNotFoundException {
        Menu menu = new Menu();
        Menu.readMenu(menuFile); // Reads the given file and creates menu ArrayLists

        Cart mainCart = new Cart(); // Create the main cart to be used

        VBox itemBox = new VBox(); // Column for items to be added to

        VBox drinkBox = new VBox();; // Column for the drink to be added to

        HBox menuBox = new HBox(itemBox,drinkBox); // The main HBox that contains both columns

        /*-------------------------------------------
            Creating the menu bar of the scene
        -------------------------------------------*/ 
        // Create the name of the menu
        Label menuName = new Label(menu.getName());
        menuName.setFont(Font.font("Arial", FontWeight.BLACK, FontPosture.REGULAR ,48));

        // Creating the name container
        HBox menuNameContainer = new HBox(menuName);
        HBox.setHgrow(menuNameContainer, Priority.ALWAYS);
        menuNameContainer.setAlignment(Pos.CENTER_LEFT);
        HBox.setMargin(menuNameContainer, new Insets(0,0,0,30));

        // Creating the cart button
        Button cartButton = new Button("Go to cart");
        cartButton.getStyleClass().add("Cart-Button"); // Add CSS class to add stlyes
        cartButton.setFont(Font.font("Arial", FontWeight.BLACK, FontPosture.REGULAR, 18));

        // Creating the button container
        StackPane buttonContainer = new StackPane(cartButton);
        HBox.setHgrow(buttonContainer, Priority.ALWAYS);
        buttonContainer.setAlignment(Pos.CENTER_RIGHT);
        HBox.setMargin(buttonContainer, new Insets(0,50,0,0));

        // Add the name and button to an HBox
        HBox menuBar = new HBox(menuNameContainer,buttonContainer);
        menuBar.getStyleClass().add("Bar"); // Add CSS class to add styles
        menuBar.setStyle("-fx-background-color: #e4e4e4;\n");
        menuBar.setPrefHeight(150);

        // Add the menuBar and mainBox to a VBox
        VBox mainStructure = new VBox(menuBar,menuBox);
        mainStructure.setAlignment(Pos.TOP_CENTER);
        mainStructure.setAlignment(Pos.TOP_CENTER);
        HBox.setHgrow(mainStructure, Priority.ALWAYS);

        // Main VBox to a scroll pane
        ScrollPane sp = new ScrollPane(mainStructure);
        sp.setFitToWidth(true);

        /*-------------------------------------------
            Creating the item boxes
        -------------------------------------------*/ 
        // Will loop through the menu item list and create a box for each
        for (int i = 0; i < menu.getItemMenu().size(); i++){
            item currentItem = menu.getItemAt(i); // The current item being created

            // Creating the item title and calories
            String nameString = currentItem.getName();
            Label nameAndCal = new Label(nameString);
            nameAndCal.setWrapText(true);
            nameAndCal.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR ,28));

            // Creating name container
            HBox nameBox = new HBox(nameAndCal);
            HBox.setMargin(nameBox,new Insets(20,20,0,0));
            nameBox.setStyle("-fx-padding: 10 0 5 0;\n");

            // Creating cost label and cost container
            Label costLabel = new Label(String.format("$%.2f  -  " + currentItem.getCal() + " Calories", currentItem.convertPrice()));
            HBox cost = new HBox(costLabel);
            costLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, FontPosture.REGULAR ,20));
            cost.setStyle("-fx-padding: 0 0 5 0;\n");

            // Creating description and ingredients
            String descAndIng = currentItem.getDesc() + "\n\n" + currentItem.getIngredients();
            Label desc = new Label(descAndIng);
            desc.setWrapText(true);
            desc.setFont(new Font("Arial",16));

            // Creating description and ingredients container
            VBox descBox = new VBox(desc);
            VBox.setMargin(descBox, new Insets(0, 0, 20, 0));

            // Adding all the data to a VBox
            VBox data = new VBox(nameBox,cost,descBox);
            HBox.setHgrow(data,Priority.ALWAYS);
            data.setStyle("-fx-padding: 10;\n");

            // Creating the data container
            HBox dataBox = new HBox(data);
            dataBox.setPrefWidth(600);

            // Creating the add button
            Button addButton = new Button("Add item");
            addButton.setFont(Font.font("Arial", FontWeight.BLACK, FontPosture.REGULAR ,16));
            addButton.getStyleClass().add("Add-Button");

            // Creating the add button container
            VBox buttonBox = new VBox(addButton);
            buttonBox.setAlignment(Pos.CENTER);
            buttonBox.setPrefWidth(200);

            // Coding the add button action
            addButton.setOnAction(event -> {
                mainCart.addItem(currentItem); // Add the current item to the cart
            });

            // Adding the data and button to a HBox
            HBox itemContainer = new HBox(dataBox, buttonBox);
            itemContainer.getStyleClass().add("Item-Container");

            // Creating item Container
            HBox hbox = new HBox(itemContainer);
            hbox.setStyle(
                "-fx-padding: 20 10 15 20;\n"
            );

            hbox.getStyleClass().add("Item-Box"); // Add CSS class to add styles

            itemBox.getChildren().add(0, hbox); // Add the final item box visually to the item column
        }
        
        /*-------------------------------------------
            Creating the drink boxes
        -------------------------------------------*/ 
        // Will loop through the menu drink list and create a box for each
        for (int i = 0; i < menu.getDrinkMenu().size(); i++){
            drink currentItem = menu.getDrinkAt(i); // The current drink being created

            // Creating the name and calories label
            String nameString = currentItem.getName();
            Label nameAndCal = new Label(nameString);
            nameAndCal.setWrapText(true);
            nameAndCal.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR ,28));

            // Creating name and calories container
            HBox nameBox = new HBox(nameAndCal);
            HBox.setMargin(nameBox,new Insets(20,20,0,0));
            nameBox.setStyle("-fx-padding: 10 0 5 0;\n");

            // Creating cost label and container
            Label costLabel = new Label(String.format("$%.2f  -  " + currentItem.getCal() + " Calories", currentItem.convertPrice()));
            HBox cost = new HBox(costLabel);
            costLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, FontPosture.REGULAR ,20));
            cost.setStyle("-fx-padding: 0 0 5 0;\n");

            // Creating description and ingredients label
            String descAndIng = currentItem.getDesc() + "\n\n" + currentItem.getIngredients();
            Label desc = new Label(descAndIng);
            desc.setWrapText(true);
            desc.setFont(new Font("Arial",16));

            // Creating  description and ingredients container
            VBox descBox = new VBox(desc);
            VBox.setMargin(descBox, new Insets(0, 0, 20, 0));

            // Adding all data to a VBox
            VBox data = new VBox(nameBox,cost,descBox);
            HBox.setHgrow(data,Priority.ALWAYS);
            data.setStyle("-fx-padding: 10;\n");

            // Making a container for the data VBox
            HBox dataBox = new HBox(data);
            dataBox.setPrefWidth(600);

            // Creating the add buttion
            Button addButton = new Button("Add item");
            addButton.setFont(Font.font("Arial", FontWeight.BLACK, FontPosture.REGULAR ,16));
            addButton.getStyleClass().add("Add-Button");

            // Creating add buttion container
            VBox buttonBox = new VBox(addButton);
            buttonBox.setAlignment(Pos.CENTER);
            buttonBox.setPrefWidth(200);

            // Coding the add button action
            addButton.setOnAction(event -> {
                mainCart.addDrink(currentItem); // Add the current drink to the cart
            });

            // Add the data and button to a HBox
            HBox itemContainer = new HBox(dataBox, buttonBox);

            // Create item container
            HBox hbox = new HBox(itemContainer);
            hbox.setStyle(
                "-fx-padding: 20 20 15 10;\n"
            );
            hbox.getStyleClass().add("Item-Box"); // Add CSS class to add styles
            itemContainer.getStyleClass().add("Item-Container"); // Add CSS class to add styles
           
            drinkBox.getChildren().add(0, hbox); // Add the final drink box to the drink column
        }

        /*-------------------------------------------
            Setting up the menu scene
        -------------------------------------------*/ 
        Scene menuScene = new Scene(sp, screenWidth, 1000); // Create menu Scene

        // Create button to send to the refresh cart method
        Button backToMenuButton = new Button("Back to menu");

        // Coding the cart button to swtich scenes
        cartButton.setOnAction(event -> {
            stage.setScene(refreshCart(mainCart, backToMenuButton)); // refresh the cart and switch to the scene that is returned
        });

        // Coding the button that will go back to the menu
        backToMenuButton.setOnAction(event -> {
            stage.setScene(menuScene); // switch to the original menu scene
        });

        menuScene.getStylesheets().add("styles.css"); // ADD CSS STYLESHEET TO THE SCENE

        stage.setScene(menuScene); // Show the menuScene to the stage as default
        stage.show(); // Start the stage
    }

    /**
     * The main function of the program that launches the program
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }
}