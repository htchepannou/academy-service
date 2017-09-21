package io.tchepannou.academy.service;

import io.tchepannou.academy.dao.QuizChoiceDao;
import io.tchepannou.academy.dao.QuizDao;
import io.tchepannou.academy.dao.QuizTypeDao;
import io.tchepannou.academy.domain.Quiz;
import io.tchepannou.academy.domain.QuizChoice;
import io.tchepannou.academy.domain.QuizType;
import io.tchepannou.academy.dto.quiz.QuizDto;
import io.tchepannou.academy.dto.quiz.QuizResponse;
import io.tchepannou.academy.exception.BusinessError;
import io.tchepannou.academy.exception.NotFoundException;
import io.tchepannou.academy.mapper.QuizMapper;
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

    public QuizResponse findById (Integer id){
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

}
