package pojo;

import io.cucumber.core.internal.com.fasterxml.jackson.annotation.JsonProperty;

public class GitHubCreateRepoPOJO {

	private String name;
	private String description;
		
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

	@JsonProperty(value = "private")
	private boolean privateVal;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
