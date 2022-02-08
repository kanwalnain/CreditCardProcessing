package github.kanwalnain.creditcard.rest;

import github.kanwalnain.creditcard.model.CreditCardRequest;
import github.kanwalnain.creditcard.repository.CardDetailsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
public class CreditCardController {

    private final Logger logger = LoggerFactory.getLogger(CreditCardController.class);
    private int i=1;
    @Autowired
    private CardDetailsRepository cardDetailsRepository;

    @PostMapping(path = "/creditCards")
    public String addCreditCard(@RequestBody CreditCardRequest creditCardRequest){
        cardDetailsRepository.CARD_DB.put(i, creditCardRequest);
        i++;
        return "Card inserted with id: " + (i-1);
    }


    @GetMapping(path = "/creditCards")
    public Collection<CreditCardRequest> getCreditCards(){
        return cardDetailsRepository.CARD_DB.values();
    }
}
