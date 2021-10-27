package christinagorina.homework.config;

import christinagorina.homework.domain.LocaleMap;
import christinagorina.homework.domain.Question;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(StudentTestingProperties.class)
public class ProjectConfig {

    private final StudentTestingProperties studentTestingProperties;

    @Bean
    public CsvToBean<Question> csvToBean() {
        InputStream fileInputStream = getClass()
                .getClassLoader()
                .getResourceAsStream(studentTestingProperties.getFileName());
        return new CsvToBeanBuilder(new InputStreamReader(fileInputStream))
                .withType(Question.class)
                .build();
    }

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }

    @Bean
    public LocaleMap localeMap() {
        return new LocaleMap();
    }

}
