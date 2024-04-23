package in.ashokit.binding;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class UnlockForm {
	private String temppwd;
	private String newpwd;
	private String conpwd;
	private String email;

}
