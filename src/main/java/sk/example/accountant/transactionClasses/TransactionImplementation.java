package sk.example.accountant.transactionClasses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sk.example.accountant.core.BankAccount;
import sk.example.accountant.core.CreateAccountServiceImplementation;
import sk.example.accountant.model.Fees;
import sk.example.accountant.repository.FeeRepository;
import sk.example.accountant.repository.PersonAccount;
@Service
public class TransactionImplementation implements ITransactions {
double charge;
	@Autowired
	CreateAccountServiceImplementation repositorySrvice;
	
	@Autowired
	PersonAccount personRepositoryAccounts;
	
	@Autowired
	FeeRepository feeRepository;
public double getCharge() {
	return charge;
}
	private double getChargeValue(String reason) {
		Iterable<Fees> allFees = feeRepository.findAll();
//		double charge=0.0; 
		for (Fees fees : allFees) {
			System.out.println("'"+fees.getReasonName() + "' vs '"+ reason+"'");
			if (fees.getReasonName().equalsIgnoreCase(reason)) {
				charge =fees.getFixedFees();
				break;
			}
			else {	//defaul charge if not exist in table
				charge = 0;
			}
		}
		return charge;
	}
	
	@Override
	public void takeCashDispencerInMyBank(int value, long iban) {
		BankAccount loaded = repositorySrvice.findByIban(iban);
		//If credit is remains and going to take credit, is more or equal 5 Euro, is possible for make transaction
		if(loaded.getAccountBalance() >=5 && value >=5) {
			System.out.println(" previous: "+loaded.getAccountBalance());
			final String reason = "Withdraw_my_bank";
			
			Iterable<Fees> allFees = feeRepository.findAll();
			for (Fees fees : allFees) {
				System.out.println("'"+fees.getReasonName() + "' vs '"+ reason+"'");
				if (fees.getReasonName().equalsIgnoreCase(reason)) {
					int correctFee =fees.getFixedFees();
					System.err.println(value + "  money for minus, charge:"+correctFee );
					loaded.setAccountBalance((loaded.getAccountBalance()-value)-correctFee);
					System.out.println(" NOW:> "+loaded.getAccountBalance());
					personRepositoryAccounts.save(loaded);
					break;
				}
				else {	
				}
			}
			
		}
		else {
			System.out.println("Cannot perform transaction, non existing IBAN or bad credit :" +loaded.getAccountBalance());
		}
	}

	@Override
	public void takeCashDispencerInOtherBank(int value, long iban) {
		final String reason = "Withdraw_other_bank";
		BankAccount loaded = repositorySrvice.findByIban(iban);
		System.out.println(" previous: "+loaded.getAccountBalance());
		if(loaded.getAccountBalance() >=5 && value >=5) {
			// prepare transaction -> set current credit in account, set charge value
			Transaction transaction = new Transaction(getChargeValue(reason),loaded.getAccountBalance());
			
			//calculate credit with charge fee
			double newCredit =transaction.takeCashDispencerInOtherBank(value); // result is return new credit balance 
			// set this new credit as current credit in loaded BankAccount
	
			loaded.setAccountBalance(newCredit);
			personRepositoryAccounts.save(loaded);
			System.out.println(" NOW: "+loaded.getAccountBalance());
			System.err.println(value + "  money for minus, charge:"+getChargeValue(reason));
		}
	}
	
	/**
	 * double charge cover value which will be subtract
	 */

	@Override
	public void makeInvoicePayment(long iban) {

		final String reason = "invoice_payment";
		BankAccount account = repositorySrvice.findByIban(iban);
		if(account.getAccountBalance()!=null) {
			Transaction transaction = new Transaction(getChargeValue(reason),account.getAccountBalance());

			double newCredit = transaction.makeInvoicePayment();
			account.setAccountBalance(newCredit);
			personRepositoryAccounts.save(account);
			System.out.println("Success update credit on: " + account.getAccountBalance());
		}
		
	}

	@Override
	public void makeCreditTransaction(double value, long iban, long receiverIban) {
		final String reason = "Transaction_my_bANK";
		BankAccount senderAccount = repositorySrvice.findByIban(iban);
		BankAccount receiverAccount = repositorySrvice.findByIban(receiverIban);
		System.err.println("BEFORE "+senderAccount.getClient().getName() + ": " +senderAccount.getAccountBalance()+ ", to: " +receiverAccount.getClient().getName() + " " + receiverAccount.getAccountBalance());
		
		if(senderAccount.getAccountBalance() >0 && value >0 && value <= senderAccount.getAccountBalance()) {
			Transaction transaction = new Transaction(getChargeValue(reason),senderAccount.getAccountBalance());
			double result[] = transaction.makeBankTransaction(value);
			double newCreditSender = result[0];
			double creditForReceiverAccount = result [1];
			
			senderAccount.setAccountBalance(newCreditSender);
			personRepositoryAccounts.save(senderAccount);
			
			addCreditToReceiver(creditForReceiverAccount, receiverAccount);
			personRepositoryAccounts.save(receiverAccount);
			
			System.err.println(senderAccount.getClient().getName() + " send money: " +value+ ", to: " +receiverAccount.getClient().getName());
			System.err.println("AFTER "+senderAccount.getClient().getName() + ": " +senderAccount.getAccountBalance()+ ", to: " +receiverAccount.getClient().getName() + " " + receiverAccount.getAccountBalance());
			
		}
		}

	@Override
	public void addCreditToReceiver(double value, BankAccount receiverAccount) {

		if(receiverAccount.getAccountBalance()!=null && value>0) {
			Transaction transaction = new Transaction(0,receiverAccount.getAccountBalance());
			double newCreditReceiver = transaction.addCreditToAccount(value);
			receiverAccount.setAccountBalance(newCreditReceiver);
			System.out.println(receiverAccount.getClient().getName()+" have new balance" +receiverAccount.getAccountBalance());
		}
		else {
			System.err.println("Error in receiver account: " + receiverAccount.getIban());
		}
		
	}


		
	
	
}
