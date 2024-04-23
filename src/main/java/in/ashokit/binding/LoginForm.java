package in.ashokit.binding;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class LoginForm {
	private String email;
	private String password;
	

}
