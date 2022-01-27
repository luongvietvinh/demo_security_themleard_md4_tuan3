package spring_boot_security_md4.demo.service;

import spring_boot_security_md4.demo.model.Account;
import spring_boot_security_md4.demo.model.Role;

import java.util.List;

public interface IRoleService {

    public List<Role> findAll();

}
