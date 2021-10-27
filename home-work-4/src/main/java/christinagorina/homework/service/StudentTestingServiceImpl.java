package christinagorina.homework.service;

import christinagorina.homework.config.StudentTestingProperties;
import christinagorina.homework.domain.LocaleMap;
import christinagorina.homework.domain.Question;
import christinagorina.homework.utils.Util;
import com.opencsv.bean.CsvToBean;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

@EnableConfigurationProperties(StudentTestingProperties.class)
@RequiredArgsConstructor
@Service
public class StudentTestingServiceImpl implements StudentTestingService {
    private final CsvToBean<Question> csvToBean;
    private final Scanner scanner;
    private final StudentTestingProperties studentTestingProperties;
    private final MessageSource messageSource;
    private final LocaleMap localeMap;

    @Override
    public void startTesting() {
        List<Question> questions = csvToBean.parse();
        /////////////////////////////////
        Map<String, String> localeMapWithData = Util.fillInLocaleMap(localeMap);


        System.out.println(messageSource.getMessage("strings.entername",
                new String[] {":"}, Locale.forLanguageTag("ru-RU")));

        String name = scanner.nextLine();
        AtomicInteger countOfRightAnswers = new AtomicInteger();

        System.out.println(messageSource.getMessage("strings.answerquestions",
                new String[] {":"}, Locale.forLanguageTag("ru-RU")));

        questions.forEach(q -> {
            System.out.println(messageSource.getMessage(localeMapWithData.get(createLocaleKay(q.getQuestion())),
                    new String[] {":"}, Locale.forLanguageTag("ru-RU")));
            String userAnswer = scanner.nextLine();
            String userAnswerLocale = messageSource.getMessage(localeMapWithData.get(createLocaleKay(userAnswer)),
                    new String[] {""}, Locale.getDefault());
            boolean isTrue = checkAnswer(userAnswerLocale, q.getAnswer());
            if (isTrue) {
                countOfRightAnswers.getAndIncrement();
            }

        });

        System.out.println("Пользователь с именем: " + name +
                "; Количество верных ответов: " + countOfRightAnswers);

        if (countOfRightAnswers.get() >= studentTestingProperties.getSuccess()) {
            System.out.println("Пользователь получил зачет");
        } else {
            System.out.println("Пользователь получил незачет");
        }
    }

    public boolean checkAnswer(String userAnswer, String rightAnswer) {
        if (userAnswer.trim().equalsIgnoreCase(rightAnswer)) {
            return true;
        } else {
            return false;
        }
    }

    public String createLocaleKay(String question) {

        return question.toLowerCase().replaceAll(" ", "")
                .replace("?", "").trim();
    }

}
