package cmis.springbootweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"Controller", "Configurations", "Daos", "Mappers", "Models", "Services", "Utils"})
public class CMISApplication {

    public static void main(String[] args) {

        SpringApplication.run(CMISApplication.class, args);
    }

}
