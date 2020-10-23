import java.util.*;
import java.io.*;
public class RiderUI{
	public static void  main(String[] args){
		File allRidersDir = new File("Rider");
		allRidersDir.mkdir();
		loginScreen();
	}
	static void loginScreen(){
		Scanner scan = new Scanner(System.in);
		// clearScreen();
		System.out.println();
		System.out.println();

		System.out.println("\t _                _       ");
		System.out.println("\t| |    ___   __ _(_)_ __  ");
		System.out.println("\t| |   / _ \\ / _` | | '_ \\ ");
		System.out.println("\t| |__| (_) | (_| | | | | |");
		System.out.println("\t|_____\\___/ \\__, |_|_| |_|");
		System.out.println("\t            |___/         ");
		System.out.println("\n\t Enter your username and password");
		System.out.print("\t Username: ");
		String username = scan.nextLine();
		System.out.print("\t Password: ");
		String password = scan.nextLine();
		try{
			Rider r = new Rider(username); // construct a Rider object using the username, it will read the rest of info from files
			if(r.validateLogin(username, password)){ // if username and password in files matche the enterd ones
				// clearScreen();
				System.out.println("\t\t\t _                _         ____                               __       _ _ ");
				System.out.println("\t\t\t| |    ___   __ _(_)_ __   / ___| _   _  ___ ___ ___  ___ ___ / _|_   _| | |");
				System.out.println("\t\t\t| |   / _ \\ / _` | | '_ \\  \\___ \\| | | |/ __/ __/ _ \\/ __/ __| |_| | | | | |");
				System.out.println("\t\t\t| |__| (_) | (_| | | | | |  ___) | |_| | (_| (_|  __/\\__ \\__ \\  _| |_| | |_|");
				System.out.println("\t\t\t|_____\\___/ \\__, |_|_| |_| |____/ \\__,_|\\___\\___\\___||___/___/_|  \\__,_|_(_)");
				System.out.println("\t\t\t            |___/                                                           ");
				riderDashboard(r); // after logging in successfully, move to the dashboard
			}
			else{
				System.out.print("Username or Password is incorrect, press Enter to try again");
				scan.nextLine();
			}
		}
		catch(IOException ex){
			System.out.println("Username not found"); // since the file not found, then there is no rider with the entered username
		}
	}
	
	
	static void riderDashboard(Rider rider){
		Scanner scan = new Scanner(System.in);
		do{
			// clearScreen();
			System.out.println("\n\t\t\t\t   +-----------------------------------------------------+");
			System.out.println("\t\t\t\t   |\t\t\t\t\t\t\t |");
			System.out.println("\t\t\t\t   |  \t\t " +"   "+  " Rider Dashboard \t\t\t |");
			System.out.println("\t\t\t\t   |\t\t\t\t\t\t\t |");
			System.out.println("\t\t\t\t   +-----------------------------------------------------+");
			
			System.out.println("\t\t\t\t   |      1) Check current order \t\t\t |");
			System.out.println("\t\t\t\t   |      2) Check queue position \t\t\t |");
			System.out.println("\t\t\t\t   |      3) Order history \t\t\t\t |");
			System.out.println("\t\t\t\t   |      4) Back to login \t\t\t\t |");
			System.out.print("\n\t\t\t\t    >> Enter number of the needed function: ");
			int dashCohice = scan.nextInt();
			if(dashCohice == 1){
				checkCurrentOrder(rider); // read the current order for rider
			}
			else if(dashCohice == 2){
				if(rider.getQueuePos() < 0) // if the method returns -1, that means the rider is already has an order to deliver
					System.out.println("\n\t\t\t You aren't in the rider queue in the mean time, deliver your current order to enter it again.");
				else if(rider.getQueuePos() == 0)// if the method returns 0, that means the rider is first in line
					System.out.println("\n\t\t\t\t You are first in line! please stay tuned, you will receive the very next order!");
				else{
					System.out.println("\n\t\t\t\t there is " + rider.getQueuePos() + " rider/s in front of you, please wait" );
				}
			}
			else if(dashCohice == 3){ // show a brief about orders deliverd by this driver
				for(int i = 0; i < rider.getPastOrders().size(); ++i){
					System.out.println(rider.getPastOrders().get(i) + ", Address: " + rider.getPastOrders().get(i).getDelAddress());
				}
				System.out.println("Press Enter to go back");
				scan.nextLine();
				scan.nextLine();
			}
			else if(dashCohice == 4){ // go back to login
				break;
			}
		}while(true);
	}	
	public static void checkCurrentOrder(Rider rider){ // show current order in a proper way with option to set it as deliverd
		Scanner scan = new Scanner(System.in);
		if(rider.getCurrentOrder() == null)
			System.out.println("You have no order assigned to you right now!");
		else{
			System.out.println("+----------------------------------------------------+");
			System.out.println("|\tOrder ID: " + rider.getCurrentOrder().getID());
			System.out.println("|\tCustomer Username: " + rider.getCurrentOrder().getCusUsername());
			System.out.println("|\tDelivery Address: " + rider.getCurrentOrder().getDelAddress());
			System.out.println("|\tTime Requested: " + rider.getCurrentOrder().getTimeRequested());
			System.out.println("|\tOrder Status: " + rider.getCurrentOrder().getOrderStatus());
			System.out.println("|\tOrder Contents: " );
			for(int i = 0; i < rider.getCurrentOrder().getOrderContents().size(); ++i){
				System.out.println("|\t\tItem #" + (i+1) + " Name: " + rider.getCurrentOrder().getOrderContents().get(i).getItemName());
				System.out.println("|\t\tItem ID: " + rider.getCurrentOrder().getOrderContents().get(i).getItemID());
				System.out.println("|\t\tItem Price: " + rider.getCurrentOrder().getOrderContents().get(i).getItemPrice());
				System.out.println("|");
			}
			System.out.println("|\tTotal Price: RM" + rider.getCurrentOrder().getOrderPrice());
			System.out.println("|\n|\t1) Set order as deliverd ");
			System.out.println("|\t2) Back to Rider Dashboard ");
			System.out.print("|\t>>");
			int choosenAction = scan.nextInt();
			if(choosenAction == 1){ // sets the order status as deliverd in restaurant and customer files, and makes a file for the order in rider history
				rider.deliverCurrentOrder();
			}
		}
	}
}