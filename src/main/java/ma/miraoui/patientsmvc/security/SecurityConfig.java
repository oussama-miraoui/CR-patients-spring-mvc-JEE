package ma.miraoui.patientsmvc.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = passwordEncoder();
        String encodedPassword = passwordEncoder.encode("1234");
        auth.inMemoryAuthentication().withUser("oussama").password(encodedPassword).roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password(encodedPassword).roles("USER","ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin();
        http.authorizeRequests().antMatchers(
                "/delete/**",
                "/edit/**",
                "/save/**",
                "/formPatients/**" ).hasRole("ADMIN");

        http.authorizeRequests().antMatchers("/index/**").hasRole("USER");

        http.exceptionHandling().accessDeniedPage("/403");

        http.authorizeRequests().anyRequest().authenticated();

    }

    //cré au démarrage un objet de type PasswordEncoder
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}

