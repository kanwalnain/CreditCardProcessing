package github.kanwalnain.creditcard.rest;

import github.kanwalnain.creditcard.model.CreditCardRequest;
import github.kanwalnain.creditcard.repository.CardDetailsRepository;
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


@RestController
public class CreditCardController {

    private final Logger logger = LoggerFactory.getLogger(CreditCardController.class);

    @Autowired
    private CreditCardService creditCardService;

    @PostMapping(path = "/creditCards")
    public CreditCardRequest addCreditCard(@Valid @RequestBody CreditCardRequest creditCardRequest){
        return creditCardService.addCreditCard(creditCardRequest);
    }


    @GetMapping(path = "/creditCards")
    public Collection<CreditCardRequest> getCreditCards(){
        return creditCardService.getAllCreditCards();
    }
}
