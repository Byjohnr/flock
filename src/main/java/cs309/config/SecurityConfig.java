package cs309.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

/**
 * Created by jeffrey on 2/15/16.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                    .antMatchers("/bower_components/**","/console/**").permitAll()
                    .anyRequest()
                    .permitAll()
//                    .authenticated()
                    .and()
                .formLogin()
                    .loginPage("/main")
                    .usernameParameter("email").passwordParameter("password")
                    .permitAll()
                    .and()
                .logout()
                    .permitAll()
                    .and()
                .csrf()
                    .disable();

        httpSecurity.headers().frameOptions().disable();

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                    .withUser("admin").password("password").roles("ADMIN", "USER")
                .and()
                    .withUser("user").password("password").roles("USER");
    }

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder())
                .usersByUsernameQuery(
                "select email,password from user where email=?")
                .authoritiesByUsernameQuery("");
    }
}
