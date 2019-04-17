package com.lemon.security.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class IdCardValidator implements ConstraintValidator<IsIdCard, String> {
   public void initialize(IsIdCard constraint) {
      String message = constraint.message();
      System.out.println("用户自定义的message信息是：".concat(message));
   }

   public boolean isValid(String obj, ConstraintValidatorContext context) {
      System.out.println(obj);
      String regex = "\\d{18}(\\d{2}[0-9xX])?";
      System.out.println(obj.matches(regex));
      return obj.matches(regex);
   }
}
