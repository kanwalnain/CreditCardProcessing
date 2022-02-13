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
    public void testAddCreditCardApiSuccess(){
        CreditCardRequest creditCardRequest = new CreditCardRequest("5555555555554444", "Dummy User",250.0);
        Gson gson = new Gson();
        headers.add("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<String>( new Gson().toJson(creditCardRequest), headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/creditCards"),
                HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }



    @Test
    public void testAddCreditCardApiInvalidCreditCardInput(){
        CreditCardRequest creditCardRequest = new CreditCardRequest("555555423425555554444", "Dummy User",250.0);
        Gson gson = new Gson();
        headers.add("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<String>( new Gson().toJson(creditCardRequest), headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/creditCards"),
                HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        assertTrue("Invalid Credit Card number", response.getBody().contains(ErrorMessage.INVALID_CREDIT_CARD));
        assertTrue("size must be between 0 and 19", response.getBody().contains(ErrorMessage.INVALID_CREDIT_CARD));
    }

    @Test
    public void testAddCreditCardApiEmptyName(){
        CreditCardRequest creditCardRequest = new CreditCardRequest("555555423425555554444", "",250.0);
        Gson gson = new Gson();
        headers.add("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<String>( new Gson().toJson(creditCardRequest), headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/creditCards"),
                HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue("must not be blank", response.getBody().contains(ErrorMessage.INVALID_CREDIT_CARD));
    }
    @Test
    public void testGetAllCreditCardSuccess(){
        setupDummyData();
        ResponseEntity<List> creditCardRequests = restTemplate.getForEntity(createURLWithPort("/creditCards"), List.class);
        assertEquals("Mismatch in expected records.", 3,creditCardRequests.getBody().size());

    }


    public void setupDummyData(){
        CreditCardRequest creditCardRequest = new CreditCardRequest("5555555555554444", "Dummy User1",250.0);
        Gson gson = new Gson();
        headers.add("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<String>( new Gson().toJson(creditCardRequest), headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/creditCards"),
                HttpMethod.POST, entity, String.class);
        creditCardRequest.setCardNumber("6703 4444 4444 4449");
        creditCardRequest.setGivenName("Dummy User 2");
        restTemplate.exchange(
                createURLWithPort("/creditCards"),
                HttpMethod.POST, entity, String.class);
    }
    private String createURLWithPort(String uri) {
        return "http://localhost:" + port +"/api"+ uri;
    }

}
