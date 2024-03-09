package complexPojoClassConcept;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetPojoClass {

	private Integer id;
	private Category category;
	private String name;
	List<String> photoUrls;
	List<Tag> tags;
	private String status;

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class Category {
		private Integer id;
		private String name;

	}

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class Tag {

		private Integer id;
		private String name;

	}
}
