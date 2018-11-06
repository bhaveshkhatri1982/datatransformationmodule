package ee.ria.datatransformationmodule.data;

import java.time.LocalDate;

import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@Scope("prototype")
public class MobileReport {

    private String mobile_station_id;
    private double distance = 0.0f;
    private String timeStamp;
}
