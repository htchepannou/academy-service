package io.tchepannou.academy.service.quiz;

import io.tchepannou.academy.domain.Quiz;
import io.tchepannou.academy.domain.QuizChoice;
import io.tchepannou.academy.util.SqlExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class SqlAnswerValidator implements AnswerValidator {
    private static final Logger LOGGER = LoggerFactory.getLogger(SqlAnswerValidator.class);

    @Autowired
    private SqlExecutor sql;

    @Override
    public boolean isValid(final List<String> answers, final Quiz quiz, final List<QuizChoice> choices) {
        if (answers.size() != 1){
            return false;
        }

        try {

            final String initScript = quiz.getSqlInitScript();
            final List<Map<String, Object>> answer = sql.executeSql(initScript, answers.get(0));
            final List<Map<String, Object>> expected = sql.executeSql(initScript, quiz.getAnswer());
            return equals(answer, expected);

        } catch (SQLException | IOException e){

            LOGGER.error("Unable to execute the query", e);
            return false;

        }
    }

    private boolean equals(final List<Map<String, Object>> answerList, final List<Map<String, Object>> expectedList){
        // Check size
        if (answerList.size() != expectedList.size()){
            return false;
        }

        // Check the rows
        for (int i=0 ; i<answerList.size() ; i++){
            final Map<String, Object> answer = answerList.get(i);
            final Map<String, Object> expected = expectedList.get(i);
            if (!equals(answer, expected)){
                return false;
            }
        }

        return true;
    }

    private boolean equals(final Map<String, Object> answer, final Map<String, Object> expected) {
        // Check size
        if (answer.size() != expected.size()){
            return false;
        }

        // Check cells
        for (String key : answer.keySet()){
            final Object answerValue = answer.get(key);
            final Object expectedValue = expected.get(key);
            if ((answerValue == null && expectedValue  != null) || !answerValue.equals(expectedValue)) {
                return false;
            }
        }
        return true;
    }
}
