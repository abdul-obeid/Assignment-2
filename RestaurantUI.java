import java.io.*;
import java.util.*;

public class RestaurantUI{
		static MyQueue<Rider> riderQueue = new MyQueue<>(); // temp
		// try{
			// static MyQueue<Rider> queue = Admin.getRiderQueue();
		// }
		// catch(IOException ex){
			// System.out.println(ex.getMessage());
		// }
		
	public static void  main(String[] args){
		File allRestDir = new File("Restaurant");
		allRestDir.mkdir();
		Restaurant[] restaurants = {new Restaurant ("Pizza Palace","0123456789", "0123456789", "Come home to true Italian Pizza at Pizza Palace, we offer a wide range of home-made Italian Pizzas alongside a menu complete with classic and rustic Italian dishes and a variety of cocktails.", "29 Jalan Riong, Bangsar, Kuala Lumpur, MY 59100"),
									new Restaurant ("Mishaltit", "0123456789","0123456789", "Mishaltit restaurant is the best choice for Arabic & Western cuisine , Promises a value lifestyle proposition of great variety and quality food at affordable prices ", " 226 Jalan Ampang, Kuala Lumpur, MY 50450"),
									new Restaurant ("Mamak Spot", "0123456789", "0123456789", "Mamak Station brings you the best in comfort food from the diverse street vendors of Malaysia. Our food is a celebration of flavors...layered from Chinese, Indian, and Malay roots.", "2 Jalan Robertson, G4 & G5, Idaman Robertson, Kuala Lumpur, MY 50150")};
		System.out.println(restaurants[0].getOrderCount());
		setDefaultItems(restaurants);
		// riderQueue.add(new Rider("saber01","sub009","Saber","0113724413"));
		// riderQueue.add(new Rider("julio32","jul009","Julio","0114578665"));
		// selectRestaurant(restaurants);
		
		
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
				restaurants[0].getMenu().copyItems();
			}
			if(restaurants[1].getMenu().getMenuContents().size() == 0){
				restaurants[1].getMenu().addItemToMenuDir(new Item("Hommous", 5,"pureed chickpeas with tahini sauce", "Appetizers"));
				restaurants[1].getMenu().addItemToMenuDir(new Item("Foul Mudamas", 5,"mshed boiled fava beans with garli and lemon juice", "Appetizers"));
				restaurants[1].getMenu().addItemToMenuDir(new Item("Labneh With Garlic", 5,"homemade yogurt mixed with mashed garlic", "Appetizers"));
				restaurants[1].getMenu().addItemToMenuDir(new Item("Lemon Crush Juice",3 ,"orange, lemon, sugar, and ice", "Juice"));
				restaurants[1].getMenu().copyItems();
			}
			if(restaurants[2].getMenu().getMenuContents().size() == 0){
				restaurants[2].getMenu().addItemToMenuDir(new Item("Roti Canai",4 ,"crispy Indian flatbread w. curry dipping sauce", "Starters"));
				restaurants[2].getMenu().addItemToMenuDir(new Item("Salt & Pepper Chicken Wings",7 ,"crispy fried chicken wings", "Starters"));
				restaurants[2].getMenu().addItemToMenuDir(new Item("Curry Puff Pastry",6 ,"homemade pastry w. curry chicken, onions & potato", "Starters"));
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
				System.out.println("|\n|\t1) Back to Restaurant Dashboard ");
				if(!restaurant.getCurrentOrders().get(choosenOrder).getOrderStatus().equals("Delivering") && !(restaurant.getCurrentOrders().get(choosenOrder).getOrderStatus().equals("Ready") && restaurant.getCurrentOrders().get(choosenOrder).getOrderType().equals("Delivery")))
					System.out.println("|\t2) Update Order Status ");
				System.out.print("|\t>>");
				int choosenAction = scan.nextInt();
				if(choosenAction == 2){
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
								break;
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
						System.out.println("|\n|\n|\t1)Change to Ready (if there is a dirver available, it will change to Delivering right away.)");
						int newStatus = scan.nextInt();
						if(newStatus == 1){
							try{
								MyQueue<Rider> riderQueue = Admin.getRiderQueue();
								if(!riderQueue.isEmpty()){
									restaurant.getCurrentOrders().get(choosenOrder).replaceOrderStatus(restaurant.getCurrentOrders().get(choosenOrder), "Delivering");
									riderQueue.poll().setCurrentOrderLabel(restaurant.getCurrentOrders().get(choosenOrder).getCusUsername() + "_" + restaurant.getCurrentOrders().get(choosenOrder).getID());
									Admin.setRiderQueue(riderQueue);
								}
								else{
									restaurant.getCurrentOrders().get(choosenOrder).replaceOrderStatus(restaurant.getCurrentOrders().get(choosenOrder), "Ready");
									MyQueue<Order> orderQueue = Admin.getOrderQueue();
									orderQueue.add(restaurant.getCurrentOrders().get(choosenOrder));
									Admin.setOrderQueue(orderQueue);
								}
							}
							catch(IOException ex){
								System.out.println(ex.getMessage());
							}
						}
						else
							throw new InputMismatchException();
					}
				}
				else if(choosenAction == 1){
					break;
				}
				else
					throw new InputMismatchException();
			}while(true);
		}
	}
		
}
