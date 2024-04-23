package in.ashokit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.ashokit.entity.Status;

public interface StatusRepo extends JpaRepository<Status, Integer> {
	@Query("select distinct name from Status")
	public List<String> getAllPlanStatus();
	
}
