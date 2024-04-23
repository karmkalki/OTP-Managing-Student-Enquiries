package in.ashokit.runner;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import in.ashokit.entity.Courses;
import in.ashokit.entity.Status;
import in.ashokit.repo.CoursesRepo;
import in.ashokit.repo.StatusRepo;
@Component
public class DataLoader implements ApplicationRunner{
@Autowired
CoursesRepo crepo;
@Autowired
StatusRepo srepo;
	@Override
	public void run(ApplicationArguments args) throws Exception {
		/*when application start all data which available in course or sattus table delete*/
		crepo.deleteAll();
		srepo.deleteAll();

		/*when application start all data save inn course or sattus table*/
		Courses c=new Courses();
		c.setName("JAVA");
		Courses c1=new Courses();
		c1.setName("SPRING");
		Courses c2=new Courses();
		c2.setName("PHYTHON");
		Courses c3=new Courses();
		c3.setName("C++");
		
		crepo.saveAll(Arrays.asList(c,c1,c2,c3));
	
		Status s=new Status();
s.setName("NEW");
	Status s1=new Status();
s1.setName("ENROLLED");
	Status s2=new Status();
	s2.setName("LOST");	
	
	srepo.saveAll(Arrays.asList(s,s1,s2));
	
	
	}

}
