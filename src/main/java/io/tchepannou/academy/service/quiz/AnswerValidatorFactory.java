package io.tchepannou.academy.service.quiz;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AnswerValidatorFactory {
    private Map<String, AnswerValidator> validators;

    public AnswerValidatorFactory(){
        validators = new HashMap<>();
        validators.put("singlechoice", new SingleChoiceAnswerValidator());
        validators.put("multichoice", new MultiChoiceAnswerValidator());
        validators.put("text", new TextAnswerValidator());
    }
    public AnswerValidator getValidator(final String name){
        final AnswerValidator validator = validators.get(name.toLowerCase());
        if (validator == null){
            throw new IllegalArgumentException("Validator not supported: " + name);
        }

        return validator;
    }
}
