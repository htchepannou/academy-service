package io.tchepannou.academy.service.quiz;

import io.tchepannou.academy.domain.Quiz;
import io.tchepannou.academy.util.SqlExecutor;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SqlAnswerValidatorTest {
    @Mock
    private Quiz quiz;

    @Mock
    private SqlExecutor sql;

    @InjectMocks
    private SqlAnswerValidator validator;

    @Test
    @Ignore
    public void shouldReturnTrueWhenAnswerIsValid() throws Exception {
        // Given
        final String expected = "SELECT * FROM test";
        final List<Map<String, Object>> expectedItems = createRecordset();
        when(quiz.getAnswer()).thenReturn(expected);
        when(sql.executeSql(anyString(), expected)).thenReturn(expectedItems);

        final String answer = "SELECT id, firstName, lastName FROM test";
        final List<Map<String, Object>> answerItems = expectedItems;
        when(sql.executeSql(anyString(), answer)).thenReturn(answerItems);

        // When
        final boolean result = validator.isValid(Collections.singletonList(answer), quiz,null);

        // Then
        assertThat(result).isTrue();
    }

    @Test
    @Ignore
    public void shouldReturnFalseWhenRecordCountDoesntMismatch() throws Exception {
        // Given
        final String expected = "SELECT * FROM test";
        final List<Map<String, Object>> expectedItems = createRecordset();
        when(quiz.getAnswer()).thenReturn(expected);
        when(sql.executeSql(anyString(), expected)).thenReturn(expectedItems);

        final String answer = "SELECT id, firstName, lastName FROM test";
        final List<Map<String, Object>> answerItems = createRecordset();
        answerItems.add(createRecord(1, "Sean", "Connery"));
        when(sql.executeSql(anyString(), answer)).thenReturn(answerItems);

        // When
        final boolean result = validator.isValid(Collections.singletonList(answer), quiz,null);

        // Then
        assertThat(result).isFalse();
    }

    @Test
    @Ignore
    public void shouldReturnFalseWhenCellCountMismatch() throws Exception {
        // Given
        final String expected = "SELECT * FROM test";
        final List<Map<String, Object>> expectedItems = createRecordset();
        when(quiz.getAnswer()).thenReturn(expected);
        when(sql.executeSql(anyString(), expected)).thenReturn(expectedItems);

        final String answer = "SELECT id, firstName, lastName FROM test";
        final List<Map<String, Object>> answerItems = createRecordset();
        answerItems.get(2).remove("lastName");
        when(sql.executeSql(anyString(), answer)).thenReturn(answerItems);

        // When
        final boolean result = validator.isValid(Collections.singletonList(answer), quiz,null);

        // Then
        assertThat(result).isFalse();
    }

    @Test
    @Ignore
    public void shouldReturnFalseWhenCellMismatch() throws Exception {
        // Given
        final String expected = "SELECT * FROM test";
        final List<Map<String, Object>> expectedItems = createRecordset();
        when(quiz.getAnswer()).thenReturn(expected);
        when(sql.executeSql(anyString(), expected)).thenReturn(expectedItems);

        final String answer = "SELECT id, firstName, lastName FROM test";
        final List<Map<String, Object>> answerItems = createRecordset();
        answerItems.get(2).put("lastName", "Wu");
        when(sql.executeSql(anyString(), answer)).thenReturn(answerItems);

        // When
        final boolean result = validator.isValid(Collections.singletonList(answer), quiz,null);

        // Then
        assertThat(result).isFalse();
    }

    private List<Map<String, Object>> createRecordset(){
        return new ArrayList<>(Arrays.asList(
                createRecord(1, "Ray", "Sponsible"),
                createRecord(2, "John", "Doe"),
                createRecord(3, "Jane", "Smith")
        ));
    }
    private Map<String, Object> createRecord(Integer id, String firstName, String lastName){
        final Map<String, Object> record = new HashMap<>();
        record.put("id", id);
        record.put("firstName", firstName);
        record.put("lastName", lastName);
        return record;
    }
}
