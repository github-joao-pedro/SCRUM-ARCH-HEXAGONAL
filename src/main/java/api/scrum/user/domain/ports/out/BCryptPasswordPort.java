package api.scrum.user.domain.ports.out;

public interface BCryptPasswordPort {
    String encode(CharSequence password);
    boolean matches(CharSequence rawPassword, String encodedPassword);
}
