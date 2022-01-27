package spring_boot_security_md4.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import spring_boot_security_md4.demo.model.Account;

import java.util.ArrayList;

public interface AccountRepo extends PagingAndSortingRepository<Account , Long> {
    Account findByUserName(String userName);

    @Query(value = "select u from Account u where u.userName like concat('%' ,:name, '%')")
    ArrayList<Account> findAllByName(@Param("name") String name);

    @Query(nativeQuery = true, value = "select count(id) from account;")
    Long countAccount();
}
