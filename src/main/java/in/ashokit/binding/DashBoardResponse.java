package in.ashokit.binding;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class DashBoardResponse {
	private Integer enquiry;
	private Integer enrolled;
	private Integer lost;

}
