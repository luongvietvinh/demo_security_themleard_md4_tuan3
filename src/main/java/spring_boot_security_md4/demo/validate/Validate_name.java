package spring_boot_security_md4.demo.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import spring_boot_security_md4.demo.model.Account;
import spring_boot_security_md4.demo.service.IAccountService;

import java.util.List;
@Component
public class Validate_name implements Validator {
    @Autowired
    IAccountService accountService;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }
    @Override
    public void validate(Object target, Errors errors) {
        Account user = (Account) target;
        List<Account> users = accountService.findAll();
        for (Account u: users) {
            if (user.getUserName().equals(u.getUserName()) && (u.getId() != user.getId())){
                errors.rejectValue("userName","","userName da ton tai, moi nhap ten khac");
                return;
            }
        }
    }
}
