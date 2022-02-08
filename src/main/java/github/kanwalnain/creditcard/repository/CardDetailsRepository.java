package github.kanwalnain.creditcard.repository;

import github.kanwalnain.creditcard.model.CreditCardRequest;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class CardDetailsRepository {

    public HashMap<Integer, CreditCardRequest> CARD_DB= new HashMap<>();

}
