package serializationConcept;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Here we are creating POJO LOmbok class for both Request/Response for both
 * serialization and de-serialization concept. Since id variable we get only
 * during response and not during request so we have to create a parameterized
 * constructor without using Id so to map the same during de-serialization
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLombokPojoClass {

	@JsonProperty("name") // These Json Property we are declaring so that we can map the actual json key
							// names to the variables we declared in this class
	private String name;
	@JsonProperty("status")
	private String status;
	@JsonProperty("gender")
	private String gender;
	@JsonProperty("email")
	private String email;
	@JsonProperty("id")
	private int id;

	public UserLombokPojoClass(String name, String status, String gender, String email) {
		this.name = name;
		this.status = status;
		this.gender = gender;
		this.email = email;
	}

}
