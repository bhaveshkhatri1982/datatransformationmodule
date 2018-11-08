package ee.ria.datatransformationmodule.utility;

import java.util.HashMap;
import java.util.List;

import ee.ria.datatransformationmodule.data.BaseStationReport;
import ee.ria.datatransformationmodule.data.Mobile;

public class MobileLocator 
{
	public static HashMap<String, Integer> locateMobile(List<BaseStationReport> listBaseStationReport)
	{
		HashMap<String, Integer> mapLocation = new HashMap<String, Integer>();
		if(listBaseStationReport.size()>2)
		{	
			//mathematical expression reference
			//https://books.google.ee/books?id=Ki2DMaeeHpUC&pg=PA79&lpg=PA79&dq=trilateration+algorithm+2d+java&source=bl&ots=8YGZE33BEF&sig=kZII8OWtpCccJxPIuvtGmjTNr-A&hl=en&sa=X&ved=2ahUKEwiarp3cpcTeAhWFjSwKHcKkBxM4ChDoATALegQIARAB#v=onepage&q=trilateration%20algorithm%202d%20java&f=false
			int x = 0;
			int y = 0;
			
			//variables 
			double x1 = Utils.parseDouble(listBaseStationReport.get(0).getBaseStation().getX());
			double x2 = Utils.parseDouble(listBaseStationReport.get(1).getBaseStation().getX());
			double x3 = Utils.parseDouble(listBaseStationReport.get(2).getBaseStation().getX());
			
			double y1 = Utils.parseDouble(listBaseStationReport.get(0).getBaseStation().getY());
			double y2 = Utils.parseDouble(listBaseStationReport.get(1).getBaseStation().getY());
			double y3 = Utils.parseDouble(listBaseStationReport.get(2).getBaseStation().getY());
			
			double d1 = Utils.parseDouble(listBaseStationReport.get(0).getDistance());
			double d2 = Utils.parseDouble(listBaseStationReport.get(1).getDistance());
			double d3 = Utils.parseDouble(listBaseStationReport.get(2).getDistance());
			
			double numerator1 = (x2-x1)*(Math.pow(x3,2.0)+Math.pow(y3,2.0)- Math.pow(d3,2.0)) +  
								(x1-x3)*(Math.pow(x2,2.0)+Math.pow(y2,2.0)- Math.pow(d2,2.0)) +
								(x3-x2)*(Math.pow(x1,2.0)+Math.pow(y1,2.0)- Math.pow(d1,2.0));
			
			double denominator1 = 2*(y3*(x2-x1)+
									 y2*(x1-x3)+
									 y1*(x3-x2));

			
			y = (int) Math.ceil(numerator1/denominator1);
			
			
			double numerator2 = Math.pow(d2, 2.0) - Math.pow(d1, 2.0) +
								Math.pow(x1, 2.0) - Math.pow(x2, 2.0) + 
								Math.pow(y1, 2.0) - Math.pow(y2, 2.0) -
								2*(y1-y2)*y;
			
			double denominator2 = 2*(x1-x2);
			
			x = (int) Math.ceil(numerator2/denominator2);
			
			mapLocation.put("x", x);
			mapLocation.put("y", y);
		}
		return mapLocation;
	}
}
