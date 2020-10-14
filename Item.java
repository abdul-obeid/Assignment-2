import java.io.*;
import java.util.*;
public class Item implements Comparable<Item>{
	private int itemID;
	private static int lastID = 0;
	private String itemName;
	private double itemPrice;
	private String itemDescription;
	private String itemType;
	private boolean availability = true;
	public Item(){
		try{
			Scanner readLastID = new Scanner(new File("LastID.txt"));
			lastID = readLastID.nextInt();
			itemID = ++lastID;	
			sendLastID(lastID);
		}
		catch(FileNotFoundException ex){
			System.out.println(ex.getMessage());
		}
	}
	
	public Item(String itemName, 
				double itemPrice, 
				String itemDescription, 
				String itemType,
				boolean availability
				){
		// try{
			// FileWriter LastIDFile = new FileWriter("LastID.txt"); 
			// PrintWriter outputNames =  new PrintWriter(LastIDFile);			
			this.itemName = itemName;
			this.itemPrice = itemPrice;
			this.itemDescription = itemDescription;
			this.itemType = itemType;
			this.availability = availability;
			itemID = ++lastID;
			// outputNames.println(lastID);
			// outputNames.close();
			sendLastID(lastID);
		// }
		// catch(IOException ex){
			// System.out.println(ex.getMessage());
		// }
	}
	
	public Item(String itemName, //
				double itemPrice, 
				String itemDescription, 
				String itemType){
		// try{
			// FileWriter LastIDFile = new FileWriter("LastID.txt"); // makes a file to save the items names to read them later, the true value is to be able to write more without clearing the file every time
			// PrintWriter outputNames =  new PrintWriter(LastIDFile);			
			this.itemName = itemName;
			this.itemPrice = itemPrice;
			this.itemDescription = itemDescription;
			this.itemType = itemType;
			itemID = ++lastID;
			sendLastID(lastID);
			// outputNames.println(lastID);
			// outputNames.close();
		// }
		// catch(IOException ex){
			// System.out.println(ex.getMessage());
		// }
	}
	public Item(String itemName, 
				double itemPrice, 
				String itemDescription, 
				String itemType,
				boolean availability,
				int itemID
				){
					
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.itemDescription = itemDescription;
		this.itemType = itemType;
		this.availability = availability;
		this.itemID = itemID;
	}
	
	private void sendLastID(int lastID){
		try{
			FileWriter LastIDFile = new FileWriter("LastID.txt"); 
			PrintWriter outputNames =  new PrintWriter(LastIDFile);	
			outputNames.println(lastID);
			outputNames.close();
		}
		catch(IOException ex){
			System.out.println(ex.getMessage());
		}	
	}
	
	public static int getLastID(){
		return lastID;
	}
	public static void resetLastID( ){
		lastID = 0;
	}
	
	public void setItemName(String itemName){
		this.itemName = itemName;
	}
	
	public void setItemPrice(double itemPrice){
		this.itemPrice = itemPrice;
	}
	
	public void setItemDescription(String itemDescription){
		this.itemDescription = itemDescription;
	}
	
	public void setItemAvailability(boolean availability){
		this.availability = availability;
	}
	
	public void setItemType(String itemType){
		this.itemType = itemType;
	}
	
	public int getItemID(){
		return itemID;
	}
	
	public String getItemName(){
		return itemName;
	}
	
	public double getItemPrice(){
		return itemPrice;
	}
	
	public boolean getAvailability(){
		return availability;
	}
	
	public String getItemType(){
		return itemType;
	}
	
	public String getItemDescription(){
		return itemDescription;
	}
	
	@Override
	public String toString(){
		return ("Item: " + itemName + ".\n Price: " + itemPrice + " RM. \nDescription: " + itemDescription + ". \nType: " + itemType +". ");
	}
	
	public String writeToFile(){ // writes new 
		return (itemName + "\n" + itemPrice + "\n" + itemDescription + "\n" + itemType + "\n" + availability + "\n" + itemID);
	}
	
	public String writeToOrder(){
		return (itemName + "\n" + itemPrice + "\n" + itemDescription + "\n" + itemType );
	}
	
	@Override
	public int compareTo(Item item){
		if(getItemPrice() > item.getItemPrice())
			return 1; // > greater than
		else if(getItemPrice()  == item.getItemPrice())
			return 0; // ==
		else
			return -1; // <
	}
}