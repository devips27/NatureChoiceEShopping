package com.naturechoice.springmvceshopping.validator;
 
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.naturechoice.springmvceshopping.model.CustomerInfo;
 
// @Component: As a Bean.
@Component
public class CustomerInfoValidator implements Validator {
 
    private EmailValidator emailValidator = EmailValidator.getInstance();
 
    // This Validator support CustomerInfo class.
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == CustomerInfo.class;
    }
 
    @Override
    public void validate(Object target, Errors errors) {
        CustomerInfo custInfo = (CustomerInfo) target;
 
        // Check the fields of CustomerInfo class.
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.customerForm.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.customerForm.email");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "NotEmpty.customerForm.address");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "NotEmpty.customerForm.phone");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "curbsideChecked", "NotEmpty.customerForm.curbsideChecked");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "paymentMethod", "NotEmpty.customerForm.paymentMethod");
 
        if (!emailValidator.isValid(custInfo.getEmail())) {
            errors.rejectValue("email", "Pattern.customerForm.email");
        }
    }
 
}