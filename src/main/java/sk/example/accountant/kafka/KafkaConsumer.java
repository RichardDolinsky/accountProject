package sk.example.accountant.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.PathVariable;

import sk.example.accountant.transactionClasses.ITransactions;
@SuppressWarnings("serial")
@Component
public class KafkaConsumer extends RuntimeException {
	public KafkaConsumer() {
	    // Default constructor logic
	}
	
	   public KafkaConsumer(String message) {
	        super(message);
	    }

	    public KafkaConsumer(String message, Throwable cause) {
	        super(message, cause);
	    }
	

//    @KafkaListener(topics = "your-topic-name")
	@KafkaListener(topics = "your-topic-name", groupId = "my-consumer-group")
    public void consumeMessage(String message) {
        // Process the received message
    	System.err.println(" *** ---  **** receive");
    	System.out.println(" SPrava out");
    }
//	@Autowired
//	private KafkaTemplate<String, String> kafkaTemplate;
	@Autowired
	ITransactions transactionsService;
	
	@KafkaListener(topics = "takemoney", groupId = "my-consumer-group")
	public void takeMoneyInMyBank(String message)  {
		try {
		String[] parts = message.split(",");
		 long iban = Long.parseLong(parts[1].trim());
	        int value = Integer.parseInt(parts[0].trim());
		System.err.println(iban + " " + value);
//		System.err.println(message);
		transactionsService.takeCashDispencerInMyBank(value, iban);
		} catch (Exception e) {
	        throw new KafkaConsumer("Invalid input [value(Euro),IBAN] expeted", e);
	    }
	}
	@KafkaListener(topics = "invoice", groupId = "my-consumer-group")
	public void performInvoice(String message)  {
		long iban = Long.parseLong(message);
		 try {
		        // Your business logic code here
				transactionsService.makeInvoicePayment( iban);
		    } catch (Exception e) {
		        throw new KafkaConsumer("Error processing Kafka message", e);
		    }
		}
}

//kafka-console-producer.bat --broker-list localhost:9092 --topic your-topic-name
//1. zookeeper .\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
//2. kafka server .\bin\windows\kafka-server-start.bat .\config\server.properties
// set properly dir! C:/mkdir/...