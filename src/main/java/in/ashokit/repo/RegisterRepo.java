package in.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.Register;

public interface RegisterRepo extends JpaRepository<Register, Integer>{
	public Register findByEmail(String email);

}
