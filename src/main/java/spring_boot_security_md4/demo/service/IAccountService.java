package spring_boot_security_md4.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import spring_boot_security_md4.demo.model.Account;

import java.util.List;

public interface IAccountService extends UserDetailsService {

    Page<Account> findAllPage(Pageable pageable);

    List<Account> findAll();

    void save(Account account);

    void delete(long id);

    Account findById(long id);

    List<Account> findByName(String name);

}
