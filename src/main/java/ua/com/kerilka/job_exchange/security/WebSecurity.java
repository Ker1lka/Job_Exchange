package ua.com.kerilka.job_exchange.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ua.com.kerilka.job_exchange.repository.UserRepository;
import ua.com.kerilka.job_exchange.service.UserService;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurity {

    private final UserService userService;

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                //All Users
                                .requestMatchers("/", "/about-us", "/login", "/registration/candidate", "/registration/company").permitAll()

                                //Authorized
                                .requestMatchers("/success").authenticated()

                                //Candidates
                                .requestMatchers("/thanks", "/profile/candidate/**", "/candidate/**").hasAuthority("ROLE_candidate")

                                //Company
                                .requestMatchers("/profile/company/**", "/company/**").hasAuthority("ROLE_company")

                                //Manager
                                .requestMatchers("/manager").hasAuthority("ROLE_manager")

                                //Admin
                                .requestMatchers("/admin").hasAuthority("ROLE_admin")

                                .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/success", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                        .permitAll()
                );

        return http.build();
    }
}