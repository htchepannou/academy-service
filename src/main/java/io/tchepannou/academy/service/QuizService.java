package io.tchepannou.academy.service;

import io.tchepannou.academy.dao.QuizChoiceDao;
import io.tchepannou.academy.dao.QuizDao;
import io.tchepannou.academy.dao.QuizTypeDao;
import io.tchepannou.academy.domain.Quiz;
import io.tchepannou.academy.domain.QuizChoice;
import io.tchepannou.academy.domain.QuizType;
import io.tchepannou.academy.client.dto.QuizDto;
import io.tchepannou.academy.client.quiz.QuizResponse;
import io.tchepannou.academy.client.quiz.QuizAnswerRequest;
import io.tchepannou.academy.client.quiz.QuizAnswerResponse;
import io.tchepannou.academy.exception.BusinessError;
import io.tchepannou.academy.exception.NotFoundException;
import io.tchepannou.academy.mapper.QuizMapper;
import io.tchepannou.academy.service.quiz.AnswerValidator;
import io.tchepannou.academy.service.quiz.AnswerValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuizService {
    @Autowired
    private QuizDao quizDao;

    @Autowired
    private QuizTypeDao typeDao;

    @Autowired
    private QuizChoiceDao choiceDao;

    @Autowired
    private QuizMapper mapper;

    @Autowired
    private AnswerValidatorFactory answerValidatorFactory;


    public QuizResponse findById (final Integer id){
        final Quiz quiz = quizDao.findOne(id);
        if (quiz == null){
            throw new NotFoundException(BusinessError.QUIZ_NOT_FOUND);
        }

        final QuizType type = typeDao.findOne(quiz.getTypeId());
        final List<QuizChoice> choices = choiceDao.findByQuizId(id);

        final QuizDto dto = mapper.toQuizDto(quiz, type);
        dto.setChoices(
                choices
                    .stream()
                    .map(c -> mapper.toQuizChoiceDto(c))
                    .collect(Collectors.toList())
        );
        final QuizResponse response = new QuizResponse();
        response.setQuiz(dto);
        return response;
    }

    public QuizAnswerResponse answer(final Integer id, final QuizAnswerRequest request){
        final Quiz quiz = quizDao.findOne(id);
        if (quiz == null){
            throw new NotFoundException(BusinessError.QUIZ_NOT_FOUND);
        }

        final QuizType type = typeDao.findOne(quiz.getTypeId());
        final List<QuizChoice> choices = choiceDao.findByQuizId(id);
        final AnswerValidator validator = answerValidatorFactory.getValidator(type.getName());
        final boolean valid = validator.isValid(request.getValues(), quiz, choices);

        final QuizAnswerResponse response = new QuizAnswerResponse();
        response.setValid(valid);
        response.setMessage(
                valid ? quiz.getSuccessMessage() : quiz.getFailureMessage()
        );
        return response;
    }
}
