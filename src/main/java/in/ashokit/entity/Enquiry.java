package in.ashokit.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Enquiry {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer studentid;
	private String name;
	private String classMode;
	private String course;
	private String number;
	private String enqStatus;
	@UpdateTimestamp
	private LocalDate dateCreated;
    @CreationTimestamp
    @Column(nullable=false,updatable=false)
	private LocalDate lastUpdate;
  /*we no use any cascade type because our we dont have requirement to based enquiry we store data in user column
   * In many to one mapping ownership table(in our this class( enquiry  table) create a new column userId) create colum 
   * */
    @ManyToOne
    @JoinColumn(
    		name="user_ID",
	referencedColumnName="userid")
private Register register;    
    
    
	
}
