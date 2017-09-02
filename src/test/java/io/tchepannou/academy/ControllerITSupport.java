package io.tchepannou.academy;

import io.tchepannou.academy.exception.BusinessError;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class ControllerITSupport {
    protected ResultActions expectBusinessError(final int index, final BusinessError error, final ResultActions action) throws Exception {
        final String prefix = "$.errors[" + index + "]";
        return action
                .andExpect(jsonPath(prefix + "code", is(error.getCode())))
                .andExpect(jsonPath(prefix + "description", is(error.getDescription())))
                ;
    }
}
