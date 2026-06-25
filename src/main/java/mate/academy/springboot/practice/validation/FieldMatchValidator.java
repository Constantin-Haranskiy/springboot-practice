package mate.academy.springboot.practice.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Objects;
import org.apache.commons.beanutils.BeanUtils;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {
    private String expectedFieldName;
    private String actualFieldName;

    @Override
    public void initialize(FieldMatch fieldMatch) {
        expectedFieldName = fieldMatch.expected();
        actualFieldName = fieldMatch.actual();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return true;
        }

        try {
            Object expectedObj = BeanUtils.getProperty(value, expectedFieldName);
            Object actualObj = BeanUtils.getProperty(value, actualFieldName);
            return Objects.equals(expectedObj, actualObj);
        } catch (Exception e) {
            return false;
        }
    }
}
