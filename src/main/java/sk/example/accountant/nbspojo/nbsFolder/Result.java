package sk.example.accountant.nbspojo.nbsFolder;

import java.util.LinkedHashMap;
import java.util.Map;
import jakarta.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"terms",
"aggs"
})
@Generated("jsonschema2pojo")
public class Result {

	@Override
	public String toString() {
	StringBuilder sb = new StringBuilder();
	sb.append(Result.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
	sb.append("terms");
	sb.append('=');
	sb.append(((this.terms == null)?"<null>":this.terms));
	sb.append(',');
	sb.append("aggs");
	sb.append('=');
	sb.append(((this.aggs == null)?"<null>":this.aggs));
	sb.append(',');
	sb.append("additionalProperties");
	sb.append('=');
	sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
	sb.append(',');
	if (sb.charAt((sb.length()- 1)) == ',') {
	sb.setCharAt((sb.length()- 1), ']');
	} else {
	sb.append(']');
	}
	return sb.toString();
	}

@JsonProperty("terms")
private Terms terms;
@JsonProperty("aggs")
private Aggs__1 aggs;
@JsonIgnore
private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

@JsonProperty("terms")
public Terms getTerms() {
return terms;
}

@JsonProperty("terms")
public void setTerms(Terms terms) {
this.terms = terms;
}

@JsonProperty("aggs")
public Aggs__1 getAggs() {
return aggs;
}

@JsonProperty("aggs")
public void setAggs(Aggs__1 aggs) {
this.aggs = aggs;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}
