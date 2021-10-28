package christinagorina.homework;

import christinagorina.homework.service.StudentTestingService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		StudentTestingService studentTestingService = context.getBean(StudentTestingService.class);
		studentTestingService.startTesting();
	}

}
