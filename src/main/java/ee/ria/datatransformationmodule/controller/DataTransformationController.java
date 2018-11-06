package ee.ria.datatransformationmodule.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ee.ria.datatransformationmodule.data.BaseStation;
import ee.ria.datatransformationmodule.data.BaseStationReport;
import ee.ria.datatransformationmodule.data.BaseStationReportRequest;
import ee.ria.datatransformationmodule.data.LocateMobileRequest;
import ee.ria.datatransformationmodule.data.Mobile;
import ee.ria.datatransformationmodule.data.Status;
import ee.ria.datatransformationmodule.service.BaseStationReportService;
import ee.ria.datatransformationmodule.service.BaseStationService;
import ee.ria.datatransformationmodule.service.MobileService;
import ee.ria.datatransformationmodule.utility.Utils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@CrossOrigin
@Controller
@RequestMapping(DataTransformationController.API_ROUTE_MAPPING)
public class DataTransformationController {
    static final String API_ROUTE_MAPPING = "/api/v1/baseStation";
    private static final Logger logger = LoggerFactory.getLogger(DataTransformationController.class);
    
    @Autowired
    BaseStationService baseStationService;
       
    @Autowired
    BaseStationReportService baseStationReportService;
    
    @Autowired
    MobileService mobileService;
    
    @Autowired
    ApplicationContext application;
    
    @PostMapping("/save")
    public @ResponseBody ResponseEntity<Status> collectBaseStationReport(@RequestBody BaseStationReportRequest baseStationReportRequest)
	{
		Status status = new Status();
		 //baseStation = null;
		List<BaseStation> listBaseStation = (ArrayList<BaseStation>)baseStationService.findByUuid(baseStationReportRequest.getBase_station_id());
		logger.info("id = "+listBaseStation.size());
		if(listBaseStation != null && listBaseStation.size()>0)
		{
			BaseStation baseStation = listBaseStation.get(0);
			
			logger.info("id = "+baseStation.getName());
			baseStationReportRequest.getListMobileReport().stream().forEach(mobileReport ->{
				BaseStationReport baseStationReport = (BaseStationReport)application.getBean("baseStationReport");
				baseStationReport.setBaseStation(baseStation);
				
				Mobile mobile = mobileService.findByUuid(mobileReport.getMobile_station_id());
				if(mobile != null)
				{
					baseStationReport.setMobile(mobile);
				}
				else
				{
					// mobile is not in database so need to add it before it gets save
					mobile = (Mobile)application.getBean("mobile");
					mobile.setUuid(mobileReport.getMobile_station_id());
					
					mobileService.save(mobile);
					
					baseStationReport.setMobile(mobile);
				}	
				baseStationReport.setDistance(mobileReport.getDistance());
				baseStationReport.setTimestamp(Utils.getParsedDate(mobileReport.getTimeStamp()));
				
				baseStationReportService.save(baseStationReport);
				
				status.setCode(1);
				status.setMessage("Data recorded successfully");
			});	
		}
		else
		{
			//raise exception
			status.setCode(0);
			status.setMessage("Base station uuid is not recognized. Please check base station identity");
		}	
		
		return new ResponseEntity<>(status, HttpStatus.OK);
	}
    
    @PostMapping("/locateMobile")
    public @ResponseBody ResponseEntity<Mobile> locateMobile(@RequestBody LocateMobileRequest locateMobileRequest)
	{
    	Mobile mobile = mobileService.findByUuid(locateMobileRequest.getMobile_station_id());
    	
    	List<BaseStationReport> listBaseStationReport = baseStationReportService.findMostRecentMobileReports(locateMobileRequest.getMobile_station_id(), Utils.getParsedDate(locateMobileRequest.getTimeStamp()));
    	listBaseStationReport.forEach(lbs->{
    		logger.info(lbs.getBaseStation().getName() + " - "+ lbs.getTimestamp());
    	});
    	
    	//logic for finding Mobile location
    
    	return new ResponseEntity<>(mobile, HttpStatus.OK);
	}
    
}
