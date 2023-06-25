package sk.example.accountant.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = {"cause", "stackTrace", "suppressed", "localizedMessage","message"})
public class ResponseMessage extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int responseStatus;
	private String responseMessage;
	public int getResponseStatus() {
		return responseStatus;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public ResponseMessage(int responseStatus, String responseMessage) {
		this.responseStatus = responseStatus;
		this.responseMessage = responseMessage;
	}
	@Override
	public String toString() {
		return "ResponseMessage [responseStatus=" + responseStatus + ", responseMessage=" + responseMessage + "]";
	}
	

}
