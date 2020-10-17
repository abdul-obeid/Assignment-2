import java.util.*;
import java.io.*;

public class Rider extends User{
	private String name;
	private String phoneNumber;
	private Order currentOrder;
	private String currentOrderLabel;
	ArrayList<Order> pastOrders = new ArrayList<>();
	private int queuePos;
	private int deliveryCount;
	private File ridDir;
	// public Rider(){}
	
	
	public Rider(String username, String password, String name, String phoneNumber){
		super(username, password);
		this.name = name;
		this.phoneNumber = phoneNumber;
		try{
			sendRiderToFile(); // used to make a directory for the rider
		}
		catch(IOException ex){
			System.out.println("File or directory not found " + ex.getMessage());
		}
	}
	
	public Rider(String username) throws IOException {   //Constructor used for login
        super(null, null);
        Scanner ridInfo = new Scanner(new File("Rider/"+ username + "/basicInfo.txt"));  // Reading rider information from basicInfo.txt
        this.setUsername(ridInfo.nextLine());
        this.setPassword(ridInfo.nextLine());
		this.name = ridInfo.nextLine();
        this.phoneNumber = ridInfo.nextLine();
        this.queuePos = Integer.parseInt(ridInfo.nextLine());
        this.deliveryCount = Integer.parseInt(ridInfo.nextLine());
        this.currentOrderLabel = ridInfo.nextLine();
		if(!currentOrderLabel.equals("null"))
			setCurrentOrderFromFiles();
    }
	
	
	private void sendRiderToFile() throws FileNotFoundException{ // used to make a directory for the rider
		ridDir = new File("Rider/" + getUsername());
		ridDir.mkdir(); // make the directory using the Rider usernamename
		File riderInfo = new File(ridDir + "/basicInfo.txt"); // make a text file to store the rider information
		PrintWriter outputRidInfo =  new PrintWriter(riderInfo); // writes into the basicInfo.txt
		outputRidInfo.println(getUsername());// move the info to the basicInfo.txt file
		outputRidInfo.println(getPassword());
		outputRidInfo.println(name); 
		outputRidInfo.println(phoneNumber);
		outputRidInfo.println(queuePos);
		outputRidInfo.println(deliveryCount);
		outputRidInfo.println(currentOrderLabel);
		outputRidInfo.close(); // close basicInfo.txt file
	}
	
	private void setCurrentOrderFromFiles (){
		int stopIndex = 0;
		for(int i = 0; i < currentOrderLabel.length(); ++i){
			if(currentOrderLabel.charAt(i) == '_')
				stopIndex = i;
		}
		String cusUsername = currentOrderLabel.substring(0, stopIndex);
		try{
			File currentOrderFile = new File("Customer/" + cusUsername + "/Order/" + currentOrderLabel+ ".txt");
			currentOrder = new Order(currentOrderFile);
		}
		catch(IOException ex){
			System.out.println(ex.getMessage());
		}
	}
	
	public void deliverCurrentOrder(){
		try{
			getCurrentOrder().replaceOrderStatus(currentOrder, "Delivered");
			currentOrderLabel = null;
			++deliveryCount;
			sendRiderToFile();
			
		}
		catch(IOException ex){
			System.out.println(ex.getMessage());
		}
		
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getPhoneNum(){
		return phoneNumber;
	}
	
	public void setPhoneNum(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}
	
	public Order getCurrentOrder(){
		return currentOrder;
	}
	
	public void setCurrentOrder(Order currentOrder){
		this.currentOrder = currentOrder;
	}
	
	public ArrayList<Order> getPastOrders(){
		return pastOrders;
	}
	
	public void setPastOrders(ArrayList<Order> pastOrders){
		this.pastOrders = pastOrders;
	}
	
	public int getQueuePos(){
		return queuePos;
	}
	
	public void setQueuePos(int queuePos){
		this.queuePos = queuePos;
	}
	
	public int getDeliveryCount(){
		return deliveryCount;
	}
	
	public void setDeliveryCount(int deliveryCount){
		this.deliveryCount = deliveryCount;
	}
	
	public  boolean validateLogin(String userAttempt, String passwordAttempt){
		if(userAttempt.equals(getUsername()) && passwordAttempt.equals(getPassword()))
			return true;
		else
			return false;
	}
	
	@Override
	public String toString(){
		return "";
	}
}