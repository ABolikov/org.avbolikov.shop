package org.avbolikov.shop.validation.product;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BigDecimalConstraintValidator.class)
public @interface NotBlankBigDecimal {

    String message() default "Обязательно для заполнения";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
