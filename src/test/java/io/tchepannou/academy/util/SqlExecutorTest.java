package io.tchepannou.academy.util;

import org.junit.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class SqlExecutorTest {
    final static String script = "CREATE TABLE test(id INTEGER AUTO_INCREMENT, name VARCHAR(50));\n"
            + "INSERT INTO test(name) VALUES('Herve Tchepannou');\n"
            + "INSERT INTO test(name) VALUES('Ray Sponsible');\n"
            + "INSERT INTO test(name) VALUES('John Doe');\n"
            ;

    final SqlExecutor executor = new SqlExecutor();

    @Test
    public void shouldExecuteSELECT() throws Exception{
        final List<Map<String, Object>> result = executor.executeSql(script, "SELECT * FROM TEST");

        assertThat(result).hasSize(3);
    }

    @Test
    public void shouldExecuteINSERT() throws Exception{
        final List<Map<String, Object>> result = executor.executeSql(script, "INSERT INTO test(name) VALUES('__New entry__')");

        assertThat(result).isEmpty();
    }

    @Test
    public void shouldExecuteUPDATE() throws Exception{
        final List<Map<String, Object>> result = executor.executeSql(script, "UPDATE test SET name='__test__' WHERE id=1");

        assertThat(result).isEmpty();
    }


    @Test(expected = SQLException.class)
    public void shouldFailOnSyntaxError() throws Exception{
        executor.executeSql(script, "SELECT *  TEST");
    }
}
