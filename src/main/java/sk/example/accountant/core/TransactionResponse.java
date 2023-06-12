package sk.example.accountant.core;

public class TransactionResponse {
	 private String sender;
	    private String receiver;
	    private double value;
	    private double senderAccountBalance;
	    private double receiverAccountBalance;
	    private double charge;


	    public TransactionResponse(String sender, String receiver, double value, double senderAccountBalance, double receiverAccountBalance, double charge) {
	        this.sender = sender;
	        this.receiver = receiver;
	        this.value = value;
	        this.senderAccountBalance = senderAccountBalance;
	        this.receiverAccountBalance = receiverAccountBalance;
	        this.charge = charge;
	    }
	    

		public String getSender() {
			return sender;
		}


		public String getReceiver() {
			return receiver;
		}


		public double getValue() {
			return value;
		}


		public double getSenderAccountBalance() {
			return senderAccountBalance;
		}


		public double getReceiverAccountBalance() {
			return receiverAccountBalance;
		}
		
		public double getCharge () {
			return charge;
		}

}
