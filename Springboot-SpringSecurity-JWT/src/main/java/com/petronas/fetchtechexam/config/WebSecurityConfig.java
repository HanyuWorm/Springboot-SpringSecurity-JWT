package com.petronas.fetchtechexam.config;

import com.petronas.fetchtechexam.filtes.JWTAuthenticationFilter;
import com.petronas.fetchtechexam.filtes.JWTLoginFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

/**
 *  * Created by hungtv on 09s/10/2018.
 */
@Configuration
@EnableWebSecurity
@EnableWebMvc
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    public static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers(HttpMethod.GET, "/admin").permitAll()
                .antMatchers(HttpMethod.GET, "/home").permitAll()
                .antMatchers(HttpMethod.GET, "/access-denied").permitAll()
                .antMatchers(HttpMethod.POST, "/jwt/gettoken").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JWTLoginFilter("/token", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("{noop}password").roles("admin");
        //auth.inMemoryAuthentication().withUser("admin").password("password").roles("admin");

//        auth.jdbcAuthentication().dataSource(dataSource)
//                .usersByUsernameQuery("select username,password,  from \"user\" where  username=? and password =?")
//                //.authoritiesByUsernameQuery("select username, role from \"username\" where username=?")
//                .passwordEncoder(new BCryptPasswordEncoder());
//        try {
//            auth.jdbcAuthentication().dataSource(dataSource)
//                    .usersByUsernameQuery("select username, true as enabled from \'user\" where  username=?")
//                    .authoritiesByUsernameQuery("select username, role from \"user\" where username=?")
//                    .passwordEncoder(bCryptPasswordEncoder);
//            logger.info("START check "+auth.jdbcAuthentication());
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }



    }

}
