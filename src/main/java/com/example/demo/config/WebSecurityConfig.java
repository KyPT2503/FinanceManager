package com.example.demo.config;
import com.example.demo.config.CustomSuccessHandler;
import com.example.demo.service.user.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        // Sét đặt dịch vụ để tìm kiếm User trong Database.
        // Và sét đặt PasswordEncoder.
        auth.userDetailsService(appUserService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/").permitAll()
                    .antMatchers("/user").access("hasRole('ROLE_USER')")
                .and()
                .authorizeRequests().antMatchers("/balance/**").access("hasRole('ROLE_USER')")
                .and()
                .authorizeRequests().antMatchers(HttpMethod.GET,"/wallets/**").access("hasRole('ROLE_USER')")
                .and()
                .authorizeRequests().antMatchers(HttpMethod.POST,"/wallets/**").access("hasRole('ROLE_USER')")
                .and().csrf().disable()
                .authorizeRequests().antMatchers(HttpMethod.PUT,"/wallets/**").access("hasRole('ROLE_USER')")
                .and()
                .authorizeRequests().antMatchers(HttpMethod.DELETE,"/wallets/**").access("hasRole('ROLE_USER')")
                .and()

                .formLogin()
                .loginPage("/login")//
                .loginProcessingUrl("/check_login") // Submit URL
//                .defaultSuccessUrl("/user")
                .failureUrl("/login?error=true")//
                .usernameParameter("email")//
                .passwordParameter("password")
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
    }
}
