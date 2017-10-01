package io.tchepannou.academy.service.quiz;

import java.util.ArrayList;
import java.util.List;

public class MarkdownAnswerValidator extends TextAnswerValidator {
    @Override
    protected String normalize(final String str) {
        final String[] lines = str.split("[\\r\\n]+");
        final List<String> result = new ArrayList();
        for (int i=0 ; i<lines.length ; i++){
            final String line = lines[i]
                    .replaceAll("\\s+$", "");   // right trim
            if (line.length() > 0){
                result.add(line);
            }
        }
        return String.join("\n", result);
    }
}
