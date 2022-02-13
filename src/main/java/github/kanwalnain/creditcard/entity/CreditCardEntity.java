package github.kanwalnain.creditcard.entity;

import github.kanwalnain.creditcard.model.CreditCardRequest;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Entity Class to persist credit card details.
 * @Author  Kanwal Nain Singh
 */
@Entity
@Table(name = "customers")
public class CreditCardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String creditCardNumber;
    private BigDecimal creditLimit;
    private String givenName;
    private BigDecimal balance;
    private String currency = "GBP";

    public CreditCardEntity() {

    }

    /**
     * Generate credit card entity from credit card request.
     * @param creditCardRequest
     */
    public CreditCardEntity(CreditCardRequest creditCardRequest) {
        creditCardNumber = creditCardRequest.getCardNumber();
        creditLimit =  new BigDecimal(creditCardRequest.getLimit());
        givenName = creditCardRequest.getGivenName();
        balance = BigDecimal.ZERO;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "CreditCardEntity{" +
                "id=" + id +
                ", creditCardNumber='" + creditCardNumber + '\'' +
                ", creditLimit=" + creditLimit +
                ", balance=" + balance +
                ", currency='" + currency + '\'' +
                '}';
    }
}
