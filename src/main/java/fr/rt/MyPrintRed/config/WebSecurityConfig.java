package fr.rt.MyPrintRed.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private static final String[] UNSECURED_URL={
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html",
            "/mail/**",
            "/api/**",
            "/checkEmail/**",
            "/resetPassword/{token}/**",
            "/auth/**"

    };
    private static final String[] SECURED_URL_ADMIN={
            "intervenirs/**",
            "options/**",
            "typeoptions/**",
            "categories/**",

    };
    private static final String[] SECURED_URL_STAFF={

    };
    private static final String[] SECURED_URL_USER={
            "/fichiers/**",
            "/utilisateurs/**",
            "/adresses/**",
            "/commandes/**",
            "/lignecommandes/**",
            "/statuses/**",
            "/optionlignecommandes",
            "optioncategories"
    };


    private final JwtAuthenticationFilter  jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http

                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers(UNSECURED_URL)
                .permitAll()
                .and()
                .authorizeHttpRequests()
                .requestMatchers(SECURED_URL_USER)
                .hasAnyAuthority("USER","STAF","ADMIN")
                .and()
                .authorizeHttpRequests()
                .requestMatchers(SECURED_URL_STAFF)
                .hasAnyAuthority("STAF","ADMIN")
                .and()
                .authorizeHttpRequests()
                .requestMatchers(SECURED_URL_ADMIN)
                .hasAnyAuthority("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .cors();



        return http.build();
    }
}
