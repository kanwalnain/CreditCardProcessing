package github.kanwalnain.creditcard.rest;

import github.kanwalnain.creditcard.model.CreditCardRequest;
import github.kanwalnain.creditcard.service.CreditCardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collection;


/**
 * Credit card REST controller with api operation on credit card.
 * @author Kanwal Nain Singh
 */
@RestController
public class CreditCardController {

    private final Logger logger = LoggerFactory.getLogger(CreditCardController.class);

    @Autowired
    private CreditCardService creditCardService;

    /**
     * Api to add credit card to database.
     * @param creditCardRequest
     * @return confirmation of credit card addition.
     */
    @PostMapping(path = "/creditCards")
    public CreditCardRequest addCreditCard(@Valid @RequestBody CreditCardRequest creditCardRequest){
        return creditCardService.addCreditCard(creditCardRequest);
    }


    /**
     * Retrieve list of all the credit cards saved in system.
     * @return list of credit cards.
     */
    @GetMapping(path = "/creditCards")
    public Collection<CreditCardRequest> getCreditCards(){
        return creditCardService.getAllCreditCards();
    }
}
