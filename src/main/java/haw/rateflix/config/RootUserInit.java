package haw.rateflix.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import haw.rateflix.domain.User;
import haw.rateflix.repository.UserRepository;

@Profile("prod")
@Configuration
public class RootUserInit implements CommandLineRunner {

    @Value("${ROOT_USERNAME} = admin")
    private String rootUsername;

    @Value("${ROOT_PASSWORD} = adminpass")
    private String rootPassword;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) {
        if (!userRepository.existsByUsername(rootUsername)) {
            User rootUser = new User();
            rootUser.setUsername(rootUsername);
            rootUser.setPassword(passwordEncoder.encode(rootPassword));
            userRepository.save(rootUser);
        }
    }
}
