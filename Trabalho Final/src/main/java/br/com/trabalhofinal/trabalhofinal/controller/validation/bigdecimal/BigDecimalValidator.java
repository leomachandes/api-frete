package br.com.trabalhofinal.trabalhofinal.controller.validation.bigdecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class BigDecimalValidator implements ConstraintValidator<BigDecimalValid, BigDecimal> {

    private  BigDecimal valor;


    @Override
    public boolean isValid(BigDecimal value, ConstraintValidatorContext context) {

        try {
            if (value.compareTo(BigDecimal.ZERO) > 0) return  true;
            return  false;
        } catch (Exception e) {
            return false;
        }

    }
}
