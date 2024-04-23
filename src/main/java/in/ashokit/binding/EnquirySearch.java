package in.ashokit.binding;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class EnquirySearch {
	private String course;
	private String status;
	private String mode;

}
