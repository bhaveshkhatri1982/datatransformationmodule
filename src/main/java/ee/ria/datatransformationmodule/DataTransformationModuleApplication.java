package ee.ria.datatransformationmodule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class DataTransformationModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataTransformationModuleApplication.class, args);
	}
}
