import java.util.*;
import java.io.*;

public class AdminUI {
    public static void main(String [] args) {
        try {
            loginScreen();

            //         Test iterating through files: 
            // File [] test = new File("Customer").listFiles();
            // for (int i = 0; i <= test.length; i++)
            // System.out.println(test[i]);
        }
        catch (IOException | InterruptedException e) {
            System.out.println("Error: " + e);
        }

    }
    
    public static void deleteStringFromFile(File f, String deleteThis) throws IOException{
		File temp = new File("temp.txt"); // make a temp file to copy all the other names to it.
		PrintWriter moveToTemp = new PrintWriter(temp);
		Scanner originalContents = new Scanner(f);
		
		while(originalContents.hasNext()){
			String nextLine = originalContents.nextLine();
			if(!nextLine.equals(deleteThis)){
				moveToTemp.println(nextLine);
			}
		}
		moveToTemp.close();
		originalContents.close();
		File tempName = f;
		System.out.println(f.delete());
		System.out.println(temp.renameTo(tempName)); 
    }

    public static void loginScreen() throws IOException, InterruptedException{
        Scanner input = new Scanner(System.in);
        System.out.println("========================================================================");
        System.out.println("Please enter your admin username and password: (E.g. admin1 pass1234)");
        System.out.println("========================================================================");
        String userAttempt = input.next();
        String passAttempt = input.next();

        //System.out.println(userAttempt); ///Debugging
        Admin a = new Admin(userAttempt, passAttempt);
        // System.out.println(c); //Debugging
        // System.out.println("Test " + c.getUsername()); //Debugging
        // System.out.println("Test " + c.getPassword()); //Debugging
        // System.out.println("Test " + passAttempt); //Debugging
        //input.close();
        if (passAttempt.equals(a.getPassword())) {
            System.out.println("Login Successful. Welcome "+ a.getName()+"! ");
            mainScreen(a);
        }
        else if (!(a.getPassword().equals(passAttempt))){
            System.out.println("Login failed. Please try again. ");
            loginScreen();
        }
    
    }
    

    public static void mainScreen(Admin a) throws IOException, InterruptedException{
        Scanner input = new Scanner(System.in);
        System.out.println("========================================================================");
        System.out.println("Welcome, " + a.getName() + ". Please select your desired option: ");
        System.out.println("========================================================================");
        System.out.println("1. View riders");
        System.out.println("2. View restaurant statistics");
        System.out.println("3. View customer statistics");
        System.out.println("4. View combined order history");
        System.out.println("5. Sign out");
        int choice = input.nextInt();

        //input.close();
        if (choice == 1) {
            viewRidersScreen(a);
        }
        else if (choice == 2) {
            viewResOrderCount(a);
        }
        else if (choice == 3) {
            viewCusOrderCount(a);
        }
        else if (choice == 4) {
            viewOrderHistories(a);
        }
        else if (choice == 5) {
            loginScreen();
        }
    }

    public static void viewRidersScreen(Admin a) throws IOException, InterruptedException{
        ArrayList<Rider> riderList = new ArrayList<Rider>();
        if (new File("Rider\\riderList.txt").exists()) {
            File riderListTxt = new File("Rider\\riderList.txt");
            Scanner riderListReader = new Scanner(riderListTxt);
            while (riderListReader.hasNext()){
                riderList.add(new Rider(riderListReader.nextLine()));
            }
            riderListReader.close();
        }
        else {
            File riderNamesTxt = new File("Rider\\riderList.txt");
		    riderNamesTxt.createNewFile();
        }

        Collections.sort(riderList, Collections.reverseOrder());

        Scanner input = new Scanner(System.in);
        System.out.println("========================================================================");
        System.out.println("Riders: ");
        System.out.println("========================================================================");
        System.out.println("Rider name                 Username                 Password                 Phone                 Delivery count");
        System.out.println("_________________________________________________________________________________________________________________\n");

        for (Rider rid : riderList) {                                                          ////// FIX FORMATTING HERE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            System.out.println(rid.getName() +"                 " + rid.getUsername() + "                 " + rid.getPassword() + "                 " + rid.getPhoneNum() + "                 " + rid.getDeliveryCount() );
        }
        System.out.println("\n\n");
        System.out.println("1. Add new rider");
        System.out.println("2. Manage riders");
        System.out.println("3. Back to main menu");
        int choice = input.nextInt();

        //input.close();
        if (choice == 1) {
            addNewRiderScreen(a);
        }
        else if (choice == 2) {
            manageRidersScreen1(a);
        }
        else if (choice == 3) {
            mainScreen(a);
        }
        else if (choice == 4) {
            loginScreen();
        }
    
    }

    public static void viewResOrderCount(Admin a) throws IOException, InterruptedException{
        // File [] resDirectoryList = new File("Restaurant").listFiles();
        ArrayList<Restaurant> resList = new ArrayList<Restaurant>();

        resList.add(new Restaurant ("Pizza Palace","0123456789", "0123456789", "Come home to true Italian Pizza at Pizza Palace, we offer a wide range of home-made Italian Pizzas alongside a menu complete with classic and rustic Italian dishes and a variety of cocktails.", "29 Jalan Riong, Bangsar, Kuala Lumpur, MY 59100"));
        resList.add(new Restaurant ("Mishaltit", "0123456789","0123456789", "Mishaltit restaurant is the best choice for Arabic & Western cuisine , Promises a value lifestyle proposition of great variety and quality food at affordable prices ", " 226 Jalan Ampang, Kuala Lumpur, MY 50450"));
        resList.add(new Restaurant ("Mamak Spot", "0123456789", "0123456789", "Mamak Station brings you the best in comfort food from the diverse street vendors of Malaysia. Our food is a celebration of flavors...layered from Chinese, Indian, and Malay roots.", "2 Jalan Robertson, G4 & G5, Idaman Robertson, Kuala Lumpur, MY 50150"));
        
        Collections.sort(resList, Collections.reverseOrder());
        // ArrayList<Integer> resOrderCounts = new ArrayList<Integer>();
        // for (int i = 0; i < resList.size(); i++) {
        //     File [] orderPaths = new File("Restaurant\\"+ resList.get(i).getUsername()+ "\\Order").listFiles();
        //     resOrderCounts.add(orderPaths.length);
        // }

        System.out.println("====================================================================");
        System.out.println("Restaurants: ");
        System.out.println("====================================================================");
        System.out.println("Restaurant name                 Username                 Order count");
        System.out.println("____________________________________________________________________\n");
        for (int i = 0; i < resList.size(); i++) {
            System.out.println(resList.get(i).getName() + "                 " + resList.get(i).getUsername() + "                 " + resList.get(i).getOrderCount());
        }
        System.out.println("Enter anything to go back to previous menu");
        Scanner input = new Scanner(System.in);
        input.next();
        mainScreen(a);
    }

    public static void viewCusOrderCount(Admin a) throws IOException, InterruptedException{ //ADD COMPARATORS!!!!!!!!!!!!!!!
        File [] cusDirectoryList = new File("Customer").listFiles();
        ArrayList<Customer> cusList = new ArrayList<Customer>();

        for (int i = 0; i < cusDirectoryList.length; i++) {
            String fullPath = cusDirectoryList[i].getPath();
            String shortPath = fullPath.replace("Customer\\", "");
            if (!shortPath.contains("lastID.txt")) {
                cusList.add(new Customer(shortPath));
            }

            // cusList.add(new Customer(cusDirectoryList[i].getPath()));
        }

        // ArrayList<Integer> cusOrderCounts = new ArrayList<Integer>();
        // for (int i = 0; i < cusList.size(); i++) {
        //     File [] orderPaths = new File("Customer\\"+ cusList.get(i).getUsername()+ "\\Order").listFiles();
        //     cusOrderCounts.add(orderPaths.length);
        // }

        Collections.sort(cusList, Collections.reverseOrder());
        System.out.println("====================================================================");
        System.out.println("Customers: ");
        System.out.println("====================================================================");
        System.out.println("Customer name                 Username                 Order count");
        System.out.println("____________________________________________________________________\n");
        for (int i = 0; i < cusList.size(); i++) {
            System.out.println(cusList.get(i).getName() + "                 " + cusList.get(i).getUsername() + "                 " + cusList.get(i).getOrderCount()); //(cusOrderCounts.get(i)-1)
        }
        System.out.println("Enter anything to go back to previous menu");
        Scanner input = new Scanner(System.in);
        input.next();
        mainScreen(a);
    }

    public static void viewOrderHistories(Admin a) throws IOException, InterruptedException{        // FIX FORMATTING!!!!!!!!!!!!!!!!!!!!!!!!
        System.out.println("=================================================================================================================");
        System.out.println("Combined order history: ");
        System.out.println("=================================================================================================================");
        System.out.println("Order ID        Customer Name       Restaurant Name        Order Price        Order Type        Assigned Rider");
        System.out.println("_________________________________________________________________________________________________________________\n");
        for (Order o : a.getPastOrders()){
            System.out.println(o.getID() + "        " + o.getCusUsername() + "        " + o.getResName()+ "        " + o.getOrderPrice() + "        " + o.getOrderType() + "        " + o.getAssignedRider());
        }
        System.out.println("Enter anything to go back to previous menu");
        Scanner input = new Scanner(System.in);
        input.next();
        mainScreen(a);
    }
    public static void addNewRiderScreen(Admin a) throws IOException, InterruptedException{

        ArrayList<Rider> riderList = new ArrayList<Rider>();
        if (new File("Rider\\riderList.txt").exists()) {
            File riderListTxt = new File("Rider\\riderList.txt");
            Scanner riderListReader = new Scanner(riderListTxt);
            while (riderListReader.hasNext()){
                riderList.add(new Rider(riderListReader.nextLine()));
            }
            riderListReader.close();
        }

        Scanner input = new Scanner(System.in);
        
        System.out.println("========================================================================");
        System.out.println("Please enter the rider's information:  "); //(E.g. \"jack87 pass8362\") 
        System.out.println("========================================================================");
        System.out.println("Desired username: (E.g. \"ryab12\") ");
        String usernameAttempt = input.nextLine();
        for (Rider r : riderList) {
            if (r.getUsername() == usernameAttempt){
                System.out.println("Error: Username already taken. Please try a different one. ");
                addNewRiderScreen(a);
            }
        }

        System.out.println("Desired password: (E.g. \"pass1234\")"); 
        String passAttempt = input.nextLine();
        System.out.println("Rider name: (E.g. \"Ryan Letourneau\")");
        String nameAttempt = input.nextLine();
        System.out.println("Phone number: (E.g. \"+60214723898\")"); 
        String phoneAttempt = input.nextLine();

        //input.close();
        // System.out.println("Alternatively, enter \"BACK\" to go to the previous menu. ");
        Rider rid = new Rider(usernameAttempt, passAttempt, nameAttempt, phoneAttempt);
        riderList.add(rid);

        File ridDir = new File("Rider\\" + rid.getUsername());
        ridDir.mkdir(); // make the directory using the Rider username

        File ridOrderDir = new File("Rider\\" + rid.getUsername() + "\\Order\\");
        ridOrderDir.mkdir();

        
        File orderQueueTxt = new File("Rider\\orderQueue.txt");
        orderQueueTxt.createNewFile();

        File riderQueueTxt = new File("Rider\\riderQueue.txt");
        riderQueueTxt.createNewFile();
        FileWriter ridQueueFileWriter = new FileWriter(riderQueueTxt, true);
        PrintWriter ridQueuePrintWriter = new PrintWriter(ridQueueFileWriter);
        ridQueuePrintWriter.println(rid.getUsername());
        ridQueuePrintWriter.close();

        File riderListTxt = new File("Rider\\riderList.txt");
        riderListTxt.createNewFile();
        FileWriter ridListFileWriter = new FileWriter(riderListTxt, true);
        PrintWriter ridListPrintWriter = new PrintWriter(ridListFileWriter);
        ridListPrintWriter.println(rid.getUsername());
        ridListPrintWriter.close();

        System.out.println("Rider successfully created."); 
        Thread.sleep(2000);
        viewRidersScreen(a);
            
        
    }
    public static void manageRidersScreen1(Admin a) throws IOException, InterruptedException{
        ArrayList<Rider> riderList = new ArrayList<Rider>();
        if (new File("Rider\\riderList.txt").exists()) {
            File riderListTxt = new File("Rider\\riderList.txt");
            Scanner riderListReader = new Scanner(riderListTxt);
            while (riderListReader.hasNext()){
                riderList.add(new Rider(riderListReader.nextLine()));
            }
            riderListReader.close();
        }
        else {
            File riderNamesTxt = new File("Rider\\riderList.txt");
		    riderNamesTxt.createNewFile();
        }

        Collections.sort(riderList, Collections.reverseOrder());

        Scanner input = new Scanner(System.in);
        System.out.println("========================================================================");
        System.out.println("Please select a rider by entering the corresponding number: ");
        System.out.println("========================================================================");
        System.out.println("Rider name                 Username                 Password                 Phone                 Delivery count");
        System.out.println("_________________________________________________________________________________________________________________");
        int counter = 0;
        for (Rider rid : riderList) {                                                          ////// FIX FORMATTING HERE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            System.out.println(++counter + ") " + rid.getName() +"                 " + rid.getUsername() + "                 " + rid.getPassword() + "                 " + rid.getPhoneNum() + "                 " + rid.getDeliveryCount() );
        }
        System.out.println(++counter + ") Back");
        int choice = input.nextInt();

        //input.close();
        if (choice <= riderList.size() && choice > 0) {
            manageRidersScreen2(a, riderList.get(choice-1));
        }
        else if (choice == riderList.size()+1) {
            viewRidersScreen(a);
        }
    }
    public static void manageRidersScreen2(Admin a, Rider r) throws IOException, InterruptedException{
        Scanner input = new Scanner(System.in);

        System.out.println("========================================================================");
        System.out.println("Rider name: " + r.getName());
        System.out.println("Rider username: " + r.getUsername());
        System.out.println("Rider password: " + r.getPassword());
        System.out.println("Rider phone number: " + r.getPhoneNum());
        System.out.println("========================================================================");
        System.out.println("Please select an option: ");
        System.out.println("======================================================================== " + "\n\n");


        System.out.println("1) Edit name");
        System.out.println("2) Edit password");
        System.out.println("3) Edit phone");
        //System.out.println("4) Delete rider");
        System.out.println("4) Back");

        int choice = input.nextInt();

        //input.close();
        if (choice == 1) {
            editRiderNameScreen(a, r);
        }
        else if (choice == 2) {
            editRiderPasswordScreen(a, r);
        }
        else if (choice == 3) {
            editRiderPhoneScreen(a, r);
        }
        // else if (choice == 4) {
        //     deleteRiderScreen(a, r);
        // }
        else if (choice == 4) {
            viewRidersScreen(a);
        }

    }
    public static void editRiderNameScreen(Admin a, Rider r) throws IOException, InterruptedException{
        Scanner input = new Scanner(System.in);

        System.out.println("========================================================================");
        System.out.println("Previous name: " + r.getName());

        System.out.println("========================================================================");
        System.out.println("\nPlease enter the rider's new name: ");
        String newName = input.nextLine();

        //input.close();
        r.setName(newName);
        File riderInfo = new File("Rider\\"+ r.getUsername() + "\\basicInfo.txt");
        Order.replaceLines(riderInfo, newName, 3);

        manageRidersScreen2(a, r);
    }


    // public static void editRiderUsernameScreen(Admin a, Rider r) throws IOException, InterruptedException{

    // }


    public static void editRiderPasswordScreen(Admin a, Rider r) throws IOException, InterruptedException{
        Scanner input = new Scanner(System.in);

        System.out.println("========================================================================");
        System.out.println("Previous password: " + r.getPassword());

        System.out.println("========================================================================");
        System.out.println("\nPlease enter the rider's new password: ");
        String newPass = input.nextLine();

        //input.close();
        r.setPassword(newPass);
        File riderInfo = new File("Rider\\"+ r.getUsername() + "\\basicInfo.txt");
        Order.replaceLines(riderInfo, newPass, 2);

        manageRidersScreen2(a, r);
    }

    public static void editRiderPhoneScreen(Admin a, Rider r) throws IOException, InterruptedException{
        Scanner input = new Scanner(System.in);

        System.out.println("========================================================================");
        System.out.println("Previous phone number: " + r.getPhoneNum());
        System.out.println("========================================================================");
        System.out.println("\nPlease enter the rider's new phone number: ");
        String newPhone = input.nextLine();

        //input.close();
        r.setPhoneNum(newPhone);
        File riderInfo = new File("Rider\\"+ r.getUsername() + "\\basicInfo.txt");
        Order.replaceLines(riderInfo, newPhone, 4);

        manageRidersScreen2(a, r);
    }

    public static void deleteRiderScreen(Admin a, Rider r) throws IOException, InterruptedException{
        Scanner input = new Scanner(System.in);

        System.out.println("========================================================================");
        System.out.println("Are you sure you want to delete this rider? \n");
        System.out.println("Rider name: " + r.getName());
        System.out.println("Rider username: " + r.getUsername());
        System.out.println("Rider password: " + r.getPassword());
        System.out.println("Rider phone number: " + r.getPhoneNum());
        System.out.println("========================================================================\n\n");
        System.out.println("Please enter Y/N");
        char choice = input.next().toCharArray()[0];

        //input.close();
        if (choice == 'N') {
            manageRidersScreen2(a, r);
        }
        else if (choice == 'Y') {
            deleteStringFromFile(new File("Rider\\riderList.txt"), r.getUsername());
            deleteStringFromFile(new File("Rider\\riderQueue.txt"), r.getUsername());
            System.out.println(new File("Rider\\" + r.getUsername()).delete());
            manageRidersScreen1(a);
        }
    }
}
