import java.util.*;
import java.io.*;

public class HomeCustomer {
    public static void main(String[] args) {
    HomeCustomer h = new HomeCustomer();
     Scanner input = new Scanner(System.in);


     do{
        // System.out.println("                                                 ▄▄   ▄▄          ");                 
        // System.out.println("▀███▀▀▀███                 ▀███▀▀▀██▄          ▀███   ██                             ");
        // System.out.println(" ██    ▀█                   ██    ▀██▄          ██                                  ");
        // System.out.println(" ██   █   ▄██▀██▄  ▄██▀██▄  ██     ▀██ ▄▄█▀██   ██ ▀███ ▀██▀   ▀██▀  ▄▄█▀██▀███▄███" );
        // System.out.println(" ██▀▀██  ██▀   ▀████▀   ▀██ ██      ██▄█▀   ██  ██   ██   ██   ▄█   ▄█▀   ██ ██▀ ▀▀" );
        // System.out.println(" ██   █  ██     ████     ██ ██     ▄████▀▀▀▀▀▀  ██   ██    ██ ▄█    ██▀▀▀▀▀▀ ██     ");
        // System.out.println(" ██      ██▄   ▄████▄   ▄██ ██    ▄██▀██▄    ▄  ██   ██     ███     ██▄    ▄ ██     ");
        // System.out.println("▄████▄     ▀█████▀  ▀█████▀▄████████▀   ▀█████▀▄████▄████▄    █       ▀█████▀████▄   ");
        try{
        clearScreen();
        System.out.println("========================================================================");
        System.out.println("Select user type: ");
        System.out.println("1. Restaurant");
        System.out.println("2. Customer");
        System.out.println("========================================================================");
        
            int choice = input.nextInt();
            if (choice == 1) { //show the menu for restaurant owners
                //
                // Restaurant owner code
                //
                
            }
            else if (choice == 2) {  //show the menu for customers
                try{
                h.customerStartMenu();
                }
                catch(Exception e) {
                    System.out.println("Error: Please try again.");
                    System.out.println(e);
                    Thread.sleep(2000);
                    h.customerStartMenu();
                }
            
            }
            else {
                throw new InputMismatchException("Must enter integer");
            }
        }
        catch (Exception ex) {
            System.out.println("Invalid entry. Please enter the number of your choice: ");
            input.nextLine();
        }
    } while (true);
    }

    static void clearScreen() {
		// try{
		// 	final String osName = System.getProperty("os.name");
		// 	if (osName.toLowerCase().contains("windows"))
		// 		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		// 	else
		// 		new ProcessBuilder("clear").inheritIO().start().waitFor();
		// }
		// catch(IOException ex){
		// 		System.out.println(ex.getMessage());
		// }
		// catch(InterruptedException ex){
		// 		System.out.println(ex.getMessage());				
		// }
	}

    public void customerStartMenu() throws IOException, InterruptedException {
        clearScreen();
        Scanner input = new Scanner(System.in);
        System.out.println("========================================================================");
        System.out.println("Please enter the number corresponding with your desired option: ");
        System.out.println("========================================================================");

        System.out.println("1. Login ");
        System.out.println("2. Create account ");
        int choice = input.nextInt();
        if (choice == 1) {
            customerLoginScreen();
        }
        else if (choice == 2) {
            customerRegisterScreen();
        }
    }

    public void customerLoginScreen() throws IOException, InterruptedException {
        clearScreen();
        Scanner input = new Scanner(System.in);
        
        System.out.println("========================================================================");
        System.out.println("Please enter your username and password, seperated by a space (E.g. \"jack87 pass8362\") ");
        System.out.println("Alternatively, enter \"BACK\" to go to the previous menu. ");
        System.out.println("========================================================================");

        
        String choice = input.next();
        if (choice == "BACK") {
            customerStartMenu();
        }
        else {
            String userAttempt = choice;
            //System.out.println(userAttempt); ///Debugging
            try {
                Customer c = new Customer(userAttempt);
                // System.out.println(c); //Debugging
                // System.out.println("Test " + c.getUsername()); //Debugging
                // System.out.println("Test " + c.getPassword()); //Debugging

                String passAttempt = input.next();
                // System.out.println("Test " + passAttempt); //Debugging

                if (passAttempt.equals(c.getPassword())) {
                    System.out.println("Login Successful. Welcome "+ c.getName()+"! ");
                    customerMainMenu(c);
                }
                else if (!(c.getPassword().equals(passAttempt))){
                    System.out.println("Login failed. Please try again. ");
                    customerLoginScreen();
                }
            } catch (IOException e) {
                System.out.println("Error:" + e.getMessage() +". Username does not exist.");
            }
        }
    }

    public void customerRegisterScreen() throws IOException, InterruptedException{
        clearScreen();
        Scanner input = new Scanner(System.in);
        
        System.out.println("========================================================================");
        System.out.println("Please enter your desired username(E.g. \"jack87\" )");
        System.out.println("========================================================================");
        String choice = input.next();

        if (choice != "BACK") {
            String userCreateAttempt = choice;
            try {
                if ( new File("Customer\\" + userCreateAttempt + "\\").exists()) {              //Checks if username already exists
                    clearScreen();
                    System.out.println("Error: Username already taken. Please try another one.");
                    customerRegisterScreen();
                }
                else if (userCreateAttempt.contains("_")) {                         //Checks if username contains illegal character _
                    clearScreen();
                    System.out.println("Error: Username cannot contain an underscore \"_\". Please try again.");
                    customerRegisterScreen();
                }
                else {
                    clearScreen();
                    System.out.println("========================================================================");
                    System.out.println("Please enter your desired password(E.g. \"pass8362\")" );
                    System.out.println("========================================================================");
                    String passCreateAttempt = input.next();

                    clearScreen();
                    System.out.println("========================================================================");
                    System.out.println("Please enter your first name(E.g. \"John\")" );
                    System.out.println("Alternatively, enter \"BACK\" to go to the previous menu. ");
                    System.out.println("========================================================================");
                    String nameCreateAttempt = input.next();

                    clearScreen();
                    System.out.println("========================================================================");
                    System.out.println("Please enter your address(E.g. \"14 Main St., 53000 KL\")" );
                    System.out.println("Alternatively, enter \"BACK\" to go to the previous menu. ");
                    System.out.println("========================================================================");
                    String addressCreateAttempt = input.nextLine();


                    Customer c = new Customer(nameCreateAttempt, userCreateAttempt, passCreateAttempt, addressCreateAttempt);       //Constructs new Customer, including files and directory
                    customerStartMenu();
                }
            } catch (IOException e) {
                System.out.println("Error:" + e.getMessage() +". Username does not exist.");
            }
        }

        else if (choice == "BACK"){
            customerStartMenu();
        }
    }



        // String choice = input.next();
        // if (choice == "BACK") {
        //     ui1();
        // }
        

    public void customerMainMenu(Customer c) throws IOException, InterruptedException{
        Scanner input = new Scanner(System.in);
        clearScreen();
        
        System.out.println("========================================================================");
        System.out.println("Welcome " + c.getName()+ ". Please enter the number corresponding with your desired option: ");
        System.out.println("========================================================================");
        System.out.println("1. View Restaurants ");
        System.out.println("2. View Active Order ");
        System.out.println("3. View Order History ");
        System.out.println("4. Back to user select ");
        System.out.println("========================================================================");

        int choice = input.nextInt();
        if (choice == 1) {
            customerRestaurantList(c);
        }
        else if (choice == 2) {
            customerActiveOrderMenu(c);
        }
        else if (choice == 3) {
            customerOrderHistoryMenu(c);
        }
        else if (choice == 4) {
            ///////////// ENTER CODE TO GO BACK TO USER SELECT
        }
    }

    public void customerRestaurantList(Customer c) throws IOException, InterruptedException{
        clearScreen();
        Scanner input = new Scanner(System.in);
        
        Cart crt = new Cart();  

        System.out.println("========================================================================");
        System.out.println("Please enter the number corresponding with your desired option: ");
        System.out.println("1. Pizza Palace ");
        System.out.println("2. Mishaltit ");
        System.out.println("3. Mamak Spot ");
        System.out.println("4. Back to previous menu ");
        System.out.println("========================================================================");

        int choice = input.nextInt();                               //Constructs Restaurant object for selected restaurant, and passes it to next menu screen
        if (choice == 1) {
            Restaurant r = new Restaurant ("Pizza Palace","pizzaPalace01", "0123456789",        
             "Come home to true Italian Pizza at Pizza Palace, we offer a wide range of home-made Italian Pizzas alongside a menu complete with classic and rustic Italian dishes and a variety of cocktails.", "29 Jalan Riong, Bangsar, Kuala Lumpur, MY 59100");
            customerViewRestaurantMenu(c, r, crt);
        }
        else if (choice == 2) {
            Restaurant r = new Restaurant ("Mishaltit", "mish123_99","0123456789", "Mishaltit restaurant is the best choice for Arabic & Western cuisine , Promises a value lifestyle proposition of great variety and quality food at affordable prices ", " 226 Jalan Ampang, Kuala Lumpur, MY 50450");
            customerViewRestaurantMenu(c,r,crt);
        }
        else if (choice == 3) {
            Restaurant r = new Restaurant ("Mamak Spot", "mk33St", "0123456789", "Mamak Station brings you the best in comfort food from the diverse street vendors of Malaysia. Our food is a celebration of flavors...layered from Chinese, Indian, and Malay roots.", "2 Jalan Robertson, G4 & G5, Idaman Robertson, Kuala Lumpur, MY 50150");
            customerViewRestaurantMenu(c,r, crt);
        }
        else if (choice == 4) {
            customerMainMenu(c);
        }
    }

    public void customerActiveOrderMenu(Customer c) throws IOException, InterruptedException{
        clearScreen();
        Scanner input = new Scanner(System.in);
        if (c.getCurrentOrder().isEmpty()) {
            System.out.println("========================================================================");
            System.out.println("No current order. Enter anything to go back to previous menu. ");
            System.out.println("========================================================================");
        input.next();
        customerMainMenu(c);
        }
        else if (!c.getCurrentOrder().isEmpty()){                   //Retrieves list of active orders, and prints order information
            for (int i = 0; i < c.getCurrentOrder().size(); i++){
            System.out.println("========================================================================");
            System.out.println("Active Orders: ");
            System.out.println("ID: " + c.getCurrentOrder().get(i).getID()+". ");
            System.out.println("Restaurant: " + c.getCurrentOrder().get(i).getResName()+". ");
            System.out.println("Price: " +c.getCurrentOrder().get(i).getOrderPrice()+"RM. ");
            System.out.println("Status: " +c.getCurrentOrder().get(i).getOrderStatus());
            System.out.println("========================================================================");
            }
            System.out.println("\nPlease enter the number corresponding with your desired option: "); 
            //System.out.println("1. Set order as Collected ");
            System.out.println("1. Back to previous menu ");
            System.out.println("========================================================================");
            int choice = input.nextInt();
            // if (choice == 1){
            //     try{
            //     Order.replaceOrderStatus(c.getCurrentOrder().get(0), "Collected");
            //     c.setCurrentOrder(null);
            //     customerMainMenu(c);
            //     }
            //     catch (Exception e) {
            //     System.out.println("Error. Please try again. ");
            //     Thread.sleep(500);
            //     customerActiveOrderMenu(c);
            //     }
            // }
            if (choice == 1){
                customerMainMenu(c);
            }
        }
        else {
            System.out.println("Error: returning to previous menu. ");
            Thread.sleep(2000);
            customerMainMenu(c);
        }
    }

    public void customerOrderHistoryMenu(Customer c) throws IOException, InterruptedException{
        clearScreen();
        File cusOrders = new File("Customer\\" + c.getUsername() + "\\Order\\names.txt");               //Reads in all customer order names
        Scanner cusOrdersReader = new Scanner(cusOrders);
        
        System.out.println("========================================================================");
        System.out.println("\nPast Orders: ");
        System.out.println("\n"); 
        System.out.println("========================================================================");
        while (cusOrdersReader.hasNext()){                                                                      
            Order o = new Order("Customer\\" + c.getUsername() + "\\Order\\" + cusOrdersReader.nextLine() + ".txt");        //Constructs orders from stored files using the previously retrieved order names
            System.out.println("\n__________________________");
            System.out.println(o.toStringHistory());                    //Prints out order information
            System.out.println("__________________________\n");
        }
        System.out.println("========================================================================");
        System.out.println("\n"); 

        System.out.println("\nEnter anything to go back to previous menu. "); 
        Scanner input = new Scanner(System.in);
        input.next();
        customerMainMenu(c);

        }    

    public void customerViewRestaurantMenu(Customer c, Restaurant r, Cart crt) throws IOException, InterruptedException{
        clearScreen();
        
        System.out.println("========================================================================");
        System.out.println("Welcome to " + r.getName()+". ");
        System.out.println(r.getDescription());
        System.out.println("========================================================================");

        //Cart crt = new Cart();
        System.out.println("Select items to add to shopping cart");

        int menuChoices = 1;
        ArrayList<Item> menuContents = r.getMenu().getMenuContents();

        Collections.sort(menuContents, new ItemCompartorByPrice()); // ITEM COMPARATOR GOES HERE!!!!!!!!!

        System.out.printf("%-42s%-20s%-10s%n","Item name", "Type","Price (RM)");

        System.out.println("_________________________________________________________________________________________________________________\n");

        for (Item i : menuContents){
            System.out.printf("%-2s%-40s%-20s%-10f%n",menuChoices + ". ", i.getItemName(),i.getItemType(),i.getItemPrice());
            menuChoices++;
        }

        System.out.println(menuChoices++ + ". View shopping cart ");
        System.out.println(menuChoices + ". Back to restaurant ");
        System.out.println("========================================================================");

        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
        if (choice <= (menuChoices-2)){
            crt.addSelectedItem(menuContents.get(choice-1));
            clearScreen();
            System.out.println("Item added.");
            Thread.sleep(500);
            customerViewRestaurantMenu(c, r, crt);
        }
        else if ( (choice <=menuChoices) && (choice>(menuChoices-2) )){
            if (choice == (menuChoices-1)) {
                customerViewCartMenu(c,r,crt);
            }
            else if (choice == menuChoices){
                customerRestaurantList(c);
            }
        }
        else {
            System.out.println("Error: Please enter one of the available options.");
            customerViewRestaurantMenu(c, r,crt);
        }
    }

    public void customerViewCartMenu(Customer c, Restaurant r, Cart crt) throws IOException, InterruptedException {
        clearScreen();
        
        System.out.println("========================================================================");

        System.out.println("Restaurant: " + r.getName()

                      +"\n\nContents:"); 

        for (int i = 0; i< crt.getChosenItems().size(); i++){
            //System.out.println(crt.getChosenItems().get(i).getItemName() + "           " + (crt.getChosenItems().get(i)).getItemPrice()+" RM.");    //Prints out items in cart
            System.out.printf("%30s%10f%2s%n", crt.getChosenItems().get(i).getItemName(), (crt.getChosenItems().get(i)).getItemPrice(), " RM");
        }
        System.out.println("\n");
        System.out.println("========================================================================");
        System.out.println("\n1. Confirm order as collection");
        System.out.println("2. Confirm order as delivery");
        System.out.println("3. Remove item");
        System.out.println("4. Empty cart ");
        System.out.println("5. Back to restaurant");
        System.out.println("========================================================================");


        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();

        if (choice == 1) {
            Order o = c.createNewOrder(r, crt, "Collection", c.getAddress());       //Creates order file with collection delivery type
            c.addToCurrentOrder(o);
            c.increaseOrderCount();
            //System.out.println("Current order: " + c.getCurrentOrder()); /////////Debugging
            r.addToCurrentOrders(o);
            System.out.println("Order successfully created.");

            //int startSize = crt.getChosenItems().size();
            // for (int i = 0 ; i<  startSize; i++) {
            //     crt.removeItem(crt.getChosenItems().get(i));
            // }

            customerMainMenu(c);
        }
        else if (choice == 2) {
            Scanner addressIn = new Scanner(System.in);
            clearScreen();
            System.out.println("\n");
            System.out.println("========================================================================");
            System.out.println("Please enter your delivery address:");
            System.out.println("========================================================================");
            String inputAddress = addressIn.nextLine();
            Order o = c.createNewOrder(r, crt, "Delivery", inputAddress);           //Creates order file with "delivery" delivery type
            c.addToCurrentOrder(o);
            c.increaseOrderCount();
            //System.out.println("Current order: " + c.getCurrentOrder()); /////////Debugging
            r.addToCurrentOrders(o);
            System.out.println("Order successfully created.");
            Thread.sleep(1000);

            //int startSize = crt.getChosenItems().size();
            // for (int i = 0 ; i<  startSize; i++) {
            //     crt.removeItem(crt.getChosenItems().get(i));
            // }

            customerMainMenu(c);
        }
        else if (choice == 3) {
            clearScreen();
            System.out.println("Please select item to remove.");
            int menuChoices = 1;
            for (int i = 0; i< crt.getChosenItems().size();i++){
                System.out.println(Integer.toString(menuChoices) + ". " + crt.getChosenItems().get(i).getItemName() + "           " + (crt.getChosenItems().get(i)).getItemPrice()+" RM.");
                menuChoices++;
            }
            System.out.println(menuChoices + ". Back to cart.");

            Scanner input1 = new Scanner(System.in);
            int choice1 = input1.nextInt()-1;
            if (choice1 <= menuChoices-1){
                crt.removeItem(crt.getChosenItems().get(choice1));
                customerViewCartMenu(c, r, crt);
            }
            if (choice1 == menuChoices) {
                customerViewCartMenu(c,r,crt);
            }
            else {
                System.out.println("Error: Please enter one of the available options.");
                customerViewRestaurantMenu(c, r,crt);
            }
            
        }
        else if (choice == 3) {
            int startSize = crt.getChosenItems().size();
            for (int i = 0 ; i<  startSize; i++) {
                crt.removeItem(crt.getChosenItems().get(i));
            }
            customerViewCartMenu(c, r, crt);
        }
        else if (choice == 4) {
            customerViewRestaurantMenu(c, r,crt);
        }
        else {
            System.out.println("Error: Please enter one of the available options.");
            Thread.sleep(2000);
            customerViewCartMenu(c, r, crt);
        }
    }

    
}