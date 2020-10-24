import java.util.*;
import java.io.*;

public class Rider extends User implements Comparable<Rider>{
	private String name; // name of the rider
	private String phoneNumber; // rider phone number
	private Order currentOrder; // the order that is currently assigned to the rider
	private String currentOrderLabel; // name of the order, i.e. (customer username + _ + orderID)
	ArrayList<Order> pastOrders = new ArrayList<>(); // history of orders deliverd by this rider
	private int queuePos; // rider's position in rider queue.
	private int deliveryCount; // number of deliverd orders.
	private File ridDir; // rider directory.
	// public Rider(){}
	
	// used to create the rider object by the admin and save it files
	public Rider(String username, String password, String name, String phoneNumber){
		super(username, password); // constructor of parent class User
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
        this.setUsername(ridInfo.nextLine()); // setting all the attributes from files
        this.setPassword(ridInfo.nextLine());
		this.name = ridInfo.nextLine();
        this.phoneNumber = ridInfo.nextLine();
        this.queuePos = Integer.parseInt(ridInfo.nextLine());
        this.deliveryCount = Integer.parseInt(ridInfo.nextLine());
        this.currentOrderLabel = ridInfo.nextLine();
		if(!currentOrderLabel.equals("null")) // if the rider has an assigned order, get it from its file
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
		new File(ridDir + "/Order").mkdirs(); // make a directory called Order to store delivery history
	}
	
	private void setCurrentOrderFromFiles (){ // using the currentOrderLabel that was readen from files, set the currentOrder attribute from files
		int stopIndex = 0;
		for(int i = 0; i < currentOrderLabel.length(); ++i){ // find at which char that the underscore that separate between customer username and orderID
			if(currentOrderLabel.charAt(i) == '_')
				stopIndex = i;
		}
		String cusUsername = currentOrderLabel.substring(0, stopIndex); // set the customer username to substring of the currentOrderLabel from char 0 to the underscore '_'
		try{
			File currentOrderFile = new File("Customer/" + cusUsername + "/Order/" + currentOrderLabel+ ".txt"); // get the order file
			currentOrder = new Order(currentOrderFile); // read currentOrder from files
		}
		catch(IOException ex){
			System.out.println(ex.getMessage());
		}
	}
	
	public void deliverCurrentOrder(){ // set the currentOrder as deliverd, and free the rider to accept next orders
		try{
			getCurrentOrder().replaceOrderStatus(currentOrder, "Delivered"); // replace the order status in Restaurant and Customer files for the specific order
			sendCompletedOrderToFiles(); // write the currentOrder as deliverd to the rider history
			currentOrderLabel = null; // reset the currentOrderLabel to null to allow rider to accept further orders
			currentOrder = null; // reset the currentOrder to null to allow rider to accept further orders
			++deliveryCount; // increment the deliveryCount as currentOrder has been deliverd
			MyQueue<Order> orderQueue = Admin.getOrderQueue(); // get the orderQueue using the admin static method
			if(orderQueue.isEmpty()){ // check if there is an order in the OrderQueue, if no, enqueue the rider to the riderQueue again.
				MyQueue<Rider> riderQueue = Admin.getRiderQueue();
				riderQueue.add(this);
				Admin.setRiderQueue(riderQueue);
			}
			else{ // if the orderQueue isn't empty, assign it to the rider immediately
				Order.replaceOrderStatus(orderQueue.peek(), "Delivering");
				this.setCurrentOrderLabel(orderQueue.peek().getCusUsername() + "_" +orderQueue.poll().getID());
				Admin.setOrderQueue(orderQueue);

			}
			sendRiderToFile(); // update the rider file with the new info
		}
		catch(IOException ex){
			System.out.println(ex.getMessage());
		}
		
	}
	
	private void sendCompletedOrderToFiles() throws IOException{ // write the completed order to the rider directory as history
		ridDir = new File("Rider/" + getUsername());
		ridDir.mkdir();
		FileWriter NamesFileOutput = new FileWriter(ridDir + "/Order/names.txt", true);
		PrintWriter outputNames =  new PrintWriter(NamesFileOutput);
		outputNames.println(currentOrderLabel);
		outputNames.close();
		
		
		File OrderFile = new File(ridDir + "/Order/" + currentOrderLabel + ".txt"); 
		PrintWriter outputOrder =  new PrintWriter(OrderFile);
		
		outputOrder.println(currentOrder.getID());
		outputOrder.println(currentOrder.getCusUsername());
		outputOrder.println(currentOrder.getResName());
		outputOrder.println(currentOrder.getOrderStatus());
		outputOrder.println(currentOrder.getOrderPrice());
		outputOrder.println(currentOrder.getOrderType());
		outputOrder.println(currentOrder.getDelAddress());
        for (int i = 0; i< currentOrder.getOrderContents().size(); i++) {
            outputOrder.println(currentOrder.getOrderContents().get(i).writeToOrder()); //Writes item information to file
        }
		outputOrder.close();
	}
	
	public String getName(){ // get rider name
		return name;
	}
	
	public void setName(String name){ // set rider name
		this.name = name;
	}
	
	public String getPhoneNum(){ // get rider phone
		return phoneNumber;
	}
	
	public void setPhoneNum(String phoneNumber){ // set rider name
		this.phoneNumber = phoneNumber;
	}
	
	public Order getCurrentOrder(){ 
		return currentOrder;
	}
	
	public void setCurrentOrder(Order currentOrder){
		this.currentOrder = currentOrder;
	}
	
	public String getCurrentOrderLabel(){
		return currentOrderLabel;
	}
	
	public void setCurrentOrderLabel(String currentOrderLabel){ // et the currentOrderLabel and send it to files
		this.currentOrderLabel = currentOrderLabel;
		setCurrentOrderFromFiles();
		try{
			sendRiderToFile();
		}
		catch(IOException ex){
			System.out.println(ex.getMessage());
		}
	}
	
	public ArrayList<Order> getPastOrders(){ // get the past orders, read the files first to the pastOrders arraylist
		try{
			readOrderHistoryFromFiles(); // sets the order files in rider directory to the pastOrders arrayList
		}
		catch(IOException ex){
			System.out.println("File not found" + ex.getMessage());
		}
		return pastOrders;
	}
	
	public void setPastOrders(ArrayList<Order> pastOrders){
		this.pastOrders = pastOrders;
	}
	
	private void readOrderHistoryFromFiles() throws IOException{ // sets the order files in rider directory to the pastOrders arrayList
		pastOrders.clear(); // clear the pastOrders to avoid redundant items
		ridDir = new File("Rider/" + getUsername());
		ridDir.mkdir(); // make the directory using the Rider usernamename
		File pastOrdersFileInput = new File(ridDir + "/Order/names.txt" );
		Scanner inputOrders = new Scanner(pastOrdersFileInput);
		while(inputOrders.hasNext()){
			File orderFile = new File(ridDir + "/Order/" + inputOrders.nextLine() + ".txt");
			Scanner OrderNameScanner = new Scanner(orderFile);
			pastOrders.add(new Order(orderFile));
		}
		inputOrders.close();
	}
	
	public int getQueuePos(){ // get rider's position in the queue by returning the index that equals his username, if not found, return -1
		try{
			MyQueue<Rider> extractRiderQueue = Admin.getRiderQueue();
			int startingSize = extractRiderQueue.size();
			for(int i = 0; i < startingSize; ++i){
				if(this.getUsername().equals(extractRiderQueue.poll().getUsername()))
					return i;
			}
		}
		catch(IOException ex){
			System.out.println(ex.getMessage());
		}
		return -1;
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
	
	public  boolean validateLogin(String userAttempt, String passwordAttempt){ // checks that the login information mathces
		if(userAttempt.equals(getUsername()) && passwordAttempt.equals(getPassword()))
			return true;
		else
			return false;
	}
	
	@Override
	public String toString(){
		return getUsername();
	}
	
	@Override
	public int compareTo(Rider r){ // comparable method to sort the riders according to their deliveryCount
		return getDeliveryCount() - r.getDeliveryCount();
	}
}