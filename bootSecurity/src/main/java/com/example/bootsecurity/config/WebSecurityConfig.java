package com.example.bootsecurity.config;

//import com.example.bootsecurity.domain.User;
//import com.example.bootsecurity.repos.UserDetailsRepo;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import java.time.LocalDateTime;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .authorizeRequests()
                .csrf().disable()
                .antMatcher("/**").authorizeRequests()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout(l -> l
                        .logoutSuccessUrl("/").permitAll())
                .oauth2Login();
    }
//    @Bean
//    public PrincipalExtractor principalExtractor(UserDetailsRepo userDetailsRepo) {
//        return map -> {
//            String id = (String) map.get("sub");
//
//            User user = userDetailsRepo.findById(id).orElseGet(() -> {
//                User newUser = new User();
//
//                newUser.setId(id);
//                newUser.setName((String) map.get("name"));
//                newUser.setEmail((String) map.get("email"));
//                newUser.setGender((String) map.get("gender"));
//                newUser.setLocale((String) map.get("locale"));
//                newUser.setUserpic((String) map.get("picture"));
//
//                return newUser;
//            });
//
//            user.setLastVisit(LocalDateTime.now());
//
//            return userDetailsRepo.save(user);
//        };
//    }
}