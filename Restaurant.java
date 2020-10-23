import java.util.*;
import java.io.*;

public class Restaurant extends User implements Comparable<Restaurant>{
	private String name; // Restaurant name
	private Menu menu; // Restaurant's Menu
	private String description; // Restaurant's description 
	private ArrayList<Order> currentOrders = new ArrayList<>(); // Restaurant's active orders (not collected yet)
	private ArrayList<Order> pastOrders = new ArrayList<>(); // Restaurant's past orders (collected )
	private String address; // Restaurant address
	private File restDir; // Restaurant Directory (declare it here to use it in different methods), NOTE TO PROGRAMMER -> IF SOMETHING WENT WRONG, CHANGE BACK TO DEFAULT
	private int orderCount;
	// public Restaurant(){ //default constructor 
	// }
	
	public Restaurant(String name,String username, String password, String description, String address) {
		super(username, password); //get username and password from User class
		this.name = name;
		this.description = description;
		this.address = address;
		try{
			sendRestToFile(); // used to make a directory for the restaurant
			menu = new Menu(name, restDir); // send Restaurant name and Directory file to make the menu
			File f = new File("Restaurant/" + name + "/Order/");
			if(f.exists()){ // if there is at least one order has been made for the restaurant, set the order count to the number of files inside the order directory - 1 (names of orders file)
				File[] files = f.listFiles();
				this.orderCount = files.length - 1;
			}
			else
				this.orderCount = 0;
		}
		catch(IOException ex){
			System.out.println("File or directory not found " + ex.getMessage());
		}
	}		
	
	private void sendRestToFile() throws FileNotFoundException{ // used to make a directory for the restaurant
		restDir = new File("Restaurant/" + name);
		restDir.mkdir(); // make the directory using the restaurant name
		File restaurantInfo = new File(restDir + "/basicInfo.txt"); // make a text file to store the restaurant information
		PrintWriter outputRestInfo =  new PrintWriter(restaurantInfo); // writes into the basicInfo.txt
		outputRestInfo.println(name); // move the info to the basicInfo.txt file
		outputRestInfo.println(getUsername());
		outputRestInfo.println(getPassword());
		outputRestInfo.println(description);
		outputRestInfo.println(address);
		outputRestInfo.close(); // close basicInfo.txt file
	}
	public void setName(String name){
		this.name = name;
	}
	
	public void setMenue(Menu menu){
		this.menu = menu;
	
	}
	
	
	public void setAddress(String adress){
		this.address= address;
	}
	
	public String getName(){
		return name;
	}
	
	
	public Menu getMenu(){
		return menu;
	}
	
	public String getAddress(){
		return address;
	}
	public String getDescription(){
        return this.description;
    }
	public void setDescription(String description){
        this.description = description;
    }
	public ArrayList<Order> getCurrentOrders(){ // get the active orders which not collected yet
		try{
			readOrderHistoryFromFiles(); // reads the order files and sets them as history  
		}
		catch(IOException ex){
			System.out.println("File not found: " + ex.getMessage());
		}
		return currentOrders;
	}
	
	public void removeFromCurrentOrders(Order removedOrder){ // remove the order from current orders after being collected
		currentOrders.remove(removedOrder);
	}
	public void addToCurrentOrders(Order newOrder){ // make new order in the restaurant
		currentOrders.add(newOrder);
	}
	
	public ArrayList<Order> getPastOrders(){ // get order history for the restaurant
		try{
			readOrderHistoryFromFiles(); // reads the order files and sets them as history  
		}
		catch(IOException ex){
			System.out.println("File not found: " + ex.getMessage());
		}
		return pastOrders;
	}
	
	private void readOrderHistoryFromFiles() throws IOException{
		pastOrders.clear(); // clear the menuContents to avoid redundant items
		currentOrders.clear(); // clear the menuContents to avoid redundant items
		File pastOrdersFileInput = new File(restDir + "/Order/names.txt" );
		Scanner inputOrders = new Scanner(pastOrdersFileInput);
		while(inputOrders.hasNext()){
			File orderFile = new File(restDir + "/Order/" + inputOrders.nextLine() + ".txt");
			// Scanner OrderNameScanner = new Scanner(orderFile);
			pastOrders.add(new Order(orderFile));
			if(!pastOrders.get(pastOrders.size()-1).getOrderStatus().equals("Collected") && !pastOrders.get(pastOrders.size()-1).getOrderStatus().equals("Delivered"))
				currentOrders.add(pastOrders.get(pastOrders.size()-1));
		}
		inputOrders.close();
	}
	public String toString(){
		return ("Restaurant Name: " + name + "\n, Address: " + address + ", About us: " + description);
	}
	
	public  boolean validateLogin(String userAttempt, String passwordAttempt){ // return true if the username and password matches
		if(userAttempt.equals(getUsername()) && passwordAttempt.equals(getPassword()))
			return true;
		else
			return false;
	}
	public int getOrderCount(){
		return orderCount;
	}
	public void setOrderCount(int orderCount){
		this.orderCount = orderCount;
	}
	
	@Override
	public int compareTo(Restaurant r){ // comparable method to sort restaurants by number of orders they have
		return getOrderCount() - r.getOrderCount();
	}

	
}