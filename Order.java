import java.io.*;
import java.util.*;

public class Order {
    //ATTRIBUTES

    private int orderID;           //Unique order ID
    private static int lastID; //Keeps track of last-used orderID to allow each order to have a unique ID.
    private String cusUsername;    //Customer username
    private String resName;        //Restaurant name
    private ArrayList<Item> orderContents = new ArrayList<Item>();  //ArrayList containing order items
    private String orderStatus;                 //Order status can be: Sent, Preparing, Ready, or Collected
    private double orderPrice;                  //Total order price
    private Date timeRequested = new Date();    //Sets timeRequested to the current time by default
    private Date timeReady;                     //Time when the restaurant finished preparing order
    private Date timeCollected;                 //Time when customer collected order
    private String orderType;
    private String delAddress;
    private Rider assignedRider;

    static File cusDir = new File("Customer\\"); // + cusUsername
    static File resDir = new File("Restaurant\\" ); //+ resName


    public static void saveLastID() throws IOException {
        new File(cusDir + "\\lastID.txt").mkdirs();
        File lastIDTxt = new File(cusDir + "\\lastID.txt");
        PrintWriter lastIDPrintWriter = new PrintWriter(lastIDTxt);
        lastIDPrintWriter.println(lastID);
        lastIDPrintWriter.close();
    }
    public static int getLastID(File f) throws IOException {
        if (f.exists()){
            Scanner readLastID = new Scanner(f);
            if (readLastID.hasNextLine()) {
                int scannedID = Integer.parseInt(readLastID.nextLine());
                readLastID.close();
                return scannedID;
            }
            else {
                // FileWriter lastIDFileWriter = new FileWriter(f);
                PrintWriter lastIDPrintWriter = new PrintWriter(f);
                lastIDPrintWriter.println("0");
                lastIDPrintWriter.close();
                // lastIDFileWriter.close();
                return 0; ////////
            }
        }
        else {
            new File(cusDir + "\\lastID.txt").mkdirs();
            File lastIDTxt = new File(cusDir + "\\lastID.txt");
            lastIDTxt.createNewFile();
            PrintWriter lastIDPrintWriter = new PrintWriter(lastIDTxt);
            lastIDPrintWriter.println("0");
            lastIDPrintWriter.close();
            return 0;
        }
    }

    public static void replaceLines(File f, String replacementString, int lineNumber)  throws IOException {         //Used to edit lines in text files
        StringBuffer replacementLine = new StringBuffer(replacementString);
        Scanner readFile = new Scanner(f);
        ArrayList <String> fileLines = new ArrayList<String>(); //ArrayList where lines are stored; line number corresponds with index number
        while (readFile.hasNextLine()) {            //copies file contents into arraylist
            fileLines.add(readFile.nextLine());
        }
        readFile.close();
        Object [] lineArray = fileLines.toArray();
        lineArray[lineNumber-1] = replacementLine;  // Replaces desired line
        PrintWriter clearFile = new PrintWriter(f); //clears old file contents
        clearFile.print("");
        clearFile.close();
        
        PrintWriter printFile = new PrintWriter(f);     
        StringBuffer output = new StringBuffer("");
        for (int i = 0; i<lineArray.length; i++) {      //Combines new lines into a StringBuffer
            output.append(lineArray[i]);
            output.append("\n");
        }
        printFile.println(output);      //Prints combined StringBuffer to file
        printFile.close();
    }

    public static void replaceOrderStatus(Order o, String newStatus) throws IOException{
        //replace customer order file
        File cusOrder = new File("Customer\\"+o.getCusUsername()+"\\Order\\"+o.getCusUsername()+"_"+o.getID()+".txt");
        Order.replaceLines(cusOrder, newStatus, 4);
        //replace restaurant order file
        File resOrder = new File("Restaurant\\"+ o.getResName() +"\\Order\\"+o.getCusUsername()+"_"+o.getID()+".txt");
        Order.replaceLines(resOrder, newStatus, 4);
    }

    public void createCusFiles(int orderID, String cusUsername, String resName, ArrayList<Item> orderContents, String orderStatus, double orderPrice, String orderType, String delAddress) throws IOException{

        new File(cusDir + "\\" + cusUsername + "\\Order\\").mkdirs();                                              // code for making order file in restaurant directory
        File cusOrderNames = new File(cusDir + "\\" + cusUsername + "\\Order\\"+"names.txt"); //Adds order filename to names.txt file. Used to fetch order file names later.
        FileWriter cusOrderNamesFileWriter = new FileWriter(cusOrderNames, true);
        PrintWriter cusOrderNamesPrintWriter = new PrintWriter(cusOrderNamesFileWriter);
        cusOrderNamesPrintWriter.println(cusUsername+"_"+ orderID);
        cusOrderNamesPrintWriter.close();
        cusOrderNamesFileWriter.close();

        File cusOrder = new File(cusDir + "\\" + cusUsername + "\\Order\\" +cusUsername+"_"+ orderID +".txt");
		PrintWriter outputCusOrder =  new PrintWriter(cusOrder);
        outputCusOrder.println(orderID);
        outputCusOrder.println(cusUsername);
        outputCusOrder.println(resName);
        outputCusOrder.println(orderStatus);
        outputCusOrder.println(orderPrice);
        outputCusOrder.println(orderType);
        outputCusOrder.println(delAddress);

        for (int i = 0; i< orderContents.size(); i++) {
            outputCusOrder.println(orderContents.get(i).writeToOrder()); //Writes item information to file
        }
        outputCusOrder.close();

    }


    public void createResFiles(int orderID, String cusUsername, String resName, ArrayList<Item> orderContents, String orderStatus, double orderPrice, String orderType, String delAddress) throws IOException{
        
        new File(resDir + "\\" + resName + "\\Order\\").mkdirs();                                              // code for making order file in restaurant directory
        File orderNames = new File(resDir + "\\" + resName + "\\Order\\"+"names.txt"); //Adds order filename to names.txt file. Used to fetch order file names later.
        FileWriter orderNamesFileWriter = new FileWriter(orderNames, true);
        PrintWriter orderNamesWriter = new PrintWriter(orderNamesFileWriter);
        orderNamesWriter.println(cusUsername+"_"+ orderID);
        orderNamesWriter.close();

		File resOrder = new File(resDir + "\\"+resName+"\\Order\\"+cusUsername+"_"+ orderID +".txt");
		PrintWriter outputResOrder =  new PrintWriter(resOrder);
        outputResOrder.println(orderID);
        outputResOrder.println(cusUsername);
        outputResOrder.println(resName);
        outputResOrder.println(orderStatus);
        outputResOrder.println(orderPrice);
        outputResOrder.println(orderType);
        outputResOrder.println(delAddress);

        for (int i = 0; i< orderContents.size(); i++) {
            outputResOrder.println(orderContents.get(i).writeToOrder()); //Writes item information to file
        }
        outputResOrder.close();
    }



    public void createOrderFiles(int orderID, String cusUsername, String resName, ArrayList<Item> orderContents, String orderStatus, double orderPrice, String orderType, String delAddress) throws IOException{
        
        createCusFiles(orderID, cusUsername, resName, orderContents,orderStatus, orderPrice, orderType, delAddress);
        createResFiles(orderID, cusUsername, resName, orderContents, orderStatus, orderPrice, orderType, delAddress);
	}

    //CONSTRUCTORS

    public Order() {}   //Default constructor        
        
    public Order(String newCusUsername, String newResName, ArrayList<Item> newContents, String newOrderType, String newDelAddress) throws IOException{  //Constructor for new orders
        File f = new File(cusDir + "\\lastID.txt");
        if(!(f.exists()))
            f.createNewFile();
        setLastID(getLastID(f));
        //System.out.println(getLastID()); ////////
        this.orderID = ++lastID;
        saveLastID();
        this.cusUsername = newCusUsername;
        this.resName = newResName;
        this.orderType = newOrderType;
        this.delAddress = newDelAddress;
        for (Item i:newContents){    //Copies newContents into orderContents
            this.orderContents.add(i);
            this.orderPrice += i.getItemPrice();
        }
        this.orderStatus = "Preparing";
        this.timeRequested = new Date(); //Sets timeRequested to the current time
        createOrderFiles(this.orderID, this.cusUsername, this.resName, this.orderContents, this.orderStatus, this.orderPrice, this.orderType, this.delAddress);
    }
    public Order(String filePath) throws IOException {      //Constructor for orders from filepath
        File f = new File(cusDir + "\\lastID.txt");
        if(!(f.exists()))
            f.createNewFile();
        setLastID(getLastID(new File(cusDir + "\\lastID.txt")));
        this.orderID = ++lastID;
        saveLastID();
        Scanner orderInfo = new Scanner(new File(filePath));
        this.orderID = Integer.parseInt(orderInfo.nextLine());
        this.cusUsername = orderInfo.nextLine();
        this.resName = orderInfo.nextLine();
        this.orderStatus = orderInfo.nextLine();
        this.orderPrice = Double.parseDouble(orderInfo.nextLine());
        this.orderType = orderInfo.nextLine();
        this.delAddress = orderInfo.nextLine();

        while (orderInfo.hasNext()) {       // Creates items from file
            this.orderContents.add(new Item(orderInfo.nextLine(),Double.parseDouble(orderInfo.nextLine()),orderInfo.nextLine(),orderInfo.nextLine(),Integer.parseInt(orderInfo.nextLine()))); 
        }
        orderInfo.close();
    }

    public Order(File newFile) throws IOException { //Constructor for orders from file object
        File f = new File(cusDir + "\\lastID.txt");
        if(!(f.exists()))
            f.createNewFile();
        setLastID(getLastID(new File(cusDir + "\\lastID.txt")));
        this.orderID = ++lastID;
        saveLastID();
        Scanner orderInfo = new Scanner(newFile);
        this.orderID = Integer.parseInt(orderInfo.nextLine());
        this.cusUsername = orderInfo.nextLine();
        this.resName = orderInfo.nextLine();
        this.orderStatus = orderInfo.nextLine();
        this.orderPrice = Double.parseDouble(orderInfo.nextLine());
        this.orderType = orderInfo.nextLine();
        this.delAddress = orderInfo.nextLine();

        while (orderInfo.hasNext()) {       // Creates items from file
            this.orderContents.add(new Item(orderInfo.nextLine(),Double.parseDouble(orderInfo.nextLine()),orderInfo.nextLine(),orderInfo.nextLine(),Integer.parseInt(orderInfo.nextLine()))); 
        }
        orderInfo.close();
    }


    //GETTERS AND SETTERS

    public int getID() {    
        return orderID;
    }
    public void setID(int newID) {  
        this.orderID = newID;
    }
    public String getCusUsername() {
        return cusUsername;
    }
    public void setCusUsername(String newName) {
        this.cusUsername = newName;
    }
    public String getResName() {
        return resName;
    }
    public void setResName(String newName) {
        this.resName = newName;
    }
    public ArrayList<Item> getOrderContents() {
        return orderContents;
    }
    public String getOrderStatus() {
        return orderStatus;
    }
    public void setOrderStatus(String newStatus) {
        this.orderStatus = newStatus;
    }
    public double getOrderPrice(){
        return orderPrice;
    }
    public Date getTimeRequested() {
        return timeRequested;
    }
    public Date getTimeReady() {
        return timeReady;
    }
    public Date getTimeCollected() {
        return timeCollected;
    }
    public static int getLastID() {
        return lastID;
    }   
    public static void setLastID(int newID) {
        lastID = newID;
    }
    public String getOrderType() {
        return orderType;
    }
    public void setOrderType(String newType) {
        this.orderType = newType;
    }
    public String getDelAddress() {
        return delAddress;
    }
    public void setDelAddress(String newAddress) {
        this.delAddress = newAddress;
    }
    public Rider getAssignedRider() {
        return assignedRider;
    }
    public void setAssignedRider(Rider newRider) {
        this.assignedRider = newRider;
    }
    
    
    // TOSTRING METHOD

    @Override
    public String toString() {
        return "Order ID " + orderID +". Ordered by " + cusUsername
        + " Total Price RM" + getOrderPrice();
    }

    public String toStringHistory() {
        StringBuffer or = new StringBuffer("OrderID: " + orderID +". Ordered by " + cusUsername + " from restaurant " + resName + ". ");
        or.append("\nContents: ");
        for (Item i: this.getOrderContents()) {
            or.append(i.toString() + "\n");
        }
        return or.toString();

    }
}