package tacos.tacocloudrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class TacoCloudRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TacoCloudRestApplication.class, args);
	}

}
