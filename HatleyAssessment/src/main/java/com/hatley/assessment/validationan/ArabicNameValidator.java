package com.hatley.assessment.validationan;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.hatley.assessment.exceptionutils.IllegalArabicNameException;

public class ArabicNameValidator  implements ConstraintValidator<ArabicCharValidator, String>  {
	private static final String PATTERN = "^[\\u0621-\\u064A\\u0660-\\u0669 ]+$";
	@Override
    public void initialize( ArabicCharValidator  arabicCharValidator) {
    }

    @Override
    public boolean isValid (String arabicName,
      ConstraintValidatorContext cxt)  {
        if ( !arabicName.matches(PATTERN)
          || (arabicName.length() >100)) throw new IllegalArabicNameException("Invalid arabic name  please provide the correct arabic name with limit of 100 charachters") ;
            return true ;    
    }

}
