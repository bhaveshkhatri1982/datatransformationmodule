package ee.ria.datatransformationmodule.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ee.ria.datatransformationmodule.data.BaseStationReport;


@Repository("baseStationReportRepository")
@Scope("prototype")
public interface BaseStationReportRepository extends CrudRepository<BaseStationReport, Integer>
{
	@Query("from BaseStationReport where mobile.uuid=:uuid and timeStamp<=:timeStamp order By timeStamp desc")
	List<BaseStationReport> findMostRecentMobileReportTimeStamp(Pageable pageable, @Param("uuid") String uuid, @Param("timeStamp") LocalDateTime timeStamp);
	
	@Query("from BaseStationReport where mobile.uuid=:uuid and timeStamp=:timeStamp")
	List<BaseStationReport> findMobileReportsForSameTime(@Param("uuid") String uuid, @Param("timeStamp") LocalDateTime timeStamp);
}
