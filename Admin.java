import java.util.*;
import java.io.*;

public class Admin extends User{
    private String name;
    private ArrayList<Order> pastOrders = new ArrayList<Order>();
    private static MyQueue<Rider> riderQueue= new MyQueue<Rider>();
    private static MyQueue<Order> orderQueue= new MyQueue<Order>();
    private File adminDir;

    Admin() {
        super(null,null);
    }

    Admin(String username, String password) throws IOException{
        super(username, password);
        // this.name = name;
        Scanner adminInfo = new Scanner(new File("Admin\\"+ username + "\\info.txt"));  // Reading customer information from info.txt
        this.setUsername(adminInfo.nextLine());
        this.setPassword(adminInfo.nextLine());
        this.setName(adminInfo.nextLine());

        Scanner mishaltitOrderNameTxt = new Scanner(new File("Restaurant\\Mishaltit\\Order\\names.txt"));    // Reading order information to create pastOrders
        Scanner pizzaOrderNameTxt = new Scanner(new File("Restaurant\\Pizza Palace\\Order\\names.txt"));    // Reading order information to create pastOrders
        Scanner mamakOrderNameTxt = new Scanner(new File("Restaurant\\Mamak Spot\\Order\\names.txt"));    // Reading order information to create pastOrders

        while (pizzaOrderNameTxt.hasNext()) {
            String orderNames = pizzaOrderNameTxt.nextLine();
            this.pastOrders.add(new Order("Restaurant\\Pizza Palace\\Order\\"+ orderNames +".txt"));   
        }
        while (mishaltitOrderNameTxt.hasNext()) {
            String orderNames = mishaltitOrderNameTxt.nextLine();
            this.pastOrders.add(new Order("Restaurant\\Mishaltit\\Order\\"+ orderNames +".txt"));   
        }
        while (mamakOrderNameTxt.hasNext()) {
            String orderNames = mamakOrderNameTxt.nextLine();
            this.pastOrders.add(new Order("Restaurant\\Mamak Spot\\Order\\"+ orderNames +".txt"));   
        }
    }

    public void createAdminFile(String username, String password, String name) throws IOException{
        adminDir = new File("Admin\\" + username +"\\");
        adminDir.mkdirs();
        new File(adminDir + "\\Order\\").mkdirs();                                              // code for making order file in restaurant directory
        File adminOrderNames = new File(adminDir + "\\Order\\"+"names.txt"); //Adds order filename to names.txt file. Used to fetch order file names later.
        adminOrderNames.createNewFile();
		File cusInfo = new File(adminDir + "\\info.txt");
		PrintWriter outputCusInfo =  new PrintWriter(cusInfo);
        outputCusInfo.println(username);
        outputCusInfo.println(password);
        outputCusInfo.println(name);
		outputCusInfo.close();
    }
    
     
    public String getName() {
        return this.name;
    }
    public void setName(String newName){
        this.name = newName;
    }
    public ArrayList<Order> getPastOrders() {
        return this.pastOrders;
    }
    public void setPastOrders(ArrayList<Order> newPastOrders){
        this.pastOrders = newPastOrders;
    }

    public static MyQueue<Rider> getRiderQueue() {
        return riderQueue;
    }
    public static void setRiderQueue(MyQueue<Rider> newRiderQueue){
        riderQueue = newRiderQueue;
    }
    public static MyQueue<Order> getOrderQueue() {
        return orderQueue;
    }
    public static void setOrderQueue(MyQueue<Order> newOrderQueue){
        orderQueue = newOrderQueue;
    }
    public static void addToOrderQueue(Order newOrder){
        orderQueue.add(newOrder);
    }
    public static void addToRiderQueue(Rider newRider){
        riderQueue.add(newRider);
    }

    @Override
    public  boolean validateLogin(String userAttempt, String passwordAttempt){
		if(userAttempt.equals(getUsername()) && passwordAttempt.equals(getPassword()))
			return true;
		else
			return false;
    }
    @Override
    public String toString() {
        return "Admin " + name + ". ";
    }
}
