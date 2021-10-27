package christinagorina.homework;

import christinagorina.homework.service.StudentTestingServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class ApplicationTests {

	@Autowired
	private StudentTestingServiceImpl studentTestingServiceImpl;

	@Test
	public void checkAnswerRight() {
		String userAnswer = "rightAnswer";
		String rightAnswer = "rightAnswer";
		assertThat(studentTestingServiceImpl.checkAnswer(userAnswer, rightAnswer)).isTrue();
	}

	@Test
	public void checkAnswerWrong() {
		String userAnswer = "wrongUserAnswer";
		String rightAnswer = "rightAnswer";
		assertThat(studentTestingServiceImpl.checkAnswer(userAnswer, rightAnswer)).isFalse();
	}

	@Test
	public void checkAnswerWithSpaceAndWrongRegister() {
		String userAnswer = "  rightanswer ";
		String rightAnswer = "rightAnswer";
		assertThat(studentTestingServiceImpl.checkAnswer(userAnswer, rightAnswer)).isTrue();
	}

}



