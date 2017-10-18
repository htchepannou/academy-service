package io.tchepannou.academy.util;

import com.google.common.base.Strings;
import org.h2.tools.RunScript;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SqlExecutor {
    private static final Logger LOGGER = LoggerFactory.getLogger(SqlExecutor.class);

    public List<Map<String, Object>> executeSql(final String initScript, final String sql) throws SQLException, IOException {
        final File file = File.createTempFile("test", ".h2");
        final String url = "jdbc:h2:" + file.getAbsolutePath();
        LOGGER.info("Opening new database: {}", file.getAbsoluteFile());

        try(final Connection cnn = DriverManager.getConnection(url, "sa", "")){
            init(initScript, cnn);

            if (isSelect(sql)) {
                final ResultSet rs = cnn.prepareStatement(sql).executeQuery();
                final List<String> columns = extractColumns(rs);

                return toRecords(columns, rs);
            } else {
                cnn.prepareStatement(sql).execute();
                return Collections.emptyList();
            }
        } finally {
            file.delete();
        }
    }

    private void init(final String script, final Connection cnn) throws SQLException{
        if (Strings.isNullOrEmpty(script)){
            return;
        }

        LOGGER.info("Initializing the DB");
        final StringReader reader = new StringReader(script);
        RunScript.execute(cnn, reader);

    }

    private List<String> extractColumns (final ResultSet rs) throws SQLException {
        final List<String> columns = new ArrayList<>();
        for (int i=1 ; i<=rs.getMetaData().getColumnCount() ; i++){
            columns.add(rs.getMetaData().getColumnName(i));
        }
        return columns;
    }

    private List<Map<String, Object>> toRecords(final List<String> columns, final ResultSet rs) throws SQLException {
        final List<Map<String, Object>> records = new ArrayList<>();
        if(rs.first()){
            do{
                final Map<String, Object> record = new HashMap<>();
                for (String column : columns){
                    record.put(column, rs.getObject(column));
                }
                records.add(record);
            } while(rs.next());
        }
        return records;
    }

    private boolean isSelect(final String sql){
        return normalize(sql).toLowerCase().trim().startsWith("select");
    }

    protected String normalize(final String str){
        return str == null ? "" : str.replaceAll("(\\r|\\n)", "");
    }

}
