package io.tchepannou.academy.mapper;

import io.tchepannou.academy.domain.Quiz;
import io.tchepannou.academy.domain.QuizChoice;
import io.tchepannou.academy.domain.QuizType;
import io.tchepannou.academy.dto.quiz.QuizChoiceDto;
import io.tchepannou.academy.dto.quiz.QuizDto;
import org.springframework.stereotype.Component;

@Component
public class QuizMapper {
    public QuizDto toQuizDto(final Quiz quiz, final QuizType type){
        final QuizDto dto = new QuizDto();
        dto.setAnswer(quiz.getAnswer());
        dto.setDescription(quiz.getDescription());
        dto.setId(quiz.getId());
        dto.setQuestion(quiz.getQuestion());
        dto.setType(type.getName());
        dto.setVideoId(quiz.getVideoId());
        return dto;
    }

    public QuizChoiceDto toQuizChoiceDto(final QuizChoice choice){
        final QuizChoiceDto dto = new QuizChoiceDto();
        dto.setAnswer(choice.isAnswer());
        dto.setId(choice.getId());
        dto.setRank(choice.getRank());
        dto.setText(choice.getText());
        return dto;
    }

}
