package ee.ria.datatransformationmodule.data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="t_bs_report_detail")
public class BaseStationReport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="bs_report_detail_id")	
	private long baseStationId = 0l;
    
	@ManyToOne	
	@JoinColumn(name="base_station_id", nullable=false)
	private BaseStation baseStation;
	
	@ManyToOne	
	@JoinColumn(name="mobile_id", nullable=false)
	private Mobile mobile;
    
	@Column(name="distance")	
	private double distance = 0.0d;
    
	@Column(name="timestamp")	
	private LocalDateTime timestamp;
}
