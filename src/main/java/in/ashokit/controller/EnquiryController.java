package in.ashokit.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.ashokit.binding.DashBoardResponse;
import in.ashokit.binding.EnquiryForm;
import in.ashokit.binding.Search;
import in.ashokit.entity.Enquiry;
import in.ashokit.service.EnquiryServiceImp;
import jakarta.servlet.http.HttpSession;

@Controller
public class EnquiryController {
	
	  @Autowired
	    private HttpSession httpSession;
@Autowired
	private EnquiryServiceImp enquiryService;

	@GetMapping("/dashboard")
	public String dashBoardPage(Model m) {
Integer userId=(Integer)httpSession.getAttribute("userid");
DashBoardResponse dash=enquiryService.getDashboard(userId);

/*In real time we return binding class object not entity class to UI*/
		m.addAttribute("dashBoardData", dash);
return "dashboard";
	}


	private void commonData(Model m) {
		/*to load & set course in dropdown */
		m.addAttribute("courses", enquiryService.getAllCourse());
		/*to load & set status in dropdown */
		m.addAttribute("status", enquiryService.getAllPlan());
/*binding object send for form binding*/
		m.addAttribute("addEnquiry", new EnquiryForm());
	}
	
	@GetMapping("/add-enquiry")
	public String addEnquiryPage(Model m) {
		/*above method */
		commonData(m);
		return "add-enquiry";
	}
/*add or edit data in database*/
	@PostMapping("/add-enquiry")
	public String getAddEnquiryPageData(EnquiryForm ef,Model m) {
		commonData(m);
		/*get user id to session getAttribute method()*/
		Integer userId=(Integer)httpSession.getAttribute("userid");
		/*send binding or user id to service method*/
boolean add=	enquiryService.addEnquiry(ef, userId);
if(add) {
	m.addAttribute("msg", "Record Insert Sucessfull");
}else {
	m.addAttribute("msg", "Record is not Insert Sucessfull");	
}

return "add-enquiry";
	}
	
	@GetMapping("/view-enquires")
	public String viewEnquiryPage(Model m) {
	Integer userId=(Integer)httpSession.getAttribute("userid");
List<Enquiry> student=enquiryService.viewEnquiry(userId);
commonData(m);

	m.addAttribute("student",student);
	
		return "view-enquires";
	}
	
	
	
	@GetMapping("/viewSearch-enquires")
	public String viewSearchEnquiryPage(@RequestParam String course,@RequestParam String status,@RequestParam String mode,Model m) {
		Integer userId=(Integer)httpSession.getAttribute("userid");
	
	Search s=new Search();
	s.setCourse(course);
	s.setStatus(status);
	s.setMode(mode);
	List<Enquiry> enquiry=enquiryService.viewSearchEnquiry(userId,s);
    m.addAttribute("student",enquiry);
	return "viewSearch-enquires";
	}
	
	 
	@GetMapping("/edit")
	public String edit(@RequestParam Integer id,Model m) {
	m.addAttribute("courses", enquiryService.getAllCourse());
	m.addAttribute("status", enquiryService.getAllPlan());

	Enquiry E=enquiryService.editRecord(id);	
	EnquiryForm f=new EnquiryForm();
	/*Convert Entity clas obj to binding class because we bind  binding class object*/
	BeanUtils.copyProperties(E,f);
	/*set id in binding class manually bcz both variable name different*/
    f.setId(E.getStudentid());
    
    
    
m.addAttribute("msg","Record Update Sucessfully");
	m.addAttribute("addEnquiry",f)	;

			return "add-enquiry";
	}
	
	
	
	
	
	
	
	@GetMapping("/logout")
	public String logout() {
		/*session is delete*/
		httpSession.invalidate();
		return "index";
	}
}

