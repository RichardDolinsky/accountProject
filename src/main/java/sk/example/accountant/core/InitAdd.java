package sk.example.accountant.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import sk.example.accountant.repository.FeeRepository;
@Component
public class InitAdd implements ApplicationListener<ContextRefreshedEvent>
{
	
//	This class is going to use for creating initial objects in database, 3 persons is create
	
	@Autowired
	ICreateAccountService createAccount;
	
	@Autowired
	FeeRepository feeRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		createAccount.createAccount("Marian", 88L, 1400.50,111888888L);
		createAccount.createAccount("Lukas", 33L, 189.00,444433333L);
		createAccount.createAccount("Lenka", 22L, 8500.89,100005555L);

		System.out.println(" INIT ADD CLass");
		final int ATM_withdrawal_myBank = 0;
		final int ATM_withdrawal_otherBank = 5; // 5Euro pear withdraw
		final int transaction_myBank = 1;  // 1 Euro per transaction within same bank
		final int transaction_otherBank = 2; //2 Euro per transaction in other bank
		final int invoice_payment=50;
		/**
		 * Catch Exception if someone want to add entity (row) into table FEES which is exit already
		 * Only Unique are allow!
		 */
		  try {
			  
				createAccount.createFee("Withdraw_my_bank", ATM_withdrawal_myBank);
				createAccount.createFee("Withdraw_other_bank", ATM_withdrawal_otherBank);
				createAccount.createFee("Transaction_my_bANK", transaction_myBank);
				createAccount.createFee("Transaction_other_bank", transaction_otherBank);
				createAccount.createFee("Invoice_payment", invoice_payment);
				//test exception
//				createAccount.createFee("Transaction_other_bank", 99999);

		    } catch (DataIntegrityViolationException e) {
		        String errorMessage = "Duplicate entity found. Please provide a unique entity.";
		        System.err.println("ERROR + " +errorMessage);
		    }

	}



}
