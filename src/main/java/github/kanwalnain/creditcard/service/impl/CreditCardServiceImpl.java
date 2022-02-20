package github.kanwalnain.creditcard.service.impl;

import github.kanwalnain.creditcard.entity.CreditCardEntity;
import github.kanwalnain.creditcard.model.CreditCardRequest;
import github.kanwalnain.creditcard.repository.CardDetailsRepository;
import github.kanwalnain.creditcard.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CreditCardServiceImpl implements CreditCardService {

    @Autowired
    private CardDetailsRepository cardDetailsRepository;

    @Override
    public Boolean addCreditCard(CreditCardRequest creditCardRequest){
        //Set default balance as ZERO if no input.
        Optional<BigDecimal> balance = Optional.ofNullable(creditCardRequest.getBalance());
        CreditCardEntity creditCardEntity = CreditCardEntity.builder().creditCardRequest(creditCardRequest).balance(balance.orElse(BigDecimal.ZERO)).build();
        cardDetailsRepository.save(creditCardEntity);
        return Boolean.TRUE;
    }
    @Override
    public List<CreditCardRequest> getAllCreditCards() {
        List<CreditCardRequest> creditCardRequests = new ArrayList<>();
        creditCardRequests = cardDetailsRepository.findAll().stream().map(creditCard-> new CreditCardRequest(creditCard)).collect(Collectors.toList());
        return cardDetailsRepository.findAll().stream().map(creditCard-> new CreditCardRequest(creditCard)).collect(Collectors.toList());

    }
}
