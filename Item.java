import java.io.*;
import java.util.*;
public class Item {
	private int itemID; 
	private static int lastID = 0; // last if has been given to an item, used to set the next itemID to ++lastID
	private String itemName;
	private double itemPrice;
	private String itemDescription;
	private String itemType;
	private boolean availability = true;
	
	
	// different constructors for different usage, some will be used to read from files and won't increment the lastID, others used to create new items
	public Item(){ 
		try{
			Scanner readLastID = new Scanner(new File("LastID.txt"));
			lastID = readLastID.nextInt();
			itemID = ++lastID;	
			sendLastID(lastID); // set the lastID file to the new one.
		}
		catch(FileNotFoundException ex){
			System.out.println(ex.getMessage());
		}
	}
	
	public Item(String itemName, 
				double itemPrice, 
				String itemDescription, 
				String itemType){
			this.itemName = itemName;
			this.itemPrice = itemPrice;
			this.itemDescription = itemDescription;
			this.itemType = itemType;
			itemID = ++lastID;
			sendLastID(lastID);
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
	
	
	public Item(String itemName, 
				double itemPrice, 
				String itemDescription, 
				String itemType,
				int itemID
				){
					
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.itemDescription = itemDescription;
		this.itemType = itemType;
		this.itemID = itemID;
	}
	
	private void sendLastID(int lastID){ // set the lastID file to the new one.
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
	public String toString(){ // String representation of the ite, shows some of its attributes
		return ("Item: " + itemName + ".\n Price: " + itemPrice + " RM. \nDescription: " + itemDescription + ". \nType: " + itemType +". ");
	}
	
	public String writeToFile(){ // used to write the needed attributes of item to menu directory 
		return (itemName + "\n" + itemPrice + "\n" + itemDescription + "\n" + itemType + "\n" + availability + "\n" + itemID);
	}
	
	public String writeToOrder(){ // used to write the needed attributes of item to order directory
		return (itemName + "\n" + itemPrice + "\n" + itemDescription + "\n" + itemType + "\n" + itemID ); // 14/10 change log
	}
}