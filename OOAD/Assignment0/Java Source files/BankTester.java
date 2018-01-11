import java.util.Scanner;
public class BankTester {

	public static void main(String[] args) {

		Bank myBank = new Bank();
		int option;
		Scanner optionscan = new Scanner(System.in);
		
		/* Prompt user with 2 options for selection of login or save and quit  */
		System.out.println("Options:");
		System.out.println("1) Login");
		System.out.println("2) Save and Quit");
		System.out.print("Choice:");
		option = optionscan.nextInt();
		
		/* Option 1 is to login in to bank account */
		while(option==1){
			
			myBank.login();
			
			System.out.println("Options:");
			System.out.println("1) Login");
			System.out.println("2) Save and Quit");
			System.out.print("Choice:");
			option = optionscan.nextInt();
		
		/*option 2 is to update data and save in to database file */
			if(option==2){
				myBank.writeToDBSpecs();
			}

		}
		optionscan.close();
	}

}
