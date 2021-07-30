package com.effective.ecommerce.clean.testutils.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.http.HttpMethod.*;

public final class RequestSpec {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private MockHttpServletRequestBuilder requestBuilder;

    private HttpMethod method;
    private String uri;
    private Object body;

    private RequestSpec(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    public RequestSpec andGet() {
        return andMethod(GET);
    }

    public RequestSpec andPost(String uri) {
        return andPath(POST, uri);
    }

    public RequestSpec andPost() {
        return andMethod(POST);
    }

    public RequestSpec andPut() {
        return andMethod(PUT);
    }

    public RequestSpec andDelete() {
        return andMethod(DELETE);
    }

    public RequestSpec andMethod(HttpMethod method) {
        this.method = method;
        return this;
    }

    public RequestSpec andUri(String uri) {
        this.uri = uri;
        return this;
    }

    public RequestSpec andPath(HttpMethod method, String uri) {
        return andMethod(method).andUri(uri);
    }

    public RequestSpec andBody(Object body) {
        this.body = body;
        return this;
    }

    public ResponseSpec whenMakeRequest() throws Exception {
        if (method == null) throw new IllegalStateException("Method is not set");
        if (uri == null) throw new IllegalStateException("URI is not set");
        requestBuilder = MockMvcRequestBuilders
                .request(method, uri)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        if (body != null) {
            var content = objectMapper.writeValueAsString(body);
            requestBuilder = requestBuilder.content(content);
        }

        var resultActions = mockMvc.perform(requestBuilder);
        return new ResponseSpec(objectMapper, resultActions);
    }

    public ResponseSpec makeRequest() throws Exception {
        return whenMakeRequest();
    }


    public static RequestSpec givenHttpRequest(MockMvc mockMvc, ObjectMapper objectMapper) {
        return new RequestSpec(mockMvc, objectMapper);
    }
}
