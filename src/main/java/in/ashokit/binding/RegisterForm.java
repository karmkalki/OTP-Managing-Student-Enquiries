package in.ashokit.binding;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class RegisterForm {
	private String name;
	private String email;
	private String no;

	
}
