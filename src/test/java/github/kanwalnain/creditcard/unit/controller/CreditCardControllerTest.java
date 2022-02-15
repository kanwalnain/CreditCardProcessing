package github.kanwalnain.creditcard.unit.controller;

import github.kanwalnain.creditcard.model.CreditCardRequest;
import github.kanwalnain.creditcard.rest.CreditCardController;
import github.kanwalnain.creditcard.service.CreditCardService;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CreditCardControllerTest {
    @InjectMocks
    private CreditCardController creditCardController;
    @Mock
    private CreditCardService creditCardService;

    @Test
    @DisplayName("Test to validate new addition of card.")
    public void testAddNewCreditCardApiSuccessfully(){
     //Create sample card.
        CreditCardRequest creditCardRequest = new CreditCardRequest();
        creditCardRequest.setGivenName("A B Singh");
        creditCardRequest.setCardNumber("5555555555554444");

    }
}
