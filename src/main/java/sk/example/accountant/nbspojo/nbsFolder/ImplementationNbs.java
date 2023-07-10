package sk.example.accountant.nbspojo.nbsFolder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
@Service
public class ImplementationNbs implements RestApiServisNBS {

	@Override
	public NbsPojo getPojoExchangeRate(String dateString) {
		RestTemplate template = new RestTemplate();
		NbsPojo pojoRequest = new NbsPojo();
		pojoRequest.setSize(0);

		Aggs aggs = new Aggs();
		pojoRequest.setAggs(aggs);

		Result result = new Result();
		pojoRequest.getAggs().setResult(result);

		Aggs__1 aggs__1 = new Aggs__1();
		pojoRequest.getAggs().getResult().setAggs(aggs__1);

		LatestRate latestRate = new LatestRate();
		pojoRequest.getAggs().getResult().getAggs().setLatestRate(latestRate);

		TopHits topHists = new TopHits();
		topHists.setSize(1);
		pojoRequest.getAggs().getResult().getAggs().getLatestRate().setTopHits(topHists);

		Query query = new Query();
		pojoRequest.setQuery(query);
		Sort sort = new Sort();
		Date__1 date__1 = new Date__1();
		date__1.setOrder("desc");
		sort.setDate(date__1);
		List<Sort> sortList = new ArrayList<>();
		sortList.add(sort);
		pojoRequest.getAggs().getResult().getAggs().getLatestRate().getTopHits().setSort(sortList);
		Terms terms = new Terms();
		pojoRequest.getAggs().getResult().setTerms(terms);
		pojoRequest.getAggs().getResult().getTerms().setField("currency");
		pojoRequest.getAggs().getResult().getTerms().setSize(300);

		Bool bool = new Bool();
		Must must = new Must();
		List<Must> mustList = new ArrayList<>();
		mustList.add(must);
		bool.setMust(mustList);

		pojoRequest.getQuery().setBool(bool);

		pojoRequest.getQuery().getBool().setMust(mustList);
		Range range = new Range();
		pojoRequest.getQuery().getBool().getMust().get(0).setRange(range);
		Date date = new Date();
		date.setLte(getCurrentDate());
		pojoRequest.getQuery().getBool().getMust().get(0).getRange().setDate(date);

		try {
			ResponseEntity<NbsPojo> response = template.postForEntity("https://nbs.sk/elastic/exchange_rates/_search",
					pojoRequest, NbsPojo.class);
			return response.getBody();
		} catch (HttpClientErrorException ex) {
			// If there is a client-side error (4xx status code)
			HttpStatusCode statusCode = ex.getStatusCode();
			// handle the error or log the status code
			System.out.println("Error occurred with status code: " + statusCode);
			return null;
		}

	}

	public static String getCurrentDate() {
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return currentDate.format(formatter);
	}


	@Override
	public String getCurrency(NbsPojo currencyPojo, String lte) {

		if (currencyPojo != null) {
			Map<String, Object> additionalProperties = currencyPojo.getAdditionalProperties();
			@SuppressWarnings("unchecked")
			Map<String, Object> aggregations = (Map<String, Object>) additionalProperties.get("aggregations");
			@SuppressWarnings("unchecked")
			Map<String, Object> result = (Map<String, Object>) aggregations.get("result");
			@SuppressWarnings("unchecked")
			List<Object> buckets = (List<Object>) result.get("buckets");

			for (Object bucket : buckets) {
				@SuppressWarnings("unchecked")
				Map<String, Object> bucketData = (Map<String, Object>) bucket;
				// Access specific properties of each bucket
				@SuppressWarnings("unchecked")
				Map<String, Object> latestRate = (Map<String, Object>) bucketData.get("latest_rate");
				@SuppressWarnings("unchecked")
				Map<String, Object> hits = (Map<String, Object>) latestRate.get("hits");
				@SuppressWarnings("unchecked")
				List<Object> miniHits = (List<Object>) hits.get("hits");
				@SuppressWarnings("unchecked")
				Map<String, Object> miniData = (Map<String, Object>) miniHits.get(0);
				String id = miniData.get("_id").toString();
				String currency = id.substring(id.indexOf("|") + 1);

				if (currency.equalsIgnoreCase(lte)) {
					@SuppressWarnings("unchecked")
					Map<String, Object> exChangeCourse = (Map<String, Object>) miniData.get("_source");
					System.out.println(
							"currency for " + exChangeCourse.get("currency") + " is: " + exChangeCourse.get("rate"));
					return exChangeCourse.get("rate").toString();
				}
				else continue;
			}
		}
		return "-1";
	}
}
