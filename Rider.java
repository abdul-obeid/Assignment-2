import java.util.*;
import java.io.*;

public class Rider extends User implements Comparable<Rider>{
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
		new File(ridDir + "/Order").mkdirs();
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
			sendCompletedOrderToFiles();
			currentOrderLabel = null;
			currentOrder = null;
			++deliveryCount;
			MyQueue<Order> orderQueue = Admin.getOrderQueue();
			if(orderQueue.isEmpty()){
				MyQueue<Rider> riderQueue = Admin.getRiderQueue();
				riderQueue.add(this);
				Admin.setRiderQueue(riderQueue);
			}
			else{
				Order.replaceOrderStatus(orderQueue.peek(), "Delivering");
				this.setCurrentOrderLabel(orderQueue.peek().getCusUsername() + "_" +orderQueue.poll().getID());
				Admin.setOrderQueue(orderQueue);

			}


			
			sendRiderToFile();
			
		}
		catch(IOException ex){
			System.out.println(ex.getMessage());
		}
		
	}
	
	private void sendCompletedOrderToFiles() throws IOException{
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
	
	public Order getCurrentOrderLabel(){
		return currentOrder;
	}
	
	public void setCurrentOrderLabel(String currentOrderLabel){
		this.currentOrderLabel = currentOrderLabel;
		setCurrentOrderFromFiles();
		try{
			sendRiderToFile();
		}
		catch(IOException ex){
			System.out.println(ex.getMessage());
		}
	}
	
	public ArrayList<Order> getPastOrders(){
		try{
			readOrderHistoryFromFiles();
		}
		catch(IOException ex){
			System.out.println("File not found" + ex.getMessage());
		}
		return pastOrders;
	}
	
	public void setPastOrders(ArrayList<Order> pastOrders){
		this.pastOrders = pastOrders;
	}
	
	private void readOrderHistoryFromFiles() throws IOException{
		pastOrders.clear(); // clear the pastOrders to avoid redundant items
		File pastOrdersFileInput = new File(ridDir + "/Order/names.txt" );
		Scanner inputOrders = new Scanner(pastOrdersFileInput);
		while(inputOrders.hasNext()){
			File orderFile = new File(ridDir + "/Order/" + inputOrders.nextLine() + ".txt");
			Scanner OrderNameScanner = new Scanner(orderFile);
			pastOrders.add(new Order(orderFile));
		}
		inputOrders.close();
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
	
	@Override
	public int compareTo(Rider r){
		return getDeliveryCount() - r.getDeliveryCount();
	}
}