package com.luiz.biblioteca_pessoal.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${admin.password}")
    private String adimnPassword;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // 1. LIBERADO GERAL (Arquivos do site e Vitrine)
                        .requestMatchers("/", "/index.html", "/style.css", "/script.js", "/images/**").permitAll()

                        // 2. API PÚBLICA (Vitrine e Indicação)
                        .requestMatchers(HttpMethod.GET, "/livros").permitAll()
                        .requestMatchers(HttpMethod.POST, "/livros").permitAll()

                        // 3. ÁREA RESTRITA (Admin)
                        .requestMatchers("/admin.html").authenticated()          // Só você entra na Batcaverna
                        .requestMatchers(HttpMethod.PUT, "/livros/**").authenticated()    // Só você edita
                        .requestMatchers(HttpMethod.DELETE, "/livros/**").authenticated() // Só você deleta

                        // 4. O RESTO BLOQUEIA
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .permitAll()
                        .defaultSuccessUrl("/admin.html", true)
                )
                .logout(logout -> logout.permitAll());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("luiz")
                .password(adimnPassword)
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(admin);
    }

}
