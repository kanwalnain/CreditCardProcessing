package github.kanwalnain.creditcard.validation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;
import javax.validation.ConstraintValidatorContext;

import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestLuhnValidator {
    private ConstraintValidatorContext constraintValidatorContext;
    private LuhnValidator luhnValidator;
    @Before
    public void setUp(){
        constraintValidatorContext = mock(ConstraintValidatorContext.class);
        luhnValidator = new LuhnValidator();
    }

    @Test
    public void shouldReturnTrueIfCreditCardSatisfyLuhn10(){
        assertTrue(luhnValidator.isValid("5555555555554444",constraintValidatorContext));
        assertTrue(luhnValidator.isValid("6703 4444 4444 4449",constraintValidatorContext));
        assertTrue(luhnValidator.isValid("5066-9911-1111-1118",constraintValidatorContext));
    }

    @Test
    public void shouldReturnFalseIfCreditCardDoesNotSatisfyLuhn10(){
        assertFalse(luhnValidator.isValid("555555555223235554444",constraintValidatorContext));
    }

    @Test
    public void shouldReturnFalseIfCreditCardDoesNotSatisfyLuhn10InvalidCharacter(){
        assertFalse(luhnValidator.isValid("5555555SDSDS55223235SDSDSD554444",constraintValidatorContext));
    }

}
