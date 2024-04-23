package in.ashokit.binding;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class EnquiryForm {
	private Integer id;
private String name;
private String number;
private String classMode;
private String course;
private String enqStatus;
	
}
