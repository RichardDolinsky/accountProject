package sk.example.accountant.core;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import sk.example.accountant.model.Fees;
import sk.example.accountant.repository.ClientRepo;
import sk.example.accountant.repository.FeeRepository;
import sk.example.accountant.repository.PersonAccount;
import sk.example.accountant.transactionClasses.BeanTransaction;

@Service
public class CreateAccountServiceImplementation implements ICreateAccountService{
//create repository instance
	@Autowired
	PersonAccount personAccount;
	@Autowired
	ClientRepo clientRepo;
	@Autowired
	FeeRepository feeRepository;
	
	@Autowired
	private BeanTransaction beanTransaction;
//    @Qualifier("performCashDispencerInMyBank")
//		private long cashDispenser;
    
	
	@Override
	public BankAccount createAccount(String name, Long personId, Double accountBalance,long iban) {
		Client client = new Client(name, personId);
	
		BankAccount account = new BankAccount(accountBalance, client,iban);
//		client.setBankAccount(account);
		clientRepo.save(client);
		return personAccount.save(account);
	}

	@Override
	public List<Client> getAllClients() {
		System.out.println(" ------------- All Clients ---------");
		List<Client> clients = clientRepo.findAll();
        System.out.println(clients);
        return clients;
	}

	@Override
	public List<BankAccount> getAllAccounts() {
		return personAccount.findAll();
	}

	@Override
	public Client findByPersonId(long id) {
		Optional<Client> potencialClient = clientRepo.findById(id);
		
		if(potencialClient.isPresent()) {
			return potencialClient.get();
		}
		else {
			return null;
		}
	}

	@Override
	public BankAccount findByIban(long iban) {
//		personAccount.findAllById(null)
		Iterable<BankAccount> clientRepoList = personAccount.findAll();
		for (BankAccount account : clientRepoList) {
			if(account.getIban()==iban) {
				return account;
			}
		}
//		return new BankAccount(-1.0, new Client("Non-Exist",-1L), -9L);
		return null;
	}



	@Override
	public Fees createFee(String nameOfFee, int valueFee) {
		Fees fee = new Fees(); 
		fee.setFixedFees(valueFee);
		fee.setReasonName(nameOfFee);
		return feeRepository.save(fee);
	}

}
