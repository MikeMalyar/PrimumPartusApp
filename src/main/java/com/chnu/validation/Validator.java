package com.chnu.validation;

import com.chnu.exception.GeneralValidationException;
import com.chnu.util.LoggerUtil;
import org.apache.logging.log4j.Logger;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.util.Set;
import java.util.stream.Collectors;

public class Validator {

    private static javax.validation.Validator validator;
    private static final Logger logger = LoggerUtil.getLogger(Validator.class);

    public static <T> void validate(T object) {
        if(validator == null) {
            validator = Validation.buildDefaultValidatorFactory().getValidator();
        }

        Set<ConstraintViolation<T>> violations = validator.validate(object);

        if(!violations.isEmpty()) {
            throw new GeneralValidationException("Validation error")
                    .setValidationErrors(
                        violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList())
                    ).setClazz(object.getClass());
        }
    }

}
