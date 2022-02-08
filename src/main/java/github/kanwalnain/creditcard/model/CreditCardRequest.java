package github.kanwalnain.creditcard.model;


import github.kanwalnain.creditcard.validation.LuhnValidation;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


public class CreditCardRequest {

    @Size(max = 19, message = "Max length can be up to 19.")
    @LuhnValidation
    @NotEmpty
    private String cardNumber;

    @NotEmpty
    private String givenName;

    @NotEmpty
    private Double limit;

    public CreditCardRequest(String cardNumber, String givenName, Double limit) {
        this.cardNumber = cardNumber;
        this.givenName = givenName;
        this.limit = limit;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public Double getLimit() {
        return limit;
    }

    public void setLimit(Double limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "CreditCardRequest{" +
                "cardNumber='" + cardNumber + '\'' +
                ", givenName='" + givenName + '\'' +
                ", limit=" + limit +
                '}';
    }
}
