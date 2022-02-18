package github.kanwalnain.creditcard.service;

import github.kanwalnain.creditcard.model.CreditCardRequest;

import java.util.List;

public interface CreditCardService {
    public Boolean addCreditCard(CreditCardRequest creditCardRequest);

    public List<CreditCardRequest> getAllCreditCards();
}
