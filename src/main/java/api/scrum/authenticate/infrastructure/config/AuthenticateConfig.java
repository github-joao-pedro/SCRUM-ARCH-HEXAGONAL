package api.scrum.authenticate.infrastructure.config;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;

import api.scrum.authenticate.application.services.AuthService;
import api.scrum.authenticate.application.usecase.AuthUseCaseImpl;
import api.scrum.authenticate.application.usecase.ValidateUseCaseImpl;
import api.scrum.authenticate.domain.ports.out.AuthJwtPort;
import api.scrum.authenticate.infrastructure.adapter.AuthJwtAdapter;
import api.scrum.user.domain.ports.out.BCryptPasswordPort;
import api.scrum.user.domain.ports.out.UserRepositoryPort;

@Configuration
@EnableWebSecurity
public class AuthenticateConfig {
    
    @Value("${jwt.public.key}")
    private RSAPublicKey publicKey;

    @Value("${jwt.private.key}")
    private RSAPrivateKey privateKey;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers(HttpMethod.POST,"/api/v1/auth").permitAll()
                .requestMatchers(HttpMethod.GET,"/api/v1/auth").permitAll()
                .requestMatchers(HttpMethod.POST,"/api/v1/user").permitAll()
                .anyRequest().authenticated())
            .csrf(csrf -> csrf.disable())
            .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }
    
    @Bean
    public JwtEncoder jwtEncoder() {
        JWK jwk = new RSAKey.Builder(this.publicKey).privateKey(privateKey).build();
        var jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }
    
    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(publicKey).build();
    }

    @Bean
    public AuthJwtPort authJwtPort(JwtEncoder jwtEncoder, JwtDecoder jwtDecoder) {
        return new AuthJwtAdapter(jwtEncoder, jwtDecoder);
    }

    @Bean
    public AuthService authService(UserRepositoryPort userRepositoryPort, BCryptPasswordPort bCryptPasswordPort, AuthJwtPort authJwtPort, ModelMapper modelMapper) {
        return new AuthService(
            new AuthUseCaseImpl(userRepositoryPort, bCryptPasswordPort, authJwtPort, modelMapper),
            new ValidateUseCaseImpl(authJwtPort)
        );
    }
}
