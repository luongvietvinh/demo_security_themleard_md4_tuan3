package spring_boot_security_md4.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spring_boot_security_md4.demo.model.Account;
import spring_boot_security_md4.demo.repository.AccountRepo;

import java.util.List;
@Service
public class AccountService implements IAccountService{
    @Autowired
    AccountRepo accountRepo;

    @Override
    public Page<Account> findAllPage(Pageable pageable) {
        return accountRepo.findAll(pageable);
    }

    @Override
    public List<Account> findAll() {
        return (List<Account>) accountRepo.findAll();
    }

    @Override
    public void save(Account account) {
    accountRepo.save(account);
    }

    @Override
    public void delete(long id) {
        accountRepo.deleteById(id);
    }

    @Override
    public Account findById(long id) {
        return accountRepo.findById(id).get();
    }

    @Override
    public List<Account> findByName(String name) {
        return accountRepo.findAllByName(name);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepo.findByUserName(username);
        return new User(account.getUserName(),account.getPassword(),account.getRoles());
    }
}
