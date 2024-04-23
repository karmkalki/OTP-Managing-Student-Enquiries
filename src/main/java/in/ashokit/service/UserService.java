package in.ashokit.service;

import in.ashokit.binding.ForgetForm;
import in.ashokit.binding.LoginForm;
import in.ashokit.binding.RegisterForm;
import in.ashokit.binding.UnlockForm;


public interface UserService {
	public boolean register(RegisterForm rf);
	public boolean unlock(UnlockForm uf);
	public String login(LoginForm lf);
	public boolean forget(ForgetForm lf);
}
