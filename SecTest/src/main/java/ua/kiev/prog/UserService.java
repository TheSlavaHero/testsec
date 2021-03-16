package ua.kiev.prog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static ua.kiev.prog.mail.SendAuthCodeViaMail.send;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<CustomUser> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public CustomUser findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Transactional
    public void deleteUsers(List<Long> ids) {
        ids.forEach(id -> {
            Optional<CustomUser> user = userRepository.findById(id);
            user.ifPresent(u -> {
                if ( ! AppConfig.ADMIN.equals(u.getLogin())) {
                    userRepository.deleteById(u.getId());
                }
            });
        });
    }

    @Transactional
    public boolean addUser(String login, String passHash,
                           UserRole role, String email,
                           String phone, String age) {
        if (userRepository.existsByLogin(login))
            return false;

        CustomUser user = new CustomUser(login, passHash, role, email, phone, age);
        userRepository.save(user);
        send(email, user.getAuthKey());

        return true;
    }

    @Transactional
    public void updateUser(String login, String email, String phone, String age) {
        CustomUser user = userRepository.findByLogin(login);
        if (user == null)
            return;

        user.setEmail(email);
        user.setPhone(phone);
        user.setAge(age);

        userRepository.save(user);
    }

    @Transactional
    public void updateUser(String login, Boolean authentication, String authKey) {
        CustomUser user = userRepository.findByLogin(login);
        if (user == null)
            return;

        user.setAuthentication(authentication);
        user.setAuthKey(authKey);

        userRepository.save(user);
    }

    public void addUser(String admin, String password, UserRole admin1, String s, String s1) {
    }
}
