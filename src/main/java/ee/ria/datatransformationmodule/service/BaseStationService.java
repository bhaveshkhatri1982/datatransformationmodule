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
import ee.ria.datatransformationmodule.data.Mobile;
import ee.ria.datatransformationmodule.dto.BaseStationRepository;


@Service("baseStationService")
@Scope("prototype")
public class BaseStationService 
{
	@Autowired
    private BaseStationRepository baseStationRepository;
	
	public List<BaseStation> findByUuid(String uuid)
	{
		List<BaseStation> listBaseStation = new ArrayList<BaseStation>();
		System.out.println("found size = "+baseStationRepository.findByUuid(uuid).size());
		baseStationRepository.findByUuid(uuid).forEach(b->listBaseStation.add(b));
		
		return listBaseStation;
	}
}

