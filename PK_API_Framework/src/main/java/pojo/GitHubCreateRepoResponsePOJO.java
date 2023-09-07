package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GitHubCreateRepoResponsePOJO {

	private String name;
	private String description;
	@JsonProperty(value = "private")
	private boolean privateVal;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isPrivateVal() {
		return privateVal;
	}

	public void setPrivateVal(boolean privateVal) {
		this.privateVal = privateVal;
	}

}
