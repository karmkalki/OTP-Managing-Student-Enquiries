package in.ashokit.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Register {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Integer userid;
private String name;
private String email;
private String no;
private String pwd;
private String acc_Status;

/*we use cascade type because our we have requirement to based User we store data in enquiry table
 * CascadeType.ALL so if data is not available in child table no exception come
 * or if you insert the parent class data automatically insert in child class also
 * fetch=FetchType.EAGER acess parent as well as parent class data === if we remove no effect on project
 * */
@OneToMany(mappedBy = "register",
cascade=CascadeType.ALL,
fetch=FetchType.EAGER)
private List<Enquiry> enquiry;


}
