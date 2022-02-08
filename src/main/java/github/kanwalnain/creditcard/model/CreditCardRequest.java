package github.kanwalnain.creditcard.model;


import github.kanwalnain.creditcard.validation.LuhnValidation;

public class CreditCardRequest {

    @LuhnValidation
    private String cardNumber;
    private String givenName;
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
