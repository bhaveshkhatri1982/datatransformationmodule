package ee.ria.datatransformationmodule.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.data.repository.CrudRepository;

import ee.ria.datatransformationmodule.data.BaseStation;
import ee.ria.datatransformationmodule.data.BaseStationReport;
import ee.ria.datatransformationmodule.data.Mobile;
import ee.ria.datatransformationmodule.dto.BaseStationRepository;
import ee.ria.datatransformationmodule.dto.MobileRepository;


@Service("mobileService")
@Scope("prototype")
public class MobileService 
{
	@Autowired
    private MobileRepository mobileRepository;
	
	public Mobile findByUuid(String uuid)
	{
		Mobile mobile = null;
		List<Mobile> listMobile = new ArrayList<Mobile>(); 
		mobileRepository.findByUuid(uuid).forEach(m->listMobile.add(m));
		if(listMobile!=null && listMobile.size()>0)
			mobile = listMobile.get(0);
		return mobile;
	}
	
	public void save(Mobile mobile)
	{
		mobileRepository.save(mobile);
	}
}

