package ee.ria.datatransformationmodule.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@Scope("prototype")
@Entity
@Table(name="m_mobile")
public class Mobile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="mobile_id")	
	private long mobileId = 0l;
	
	@Column(name="uuid", unique=true)
    private String uuid = "";
	
	@Column(name="last_known_x")
    private int lastKnownX = 0;
    
	@Column(name="last_known_y")
	private int lastKnownY = 0;

}
