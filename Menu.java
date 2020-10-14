import java.util.*;
import java.io.*;
public class Menu{
	private ArrayList<Item> menuContents = new ArrayList<>();
	private File menuDir;
	public Menu(){
		
	}
	
	public Menu(String restName, File restDir) throws IOException{		
		menuDir = new File(restDir + "/Menu");  // makes a directory for the menu inside each restaurant
		menuDir.mkdir();
		try{
			copyItems(); // copy the items from the files to the menuContents 
		}
		catch(FileNotFoundException ex){
			System.out.println("you have no items in " + restName +  " menu, you need to add some");
		}
	}
	
	public ArrayList<Item> getMenuContents(){
		return menuContents;
	}
	
	
	public void addNewItem() throws IOException{ // makes a new file for the items (used to update and input new items)
		RestaurantUI.clearScreen();
		boolean existFlag = false;
		Scanner scan = new Scanner(System.in);
		Item newItem = new Item();
		
		System.out.print("Enter item Name: ");
		newItem.setItemName(scan.nextLine());
		for(int i = 0; i < menuContents.size(); ++i){
			if(menuContents.get(i).getItemName().equals(newItem.getItemName()))
				existFlag = true;
		}
		
		if(existFlag){
			System.out.println("Item already exist");
			RestaurantUI.loadingWait();
		}
		else{
			System.out.print("Enter item Price: ");
			newItem.setItemPrice(scan.nextDouble());
			
			scan.nextLine(); // consume rest of that line including newline (needed after nextDouble Int ...etc)
			
			System.out.print("Enter item Description: ");
			newItem.setItemDescription(scan.nextLine());
			
			System.out.print("Enter item Type: ");
			newItem.setItemType(scan.nextLine());
			addItemToMenuDir(newItem);
			System.out.print("Item successfully added");
			RestaurantUI.loadingWait();
		}
	}

	public void addItemToMenuDir(Item newItem) throws IOException{ // makes a new file for the items (used to update and input new items)
		FileWriter NamesFileOutput = new FileWriter(menuDir + "/ItemsNames.txt", true); // makes a file to save the items names to read them later, the true value is to be able to write more without clearing the file every time
		PrintWriter outputNames =  new PrintWriter(NamesFileOutput); // PrintWriter to write to Itemsnames.txt
		
		
		String itemName = newItem.getItemName();
		// itemName = itemName.replace(' ', '_'); // replace the spaces in the item name with _ to make reading the files easier
		
		outputNames.println(itemName);
		
		
		File itemFile = new File(menuDir + "/"  + itemName + ".txt"); // makes a new file with the new item name and its information
		PrintWriter outputItem =  new PrintWriter(itemFile);
		outputItem.println(newItem.writeToFile());
		outputItem.close();
		
		outputNames.close();
	}
	public void removeItem(Item removedItem) throws FileNotFoundException{ // remove the item from the ItemsName.txt and menuContents, it won't be read anymore by copyItems method
		File NamesFileInput = new File(menuDir + "/ItemsNames.txt" );
		File temp = new File(menuDir + "/temp.txt"); // make a temp file to copy all the other names to it.
		PrintWriter moveToTemp = new PrintWriter(temp);
		Scanner originalNames = new Scanner(NamesFileInput);
		
		while(originalNames.hasNext()){
			String nextName = originalNames.nextLine();
			// nextName = nextName.replace('_', ' ');
			if(!nextName.equals(removedItem.getItemName())){
				// nextName = nextName.replace(' ', '_');
				moveToTemp.println(nextName);
			}
		}
			
		moveToTemp.close();
		originalNames.close();
		File tempName = NamesFileInput;
		System.out.println(NamesFileInput.delete());
		System.out.println(temp.renameTo(tempName)); // give the temp file the name "ItemsName" after deleting the old one
		menuContents.remove(removedItem);
	}
	
	
	public void copyItems() throws FileNotFoundException{ // copy the items with the names from ItemsNames.txt to the actual menuContents
		// Item.resetLastID();
		menuContents.clear(); // clear the menuContents to avoid redundant items
		File NamesFileInput = new File(menuDir + "/ItemsNames.txt" );
		Scanner inputNames = new Scanner(NamesFileInput);
		while(inputNames.hasNext()){
			File itemFile = new File(menuDir + "/" + inputNames.nextLine() + ".txt");
			Scanner itemNameScanner = new Scanner(itemFile);
			menuContents.add(new Item(itemNameScanner.nextLine(), //using the reading constructor (without incrementing the lastID)
								  Double.parseDouble(itemNameScanner.nextLine()), 
								  itemNameScanner.nextLine(), 
								  itemNameScanner.nextLine(),
								  itemNameScanner.nextBoolean(),
								  itemNameScanner.nextInt()));
			itemNameScanner.close();
		}
		inputNames.close();
	}
	
	public void showItems(){
		
		try{
			copyItems(); // copy the items from the files to the menuContents 
		}
		catch(FileNotFoundException ex){
			System.out.println("you have no items in the menu, you need to add some");
		}
		Scanner scan = new Scanner(System.in);
		do{
			RestaurantUI.clearScreen();
			System.out.println("\n\n\t\t\t\t+------------------------------------------------+");
			System.out.println("\t\t\t\t| \t\t\t\t\t\t |");
			System.out.println("\t\t\t\t|\t  Select item to view or update: \t |");
			System.out.println("\t\t\t\t| \t\t\t\t\t\t |");
			int numberOfItems;
			for(numberOfItems  = 0; numberOfItems < menuContents.size(); ++numberOfItems){
				System.out.println("\t\t\t\t| " + (numberOfItems + 1) + ") " + menuContents.get(numberOfItems).getItemName() );
			}
			System.out.println("\t\t\t\t| " + (numberOfItems + 1) + ") " + "Back to main menu");
			System.out.print("\n\n\t\t\t\tEnter number of item: ");
			try{
				int choosenItem = (scan.nextInt() - 1);
				if(choosenItem == numberOfItems)
					return;
				else if(choosenItem > numberOfItems || choosenItem < 0){
					throw new InputMismatchException();
				}
				else{
					RestaurantUI.clearScreen();
					System.out.println("\n\n\nName: " + menuContents.get(choosenItem).getItemName()); 
					System.out.println("ID: " + menuContents.get(choosenItem).getItemID());
					System.out.println("Price: RM" + menuContents.get(choosenItem).getItemPrice());
					System.out.println("Type: " + menuContents.get(choosenItem).getItemType());
					System.out.println("Description: " + menuContents.get(choosenItem).getItemDescription());
					System.out.println("Availability:(true = available, false = Unavailable ) " + menuContents.get(choosenItem).getAvailability());
					System.out.println("\n\n1) Update item ");
					System.out.println("2) Remove item ");
					System.out.println("3) Back to item menu");
					System.out.print("Select action>> ");
					int choosenAction = scan.nextInt();
					if(choosenAction == 1){
						updateItem(menuContents.get(choosenItem));
						break;
					}
					else if(choosenAction == 2){
						try{
							removeItem(menuContents.get(choosenItem));
							showItems();
							break;
						}
						catch(FileNotFoundException ex){
							System.out.println("itemsname file not found ");
						}
					}
					else if(choosenAction == 3){
						showItems();
						break;
					}
					else{
						throw new InputMismatchException();
					}
				}
			}catch(InputMismatchException ex){
				System.out.println("Invalid Input, please try again!");
				scan.nextLine();
				RestaurantUI.loadingWait();
			}
		}while(true);
	}
	
	public void updateItem(Item updatedItem){
		Scanner scan = new Scanner(System.in);
		int choosenAttribute = 0;
		do{
			RestaurantUI.clearScreen();
			System.out.println("\n\n\t\t\t\t _   _           _       _         ___ _                 ");
			System.out.println("\t\t\t\t| | | |_ __   __| | __ _| |_ ___  |_ _| |_ ___ _ __ ___  ");
			System.out.println("\t\t\t\t| | | | '_ \\ / _` |/ _` | __/ _ \\  | || __/ _ \\ '_ ` _ \\ ");
			System.out.println("\t\t\t\t| |_| | |_) | (_| | (_| | ||  __/  | || ||  __/ | | | | |");
			System.out.println("\t\t\t\t \\___/| .__/ \\__,_|\\__,_|\\__\\___| |___|\\__\\___|_| |_| |_|");
			System.out.println("\t\t\t\t      |_|                                                ");
			System.out.println("\n\t\t\t\t1)Description");
			System.out.println("\t\t\t\t2)Price");
			System.out.println("\t\t\t\t3)Type");
			System.out.println("\t\t\t\t4)Availability");
			System.out.println("\t\t\t\t5)confirm changes and go back");
			System.out.print("\t\t\t\tSelect number of attribute to change, then press 5 to confirm: ");
			choosenAttribute = scan.nextInt(); 
			scan.nextLine(); 
			
			if(choosenAttribute == 1){
				System.out.print("\t\t\t\tEnter new description: ");
				updatedItem.setItemDescription(scan.nextLine());
			}
			else if(choosenAttribute == 2){
				System.out.print("\t\t\t\tEnter new price(e.g. 15): ");
				updatedItem.setItemPrice(scan.nextDouble());
			}
			else if(choosenAttribute == 3){
				System.out.print("\t\t\t\tEnter new Type: ");
				updatedItem.setItemType(scan.nextLine());
			}
			else if(choosenAttribute == 4){
				System.out.print("\t\t\t\tEnter new Availability(true,false): ");
				updatedItem.setItemAvailability(scan.nextBoolean());
			}
			else if(choosenAttribute == 5){
				try{
					File itemFile = new File(menuDir + "/"  + updatedItem.getItemName() + ".txt"); // makes a new file with the new item name and its information
					PrintWriter outputItem =  new PrintWriter(itemFile);
					outputItem.println(updatedItem.writeToFile());
					outputItem.close();
				}
				catch(IOException ex){
					System.out.println("directorty or file not found to replace item");
				}
				
				showItems();
			}
			else
				throw new InputMismatchException();
			
			
		}while(choosenAttribute != 5);
	}
	
}

