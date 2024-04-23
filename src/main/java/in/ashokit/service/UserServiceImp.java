package in.ashokit.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.binding.ForgetForm;
import in.ashokit.binding.LoginForm;
import in.ashokit.binding.RegisterForm;
import in.ashokit.binding.UnlockForm;
import in.ashokit.constants.AppConstant;
import in.ashokit.entity.Register;
import in.ashokit.repo.RegisterRepo;
import in.ashokit.utility.EmailGenerator;
import in.ashokit.utility.TempPasswordGenerator;
import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImp implements UserService {
	@Autowired 
	RegisterRepo registerRepo;

	@Autowired
	EmailGenerator email;
	
	  @Autowired
	    private HttpSession httpSession;

	
	@Override
	public boolean register(RegisterForm rf) {
  /*check email in database by findBYMethod use in Userrepo is available or not if is available return false it means email is already available*/
		Register emailCheck=registerRepo.findByEmail(rf.getEmail())	;
	if(emailCheck!=null) {
		return false;}
	
	/*Copy binding class data to entity object bcz save(entity)*/
		Register r=new Register();
		BeanUtils.copyProperties(rf, r);
		/*Generate temp random password*/
		String pwd=TempPasswordGenerator.passwordGenerater();
		r.setPwd(pwd);
		r.setAcc_Status(AppConstant.STR_LOCKED);
        /*save data in database */
		registerRepo.save(r);
		
		/*Send Gmail with Temp Password*/
		String subject = "Unlock Your Account!AshokIT";
        String to= rf.getEmail();
        
        StringBuilder body = new StringBuilder();
        body.append("<html><body>");
        body.append("<h1>Below Your Temperory Password </h1>");
        body.append("<br>");
        body.append(pwd);
        body.append("<br>");
        body.append("<a href=\"");
        /*send link to email to unlock account use Query by parameter*/
        body.append("http://localhost:8080/unlock?email="+to);
        body.append("\">");
        body.append("CLICK HERE TO UNLOCK YOUR ACCOUNT");
        body.append("</a>");
        body.append("</body></html>");
        
	   boolean emailSend= email.sendEmail(to,subject, body.toString());	

		return emailSend;
	}

	@Override
	public boolean unlock(UnlockForm uf) {
		 Register emailCheck=registerRepo.findByEmail(uf.getEmail())	;
	
		 /*validation for temporary password same or not*/
		 if((emailCheck.getPwd().equals(uf.getTemppwd()) )) {
				emailCheck.setPwd(uf.getConpwd());
				emailCheck.setAcc_Status(AppConstant.STR_UNLOCKED);
				registerRepo.save(emailCheck);
				return true;}
		 return false;
	}

	@Override
	public String login(LoginForm lf) {
	
		 Register emailCheck=registerRepo.findByEmail(lf.getEmail())	;
	
		 if(emailCheck==null) {
				return "notfound";
				}

		 else if(emailCheck.getAcc_Status().equals(AppConstant.STR_LOCKED)) {
			 return "locked";		 
		 }
		 else if(emailCheck.getPwd().equals(lf.getPassword())) {
			 /*create session here bcz when user login according to user insert or view data on pages*/
			 httpSession.setAttribute("userid",emailCheck.getUserid()); 
	return "valid";
}
		return "invalid";
	}

	@Override
	public boolean forget(ForgetForm lf) {
		 Register emailCheck=registerRepo.findByEmail(lf.getEmail())	;
		 String subject = "Your Login Password";
	        String to= emailCheck.getEmail();
	        
	        StringBuilder body = new StringBuilder();
	        body.append("<html><body>");
	        body.append("<h1>Below Your Password </h1>");
	        body.append("<br>");
	        body.append(emailCheck.getPwd());
	        body.append("<br>");
	       body.append("</body></html>");
	        
		   boolean emailSend= email.sendEmail(to,subject, body.toString());	
	
		
		return emailSend;
	}


}
