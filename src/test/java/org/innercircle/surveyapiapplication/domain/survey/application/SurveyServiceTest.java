package org.innercircle.surveyapiapplication.domain.survey.application;

import jakarta.persistence.EntityManager;
import org.innercircle.surveyapiapplication.domain.question.application.QuestionService;
import org.innercircle.surveyapiapplication.domain.question.domain.LongAnswerQuestion;
import org.innercircle.surveyapiapplication.domain.question.domain.MultiChoiceQuestion;
import org.innercircle.surveyapiapplication.domain.question.domain.Question;
import org.innercircle.surveyapiapplication.domain.question.domain.ShortAnswerQuestion;
import org.innercircle.surveyapiapplication.domain.question.domain.SingleChoiceQuestion;
import org.innercircle.surveyapiapplication.domain.survey.domain.Survey;
import org.innercircle.surveyapiapplication.global.exception.CustomException;
import org.innercircle.surveyapiapplication.global.exception.CustomExceptionStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("local")
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class SurveyServiceTest {

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private EntityManager entityManager;

    @Test
    @DisplayName("[SUCCESS] 단답형 설문항목을 가진 설문조사를 생성할 수 있다.")
    void createSurveyWithShortAnswerQuestion() {
        // given
        final Long surveyId = 1L;
        final Long questionId = 1L;

        Survey survey = new Survey(surveyId, "설문조사이름", "설문조사설명");
        ShortAnswerQuestion question = new ShortAnswerQuestion(questionId,
            "설문항목이름",
            "설문항목설명",
            false);

        survey = survey.addQuestion(question);

        // when
        surveyService.createSurvey(survey);
        entityManager.flush();

        // then
        Survey savedSurvey = surveyService.findById(surveyId);
        ShortAnswerQuestion savedQuestion = (ShortAnswerQuestion) questionService.findById(questionId);

        assertThat(savedSurvey.getId()).isEqualTo(surveyId);
        assertThat(savedSurvey.getName()).isEqualTo(survey.getName());
        assertThat(savedSurvey.getDescription()).isEqualTo(survey.getDescription());
        assertThat(savedSurvey.getQuestions()).containsAll(survey.getQuestions());

        assertThat(savedQuestion.getId()).isEqualTo(questionId);
        assertThat(savedQuestion.getName()).isEqualTo(question.getName());
        assertThat(savedQuestion.getDescription()).isEqualTo(question.getDescription());
        assertThat(savedQuestion.getSurveyId()).isEqualTo(surveyId);
    }

    @Test
    @DisplayName("[SUCCESS] 장문형 설문항목을 가진 설문조사를 생성할 수 있다.")
    void createSurveyWithLongAnswerQuestion() {
        // given
        final Long surveyId = 1L;
        final Long questionId = 1L;

        Survey survey = new Survey(surveyId, "설문조사이름", "설문조사설명");
        LongAnswerQuestion question = new LongAnswerQuestion(questionId,
            "설문항목이름",
            "설문항목설명",
            false);
        survey.addQuestion(question);

        // when
        surveyService.createSurvey(survey);

        // then
        Survey savedSurvey = surveyService.findById(surveyId);
        LongAnswerQuestion savedQuestion = (LongAnswerQuestion) questionService.findById(questionId);

        assertThat(savedSurvey.getId()).isEqualTo(surveyId);
        assertThat(savedSurvey.getName()).isEqualTo(survey.getName());
        assertThat(savedSurvey.getDescription()).isEqualTo(survey.getDescription());
        assertThat(savedSurvey.getQuestions()).containsAll(survey.getQuestions());

        assertThat(savedQuestion.getId()).isEqualTo(questionId);
        assertThat(savedQuestion.getName()).isEqualTo(question.getName());
        assertThat(savedQuestion.getDescription()).isEqualTo(question.getDescription());
        assertThat(savedQuestion.getSurveyId()).isEqualTo(surveyId);
    }

    @Test
    @DisplayName("[SUCCESS] 단항선택형 설문항목을 가진 설문조사를 생성할 수 있다.")
    void createSurveyWithSingleChoiceQuestion() {
        // given
        final Long surveyId = 1L;
        final Long questionId = 1L;

        Survey survey = new Survey(surveyId, "설문조사이름", "설문조사설명");
        SingleChoiceQuestion question = new SingleChoiceQuestion(questionId,
            "설문항목이름",
            "설문항목설명",
            false,
            List.of("설문항목답변1", "설문항목답변2", "설문항목답변3", "설문항목답변4", "설문항목답변5")
        );
        survey.addQuestion(question);

        // when
        surveyService.createSurvey(survey);

        // then
        Survey savedSurvey = surveyService.findById(surveyId);
        SingleChoiceQuestion savedQuestion = (SingleChoiceQuestion) questionService.findById(questionId);

        assertThat(savedSurvey.getId()).isEqualTo(surveyId);
        assertThat(savedSurvey.getName()).isEqualTo(survey.getName());
        assertThat(savedSurvey.getDescription()).isEqualTo(survey.getDescription());
        assertThat(savedSurvey.getQuestions()).containsAll(survey.getQuestions());

        assertThat(savedQuestion.getId()).isEqualTo(questionId);
        assertThat(savedQuestion.getName()).isEqualTo(question.getName());
        assertThat(savedQuestion.getDescription()).isEqualTo(question.getDescription());
        assertThat(savedQuestion.getOptions()).containsAll(question.getOptions());
        assertThat(savedQuestion.getSurveyId()).isEqualTo(surveyId);
    }

    @Test
    @DisplayName("[SUCCESS] 다항선택형 설문항목을 가진 설문조사를 생성할 수 있다.")
    void createSurveyWithMultiChoiceQuestion() {
        // given
        final Long surveyId = 1L;
        final Long questionId = 1L;

        Survey survey = new Survey(surveyId, "설문조사이름", "설문조사설명");
        MultiChoiceQuestion question = new MultiChoiceQuestion(questionId,
            "설문항목이름",
            "설문항목설명",
            false,
            List.of("설문항목답변1", "설문항목답변2", "설문항목답변3", "설문항목답변4", "설문항목답변5")
        );
        survey.addQuestion(question);

        // when
        surveyService.createSurvey(survey);

        // then
        Survey savedSurvey = surveyService.findById(surveyId);
        MultiChoiceQuestion savedQuestion = (MultiChoiceQuestion) questionService.findById(questionId);

        assertThat(savedSurvey.getId()).isEqualTo(surveyId);
        assertThat(savedSurvey.getName()).isEqualTo(survey.getName());
        assertThat(savedSurvey.getDescription()).isEqualTo(survey.getDescription());
        assertThat(savedSurvey.getQuestions()).containsAll(survey.getQuestions());

        assertThat(savedQuestion.getId()).isEqualTo(questionId);
        assertThat(savedQuestion.getName()).isEqualTo(question.getName());
        assertThat(savedQuestion.getDescription()).isEqualTo(question.getDescription());
        assertThat(savedQuestion.getOptions()).containsAll(question.getOptions());
        assertThat(savedQuestion.getSurveyId()).isEqualTo(surveyId);
    }

    @Test
    @DisplayName("[FAILURE] 10개 이상의 설문항목을 가진 설문조사는 생성될 수 없다.")
    void failToCreateSurveyWhenSurveyHaveQuestionOverTen() {
        // given
        final Long surveyId = 1L;

        Survey survey = new Survey(surveyId, "설문조사이름", "설문조사설명");
        List<Question> questions = List.of(
            new ShortAnswerQuestion(1L, "설문조사이름", "설문항목설명", false),
            new ShortAnswerQuestion(2L, "설문조사이름", "설문항목설명", false),
            new ShortAnswerQuestion(3L, "설문조사이름", "설문항목설명", false),
            new ShortAnswerQuestion(4L, "설문조사이름", "설문항목설명", false),
            new ShortAnswerQuestion(5L, "설문조사이름", "설문항목설명", false),
            new ShortAnswerQuestion(6L, "설문조사이름", "설문항목설명", false),
            new ShortAnswerQuestion(7L, "설문조사이름", "설문항목설명", false),
            new ShortAnswerQuestion(8L, "설문조사이름", "설문항목설명", false),
            new ShortAnswerQuestion(9L, "설문조사이름", "설문항목설명", false),
            new ShortAnswerQuestion(10L, "설문조사이름", "설문항목설명", false),
            new ShortAnswerQuestion(11L, "설문조사이름", "설문항목설명", false)
        );

        assertThatThrownBy(() -> survey.addQuestions(questions))
            .isInstanceOf(CustomException.class)
            .extracting(e -> ((CustomException) e).getStatus())
            .isEqualTo(CustomExceptionStatus.QUESTION_SIZE_FULL);
    }

}