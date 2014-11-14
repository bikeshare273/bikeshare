import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import controller.BikeShareController;

@ComponentScan
@EnableAutoConfiguration
public class BikeShareLoader {

	public static void main(String[] args) {
		SpringApplication.run(BikeShareController.class, args);
	}

}
