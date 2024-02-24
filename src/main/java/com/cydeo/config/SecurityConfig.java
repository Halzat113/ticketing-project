package com.cydeo.config;

import com.cydeo.service.SecurityService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

        private final SecurityService securityService;
        private final AuthenticationSuccessHandler successHandler;

    public SecurityConfig(SecurityService securityService, AuthenticationSuccessHandler successHandler) {
        this.securityService = securityService;
        this.successHandler = successHandler;
    }
    //    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder encoder){
//        List<UserDetails> userList = new ArrayList<>();
//        userList.add(
//                new User("mike",encoder.encode("password"), Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"))));
//        userList.add(
//                new User("halzat",encoder.encode("password"), Arrays.asList(new SimpleGrantedAuthority("ROLE_MANAGER"))));
//        return new InMemoryUserDetailsManager(userList);
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests()
                //.antMatchers("/user/**").hasRole("Admin")
                .antMatchers("/user/**").hasAnyAuthority("Admin")
                .antMatchers("/project/**").hasAnyAuthority("Manager")
                .antMatchers("/task/employee/**").hasAnyAuthority("Employee")
                .antMatchers("/task/**").hasAnyAuthority("Manager")

                .antMatchers(
                        "/",
                        "/login",
                        "/fragments/**",
                        "/assets/**",
                        "/images/**"
                ).permitAll()
                .anyRequest().authenticated()
                .and()
               // .httpBasic()
                .formLogin()
                    .loginPage("/login")
//                    .defaultSuccessUrl("/welcome")
                .successHandler(successHandler)
                    .failureUrl("/login?error=true")
                    .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .and()
                .rememberMe()
                    .tokenValiditySeconds(120)
                    .key("example")
                    .userDetailsService(securityService )
                .and()
                .build();
    }
}
