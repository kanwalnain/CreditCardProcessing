package github.kanwalnain.creditcard.unit.controller;

import com.google.gson.Gson;
import github.kanwalnain.creditcard.model.CreditCardRequest;
import github.kanwalnain.creditcard.rest.CreditCardController;
import github.kanwalnain.creditcard.service.impl.CreditCardServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CreditCardController.class)
public class CreditCardControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CreditCardServiceImpl creditCardService;

    @Test
    @DisplayName("Test to validate new addition of card.")
    public void testAddNewCreditCardApiSuccessfully() throws Exception{
     //Create sample card.
        CreditCardRequest creditCardRequest = new CreditCardRequest();
        creditCardRequest.setGivenName("A B Singh");
        creditCardRequest.setCardNumber("5555555555554444");
        creditCardRequest.setLimit(new BigDecimal(250.0));
        creditCardRequest.setBalance(new BigDecimal(50.0));
        creditCardService.addCreditCard(Mockito.any(CreditCardRequest.class));
        mvc.perform(MockMvcRequestBuilders.post("/creditCards")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Basic ZGVtb3VzZXI6ZGVtb3Bhc3M=")
                        .content(new Gson().toJson(creditCardRequest).toString()))
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    @DisplayName("Test to validate failure in case of duplicate credit card..")
    public void testAddNewCreditCardApiDuplicateCard()  throws Exception{
        //Create sample card.
        CreditCardRequest creditCardRequest = new CreditCardRequest();
        creditCardRequest.setGivenName("A B Singh");
        creditCardRequest.setCardNumber("5555555555554444");
        creditCardRequest.setLimit(new BigDecimal(250.0));
        creditCardRequest.setBalance(new BigDecimal(50.0));
        Mockito.doThrow(new DataIntegrityViolationException("")).when(creditCardService).addCreditCard(Mockito.any(CreditCardRequest.class));

        mvc.perform(MockMvcRequestBuilders.post("/creditCards")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Basic ZGVtb3VzZXI6ZGVtb3Bhc3M=")
                        .content(new Gson().toJson(creditCardRequest).toString()))
                .andExpect(MockMvcResultMatchers.status().isConflict());
    }

    @Test
    @DisplayName("Test to validate failure in case of invalid credit card..")
    public void testAddNewCreditCardApiInvalidCard()  throws Exception{
        //Create sample card.
        CreditCardRequest creditCardRequest = new CreditCardRequest();
        creditCardRequest.setGivenName("A B Singh");
        creditCardRequest.setCardNumber("55555555sddsd55554444");
        creditCardRequest.setLimit(new BigDecimal(250.0));
        creditCardRequest.setBalance(new BigDecimal(50.0));

        //Mockito.when(creditCardService.addCreditCard(Mockito.any(CreditCardRequest.class))).thenThrow(new DataIntegrityViolationException("Duplicate Data"){});
        mvc.perform(MockMvcRequestBuilders.post("/creditCards")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Basic ZGVtb3VzZXI6ZGVtb3Bhc3M=")
                        .content(new Gson().toJson(creditCardRequest).toString()))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


    @Test
    @DisplayName("Test to fetch all  credit cards")
    public void testGetAllCreditCardApi()  throws Exception{

        //Create sample card.
        CreditCardRequest creditCardRequest = new CreditCardRequest();
        creditCardRequest.setGivenName("A B Singh");
        creditCardRequest.setCardNumber("5555555555554444");
        creditCardRequest.setLimit(new BigDecimal(250.0));
        creditCardRequest.setBalance(new BigDecimal(50.0));

        //Mock getAllCredits service call
        Mockito.when(creditCardService.getAllCreditCards()).thenReturn(List.of(creditCardRequest));
        mvc.perform(MockMvcRequestBuilders.get("/creditCards")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Basic ZGVtb3VzZXI6ZGVtb3Bhc3M="))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    @DisplayName("Unauthorised user trying to get credit cards")
    public void testInvalidUserGetAllCreditCardApi()  throws Exception{

        //Create sample card.
        CreditCardRequest creditCardRequest = new CreditCardRequest();
        creditCardRequest.setGivenName("A B Singh");
        creditCardRequest.setCardNumber("5555555555554444");
        creditCardRequest.setLimit(new BigDecimal(250.0));
        creditCardRequest.setBalance(new BigDecimal(50.0));

        //Mock getAllCredits service call
        Mockito.when(creditCardService.getAllCreditCards()).thenReturn(List.of(creditCardRequest));
        mvc.perform(MockMvcRequestBuilders.get("/creditCards")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }
}
