package in.ashokit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.ashokit.binding.ForgetForm;
import in.ashokit.binding.LoginForm;
import in.ashokit.binding.RegisterForm;
import in.ashokit.binding.UnlockForm;
import in.ashokit.service.UserServiceImp;

@Controller
public class UserController {
	@Autowired
	UserServiceImp userservice;
	/*Display our SignUp(Registeration Page) or bind object to get data*/
	
	@GetMapping("/register")
	public String registerPage(Model m) {
		m.addAttribute("user",new RegisterForm() );
		return "register";
	}
	/*Data come from registeration page or send to service layer method */
	
	@PostMapping("/register")
	public String getRegisterPageData(@ModelAttribute("user") RegisterForm rf,Model m) {
		
		boolean send=userservice.register(rf);
		if(send) {
			m.addAttribute("sucess","Acount create,check Your email");
		}else {
			m.addAttribute("unsucess","Duplicate Gmail Account Already Created");
		}
		return "register";
	}
	
	/*Display our Unlock Page or bind object to get data
	 * when we send send link we use querybyparameter(email=?) to send email so to get that email we use @RequestParam String email
	 * */
	@GetMapping("/unlock")
	public String getunlockPage(@RequestParam String email,Model m) {
		UnlockForm unlock=new UnlockForm();
		/*store email in our binding class variable to display on unlock page
		 * or imp to store email in binding class we use hidden variable in unlock.html page other wise null email send 
		 * */
		unlock.setEmail(email);
		m.addAttribute("Userunlock", unlock);
		return "unlock";
	}
	/*Data come from unlock page or send to service layer method */
	@PostMapping("/unlock")
	public String getunlockPageData(@ModelAttribute("Userunlock") UnlockForm unlock ,Model m) {
	
		boolean unlocked=false;
		
		if(unlock.getNewpwd().equals(unlock.getConpwd())) {
			/*call service method*/
			unlocked=userservice.unlock(unlock);
			
			if(unlocked) {
				m.addAttribute("sucess","Your Account is UnLocked");						
			}else {
				m.addAttribute("unsucess","Check,Temporary Password is Wrong");	
			}
		}
		else {
			m.addAttribute("unsucess","Check Both Password is not same");
		}
		return "unlock";
	}
/*Display our login Page or bind object to get data*/
	@GetMapping("/login")
	public String loginPage(Model m) {
		m.addAttribute("Userlogin",new LoginForm());
		return "login";
	}
	/*Get Data from login page or send to service method*/
	@PostMapping("/login")
	public String getloginPageData(@ModelAttribute("Userlogin") LoginForm lg,Model m) {
	String check=userservice.login(lg);
	if(check.equals("valid")) {
		/*redirect:/dashboard means to call dashboard method and dashboard page is display*/
		return "redirect:/dashboard";
	}
	else if(check.equals("notfound")) {
		m.addAttribute("unsucess", "Email not found");
	}
	else if(check.equals("locked")) {
		m.addAttribute("unsucess", "First Unlock your account");
	}
	else {
		m.addAttribute("unsucess", "Check,password is incorect!");
	}
	return "login";
	}
	

	/*Display our forget Page or bind object to get data*/
	
	@GetMapping("/forgotPwd")
	public String forgotPage(Model m) {
		m.addAttribute("Userforget",new ForgetForm());
		return "forgotPwd";
	}
	/*Get Data from forget page or send to service method*/
	@PostMapping("/forgotPwd")
	public String getforgotPagedata(@ModelAttribute("Userforget") ForgetForm fg,Model m) {
		
		boolean send=userservice.forget(fg);
		if(send) {
			m.addAttribute("sucess","Check You Email Password is sent" );
		}else {
			m.addAttribute("unsucess","Invalid Email " );
		}
		return "forgotPwd";
	}

	
}
