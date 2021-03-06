import java.util.*;
import java.io.*;

public class Admin extends User{            //This class stores admin user information for login
    private String name;                    //Admin name
    private ArrayList<Order> pastOrders = new ArrayList<Order>();   //Combined list of all orders made on the platform
    private static MyQueue<Rider> riderQueue= new MyQueue<Rider>(); //Globally used rider queue (used for determining delivery order)
    private static MyQueue<Order> orderQueue= new MyQueue<Order>(); //Globally used order queue (used for determining delivery order)
    private File adminDir;          //Directory where admin information is stored

    Admin() {           //Default constructor (not used)
        super(null,null);
    }

    Admin(String username, String password) throws IOException{ // Constructor used for login
        super(username, password);
        // this.name = name;
        Scanner adminInfo = new Scanner(new File("Admin\\"+ username + "\\info.txt"));  // Reading customer information from info.txt
        this.setUsername(adminInfo.nextLine());
        this.setPassword(adminInfo.nextLine());
        this.setName(adminInfo.nextLine());

        // Reading all order history information into pastOrders, goes through each restaurant directory
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

    public void createAdminFile(String username, String password, String name) throws IOException{  // Creates admin info file in admin directory, used for login
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

    public static MyQueue<Rider> getRiderQueue() throws IOException{        // Converts riderQueue.txt file into a MyQueue, used by other classes
        MyQueue<Rider> riderQueueObj = new MyQueue<Rider>();
        if (new File("Rider\\riderQueue.txt").exists()) {
            File riderQueueTxt = new File("Rider\\riderQueue.txt");
            Scanner riderQueueReader = new Scanner(riderQueueTxt);
            while (riderQueueReader.hasNext()){
                riderQueueObj.add(new Rider(riderQueueReader.nextLine()));
            }
            riderQueueReader.close();
            return riderQueueObj;
        }
        else {
            File riderQueueTxt = new File("Rider\\riderQueue.txt");
            riderQueueTxt.createNewFile();
            return new MyQueue<Rider>();
        }
    }

    public static void setRiderQueue(MyQueue<Rider> newRiderQueue) throws IOException{      //Converts MyQueue object back into riderQueue.txt file, used by other classes
        MyQueue<Rider> tempQueue = newRiderQueue;
        
        File riderQueueTxt = new File("Rider\\riderQueue.txt");
        FileWriter riderQueueFileWriter = new FileWriter(riderQueueTxt, false);
        PrintWriter riderQueuePrintWriter = new PrintWriter(riderQueueFileWriter);

        StringBuilder source = new StringBuilder();

        while (!tempQueue.isEmpty()){
            source.append(tempQueue.poll().getUsername() + "\n");
        }
        riderQueuePrintWriter.print(source.toString());
        riderQueueFileWriter.close();
        riderQueuePrintWriter.close();
    }

    public static MyQueue<Order> getOrderQueue() throws IOException{            // Converts orderQueue.txt file into a MyQueue, used by other classes
        MyQueue<Order> orderQueueObj = new MyQueue<Order>();
        if (new File("Rider\\orderQueue.txt").exists()) {
            File orderQueueTxt = new File("Rider\\orderQueue.txt");
            Scanner orderQueueReader = new Scanner(orderQueueTxt);

            String readerInput;
            while (orderQueueReader.hasNext()){
                readerInput = orderQueueReader.nextLine();
                orderQueueObj.add(new Order("Customer\\"+ readerInput.split("_")[0] + "\\Order\\" +readerInput+".txt"));
            }
            orderQueueReader.close();
            return orderQueueObj;
        }
        else {
            File orderQueueTxt = new File("Rider\\orderQueue.txt");
            orderQueueTxt.createNewFile();
            return new MyQueue<Order>();
        }
    }
    public static void setOrderQueue(MyQueue<Order> newOrderQueue) throws IOException {     //Converts MyQueue object back into orderQueue.txt file, used by other classes
        MyQueue<Order> tempQueue = newOrderQueue;
        
        File orderQueueTxt = new File("Rider\\orderQueue.txt");
        FileWriter orderQueueFileWriter = new FileWriter(orderQueueTxt, false);
        PrintWriter orderQueuePrintWriter = new PrintWriter(orderQueueFileWriter);

        StringBuilder source = new StringBuilder();

        while (!tempQueue.isEmpty()){
            Order currOrder = tempQueue.poll();
            source.append(currOrder.getCusUsername() + "_" + currOrder.getID() + "\n");
        }
        orderQueuePrintWriter.print(source.toString());
        orderQueueFileWriter.close();
        orderQueuePrintWriter.close();
    }
    public static void addToOrderQueue(Order newOrder){
        orderQueue.add(newOrder);
    }
    public static void addToRiderQueue(Rider newRider){
        riderQueue.add(newRider);
    }

    @Override
    public  boolean validateLogin(String userAttempt, String passwordAttempt){              //Used to verify if login information is correct
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
