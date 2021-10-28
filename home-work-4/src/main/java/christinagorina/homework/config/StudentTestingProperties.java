package christinagorina.homework.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Data
@ConstructorBinding
@ConfigurationProperties("studenttesting")
public class StudentTestingProperties {
    private final String fileName;
    private final int success;
}
