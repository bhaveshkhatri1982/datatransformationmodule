package ee.ria.datatransformationmodule.dto;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import ee.ria.datatransformationmodule.data.BaseStation;
import ee.ria.datatransformationmodule.data.Mobile;


@Repository("mobileRepository")
@Scope("prototype")
public interface MobileRepository extends CrudRepository<Mobile, Integer>
{
	List<Mobile> findByUuid(String uuid);
}
