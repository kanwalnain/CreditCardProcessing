package github.kanwalnain.creditcard.entity;

import github.kanwalnain.creditcard.model.CreditCardRequest;
import github.kanwalnain.creditcard.util.EncryptionService;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Entity Class to persist credit card details.
 * @Author  Kanwal Nain Singh
 */
@Entity
@Table(name = "credit_card_details")
public class CreditCardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "credit_card_number", unique=true)
    private String creditCardNumber;
    @Column(name = "credit_limit")
    private BigDecimal creditLimit;
    @Column(name = "given_name")
    private String givenName;
    @Column(name="card_balance")
    private BigDecimal balance;


    public CreditCardEntity() {

    }

    public Long getId() {
        return id;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public String getGivenName() {
        return givenName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public static CreditCardEntity.CreditCardEntityBuilder builder() {
        return new CreditCardEntity.CreditCardEntityBuilder();
    }

    private CreditCardEntity(final Long id, final String givenName, final String creditCardNumber, final  BigDecimal creditLimit, final  BigDecimal balance) {
        this.id = id;
        this.givenName = givenName;
        this.creditLimit = creditLimit;
        this.creditCardNumber = creditCardNumber;
        this.balance = balance;
    }

    public static class CreditCardEntityBuilder {
        private Long id;
        private String creditCardNumber;
        private BigDecimal creditLimit;
        private String givenName;
        private BigDecimal balance;

        CreditCardEntityBuilder() {
        }

        public CreditCardEntity.CreditCardEntityBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        public CreditCardEntity.CreditCardEntityBuilder name(final String givenName) {
            this.givenName = givenName;
            return this;
        }

        public CreditCardEntity.CreditCardEntityBuilder cardNumber(final String creditCardNumber) {
            this.creditCardNumber = creditCardNumber;
            return this;
        }

        public CreditCardEntity.CreditCardEntityBuilder limit(final BigDecimal creditLimit) {
            this.creditLimit = creditLimit;
            return this;
        }

        public CreditCardEntity.CreditCardEntityBuilder balance(final BigDecimal balance) {
            this.balance = balance;
            return this;
        }

        /**
         * Generate credit card entity from credit card request.
         * @param creditCardRequest
         */
        public CreditCardEntity.CreditCardEntityBuilder creditCardRequest(CreditCardRequest creditCardRequest) {
            this.creditCardNumber = EncryptionService.encrypt(creditCardRequest.getCardNumber());
            this.creditLimit =  creditCardRequest.getLimit();
            this.givenName = creditCardRequest.getGivenName();
            this.balance = creditCardRequest.getBalance();
            return this;
        }

        public CreditCardEntity build() {
            return new CreditCardEntity(this.id, this.givenName, this.creditCardNumber, this.creditLimit, this.balance);
        }
    }
}
