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
"order"
})
@Generated("jsonschema2pojo")
public class Date__1 {

	@Override
	public String toString() {
	StringBuilder sb = new StringBuilder();
	sb.append(Date__1 .class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
	sb.append("order");
	sb.append('=');
	sb.append(((this.order == null)?"<null>":this.order));
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

@JsonProperty("order")
private String order;
@JsonIgnore
private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

@JsonProperty("order")
public String getOrder() {
return order;
}

@JsonProperty("order")
public void setOrder(String order) {
this.order = order;
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
