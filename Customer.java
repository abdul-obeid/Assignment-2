import java.io.*;
import java.util.*;

public class Customer extends User {
    private String name;
    // private String username;
    // private String password;
    private ArrayList<Order> currentOrders = new ArrayList<Order>();
    private ArrayList<Order> pastOrders = new ArrayList<Order>();
    private int orderCount = 0;
    private String homeAddress;
    public File cusDir;

    public void createCusFile(String name, String username, String password , String homeAddress, int orderCount) throws IOException{
        cusDir = new File("Customer\\" + username +"\\");
        cusDir.mkdirs();
        new File(cusDir + "\\Order\\").mkdirs();                                              // code for making order file in restaurant directory
        File cusOrderNames = new File(cusDir + "\\Order\\"+"names.txt"); //Adds order filename to names.txt file. Used to fetch order file names later.
        cusOrderNames.createNewFile();
		File cusInfo = new File(cusDir + "\\info.txt");
		PrintWriter outputCusInfo =  new PrintWriter(cusInfo);
        outputCusInfo.println(name);
        outputCusInfo.println(username);
        outputCusInfo.println(password);
        outputCusInfo.println(homeAddress);
        outputCusInfo.println(orderCount);
		outputCusInfo.close();
	}
    public Customer() {
        super(null, null);
    }
    // public Customer(String newName, String newUsername, String newPassword) throws IOException{ //Constructor used for registration
    //     super(newUsername, newPassword);
    //     this.name = newName;
    //     createCusFile(newName, newUsername, newPassword);
    // }
    public Customer(String newName, String newUsername, String newPassword, String newAddress) throws IOException{ //Constructor used for registration
        super(newUsername, newPassword);
        this.name = newName;
        this.homeAddress = newAddress;
        createCusFile(newName, newUsername, newPassword, newAddress, 0);
    }
    public Customer(String cusUsername) throws IOException {   //Constructor used for login
        super(null, null);
        Scanner cusInfo = new Scanner(new File("Customer\\"+ cusUsername + "\\info.txt"));  // Reading customer information from info.txt
        this.name = cusInfo.nextLine();
        this.setUsername(cusInfo.nextLine());
        this.setPassword(cusInfo.nextLine());
        this.setAddress(cusInfo.nextLine());
        this.setOrderCount(Integer.parseInt(cusInfo.nextLine())); // This line is kinda sus

        Scanner orderNameTxt = new Scanner(new File("Customer\\"+cusUsername+"\\Order\\names.txt"));    // Reading order information to create pastOrders
        while (orderNameTxt.hasNext()) {
            String orderNames = orderNameTxt.nextLine();
            this.pastOrders.add(new Order("Customer\\"+cusUsername+"\\Order\\" + orderNames +".txt"));   
        }
    }

    public  boolean validateLogin(String userAttempt, String passwordAttempt){
		if(userAttempt.equals(getUsername()) && passwordAttempt.equals(getPassword()))
			return true;
		else
			return false;
    }
    
    public Order createNewOrder(Restaurant res, Cart shoppingCart, String orderType, String delAddress) throws IOException {
        Order or = new Order(this.getUsername(), res.getName(), shoppingCart.getChosenItems(), orderType, delAddress);
        return or;
    }


    public String getName() {
        return this.name;
    }
    public void setName(String newName) {
        this.name = newName;
    }
    public ArrayList<Order> getCurrentOrder() {     // Changed from Order to Arraylist <---------- SUS
        return this.currentOrders;
    }
    public void setCurrentOrder(ArrayList<Order> newOrder) {
        this.currentOrders = newOrder;
    }
    public ArrayList<Order> getPastOrders() {
        return this.pastOrders;
    }
    public int getOrderCount() {
        return this.orderCount;
    }
    public void setOrderCount(int newCount) {
        this.orderCount = newCount;
    }
    public String getAddress() {
        return this.homeAddress;
    }
    public void setAddress(String newAddress) {
        this.homeAddress = newAddress;
    }

    @Override
    public String toString() {
        return ("Customer name: " + this.name + ". Username: " + this.getUsername()+". ");
    }
}