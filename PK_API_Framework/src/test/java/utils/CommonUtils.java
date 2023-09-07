package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

import io.restassured.response.Response;
import pojo.GitHubCreateRepoPOJO;
import pojo.GitHubCreateRepoResponsePOJO;

public class CommonUtils {

	public static GitHubCreateRepoPOJO getCreateRepoPOJOObj(String paramName, String paramValue) {
		String[] keyArray = paramName.split(",");
		String[] valueArray = paramValue.split(",");

		GitHubCreateRepoPOJO obj = new GitHubCreateRepoPOJO();

		for (int i = 0; i < keyArray.length; i++) {
			String key = keyArray[i];
			if (key.equals("name")) {
				obj.setName(valueArray[i]);
			}
			if (key.equals("description")) {
				obj.setDescription(valueArray[i]);
			}
			if (key.equals("private")) {
				obj.setPrivateVal(Boolean.parseBoolean(valueArray[i]));
			}
		}
		return obj;

	}
	
	public static void validateCreateResponsePOJO(Response res, String paramName, String paramValue) {
		GitHubCreateRepoResponsePOJO obj = res.then().extract().as(GitHubCreateRepoResponsePOJO.class);

		String[] keyArray = paramName.split(",");
		String[] valueArray = paramValue.split(",");

		for (int i = 0; i < keyArray.length; i++) {
			String key = keyArray[i];
			if (key.equals("name")) {
				String actualName = obj.getName();
				MatcherAssert.assertThat(actualName, Matchers.equalTo(valueArray[i]));
			}
			if (key.equals("description")) {
				MatcherAssert.assertThat(obj.getDescription(), Matchers.equalTo(valueArray[i]));
			}
			if (key.equals("private")) {
				MatcherAssert.assertThat(obj.isPrivateVal(), Matchers.equalTo(Boolean.parseBoolean(valueArray[i])));
			}
		}
	}
	
	public static String getProperty(String env, String key) {
		String baseURI = null;
		try {
			FileInputStream fis = new FileInputStream("src/test/resources/"+env+".properties");
			Properties properties = new Properties();
			properties.load(fis);
			baseURI = properties.getProperty(key);
			
		} catch (IOException e) {
			
		}
		return baseURI;
		
	}

}
