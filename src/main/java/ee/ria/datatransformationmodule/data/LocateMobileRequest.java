package ee.ria.datatransformationmodule.data;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@Scope("prototype")
public class LocateMobileRequest {

    private String mobile_station_id;
    private String timeStamp;
}
