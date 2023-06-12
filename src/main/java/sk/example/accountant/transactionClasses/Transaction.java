package sk.example.accountant.transactionClasses;

public class Transaction {
	private double charge;
	private double currentCredit;
	
	/**
	 * Constructor for create new transaction and set values:
	 * @param charge , loaded from Database as charge fee
	 * @param currentCredit , set current credit as its upcoming transaction
	 */
	public Transaction(double charge, double currentCredit) {
		this.charge = charge;
		this.currentCredit = currentCredit;
	}
	
	/**
	 * Method for take cash in Cash- Dispenser machine
	 * @param value, value for subtract from current credit set is constructor
	 * @return new current credit
	 */
	public double takeCashDispencerInMyBank(int value) {
		if(value>=5 && currentCredit>=5 && currentCredit >= value) {
			return ((currentCredit-value)-charge);
		}
		else return currentCredit;
	}
	
	public double takeCashDispencerInOtherBank(int value) {
		if(value>=5 && currentCredit>=5 && currentCredit >= value) {
			return ((currentCredit-value)-charge);
		}
		else return currentCredit;
	}
	
	/**
	 * Perform payment with possible debet credit as force transaction
	 * @param value give value which will be subtract
	 * @return new calculated credit after subtract
	 */
	public double makeInvoicePayment() {

			return ((currentCredit)-charge);

	}
	/**
	 * Perform bank transaction which subtract credit
	 * @param value subtract value from credit
	 * 
	 * @return double[] , first position is newCurrentCredit, second is send credit value to receiver]
	 */
	public double[] makeBankTransaction(double value) {
		double[] resultArray = new double[2];
	
		if(value>0 && value <=currentCredit) {
			resultArray[0]=((currentCredit-value)-charge);
			resultArray[1]=value;
		}
		else {
			resultArray[0] = currentCredit;
		}
		return resultArray;
	}
	
	public double addCreditToAccount(double value) {
		System.out.println(currentCredit + " TRan before");
		return currentCredit = currentCredit+value;
	}
	
	public double getCharge() {
		return charge;
	}
}


