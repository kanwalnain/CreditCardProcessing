package github.kanwalnain.creditcard.model;


import github.kanwalnain.creditcard.entity.CreditCardEntity;
import github.kanwalnain.creditcard.unit.validation.LuhnValidation;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class CreditCardRequest {

    @LuhnValidation
    @NotBlank
    @Size(max = 19)
    private String cardNumber;

    @NotBlank
    private String givenName;

    private Double limit;


    public CreditCardRequest(String cardNumber, String givenName, Double limit) {
        this.cardNumber = cardNumber;
        this.givenName = givenName;
        this.limit = limit;
    }

    public CreditCardRequest(CreditCardEntity creditCardEntity) {
         this.cardNumber = creditCardEntity.getCreditCardNumber();
         this.givenName = creditCardEntity.getCreditCardNumber();
         this.limit = creditCardEntity.getCreditLimit().doubleValue();
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
