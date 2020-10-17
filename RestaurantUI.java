import java.io.*;
import java.util.*;

public class RestaurantUI{
	public static void  main(String[] args){
		File allRestDir = new File("Restaurant");
		allRestDir.mkdir();
		Restaurant[] restaurants = {new Restaurant ("Pizza Palace","0123456789", "0123456789", "Come home to true Italian Pizza at Pizza Palace, we offer a wide range of home-made Italian Pizzas alongside a menu complete with classic and rustic Italian dishes and a variety of cocktails.", "29 Jalan Riong, Bangsar, Kuala Lumpur, MY 59100"),
									new Restaurant ("Mishaltit", "0123456789","0123456789", "Mishaltit restaurant is the best choice for Arabic & Western cuisine , Promises a value lifestyle proposition of great variety and quality food at affordable prices ", " 226 Jalan Ampang, Kuala Lumpur, MY 50450"),
									new Restaurant ("Mamak Spot", "0123456789", "0123456789", "Mamak Station brings you the best in comfort food from the diverse street vendors of Malaysia. Our food is a celebration of flavors...layered from Chinese, Indian, and Malay roots.", "2 Jalan Robertson, G4 & G5, Idaman Robertson, Kuala Lumpur, MY 50150")};
		
		setDefaultItems(restaurants);
		selectRestaurant(restaurants);
		
		// try{
			// restaurants[0].getMenu().showItems();
			// restaurants[0].getMenu().removeItem(restaurants[0].getMenu().getMenuContents().get(0));
			// restaurants[0].getMenu().showItems();
		// }
		// catch(FileNotFoundException ex){
			// System.out.println("not found");
		// }
		
		
	}
	static void selectRestaurant(Restaurant[] restaurants){
			Scanner scan = new Scanner(System.in);
			do{
				clearScreen();
				System.out.println("\n\t\t\t   +----------------------------------------------------------------+");
				System.out.println("\t\t\t   |\t\t\t\t\t\t\t\t    |\n   \t\t\t   |     ____           _                              _       \t    |");
				System.out.println("\t\t\t   |\t|  _ \\ ___  ___| |_ __ _ _   _ _ __ __ _ _ __ | |_ ___ \t    |");
				System.out.println("\t\t\t   |\t| |_) / _ \\/ __| __/ _` | | | | '__/ _` | '_ \\| __/ __|\t    |");
				System.out.println("\t\t\t   |\t|  _ <  __/\\__ \\ || (_| | |_| | | | (_| | | | | |_\\__ \\\t    |");
				System.out.println("\t\t\t   |\t|_| \\_\\___||___/\\__\\__,_|\\__,_|_|  \\__,_|_| |_|\\__|___/\t    |");
				System.out.println("\t\t\t   |\t\t\t\t\t\t\t\t    |");
				for(int i = 0; i < restaurants.length; ++i){
					
					System.out.print("\t\t\t   |\t\t\t" + (i+1) + ") " 
										+ restaurants[i].getName() + "\t\t\t\t    |");
					System.out.println();
				}
				System.out.print("\t\t\t   |\t\t\t\t\t\t\t\t    |");
				System.out.println();
				System.out.print("\t\t\t\t\tSelect a restaurant by its number:");
				try{
					int choice = scan.nextInt();
					if(choice >=1 && choice <= 3){
						loginScreen(choice, restaurants);
						break;
					}
					else
						throw new InputMismatchException();
				}
				catch(InputMismatchException ex){
					System.out.println("Wrong input, please try again!");
					scan.nextLine();
					loadingWait();
				}
			}while(true);
	}
	static void clearScreen() {
		try{
			final String osName = System.getProperty("os.name");
			if (osName.toLowerCase().contains("windows"))
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			else
				new ProcessBuilder("clear").inheritIO().start().waitFor();
		}
		catch(IOException ex){
				System.out.println(ex.getMessage());
		}
		catch(InterruptedException ex){
				System.out.println(ex.getMessage());				
		}
	}
	
	static void loadingWait(){
		try
		{
			Thread.sleep(1000);
		}
		catch(InterruptedException ex)
		{
			Thread.currentThread().interrupt();
		}
	}
	
	static void setDefaultItems(Restaurant[] restaurants){
		try{
			if(restaurants[0].getMenu().getMenuContents().size() == 0){
				restaurants[0].getMenu().addItemToMenuDir(new Item("Chickenosaurus pizza", 20 ,"Roasted Chicken, Chicken Pepperoni and Mushroom Slices on our awesome Smoky Blended BBQ Sauce", "pastry"));
				restaurants[0].getMenu().addItemToMenuDir(new Item("Ultimate Hawaiian pizza",23 ,"Loads of delicious roasted chicken, shredded chicken juicy pineapples and fresh mushrooms on our brand new pizza.", "pastry"));
				restaurants[0].getMenu().addItemToMenuDir(new Item("Metasaurus pizza",27.0 ,"Generous portions of everyone's favorite beef pepperoni, ground beef and fresh mushrooms on our new blended smoky BBQ sauce","pastry"));
				restaurants[0].getMenu().addItemToMenuDir(new Item("Smoky pepperoni mushroom pizza", 30 ,"Beef Pepperoni and Mushroom Slices on our awesome Smoky Blended BBQ Sauce","pastry"));
				restaurants[0].getMenu().addItemToMenuDir(new Item("Simply cheese pizza", 24 ,"100% mozzarella cheese, Parmesan cheese & Oregano on our Signature Sauce.","pastry"));
				restaurants[0].getMenu().addItemToMenuDir(new Item("Flaming tuna pizza",25 ,"Delicious tuna chunks, fresh onions topped with red-hot chilies! Get ready for a spicy encounter.","pastry"));
				restaurants[0].getMenu().addItemToMenuDir(new Item("Blueberry Cheesecake",8 ,"A creamy cheesecake filled with a luscious, warm sauce.","Dessert"));
				restaurants[0].getMenu().addItemToMenuDir(new Item("Chocolate Lava Cake",9 ,"Discover a lusciously melted soft chocolate centre, overflowing with chocolatey goodness","Dessert"));
				restaurants[0].getMenu().addItemToMenuDir(new Item("7-Up",2 ,"soft drink","Drink"));
				restaurants[0].getMenu().addItemToMenuDir(new Item("Pepsi",2 ,"soft drink","Drink"));
				restaurants[0].getMenu().addItemToMenuDir(new Item("BBQ Baked Meatballs",8 ,"BBQ Baked Meatballs with BBQ sauce and bread","Sides"));
				restaurants[0].getMenu().addItemToMenuDir(new Item("Garlic Cheese Onion Rings",7 ,"Thick-sliced, breaded whole sweet onion turn crisp and crunchy. Just the perfect sides!","Sides"));
				restaurants[0].getMenu().copyItems();
			}
			if(restaurants[1].getMenu().getMenuContents().size() == 0){
				restaurants[1].getMenu().addItemToMenuDir(new Item("Hommous", 5,"pureed chickpeas with tahini sauce", "Appetizers"));
				restaurants[1].getMenu().addItemToMenuDir(new Item("Foul Mudamas", 5,"mshed boiled fava beans with garli and lemon juice", "Appetizers"));
				restaurants[1].getMenu().addItemToMenuDir(new Item("Labneh With Garlic", 5,"homemade yogurt mixed with mashed garlic", "Appetizers"));
				restaurants[1].getMenu().addItemToMenuDir(new Item("Lemon Crush Juice",3 ,"orange, lemon, sugar, and ice", "Juice"));
				restaurants[1].getMenu().addItemToMenuDir(new Item("Milk Cocktail",3 ,"milk, banana, honey, and ice.16oz", "Juice"));
				restaurants[1].getMenu().addItemToMenuDir(new Item("Carrot Smoothie",3 ,"carrot, banana, honey, and ice.", "Juice"));
				restaurants[1].getMenu().addItemToMenuDir(new Item("Shawarma",12 ,"beef and lamb marinated with our special spices", "Main Entrees"));
				restaurants[1].getMenu().addItemToMenuDir(new Item("Fried Chicken",11 ,"Chicken deep fried with cheese and chili sauce", "Main Entrees"));
				restaurants[1].getMenu().addItemToMenuDir(new Item("Chicken Kafta",15 ,"ground chicken with onions and parsley", "Main Entrees"));
				restaurants[1].getMenu().addItemToMenuDir(new Item("Seafood Fried Fish",18 ,"whole white boned fish fried with sesame sauce, served with fries or rice", "Main Entrees"));
				restaurants[1].getMenu().addItemToMenuDir(new Item("Grilled Chicken",19 ,"marinated and served with fries or rice and salad", "Main Entrees"));
				restaurants[1].getMenu().addItemToMenuDir(new Item("Lebanese Salad",8 ,"lettuce, tomatoes, parsley, radishs, and cucumber", "Salad"));
				restaurants[1].getMenu().addItemToMenuDir(new Item("Fattoush",9 ,"lebanese salad with fried pita bread", "Salad"));
				restaurants[1].getMenu().copyItems();
			}
			if(restaurants[2].getMenu().getMenuContents().size() == 0){
				restaurants[2].getMenu().addItemToMenuDir(new Item("Roti Canai",4 ,"crispy Indian flatbread w. curry dipping sauce", "Starters"));
				restaurants[2].getMenu().addItemToMenuDir(new Item("Salt & Pepper Chicken Wings",7 ,"crispy fried chicken wings", "Starters"));
				restaurants[2].getMenu().addItemToMenuDir(new Item("Curry Puff Pastry",6 ,"homemade pastry w. curry chicken, onions & potato", "Starters"));
				restaurants[2].getMenu().addItemToMenuDir(new Item("Bak Kut Teh",12 ,"intensely flavorful herb soup w. spareribs, mushroom & tofu", "Soup & Porridge"));
				restaurants[2].getMenu().addItemToMenuDir(new Item("Mustard Green Fish Fillet Soup",11 ,"mustard green w. fish fillet in a clear broth", "Soup & Porridge"));
				restaurants[2].getMenu().addItemToMenuDir(new Item("Westlake Beef Soup",10 ,"minced beef egg drop soup", "Soup & Porridge"));
				restaurants[2].getMenu().addItemToMenuDir(new Item("Seafood Pan Fried Noodles",14 ,"double pan fried egg noodles w. shrimp, squid, scallop & fish cake", "Noodles"));
				restaurants[2].getMenu().addItemToMenuDir(new Item("Hainanese Chicken Loh Mee",10 ,"egg noodles toss in dark soy sauce, w. hainanese chicken", "Noodles"));
				restaurants[2].getMenu().addItemToMenuDir(new Item("Fish Fillet Mee Fun",11 ,"popular KL fish head rice vermicelli noodles", "Noodles"));
				restaurants[2].getMenu().addItemToMenuDir(new Item("Nasi Lemak",10 ,"fragrant coconut rice w. sambal, anchovies & egg - choice of chicken or spareribs", "Rice"));
				restaurants[2].getMenu().addItemToMenuDir(new Item("Hainanese Chicken",8 ,"slowly simmered bone-in chicken w. fragrant rice, served w. ginger chili sauce", "Rice"));
				restaurants[2].getMenu().addItemToMenuDir(new Item("Fried Rice",9,"chicken wok fried rice", "Rice"));
				restaurants[2].getMenu().addItemToMenuDir(new Item("Kari Ayam",13,"classic Malaysian chicken curry w. potatoes", "Entrees"));
				restaurants[2].getMenu().addItemToMenuDir(new Item("Fresh Garlic Romaine Lettuce",11,"wok toss fresh garlic romaine greens", "Entrees"));
				restaurants[2].getMenu().addItemToMenuDir(new Item("Eggplant Salted Fish & Chicken",15,"braised eggplant w. salted fish & tender chicken", "Entrees"));
				restaurants[2].getMenu().copyItems();
			}
		}catch(IOException ex){
			System.out.println(ex.getMessage());
		}
	}
	
	static void loginScreen(int restChoice, Restaurant[] loginRestaurant){
		Scanner scan = new Scanner(System.in);
		clearScreen();
		System.out.println();
		System.out.println();

		System.out.println("\t _                _       ");
		System.out.println("\t| |    ___   __ _(_)_ __  ");
		System.out.println("\t| |   / _ \\ / _` | | '_ \\ ");
		System.out.println("\t| |__| (_) | (_| | | | | |");
		System.out.println("\t|_____\\___/ \\__, |_|_| |_|");
		System.out.println("\t            |___/         ");
		System.out.println("\n\t Enter your username and password");
		System.out.print("\t Username: ");
		String username = scan.nextLine();
		System.out.print("\t Password: ");
		String password = scan.nextLine();
		
		if(loginRestaurant[restChoice -1].validateLogin(username, password)){
			clearScreen();
			System.out.println("\t\t\t _                _         ____                               __       _ _ ");
		System.out.println("\t\t\t| |    ___   __ _(_)_ __   / ___| _   _  ___ ___ ___  ___ ___ / _|_   _| | |");
		System.out.println("\t\t\t| |   / _ \\ / _` | | '_ \\  \\___ \\| | | |/ __/ __/ _ \\/ __/ __| |_| | | | | |");
		System.out.println("\t\t\t| |__| (_) | (_| | | | | |  ___) | |_| | (_| (_|  __/\\__ \\__ \\  _| |_| | |_|");
		System.out.println("\t\t\t|_____\\___/ \\__, |_|_| |_| |____/ \\__,_|\\___\\___\\___||___/___/_|  \\__,_|_(_)");
		System.out.println("\t\t\t            |___/                                                           ");
		loadingWait();
			resturantDashboard(loginRestaurant[restChoice -1]);
		}
		else{
			System.out.print("Username or Password is incorrect, (type back to select restaurant or else to try again): ");
			String editChoice = scan.nextLine();
			if(editChoice.equals("back"))
				selectRestaurant(loginRestaurant);
			else
				loginScreen(restChoice, loginRestaurant);
		}
		
		
	}
	static void resturantDashboard(Restaurant restaurant){
		Scanner scan = new Scanner(System.in);
		do{
			clearScreen();
			System.out.println("\n\t\t\t\t   +-----------------------------------------------------+");
			System.out.println("\t\t\t\t   |\t\t\t\t\t\t\t |");
			System.out.println("\t\t\t\t   |  \t\t " +"   "+ restaurant.getName() + " Dashboard \t\t |");
			System.out.println("\t\t\t\t   |\t\t\t\t\t\t\t |");
			System.out.println("\t\t\t\t   +-----------------------------------------------------+");
			
			System.out.println("\t\t\t\t   |      1) Show and update active orders \t\t |");
			System.out.println("\t\t\t\t   |      2) Show order history \t\t\t |");
			System.out.println("\t\t\t\t   |      3) Show menu items \t\t\t\t |");
			System.out.println("\t\t\t\t   |      4) Add item to menu \t\t\t\t |");
			System.out.println("\t\t\t\t   |      5) Return to user select \t\t\t |");
			System.out.print("\n\t\t\t\t    >> Enter number of the needed function: ");
			try{
				int dashCohice = scan.nextInt();
				if(dashCohice == 1){
					// for(int i = 0; i < restaurant.getPastOrders().size(); ++i){
						showAndUpdateCurrentOrders(restaurant);
						resturantDashboard(restaurant);
						break;
					// }
				}
				else if(dashCohice == 2){
					clearScreen();
					for(int i = 0; i < restaurant.getPastOrders().size(); ++i){
						System.out.println(restaurant.getPastOrders().get(i));
					}
					System.out.println("Press Enter to go back");
					scan.nextLine();
					scan.nextLine();
				}
				else if(dashCohice == 3){
					restaurant.getMenu().showItems();
					resturantDashboard(restaurant);
					break;
				}
				else if(dashCohice == 4){
					try{
						restaurant.getMenu().addNewItem();
						resturantDashboard(restaurant);
						break;
					}
					catch(IOException ex){
						System.out.println("cant find directory of the menu");
					}
				}
				else if(dashCohice == 5){
					break;
				}
				else{
					throw new InputMismatchException();
				}
			}catch(InputMismatchException ex ){
				System.out.println("Invalid input, please try again!");
				scan.nextLine();
				loadingWait();
			}
			catch(IndexOutOfBoundsException ex){
				System.out.println("Invalid input, please try again!");
				loadingWait();
			}
		}while(true);
	}
	 static void showAndUpdateCurrentOrders(Restaurant restaurant){ // show the orders and update the status to ready
		Scanner scan = new Scanner(System.in);
		if(restaurant.getCurrentOrders().size() == 0){
			System.out.print("you have no orders in the meanTime");
			// return;
			loadingWait();
		}
		else{
			clearScreen(); // clear the screen
			System.out.println();
			System.out.println();
			for(int i = 0; i < restaurant.getCurrentOrders().size(); ++i){
				// if(!restaurant.getCurrentOrder().get(i).getOrderStatus().equals("Collected") && !restaurant.getPastOrders().get(i).getOrderStatus().equals("Delivered"))
					System.out.println("\t\t " + (i+1) +"# " + restaurant.getCurrentOrders().get(i));
			}
			System.out.print("\n\n\t\t" + "choose number of order to show or update: ");
			int choosenOrder = (scan.nextInt() - 1);
			do{
				clearScreen();
				System.out.println("+----------------------------------------------------+");
				System.out.println("|\tOrder ID: " + restaurant.getCurrentOrders().get(choosenOrder).getID());
				System.out.println("|\tOrder Type: " + restaurant.getCurrentOrders().get(choosenOrder).getOrderType());
				System.out.println("|\tCustomer Username: " + restaurant.getCurrentOrders().get(choosenOrder).getCusUsername());
				System.out.println("|\tTime Requested: " + restaurant.getCurrentOrders().get(choosenOrder).getTimeRequested());
				System.out.println("|\tOrder Status: " + restaurant.getCurrentOrders().get(choosenOrder).getOrderStatus());
				System.out.println("|\tOrder Contents: " );
				for(int i = 0; i < restaurant.getCurrentOrders().get(choosenOrder).getOrderContents().size(); ++i){
					System.out.println("|\t\tItem #" + (i+1) + " Name: " + restaurant.getCurrentOrders().get(choosenOrder).getOrderContents().get(i).getItemName());
					System.out.println("|\t\tItem ID: " + restaurant.getCurrentOrders().get(choosenOrder).getOrderContents().get(i).getItemID());
					System.out.println("|\t\tItem Price: " + restaurant.getCurrentOrders().get(choosenOrder).getOrderContents().get(i).getItemPrice());
					System.out.println("|");
				}
				System.out.println("|\tTotal Price: RM" + restaurant.getCurrentOrders().get(choosenOrder).getOrderPrice());
				System.out.println("|\n|\t1) Update Order Status ");
				System.out.println("|\t2) Back to Restaurant Dashboard ");
				System.out.print("|\t>>");
				int choosenAction = scan.nextInt();
				if(choosenAction == 1){
					if(restaurant.getCurrentOrders().get(choosenOrder).getOrderType().equals("Collection")){
						System.out.println("|\n|\n|\t1)Change to Ready ");
						System.out.println("|\t2)Change to Collected ");
						System.out.print("|\t>>");
						int newStatus = scan.nextInt();
						if(newStatus == 1){
							try{
								restaurant.getCurrentOrders().get(choosenOrder).replaceOrderStatus(restaurant.getCurrentOrders().get(choosenOrder), "Ready");
								// customer.getCurrentOrder().setOrderStatus("Ready");
							}
							catch(IOException ex){
								System.out.println(ex.getMessage());
							}
						}
						else if(newStatus == 2){
							try{
								restaurant.getCurrentOrders().get(choosenOrder).replaceOrderStatus(restaurant.getCurrentOrders().get(choosenOrder), "Collected");
								// customer.getCurrentOrder().setOrderStatus("Ready");
							}
							catch(IOException ex){
								System.out.println(ex.getMessage());
							}
						}
						else
							throw new InputMismatchException();
					}
					else{
						System.out.println("|\n|\n|\t1)Change to Ready ");
						System.out.println("|\n|\n|\t2)Change to Delivering ");
						int newStatus = scan.nextInt();
						if(newStatus == 1){
							try{
								restaurant.getCurrentOrders().get(choosenOrder).replaceOrderStatus(restaurant.getCurrentOrders().get(choosenOrder), "Ready");
								// customer.getCurrentOrder().setOrderStatus("Ready");
							}
							catch(IOException ex){
								System.out.println(ex.getMessage());
							}
						}
						else if(newStatus == 2){
							try{
								restaurant.getCurrentOrders().get(choosenOrder).replaceOrderStatus(restaurant.getCurrentOrders().get(choosenOrder), "Delivering");
								// customer.getCurrentOrder().setOrderStatus("Ready");
							}
							catch(IOException ex){
								System.out.println(ex.getMessage());
							}
						}
						else
							throw new InputMismatchException();
					}
				}
				else if(choosenAction == 2){
					break;
				}
				else
					throw new InputMismatchException();
			}while(true);
		}
	}
		
}
