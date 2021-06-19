package com.yudin.project.messanger;

import com.yudin.project.messanger.auth.CustomAuthencationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final AuthenticationEntryPoint authEntryPoint;
    private final CustomAuthencationProvider customAuthencationProvider;

    public SecurityConfig( CustomAuthencationProvider customAuthencationProvider, AuthenticationEntryPoint authEntryPoint) {
        this.customAuthencationProvider = customAuthencationProvider;
        this.authEntryPoint = authEntryPoint;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth){
        auth.authenticationProvider(customAuthencationProvider);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/users/register").permitAll()
                .anyRequest().authenticated()
                .and().httpBasic()
                .authenticationEntryPoint(authEntryPoint);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("john123").password("password").roles("USER");
    }
}
