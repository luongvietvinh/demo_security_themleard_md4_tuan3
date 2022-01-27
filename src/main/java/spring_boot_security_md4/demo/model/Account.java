package spring_boot_security_md4.demo.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotEmpty(message = "user_name ko duoc de trong")
    @Size(min = 4,max = 9,message = "min = 4 && max = 9")
    private String userName;
    @NotEmpty(message = "password ko duoc de trong")
    private String password;
    @NotEmpty(message = "password ko duoc de trong")
    private String phoneNumber;
    @NotEmpty(message = "password ko duoc de trong")
    private String email;
    private String img;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;
}
