package github.kanwalnain.creditcard.unit.validation;

import javax.validation.ConstraintValidator;
    import javax.validation.ConstraintValidatorContext;

/**
 * Validator to validate if input credit card number satisfy lunn 10 validation.
 * @author Kanwal Nain Singh
 */
public class LuhnValidator implements ConstraintValidator<LuhnValidation, String> {

    public boolean isValid(String number, ConstraintValidatorContext cxt) {
        return isLuhnNumber(number);
    }

    public Boolean isLuhnNumber(String number) {
        //Remove special characters from input number.
        number = number.replace("-", "");
        number = number.replace(" ", "");

        int sum = 0;
        try {
            boolean alternate = false;
            for (int i = number.length() - 1; i >= 0; i--) {
                int n = Integer.parseInt(number.substring(i, i + 1));
                if (alternate) {
                    n *= 2;
                    if (n > 9) {
                        n = (n % 10) + 1;
                    }
                }
                sum += n;
                alternate = !alternate;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return (sum % 10 == 0);
    }
}
