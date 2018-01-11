import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;

import javafx.scene.chart.PieChart.Data;
import sun.applet.Main;

public class Bank {

	ArrayList<BankAccount> acc = new ArrayList<BankAccount>();

	final String dbfile = "bankDB.csv";

/* constructor initiate readDBspecs to read the database file and create bank accounts and store */
	public Bank() {
		readDBspecs();
	}

/* login method asks user for bank account number, pin number. If pin number is incorrect, will allow 
 * user to try for 3 attempts and greet user by their name. 
 */
	
	public void login() {

		Scanner scan = new Scanner(System.in);
		int accNum, accIndex;
		int count = 0;
		int pass = 0;
		String input;

		System.out.print("Enter in Bank Account Number:");
		accNum = scan.nextInt();
		accIndex = accNum - 1;
		BankAccount b = acc.get(accIndex);
		if (b.getAccNum() == accNum) {
			while (count < 3) {
				System.out.print("Enter in Pin:");
				pass = scan.nextInt();
				count++;
				if (b.getPinNum() == pass) {

					input = "Hello " + b.getAccName() + " which account would you like to access:";
					System.out.println(input);
					menuSpecification(accIndex);
					break;

				}else {
					System.out.println("Wrong password!");
					System.out.println("try again");
				}
			}
			if (count == 3){
				System.out.println("Attempt reach to maximum limit, see you again!");
			}
			
		} else {
			System.out.println("account doesn't exist");
		}

	}

/* This method prompted user to deposit or withdraw money. Updated balance shows and control trasfer back to
 * BankTester class.
 * 
 */
	private void depositOrWithdraw(int accIndex, int option) {

		double addedbalance;
		String newbalanceinfo;
		BankAccount b = acc.get(accIndex);
		System.out.println("How would you like to proceed?");
		System.out.println("(Enter in positive number to deposit, negative number to withdraw)");
		System.out.print("Amount:");
		Scanner optionsc = new Scanner(System.in);
		addedbalance = optionsc.nextDouble();

		if (option == 1) {
			b.setSavingBal(addedbalance + b.getSavingBal());
			newbalanceinfo = "Thank you, your new Saving balance is " + b.getSavingBal();
		} else {
			b.setCheckingBal(addedbalance + b.getCheckingBal());
			newbalanceinfo = "Thank you, your new Checkings balance is " + b.getCheckingBal();
		}
		System.out.println(newbalanceinfo);
		System.out.println("Returning to login screen");
	}

	
/* This method provides menu for user to navigate between different options. If user has checking account
 * then it prompted user to enter which account should be accessed. Otherwise, it shows  saving account 
 * details and proceed for depositOrWithdraw method. 
 */
	private void menuSpecification(int accIndex) {

		int option;
		String balanceinfo;
		BankAccount b = acc.get(accIndex);
		Scanner sc = new Scanner(System.in);

		if (b.hascheckingAccount()) {
			System.out.println("1) Checkings");
			System.out.println("2) Savings");
			System.out.print("Choice:");
			option = sc.nextInt();
		} else
			option = 1;

		switch (option) {
		case 1:
			balanceinfo = "Your Saving account has a balance of " + b.getSavingBal();
			System.out.println(balanceinfo);
			break;

		default:
			balanceinfo = "Your Checking account has a balance of " + b.getCheckingBal();
			System.out.println(balanceinfo);
			break;
		}

		depositOrWithdraw(accIndex, option);
	}

/* This method read the database file, create accounts and store them into bankaccounts list. */
	private void readDBspecs() {

		BufferedReader br = null;
		String line = "";
		String csvsplitby = ",";

		try {
			br = new BufferedReader(new FileReader(dbfile));
		} catch (IOException e) {
			// Exception in case file not found, just carry on..
		}

		try {
			while ((line = br.readLine()) != null) {

				int accNum;
				int pinNum;
				double savingBal;
				double checkingBal = 0;
				String accName;
				boolean hasCheckingAcc;
				int index = 0;
				String[] country = line.split(csvsplitby);
				accNum = Integer.parseInt(country[index++]);
				pinNum = Integer.parseInt(country[index++]);
				savingBal = Double.parseDouble(country[index++]);
				try {
					checkingBal = Double.parseDouble(country[index++]);
					hasCheckingAcc = true;
				} catch (NumberFormatException e) {
					hasCheckingAcc = false;
					index--;
				}
				accName = country[index++];

				if (hasCheckingAcc) {

					acc.add(new BankAccount(accNum, pinNum, savingBal, checkingBal, accName, hasCheckingAcc));

				}else{
					acc.add(new BankAccount(accNum, pinNum, savingBal, accName, hasCheckingAcc));
				}
			}
		}catch (IOException e) {
			e.printStackTrace();
		}

	}

/*This method write updated data back to database file. This method only invoked when user selects the 
 * save and quit option.
 * 
 */
	public void writeToDBSpecs() {
		FileWriter fwOb = null;
		try {
			fwOb = new FileWriter(dbfile, false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		PrintWriter pwOb = new PrintWriter(fwOb, false);
		pwOb.flush();

		StringBuilder sb = new StringBuilder();

		for (BankAccount bankAccount : acc) {
			sb.append(bankAccount.csvSpecification());
			sb.append("\n");
		}
		
		pwOb.write(sb.toString());
		pwOb.close();
		try {
			fwOb.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
