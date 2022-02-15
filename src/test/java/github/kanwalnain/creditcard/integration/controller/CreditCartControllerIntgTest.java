package github.kanwalnain.creditcard.integration.controller;

import com.google.gson.Gson;
import github.kanwalnain.creditcard.CreditCardApplication;
import github.kanwalnain.creditcard.constant.ErrorMessage;
import github.kanwalnain.creditcard.model.CreditCardRequest;
import github.kanwalnain.creditcard.repository.CardDetailsRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;


import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CreditCardApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreditCartControllerIntgTest {
    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    static HttpHeaders headers = new HttpHeaders();

    static {
        headers.add("Authorization", "Basic ZGVtb3VzZXI6ZGVtb3Bhc3M=");
        headers.add("Content-Type", "application/json");
    }

    @Autowired
    private CardDetailsRepository cardDetailsRepository;

    @After
    public void cleanup(){
        cardDetailsRepository.deleteAll();
    }
    @Test
    @DisplayName("Test case to validate successful addition of new card.")
    public void testAddCreditCardApiSuccess(){
        //Create dummy card to add.
        CreditCardRequest creditCardRequest = new CreditCardRequest("5555555555554444", "Dummy User",250.0);
        Gson gson = new Gson();
        headers.add("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<String>( new Gson().toJson(creditCardRequest), headers);

        //Call service addition.
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/creditCards"),
                HttpMethod.POST, entity, String.class);
        //assert successful creation of new card.
        assertEquals("Assert successful creation of new card.", HttpStatus.CREATED, response.getStatusCode());
    }



    @Test
    @DisplayName("Test case to validate failure in addition of new card due to bad input.")
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
    public void testGetAllCreditCardSuccessValidateSize(){
        setupDummyData();
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
         ResponseEntity<CreditCardRequest[]> creditCardRequests = restTemplate.exchange(createURLWithPort("/creditCards"), HttpMethod.GET, requestEntity, CreditCardRequest[].class);
        assertEquals("Mismatch in expected records.", 2,creditCardRequests.getBody().length);
    }

    @Test
    public void testGetAllCreditCardSuccessValidateData(){
        CreditCardRequest creditCardRequest = new CreditCardRequest("5555555555554444", "Dummy User1",250.0);
        Gson gson = new Gson();
        headers.add("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<String>( new Gson().toJson(creditCardRequest), headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/creditCards"),
                HttpMethod.POST, entity, String.class);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<CreditCardRequest[]> creditCardRequests = restTemplate.exchange(createURLWithPort("/creditCards"), HttpMethod.GET, requestEntity, CreditCardRequest[].class);
        assertEquals("Mismatch in expected records.", 1,creditCardRequests.getBody().length);
        assertEquals("CreditCard number did not match.", creditCardRequests.getBody()[0].getCardNumber(),creditCardRequest.getCardNumber());
        assertEquals("Given name did not match.", creditCardRequests.getBody()[0].getGivenName(),creditCardRequest.getGivenName());
        assertEquals("Limit did not match.", creditCardRequests.getBody()[0].getLimit(),creditCardRequest.getLimit());
        assertEquals("Balance did not match.", creditCardRequests.getBody()[0].getBalance(), new BigDecimal("0.00"));

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
