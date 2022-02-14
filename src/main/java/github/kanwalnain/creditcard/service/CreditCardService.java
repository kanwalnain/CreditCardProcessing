package github.kanwalnain.creditcard.service;

import github.kanwalnain.creditcard.entity.CreditCardEntity;
import github.kanwalnain.creditcard.model.CreditCardRequest;
import github.kanwalnain.creditcard.repository.CardDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreditCardService {
    @Autowired
    private CardDetailsRepository cardDetailsRepository;

    public CreditCardRequest addCreditCard(CreditCardRequest creditCardRequest) {
        return new CreditCardRequest(cardDetailsRepository.save(new CreditCardEntity(creditCardRequest)));
    }

    public List<CreditCardRequest> getAllCreditCards() {
        return cardDetailsRepository.findAll().stream().map(creditCard-> new CreditCardRequest(creditCard)).collect(Collectors.toList());
    }
}
