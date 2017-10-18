package io.tchepannou.academy.service.quiz;

import io.tchepannou.academy.domain.Quiz;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
public class QuizSqlExecutor {
    public List<Map<String, Object>> executeSql(final Quiz quiz, final String sql) throws SQLException{
        return Collections.emptyList();
    }
}
