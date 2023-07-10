package sk.example.accountant.core;

import java.text.NumberFormat;
import java.text.ParseException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import sk.example.accountant.nbspojo.nbsFolder.ImplementationNbs;
import sk.example.accountant.nbspojo.nbsFolder.NbsPojo;
//import sk.example.accountant.kafka.KafkaConsumer;
import sk.example.accountant.transactionClasses.ITransactions;

@RestController
@RequestMapping("/api")
//@RequestMapping(value = {"/api", "/api/"})
public class BankAccountController {
	@Autowired
	ICreateAccountService serviceMethods;

	@Autowired
	ITransactions transactionsService;

//	Constructor dependency injection
	ImplementationNbs nbsService;

	BankAccountController(ImplementationNbs nbsService) {
		this.nbsService = nbsService;
	}

	@GetMapping("/all-clients")
	List<Client> allClients() {
		return serviceMethods.getAllClients();
	}

	@GetMapping("/all-accounts")
	List<BankAccount> allAccounts() {
		System.out.println(" All clients ** ");
		return serviceMethods.getAllAccounts();
	}

	@GetMapping("/client/{id}")
	Client getClientById(@PathVariable long id) {
		return serviceMethods.findByPersonId(id);
	}

	@GetMapping("/clientv2/{id}")
	ResponseEntity<?> getClientByIdv2(@PathVariable long id) {

		Client client = serviceMethods.findByPersonId(id);
		Map<String, Object> responseMap = new HashMap<>();
		if (client != null) {
			return ResponseEntity.ok(client);
		} else if (id < 0) {
//			responseList.put("Incorrect id value", HttpStatus.BAD_REQUEST.value());
			responseMap.put("statusCode", HttpStatus.BAD_REQUEST.value());
			responseMap.put("message", "Incorrect id value");
			ResponseEntity<?> res = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMap);
			return res;
		} else {
			responseMap.put("statusCode", HttpStatus.NOT_FOUND.value());
			responseMap.put("message", "Person with id:" + id + " was not found!");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMap);
		}
	}

	@GetMapping("/currency/{lte}")
	String getCurrency(@PathVariable String lte) {
		String currentDate = ImplementationNbs.getCurrentDate();
		NbsPojo myPojo = nbsService.getPojoExchangeRate(currentDate);
		return nbsService.getCurrency(myPojo, lte);
	}

	@PutMapping("/currency/{value}/{intoLte}")
	ResponseEntity<?> fromEuroToCurrency(@PathVariable String intoLte, @PathVariable String value) {
		String currentDate = ImplementationNbs.getCurrentDate();
		NbsPojo myPojo = nbsService.getPojoExchangeRate(currentDate);
		// get currency rate from API call NBS bank web application
		String currencyRateString = nbsService.getCurrency(myPojo, intoLte);

		NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
		try {
			// parse value from String into Number
			Number exchangeRate = numberFormat.parse(currencyRateString);
			Number enumericValue = numberFormat.parse(value);

			Number resultRate = calculateExchangeToCurrency(enumericValue, exchangeRate);
			ResponseExchangeRate response = new ResponseExchangeRate("Euro", intoLte, enumericValue, resultRate);
			if ((Double) response.getExchangedValue() > 0) {
				System.out.println(resultRate + " result");
				return ResponseEntity.ok(response);
			}
			else {
				throw new ResponseMessage(HttpStatus.NOT_FOUND.value(), "Input field LTE was not in correct state");
			}
		} catch (ResponseMessage e) {
			System.out.println("lte error");
			return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(e);
		} catch (ParseException e) {
			System.out.println("Parsing error: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(
					HttpStatus.BAD_REQUEST.value(), "Invalid value format for exchange rate, or input value"));
		}
	}

	private Number calculateExchangeToCurrency(Number value, Number exchangeRate) {
		System.out.println(value + " * " + exchangeRate);
		double result = value.doubleValue() * exchangeRate.doubleValue();
		return (Number) result;
	}

	@GetMapping("/iban/{id}")
	ResponseEntity<?> getBankAccount(@PathVariable long id) {
		int responseStatus = 0;
		try {
			BankAccount account = serviceMethods.findByIban(id);
			if (account != null) {
				return ResponseEntity.ok(account);
			} else if (id < 0 || (Long) id == null) {
				responseStatus = HttpStatus.BAD_REQUEST.value();
				throw new ResponseMessage(responseStatus, "Incorrect id");
			} else {
				responseStatus = HttpStatus.NOT_FOUND.value();
				throw new ResponseMessage(responseStatus, "Account with IBAN:" + id + " was not found!");
			}
//		return serviceMethods.findByIban(id);
		} catch (ResponseMessage ex) {
			return ResponseEntity.status(responseStatus).body(ex);
		}
	}

	@PutMapping("/take/{value}/{iban}")
	public ResponseEntity<?> makeTransaction(@PathVariable long iban, @PathVariable int value) {
		try {
			boolean transaction = transactionsService.takeCashDispencerInMyBank(value, iban);
			if (transaction) {
				return ResponseEntity.ok(serviceMethods.findByIban(iban));

			} else {
				throw new ResponseMessage(HttpStatus.NOT_FOUND.value(),
						"Account with IBAN:" + iban + " was not found! or transation credit is exceed");
			}

		} catch (ResponseMessage ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(ex);
		}
	}

	@PutMapping("/takemoney/{value}/{iban}")
	public BankAccount makeTransactionOtherBank(@PathVariable long iban, @PathVariable int value) {
		transactionsService.takeCashDispencerInOtherBank(value, iban);
		return serviceMethods.findByIban(iban);
	}

	@PutMapping("/invoice/{iban}")
	public BankAccount performeInvoice(@PathVariable long iban) {
		transactionsService.makeInvoicePayment(iban);
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

		TransactionResponse response = new TransactionResponse(senderName, receiverName, value, senderAccountBalance,
				receiverAccountBalance, charge);

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
