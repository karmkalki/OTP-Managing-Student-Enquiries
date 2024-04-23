package in.ashokit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.ashokit.entity.Courses;

public interface CoursesRepo extends JpaRepository<Courses, Integer> {
	
}
