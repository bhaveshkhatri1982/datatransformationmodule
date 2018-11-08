package ee.ria.datatransformationmodule;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import java.net.URI;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import ee.ria.datatransformationmodule.data.BaseStation;
import ee.ria.datatransformationmodule.data.BaseStationReport;
import ee.ria.datatransformationmodule.data.BaseStationReportRequest;
import ee.ria.datatransformationmodule.data.LocateMobileRequest;
import ee.ria.datatransformationmodule.data.Mobile;
import ee.ria.datatransformationmodule.data.MobileReport;
import ee.ria.datatransformationmodule.data.Status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class ErrorhandlingcomponentApplicationTests {
	
    @LocalServerPort
    private int port;
    
    @Autowired
    private TestRestTemplate template;
    
    private final String BASE_URI = "http://localhost:8080/api/v1/baseStation/";
    private final int UNKNOWN_ID = Integer.MAX_VALUE;
    
	@Test
	public void contextLoads() {
	}
	
	
	@Test
    public void SuccessBaseStationReport(){
		
		BaseStationReportRequest baseStationReportRequest = new BaseStationReportRequest();
		
		baseStationReportRequest.setBase_station_id("b2");
		
		ArrayList<MobileReport> listMobileReport = new ArrayList<MobileReport>();
		
		MobileReport mobileReport1 = new MobileReport();
		mobileReport1.setMobile_station_id("m1");
		mobileReport1.setDistance(5);
		mobileReport1.setTimeStamp("2018-10-03 01:30:12");
		listMobileReport.add(mobileReport1);
		
		MobileReport mobileReport3 = new MobileReport();
		mobileReport3.setMobile_station_id("m3");
		mobileReport3.setDistance(12);
		mobileReport3.setTimeStamp("2018-10-03 03:01:12");
		listMobileReport.add(mobileReport3);
		
		baseStationReportRequest.setListMobileReport(listMobileReport);
		
		ResponseEntity<Status> response = template.postForEntity(BASE_URI+"/save", baseStationReportRequest, Status.class);
		Status status = response.getBody();
        assertThat(status.getCode(), is(1));
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
    }
	
	@Test
    public void SuccessBaseStationReportWithNewMobile(){
		
		BaseStationReportRequest baseStationReportRequest = new BaseStationReportRequest();
		
		baseStationReportRequest.setBase_station_id("b3");
		
		ArrayList<MobileReport> listMobileReport = new ArrayList<MobileReport>();
		
		MobileReport mobileReport1 = new MobileReport();
		mobileReport1.setMobile_station_id("m1");
		mobileReport1.setDistance(5);
		mobileReport1.setTimeStamp("2018-10-04 01:30:12");
		listMobileReport.add(mobileReport1);
		
		MobileReport mobileReport3 = new MobileReport();
		mobileReport3.setMobile_station_id("m100");
		mobileReport3.setDistance(12);
		mobileReport3.setTimeStamp("2018-10-04 03:01:12");
		listMobileReport.add(mobileReport3);
		
		baseStationReportRequest.setListMobileReport(listMobileReport);
		
		ResponseEntity<Status> response = template.postForEntity(BASE_URI+"/save", baseStationReportRequest, Status.class);
		Status status = response.getBody();
        assertThat(status.getCode(), is(1));
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
    }
	
	@Test
    public void FailBaseStationReportUnknownBS(){
		
		BaseStationReportRequest baseStationReportRequest = new BaseStationReportRequest();
		
		baseStationReportRequest.setBase_station_id("b10000");
		
		ArrayList<MobileReport> listMobileReport = new ArrayList<MobileReport>();
		
		MobileReport mobileReport1 = new MobileReport();
		mobileReport1.setMobile_station_id("m1");
		mobileReport1.setDistance(5);
		mobileReport1.setTimeStamp("2018-10-03 01:30:12");
		listMobileReport.add(mobileReport1);
		
		MobileReport mobileReport2 = new MobileReport();
		mobileReport2.setMobile_station_id("m2");
		mobileReport2.setDistance(7);
		mobileReport2.setTimeStamp("2018-10-03 02:01:12");
		listMobileReport.add(mobileReport2);
		
		MobileReport mobileReport3 = new MobileReport();
		mobileReport3.setMobile_station_id("m3");
		mobileReport3.setDistance(12);
		mobileReport3.setTimeStamp("2018-10-03 03:01:12");
		listMobileReport.add(mobileReport3);
		
		baseStationReportRequest.setListMobileReport(listMobileReport);
		
		ResponseEntity<Status> response = template.postForEntity(BASE_URI+"/save", baseStationReportRequest, Status.class);
		Status status = response.getBody();
        assertThat(status.getCode(), is(0));
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
    }
    
	
	@Test
    public void SuccessLocateMobile(){
		LocateMobileRequest locateMobileRequest = new LocateMobileRequest();
		locateMobileRequest.setMobile_station_id("m2");
		locateMobileRequest.setTimeStamp("2018-10-04 01:01:12");
		ResponseEntity<Mobile> response = template.postForEntity(BASE_URI+"/locateMobile", locateMobileRequest, Mobile.class);
        Mobile mobile = response.getBody();
        assertThat(mobile.getUuid(), is("m2"));
        assertThat(mobile.getLastKnownX(), is(8));
        assertThat(mobile.getLastKnownY(), is(11));
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
    }
	
	@Test
    public void FailLocateMobileUnknowMobile(){
		LocateMobileRequest locateMobileRequest = new LocateMobileRequest();
		locateMobileRequest.setMobile_station_id("m1784");
		locateMobileRequest.setTimeStamp("2018-10-04 01:01:12");
		ResponseEntity<Mobile> response = template.postForEntity(BASE_URI+"/locateMobile", locateMobileRequest, Mobile.class);
        Mobile mobile = response.getBody();
        assertThat(mobile.getUuid(), containsString("Error:"));
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
    }
}
