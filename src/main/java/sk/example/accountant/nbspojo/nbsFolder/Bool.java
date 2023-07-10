package sk.example.accountant.nbspojo.nbsFolder;

import java.util.LinkedHashMap;
import java.util.List;
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
"must"
})
@Generated("jsonschema2pojo")
public class Bool {

@JsonProperty("must")
private List<Must> must;
@JsonIgnore
private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

@JsonProperty("must")
public List<Must> getMust() {
return must;
}

@JsonProperty("must")
public void setMust(List<Must> must) {
this.must = must;
}

@Override
public String toString() {
StringBuilder sb = new StringBuilder();
sb.append(Bool.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
sb.append("must");
sb.append('=');
sb.append(((this.must == null)?"<null>":this.must));
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

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}
