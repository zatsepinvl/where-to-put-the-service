package com.effective.ecommerce.clean.testutils.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import java.io.IOException;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public final class ResponseSpec {

    public enum StatusCode {
        STATUS_200(status().isOk()),
        STATUS_201(status().isCreated()),
        STATUS_400(status().isBadRequest()),
        STATUS_403(status().isForbidden()),
        STATUS_404(status().isNotFound()),
        STATUS_409(status().isConflict()),
        STATUS_500(status().isInternalServerError());

        private final ResultMatcher resultMatcher;

        StatusCode(ResultMatcher resultMatcher) {
            this.resultMatcher = resultMatcher;
        }
    }

    private final ObjectMapper objectMapper;
    private ResultActions resultActions;

    public ResponseSpec(ObjectMapper objectMapper, ResultActions resultActions) {
        this.objectMapper = objectMapper;
        this.resultActions = resultActions;
    }

    public ResponseSpec thenStatusShouldBe(StatusCode code) throws Exception {
        resultActions = resultActions.andExpect(code.resultMatcher);
        return this;
    }

    public <T> T getBody(Class<T> valueType) throws IOException {
        var content = resultActions.andReturn()
                .getResponse()
                .getContentAsString();
        return objectMapper.readValue(content, valueType);
    }
}
