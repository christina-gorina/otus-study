package christinagorina.homework.service;

public interface StudentTestingService {

    void startTesting();

    boolean checkAnswer(String userAnswer, String rightAnswer);

    String createLocaleKay(String question);
}
