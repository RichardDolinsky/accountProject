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
"top_hits"
})
@Generated("jsonschema2pojo")
public class LatestRate {

	@Override
	public String toString() {
	StringBuilder sb = new StringBuilder();
	sb.append(LatestRate.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
	sb.append("topHits");
	sb.append('=');
	sb.append(((this.topHits == null)?"<null>":this.topHits));
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

@JsonProperty("top_hits")
private TopHits topHits;
@JsonIgnore
private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

@JsonProperty("top_hits")
public TopHits getTopHits() {
return topHits;
}

@JsonProperty("top_hits")
public void setTopHits(TopHits topHits) {
this.topHits = topHits;
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
