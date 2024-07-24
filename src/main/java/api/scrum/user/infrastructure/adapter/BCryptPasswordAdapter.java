package api.scrum.user.infrastructure.adapter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import api.scrum.user.domain.ports.out.BCryptPasswordPort;

public class BCryptPasswordAdapter implements BCryptPasswordPort {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    
    public BCryptPasswordAdapter(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public String encode(CharSequence password) {
        return this.bCryptPasswordEncoder.encode(password);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return this.bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
    }
    
}
