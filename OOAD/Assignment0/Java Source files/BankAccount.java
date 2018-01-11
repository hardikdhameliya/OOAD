import com.sun.org.apache.xpath.internal.operations.Bool;

public class BankAccount {

	private int accNum;
	private int pinNum;
	private double savingBal;
	private double checkingBal;
	private String accName;
	private boolean hasCheckingAcc;
	
	
/* Constructor for the bank account which has both checking and saving account */
	public BankAccount(int accNum, int pinNum, double savingBal, double checkingBal, String accName, boolean accType) {
		this.accNum = accNum;
		this.pinNum = pinNum;
		this.savingBal = savingBal;
		this.checkingBal = checkingBal;
		this.accName = accName;
		this.hasCheckingAcc = accType;
	}
	
/* Constructor for the bank account which has only saving account */
	public BankAccount(int accNum, int pinNum, double savingBal, String accName, boolean accType) {
		this.accNum = accNum;
		this.pinNum = pinNum;
		this.savingBal = savingBal;
		this.accName = accName;
		this.hasCheckingAcc = accType;
	}

/* Returns account number */
	public int getAccNum() {
		return accNum;
	}

/* Set account number */	
	public void setAccNum(int accNum) {
		this.accNum = accNum;
	}

/* Returns account pin number */
	public int getPinNum() {
		return pinNum;
	}

/* Set account pin number */
	public void setPinNum(int pinNum) {
		this.pinNum = pinNum;
	}
	
/* Returns saving account balance */
	public double getSavingBal() {
		return savingBal;
	}

/* Set saving account balance */	
	public void setSavingBal(double savingBal) {
		this.savingBal = savingBal;
	}
	
/* Returns checking account balance */	
	public double getCheckingBal() {
		return checkingBal;
	}

/* Set checking account balance */
	public void setCheckingBal(double checkingBal) {
		this.checkingBal = checkingBal;
	}
	
/* Returns account name */
	public String getAccName() {
		return accName;
	}

/* Set account name */	
	public void setAccName(String accName) {
		this.accName = accName;
	}

/* Returns weather account has checking account or not */	
	public boolean getAccType() {
		return hasCheckingAcc;
	}
	
/* Set boolean value indicating type of account. If account has checking account then its value will be true
 * otherwise false.
 */
	public void setAccType(boolean accType) {
		this.hasCheckingAcc = accType;
	}
	
/* Returns boolean value indicating weather bank account has checking account or not*/
	public boolean hascheckingAccount(){
		return hasCheckingAcc;
	}
	
/* verifying pin of bank account */
	public boolean pinVarify(int pinNum){
		
		if(this.pinNum==pinNum){
			return true;
		}else
			return false;
	}

/* Returns string value with csv specification of account */
	public String csvSpecification() {
	
		if(this.hasCheckingAcc){
		
			return accNum + "," + pinNum +"," + savingBal + ","+ checkingBal + "," + accName;
				
		}else{
		
			return accNum + "," + pinNum +"," + savingBal + ","+ accName;
		}
	}
	
	
}
