import java.io.*;
import java.util.*;

public class Customer extends User {
    private String name;
    // private String username;
    // private String password;
    private Order currentOrder;
    private ArrayList<Order> pastOrders = new ArrayList<Order>();
    public File cusDir;

    public void createCusFile(String name, String username, String password ) throws IOException{
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
		outputCusInfo.close();
	}
    public Customer() {
        super(null, null);
    }
    public Customer(String newName, String newUsername, String newPassword) throws IOException{ //Constructor used for registration
        super(newUsername, newPassword);
        this.name = newName;
        createCusFile(newName, newUsername, newPassword);
    }
    public Customer(String cusUsername) throws IOException {   //Constructor used for login
        super(null, null);
        Scanner cusInfo = new Scanner(new File("Customer\\"+ cusUsername + "\\info.txt"));
        this.name = cusInfo.nextLine();
        this.setUsername(cusInfo.nextLine());
        this.setPassword(cusInfo.nextLine());
        Scanner orderNameTxt = new Scanner(new File("Customer\\"+cusUsername+"\\Order\\names.txt"));
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
    
    public Order createNewOrder(Restaurant res, Cart shoppingCart) throws IOException {
        Order or = new Order(this.getUsername(), res.getName(), shoppingCart.getChosenItems());
        return or;
    }


    public String getName() {
        return this.name;
    }
    public void setName(String newName) {
        this.name = newName;
    }
    public Order getCurrentOrder() {
        return this.currentOrder;
    }
    public void setCurrentOrder(Order newOrder) {
        this.currentOrder = newOrder;
    }
    public ArrayList<Order> getPastOrders() {
        return this.pastOrders;
    }

    @Override
    public String toString() {
        return ("Customer name: " + this.name + ". Username: " + this.getUsername()+". ");
    }
}