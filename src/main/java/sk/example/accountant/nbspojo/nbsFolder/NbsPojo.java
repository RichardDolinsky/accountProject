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
"query",
"size",
"aggs"
})
@Generated("jsonschema2pojo")
public class NbsPojo {



@JsonProperty("query")
private Query query;
@JsonProperty("size")
private Integer size;
@JsonProperty("aggs")
private Aggs aggs;
@JsonIgnore
private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

@JsonProperty("query")
public Query getQuery() {
return query;
}

@JsonProperty("query")
public void setQuery(Query query) {
this.query = query;
}

@JsonProperty("size")
public Integer getSize() {
return size;
}

@JsonProperty("size")
public void setSize(Integer size) {
this.size = size;
}

@JsonProperty("aggs")
public Aggs getAggs() {
return aggs;
}

@JsonProperty("aggs")
public void setAggs(Aggs aggs) {
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

@Override
public String toString() {
StringBuilder sb = new StringBuilder();
sb.append(NbsPojo.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
sb.append("query");
sb.append('=');
sb.append(((this.query == null)?"<null>":this.query));
sb.append(',');
sb.append("size");
sb.append('=');
sb.append(((this.size == null)?"<null>":this.size));
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

}