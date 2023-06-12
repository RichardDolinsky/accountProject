package sk.example.accountant.core;

import java.util.List;

import sk.example.accountant.model.Fees;

public interface ICreateAccountService {
	public BankAccount createAccount(String name, Long personId,Double accountBalance, long iban);
	public List<Client> getAllClients();
	public List <BankAccount> getAllAccounts();
	public Client findByPersonId(long id);
	public BankAccount findByIban(long iban);
	public Fees createFee(String nameOfFee,int valueFee);
	public void makeTransaction(Boolean cash,Boolean iban,Boolean sameBank,long value,long loggedUserID);

}
