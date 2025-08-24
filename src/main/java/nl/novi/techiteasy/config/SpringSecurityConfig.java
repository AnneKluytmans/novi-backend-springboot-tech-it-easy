package nl.novi.techiteasy.config;

import nl.novi.techiteasy.filters.JwtRequestFilter;
import nl.novi.techiteasy.services.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final JwtRequestFilter jwtRequestFilter;
    private final PasswordEncoder passwordEncoder;

    public SpringSecurityConfig(CustomUserDetailsService customUserDetailsService, JwtRequestFilter jwtRequestFilter, PasswordEncoder passwordEncoder) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
        this.passwordEncoder = passwordEncoder;
    }

    // Authenticatie met customUserDetailsService en passwordEncoder
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
        var builder = http.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder);

        return builder.build();
    }


    // Authorisatie met jwt
    @Bean
    protected SecurityFilterChain filter (HttpSecurity http) throws Exception {

        //JWT token authentication
        http
                .csrf(csrf -> csrf.disable())
                .httpBasic(hp -> hp.disable())
                .cors(cors -> {
                })
                .authorizeHttpRequests(auth -> auth
                        // User management
                        .requestMatchers(HttpMethod.GET,"/users").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/users/{username}").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET, "/users/{username}/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/users", "/users/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/users/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")

                        // Authentication
                        .requestMatchers("/authenticated").authenticated()
                        .requestMatchers("/authenticate").permitAll()/*alleen dit punt mag toegankelijk zijn voor niet ingelogde gebruikers*/

                        //Televisions
                        .requestMatchers(HttpMethod.GET, "/televisions", "/televisions/{id}", "/televisions/sales").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/televisions").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/televisions/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/televisions/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/televisions/{id}").hasRole("ADMIN")

                        // Remote controllers
                        .requestMatchers(HttpMethod.GET, "/televisions/{televisionId}/remote-controllers/{remoteControllerId}").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/televisions/{televisionId}/remote-controllers").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/televisions/{televisionId}/remote-controllers/{remoteControllerId}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/televisions/{televisionId}/remote-controllers/{remoteControllerId}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/televisions/{televisionId}/remote-controllers/{remoteControllerId}").hasRole("ADMIN")

                        //Default security
                        .anyRequest().denyAll() /*Deze voeg je altijd als laatste toe, om een default beveiliging te hebben voor eventuele vergeten endpoints of endpoints die je later toevoegd. */
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
