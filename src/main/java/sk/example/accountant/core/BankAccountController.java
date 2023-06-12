package sk.example.accountant.core;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestBody;

import sk.example.accountant.kafka.KafkaConsumer;
import sk.example.accountant.transactionClasses.ITransactions;



@RestController
@RequestMapping("/api") 
public class BankAccountController {
	@Autowired
	ICreateAccountService serviceMethods;
	
	@Autowired
	ITransactions transactionsService;
	
	
	@GetMapping("/all-clients")
	List<Client> allClients(){
		return serviceMethods.getAllClients();
	}
	
	@GetMapping("/all-accounts")
	List<BankAccount> allAccounts(){
		System.out.println(" All clients ** ");
		return serviceMethods.getAllAccounts();
	}
	
	@GetMapping("/client/{id}")
	Client getClientById(@PathVariable long id){
		return serviceMethods.findByPersonId(id);
	}
	
	@GetMapping("/iban/{id}")
	BankAccount getBankAccount(@PathVariable long id){
		return serviceMethods.findByIban(id);
	}
	@PutMapping("/take/{value}/{iban}")
	public BankAccount makeTransaction(@PathVariable long iban,@PathVariable int value){
		transactionsService.takeCashDispencerInMyBank(value, iban);
		return serviceMethods.findByIban(iban);
	}
	
	@PutMapping("/takemoney/{value}/{iban}")
	public BankAccount makeTransactionOtherBank(@PathVariable long iban,@PathVariable int value){
		transactionsService.takeCashDispencerInOtherBank(value, iban);
		return serviceMethods.findByIban(iban);
	}
	
	@PutMapping("/invoice/{iban}")
	public BankAccount performeInvoice(@PathVariable long iban){
		transactionsService.makeInvoicePayment( iban);
		 return serviceMethods.findByIban(iban);
	}

	
	 @PostMapping("/transaction")
	    public ResponseEntity<TransactionResponse> processTransaction(@RequestBody TransactionRequest request) {
	        long ibanSender = request.getIbanSender();
	        long ibanReceiver = request.getIbanReceiver();
	        double value = request.getValue();
	        System.out.println(ibanSender + " posiela=> " + ibanReceiver + " hodnotu: " + value);
	        transactionsService.makeCreditTransaction(value, ibanSender, ibanReceiver);
	        // Return an appropriate response
	       
	        String senderName = serviceMethods.findByIban(ibanSender).getClient().getName();
	        String receiverName = serviceMethods.findByIban(ibanReceiver).getClient().getName();
	        double senderAccountBalance = serviceMethods.findByIban(ibanSender).getAccountBalance();
	        double receiverAccountBalance = serviceMethods.findByIban(ibanReceiver).getAccountBalance();
	        double charge = transactionsService.getCharge();
	        
	        TransactionResponse response = new TransactionResponse(senderName, receiverName, value, senderAccountBalance, receiverAccountBalance,charge);

	        // Return the response in JSON format with 200 OK status
	        return ResponseEntity.ok(response);
	    }
	 
	 
	 
	 public static class TransactionRequest {
	        private long ibanSender;
	        private long ibanReceiver;
	        private double value;

	        public long getIbanSender() {
	            return ibanSender;
	        }

	        public void setIbanSender(long ibanSender) {
	            this.ibanSender = ibanSender;
	        }

	        public long getIbanReceiver() {
	            return ibanReceiver;
	        }

	        public void setIbanReceiver(long ibanReceiver) {
	            this.ibanReceiver = ibanReceiver;
	        }

	        public double getValue() {
	            return value;
	        }

	        public void setValue(double value) {
	            this.value = value;
	        }
	    }

	 
}
