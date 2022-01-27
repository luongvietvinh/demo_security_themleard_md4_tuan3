package spring_boot_security_md4.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import spring_boot_security_md4.demo.model.Role;

public interface RoleRepo extends PagingAndSortingRepository<Role , Long> {
}
