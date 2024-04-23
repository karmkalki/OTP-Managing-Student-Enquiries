package in.ashokit.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.binding.DashBoardResponse;
import in.ashokit.binding.EnquiryForm;
import in.ashokit.binding.Search;
import in.ashokit.constants.AppConstant;
import in.ashokit.entity.Courses;
import in.ashokit.entity.Enquiry;
import in.ashokit.entity.Register;
import in.ashokit.repo.CoursesRepo;
import in.ashokit.repo.EnquiryRepo;
import in.ashokit.repo.RegisterRepo;
import in.ashokit.repo.StatusRepo;

@Service
public class EnquiryServiceImp implements EnquiryService{
	@Autowired
	RegisterRepo registerrepo;
	@Autowired
	EnquiryRepo enquiryrepo;
@Autowired
StatusRepo statusrepo;

@Autowired
CoursesRepo courserepo;

	@Override
	public DashBoardResponse getDashboard(Integer userId) {
	DashBoardResponse dash=new DashBoardResponse();
			/*first check in this user id user accound available or not */
	Optional<Register> findbyId=registerrepo.findById(userId);
	/*if user account present than enter to if condition*/
	
					if(findbyId.isPresent()) {
		/*than get that user data from database*/
				    Register user=findbyId.get();
	/*in our entity class(Register or Enquiry) one to many mapping we do . so in register class we have a object enquiry name that return 
	 * list of eqnuiry. in our requirement we want only particular user data who is login ,in above we get user data and store in userobj 
	 * so call getEnquiry() with the help of userobj so only that user store data is retriew from enquiry table
	 *  */
				   List<Enquiry> studentEnquiry=user.getEnquiry();
	/*size() return integer value which is total record*/
				   int total=studentEnquiry.size();
	
	/*stream is used to filter data */
				   int enrolled=studentEnquiry.stream()
				  .filter(e -> e.getEnqStatus().equals(AppConstant.STR_ENROLLED))
	              .collect(Collectors.toList()).size();

                   int lost=studentEnquiry.stream()
				  .filter(e -> e.getEnqStatus().equals(AppConstant.STR_LOST))
	              .collect(Collectors.toList()).size();
                
                   /*set data in DashBOard binding  class*/
                   dash.setEnquiry(total);
                   dash.setEnrolled(enrolled);
                   dash.setLost(lost); }
		return dash;
	}

	/*data come from database and set data to plan status dropdown*/
	@Override
	public List<String> getAllPlan() {		
		return statusrepo.getAllPlanStatus();
	}

	/*different approach  and set data to courses dropdown*/
	@Override
	public List<String> getAllCourse() {
	
	   List<Courses> courseList = courserepo.findAll();
	    List<String> courseNames = new ArrayList<>();
	    for (Courses course : courseList) {
	        courseNames.add(course.getName());
	    }
	
	
	return courseNames;
	}

	@Override
	public boolean addEnquiry(EnquiryForm ef,Integer id) {
	/*with the given id find records in user register table */
		Optional<Register> findbyId=registerrepo.findById(id);
		if(findbyId.isPresent()) {
			
		Enquiry e=new Enquiry();
		/* copy binding class data to Enquiry entity class to store in db*/
		BeanUtils.copyProperties(ef,e);
		
		/*for edit data set manually id otherwise new data enter in database 
		 * agr id present hai to update or agr not present than insert new record
		 * */
e.setStudentid(ef.getId());		
	

/* findbyId.get() return register object which have that user id (ex=1)*/
		Register r=findbyId.get();
		
/*In enquiry table we do Many to one with register table so pass register object
 * In @joincolumn wehich column join so automatically join user id column(ex=1)
 * */		
	e.setRegister(r);
/*Now record is save with foregin key*/
	enquiryrepo.save(e);
	return true;
}
		return false;
	}

	@Override
	public List<Enquiry> viewEnquiry(Integer userid) {
	Optional<Register> findById=registerrepo.findById(userid);
	
	if(findById.isPresent()) {
		Register user=findById.get();
		List<Enquiry> student=user.getEnquiry();
		return student;
	}
	return null;
	}

	@Override
	public List<Enquiry> viewSearchEnquiry(Integer userid,Search s) {

		Optional<Register> findById=registerrepo.findById(userid);
		
		if(findById.isPresent()) {
			Register user=findById.get();
			List<Enquiry> student=user.getEnquiry();
		
		 
                /*filter data */			
			if(!"".equals(s.getCourse())){
        student=student.stream()
		.filter(e -> e.getCourse().equals(s.getCourse()))
		  .collect(Collectors.toList());	
			}
			

			if(!"".equals(s.getStatus())) {

         	student=student.stream()
		  .filter(e -> e.getEnqStatus().equals(s.getStatus()))
		  .collect(Collectors.toList());	
			}
			

			if(!"".equals(s.getMode())) {
		  student=student.stream()
		 .filter(e -> e.getClassMode().equals(s.getMode()))
		  .collect(Collectors.toList());	
			}
			
			return student;
		
		}	

		return null;
	}

	@Override
	public Enquiry editRecord(Integer id) {
	
	Optional<Enquiry> e=enquiryrepo.findById(id);
	if(e.isPresent()) {
		Enquiry edit=e.get();
		
		return edit;
	}
	return null;
	}

	
	
	
	
	
	
	

}
