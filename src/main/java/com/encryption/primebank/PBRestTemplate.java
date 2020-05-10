package com.encryption.primebank;

import com.encryption.primebank.model.RequestModel;
import com.encryption.primebank.model.ResponseModel;
import com.encryption.primebank.model.UserRequest;
import com.encryption.primebank.model.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class PBRestTemplate extends RestTemplate {
    private final String baseUrl = "http://127.0.0.1:8080";
    private final String authUrl = "/authman/v3_0/usersec/authenticate";

    RestTemplate restTemplate = new RestTemplate();

    public ResponseModel postModel(RequestModel request) {
        String fullUrl = baseUrl.concat(authUrl);
//        HttpHeaders httpHeaders = restTemplate.headForHeaders(baseUrl + authUrl);
//        assertTrue(httpHeaders.getContentType().includes(MediaType.APPLICATION_JSON));
        ResponseEntity<ResponseModel> responseModel = restTemplate.postForEntity(fullUrl, request, ResponseModel.class);
        if (responseModel != null && responseModel.getBody() != null)
            return responseModel.getBody();
        else
            return new ResponseModel();
    }

    public UserRequest postUserModel(UserRequest request) {
        String fullUrl = "https://jsonplaceholder.typicode.com/posts";
        ResponseEntity<UserRequest> responseModel = restTemplate.postForEntity(fullUrl, request, UserRequest.class);

        return responseModel.getBody();
    }

    public UserResponse getModel() {
//        String fullUrl = baseUrl.concat(authUrl);
        String fullUrl = "https://jsonplaceholder.typicode.com/todos/1";
//        HttpHeaders httpHeaders = restTemplate.headForHeaders(baseUrl + authUrl);
//        assertTrue(httpHeaders.getContentType().includes(MediaType.APPLICATION_JSON));
        UserResponse responseModel = restTemplate.getForObject(fullUrl, UserResponse.class);
        System.out.println(responseModel);
        return responseModel;
    }


    private ClientHttpRequestFactory getClientHttpRequestFactory() {
        int timeout = 5000;
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory
                = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(timeout);
        return clientHttpRequestFactory;
    }
}
