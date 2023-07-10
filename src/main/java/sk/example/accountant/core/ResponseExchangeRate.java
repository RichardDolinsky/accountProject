package sk.example.accountant.core;

public class ResponseExchangeRate {

	private String fromCurrency;
	private String intoCurrency;
	private Number valueForExchange;
	private Number exchangedValue;
	public ResponseExchangeRate(String fromCurrency, String intoCurrency, Number valueForExchange,
			Number exchangedValue) {
		this.fromCurrency = fromCurrency;
		this.intoCurrency = intoCurrency;
		this.valueForExchange = valueForExchange;
		this.exchangedValue = exchangedValue;
	}
	public String getFromCurrency() {
		return fromCurrency;
	}
	public String getIntoCurrency() {
		return intoCurrency;
	}
	public Number getValueForExchange() {
		return valueForExchange;
	}
	public Number getExchangedValue() {
		return exchangedValue;
	}
	
	
}
