package ee.ria.datatransformationmodule.data;

import java.util.ArrayList;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@Scope("prototype")
public class BaseStationReportRequest
{
	private String base_station_id;
	ArrayList<MobileReport> listMobileReport = new ArrayList<MobileReport>();
}
