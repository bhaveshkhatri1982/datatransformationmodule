package ee.ria.datatransformationmodule.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import ee.ria.datatransformationmodule.data.BaseStationReport;
import ee.ria.datatransformationmodule.dto.BaseStationReportRepository;


@Service("baseStationReportService")
@Scope("prototype")
public class BaseStationReportService
{
	@Autowired
    private BaseStationReportRepository baseStationReportRepository;
	
	public void save(BaseStationReport baseStationReport)
	{
		baseStationReportRepository.save(baseStationReport);
	}
	
	@SuppressWarnings("deprecation")
	public List<BaseStationReport> findMostRecentMobileReports(String uuid, LocalDateTime timeStamp)
	{
		List<BaseStationReport> listBaseStationReport = new ArrayList<BaseStationReport>();
		
		
		List<BaseStationReport> listBaseStationReportForTimeStamp = new ArrayList<BaseStationReport>();
		baseStationReportRepository.findMostRecentMobileReportTimeStamp(new PageRequest(0, 1), uuid, timeStamp).forEach(bt->listBaseStationReportForTimeStamp.add(bt));
		
		System.out.println("listBaseStationReportForTimeStamp size = "+listBaseStationReportForTimeStamp.size());
		if(listBaseStationReportForTimeStamp != null && listBaseStationReportForTimeStamp.size()>0)
		{	
			LocalDateTime mostRecentTimeStamp = listBaseStationReportForTimeStamp.get(0).getTimestamp(); 
			System.out.println("mostRecentTimeStamp = "+mostRecentTimeStamp);
			System.out.println("size = "+baseStationReportRepository.findMobileReportsForSameTime(uuid, mostRecentTimeStamp).size());
			baseStationReportRepository.findMobileReportsForSameTime(uuid, mostRecentTimeStamp).forEach(bt->listBaseStationReport.add(bt));
		}
		return listBaseStationReport;
	}
}
