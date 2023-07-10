package sk.example.accountant.nbspojo.nbsFolder;

public interface RestApiServisNBS {
	
NbsPojo getPojoExchangeRate(String date);
String getCurrency(NbsPojo pojo, String lte);

}
