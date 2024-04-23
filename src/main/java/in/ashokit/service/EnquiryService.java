
package in.ashokit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.ashokit.binding.DashBoardResponse;
import in.ashokit.binding.EnquiryForm;
import in.ashokit.binding.Search;
import in.ashokit.entity.Enquiry;

@Service
public interface EnquiryService {
public List<String>	getAllPlan();
public List<String> getAllCourse();
public DashBoardResponse getDashboard(Integer userId);
/*In real time we are not send return type entity class obj(we are not exposing them) entity is used for persistence layer(Database)
 * thats why we use return type as binding class..bcz binding class is used to capture or send data to UI (Dto or presentation layer(UI))
 * */

boolean addEnquiry(EnquiryForm ef, Integer id);
public List<Enquiry> viewEnquiry(Integer userid);
List<Enquiry> viewSearchEnquiry(Integer userid, Search s);
public Enquiry editRecord(Integer id) ;



	
}
