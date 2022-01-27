package spring_boot_security_md4.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_boot_security_md4.demo.model.Role;
import spring_boot_security_md4.demo.repository.RoleRepo;

import java.util.List;
@Service
public class RoleService implements IRoleService{
    @Autowired
    RoleRepo roleRepo;
    @Override
    public List<Role> findAll() {
        return (List<Role>) roleRepo.findAll();
    }
}
