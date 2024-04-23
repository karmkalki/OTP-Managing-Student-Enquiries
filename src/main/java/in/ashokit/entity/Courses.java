package in.ashokit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Courses {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer courseid;
	private String name;

	
}
