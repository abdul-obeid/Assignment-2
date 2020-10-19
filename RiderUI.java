import java.util.*;
import java.io.*;
public class RiderUI{
	public static void  main(String[] args){
		File allRidersDir = new File("Rider");
		allRidersDir.mkdir();
		Rider r = new Rider("saber01","sub009","Saber","0113724413");
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
			Rider r = new Rider(username);
		
			if(r.validateLogin(username, password)){
				// clearScreen();
				System.out.println("\t\t\t _                _         ____                               __       _ _ ");
				System.out.println("\t\t\t| |    ___   __ _(_)_ __   / ___| _   _  ___ ___ ___  ___ ___ / _|_   _| | |");
				System.out.println("\t\t\t| |   / _ \\ / _` | | '_ \\  \\___ \\| | | |/ __/ __/ _ \\/ __/ __| |_| | | | | |");
				System.out.println("\t\t\t| |__| (_) | (_| | | | | |  ___) | |_| | (_| (_|  __/\\__ \\__ \\  _| |_| | |_|");
				System.out.println("\t\t\t|_____\\___/ \\__, |_|_| |_| |____/ \\__,_|\\___\\___\\___||___/___/_|  \\__,_|_(_)");
				System.out.println("\t\t\t            |___/                                                           ");
				riderDashboard(r);
			}
			else{
				System.out.print("Username or Password is incorrect, press Enter to try again");
				scan.nextLine();
			}
		
		}
		catch(IOException ex){
			System.out.println("Username not found");
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
				checkCurrentOrder(rider);
			}
			else if(dashCohice == 2){
				System.out.println(rider.getQueuePos());
			}
			else if(dashCohice == 3){
				
			}
			else if(dashCohice == 4){
				break;
			}
		}while(true);
	}	
	public static void checkCurrentOrder(Rider rider){
		Scanner scan = new Scanner(System.in);
		if(rider.getCurrentOrder() == null)
			System.out.println("You have no order assigned to you right now!");
		else{
			System.out.println("+----------------------------------------------------+");
			System.out.println("|\tOrder ID: " + rider.getCurrentOrder().getID());
			System.out.println("|\tCustomer Username: " + rider.getCurrentOrder().getCusUsername());
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
			if(choosenAction == 1){
				rider.deliverCurrentOrder();
			}
		}
	}
}