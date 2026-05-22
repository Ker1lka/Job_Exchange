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

    // Основний ланцюжок фільтрів безпеки та авторизації (SecurityFilterChain)
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                // Публічні адреси, доступні абсолютно всім
                                .requestMatchers("/", "/about-us", "/login", "/registration/candidate",
                                        "/registration/company", "/static/**", "/css/**", "/images/**").permitAll()

                                // Перенаправлення користувача за роллю після авторизації
                                .requestMatchers("/success").authenticated()

                                // Доступ суто для кандидатів (безробітних)
                                .requestMatchers("/profile/candidate/**", "/candidate/**").hasAuthority("ROLE_candidate")

                                // Доступ суто для компаній (роботодавців)
                                .requestMatchers("/profile/company/**", "/company/**").hasAuthority("ROLE_company")

                                // Доступ суто для менеджерів платформи
                                .requestMatchers("/manager/**").hasAuthority("ROLE_manager")

                                // Доступ суто для адміністратора
                                .requestMatchers("/admin/**").hasAuthority("ROLE_admin")

                                .anyRequest().authenticated()
                )
                // Налаштування форми входу в систему
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/success", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                        .permitAll()
                )
                // Безпечний вихід із системи з повним анулюванням поточної сесії
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                );
        return http.build();
    }
}