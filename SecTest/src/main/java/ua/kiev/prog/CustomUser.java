package ua.kiev.prog;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static ua.kiev.prog.authentication.Authentication.generateAuthKey;

@Entity
@Data @NoArgsConstructor
public class CustomUser {
    @Id
    @GeneratedValue
    private Long id;

    private String login;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    private String email;
    private String phone;
    private String age;

    private boolean authentication;
    private String authKey;

    public CustomUser(String login, String password, UserRole role, String email, String phone, String age) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.email = email;
        this.phone = phone;
        this.age = age;
        this.authentication = false;
        this.authKey = generateAuthKey();

    }

}
