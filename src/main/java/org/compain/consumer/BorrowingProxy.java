package org.compain.consumer;

import org.compain.model.UserLateBorrowing;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class BorrowingProxy {

    private String token="";
    private String urlUsersBorrowing = "http://localhost:8081/api/borrowings/";

    private final RestTemplate restTemplate;

    public BorrowingProxy(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<UserLateBorrowing> getLateBorrowing(LocalDateTime today, String token){
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        header.set(HttpHeaders.AUTHORIZATION,"Bearer " + token);
        HttpEntity<String> request = new HttpEntity<>(header);
        ResponseEntity<List<UserLateBorrowing>> response = restTemplate.exchange(urlUsersBorrowing + "late-borrowing", HttpMethod.GET, request, new ParameterizedTypeReference<List<UserLateBorrowing>>() {});

        return response.getBody();
    }

    public void sendMailForLateBorrowing(UserLateBorrowing userLateBorrowing, String token){
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        header.set(HttpHeaders.AUTHORIZATION,"Bearer " + token);
        HttpEntity<UserLateBorrowing> request = new HttpEntity<>(userLateBorrowing, header);
        ResponseEntity<Void> response = restTemplate.exchange(urlUsersBorrowing + "recovery-late-borrowing", HttpMethod.POST,request,Void.class);
    }
}
