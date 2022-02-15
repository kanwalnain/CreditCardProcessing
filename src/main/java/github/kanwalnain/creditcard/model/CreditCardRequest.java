package github.kanwalnain.creditcard.model;


import github.kanwalnain.creditcard.entity.CreditCardEntity;
import github.kanwalnain.creditcard.service.EncryptionService;
import github.kanwalnain.creditcard.unit.validation.LuhnValidation;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;


public class CreditCardRequest {

    @LuhnValidation
    @NotBlank
    @Size(max = 19)
    private String cardNumber;

    @NotBlank
    private String givenName;

    private Double limit;

    private BigDecimal balance;

    public CreditCardRequest() {
    }

    public CreditCardRequest(String cardNumber, String givenName, Double limit, BigDecimal balance) {
        this.cardNumber = cardNumber;
        this.givenName = givenName;
        this.limit = limit;
        this.balance = balance;
    }

    public CreditCardRequest(CreditCardEntity creditCardEntity) {
         this.cardNumber = EncryptionService.decrypt(creditCardEntity.getCreditCardNumber());
         this.givenName = creditCardEntity.getGivenName();
         this.limit = creditCardEntity.getCreditLimit().doubleValue();
         this.balance = creditCardEntity.getBalance();
    }

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



    public BigDecimal getBalance() {
        return balance;
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
