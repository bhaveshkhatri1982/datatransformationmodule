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
@Table(name="m_base_station")
public class BaseStation 
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="base_station_id")	
	private long baseStationId = 0l;
    
	@Column(name="uuid", unique=true)
	private String uuid = "";
    
	@Column(name="name")	
	private String name = "";
    
	@Column(name="x_value")	
	private int x = 0;
    
	@Column(name="y_value")	
	private int y = 0;
    
	@Column(name="detection_radius")	
	private double detectionRadius = 0.0f;
}
