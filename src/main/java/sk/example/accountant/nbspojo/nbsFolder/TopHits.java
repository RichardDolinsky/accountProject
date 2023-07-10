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
"sort",
"size"
})
@Generated("jsonschema2pojo")
public class TopHits {

@JsonProperty("sort")
private List<Sort> sort;
@JsonProperty("size")
private Integer size;
@JsonIgnore
private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

@JsonProperty("sort")
public List<Sort> getSort() {
return sort;
}

@JsonProperty("sort")
public void setSort(List<Sort> sort) {
this.sort = sort;
}

@Override
public String toString() {
StringBuilder sb = new StringBuilder();
sb.append(TopHits.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
sb.append("sort");
sb.append('=');
sb.append(((this.sort == null)?"<null>":this.sort));
sb.append(',');
sb.append("size");
sb.append('=');
sb.append(((this.size == null)?"<null>":this.size));
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

@JsonProperty("size")
public Integer getSize() {
return size;
}

@JsonProperty("size")
public void setSize(Integer size) {
this.size = size;
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