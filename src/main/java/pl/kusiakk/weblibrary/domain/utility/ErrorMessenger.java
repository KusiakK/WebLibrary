package pl.kusiakk.weblibrary.domain.utility;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ErrorMessenger {

    private static ErrorMessenger instance;
    private Validator validator;

    private ErrorMessenger() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public static ErrorMessenger getInstance() {
        if (null == instance) {
            instance = new ErrorMessenger();
        }
        return instance;
    }

    public <T> List<String> getMessages(T object) {
        List <String> messages = new ArrayList<>();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(object);
        for (ConstraintViolation<T> violation : constraintViolations) {
            messages.add(violation.getPropertyPath().toString() + " " + violation.getMessage());
        }
        return messages;
    }
}
