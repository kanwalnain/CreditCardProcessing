package github.kanwalnain.creditcard.integration.controller;

import com.google.gson.Gson;
import github.kanwalnain.creditcard.CreditCardApplication;
import github.kanwalnain.creditcard.constant.ErrorMessage;
import github.kanwalnain.creditcard.model.CreditCardRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CreditCardApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreditCartControllerIntgTest {
    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();


    @Test
    public void testAddCreditCardApiValidCase(){
        CreditCardRequest creditCardRequest = new CreditCardRequest("5555555555554444", "Dummy User",250.0);
        Gson gson = new Gson();
        headers.add("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<String>( new Gson().toJson(creditCardRequest), headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/creditCards"),
                HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testAddCreditCardApiInvalidCreditCard(){
        CreditCardRequest creditCardRequest = new CreditCardRequest("555555423425555554444", "Dummy User",250.0);
        Gson gson = new Gson();
        headers.add("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<String>( new Gson().toJson(creditCardRequest), headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/creditCards"),
                HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        List<String> expectedErrors = new LinkedList<>();
        expectedErrors.add("\""+ErrorMessage.INVALID_CREDIT_CARD+"\"");
        assertEquals(expectedErrors.toString(), response.getBody());

    }

    @Test
    public void testAddCreditCardApiInvalidInput(){
        CreditCardRequest creditCardRequest = new CreditCardRequest("555555423425555554444", "Dummy User",250.0);
        Gson gson = new Gson();
        headers.add("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<String>( new Gson().toJson(creditCardRequest), headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/creditCards"),
                HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        List<String> expectedErrors = new LinkedList<>();
        expectedErrors.add("\""+ErrorMessage.INVALID_CREDIT_CARD+"\"");
        assertEquals(expectedErrors.toString(), response.getBody());

    }
    private String createURLWithPort(String uri) {
        return "http://localhost:" + port +"/api"+ uri;
    }

}
