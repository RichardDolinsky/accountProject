package sk.example.accountant.transactionClasses;

import sk.example.accountant.core.BankAccount;

public interface ITransactions {
	void takeCashDispencerInMyBank(int value, long iban);
	
	void takeCashDispencerInOtherBank(int value, long iban);
	
	void makeInvoicePayment (long iban);
	
	void makeCreditTransaction(double value,long iban, long receiverIban);
	
	void addCreditToReceiver(double value, BankAccount receiver);
	
	double getCharge();

}
