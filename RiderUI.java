import java.util.*;
import java.io.*;
public class RiderUI{
	public static void  main(String[] args){
		File allRidersDir = new File("Rider");
		allRidersDir.mkdir();
		Rider r = new Rider("saber01","sub009","Saber","0113724413");
		// loginScreen();
		
	}
	
		
		
		
		
		
		
		
		
		
		
		
		
		// static void loginScreen(int restChoice, Restaurant[] loginRestaurant){
		// Scanner scan = new Scanner(System.in);
		// clearScreen();
		// System.out.println();
		// System.out.println();

		// System.out.println("\t _                _       ");
		// System.out.println("\t| |    ___   __ _(_)_ __  ");
		// System.out.println("\t| |   / _ \\ / _` | | '_ \\ ");
		// System.out.println("\t| |__| (_) | (_| | | | | |");
		// System.out.println("\t|_____\\___/ \\__, |_|_| |_|");
		// System.out.println("\t            |___/         ");
		// System.out.println("\n\t Enter your username and password");
		// System.out.print("\t Username: ");
		// String username = scan.nextLine();
		// System.out.print("\t Password: ");
		// String password = scan.nextLine();
		
		// if(loginRestaurant[restChoice -1].validateLogin(username, password)){
			// clearScreen();
			// System.out.println("\t\t\t _                _         ____                               __       _ _ ");
			// System.out.println("\t\t\t| |    ___   __ _(_)_ __   / ___| _   _  ___ ___ ___  ___ ___ / _|_   _| | |");
			// System.out.println("\t\t\t| |   / _ \\ / _` | | '_ \\  \\___ \\| | | |/ __/ __/ _ \\/ __/ __| |_| | | | | |");
			// System.out.println("\t\t\t| |__| (_) | (_| | | | | |  ___) | |_| | (_| (_|  __/\\__ \\__ \\  _| |_| | |_|");
			// System.out.println("\t\t\t|_____\\___/ \\__, |_|_| |_| |____/ \\__,_|\\___\\___\\___||___/___/_|  \\__,_|_(_)");
			// System.out.println("\t\t\t            |___/                                                           ");
			// loadingWait();
			// resturantDashboard(loginRestaurant[restChoice -1]);
		// }
		// else{
			// System.out.print("Username or Password is incorrect, (type back to select restaurant or else to try again): ");
			// String editChoice = scan.nextLine();
			// if(editChoice.equals("back"))
				// selectRestaurant(loginRestaurant);
			// else
				// loginScreen(restChoice, loginRestaurant);
		// }
		
		
	// }
	
}